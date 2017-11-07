package com.example.dac.app_moki.presentation.comment;

import com.example.dac.app_moki.model.object.Comment;
import com.example.dac.app_moki.model.object.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 11/7/2017.
 */

public class IOComment {
    public List<Comment> getListComment(String jsonData){
        List<Comment> lstComment  = new ArrayList<>();

        if(jsonData == null || jsonData == ""){
            return lstComment;
        }
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("data");

            for(int i = 0 ; i < jsonArray.length(); i++){
                JSONObject objectItem = jsonArray.getJSONObject(i);

                Comment comment = new Comment();
                comment.setId(Integer.parseInt(objectItem.getString("id")));
                comment.setContent(String.valueOf(objectItem.getString("comment")));
                comment.setFromDate(String.valueOf(objectItem.getString("created")));

                JSONObject jsonUser = objectItem.getJSONObject("poster");
                User user = new User();
                user.setId(Integer.parseInt(jsonUser.getString("id")));
                user.setUserName(String.valueOf(jsonObject.getString("name")));
                user.setImage(String.valueOf(jsonObject.getString("avatar")));

                comment.setUser(user);

                lstComment.add(comment);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lstComment;
    }

}
