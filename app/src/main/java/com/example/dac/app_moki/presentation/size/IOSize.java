package com.example.dac.app_moki.presentation.size;

import com.example.dac.app_moki.model.object.Size;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 11/9/2017.
 */

public class IOSize {
    public List<Size> getListSizes(String jsonData){
        List<Size> lstSize = new ArrayList<>();

        if(jsonData == null || jsonData == ""){
            return lstSize;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray arrSizes = jsonObject.getJSONArray("data");

            for(int i = 0 ; i < arrSizes.length(); i++){

                JSONObject itemsize = arrSizes.getJSONObject(i);
                Size size = new Size();
                size.setId(Integer.parseInt(itemsize.getString("id")));
                size.setName(String.valueOf(itemsize.getString("size_name")));

                lstSize.add(size);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lstSize;
    }
}
