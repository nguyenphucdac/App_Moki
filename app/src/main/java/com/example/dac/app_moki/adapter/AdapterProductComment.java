package com.example.dac.app_moki.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dac.app_moki.R;

import java.util.List;

/**
 * Created by Dac on 11/3/2017.
 */

public class AdapterProductComment extends RecyclerView.Adapter<AdapterProductComment.ViewHolder> {
    Context context;
    List<String> lstString;

    public AdapterProductComment(Context context, List<String> lstString){
        this.context = context;
        this.lstString = lstString;
    }

    @Override
    public AdapterProductComment.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.product_custom_item_comment,parent, false );

        AdapterProductComment.ViewHolder viewHolder = new AdapterProductComment.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterProductComment.ViewHolder holder, int position) {

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
