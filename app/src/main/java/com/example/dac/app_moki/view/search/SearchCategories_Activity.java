package com.example.dac.app_moki.view.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.model.object.Category;
import com.example.dac.app_moki.presentation.category.PresentationCategory;
import com.example.dac.app_moki.view.adapter.AdapterCategories;

import java.util.List;

/**
 * Created by Dac on 11/3/2017.
 */

public class SearchCategories_Activity extends AppCompatActivity {
    private ImageButton btnBack;
    private RecyclerView recyclerViewCategories;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_categories);

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
        btnBack = (ImageButton) findViewById(R.id.search_categories_btn_back);

        recyclerViewCategories = (RecyclerView) findViewById(R.id.recycle_categories_search);

        PresentationCategory presentationCategory = new PresentationCategory();
        List<Category> lstCategories =  presentationCategory.getListCategories();


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewCategories.setLayoutManager(layoutManager);

        AdapterCategories adapterCategories = new AdapterCategories(this, lstCategories);
        recyclerViewCategories.setAdapter(adapterCategories);
        adapterCategories.notifyDataSetChanged();
    }
}

