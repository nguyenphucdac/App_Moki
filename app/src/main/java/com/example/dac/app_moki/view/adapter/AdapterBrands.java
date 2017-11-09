package com.example.dac.app_moki.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.model.object.Brand;

import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Dac on 11/9/2017.
 */

public class AdapterBrands extends RecyclerView.Adapter<AdapterBrands.ViewHolder> {

    Context context;
    List<Brand> lstBrands;

    public AdapterBrands(Context context, List<Brand> lstBrands){
        this.context = context;
        this.lstBrands = lstBrands;
    }

    @Override
    public AdapterBrands.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.search_brand_item_custom,parent, false );

        AdapterBrands.ViewHolder viewHolder = new AdapterBrands.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterBrands.ViewHolder holder, int position) {
        final Brand brand = lstBrands.get(position);
        holder.nameBrand.setText(brand.getName());

        holder.itemViewBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("brand_id", (brand.getId()+"").toString());
                intent.putExtra("brand_name", (brand.getName()+"").toString());

                ((Activity)context).setResult(RESULT_OK, intent);
                ((Activity)context).finish();
            }
        });
    }


    @Override
    public int getItemCount() {
        return lstBrands.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameBrand;
        private View itemViewBrand;

        public ViewHolder(final View itemView) {
            super(itemView);
            nameBrand = (TextView) itemView.findViewById(R.id.name_brand_search);
            itemViewBrand = itemView.findViewById(R.id.item_brand_search);
        }
    }
}

