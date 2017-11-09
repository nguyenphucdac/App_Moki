package com.example.dac.app_moki.local.data;

import com.example.dac.app_moki.model.object.Category;
import com.example.dac.app_moki.model.object.Product;

import java.util.List;

/**
 * Created by Dac on 11/9/2017.
 */

public class CategoryLocal {

    public static List<Category> lstCategoriesRoot;
    public static List<Product> lstProductOfCategory;

    public static List<Product> getLstProductOfCategory() {
        return lstProductOfCategory;
    }

    public static void setLstProductOfCategory(List<Product> lstProductOfCategory) {
        CategoryLocal.lstProductOfCategory = lstProductOfCategory;
    }

    public static List<Category> getLstCategoriesRoot() {
        return lstCategoriesRoot;
    }

    public static void setLstCategoriesRoot(List<Category> lstCategoriesRoot) {
        CategoryLocal.lstCategoriesRoot = lstCategoriesRoot;
    }
}
