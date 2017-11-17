package com.example.dac.app_moki.view.product;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.dac.app_moki.R;

/**
 * Created by Dac on 11/11/2017.
 */

public class PostProduct extends AppCompatActivity {

    private ImageButton btnBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_post);
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
        btnBack = (ImageButton) findViewById(R.id.post_product_back);
    }
}
