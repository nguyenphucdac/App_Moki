package com.example.dac.app_moki.presentation.acount;

import com.example.dac.app_moki.model.nesworks.LoadData;
import com.example.dac.app_moki.model.object.User;
import com.example.dac.app_moki.presentation.user.IOUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by Dac on 11/7/2017.
 */

public class PresentationLogin {
    public User Login(String userName, String password) {
        String jsonData = "";
        String message = "";
        password = MD5.crypt(password);
        String link = "http://192.168.1.251:1337/api/login?user_name=" + userName +"&password" + password;
        User user = new User();

        LoadData loadData = new LoadData(link);
        loadData.execute();

        try {
            jsonData = loadData.get();
            JSONObject jsonObject = new JSONObject(jsonData);
            message = jsonObject.getString("message");

            if(message != "Ok"){
                return user;
            }

            IOUser ioUser = new IOUser();
            user = ioUser.getUser(jsonData);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
    }

}
