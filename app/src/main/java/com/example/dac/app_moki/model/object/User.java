package com.example.dac.app_moki.model.object;

import java.io.Serializable;

/**
 * Created by Dac on 11/7/2017.
 */

public class User implements Serializable {
    private int id;
    private String userName;
    private String token;
    private String status;
    private String type;
    private String image;
    private String url;
    private int folowed;
    private int isBlocked;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getFolowed() {
        return folowed;
    }

    public void setFolowed(int folowed) {
        this.folowed = folowed;
    }

    public int getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(int isBlocked) {
        this.isBlocked = isBlocked;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
