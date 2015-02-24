package org.jakelcode.monkeychat;

/**
 * POJO Chat info
 *
 * @author Pin Khe "Jake" Loo (24 February, 2015)
 */
public class ChatModel {
    private int id;

    private String user;
    private String message;

    public ChatModel() {

    }

    public String getMessage() {
        return message;
    }
}
