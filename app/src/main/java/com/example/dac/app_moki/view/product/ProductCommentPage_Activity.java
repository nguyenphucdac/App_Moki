package com.example.dac.app_moki.view.product;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.model.object.Comment;
import com.example.dac.app_moki.presentation.comment.PresentationComment;
import com.example.dac.app_moki.view.adapter.AdapterProductComment;

import java.util.List;

/**
 * Created by Dac on 11/3/2017.
 */

public class ProductCommentPage_Activity extends AppCompatActivity {
    private ImageButton btnBack;
    private RecyclerView recyclerViewComment;
    private Intent myIntent;
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
        myIntent = getIntent();
        String productId = myIntent.getStringExtra("productId");

        btnBack = (ImageButton) findViewById(R.id.product_comment_btn_back);

        PresentationComment presentationComment = new PresentationComment();
        List<Comment> lstComment = presentationComment.getListComment(Integer.parseInt(productId));

        recyclerViewComment = (RecyclerView) findViewById(R.id.recycle_product_comment);
        recyclerViewComment.setLayoutManager(new LinearLayoutManager(this));
        AdapterProductComment adapterProductComment = new AdapterProductComment(this, lstComment);
        recyclerViewComment.setAdapter(adapterProductComment);
        adapterProductComment.notifyDataSetChanged();


    }
}
