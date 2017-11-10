package com.example.dac.app_moki.local.data;

import com.example.dac.app_moki.model.object.Product;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Dac on 11/10/2017.
 */

public class ProductLocal {
    private static HashMap<String, List<Product>> lstProductsOfCategory = new HashMap<>();
    private static HashMap<String, Integer> lstLastId = new HashMap<>();

    public static List<Product> getListProductOfCategory(String catergoryId) {
        System.out.println("value of category : " + catergoryId);
        Set set = lstProductsOfCategory.entrySet();
        Iterator i = set.iterator();
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            if(me.getKey() == catergoryId){
                return lstProductsOfCategory.get(me.getKey());
            }
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());
            break;
        }
        return null;
    }

    public static void addListProductOfCategory(String categoryId, List<Product> lstProducts) {
        System.out.println("add catetory = " + categoryId );
        lstProductsOfCategory.put(categoryId, lstProducts);
    }

    public static int getListLastIdCategory(String catergoryId) {
        Set set = lstLastId.entrySet();
        Iterator i = set.iterator();
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            if(me.getKey() == catergoryId){
                return lstLastId.get(me.getKey());
            }
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());
            break;
        }
        return 0;
    }

    public static void addLstLastId(String CategoryId,int lastId) {
        lstLastId.put(CategoryId, lastId);
    }
}
