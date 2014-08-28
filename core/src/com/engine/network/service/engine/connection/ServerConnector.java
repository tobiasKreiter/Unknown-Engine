/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engine.network.service.engine.connection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author tobias
 */
public class ServerConnector {

    /**
     * Address of the server.
     */
    private final String SERVER_ADDRESS = "unknown-engine.com";
    /**
     * Port of the server.
     */
    private final int PORT = 2357;
    /**
     * Time in which a answer should be received.
     */
    public static int timeout = 10000;
    /**
     * Indicates whether the client has a connection to the server.
     */
    private boolean isConnected = false;

    /**
     * Tries to connect to the server
     *
     * @return socket if have connection otherwise null
     */
    public Socket connectToServer() {
        Socket socket = null;
        try {
            socket = new Socket();
            socket.setSoTimeout(timeout);
            socket.connect(new InetSocketAddress(InetAddress.getByName(SERVER_ADDRESS), PORT), timeout);
            isConnected = true;
        } catch (IOException ex) {
            try {
                Thread.sleep(timeout);
            } catch (Exception e) {
            }
            isConnected = false;
        }
        return socket;
    }

    /**
     * Returns if a connection exist.
     *
     * @return have connection to server
     */
    public boolean hasConnection() {
        return isConnected;
    }

    /**
     * Sets the conncetion as close.
     */
    public void connectionClosed() {
        isConnected = false;
    }

    /**
     * Returns the timeout.
     *
     * @return socket timeout
     */
    public static int getTimeout() {
        return timeout;
    }

    /**
     * Sets a new socket timeout.
     *
     * @param timeout new socket timeout
     */
    public static void setTimeout(int timeout) {
        ServerConnector.timeout = timeout;
    }
}
