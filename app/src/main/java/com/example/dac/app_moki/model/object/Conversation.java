package com.example.dac.app_moki.model.object;

/**
 * Created by Dac on 11/20/2017.
 */

public class Conversation {
    private String id;
    private User userSend;
    private String productId;
    private String lastMessage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUserSend() {
        return userSend;
    }

    public void setUserSend(User userSend) {
        this.userSend = userSend;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
