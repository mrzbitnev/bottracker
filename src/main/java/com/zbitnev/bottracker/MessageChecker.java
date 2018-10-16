package com.zbitnev.bottracker;

import java.io.*;
import java.util.Properties;
import java.util.Set;

public class MessageChecker {

    Properties userIDProperties = new Properties();

    MessageChecker() {
        try {
            InputStream ISProperties = new FileInputStream("dataConfig.properties");
            userIDProperties.load(ISProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    boolean isMagnetLink(String message) {
        if (message.startsWith("magnet:?xt=")) {
            return true;
        }
        return false;
    }

    boolean isRightUser(Long userID) {
        Set<String> keys = userIDProperties.stringPropertyNames();
        for (String key : keys) {
            if (String.valueOf(userID).equals(key)) {
                return true;
            }
        }
        return false;
    }
}

