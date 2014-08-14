/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engine.network.service.engine.connection.encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    /**
     * Dont know.
     */
    private static String IV = "AAAAAAAAAAAAAAAA";
    /**
     * Key for the encryption.
     */
    private static String encryptionKey = "QsecImsVJoWQjVX5";
    /**
     * Chiper.
     */
    private static Cipher cipher;
    /**
     * SecretKeySpec.
     */
    private static SecretKeySpec key;

    /**
     * Initializes the encryption.
     *
     * @throws Exception if encryption init failed
     */
    public AES() throws Exception {
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
    }

    /**
     * Encrypts a String. Usually its a message which was converted to a String.
     *
     * @param plainText text which should be encrypted
     * @return encrypted text as bytes
     * @throws Exception if encryption failed
     */
    public static synchronized byte[] encrypt(String plainText) throws Exception {
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
        return cipher.doFinal(plainText.getBytes("UTF-8"));
    }

    /**
     * Dencryptes a byte array to a String.
     *
     * @param cipherText byte array which should be decrypted.
     * @return decrypted String
     * @throws Exception if decryption failed
     */
    public static synchronized String decrypt(byte[] cipherText) throws Exception {
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
        return new String(cipher.doFinal(cipherText), "UTF-8");
    }
}
