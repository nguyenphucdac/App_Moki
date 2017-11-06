package com.example.dac.app_moki.model.nesworks;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Dac on 11/5/2017.
 */

public class LoadMore extends RecyclerView.OnScrollListener {
    private int aboveItem;
    private int numberItem;
    private int itemMore;
    private RecyclerView.LayoutManager layoutManager;

    public LoadMore(RecyclerView.LayoutManager layoutManager){
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        numberItem = layoutManager.getItemCount();
        if(layoutManager instanceof LinearLayoutManager){
            aboveItem = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }
        else if(layoutManager instanceof GridLayoutManager){
            aboveItem = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }

    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }
}
