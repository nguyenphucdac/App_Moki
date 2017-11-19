package com.example.dac.app_moki.presentation.conversation;

import com.example.dac.app_moki.model.object.Conversation;
import com.example.dac.app_moki.model.object.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 11/20/2017.
 */

public class IOConversation {
    public List<Conversation> getListConversation(String jsonData){
        List<Conversation> lstConversation = new ArrayList<>();

        if (jsonData == null || jsonData == "") {
            return lstConversation;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray arrConversation = jsonObject.getJSONArray("data");

            for(int i = 0 ; i < arrConversation.length() ; i++){
                JSONObject item = arrConversation.getJSONObject(i);
                JSONObject jsonObjectUser = item.getJSONObject("Partner");
                JSONObject jsonObjectProudct = item.getJSONObject("Product");
                JSONObject jsonObjectLastMessage = item.getJSONObject("LastMessage");

                Conversation conversation = new Conversation();
                User user = new User();

                user.setId(Integer.parseInt(jsonObjectUser.getString("id")));
                user.setUserName(String.valueOf(jsonObjectUser.getString("username")));
                user.setImage(String.valueOf(jsonObjectUser.getString("avatar")));

                conversation.setUserSend(user);

                conversation.setProductId(String.valueOf(jsonObjectProudct.getString("id")));
                conversation.setLastMessage(String.valueOf(jsonObjectLastMessage.getString("message")));

                lstConversation.add(conversation);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return lstConversation;
    }
}
