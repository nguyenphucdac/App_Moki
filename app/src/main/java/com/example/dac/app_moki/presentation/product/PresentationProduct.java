package com.example.dac.app_moki.presentation.product;

import com.example.dac.app_moki.model.nesworks.Host;
import com.example.dac.app_moki.model.nesworks.LoadData;
import com.example.dac.app_moki.model.object.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dac on 11/6/2017.
 */

public class PresentationProduct {
    public List<Product> getListProducts(int category) {
        String jsonData = "";
        String link = "http://"+ Host.getHost()+"/api/get_list_products?category_id=" + category;
        HashMap<String,String> lstProps = new HashMap<>();
        List<Product> lstProducts = new ArrayList<>();

        lstProps.put("category_id", String.valueOf(category));

        LoadData loadData = new LoadData(link);
        loadData.execute();

        try {
            jsonData = loadData.get();
            IOProduct ioProduct = new IOProduct();
            lstProducts = ioProduct.getListProducts(jsonData);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return lstProducts;
    }
    public Product getProduct(int id){
        String jsonData = "";
        String link = "http://"+ Host.getHost()+"/api/get_products?id="+ id;
        Product product = new Product();

        LoadData loadData = new LoadData(link);
        loadData.execute();

        try {
            jsonData = loadData.get();
            IOProduct ioProduct = new IOProduct();
            product = ioProduct.getProduct(jsonData);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return product;
    }
    public List<Product> getSerachResult(String keyword, String catergoryId){
        String jsonData = "";
        String link = "http://"+ Host.getHost()+"/api/search?category_id=" + catergoryId;
        List<Product> lstProducts = new ArrayList<>();

        System.out.println(link);

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("keyword", keyword);
        hashMap.put("category_id", catergoryId);
//        hashMap.put("product_size_id", size);
//        hashMap.put("brand_id", brand);
//        hashMap.put("price_min", priceMax);
//        hashMap.put("condition", state);

        LoadData loadData = new LoadData(link);

        loadData.execute();
        try {
            jsonData = loadData.get();
            IOProduct ioProduct = new IOProduct();
            lstProducts = ioProduct.getListProducts(jsonData);


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return lstProducts;
    }
    public List<Product> getListMyLike(String token){
        String jsonData = "";
        String link = "http://"+ Host.getHost()+"/api/get_my_likes?token="+ token;
        List<Product> lstProduct = new ArrayList<>();

        LoadData loadData = new LoadData(link);
        loadData.execute();

        try {
            jsonData = loadData.get();
            IOProduct ioProduct = new IOProduct();

            lstProduct = ioProduct.getListMyLike(jsonData);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return lstProduct;
    }
}
