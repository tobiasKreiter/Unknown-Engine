/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engine.network.service.message.achievement;

import com.engine.network.service.message.Message;

/**
 *
 * @author tobias
 */
public class CheckAchievementVersionResponse extends Message {

    /**
     * Indicates if the version of the achievements are up to date.
     */
    public boolean upToDate;

    public CheckAchievementVersionResponse() {
    }

    public CheckAchievementVersionResponse(boolean upToDate) {
        super(CHECK_ACHIEVEMENT_VERSION_RESPONSE);
        this.upToDate = upToDate;
    }

    /**
     * Returns if the version of the achievements is up to date.
     *
     * @return achievement version is up to date
     */
    public boolean isUpToDate() {
        return upToDate;
    }

}
