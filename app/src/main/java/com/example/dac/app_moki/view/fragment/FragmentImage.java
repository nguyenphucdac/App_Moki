package com.example.dac.app_moki.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dac.app_moki.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Dac on 11/8/2017.
 */

public class FragmentImage extends Fragment {

    private String image = "";
    private ImageView itemImage;
    public FragmentImage(){
        this.image = null;
    }
    public FragmentImage(String image){
        this.image=image;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_detail_list_image, container, false);
        itemImage = (ImageView) view.findViewById(R.id.image_item);

        if(image != null){
            Picasso.with(view.getContext()).load(image).into(itemImage);
        }
        else {
            Picasso.with(view.getContext()).load(R.drawable.no_image).into(itemImage);
        }

        return  view;
    }
}
