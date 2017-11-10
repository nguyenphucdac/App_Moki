package com.example.dac.app_moki.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.model.object.Product;
import com.example.dac.app_moki.view.product.ProductDetail_Activity;
import com.example.dac.app_moki.view.user.UserInfo_Activity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Dac on 10/25/2017.
 */
public class AdapterListProductType2 extends RecyclerView.Adapter<AdapterListProductType2.ViewHolder> {
    Context context;
    List<Product> lstProducts;

    public AdapterListProductType2(Context context, List<Product> lstProducts){
        this.context = context;
        this.lstProducts = lstProducts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.product_item_type_2,parent, false );

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Product product = lstProducts.get(position);

        holder.nameProduct.setText(product.getName());
        if(product.getImageOnList(0) != null){
            Picasso.with(context).load(product.getImageOnList(0)).into(holder.imageProduct);
        }
        else {
            Picasso.with(context).load(R.drawable.no_image).into(holder.imageProduct);
        }
        holder.priceProduct.setText(String.format("%,d", product.getPrice()) + " VNĐ");
        holder.btnLike.setText(String.valueOf(product.getNumberLike()));
        holder.btnComment.setText(String.valueOf(product.getNumberComment()));

        if(product.getSeller().getImage() != null){
            Picasso.with(context).load(product.getSeller().getImage()).into((ImageView) holder.avatarSeller);
        }
        else {
            Picasso.with(context).load(R.drawable.unknown_user).into((ImageView) holder.avatarSeller);
        }

        holder.nameSeller.setText(String.valueOf(product.getSeller().getNameShop()));

        holder.userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context.getApplicationContext(), UserInfo_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                intent.putExtra("user_id", (product.getSeller().getId() + "").toString());
                context.getApplicationContext().startActivity(intent);
            }
        });
        holder.imageProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context.getApplicationContext(), ProductDetail_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                intent.putExtra("productId",(product.getId()+"").toString());
                context.getApplicationContext().startActivity(intent);
            }
        });

        holder.infoProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context.getApplicationContext(), ProductDetail_Activity.class);
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
        private View userInfo;
        private ImageButton imageProduct;
        private View infoProduct;
        private View avatarSeller;
        private TextView nameSeller;
        private TextView nameProduct;
        private TextView priceProduct;
        private Button btnLike;
        private Button btnComment;

        public ViewHolder(final View itemView) {
            super(itemView);
            userInfo = itemView.findViewById(R.id.user_info_product_type_2);
            imageProduct = (ImageButton) itemView.findViewById(R.id.img_product_type_2);
            infoProduct = itemView.findViewById(R.id.info_product_type_2);
            avatarSeller = itemView.findViewById(R.id.avatarSeller);
            nameSeller = (TextView) itemView.findViewById(R.id.nameSeller);
            nameProduct = (TextView) itemView.findViewById(R.id.name_product_type_2);
            priceProduct = (TextView) itemView.findViewById(R.id.price_product_type_2);
            btnLike = (Button) itemView.findViewById(R.id.button_like_product_type_2);
            btnComment = (Button) itemView.findViewById(R.id.button_comment_product_type_2);
        }
    }
}

