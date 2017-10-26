package com.example.dac.app_moki.view.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.adapter.AdapterResultSearch;

import java.util.ArrayList;
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
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        resultSearch = (RecyclerView) findViewById(R.id.recycle_result_search);
        List<String> data = new ArrayList<>();
        for(int i = 0 ; i < 20 ; i++){
            data.add("name " + i);
        }

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        resultSearch.setLayoutManager(layoutManager);
        AdapterResultSearch adapterResultSearch = new AdapterResultSearch(this, data);
        resultSearch.setAdapter(adapterResultSearch);
        adapterResultSearch.notifyDataSetChanged();


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