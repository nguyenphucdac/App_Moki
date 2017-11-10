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
    public List<Product> getListProducts(String categoryId) {
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
    public List<Product> getSerachResult(String keyword, String catergoryId){
        String jsonData = "";
        String link = "http://"+ Host.getHost()+"/api/search?category_id=" + catergoryId;
        List<Product> lstProducts = new ArrayList<>();

        LoadData loadData = new LoadData(link);

        loadData.execute();
        try {
            jsonData = loadData.get();
            IOProduct ioProduct = new IOProduct();
            lstProducts = ioProduct.getListProducts(jsonData, catergoryId);


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
        hashMapProductId.put("id", productId);

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
}
