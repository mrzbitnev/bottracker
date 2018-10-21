package com.zbitnev.bottracker;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MessageCipher {
    private final static String KEY = "SomeunkownKeyKey";
    private final static String KEY1 = "SomeuanotherKeyK";
    private final static String KEY2 = "Whaticanimagenit";

    public byte[] getCipher(byte [] messageWithID) throws GeneralSecurityException {
        try {
            Cipher cipher = Cipher.getInstance("AES");

            SecretKeySpec key = new SecretKeySpec(KEY.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte [] firstRound = cipher.doFinal(messageWithID);

            SecretKeySpec key1 = new SecretKeySpec(KEY1.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, key1);
            byte [] secondRaund = cipher.doFinal(firstRound);

            SecretKeySpec key2 = new SecretKeySpec(KEY2.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, key2);
            return cipher.doFinal(secondRaund);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        throw new GeneralSecurityException();
    }

    public byte[] getDecrypt(byte[] bytes) throws GeneralSecurityException {
        try {
            Cipher decriptCipher = Cipher.getInstance("AES");

            SecretKeySpec key = new SecretKeySpec(KEY2.getBytes(), "AES");
            decriptCipher.init(Cipher.DECRYPT_MODE, key);
            byte [] firstRound = decriptCipher.doFinal(bytes);

            SecretKeySpec key1 = new SecretKeySpec(KEY1.getBytes(), "AES");
            decriptCipher.init(Cipher.DECRYPT_MODE, key1);
            byte [] secondRaund = decriptCipher.doFinal(firstRound);

            SecretKeySpec key2 = new SecretKeySpec(KEY.getBytes(), "AES");
            decriptCipher.init(Cipher.DECRYPT_MODE, key2);
            return decriptCipher.doFinal(secondRaund);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        throw new GeneralSecurityException();
    }

    public static void printByteArr(byte[] bytes) {
        for (byte b : bytes) {
            System.out.print((char) b);
        }
        System.out.println("\n");
    }
}
