package com.example.dac.app_moki.presentation.condition;

import com.example.dac.app_moki.model.object.Condition;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 11/9/2017.
 */

public class IOCondition {
    public List<Condition> getListConditions(String jsonData){
        List<Condition> lstConditions = new ArrayList<>();

        if(jsonData == null || jsonData == ""){
            return lstConditions;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray arrConditons = jsonObject.getJSONArray("data");

            for(int i = 0 ; i < arrConditons.length(); i++){

                JSONObject itemCondition = arrConditons.getJSONObject(i);
                Condition condition = new Condition();
                condition.setId(Integer.parseInt(itemCondition.getString("id")));
                condition.setName(String.valueOf(itemCondition.getString("condition_name")));

                lstConditions.add(condition);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lstConditions;
    }
}
