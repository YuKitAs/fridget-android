package edu.kit.pse.fridget.client.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;

import edu.kit.pse.fridget.client.activity.FullTextCoolNoteActivity;
import edu.kit.pse.fridget.client.activity.HomeActivity;
import edu.kit.pse.fridget.client.datamodel.CoolNote;
import edu.kit.pse.fridget.client.service.CoolNoteService;
import edu.kit.pse.fridget.client.service.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCoolNoteViewModel extends ViewModel {
    private final MutableLiveData<String> title = new MutableLiveData<>();
    private final MutableLiveData<String> content = new MutableLiveData<>();

    public MutableLiveData<String> getTitle() {
        return title;
    }

    public MutableLiveData<String> getContent() {
        return content;
    }

    //Erstellen der Cool Note, Viewwechsel zur FullCoolNoteActivity
    public void postCoolNote(View v) {
        CoolNote coolNote = new CoolNote(null, title.getValue(), content.getValue(), "5cd8d207-39d7-4de1-aa84-64e59804ab70", 0, 0, null, new ArrayList<>());

        final Context context = v.getContext();
        Intent intent = new Intent(context, FullTextCoolNoteActivity.class);

        RetrofitClientInstance.getRetrofitInstance().create(CoolNoteService.class).createCoolNote(coolNote).enqueue(new Callback<CoolNote>() {
            @Override
            public void onResponse(Call<CoolNote> call, Response<CoolNote> response) {
                CoolNote body = response.body();
                if (body != null) {
                    Log.i("Created Cool Note", String.format("Cool Note %s created.", new Gson().toJson(body)));
                }

                context.startActivity(intent);
            }

            @Override
            public void onFailure(Call<CoolNote> call, Throwable t) {
                t.printStackTrace();
            }
        });


        //push notification
/*        NotificationManager notificationManager;
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
        notificationManager.notify(0, notificationBuilder.build());*/
    }

    //Viewwechsel zur HomeActivity
    public void goBack(View v) {
        Context context = v.getContext();
        Intent i = new Intent(context, HomeActivity.class);
        context.startActivity(i);
    }

}
