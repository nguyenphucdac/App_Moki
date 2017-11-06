package com.example.dac.app_moki.presentation.product;

import com.example.dac.app_moki.model.object.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 11/6/2017.
 */

public class IOProduct {
    public static List<Product> getListProducts(String jsonData){
        List<Product> lstProducts = new ArrayList<>();

        try {
            if (jsonData == null || jsonData == "") {
                return lstProducts;
            }
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject dataObject = jsonObject.getJSONObject("data");
            JSONArray arrProduct = dataObject.getJSONArray("products");

            for(int i = 0 ; i < arrProduct.length(); i++){
                JSONObject itemProduct = arrProduct.getJSONObject(i);
                Product product = new Product();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

                product.setId(Integer.parseInt(itemProduct.getString("id")));
                product.setName(itemProduct.getString("name"));
                product.setPrice(Integer.parseInt(itemProduct.getString("price")));
                product.setPricePercen(Integer.parseInt(itemProduct.getString("price_percent")));
                product.setBrand(itemProduct.getString("brand"));
                product.setDescription(itemProduct.getString("described"));
                product.setFromDate(itemProduct.getString("created"));
                product.setNumberLike(Integer.parseInt(itemProduct.getString("like")));
                product.setNumberComment(Integer.parseInt(itemProduct.getString("comment")));
                product.setLike(Boolean.parseBoolean(itemProduct.getString("is_liked")));
                product.setBlocked(Boolean.parseBoolean(itemProduct.getString("is_blocked")));
                product.setCanEdit(Boolean.parseBoolean(itemProduct.getString("can_edit")));
                product.setBanned(Boolean.parseBoolean(itemProduct.getString("banned")));

                JSONArray arrImage = itemProduct.getJSONArray("image");
                List<String> lstImages = new ArrayList<>();

                if(arrImage == null || arrImage.length() <= 0){
                    lstProducts.add(product);
                    continue;
                }
                for(int j = 0; j < arrImage.length(); j ++){
                    JSONObject itemImage = arrImage.getJSONObject(j);
                    lstImages.add(itemImage.getString("url"));
                }
                product.setImage(lstImages);
                lstProducts.add(product);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lstProducts;
    }
}
