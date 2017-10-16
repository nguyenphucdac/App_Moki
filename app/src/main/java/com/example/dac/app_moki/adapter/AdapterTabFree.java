package com.example.dac.app_moki.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dac.app_moki.R;

import java.util.List;

/**
 * Created by Dac on 10/14/2017.
 */
public class AdapterTabFree extends RecyclerView.Adapter<AdapterTabFree.ViewHolder> {
    Context context;
    List<String> lstString;

    public AdapterTabFree(Context context, List<String> lstString){
        this.context = context;
        this.lstString = lstString;
    }

    @Override
    public AdapterTabFree.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.product_taball_type_2,parent, false );

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterTabFree.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return lstString.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
