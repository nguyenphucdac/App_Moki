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
import com.example.dac.app_moki.model.object.Condition;

import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Dac on 11/9/2017.
 */

public class AdapterConditions extends RecyclerView.Adapter<AdapterConditions.ViewHolder> {

    Context context;
    List<Condition> lstConditions;

    public AdapterConditions(Context context, List<Condition> lstConditions){
        this.context = context;
        this.lstConditions = lstConditions;
    }

    @Override
    public AdapterConditions.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.search_condition_item_custom,parent, false );

        AdapterConditions.ViewHolder viewHolder = new AdapterConditions.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterConditions.ViewHolder holder, int position) {
        final Condition condition = lstConditions.get(position);
        holder.nameCondition.setText(condition.getName());

        holder.itemViewCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("condition_id", (condition.getId()+"").toString());
                intent.putExtra("condition_name", (condition.getName()+"").toString());

                ((Activity)context).setResult(RESULT_OK, intent);
                ((Activity)context).finish();
            }
        });
    }


    @Override
    public int getItemCount() {
        return lstConditions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameCondition;
        private View itemViewCondition;

        public ViewHolder(final View itemView) {
            super(itemView);
            nameCondition = (TextView) itemView.findViewById(R.id.name_condition_search);
            itemViewCondition = itemView.findViewById(R.id.item_condition_search);
        }
    }
}


