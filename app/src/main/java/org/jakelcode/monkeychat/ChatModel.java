package org.jakelcode.monkeychat;

/**
 * POJO Chat info
 *
 * @author Pin Khe "Jake" Loo (24 February, 2015)
 */
public class ChatModel {
    private int mUserId;
    private String mTextMessage;

    public ChatModel(int uid, String msg) {
        mUserId = uid;
        mTextMessage = msg;
    }

    public int getUserId() {
        return mUserId;
    }

    public String getMessage() {
        return mTextMessage;
    }
}
