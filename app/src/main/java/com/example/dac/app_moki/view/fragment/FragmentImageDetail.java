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
 * Created by Dac on 11/15/2017.
 */

public class FragmentImageDetail extends Fragment {

    private String image = "";
    private ImageView itemImage;
    public FragmentImageDetail(){
        this.image = null;
    }
    public FragmentImageDetail(String image){
        this.image=image;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_detail, container, false);
        itemImage = (ImageView) view.findViewById(R.id.item_image_view_product_detail);

        if(image != null){
            Picasso.with(view.getContext()).load(image).into(itemImage);
        }
        else {
            Picasso.with(view.getContext()).load(R.drawable.no_image).into(itemImage);
        }

        //itemImage.setOnTouchListener(new Touch());

        return  view;
    }
}
