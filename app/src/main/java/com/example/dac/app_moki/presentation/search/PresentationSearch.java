package com.example.dac.app_moki.presentation.search;

import com.example.dac.app_moki.model.nesworks.Host;
import com.example.dac.app_moki.model.nesworks.LoadData;
import com.example.dac.app_moki.model.object.Product;
import com.example.dac.app_moki.presentation.product.IOProduct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dac on 11/7/2017.
 */

public class PresentationSearch {
    public List<Product> getSerachResult(String keyword, String catergory, String brand, String size, String priceMin, String priceMax, String state){
        String jsonData = "";
        String link = "http://"+ Host.getHost()+"/api/search";
        List<Product> lstProducts = new ArrayList<>();

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("keyword", keyword);
        hashMap.put("category_id", catergory);
        hashMap.put("product_size_id", size);
        hashMap.put("brand_id", brand);
        hashMap.put("price_min", priceMax);
        hashMap.put("condition", state);

        LoadData loadData = new LoadData(link, hashMap);

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
}
