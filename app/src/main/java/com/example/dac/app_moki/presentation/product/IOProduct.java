package com.example.dac.app_moki.presentation.product;

import com.example.dac.app_moki.local.data.ProductLocal;
import com.example.dac.app_moki.model.object.Category;
import com.example.dac.app_moki.model.object.Product;
import com.example.dac.app_moki.model.object.Seller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 11/6/2017.
 */

public class IOProduct {
    public List<Product> getListProducts(String jsonData, String categoryId){
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

                product.setId(Integer.parseInt(itemProduct.getString("id")));
                product.setName(itemProduct.getString("name"));
                product.setPrice(Integer.parseInt(itemProduct.getString("price")));
                product.setPricePercen(Integer.parseInt(itemProduct.getString("price_percent")));
                //product.setBrand(itemProduct.getString("brand"));
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

                for(int j = 0; j < arrImage.length(); j ++){
                    JSONObject itemImage = arrImage.getJSONObject(j);
                    lstImages.add(itemImage.getString("url"));
                }

                JSONObject objectSeller = itemProduct.getJSONObject("seller");
                Seller seller = new Seller();
                seller.setId(Integer.parseInt(objectSeller.getString("id")));
                seller.setNameShop(String.valueOf(objectSeller.getString("username")));
                seller.setImage(String.valueOf(objectSeller.getString("avatar")));

                product.setSeller(seller);
                product.setImage(lstImages);
                lstProducts.add(product);

            }
            ProductLocal.setLstProducs(lstProducts);
            ProductLocal.setCategories(categoryId);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lstProducts;
    }

    public Product getProduct(String jsonData){
        Product product = new Product();
        if(jsonData == null || jsonData == ""){
            return product;
        }
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            JSONObject itemProduct = jsonArray.getJSONObject(0);

            product.setId(Integer.parseInt(itemProduct.getString("id")));
            product.setName(itemProduct.getString("name"));
            product.setPrice(Integer.parseInt(itemProduct.getString("price")));
            product.setPricePercen(Integer.parseInt(itemProduct.getString("price_percent")));
            //product.setBrand(itemProduct.getString("brand"));
            product.setDescription(itemProduct.getString("described"));
            product.setFromDate(itemProduct.getString("created"));
            product.setNumberLike(Integer.parseInt(itemProduct.getString("like")));
            product.setNumberComment(Integer.parseInt(itemProduct.getString("comment")));
            product.setLike(Boolean.parseBoolean(itemProduct.getString("is_liked")));
            product.setBlocked(Boolean.parseBoolean(itemProduct.getString("is_blocked")));
            product.setCanEdit(Boolean.parseBoolean(itemProduct.getString("can_edit")));
            product.setBanned(Boolean.parseBoolean(itemProduct.getString("banned")));
            product.setCodition(String.valueOf(itemProduct.getString("condition")));


            product.setShipFrom(String.valueOf(itemProduct.getString("ships_from")));

            JSONArray arrSize = itemProduct.getJSONArray("additionals");

            if (arrSize != null && arrSize.length() > 0) {
                if(arrSize.length() == 1){
                    product.setWeigh(String.valueOf( (arrSize.getJSONObject(0)).getString("value")));
                }
                if(arrSize.length() == 2){
                    product.setSize(String.valueOf( (arrSize.getJSONObject(0)).getString("value")));
                    product.setWeigh(String.valueOf( (arrSize.getJSONObject(1)).getString("value")));
                }
            }


            JSONArray arrImage = itemProduct.getJSONArray("image");
            List<String> lstImages = new ArrayList<>();
            for(int j = 0; j < arrImage.length(); j ++){
                JSONObject itemImage = arrImage.getJSONObject(j);
                lstImages.add(itemImage.getString("url"));
            }
            product.setImage(lstImages);

            JSONObject objectSeller = itemProduct.getJSONObject("seller");
            Seller seller = new Seller();
            seller.setId(Integer.parseInt(objectSeller.getString("id")));
            seller.setNameShop(String.valueOf(objectSeller.getString("name")));
            seller.setImage(String.valueOf(objectSeller.getString("avatar")));
            seller.setScore(Integer.parseInt(objectSeller.getString("score")));
            seller.setNumberProduct(Integer.parseInt(objectSeller.getString("listing")));
            product.setSeller(seller);

            JSONArray arrCategory = itemProduct.getJSONArray("category");
            JSONObject objectCategory = arrCategory.getJSONObject(0);

            Category category = new Category();
            category.setId(Integer.parseInt(objectCategory.getString("id")));
            category.setName(String.valueOf(objectCategory.getString("name")));
            category.setDecription(String.valueOf(objectCategory.getString("description")));

            product.setCategory(category);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return product;
    }

    public List<Product> getListMyLike(String jsonData){
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

                product.setId(Integer.parseInt(itemProduct.getString("id")));
                product.setName(itemProduct.getString("name"));
                product.setPrice(Integer.parseInt(itemProduct.getString("price")));
                product.setPricePercen(Integer.parseInt(itemProduct.getString("price_percent")));
                product.setBrand(itemProduct.getString("brand"));
                product.setDescription(itemProduct.getString("described"));

                product.setNumberLike(Integer.parseInt(itemProduct.getString("like")));
                product.setNumberComment(Integer.parseInt(itemProduct.getString("comment")));

                JSONArray arrImage = itemProduct.getJSONArray("image");
                List<String> lstImages = new ArrayList<>();

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

    public String likeProduct(String jsonData){
        String numberLike = "";

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject data = jsonObject.getJSONObject("data");

            numberLike = data.getString("like");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return numberLike;
    }

    public List<Product> getListProducts(String jsonData){
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

                product.setId(Integer.parseInt(itemProduct.getString("id")));
                product.setName(itemProduct.getString("name"));
                product.setPrice(Integer.parseInt(itemProduct.getString("price")));
                product.setPricePercen(Integer.parseInt(itemProduct.getString("price_percent")));
                //product.setBrand(itemProduct.getString("brand"));
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

                for(int j = 0; j < arrImage.length(); j ++){
                    JSONObject itemImage = arrImage.getJSONObject(j);
                    lstImages.add(itemImage.getString("url"));
                }

                JSONObject objectSeller = itemProduct.getJSONObject("seller");
                Seller seller = new Seller();
                seller.setId(Integer.parseInt(objectSeller.getString("id")));
                seller.setNameShop(String.valueOf(objectSeller.getString("username")));
                seller.setImage(String.valueOf(objectSeller.getString("avatar")));

                product.setSeller(seller);
                product.setImage(lstImages);
                lstProducts.add(product);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lstProducts;
    }

    public List<Product> getListings(String jsonData){
        List<Product> lstProducts = new ArrayList<>();

        try {
            if (jsonData == null || jsonData == "") {
                return lstProducts;
            }
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray arrProduct = jsonObject.getJSONArray("data");

            for(int i = 0 ; i < arrProduct.length(); i++){
                JSONObject itemProduct = arrProduct.getJSONObject(i);
                Product product = new Product();

                product.setId(Integer.parseInt(itemProduct.getString("id")));
                product.setName(itemProduct.getString("name"));
                product.setPrice(Integer.parseInt(itemProduct.getString("price")));
                product.setPricePercen(Integer.parseInt(itemProduct.getString("price_percent")));
                product.setNumberLike(Integer.parseInt(itemProduct.getString("like")));
                product.setNumberComment(Integer.parseInt(itemProduct.getString("comment")));
                product.setLike(Boolean.parseBoolean(itemProduct.getString("is_liked")));
                product.setBanned(Boolean.parseBoolean(itemProduct.getString("banned")));

                JSONArray arrImage = itemProduct.getJSONArray("image");
                List<String> lstImages = new ArrayList<>();

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
