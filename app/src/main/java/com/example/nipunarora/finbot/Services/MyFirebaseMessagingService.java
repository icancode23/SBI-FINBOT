package com.example.nipunarora.finbot.Services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.nipunarora.finbot.Activities.Bot_Activity;
import com.example.nipunarora.finbot.R;
import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import static com.google.android.gms.internal.zzt.TAG;

/**
 * Created by nipunarora on 16/04/17.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //************ CHECK TO SEE IF THERE IS A DATA MESSAGE *******************
        if(remoteMessage.getData().size() >0)

        {
            String messagebody,imageurl;
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            try
            {
                JSONObject data=new JSONObject(remoteMessage.getData());
                messagebody=data.getString("Message");
                imageurl=data.getString("image");
            }
            catch (Exception e)
            {
                Log.d("Json Exception",e.toString());
                messagebody=null;
                imageurl=null;
            }
            sendBigNotification(messagebody,imageurl);



        }

        //***************** CHECK TO SEE IF THERE IS A NOTIFICATION *********************//

        if(remoteMessage.getNotification()!=null)

        {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            sendBigNotification(remoteMessage.getNotification().getBody(),"https://i.imgur.com/59JsFSI.png");
        }
    }

    //************************** CREATING SMALL NOTIFICATIONS *************//

    private void sendSmallNotification(String messageBody) {
        Intent intent = new Intent(this, Bot_Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);
        Bitmap image;
        try {
            URL url = new URL("http://api.androidhive.info/images/minion.jpg");
             image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch(IOException e) {
            image=null;
            Log.d("Bitmap error",e.toString());
        }

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.man)
                .setLargeIcon(image)
                .setContentTitle("FinBot")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setPriority(10)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }


    //**************** CREATING THE BIG NOTIFICATION *********************??

    private void sendBigNotification(String messageBody,String imgurl) {
        Intent intent = new Intent(this, Bot_Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);
        Bitmap image;
        try {
            URL url = new URL(imgurl);
            image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch(IOException e) {
            image=null;
            Log.d("Bitmap error",e.toString());
        }

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Bitmap Icon = BitmapFactory.decodeResource(getResources(), R.drawable.businessmanicon);
        Notification notification = new Notification.Builder(getApplicationContext())
                .setContentTitle("FinBot")
                .setContentText(messageBody)
                .setSmallIcon(R.drawable.man)
                .setLargeIcon(Icon)
                .setPriority(Notification.PRIORITY_MAX)
                .setStyle(new Notification.BigPictureStyle()
                        .bigPicture(image)
                        .setBigContentTitle("Finbot")
                .setSummaryText(messageBody))
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
                .build();
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notification);
    }


}
