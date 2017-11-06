package com.example.dac.app_moki.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.model.object.Product;
import com.example.dac.app_moki.view.product.ProductDetail_Activity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Dac on 10/14/2017.
 */
public class AdapterListProductType1 extends RecyclerView.Adapter<AdapterListProductType1.ViewHolder> {
    Context context;
    List<Product> lstProduct;

    public AdapterListProductType1(Context context, List<Product> lstProduct){
        this.context = context;
        this.lstProduct = lstProduct;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.product_item_type_1,parent, false );

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.price.setText((lstProduct.get(position).getPrice()) + " K");
        holder.nameProduct.setText(lstProduct.get(position).getName());
        holder.numbeComment.setText((lstProduct.get(position).getNumberComment()) + "");
        holder.numberLike.setText((lstProduct.get(position).getNumberLike()) + "");
        if(lstProduct.get(position).getImageOnList(0) != null){
            Picasso.with(context).load(lstProduct.get(position).getImageOnList(0)).into(holder.imageProductType1);
        }
        else {
            Picasso.with(context).load(R.drawable.no_image).into(holder.imageProductType1);
        }
    }

    @Override
    public int getItemCount() {

        return lstProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View itemProductType1;
        private ImageButton imageProductType1;
        private TextView nameProduct;
        private TextView numberLike;
        private TextView numbeComment;
        private TextView price;

        public ViewHolder(final View itemView) {
            super(itemView);
            itemProductType1 = itemView.findViewById(R.id.item_product_type_1);
            imageProductType1 = (ImageButton) itemView.findViewById(R.id.image_product_type_1);
            nameProduct = (TextView) itemView.findViewById(R.id.name_product_type_1);
            numberLike = (TextView) itemView.findViewById(R.id.number_like_product_type_1   );
            numbeComment = (TextView) itemView.findViewById(R.id.number_comment_product_type_1);
            price = (TextView) itemView.findViewById(R.id.price_product_type_1);

            itemProductType1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context.getApplicationContext(), ProductDetail_Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    context.getApplicationContext().startActivity(intent);
                }
            });
            imageProductType1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context.getApplicationContext(), ProductDetail_Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    context.getApplicationContext().startActivity(intent);
                }
            });
        }
    }
}
