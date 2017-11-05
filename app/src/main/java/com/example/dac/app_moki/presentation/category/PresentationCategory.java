package com.example.dac.app_moki.presentation.category;

import com.example.dac.app_moki.model.nesworks.LoadData;
import com.example.dac.app_moki.model.object.Category;
import com.example.dac.app_moki.model.parsejson.IOCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dac on 11/4/2017.
 */

public class PresentationCategory implements IPresentationCategory {
    @Override
    public List<Category> getListCategories() {
        String jsonData = "";
        String link = "http://192.168.1.2:1337/api/get_categories";
        List<Category> lstCategories = new ArrayList<>();

        LoadData loadData = new LoadData(link);
        loadData.execute();

        try {
            jsonData = loadData.get();
            IOCategory ioCategory = new IOCategory();
            lstCategories = IOCategory.getListCategory(jsonData);

            return lstCategories;

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return lstCategories;
    }
}
