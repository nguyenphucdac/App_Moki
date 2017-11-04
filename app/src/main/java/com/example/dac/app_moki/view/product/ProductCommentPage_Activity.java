package com.example.dac.app_moki.view.product;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.view.adapter.AdapterProductComment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dac on 11/3/2017.
 */

public class ProductCommentPage_Activity extends AppCompatActivity {
    private ImageButton btnBack;
    private RecyclerView recyclerViewComment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_comment);
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
        btnBack = (ImageButton) findViewById(R.id.product_comment_btn_back);

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            data.add("name " + i);
        }
        recyclerViewComment = (RecyclerView) findViewById(R.id.recycle_product_comment);
        recyclerViewComment.setLayoutManager(new LinearLayoutManager(this));
        AdapterProductComment adapterProductComment = new AdapterProductComment(this, data);
        recyclerViewComment.setAdapter(adapterProductComment);
        adapterProductComment.notifyDataSetChanged();


    }
}
