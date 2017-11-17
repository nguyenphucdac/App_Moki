package com.example.dac.app_moki.view.conversation;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.dac.app_moki.R;

/**
 * Created by Dac on 11/17/2017.
 */

public class Chat_Activity extends AppCompatActivity {
    private EditText txtContentChat;
    private ImageButton btnChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                txtContentChat.setText("");
                InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.hideSoftInputFromWindow(txtContentChat.getWindowToken(), 0);
            }
        });
    }

    private void addControls() {
        txtContentChat = (EditText) findViewById(R.id.txtcontentChat);
        btnChat = (ImageButton) findViewById(R.id.btnChat);
    }
}
