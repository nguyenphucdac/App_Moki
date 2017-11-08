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
    public List<Category> getListCategory(String jsonData){
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
                category.setHadChilde(true);

                lstCategory.add(category);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lstCategory;
    }
    public List<Category> getChildeCatetory(String jsonData, int categoryId){
        List<Category> lstCategory = new ArrayList<>();

        try {
            if(jsonData == null || jsonData ==""){
                return lstCategory;
            }

            JSONArray arrCategories = new JSONArray(jsonData);

            for(int i = 0 ; i < arrCategories.length(); i++){
                JSONObject itemCategory = arrCategories.getJSONObject(i);
                if(Integer.parseInt(itemCategory.getString("id")) == categoryId){
                    System.out.println(itemCategory.getString("id"));
                    JSONArray arrChildren = itemCategory.getJSONArray("children");
                    for (int j = 0; j < arrChildren.length(); j++) {
                        JSONObject itemChilde = arrChildren.getJSONObject(j);

                        Category category = new Category();
                        category.setId(Integer.parseInt(itemChilde.getString("id")));
                        category.setName(itemChilde.getString("name"));
                        category.setDecription(itemChilde.getString("description"));
                        category.setParentId(categoryId);

                        lstCategory.add(category);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("size lst categories in IO = " + lstCategory.size());
        return lstCategory;
    }
}
