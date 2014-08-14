/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engine.network.service.message;

/**
 *
 * @author tobias
 */
public class ErrorMessage extends Message {

    /**
     * When Server not responsing.
     */
    public static final int TIMEOUT = 1;
    /**
     * If you have sent an unknown message.
     */
    public static final int UNKNOWN_PACKAGE = 2;
    /**
     * If client has no internet connection.
     */
    public static final int NOT_REACHABLE = 3;
    /**
     * If for some reason the encryption failed.
     */
    public static final int ENCRYPTION_FAILURE = 4;
    /**
     * If a wrong key is used.
     */
    public static final int WRONG_KEY = 5;
    /**
     * Contains which type of error this message is.
     */
    private int errorType;

    public ErrorMessage() {
    }

    /**
     * Type musst be an error type and not a message type.
     *
     * @param type error type
     */
    public ErrorMessage(int type) {
        super(Message.ERROR);
        this.errorType = type;
    }

    /**
     * Returns the error type.
     *
     * @return error type.
     */
    public int getErrorType() {
        return errorType;
    }

}
