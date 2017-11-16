package com.example.dac.app_moki.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.view.product.ImageDetail;
import com.squareup.picasso.Picasso;

/**
 * Created by Dac on 11/8/2017.
 */

public class FragmentImage extends Fragment {

    private String image = "";
    private ImageButton itemImage;
    private String productId;

    public FragmentImage(){
        this.image = null;
    }
    public FragmentImage(String image, String productId){
        this.image=image;
        this.productId = productId;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_list_image, container, false);
        itemImage = (ImageButton) view.findViewById(R.id.image_item);

        if(image != null){
            Picasso.with(view.getContext()).load(image).into(itemImage);
        }
        else {
            Picasso.with(view.getContext()).load(R.drawable.no_image).into(itemImage);
        }

        itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ImageDetail.class);
                intent.putExtra("productId", productId);
                startActivity(intent);
            }
        });

        return  view;
    }
}
