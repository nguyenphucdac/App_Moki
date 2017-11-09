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
    public User login(String userName, String password) {
        String jsonData = "";
        User user = null;
        password = MD5.crypt(password);
        System.out.println("username = " + userName + " password = "+ password);
        String link = "http://"+ Host.getHost()+"/api/login?user_name="+userName+"&password="+password;

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
