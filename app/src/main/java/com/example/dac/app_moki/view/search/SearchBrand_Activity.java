package com.example.dac.app_moki.view.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.model.object.Brand;
import com.example.dac.app_moki.presentation.Brand.PresentationBrand;
import com.example.dac.app_moki.view.adapter.AdapterBrands;

import java.util.List;

/**
 * Created by Dac on 11/3/2017.
 */

public class SearchBrand_Activity extends AppCompatActivity {

    private ImageButton btnBack;
    private RecyclerView recyclerViewBrands;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_branh);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void addControls() {
        btnBack = (ImageButton) findViewById(R.id.search_brand_btn_back);
        recyclerViewBrands = (RecyclerView) findViewById(R.id.recycle_brands_search);

        PresentationBrand presentationBrand = new PresentationBrand();
        List<Brand> lstBrands =  presentationBrand.getListBrands();


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewBrands.setLayoutManager(layoutManager);

        AdapterBrands adapterBrands = new AdapterBrands(this, lstBrands);
        recyclerViewBrands.setAdapter(adapterBrands);
        adapterBrands.notifyDataSetChanged();

    }
}
