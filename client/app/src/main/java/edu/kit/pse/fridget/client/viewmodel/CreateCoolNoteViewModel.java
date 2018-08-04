package edu.kit.pse.fridget.client.viewmodel;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

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
    private final MutableLiveData<String> title = new MutableLiveData<>();
    private final MutableLiveData<String> content = new MutableLiveData<>();

    public MutableLiveData<String> getTitle() {
        return title;
    }

    public MutableLiveData<String> getContent() {
        return content;
    }

    public void bold(View v) {
        String content = this.content.getValue();
        if(content.isEmpty()){
            Toast.makeText(v.getContext(), "There is no text in the content box!", Toast.LENGTH_LONG);
        }
        else {
            SpannableStringBuilder str = new SpannableStringBuilder(content);
            str.setSpan(new StyleSpan(Typeface.BOLD), 0, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            this.content.setValue(str.toString());
        }
    }

    public void italic(View v) {
        String content = this.content.getValue();
        SpannableStringBuilder str = new SpannableStringBuilder(content);
        str.setSpan(new StyleSpan(Typeface.ITALIC), 0, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        this.content.setValue(str.toString());
    }

    public void underline(View v) {
        String content = this.content.getValue();
        SpannableStringBuilder str = new SpannableStringBuilder(content);
        str.setSpan(new UnderlineSpan(), 0, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        this.content.setValue(str.toString());
    }

    //Erstellen der Cool Note, Viewwechsel zur FullCoolNoteActivity
    public void postCoolNote(View v) {

        final Context context = v.getContext();
        Intent intent = new Intent(context, FullTextCoolNoteActivity.class);

        if (title.getValue() == null) {
            Toast.makeText(context, "Title cannot be empty!", Toast.LENGTH_LONG);
        }
        else {
            CoolNote coolNote = new CoolNote(null, title.getValue(), content.getValue(), "025b108a-0db7-4f92-b52b-a41f413e4b12", 0, 0, null, new ArrayList<>());

            RetrofitClientInstance.getRetrofitInstance().create(CoolNoteService.class).createCoolNote(coolNote).enqueue(new Callback<CoolNote>() {
                @Override
                public void onResponse(Call<CoolNote> call, Response<CoolNote> response) {
                    CoolNote body = response.body();
                    if (body != null) {
                        Log.i("Created Cool Note", String.format("Cool Note %s created.", new Gson().toJson(body)));

                        //push notification
                        NotificationManager notificationManager;
                        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
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

                        intent.putExtra("coolNoteId", body.getId());
                        context.startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<CoolNote> call, Throwable t) {
                    Log.e("Creating Cool Note", "Creating Cool Note %s failed.");
                    t.printStackTrace();
                }
            });
        }
    }

    //Viewwechsel zur HomeActivity
    public void goBack(View v) {
        Context context = v.getContext();
        Intent i = new Intent(context, HomeActivity.class);
        context.startActivity(i);
    }

}
