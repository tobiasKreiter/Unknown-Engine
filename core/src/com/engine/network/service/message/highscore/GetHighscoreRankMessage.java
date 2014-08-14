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
public class GetHighscoreRankMessage extends SecuredMessage {

    public GetHighscoreRankMessage() {
    }

    public GetHighscoreRankMessage(int gameID, String key) {
        super(key, gameID, GET_HIGHSCORE_RANK);
    }

}
