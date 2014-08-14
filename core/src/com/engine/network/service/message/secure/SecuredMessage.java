/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engine.network.service.message.secure;

import com.engine.network.service.message.Message;

/**
 *
 * @author tobias
 */
public class SecuredMessage extends Message {

    /**
     * Contains the temporary key.
     */
    public String key;
    /**
     * ID of the game.
     */
    public int gameID;

    /**
     * Because of json.
     */
    public SecuredMessage() {
    }

    public SecuredMessage(String key, int gameID, int type) {
        super(type);
        this.key = key;
        this.gameID = gameID;
    }

    /**
     * Returns the temporary key.
     *
     * @return temporary key
     */
    public String getKey() {
        return key;
    }

    /**
     * Returns the id of the game.
     *
     * @return game id
     */
    public int getGameID() {
        return gameID;
    }

}
