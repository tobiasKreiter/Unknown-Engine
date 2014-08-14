/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engine.network.service.message.register;

import com.engine.network.service.message.Message;

/**
 *
 * @author tobias
 */
public class RegisterMessage extends Message {

    /**
     * Alias or accoutn name.
     */
    public String alias;
    /**
     * Email which will be registered.
     */
    public String email;
    /**
     * Device id which will be bind to the account.
     */
    public String deviceID;
    /**
     * ID of game.
     */
    public int gameID;

    public RegisterMessage() {
    }

    public RegisterMessage(String alias, String email, String deviceID, int gameID) {
        super(Message.REGISTER);
        this.alias = alias;
        this.email = email;
        this.deviceID = deviceID;
        this.gameID = gameID;
    }

    /**
     * Returns the alias.
     *
     * @return alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Returns the email.
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public int getGameID() {
        return gameID;
    }
}
