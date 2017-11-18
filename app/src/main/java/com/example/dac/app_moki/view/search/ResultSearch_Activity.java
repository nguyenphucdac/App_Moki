package com.example.dac.app_moki.view.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.model.object.Product;
import com.example.dac.app_moki.presentation.product.PresentationProduct;
import com.example.dac.app_moki.view.adapter.AdapterListProductResult;

import java.util.List;

/**
 * Created by Dac on 10/21/2017.
 */
public class ResultSearch_Activity extends AppCompatActivity {

    RecyclerView resultSearch;
    ImageButton btnBack;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result_layout);

        addCotnrols();
        addEvents();
    }

    private void addCotnrols() {
        Intent myIntent = getIntent();
        String keyword = myIntent.getStringExtra("keyword");
        String categoryId = myIntent.getStringExtra("category_id");
        String brandId = myIntent.getStringExtra("brand_id");
        String sizeid = myIntent.getStringExtra("size_id");
        String conditionId = myIntent.getStringExtra("condition_id");

        btnBack = (ImageButton) findViewById(R.id.btnBack);
        resultSearch = (RecyclerView) findViewById(R.id.recycle_result_search);
        PresentationProduct presentationProduct = new PresentationProduct();
        List<Product> lstProduct = presentationProduct.getSerachResult(keyword, categoryId, brandId, sizeid, conditionId);

        resultSearch = (RecyclerView)findViewById(R.id.recycle_result_search);
        resultSearch.setLayoutManager(new GridLayoutManager(ResultSearch_Activity.this, 2));
        AdapterListProductResult adapterListProductResult = new AdapterListProductResult(ResultSearch_Activity.this, lstProduct);
        resultSearch.setAdapter(adapterListProductResult);
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
