/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engine.network.service.message.achievement;

import com.engine.network.service.message.Message;
import com.engine.network.service.message.secure.SecuredMessage;

/**
 *
 * @author tobias
 */
public class CheckAchievementVersionMessage extends SecuredMessage {

    /**
     * Version of the achievements.
     */
    public int achievementVersion;

    public CheckAchievementVersionMessage() {
    }

    public CheckAchievementVersionMessage(int achievementVersion, int gameID, String key) {
        super(key, gameID, CHECK_ACHIEVEMENT_VERSION);
        this.achievementVersion = achievementVersion;
    }

    /**
     * Returns the version of the achievements.
     *
     * @return achievement version
     */
    public int getAchievementVersion() {
        return achievementVersion;
    }

}
