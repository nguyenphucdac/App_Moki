package com.example.dac.app_moki.presentation.user;

import com.example.dac.app_moki.model.nesworks.Host;
import com.example.dac.app_moki.model.nesworks.LoadData;
import com.example.dac.app_moki.model.object.User;

import java.util.concurrent.ExecutionException;

/**
 * Created by Dac on 11/17/2017.
 */

public class PresentationUser {
    public User getUser(int id){
        String jsonData = "";
        User user = null;
        //password = MD5.crypt(password);
        String link = "http://"+ Host.getHost()+"/api/get_user_info?user_id=" + id;

        LoadData loadData = new LoadData(link);
        loadData.execute();

        try {
            jsonData = loadData.get();

            IOUser ioUser = new IOUser();
            user = ioUser.getInfoLogin(jsonData);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return user;
    }
}
