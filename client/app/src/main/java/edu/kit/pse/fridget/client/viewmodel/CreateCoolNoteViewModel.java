package edu.kit.pse.fridget.client.viewmodel;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.view.View;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.activity.FullTextCoolNoteActivity;
import edu.kit.pse.fridget.client.activity.HomeActivity;
import edu.kit.pse.fridget.client.datamodel.CoolNote;

public class CreateCoolNoteViewModel extends ViewModel {

    int i = 0;
    private String tempId = "a6373664-7b90-11e8-adc0-fa7ae01bbeb";
    private String tempFlatshareId = "a6373664-7b90-11e8-adc0-fa7ae01bbebc";
    private String tempCreatorUserId = "3879a0a8-546d-41cb-b26e-eb1324d0e72c";
    private String tempCreatedAt = "01.01.2018";
    private String title = "";
    private String content = "";
    private int tempPosition = 4;

    //No data sent to server yet
    public CoolNote createCoolNote(String title, String content){
        CoolNote coolNote = new CoolNote(tempId, title, content ,tempFlatshareId, tempCreatorUserId, tempCreatedAt, tempPosition);
        return coolNote;
    }


    public void postCoolNote(View v) {
        Context context = v.getContext();
        Intent i = new Intent(context, FullTextCoolNoteActivity.class);

        //push notification
        NotificationManager notificationManager;
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.fridget_logo)
                .setContentTitle("Fridget")
                .setContentText("Look at this new Cool Note!")
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());

        context.startActivity(i);
    }

    public void goBack(View v) {
        Context context = v.getContext();
        Intent i = new Intent(context, HomeActivity.class);
        context.startActivity(i);
    }

}
