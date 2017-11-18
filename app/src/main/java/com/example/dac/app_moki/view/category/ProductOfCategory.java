package com.example.dac.app_moki.view.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.local.data.ProductLocal;
import com.example.dac.app_moki.model.object.Product;
import com.example.dac.app_moki.presentation.product.PresentationProduct;
import com.example.dac.app_moki.view.adapter.AdapterListProductResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 11/18/2017.
 */

public class ProductOfCategory extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageButton btnBack;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_list_product);

        addCotnrols();
        addEvents();
    }

    private void addCotnrols() {
        Intent myIntent = getIntent();
        String categoryId = myIntent.getStringExtra("categoryId");

        System.out.println("cadkjsfsadf : " + categoryId);

        btnBack = (ImageButton) findViewById(R.id.btnBack);
        recyclerView = (RecyclerView) findViewById(R.id.recycle_result_search);
        PresentationProduct presentationProduct = new PresentationProduct();
        List<Product> lstProduct = new ArrayList<>();

        if( ProductLocal.getLstProducs(categoryId) != null){
            lstProduct = ProductLocal.getLstProducs(categoryId);
        }
        else {
            lstProduct = presentationProduct.getListProductsOfCategory(categoryId);
        }



        recyclerView = (RecyclerView)findViewById(R.id.category_list_product);
        recyclerView.setLayoutManager(new GridLayoutManager(ProductOfCategory.this, 2));
        AdapterListProductResult adapterListProductResult = new AdapterListProductResult(ProductOfCategory.this, lstProduct);
        recyclerView.setAdapter(adapterListProductResult);
        adapterListProductResult.notifyDataSetChanged();

    }
    private void addEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
