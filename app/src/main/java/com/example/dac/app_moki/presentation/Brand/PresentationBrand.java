package com.example.dac.app_moki.presentation.Brand;

import com.example.dac.app_moki.model.nesworks.Host;
import com.example.dac.app_moki.model.nesworks.LoadData;
import com.example.dac.app_moki.model.object.Brand;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dac on 11/9/2017.
 */

public class PresentationBrand {
    public List<Brand> getListBrands(){
        String jsonData = "";
        String link = "http://"+ Host.getHost()+"/api/get_list_brands";
        List<Brand> lsrBrands = new ArrayList<>();

        LoadData loadData = new LoadData(link);
        loadData.execute();

        try {
            jsonData = loadData.get();
            IOBrand ioBrand = new IOBrand();
            lsrBrands = ioBrand.getListBrands(jsonData);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return lsrBrands;
    }
}
