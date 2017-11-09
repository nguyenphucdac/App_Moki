package com.example.dac.app_moki.view.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.model.object.Condition;
import com.example.dac.app_moki.presentation.condition.PresentationCondition;
import com.example.dac.app_moki.view.adapter.AdapterConditions;

import java.util.List;

/**
 * Created by Dac on 11/3/2017.
 */

public class SearchCondition_Activity extends AppCompatActivity {

    private RecyclerView recyclerViewCondition;
    private ImageButton btnBack;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_condition);

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
        btnBack = (ImageButton) findViewById(R.id.search_state_btn_back);

        recyclerViewCondition = (RecyclerView) findViewById(R.id.recycle_coditions_search);

        PresentationCondition presentationCondition = new PresentationCondition();
        List<Condition> lstConditions =  presentationCondition.getListConditions();


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewCondition.setLayoutManager(layoutManager);

        AdapterConditions adapterConditions = new AdapterConditions(this, lstConditions);
        recyclerViewCondition.setAdapter(adapterConditions);
        adapterConditions.notifyDataSetChanged();
    }
}

