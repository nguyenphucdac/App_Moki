package com.example.dac.app_moki.presentation.condition;

import com.example.dac.app_moki.model.nesworks.Host;
import com.example.dac.app_moki.model.nesworks.LoadData;
import com.example.dac.app_moki.model.object.Condition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dac on 11/9/2017.
 */

public class PresentationCondition {
    public List<Condition> getListConditions(){
        String jsonData = "";
        String link = "http://"+ Host.getHost()+"/api/get_list_conditions";
        List<Condition> lstConditions = new ArrayList<>();

        LoadData loadData = new LoadData(link);
        loadData.execute();

        try {
            jsonData = loadData.get();
            IOCondition ioCondition = new IOCondition();
            lstConditions = ioCondition.getListConditions(jsonData);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return lstConditions;
    }
}
