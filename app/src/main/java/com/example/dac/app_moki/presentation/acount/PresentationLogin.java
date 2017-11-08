package com.example.dac.app_moki.presentation.acount;

import com.example.dac.app_moki.model.nesworks.Host;
import com.example.dac.app_moki.model.nesworks.LoadData;
import com.example.dac.app_moki.model.object.User;
import com.example.dac.app_moki.presentation.user.IOUser;

import java.util.concurrent.ExecutionException;

/**
 * Created by Dac on 11/7/2017.
 */

public class PresentationLogin {
    public User getUser(String userName, String password) {
        String jsonData = "";
        String message = "";
        User user = null;
        //password = MD5.crypt(password);
        String link = "http://"+ Host.getHost()+"/api/login?user_name=mebomai&password=a932e5bab8e2f817fdcf916c293ae034";

        LoadData loadData = new LoadData(link);
        loadData.execute();

        try {
            jsonData = loadData.get();

            IOUser ioUser = new IOUser();
            user = ioUser.getUser(jsonData);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return user;
    }

}
