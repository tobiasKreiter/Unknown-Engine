/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engine.network.service.message.authenticate;

import com.engine.network.service.message.Message;
import java.io.Serializable;

/**
 *
 * @author tobias
 */
public class AuthenticationResponse extends Message implements Serializable{

    /**
     * Temporary key with a length of 8 which changes after a login.
     */
    public String key;
    /**
     * Account name
     */
    public String name;
    /**
     * Indicates whether the login was successful.
     */
    public boolean success = true;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String key, String name, boolean success) {
        super(Message.AUTHENTICATION_RESPONSE);
        this.key = key;
        this.name = name;
        this.success = success;
    }

    /**
     * Returns the temporary key.
     *
     * @return temporary key
     */
    public String getKey() {
        return key;
    }

    /**
     * Returns the account name.
     *
     * @return account name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns if the login was successful.
     *
     * @return login was successful
     */
    public boolean wasSuccessful() {
        return success;
    }

}
