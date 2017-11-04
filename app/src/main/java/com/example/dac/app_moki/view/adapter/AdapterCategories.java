package com.example.dac.app_moki.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.model.object.Category;

import java.util.List;

/**
 * Created by Dac on 11/4/2017.
 */

public class AdapterCategories extends RecyclerView.Adapter<AdapterCategories.ViewHolder> {

    Context context;
    List<Category> lstCategories;

    public AdapterCategories(Context context, List<Category> lstCategories){
        this.context = context;
        this.lstCategories = lstCategories;
    }

    @Override
    public AdapterCategories.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.search_categories_item_custom,parent, false );

        AdapterCategories.ViewHolder viewHolder = new AdapterCategories.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterCategories.ViewHolder holder, int position) {
        holder.nameCategory.setText(lstCategories.get(position).getName());
    }


    @Override
    public int getItemCount() {
        return lstCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameCategory;
        public ViewHolder(final View itemView) {
            super(itemView);
            nameCategory = (TextView) itemView.findViewById(R.id.nameCategory);
        }
    }
}

