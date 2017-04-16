package com.example.nipunarora.finbot.Services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());



        }

        //***************** CHECK TO SEE IF THERE IS A NOTIFICATION *********************//

        if(remoteMessage.getNotification()!=null)

        {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            sendNotification(remoteMessage.getNotification().getBody());
        }
    }

    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, Bot_Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.man)
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

}
