/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engine.network.service.message.achievement;

import com.engine.network.service.message.Message;
import java.util.ArrayList;

/**
 *
 * @author tobias
 */
public class GetAllAchievementsResponse extends Message {

    /**
     * Contains all ids of the achievements.
     */
    public ArrayList<Integer> achievementIDs;
    /**
     * Contains all names of the achievements.
     */
    public ArrayList<String> achievementNames;
    /**
     * Contains the required points for the achievements.
     */
    public ArrayList<Integer> requierements;
    /**
     * Contains the rewards in points.
     */
    public ArrayList<Integer> rewards;

    public GetAllAchievementsResponse() {
    }

    public GetAllAchievementsResponse(ArrayList<Integer> achievementIDs, ArrayList<String> achievementNames, ArrayList<Integer> requierements, ArrayList<Integer> rewards) {
        super(GET_ALL_ACHIEVEMNTS_RESPONSE);
        this.achievementIDs = achievementIDs;
        this.achievementNames = achievementNames;
        this.requierements = requierements;
        this.rewards = rewards;
    }

    /**
     * Returns the achievement ids.
     *
     * @return achievement ids
     */
    public ArrayList<Integer> getAchievementIDs() {
        return achievementIDs;
    }

    /**
     * Returns the achievement names.
     *
     * @return achievement names
     */
    public ArrayList<String> getAchievementNames() {
        return achievementNames;
    }

    /**
     * Returns the requierments of the achievements.
     *
     * @return requierements of every achievement
     */
    public ArrayList<Integer> getRequierements() {
        return requierements;
    }

    /**
     * Returns the rewards of the achievements.
     *
     * @return rewards of every achievement
     */
    public ArrayList<Integer> getRewards() {
        return rewards;
    }
}
