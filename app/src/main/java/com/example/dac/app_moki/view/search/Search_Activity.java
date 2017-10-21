package com.example.dac.app_moki.view.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.dac.app_moki.R;

/**
 * Created by Dac on 10/20/2017.
 */
public class Search_Activity extends AppCompatActivity {
    Button btnSearch;
    ImageButton btnBack;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        addControls();
        addEvents();
    }

    private void addControls() {
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
    }

    private void addEvents() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search_Activity.this, ResultSearch_Activity.class);
                startActivity(intent);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
