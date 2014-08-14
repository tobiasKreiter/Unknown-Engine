/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engine.network.service.engine.connection;

import com.engine.network.service.engine.connection.encryption.AES;
import com.engine.network.service.implementation.NetworkInterface;
import com.engine.network.service.message.ErrorMessage;
import com.engine.network.service.message.Message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author tobias
 */
public class Connection extends Thread {

    /**
     * Connection to the Server.
     */
    private Socket socket;
    /**
     * Implementing class which receives the messages.
     */
    private NetworkInterface networkListener;
    /**
     * Pipeline direct to the server.
     */
    private DataOutputStream out;
    /**
     * Pipeline from server to client.
     */
    private DataInputStream in;
    /**
     * Creates a connection to the server and returns the socket.
     */
    private ServerConnector serverConnector;
    /**
     * Indicates if the server is running.
     */
    private boolean isRunning = true;
    /**
     * Encryption and decryption object
     */
    private AES aes;
    /**
     * Queue containing all messages which should be send.
     */
    private ArrayList<Message> messagesToSend = new ArrayList();
    /**
     * Checks in this intervall if the server is reachable.
     */
    private int checkAliveIntervall = 2000;
    /**
     * Unimportant.
     */
    private int timeCounter = 0;

    public Connection(NetworkInterface networkListener) throws Exception {
        this.networkListener = networkListener;
        this.aes = new AES();
    }

    /**
     * Main-Thread of the network. Sends and receives messages and redirects them to the network interface.
     */
    public void run() {
        connectToServer();
        while (isRunning) {
            if (serverConnector.hasConnection()) {
                if (!messagesToSend.isEmpty()) {
                    Message response = sendAndReceiveMessage();
                    if (response == null) {
                        networkListener.onNetworkFailure(new ErrorMessage(ErrorMessage.TIMEOUT));
                    } else if (response.getType() == Message.ERROR) {
                        networkListener.onNetworkFailure((ErrorMessage) response);
                    } else {
                        networkListener.onNetworkResponse(response);
                    }
                } else {
                    sleep();
                    timeCounter++;
                    if (timeCounter >= checkAliveIntervall) {
                        timeCounter = 0;
                        try {
                            out.write(1);
                            out.write(new byte[]{1}, 0, 1);
                        } catch (Exception e) {
                            closeConnection();
                        }
                    }
                }
            } else {
                connectToServer();
            }
        }
    }

    /**
     * Sends a message and receives the answer from the server.
     *
     * @return message which was received
     */
    private Message sendAndReceiveMessage() {
        try {
            if (!messagesToSend.isEmpty()) {
                String plainText = messagesToSend.get(0).toString();
                byte[] message = aes.encrypt(plainText);
                out.writeInt(message.length);
                out.write(message, 0, message.length);
                messagesToSend.remove(0);
                int msgLen = in.readInt();
                byte[] msg = new byte[msgLen];
                in.readFully(msg);
                String answer = aes.decrypt(msg);
                Message respone = Message.toMessage(answer);
                return respone;
            }
        } catch (IOException ex) {
            closeConnection();
            return new ErrorMessage(ErrorMessage.NOT_REACHABLE);
        } catch (Exception e) {
            closeConnection();
            return new ErrorMessage(ErrorMessage.ENCRYPTION_FAILURE);
        }
        closeConnection();
        return new ErrorMessage(ErrorMessage.UNKNOWN_PACKAGE);
    }

    /**
     * Pauses the thread for 1 milliseconds so the thread dont go crazy.
     */
    private void sleep() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException ex) {
        }
    }

    /**
     * Tries to connect to the server.
     */
    private void connectToServer() {
        try {
            serverConnector = new ServerConnector();
            socket = serverConnector.connectToServer();
            if (serverConnector.hasConnection()) {
                out = new DataOutputStream(socket.getOutputStream());
                in = new DataInputStream(socket.getInputStream());
            }
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Adds a new message to the queue
     *
     * @param message
     */
    public void sendNewMessage(Message message) {
        this.messagesToSend.add(message);
    }

    /**
     * Closes the connection.
     */
    public void closeConnection() {
        serverConnector.connectionClosed();
    }

    /**
     * Returns if the client has a connection
     *
     * @return
     */
    public boolean connected() {
        return serverConnector.hasConnection();
    }

    /**
     * @return
     */
    public int getCheckAliveIntervall() {
        return checkAliveIntervall;
    }

    /**
     * Sets a new intervall for new connection check.
     *
     * @param checkAliveIntervall intervall in milliseconds
     */
    public void setCheckAliveIntervall(int checkAliveIntervall) {
        this.checkAliveIntervall = checkAliveIntervall;
    }
}
