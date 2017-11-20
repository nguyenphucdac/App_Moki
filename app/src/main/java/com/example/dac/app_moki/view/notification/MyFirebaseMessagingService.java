package com.example.dac.app_moki.view.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.view.home.Home_Activity;
import com.example.dac.app_moki.view.product.ProductDetail_Activity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dac on 11/17/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FCM Service";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO: Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());

        JSONObject dataObject = new JSONObject(remoteMessage.getData());
        String title = remoteMessage.getNotification().getTitle();

        Intent resultIntent = new Intent(this, MyFirebaseMessagingService.class);;
        switch (title){
            case "New Product" : {


                resultIntent = new Intent(this, ProductDetail_Activity.class);
                try {
                    resultIntent.putExtra("productId", dataObject.getString("product_id"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }; break;
            case "New Message" : {
                resultIntent = new Intent(this, Home_Activity.class);

            }; break;
        }


        PendingIntent pi = PendingIntent.getActivity(this, 0, resultIntent, 0);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext())
                .setContentIntent(pi)
                .setSound(alarmSound)
                .setAutoCancel(true)
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setSmallIcon(R.drawable.app_icon) //add a 24x24 image to the drawable folder
                // and call it here
                .setContentText(remoteMessage.getNotification().getBody()
                );



        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        nm.notify(0, builder.build());

    }
}
