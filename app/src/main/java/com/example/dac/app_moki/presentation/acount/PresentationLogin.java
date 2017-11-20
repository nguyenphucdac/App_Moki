package com.example.dac.app_moki.presentation.acount;

import com.example.dac.app_moki.model.nesworks.ConnectSocket;
import com.example.dac.app_moki.model.nesworks.Host;
import com.example.dac.app_moki.model.nesworks.LoadData;
import com.example.dac.app_moki.model.object.User;
import com.example.dac.app_moki.presentation.user.IOUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dac on 11/7/2017.
 */

public class PresentationLogin {
    public User login(String userName, String password) {
        String jsonData = "";
        User user = null;
        password = MD5.crypt(password);
        String link = "http://"+ Host.getHost()+"/api/login";

        HashMap<String , String> hashMapUserName = new HashMap<>();
        hashMapUserName.put("user_name",userName);

        HashMap<String, String> hashMapPassword = new HashMap<>();
        hashMapPassword.put("password", password);

        List<HashMap<String, String>> lstProps = new ArrayList<>();
        lstProps.add(hashMapUserName);
        lstProps.add(hashMapPassword);

        LoadData loadData = new LoadData(link, lstProps);
        loadData.execute();

        try {
            jsonData = loadData.get();

            IOUser ioUser = new IOUser();
            user = ioUser.getInfoLogin(jsonData);


            if(user != null){
                ConnectSocket.setmSocket();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return user;
    }
}
