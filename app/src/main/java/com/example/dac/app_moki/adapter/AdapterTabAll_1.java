package com.example.dac.app_moki.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.view.product.ProductDetail_Activity;

import java.util.List;

/**
 * Created by Dac on 10/14/2017.
 */
public class AdapterTabAll_1 extends RecyclerView.Adapter<AdapterTabAll_1.ViewHolder> {
    Context context;
    List<String> lstString;

    public AdapterTabAll_1(Context context, List<String> lstString){
        this.context = context;
        this.lstString = lstString;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.product_taball_type_1,parent, false );

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
        ImageButton img_prouduct;
        TextView txt_nameProduct;
        public ViewHolder(final View itemView) {
            super(itemView);
            img_prouduct = (ImageButton) itemView.findViewById(R.id.img_product_type_1);
            txt_nameProduct = (TextView) itemView.findViewById(R.id.txt_nameProduct);

            img_prouduct.setOnClickListener(new View.OnClickListener() {
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