package com.example.dac.app_moki.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.model.object.Comment;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Dac on 11/3/2017.
 */

public class AdapterProductComment extends RecyclerView.Adapter<AdapterProductComment.ViewHolder> {
    Context context;
    List<Comment> lstComment;

    public AdapterProductComment(Context context, List<Comment> lstComment){
        this.context = context;
        this.lstComment = lstComment;
    }

    @Override
    public AdapterProductComment.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.product_custom_item_comment,parent, false );

        AdapterProductComment.ViewHolder viewHolder = new AdapterProductComment.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterProductComment.ViewHolder holder, int position) {
        Comment comment = lstComment.get(position);

        holder.userName.setText(comment.getUser().getUserName());
        if(comment.getUser().getImage() != null){
            Picasso.with(context).load(comment.getUser().getImage()).into((ImageView) holder.avatar);
        }
        else {
            Picasso.with(context).load(R.drawable.unknown_user).into((ImageView) holder.avatar);
        }
        holder.content.setText(comment.getContent());
    }

    @Override
    public int getItemCount() {

        return lstComment.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView userName = (TextView) itemView.findViewById(R.id.user_name_comment);
        View avatar = itemView.findViewById(R.id.avatar_comment);
        TextView content = (TextView) itemView.findViewById(R.id.content_comment);
        TextView fromDate = (TextView) itemView.findViewById(R.id.from_date_comment);

        public ViewHolder(final View itemView) {
            super(itemView);
        }
    }
}
