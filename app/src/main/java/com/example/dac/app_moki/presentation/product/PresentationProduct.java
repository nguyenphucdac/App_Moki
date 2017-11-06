package com.example.dac.app_moki.presentation.product;

import com.example.dac.app_moki.model.nesworks.LoadData;
import com.example.dac.app_moki.model.object.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dac on 11/6/2017.
 */

public class PresentationProduct implements IPresentationProduct {
    @Override
    public List<Product> getListProducts() {
        String jsonData = "";
        String link = "http://192.168.1.251:1337/api/get_list_products";
        List<Product> lstProducts = new ArrayList<>();

        LoadData loadData = new LoadData(link);
        loadData.execute();

        try {
            jsonData = loadData.get();
            lstProducts = IOProduct.getListProducts(jsonData);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return lstProducts;
    }
}
