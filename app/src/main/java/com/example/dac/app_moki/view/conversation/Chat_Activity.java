package com.example.dac.app_moki.view.conversation;

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
import com.example.dac.app_moki.model.nesworks.ConnectSocket;
import com.example.dac.app_moki.model.object.Message;
import com.example.dac.app_moki.model.object.User;
import com.example.dac.app_moki.presentation.conversation.PresentationConversation;
import com.example.dac.app_moki.view.adapter.AdapterMessage;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Dac on 11/17/2017.
 */

public class Chat_Activity extends AppCompatActivity {
    private ImageButton btnBack;
    private EditText txtContentChat;
    private ImageButton btnChat;
    private RecyclerView recyclerViewChat;
    private List<Message> lstMessage;
    private AdapterMessage adapterMessage;
    private String roomId;

    private static final String SERVERPORT = "1337";
    private static final String SERVER_IP = "192.168.10.100";
    private Socket mSocket;


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
                PresentationConversation presentationConversation = new PresentationConversation();
                presentationConversation.sendMessage(roomId, txtContentChat.getText() + "");


                txtContentChat.setText("");
                InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.hideSoftInputFromWindow(txtContentChat.getWindowToken(), 0);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mSocket.on(mSocket.EVENT_MESSAGE, new Emitter.Listener() {

            @Override
            public void call(final Object... args) {
                Chat_Activity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject data = (JSONObject) args[0];
                        // add the message to view
                        try {
                            switch (data.getString("type")){
                                case "chat_message": {
                                    JSONObject objectMessage = data.getJSONObject("message");
                                    Message message = new Message();
                                    message.setMessage(String.valueOf(objectMessage.getString("message")));

                                    JSONObject sender = data.getJSONObject("user");
                                    User user = new User();
                                    user.setId(Integer.parseInt(sender.getString("id")));
                                    user.setUserName(String.valueOf(sender.getString("username")));
                                    user.setImage(String.valueOf(sender.getString("avartar")));
                                    message.setUser(user);

                                    lstMessage.add(message);
                                    adapterMessage.notifyDataSetChanged();
                                }; break;
                                case "join" : {

                                };break;
                                case "add":{

                                };break;

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });

    }

    private void addControls() {
        Intent intent = getIntent();

        roomId = intent.getStringExtra("roomId");
        //System.out.print("xxxxxxxxxxxxxxxxxxxx: " + intent.getStringExtra("roomId1"));
        mSocket = ConnectSocket.getmSocket();

        txtContentChat = (EditText) findViewById(R.id.txtcontentChat);
        btnChat = (ImageButton) findViewById(R.id.btnChat);
        btnBack = (ImageButton) findViewById(R.id.btn_back_conversation);

        PresentationConversation presentationConversation = new PresentationConversation();
        lstMessage = presentationConversation.getConversationDetail(roomId);

        recyclerViewChat = (RecyclerView) findViewById(R.id.recycle_chat);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Chat_Activity.this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerViewChat.setLayoutManager(linearLayoutManager);
        adapterMessage = new AdapterMessage(Chat_Activity.this, lstMessage);
        recyclerViewChat.setAdapter(adapterMessage);

        adapterMessage.notifyDataSetChanged();

    }
}
