package com.example.dac.app_moki.model.object;

/**
 * Created by Dac on 11/20/2017.
 */

public class Message {
    private String message;
    private User user;

    public String getUnread() {
        return unread;
    }

    public void setUnread(String unread) {
        this.unread = unread;
    }

    private String unread;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
