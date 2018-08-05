package edu.kit.pse.fridget.client.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.activity.FullTextCoolNoteActivity;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();
    private NotificationManager notificationManager;

    public MyFirebaseMessagingService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage == null) {
            return;
        }

        String coolNoteId = "0";
        Map<String, String> payload = remoteMessage.getData();

        if (payload.size() > 0) {
            Log.d(TAG, "Message data payload: " + payload);
            coolNoteId = payload.get("coolNoteId");
        }

        if (remoteMessage.getNotification() != null) {
            sendNotification(remoteMessage.getNotification(), coolNoteId);
        }
    }

    private void sendNotification(RemoteMessage.Notification notification, String coolNoteId) {
        Intent intent = new Intent(this, FullTextCoolNoteActivity.class);
        intent.putExtra("coolNoteId", coolNoteId);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.fridget_logo)
                .setContentTitle(notification.getTitle())
                .setContentText(notification.getBody())
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }
}
