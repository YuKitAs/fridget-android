package edu.kit.pse.fridget.client.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.activity.FullTextCoolNoteActivity;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private NotificationManager notificationManager;

    public MyFirebaseMessagingService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage)
    {
        if(remoteMessage == null)
        {
            return;
        }
        if(remoteMessage.getNotification() != null)
        {
            handleNotification(remoteMessage.getNotification().getTitle());
        }
    }
    public void handleNotification(String message)
    {
        sendNotification(message);
    }

    public void sendNotification(String message)
    {
        Intent intent = new Intent(this, FullTextCoolNoteActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        message = "Look at this new Cool Note!";
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.fridget_logo)
                .setContentTitle("Fridget")
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }
}
