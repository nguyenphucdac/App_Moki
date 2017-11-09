package com.example.dac.app_moki.view.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.model.object.Size;
import com.example.dac.app_moki.presentation.size.PresentationSize;
import com.example.dac.app_moki.view.adapter.AdapterSizes;

import java.util.List;

/**
 * Created by Dac on 11/3/2017.
 */

public class SearchSize_Activity extends AppCompatActivity {
    private RecyclerView recyclerViewSize;
    private ImageButton btnBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_size);

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
        btnBack = (ImageButton) findViewById(R.id.search_size_btn_back);

        recyclerViewSize = (RecyclerView) findViewById(R.id.recycle_sizes_search);

        PresentationSize presentationSize = new PresentationSize();
        List<Size> lstSizes =  presentationSize.getListSizes();


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewSize.setLayoutManager(layoutManager);

        AdapterSizes adapterSizes = new AdapterSizes(this, lstSizes);
        recyclerViewSize.setAdapter(adapterSizes);
        adapterSizes.notifyDataSetChanged();

    }
}
