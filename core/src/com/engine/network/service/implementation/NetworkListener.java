/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engine.network.service.implementation;

import com.engine.network.service.engine.connection.Connection;
import com.engine.network.service.message.ErrorMessage;
import com.engine.network.service.message.Message;
import com.engine.network.service.message.impression.ImpressionMessage;

/**
 * @author tobias
 */
public class NetworkListener {

    /**
     * Connection with the server.
     */
    protected Connection connection;
    /**
     * Id of a game.
     */
    private int gameID;
    /**
     *
     */
    private NetworkInterface networkInterface;

    public NetworkListener(int gameID, NetworkInterface networkInterface) {
        this.gameID = gameID;
        this.networkInterface = networkInterface;
    }

    /**
     * Connects to the server
     */
    public void connect() {
        if (connection == null) {
            try {
                connection = new Connection(networkInterface);
                connection.start();
                connection.sendNewMessage(new ImpressionMessage(gameID));
            } catch (Exception ex) {
                networkInterface.onNetworkFailure(new ErrorMessage(ErrorMessage.NOT_REACHABLE));
            }
        }
    }

    /**
     * Sends a message to the Server. Message will be encrypted.
     *
     * @param message message which are sent to the server
     */
    public void sendMessage(Message message) {
        if (connection != null) {
            connection.sendNewMessage(message);
        }
    }

    public void closeConnection() {
        if (connection != null) {
            connection.closeConnection();
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
