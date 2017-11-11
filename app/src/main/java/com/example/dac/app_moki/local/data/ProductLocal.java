package com.example.dac.app_moki.local.data;

import com.example.dac.app_moki.model.object.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 11/10/2017.
 */

public class ProductLocal {
    private static List<List<Product>> lstProducs = new ArrayList<>();
    private static List<String> lstcategories = new ArrayList<>();

    public static List<Product> getLstProducs(String category) {
        int index = getcategories(category);
        if(index == -1){
            return null;
        }
        return lstProducs.get(index);
    }

    public static void setLstProducs(List<Product> lstProducs) {
        ProductLocal.lstProducs.add(lstProducs);
    }
    public static void setCategories(String category) {
        ProductLocal.lstcategories.add(category);
    }
    public static int getcategories(String category) {
        int index = -1;
        for(int i = 0 ; i < lstcategories.size(); i++){
            if(category == lstcategories.get(i)){
                index = i;
                break;
            }
        }
        return index;
    }


}
