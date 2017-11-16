package com.example.dac.app_moki.view.product;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.local.value.ValueLocal;
import com.example.dac.app_moki.model.object.Comment;
import com.example.dac.app_moki.presentation.comment.PresentationComment;
import com.example.dac.app_moki.view.adapter.AdapterProductComment;
import com.example.dac.app_moki.view.login.Login_Activity;

import java.util.List;

/**
 * Created by Dac on 11/3/2017.
 */

public class ProductCommentPage_Activity extends AppCompatActivity {
    private ImageButton btnBack;
    private RecyclerView recyclerViewComment;
    private ImageButton btnComment;
    private EditText contentComment;
    private int lastId = 0 ;

    private Intent myIntent;
    private String productId;
    private List<Comment> lstComment;
    private PresentationComment presentationComment;

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
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = (contentComment.getText()).toString();
                if(ValueLocal.getToken() == ""){
                    Intent intent = new Intent(ProductCommentPage_Activity.this, Login_Activity.class);
                    intent.putExtra("logined", false);
                    startActivity(intent);
                }
                if (content != null && content != "") {

                    for(int i = 0 ; i < lstComment.size() ; i++){
                        if(lastId < lstComment.get(i).getId()){
                            lastId = lstComment.get(i).getId();
                        }
                    }

                    presentationComment.setComment(productId, content, lastId);
                    lstComment = presentationComment.getListComment(Integer.parseInt(productId));

                    AdapterProductComment adapterProductComment = new AdapterProductComment(ProductCommentPage_Activity.this, lstComment);
                    recyclerViewComment.setAdapter(adapterProductComment);
                    adapterProductComment.notifyDataSetChanged();

                    contentComment.setText("");
                    InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    mgr.hideSoftInputFromWindow(contentComment.getWindowToken(), 0);
                }
            }
        });
    }

    private void addControls() {
        myIntent = getIntent();
        productId = myIntent.getStringExtra("productId");

        btnBack = (ImageButton) findViewById(R.id.product_comment_btn_back);
        contentComment = (EditText) findViewById(R.id.txtContentComment);
        btnComment = (ImageButton) findViewById(R.id.btnComment);

        presentationComment = new PresentationComment();
        lstComment = presentationComment.getListComment(Integer.parseInt(productId));


        recyclerViewComment = (RecyclerView) findViewById(R.id.recycle_product_comment);
        recyclerViewComment.setLayoutManager(new LinearLayoutManager(this));
        AdapterProductComment adapterProductComment = new AdapterProductComment(this, lstComment);
        recyclerViewComment.setAdapter(adapterProductComment);
        adapterProductComment.notifyDataSetChanged();


    }
}
