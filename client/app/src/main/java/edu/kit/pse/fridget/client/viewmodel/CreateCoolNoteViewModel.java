package edu.kit.pse.fridget.client.viewmodel;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.activity.FullTextCoolNoteActivity;
import edu.kit.pse.fridget.client.activity.HomeActivity;
import edu.kit.pse.fridget.client.datamodel.CoolNote;
import edu.kit.pse.fridget.client.service.CoolNoteService;
import edu.kit.pse.fridget.client.service.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCoolNoteViewModel extends ViewModel {

    int i = 0;

    public String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd ");
        String strDate = mdformat.format(calendar.getTime());
        return strDate;
    }

    //No data sent to server yet
    public void createCoolNote(CoolNote coolNote){
        CoolNoteService coolNoteService = RetrofitClientInstance.getRetrofitInstance().create(CoolNoteService.class);
        Call<CoolNote> coolNoteCall = coolNoteService.createCoolNote(coolNote);
        coolNoteCall.enqueue(new Callback<CoolNote>() {
            @Override
            public void onResponse(Call<CoolNote> call, Response<CoolNote> response) {
                CoolNote body = response.body();
                if (body != null) {
                    Log.i("Created Cool Note", String.format("Post %s created.", new Gson().toJson(body)));
                }
            }

            @Override
            public void onFailure(Call<CoolNote> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    //Viewwechsel zur FullCoolNoteActivity
    public void postCoolNote(View v) {
        final Context context = v.getContext();
        Intent i = new Intent(context, FullTextCoolNoteActivity.class);

        //push notification
        NotificationManager notificationManager;
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.fridget_logo)
                .setContentTitle("Hey, there!")
                .setContentText("Look at this new Cool Note!")
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());

        context.startActivity(i);
    }

    //Viewwechsel zur HomeActivity
    public void goBack(View v) {
        Context context = v.getContext();
        Intent i = new Intent(context, HomeActivity.class);
        context.startActivity(i);
    }

}
