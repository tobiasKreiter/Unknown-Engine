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
public class GetSaveMessage extends SecuredMessage {

    /**
     * Requests a save response which contains a save file.
     */

    public GetSaveMessage() {
    }

    public GetSaveMessage(String key, int gameID) {
        super(key, gameID, Message.GET_SAVE_MESSAGE);
    }

}
