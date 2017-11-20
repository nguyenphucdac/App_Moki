package com.example.dac.app_moki.view.conversation;

import android.support.v7.app.AppCompatActivity;

import com.example.dac.app_moki.local.value.ValueLocal;
import com.example.dac.app_moki.model.nesworks.Host;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

/**
 * Created by Dac on 11/20/2017.
 */

public class WebsocketClient extends AppCompatActivity {
    private static final String SERVERPORT = "1337";
    private static final String SERVER_IP = "192.168.10.100";
    private static Socket mSocket;

    {
        try {
            IO.Options options = new IO.Options();
            options.forceNew = true;
            options.query = "token="+ ValueLocal.getToken();
            mSocket = IO.socket("http://"+ Host.getHost()+":1337",options);

        } catch (URISyntaxException e) {
        }
    }
    public WebsocketClient(){
        mSocket.connect();

        mSocket.on(mSocket.EVENT_MESSAGE, new Emitter.Listener() {

            @Override
            public void call(final Object... args) {
                WebsocketClient.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject data = (JSONObject) args[0];

                        try {
                            System.out.println("data from socket : " + data.getString("type"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // add the message to view

                    }
                });
            }
        });
        mSocket.connect();
    }
}
