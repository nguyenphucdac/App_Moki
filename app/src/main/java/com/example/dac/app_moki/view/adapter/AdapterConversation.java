package com.example.dac.app_moki.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.view.conversation.Chat_Activity;

import java.util.List;

/**
 * Created by Dac on 11/17/2017.
 */

public class AdapterConversation extends RecyclerView.Adapter<AdapterConversation.ViewHolder> {

    Context context;
    List<String> lstString;

    public AdapterConversation(Context context, List<String> lstString){
        this.context = context;
        this.lstString = lstString;
    }

    @Override
    public AdapterConversation.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_convesation_custom,parent, false );

        AdapterConversation.ViewHolder viewHolder = new AdapterConversation.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterConversation.ViewHolder holder, int position) {
        holder.itemConversation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), Chat_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                //intent.putExtra("productId",(product.getId()+"").toString());
                context.getApplicationContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return lstString.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View itemConversation;

        public ViewHolder(final View itemView) {
            super(itemView);
            itemConversation = itemView.findViewById(R.id.item_conversation);
        }
    }
}
