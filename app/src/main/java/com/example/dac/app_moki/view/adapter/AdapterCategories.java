package com.example.dac.app_moki.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.model.object.Category;
import com.example.dac.app_moki.presentation.category.PresentationCategory;

import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Dac on 11/4/2017.
 */

public class AdapterCategories extends RecyclerView.Adapter<AdapterCategories.ViewHolder> {

    Context context;
    List<Category> lstCategories;
    RecyclerView recyclerViewCategories;
    PresentationCategory presentationCategory = new PresentationCategory();

    public AdapterCategories(Context context, List<Category> lstCategories, RecyclerView recyclerViewCategories){
        this.context = context;
        this.lstCategories = lstCategories;
        this.recyclerViewCategories = recyclerViewCategories;
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
        final Category category = lstCategories.get(position);
            holder.nameCategory.setText(category.getName());
            holder.itemViewCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if((presentationCategory.getChildeCategories(category.getId())).size() == 0 ){
                        Intent intent = new Intent();
                        intent.putExtra("category_id", (category.getId()+"").toString());
                        intent.putExtra("category_name", (category.getName()+"").toString());

                        ((Activity)context).setResult(RESULT_OK, intent);
                        ((Activity)context).finish();
                    }
                    else {
                        List<Category> lstCategories = presentationCategory.getChildeCategories(category.getId());

                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
                        recyclerViewCategories.setLayoutManager(layoutManager);

                        AdapterCategories adapterCategories = new AdapterCategories(context, lstCategories, recyclerViewCategories);
                        recyclerViewCategories.setAdapter(adapterCategories);
                        adapterCategories.notifyDataSetChanged();
                    }
                }
            });
            //Picasso.with(context).load(R.drawable.dot_active).into((ImageView) holder.imageNext);

    }


    @Override
    public int getItemCount() {
        return lstCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameCategory;
        private View itemViewCategory;
        private ImageView imageNext;

        public ViewHolder(final View itemView) {
            super(itemView);
            nameCategory = (TextView) itemView.findViewById(R.id.nameCategory);
            itemViewCategory = itemView.findViewById(R.id.item_category_search);
            imageNext = (ImageView) itemView.findViewById(R.id.display_next);
        }
    }
}

