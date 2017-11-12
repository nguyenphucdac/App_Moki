package com.example.dac.app_moki.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.local.value.ValueLocal;

/**
 * Created by Dac on 11/12/2017.
 */

public class AdapterSortValue  extends RecyclerView.Adapter<AdapterSortValue.ViewHolder> {

    Context context;
    String[] arrValues;

    public AdapterSortValue(Context context, String[] values){
        this.context = context;
        this.arrValues = values;
    }
    @Override
    public AdapterSortValue.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.list_check_textview_item,parent, false );

        AdapterSortValue.ViewHolder viewHolder = new AdapterSortValue.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final AdapterSortValue.ViewHolder holder, final int position) {
        holder.itemTextView.setText(arrValues[position]);
        holder.itemTextView.setChecked(false);

        holder.itemTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.itemTextView.isChecked()) {
                    holder.itemTextView.setCheckMarkDrawable(R.drawable.dot_active);
                    holder.itemTextView.setChecked(false);
                } else {
                    holder.itemTextView.setCheckMarkDrawable(R.drawable.checked);
                    holder.itemTextView.setChecked(true);
                    if(position == 0){
                        ValueLocal.setSort("p_price");
                        ValueLocal.setTypeSort("1");
                    }
                    else if(position == 1){
                        ValueLocal.setSort("p_price");
                        ValueLocal.setTypeSort("0");
                    }
                    else {
                        ValueLocal.setSort("p_fromdate");
                        ValueLocal.setTypeSort("0");
                    }
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return arrValues.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CheckedTextView itemTextView;
        public ViewHolder(final View itemView) {
            super(itemView);
            itemTextView = (CheckedTextView) itemView.findViewById(R.id.checked_text_view);

        }
    }
}