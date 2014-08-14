/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engine.network.service.message.impression;

import com.engine.network.service.message.Message;

/**
 *
 * @author tobias
 */
public class ImpressionMessage extends Message {

    /**
     * Contains the id of the game which is launched.
     */
    public int gameID;

    public ImpressionMessage() {
    }
    
    /**
     * Is sent at every lunch of the engine for statistics.
     */
    public ImpressionMessage(int gameID) {
        super(Message.IMPRESSION);
        this.gameID = gameID;
    }

    /**
     * Returns the sent game id.
     *
     * @return game id
     */
    public int getGameID() {
        return gameID;
    }

}
