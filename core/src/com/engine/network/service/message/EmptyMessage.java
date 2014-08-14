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
public class EmptyMessage extends Message {
    /**
     * Is needed because the server must response with something.
     */
    public EmptyMessage() {
        super(Message.EMPTY);
    }

}
