/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engine.network.service.implementation;

import com.engine.network.service.message.ErrorMessage;
import com.engine.network.service.message.Message;


/**
 * @author tobias
 */
public interface NetworkInterface {

    /**
     * Is executed when a new message is received.
     *
     * @param message new response
     */
    public void onNetworkResponse(Message message);

    /**
     * Is executed when a new error message arrived.
     *
     * @param error error message
     */
    public void onNetworkFailure(ErrorMessage error);

    /**
     * Returns an unique id with which the device identify itself.
     *
     * @return unique id
     */
    public String getUniqueID();

}
