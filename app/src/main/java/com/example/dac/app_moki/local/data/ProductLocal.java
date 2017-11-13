package com.example.dac.app_moki.local.data;

import com.example.dac.app_moki.model.object.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 11/10/2017.
 */

public class ProductLocal {
    private static List<List<Product>> lstProductss = new ArrayList<>();
    private static List<String> lstcategoriesId = new ArrayList<>();
    private static List<String> lstlastId = new ArrayList<>();

    public static void setLastId(String lastId){
        lstlastId.add(lastId);
    }

    public static String getLastId(String categoryId){
        int index = getcategories(categoryId);
        if(index == -1){
            return null;
        }
        return lstlastId.get(index);
    }

    public static void updateLastId(String lastId, String categoryId){
        int index = getcategories(categoryId);
        lstlastId.set(index, lastId);
    }

    public static List<Product> getLstProducs(String categoryId) {
        int index = getcategories(categoryId);
        if(index == -1){
            return null;
        }
        return lstProductss.get(index);
    }

    public static void setLstProducs(List<Product> lstProducs) {
        ProductLocal.lstProductss.add(lstProducs);
    }
    public static void setCategories(String categoryId) {
        ProductLocal.lstcategoriesId.add(categoryId);
    }
    public static int getcategories(String categoryId) {
        int index = -1;
        for(int i = 0 ; i < lstcategoriesId.size(); i++){
            if(categoryId == lstcategoriesId.get(i)){
                index = i;
                break;
            }
        }
        return index;
    }
    public static void updatelstProduct(List<Product> lstProduct, String categoryId){
        int index = getcategories(categoryId);
        ProductLocal.lstProductss.set(index, lstProduct);
    }

    public static void appendListProduct(List<Product> lstProduct, String categoryId){
        int index = getcategories(categoryId);
        if(index == -1){
            return;
        }
        lstProductss.get(index).addAll(lstProduct);
    }
}
