package com.example.dac.app_moki.presentation.category;

import com.example.dac.app_moki.model.object.Category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 11/4/2017.
 */

public class IOCategory {
    public static List<Category> getListCategory(String jsonData){
        List<Category> lstCategory = new ArrayList<>();

        try {
            if(jsonData == null || jsonData ==""){
                return lstCategory;
            }

            JSONArray arrCategories = new JSONArray(jsonData);
            for (int i = 0 ; i < arrCategories.length(); i++){
                JSONObject itemCategory = arrCategories.getJSONObject(i);
                if(itemCategory.getString("parent") == null){
                    continue;
                }

                Category category = new Category();
                category.setId(Integer.parseInt(itemCategory.getString("id")));
                category.setName(itemCategory.getString("name"));
                category.setDecription(itemCategory.getString("description"));

                lstCategory.add(category);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lstCategory;
    }
}
