/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engine.network.service.message.highscore;

import com.engine.network.service.message.Message;
import java.util.ArrayList;

/**
 *
 * @author tobias
 */
public class HighscoreResponse extends Message {

    /**
     * Contains all names of the highscore list.
     */
    public ArrayList<String> names;
    /**
     * Contains all scores of the highscore list.
     */
    public ArrayList<Integer> scores;

    /**
     * Place 1 is index 0
     */
    public HighscoreResponse() {
    }

    public HighscoreResponse(ArrayList<String> names, ArrayList<Integer> scores) {
        super(HIGHSCORE_RESPONSE);
        this.names = names;
        this.scores = scores;
    }

    /**
     * Returns all names from the highscore list.
     *
     * @return all names
     */
    public ArrayList<String> getNames() {
        return names;
    }

    /**
     * Returns all scores from the highscore list.
     *
     * @return all scores
     */
    public ArrayList<Integer> getScores() {
        return scores;
    }

}
