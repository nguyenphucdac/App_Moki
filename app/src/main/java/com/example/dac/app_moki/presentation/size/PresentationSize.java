package com.example.dac.app_moki.presentation.size;

import com.example.dac.app_moki.model.nesworks.Host;
import com.example.dac.app_moki.model.nesworks.LoadData;
import com.example.dac.app_moki.model.object.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dac on 11/9/2017.
 */

public class PresentationSize {
    public List<Size> getListSizes(){
        String jsonData = "";
        String link = "http://"+ Host.getHost()+"/api/get_list_Sizes";
        List<Size> lsrSizes = new ArrayList<>();

        LoadData loadData = new LoadData(link);
        loadData.execute();

        try {
            jsonData = loadData.get();
            IOSize ioSize = new IOSize();
            lsrSizes = ioSize.getListSizes(jsonData);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return lsrSizes;
    }
}
