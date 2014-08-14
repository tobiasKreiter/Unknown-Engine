/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engine.network.service.message.highscore;

import com.engine.network.service.message.Message;

/**
 *
 * @author tobias
 */
public class HighscorePlaceResponse extends Message {

    /**
     * Place of the account at a specific game.
     */
    public int place;
    
    public HighscorePlaceResponse() {
    }
    
    public HighscorePlaceResponse(int place) {
        super(HIGHSCORE_PLACE_RESPONSE);
        this.place = place;
    }

    public int getPlace() {
        return place;
    }
}
