package com.example.dac.app_moki.presentation.Brand;

import com.example.dac.app_moki.model.object.Brand;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 11/9/2017.
 */

public class IOBrand {
    public List<Brand> getListBrands(String jsonData){
        List<Brand> lstBrands = new ArrayList<>();

        if(jsonData == null || jsonData == ""){
            return lstBrands;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray arrBrands = jsonObject.getJSONArray("data");

            for(int i = 0 ; i < arrBrands.length(); i++){

                JSONObject itemBrand = arrBrands.getJSONObject(i);
                Brand brand = new Brand();
                brand.setId(Integer.parseInt(itemBrand.getString("id")));
                brand.setName(String.valueOf(itemBrand.getString("brand_name")));

                lstBrands.add(brand);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lstBrands;
    }
}
