package com.example.dac.app_moki.presentation.category;

import com.example.dac.app_moki.model.nesworks.Host;
import com.example.dac.app_moki.model.nesworks.LoadData;
import com.example.dac.app_moki.model.object.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dac on 11/4/2017.
 */

public class PresentationCategory{
    public List<Category> getListCategories() {
        String jsonData = "";
        String link = "http://"+ Host.getHost()+"/api/get_categories";
        List<Category> lstCategories = new ArrayList<>();

        LoadData loadData = new LoadData(link);
        loadData.execute();

        try {
            jsonData = loadData.get();
            IOCategory ioCategory = new IOCategory();
            lstCategories = ioCategory.getListCategory(jsonData);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return lstCategories;
    }
   public List<Category> getChildeCategories(int categoryId){
       String jsonData = "";
       String link = "http://"+ Host.getHost()+"/api/get_categories";
       List<Category> lstCategories = new ArrayList<>();

       LoadData loadData = new LoadData(link);
       loadData.execute();

       try {
           jsonData = loadData.get();
           IOCategory ioCategory = new IOCategory();
           lstCategories = ioCategory.getChildeCatetory(jsonData, categoryId);
       } catch (InterruptedException e) {
           e.printStackTrace();
       } catch (ExecutionException e) {
           e.printStackTrace();
       }
       return lstCategories;
   }
}
