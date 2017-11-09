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
import com.example.dac.app_moki.model.object.Size;

import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Dac on 11/9/2017.
 */

public class AdapterSizes extends RecyclerView.Adapter<AdapterSizes.ViewHolder> {

    Context context;
    List<Size> lstSizes;

    public AdapterSizes(Context context, List<Size> lstSizes){
        this.context = context;
        this.lstSizes = lstSizes;
    }

    @Override
    public AdapterSizes.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.search_size_item_custom,parent, false );

        AdapterSizes.ViewHolder viewHolder = new AdapterSizes.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterSizes.ViewHolder holder, int position) {
        final Size size = lstSizes.get(position);
        holder.nameSize.setText(size.getName());

        holder.itemViewSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("size_id", (size.getId()+"").toString());
                intent.putExtra("size_name", (size.getName()+"").toString());

                ((Activity)context).setResult(RESULT_OK, intent);
                ((Activity)context).finish();
            }
        });
    }


    @Override
    public int getItemCount() {
        return lstSizes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameSize;
        private View itemViewSize;

        public ViewHolder(final View itemView) {
            super(itemView);
            nameSize = (TextView) itemView.findViewById(R.id.name_size_search);
            itemViewSize = itemView.findViewById(R.id.item_size_search);
        }
    }
}

