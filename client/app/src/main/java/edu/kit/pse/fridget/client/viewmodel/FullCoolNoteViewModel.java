package edu.kit.pse.fridget.client.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.util.List;

import edu.kit.pse.fridget.client.activity.HomeActivity;
import edu.kit.pse.fridget.client.datamodel.CoolNote;
import edu.kit.pse.fridget.client.service.CoolNoteService;
import edu.kit.pse.fridget.client.service.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FullCoolNoteViewModel extends ViewModel {

    public String id;
    public String title;
    public String content;
    public String creatorMembershipId;
    public int position;
    public int importance;
    public String createdAt;
    public List<String> taggedMembershipIds;
    public CoolNote coolNote;


    private String tempMagnetColor = "#00FF00";

    public int getMagnetColor() {
        return Color.parseColor(tempMagnetColor);
    }

    public void setCoolNote(CoolNote coolNote) {
        this.coolNote = coolNote;
    }

    public void getCoolNote(String coolNoteId) {
        RetrofitClientInstance.getRetrofitInstance().create(CoolNoteService.class).getCoolNote(coolNoteId).enqueue(new Callback<CoolNote>() {
            @Override
            public void onResponse(Call<CoolNote> call, Response<CoolNote> response) {
                CoolNote body = response.body();
                if (body != null) {
                    Log.i("Fetched Cool Note", String.format("Cool Note %s fetched.", new Gson().toJson(body)));
                }

                title = body.getTitle();
                content = body.getContent();
                createdAt = body.getCreatedAt();
                id = body.getId();

            }

            @Override
            public void onFailure(Call<CoolNote> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void deleteCoolNote(String userId, String flatshareId, String coolNoteId) {
        coolNoteId = this.id;
        RetrofitClientInstance.getRetrofitInstance().create(CoolNoteService.class).deleteCoolNote(coolNoteId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("Deleted Cool Note", String.format("Cool Note %s deleted.", new Gson().toJson(null)));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void goBack(View v) {

        Context context = v.getContext();
        Intent i = new Intent(context, HomeActivity.class);
        context.startActivity(i);

    }

}
