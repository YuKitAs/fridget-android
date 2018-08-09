package edu.kit.pse.fridget.client.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import edu.kit.pse.fridget.client.activity.HomeActivity;
import edu.kit.pse.fridget.client.datamodel.FrozenNote;
import edu.kit.pse.fridget.client.service.FrozenNoteService;
import edu.kit.pse.fridget.client.service.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FullFrozenNoteViewModel extends ViewModel {

    private String title;
    private String content;
    private String id;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void getFrozenNote(String frozenNoteId){
        RetrofitClientInstance.getRetrofitInstance().create(FrozenNoteService.class).getFrozenNote(frozenNoteId).enqueue(new Callback<FrozenNote>() {
            @Override
            public void onResponse(Call<FrozenNote> call, Response<FrozenNote> response) {
                FrozenNote body = response.body();
                Log.i("Fetching Frozen Note", String.format("Frozen Note has been fetched.", new Gson().toJson(body)));

                title = body.getTitle();
                content = body.getContent();
                id = body.getId();
            }

            @Override
            public void onFailure(Call<FrozenNote> call, Throwable t) {
                Log.e("Fetching Frozen Note", "Fetching Frozen Note has failed.");
                t.printStackTrace();
            }
        });
    }

    public void goBack(View v) {

        Context context = v.getContext();
        Intent i = new Intent(context, HomeActivity.class);
        context.startActivity(i);

    }

}
