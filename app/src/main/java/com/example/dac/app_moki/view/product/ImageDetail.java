package com.example.dac.app_moki.view.product;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.model.object.Product;
import com.example.dac.app_moki.presentation.product.PresentationProduct;
import com.example.dac.app_moki.view.adapter.AdapterImageDetail;

/**
 * Created by Dac on 11/15/2017.
 */

public class ImageDetail extends AppCompatActivity {

    private ViewPager viewPagerListImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_image_detail);

        Intent intent = getIntent();

        String productId = intent.getStringExtra("productId");

        PresentationProduct presentationProduct = new PresentationProduct();
        Product product = presentationProduct.getProduct(Integer.parseInt(productId));

        viewPagerListImage = (ViewPager) findViewById(R.id.viewpager_list_image_detail);
        AdapterImageDetail adapterImageDetail = new AdapterImageDetail(getSupportFragmentManager(), product.getListImage());
        viewPagerListImage.setAdapter(adapterImageDetail);

    }
}
