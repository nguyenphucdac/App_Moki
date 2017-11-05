package com.example.dac.app_moki.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.view.product.ProductDetail_Activity;
import com.example.dac.app_moki.view.user.UserInfo_Activity;

import java.util.List;

/**
 * Created by Dac on 10/25/2017.
 */
public class AdapterListProductType2 extends RecyclerView.Adapter<AdapterListProductType2.ViewHolder> {
    Context context;
    List<String> lstString;

    public AdapterListProductType2(Context context, List<String> lstString){
        this.context = context;
        this.lstString = lstString;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.product_taball_type_2,parent, false );

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }



    @Override
    public int getItemCount() {

        return lstString.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View userInfo;
        private ImageButton imgProduct;
        private View infoProduct;
        public ViewHolder(final View itemView) {
            super(itemView);
            userInfo = itemView.findViewById(R.id.user_info_product_type_2);
            imgProduct = (ImageButton) itemView.findViewById(R.id.img_product_type_2);
            infoProduct = itemView.findViewById(R.id.info_product_type_2);

            userInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context.getApplicationContext(), UserInfo_Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    context.getApplicationContext().startActivity(intent);
                }
            });
            imgProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context.getApplicationContext(), ProductDetail_Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    context.getApplicationContext().startActivity(intent);
                }
            });

            infoProduct.setOnClickListener(new View.OnClickListener() {
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

