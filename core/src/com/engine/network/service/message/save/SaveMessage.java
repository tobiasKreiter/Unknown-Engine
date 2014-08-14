/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engine.network.service.message.save;

import com.engine.network.service.message.Message;
import com.engine.network.service.message.secure.SecuredMessage;

/**
 * @author tobias
 */
public class SaveMessage extends SecuredMessage {

    /**
     * Save file converted as byte array.
     */
    public byte[] save;

    public SaveMessage() {
    }

    public SaveMessage(byte[] serializedSaveObject, String key, int gameID) {
        super(key, gameID, Message.SAVE_MESSAGE);
        this.save = serializedSaveObject;
    }

    /**
     * Returns the save file as byte array.
     * @return save file as byte array
     */
    public byte[] getSave() {
        return save;
    }

}
