package com.example.dac.app_moki.view.category;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.local.data.ProductLocal;
import com.example.dac.app_moki.model.object.Product;
import com.example.dac.app_moki.presentation.product.PresentationProduct;
import com.example.dac.app_moki.view.adapter.AdapterProductType1;
import com.example.dac.app_moki.view.adapter.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 11/18/2017.
 */

public class ProductOfCategory extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ImageButton btnBack;
    private TextView nameCategory;
    private List<Product> lstProduct;
    private PresentationProduct presentationProduct;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_list_product);

        addCotnrols();
        addEvents();
    }

    private void addCotnrols() {
        Intent myIntent = getIntent();
        final String categoryId = myIntent.getStringExtra("categoryId");
        String categoryName = myIntent.getStringExtra("categoryName");

        btnBack = (ImageButton) findViewById(R.id.btnBack);
        nameCategory = (TextView) findViewById(R.id.name_category_list_product);
        nameCategory.setText(categoryName);
        recyclerView = (RecyclerView) findViewById(R.id.recycle_result_search);
        presentationProduct = new PresentationProduct();
        lstProduct = new ArrayList<>();

        if( ProductLocal.getLstProducs(categoryId) != null){
            lstProduct = ProductLocal.getLstProducs(categoryId);
        }
        else {
            lstProduct = presentationProduct.getListProductsOfCategory(categoryId);
        }

//        recyclerView = (RecyclerView)findViewById(R.id.category_list_product);
//        recyclerView.setLayoutManager(new GridLayoutManager(ProductOfCategory.this, 2));
//        AdapterListProductResult adapterListProductResult = new AdapterListProductResult(ProductOfCategory.this, lstProduct);
//        recyclerView.setAdapter(adapterListProductResult);
//        adapterListProductResult.notifyDataSetChanged();

        recyclerView = (RecyclerView) findViewById(R.id.category_list_product);
        recyclerView.setLayoutManager(new GridLayoutManager(ProductOfCategory.this, 2));
        final AdapterProductType1 adapterProductType1 = new AdapterProductType1(ProductOfCategory.this, lstProduct, recyclerView);
        recyclerView.setAdapter(adapterProductType1);
        adapterProductType1.notifyDataSetChanged();

        adapterProductType1.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                lstProduct.add(null);
                adapterProductType1.notifyItemInserted(lstProduct.size() - 1);
                //Load more data for reyclerview
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //Remove loading item
                        lstProduct.remove(lstProduct.size() - 1);
                        adapterProductType1.notifyItemRemoved(lstProduct.size());
                        //Load data
                        int index = lstProduct.size();
                        int end = index + 10;

                        List<Product> lstProductMore = presentationProduct.getMoreProductsOfCategory(categoryId, lstProduct.size(), Integer.parseInt(ProductLocal.getLastId(categoryId)));
                        lstProduct.addAll(lstProductMore);

                        adapterProductType1.notifyDataSetChanged();
                        adapterProductType1.setLoaded();
                    }
                }, 4000);
            }
        });

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
