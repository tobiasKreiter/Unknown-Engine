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
public class SubmitScoreMessage extends SecuredMessage {

    /**
     * Score
     */
    public int score;

    public SubmitScoreMessage() {
    }
    
    public SubmitScoreMessage(int score, int gameID, String key) {
        super(key, gameID, SUMBIT_SCORE);
        this.score = score;
    }

    /**
     * Returns the score.
     *
     * @return score
     */
    public int getScore() {
        return score;
    }
}
