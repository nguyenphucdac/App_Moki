package com.example.dac.app_moki.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.model.object.Message;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Dac on 11/20/2017.
 */

public class AdapterMessage extends RecyclerView.Adapter<AdapterMessage.ViewHolder> {
    Context context;
    List<Message> lstMessage;

    public AdapterMessage(Context context, List<Message> lstMessage){
        this.context = context;
        this.lstMessage = lstMessage;
    }

    @Override
    public AdapterMessage.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.message_custom,parent, false );

        AdapterMessage.ViewHolder viewHolder = new AdapterMessage.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterMessage.ViewHolder holder, int position) {
        Message message = lstMessage.get(position);

        holder.messageText.setText(message.getMessage());
        if(message.getUser().getImage() != null){
            Picasso.with(context).load(message.getUser().getImage()).into((ImageView) holder.avatar);
        }
        else {
            Picasso.with(context).load(R.drawable.unknown_user).into((ImageView) holder.avatar);
        }

    }

    @Override
    public int getItemCount() {

        return lstMessage.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View avatar;
        private TextView messageText;

        public ViewHolder(final View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar_user);
            messageText = (TextView) itemView.findViewById(R.id.message_text);
        }
    }
}

