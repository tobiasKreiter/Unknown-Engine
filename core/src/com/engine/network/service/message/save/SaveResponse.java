/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engine.network.service.message.save;

import com.engine.network.service.message.Message;

/**
 * @author tobias
 */
public class SaveResponse extends Message {

    /**
     * Save-file as byte array.
     */
    public byte[] save;

    public SaveResponse() {
    }

    public SaveResponse(byte[] save) {
        super(Message.SAVE_REPOSNE);
        this.save = save;
    }

    /**
     * Returns the save-file as byte array.
     *
     * @return save-file as byte array
     */
    public byte[] getSave() {
        return save;
    }

}
