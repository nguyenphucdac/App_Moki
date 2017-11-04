package com.example.dac.app_moki.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.view.product.ProductDetail_Activity;

import java.util.List;

/**
 * Created by Dac on 11/2/2017.
 */

public class AdapterMenuListMyLike extends RecyclerView.Adapter<AdapterMenuListMyLike.ViewHolder> {

    Context context;
    List<String> lstString;

    public AdapterMenuListMyLike(Context context, List<String> lstString){
        this.context = context;
        this.lstString = lstString;
    }

    @Override
    public AdapterMenuListMyLike.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.menu_list_my_like_custom_item,parent, false );

        AdapterMenuListMyLike.ViewHolder viewHolder = new AdapterMenuListMyLike.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterMenuListMyLike.ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return lstString.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View itemProductMyLike;
        public ViewHolder(final View itemView) {
            super(itemView);
            itemProductMyLike = itemView.findViewById(R.id.item_product_my_like);
            itemProductMyLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context.getApplicationContext(), ProductDetail_Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    context.getApplicationContext().startActivity(intent);
                }
            });
        }
    }
}


