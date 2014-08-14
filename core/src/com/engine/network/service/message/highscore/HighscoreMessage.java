/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engine.network.service.message.highscore;

import com.engine.network.service.message.secure.SecuredMessage;

/**
 *
 * @author tobias
 */
public class HighscoreMessage extends SecuredMessage {

    /**
     * How long the highscore list should be.
     */
    public int limit;

    public HighscoreMessage() {
    }

    public HighscoreMessage(int limit, int gameID, String key) {
        super(key, gameID, HIGHSCORE_MESSAGE);
        this.limit = limit;
    }
    
    public int getLimit() {
        return limit;
    }

}
