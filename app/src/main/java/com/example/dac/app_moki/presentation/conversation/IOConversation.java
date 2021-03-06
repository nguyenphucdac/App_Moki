package com.example.dac.app_moki.presentation.conversation;

import com.example.dac.app_moki.model.object.Conversation;
import com.example.dac.app_moki.model.object.Message;
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
                conversation.setId(String.valueOf(item.get("id")));

                lstConversation.add(conversation);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return lstConversation;
    }
    public List<Message> getConversation(String jsonData){
        List<Message> lstMessage = new ArrayList<>();

        if (jsonData == null || jsonData == "") {
            return lstMessage;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray arrMessage = jsonObject.getJSONArray("data");

            for(int i = 0 ; i < arrMessage.length() ; i++){
                JSONObject objectMessage = arrMessage.getJSONObject(i);
                Message message = new Message();
                message.setMessage(String.valueOf(objectMessage.getString("message")));

                JSONObject sender = objectMessage.getJSONObject("sender");
                User user = new User();
                user.setId(Integer.parseInt(sender.getString("id")));
                user.setUserName(String.valueOf(sender.getString("username")));
                user.setImage(String.valueOf(sender.getString("avartar")));
                message.setUser(user);

                message.setUnread(String.valueOf(objectMessage.get("unread")));

                lstMessage.add(message);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lstMessage;
    }
}
