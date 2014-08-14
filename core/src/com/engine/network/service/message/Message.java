/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engine.network.service.message;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

/**
 *
 * @author tobias
 */
public abstract class Message {

    /**
     * Contains the type (class) of the message so no instanceof is required.
     */
    protected int type;
    /**
     * Deserializes the incoming messages.
     */
    private static JSONDeserializer deserializer;
    /**
     * Serializes the outgoing messages.
     */
    private static JSONSerializer serializer;
    /**
     * Empty-Message.
     */
    public static final int EMPTY = 1;
    /**
     * Error-Message.
     */
    public static final int ERROR = 2;
    /**
     * Authentification-Message for login.
     */
    public static final int AUTHENTICATION = 3;
    /**
     * Response of a login request.
     */
    public static final int AUTHENTICATION_RESPONSE = 4;
    /**
     * Register-message to register new device.
     */
    public static final int REGISTER = 5;
    /**
     * Response of a register request.
     */
    public static final int REGISTER_RESPONSE = 6;
    /**
     * Inpression-Message for statistics. At every lunch of the engine an
     * impression-message is sent. Response is an empty message.
     */
    public static final int IMPRESSION = 7;
    /**
     * Message to get all achievements of a game.
     */
    public static final int GET_ALL_ACHIEVEMENTS = 8;
    /**
     * Response which conatins all achievements.
     */
    public static final int GET_ALL_ACHIEVEMNTS_RESPONSE = 9;
    /**
     * Checks if the achievement version is up to date.
     */
    public static final int CHECK_ACHIEVEMENT_VERSION = 10;
    /**
     * Response if the achievements are up to date.
     */
    public static final int CHECK_ACHIEVEMENT_VERSION_RESPONSE = 11;
    /**
     * Registers an achievement to an account.
     */
    public static final int ACHIEVEMENT_ACHIEVED = 12;
    /**
     * Request the highscore of a game.
     */
    public static final int HIGHSCORE_MESSAGE = 13;
    /**
     * Contains the highscore list of the specific game.
     */
    public static final int HIGHSCORE_RESPONSE = 14;
    /**
     * Submit score to check if in highscore list
     */
    public static final int SUMBIT_SCORE = 15;
    /**
     * Returns the score and place of the highscore list of a specific game.
     */
    public static final int HIGHSCORE_PLACE_RESPONSE = 16;
    /**
     * Requests a highscore place response.
     */
    public static final int GET_HIGHSCORE_RANK = 17;
    /**
     * Send a save object to the server.
     */
    public static final int SAVE_MESSAGE = 18;
    /**
     * Contains a save object.
     */
    public static final int SAVE_REPOSNE = 19;
    /**
     * Requests a save state.
     */
    public static final int GET_SAVE_MESSAGE = 20;

    /**
     * For deserializaition.
     */
    public Message() {
    }

    /**
     * Is called by subclasses to identifiy the message.
     *
     * @param type message type
     */
    public Message(int type) {
        this.type = type;
    }

    /**
     * Serializes the message to a string.
     *
     * @return message as string.
     */
    public String toString() {
        if (serializer == null) {
            serializer = new JSONSerializer();
        }
        return serializer.deepSerialize(this);
    }

    /**
     * Deserializes a string to a message.
     *
     * @param message message as string
     * @return message as object
     */
    public static Message toMessage(String message) {
        if (deserializer == null) {
            deserializer = new JSONDeserializer();
        }
        try {
            Message m = (Message) deserializer.deserialize(message);
            if (m == null) {
                m = new ErrorMessage(ErrorMessage.UNKNOWN_PACKAGE);
            }
            return m;
        } catch (Exception e) {
            return new ErrorMessage(ErrorMessage.UNKNOWN_PACKAGE);
        }
    }

    /**
     * Returns the type of the message.
     *
     * @return type of message
     */
    public int getType() {
        return type;
    }

}
