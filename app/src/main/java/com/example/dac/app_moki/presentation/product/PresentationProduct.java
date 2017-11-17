package com.example.dac.app_moki.presentation.product;

import com.example.dac.app_moki.local.value.ValueLocal;
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
    public List<Product> getListProductsOfCategory(String categoryId) {
        String jsonData = "";
        String link = "http://"+ Host.getHost()+"/api/get_list_products?category_id=" + categoryId;
        List<Product> lstProducts = new ArrayList<>();

        LoadData loadData = new LoadData(link);
        loadData.execute();

        try {
            jsonData = loadData.get();
            IOProduct ioProduct = new IOProduct();
            lstProducts = ioProduct.getListProducts(jsonData, categoryId);
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
    public List<Product> getSerachResult(String keyword, String categoryId, String brandId, String sizeId, String conditionId){
        String jsonData = "";
        String link = "http://"+ Host.getHost()+"/api/search";
        List<Product> lstProducts = new ArrayList<>();

        List<HashMap<String, String>> lstProps = new ArrayList<>();

        if(keyword!=null && keyword!=""){
            HashMap<String, String> hashMapKeyword = new HashMap<>();
            hashMapKeyword.put("keyword", keyword);
            lstProps.add(hashMapKeyword);
        }

       if(categoryId !=null){
           System.out.println("value of categoryId = " + categoryId);
           HashMap<String, String> hashMapCategoryId = new HashMap<>();
           hashMapCategoryId.put("category_id", categoryId);
           lstProps.add(hashMapCategoryId);
       }

        if(brandId != null){
            HashMap<String, String> hashMapBrandId = new HashMap<>();
            hashMapBrandId.put("brand_id", brandId);
            lstProps.add(hashMapBrandId);
        }

        if(sizeId != null){
            HashMap<String, String> hashMapSizeId = new HashMap<>();
            hashMapSizeId.put("size_id", sizeId);
            lstProps.add(hashMapSizeId);
        }

        if(conditionId != null){
            HashMap<String, String> hashMapCondition = new HashMap<>();
            hashMapCondition.put("condition_id", conditionId);
            lstProps.add(hashMapCondition);
        }

        LoadData loadData = new LoadData(link, lstProps);

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
    public List<Product> getListMyLike(){
        String jsonData = "";
        String link = "http://"+ Host.getHost()+"/api/get_my_likes";
        List<Product> lstProduct = new ArrayList<>();

        HashMap<String, String> hashMapToken = new HashMap<>();
        hashMapToken.put("token", ValueLocal.getToken());

        List<HashMap<String, String>> lstProps = new ArrayList<>();
        lstProps.add(hashMapToken);

        LoadData loadData = new LoadData(link, lstProps);
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
    public String LikeProduct(String productId){
        String jsonData = "";
        String numberLike = "";
        String link = "http://"+ Host.getHost()+"/api/like_products";

        HashMap<String, String> hashMapToken = new HashMap<>();
        hashMapToken.put("token", ValueLocal.getToken());

        HashMap<String,String> hashMapProductId = new HashMap<>();
        hashMapProductId.put("product_id", productId);

        List<HashMap<String, String>> lstProps = new ArrayList<>();
        lstProps.add(hashMapToken);
        lstProps.add(hashMapProductId);

        LoadData loadData = new LoadData(link, lstProps);
        loadData.execute();

        try {
            jsonData = loadData.get();
            IOProduct ioProduct = new IOProduct();

            numberLike = ioProduct.likeProduct(jsonData);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return numberLike;
    }
    public List<Product> getUserListings(int userId){
        String jsonData = "";
        String link = "http://"+ Host.getHost()+"/api/get_user_listings?user_id=" + userId;
        List<Product> lstProducts = new ArrayList<>();

        LoadData loadData = new LoadData(link);
        loadData.execute();

        try {
            jsonData = loadData.get();
            IOProduct ioProduct = new IOProduct();
            lstProducts = ioProduct.getListings(jsonData);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return lstProducts;
    }
    public List<Product> getMoreProductsOfCategory(String categoryId, int index, int lastId) {
        String jsonData = "";
        String link = "http://"+ Host.getHost()+"/api/get_list_products";
        List<Product> lstProducts = new ArrayList<>();


        HashMap<String , String> hashMap0 = new HashMap<>();
        hashMap0.put("category_id", categoryId);

        HashMap<String , String> hashMap1 = new HashMap<>();
        hashMap1.put("last_id", lastId+"");

        HashMap<String, String> hashMap2 = new HashMap<>();
        hashMap2.put("index", index+"");

        List<HashMap<String, String>> lstProps = new ArrayList<>();
        lstProps.add(hashMap0);
        lstProps.add(hashMap1);
        lstProps.add(hashMap2);

        LoadData loadData = new LoadData(link,lstProps);
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
    public boolean addProduct(
            String name,
            String price,
            String size_id,
            String brand_id,
            String category_id,
            String described,
            String shipFrom,
            String condition_id,
            String dimension,
            String weight
    ){
        String jsonData = "";
        String link = "http://"+ Host.getHost()+"/api/add_products";

        HashMap<String, String> hashMapToken = new HashMap<>();
        hashMapToken.put("token", ValueLocal.getToken());

        HashMap<String, String> hashMapNameProduct = new HashMap<>();
        hashMapNameProduct.put("name", name);

        HashMap<String, String> hashMapPrice = new HashMap<>();
        hashMapPrice.put("price", price);

        HashMap<String, String> hashMapSizeId = new HashMap<>();
        hashMapSizeId.put("product_size_id", size_id);

        HashMap<String, String> hashMapBrandId = new HashMap<>();
        hashMapBrandId.put("brand_id", brand_id);

        HashMap<String, String> hashMapCategoryId = new HashMap<>();
        hashMapCategoryId.put("category_id", category_id);

        HashMap<String, String> hashMapDescribed = new HashMap<>();
        hashMapDescribed.put("described", described);

        HashMap<String, String> hashMapShipFrom = new HashMap<>();
        hashMapShipFrom.put("ships_from", shipFrom);

        HashMap<String, String> hashMapConditionId = new HashMap<>();
        hashMapPrice.put("condition_id", condition_id);

        HashMap<String, String> hashMapDimension = new HashMap<>();
        hashMapDimension.put("dimension", dimension);

        HashMap<String, String> hashMapWeight = new HashMap<>();
        hashMapWeight.put("weight", weight);

        List<HashMap<String, String>> lstProps = new ArrayList<>();
        lstProps.add(hashMapToken);
        lstProps.add(hashMapNameProduct);
        lstProps.add(hashMapPrice);
        lstProps.add(hashMapSizeId);
        lstProps.add(hashMapBrandId);
        lstProps.add(hashMapCategoryId);
        lstProps.add(hashMapDescribed);
        lstProps.add(hashMapShipFrom);
        lstProps.add(hashMapConditionId);
        lstProps.add(hashMapDimension);
        lstProps.add(hashMapWeight);

        LoadData loadData = new LoadData(link, lstProps);
        loadData.execute();

        try {
            jsonData = loadData.get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return true;
    }

}
