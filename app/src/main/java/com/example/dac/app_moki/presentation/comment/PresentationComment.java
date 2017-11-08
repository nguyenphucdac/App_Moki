package com.example.dac.app_moki.presentation.comment;

import com.example.dac.app_moki.model.nesworks.Host;
import com.example.dac.app_moki.model.nesworks.LoadData;
import com.example.dac.app_moki.model.object.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dac on 11/7/2017.
 */

public class PresentationComment {
    public List<Comment> getListComment (int productId){
        String jsonData = "";
        String link = "http://"+ Host.getHost()+"/api/get_comment_products?product_id=" + productId;
        List<Comment> lstComment = new ArrayList<>();

        LoadData loadData = new LoadData(link);
        loadData.execute();

        try {
            jsonData = loadData.get();
            IOComment ioComment = new IOComment();
            lstComment = ioComment.getListComment(jsonData);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return lstComment;
    }
    public List<Comment> setComment(String productId, String commentContent, String index){
        String jsonData = "";
        String link = "http://"+ Host.getHost()+"/api/get_comment_products";
        List<Comment> lstComment = new ArrayList<>();

        LoadData loadData = new LoadData(link);
        loadData.execute();

        try {
            jsonData = loadData.get();
            IOComment ioComment = new IOComment();
            lstComment = ioComment.getListComment(jsonData);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return lstComment;
    }

}
