package com.example.dac.app_moki.presentation.conversation;

import com.example.dac.app_moki.local.value.ValueLocal;
import com.example.dac.app_moki.model.nesworks.Host;
import com.example.dac.app_moki.model.nesworks.LoadData;
import com.example.dac.app_moki.model.object.Conversation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dac on 11/20/2017.
 */

public class PresentationConversation {
    public List<Conversation> getListConversation(){
        String jsonData = "";
        List<Conversation> lstConversation = new ArrayList<>();
        String link = "http://"+ Host.getHost()+"/api/get_list_conversation";

        HashMap<String, String> hashMapToken = new HashMap<>();
        hashMapToken.put("token", ValueLocal.getToken());

        List<HashMap<String, String>> lstProps = new ArrayList<>();
        lstProps.add(hashMapToken);

        LoadData loadData = new LoadData(link, lstProps);
        loadData.execute();

        try {
            jsonData = loadData.get();
            IOConversation ioConversation = new IOConversation();
            lstConversation = ioConversation.getListConversation(jsonData);


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return lstConversation;
    }
}
