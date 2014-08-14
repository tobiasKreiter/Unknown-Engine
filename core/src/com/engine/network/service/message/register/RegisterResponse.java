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
public class RegisterResponse extends Message {

    /**
     * When the alias is already used.
     */
    public static final int ERROR_ALIAS = 1;
    /**
     * When the email is already used.
     */
    public static final int ERROR_EMAIL = 2;
    /**
     * When an account is already bound to the device id.
     */
    public static final int ERROR_DEVICE_ID = 3;

    /**
     * Inidicates whether the register process succeeded.
     */
    public boolean success;
    /**
     * Contains the reason why the registration failed.
     */
    public int errorReason;

    public RegisterResponse() {
    }

    public RegisterResponse(boolean success) {
        super(Message.REGISTER_RESPONSE);
        this.success = success;
    }

    public RegisterResponse(int errorReason) {
        super(Message.REGISTER_RESPONSE);
        this.success = false;
        this.errorReason = errorReason;
    }

    /**
     * Returns if the register progress was successful.
     *
     * @return registration successful
     */
    public boolean wasSuccessful() {
        return success;
    }

    /**
     * Returns the reason why the registration failed.
     *
     * @return reason why failed
     */
    public int getErrorReason() {
        return errorReason;
    }

}
