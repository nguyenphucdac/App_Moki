package com.example.dac.app_moki.model.nesworks;

import android.support.v7.app.AppCompatActivity;

import com.example.dac.app_moki.local.value.ValueLocal;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

/**
 * Created by Dac on 11/20/2017.
 */

public class ConnectSocket extends AppCompatActivity {
    public static Socket mSocket;

    public static void setmSocket(){
        try {
            IO.Options options = new IO.Options();
            options.forceNew = true;
            options.query = "token="+ ValueLocal.getToken();
            mSocket = IO.socket("http://192.168.10.100:1337",options);
            mSocket.connect();

        } catch (URISyntaxException e) {
        }
    }
    public static Socket getmSocket(){
        return mSocket;
    }
}
