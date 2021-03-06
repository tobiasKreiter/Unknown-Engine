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
public class AchievementAchievedMessage extends SecuredMessage {

    /**
     * Achievement ID.
     */
    public int achievementID;

    public AchievementAchievedMessage() {
    }
    
    public AchievementAchievedMessage(int achievementID, int gameID, String key) {
        super(key, gameID, ACHIEVEMENT_ACHIEVED);
        this.achievementID = achievementID;
    }

    /**
     * Returns the ID of the achievement.
     *
     * @return achievement id
     */
    public int getAchievementID() {
        return achievementID;
    }

}
