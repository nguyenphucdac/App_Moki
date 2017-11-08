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
import com.example.dac.app_moki.model.object.Product;
import com.example.dac.app_moki.view.product.ProductDetail_Activity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Dac on 11/2/2017.
 */

public class AdapterMenuListMyLike extends RecyclerView.Adapter<AdapterMenuListMyLike.ViewHolder> {

    Context context;
    List<Product> lstProducts;

    public AdapterMenuListMyLike(Context context, List<Product> lstProducts){
        this.context = context;
        this.lstProducts = lstProducts;
    }

    @Override
    public AdapterMenuListMyLike.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.menu_list_my_like_custom_item,parent, false );

        AdapterMenuListMyLike.ViewHolder viewHolder = new AdapterMenuListMyLike.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterMenuListMyLike.ViewHolder holder, int position) {
        final Product product = lstProducts.get(position);
        if(product.getImageOnList(0) != null){
            Picasso.with(context).load(product.getImageOnList(0)).into(holder.imageProduct);
        }
        else {
            Picasso.with(context).load(R.drawable.no_image).into(holder.imageProduct);
        }

        holder.nameProduct.setText(product.getName());
        holder.priceProduct.setText(product.getPrice() + " VNĐ");

        holder.itemProductMyLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), ProductDetail_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                intent.putExtra("productId",(product.getId()+"").toString());
                context.getApplicationContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return lstProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View itemProductMyLike;
        private ImageView imageProduct;
        private TextView nameProduct;
        private TextView priceProduct;

        public ViewHolder(final View itemView) {
            super(itemView);
            itemProductMyLike = itemView.findViewById(R.id.item_product_my_like);
            imageProduct= (ImageView) itemView.findViewById(R.id.image_product_my_like);
            nameProduct = (TextView) itemView.findViewById(R.id.name_product_my_like);
            priceProduct = (TextView) itemView.findViewById(R.id.price_product_my_like);
        }
    }
}


