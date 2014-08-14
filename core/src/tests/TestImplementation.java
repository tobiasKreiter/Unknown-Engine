/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import com.engine.network.service.implementation.NetworkInterface;
import com.engine.network.service.implementation.NetworkListener;
import com.engine.network.service.message.ErrorMessage;
import com.engine.network.service.message.Message;
import com.engine.network.service.message.authenticate.AuthenticationMessage;
import com.engine.network.service.message.authenticate.AuthenticationResponse;

/**
 *
 * @author tobias
 */
public class TestImplementation implements NetworkInterface {

    /**
     * Manages the network traffic.
     */
    private NetworkListener networkListener;
    /**
     * Test-Game has the id 1
     */
    private static final int GAME_ID = 1;

    public TestImplementation() {
        // The networklistener needs the id of the game and a networkinterface.
        networkListener = new NetworkListener(GAME_ID, this);

        //Example for sending an authentification message:
        networkListener.sendMessage(new AuthenticationMessage(getUniqueID(), GAME_ID));
    }

    /**
     * Is executed when you get a normal response.
     *
     * @param message normal message
     */
    @Override
    public void onNetworkResponse(Message message) {
        /*
         *  By sending a message you get a specific response.
         *  
         *  List of message you send and the responses you get:
         * 
         *  ImpressionMessage              -> EmptyMessage
         *  AuthenticationsMessage         -> AuthenticationResponse
         *  RegisterMessage                -> RegisterResponse
         *  CheckAchievementVersionMessage -> CheckAchievementVersionResponse
         *  GetAllAchievementsMessage      -> GetAllAchievementsResponse
         *  AchievementAchievedMessage     -> EmptyMessage
         *  HighscoreMessage               -> HighscoreResponse
         *  SubmitScoreMessage             -> HighscorePlaceResponse
         *  GetHighscoreRankMessage        -> HighscorePlaceResponse
         * 
         * Example for an authtentication reponse:
         */

        if (message.getType() == Message.AUTHENTICATION_RESPONSE) {
            //Because the type is an authentication response it can be casted to an authentication-response message
            AuthenticationResponse response = (AuthenticationResponse) message;
            //For further requests you need an 16-Bit temporary key which is only until the next login process valid.
            System.out.println("Key: " + response.getKey());
            //Because you dont know the Alias of the account you get it with the authentications response.
            System.out.println("Account name: " + response.getName());
        }
    }

    /**
     * Is executed when you get an error message.
     *
     * @param error specific error message
     */
    @Override
    public void onNetworkFailure(ErrorMessage error) {
        /*
         * You get an error message for different reasons:
         *
         * - timeout:            When you get no response after a specific time 
         *                       you get a timeout error-message 
         * - unknown package:    You sent a package to the server which he 
         *                       couldnt handle 
         * - not reachable:      server is down
         * - encryption failure: somethings is wrong with the encryption. Maybe
         *                       the device does not support the encryption 
         * - wrong key:          when you use a wrong key.
         */
    }

    /**
     * Returns an unique id of the device.
     *
     * @return unique id
     */
    @Override
    public String getUniqueID() {
        return "0sdaf67fdasdf986adf9adf876af";
    }

}
