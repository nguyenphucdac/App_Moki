package com.example.dac.app_moki.presentation.user;

import com.example.dac.app_moki.model.object.User;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dac on 11/7/2017.
 */

public class IOUser {
    public User getInfoLogin(String jsonData){
        User user = null;
        try {

            if(jsonData == null || jsonData == ""){
                return user;
            }

            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject userData = jsonObject.getJSONObject("data");

            user = new User();
            user.setId(Integer.parseInt(userData.getString("id")));
            user.setUserName(String.valueOf(userData.getString("username")));
            user.setImage(String.valueOf(userData.getString("avartar")));
            user.setToken(String.valueOf(userData.getString("token")));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
    }
    public User getUser(String jsonData){
        User user = new User();
        try {

            if(jsonData == null || jsonData == ""){
                return user;
            }

            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject userData = jsonObject.getJSONObject("data");


            user.setId(Integer.parseInt(userData.getString("id")));
            user.setUserName(String.valueOf(userData.getString("username")));
            user.setUrl(String.valueOf(userData.getString("url")));
            user.setStatus(String.valueOf(userData.getString("status")));
            user.setImage(String.valueOf(userData.getString("avartar")));
            user.setFolowed(Integer.parseInt(userData.getString("followed")));
            user.setIsBlocked(Integer.parseInt(userData.getString("is_blocked")));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
    }
}
