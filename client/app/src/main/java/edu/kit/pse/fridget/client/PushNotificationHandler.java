package edu.kit.pse.fridget.client;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import edu.kit.pse.fridget.client.activity.FullTextCoolNoteActivity;

public class PushNotificationHandler extends FirebaseMessagingService {
    private static final String TAG = PushNotificationHandler.class.getSimpleName();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.i(TAG, "Received remote message");
        if (remoteMessage == null) {
            return;
        }

        String coolNoteId = "0";
        Map<String, String> payload = remoteMessage.getData();

        if (payload.size() > 0) {
            Log.i(TAG, "Message data payload: " + payload);
            coolNoteId = payload.get("coolNoteId");
        }

        if (remoteMessage.getNotification() != null) {
            sendNotification(remoteMessage.getNotification(), coolNoteId);
        }
    }

    public Intent getPushNotificationIntent(String coolNoteId) {
        Intent intent = new Intent(this, FullTextCoolNoteActivity.class);
        intent.putExtra("coolNoteId", coolNoteId);
        return intent;
    }

    private void sendNotification(RemoteMessage.Notification notification, String coolNoteId) {
        Intent intent = getPushNotificationIntent(coolNoteId);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        String channelId = getString(R.string.default_notification_channel_id);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.fridget_logo)
                .setContentTitle(notification.getTitle())
                .setContentText(notification.getBody())
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0, notificationBuilder.build());
    }
}
