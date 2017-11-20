package com.example.dac.app_moki.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.model.object.Conversation;
import com.example.dac.app_moki.view.conversation.Chat_Activity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Dac on 11/17/2017.
 */

public class AdapterConversation extends RecyclerView.Adapter<AdapterConversation.ViewHolder> {

    Context context;
    List<Conversation> lstConversation;

    public AdapterConversation(Context context, List<Conversation> lstConversation){
        this.context = context;
        System.out.println("size of list : " + lstConversation.size());
        this.lstConversation = lstConversation;
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
        final Conversation conversation = lstConversation.get(position);

        holder.itemConversation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), Chat_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                intent.putExtra("roomId", conversation.getId());
                context.getApplicationContext().startActivity(intent);
            }
        });
        Picasso.with(context).load(conversation.getUserSend().getImage()).into((ImageView) holder.avatar);

        holder.nameUser.setText(conversation.getUserSend().getUserName());
        holder.lastMessage.setText(conversation.getLastMessage());
    }


    @Override
    public int getItemCount() {
        return lstConversation.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View itemConversation;
        private View avatar;
        private TextView nameUser;
        private TextView lastMessage;

        public ViewHolder(final View itemView) {
            super(itemView);
            itemConversation = itemView.findViewById(R.id.item_conversation);
            avatar = itemView.findViewById(R.id.avatar_user);
            nameUser = (TextView) itemView.findViewById(R.id.name_user_conversation);
            lastMessage = (TextView) itemView.findViewById(R.id.last_message);

        }
    }
}
