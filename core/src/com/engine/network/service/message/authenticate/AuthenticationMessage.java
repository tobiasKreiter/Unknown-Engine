/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engine.network.service.message.authenticate;

import com.engine.network.service.message.Message;

/**
 *
 * @author tobias
 */
public class AuthenticationMessage extends Message {

    /**
     * Contains an unique id which is sent to the sever to authenticate the
     * device.
     */
    public String uniqueID;
    /**
     * ID of game
     */
    public int gameID;

    public AuthenticationMessage() {
    }

    public AuthenticationMessage(String uniqueID, int gameID) {
        super(Message.AUTHENTICATION);
        this.uniqueID = uniqueID;
        this.gameID = gameID;
    }

    /**
     * Returns the unique id.
     *
     * @return unique id
     */
    public String getUniqueID() {
        return uniqueID;
    }
    
    /**
     * Returns the game id
     * @return game id
     */
    public int getGameID() {
        return gameID;
    }

}
