package com.example.dac.app_moki.view.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.dac.app_moki.R;

/**
 * Created by Dac on 10/31/2017.
 */
public class UserInfo_Activity extends AppCompatActivity {

    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);

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
        btnBack = (ImageButton) findViewById(R.id.user_info_btn_back);
    }
}
