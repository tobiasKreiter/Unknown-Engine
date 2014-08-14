/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engine.network.service.message.achievement;

import com.engine.network.service.message.secure.SecuredMessage;

/**
 *
 * @author tobias
 */
public class GetAllAchievementsMessage extends SecuredMessage {

    public GetAllAchievementsMessage() {
    }

    public GetAllAchievementsMessage(int gameID, String key) {
        super(key, gameID, GET_ALL_ACHIEVEMENTS);
    }

}
