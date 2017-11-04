package com.example.dac.app_moki.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dac.app_moki.R;

import java.util.List;

/**
 * Created by Dac on 10/28/2017.
 */
public class AdapterMenuNews extends RecyclerView.Adapter<AdapterMenuNews.ViewHolder> {

    Context context;
    List<String> lstString;

    public AdapterMenuNews(Context context, List<String> lstString){
        this.context = context;
        this.lstString = lstString;
    }

    @Override
    public AdapterMenuNews.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.menu_news_custom_item,parent, false );

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return lstString.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(final View itemView) {
            super(itemView);

        }
    }
}

