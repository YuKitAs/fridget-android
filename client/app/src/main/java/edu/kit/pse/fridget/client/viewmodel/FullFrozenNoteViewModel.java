package edu.kit.pse.fridget.client.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import edu.kit.pse.fridget.client.activity.EditTextFrozenNoteActivity;
import edu.kit.pse.fridget.client.activity.HomeActivity;
import edu.kit.pse.fridget.client.activity.MenuDrawerActivity;
import edu.kit.pse.fridget.client.datamodel.FrozenNote;
import edu.kit.pse.fridget.client.service.FrozenNoteService;
import edu.kit.pse.fridget.client.service.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FullFrozenNoteViewModel extends ViewModel {

    private FrozenNote frozenNote;
    private String frozenNoteId;
    private Spanned spanned;

    public Spanned getSpanned() {
        return spanned;
    }

    public void setFrozenNoteId(String id) {
        this.frozenNoteId = id;
    }

    public FrozenNote getFrozenNote() {
        return frozenNote;
    }

    private SharedPreferencesData sharedPreferencesData =new SharedPreferencesData();

    public void getFN(){
        RetrofitClientInstance.getRetrofitInstance().create(FrozenNoteService.class).getFrozenNote(frozenNoteId).enqueue(new Callback<FrozenNote>() {
            @Override
            public void onResponse(Call<FrozenNote> call, Response<FrozenNote> response) {
                frozenNote = response.body();
                if (frozenNote != null) {
                    Log.i("Fetching Frozen Note", String.format("Frozen Note has been fetched.", new Gson().toJson(frozenNote)));
                }
                spanned = Html.fromHtml(frozenNote.getContent());
            }

            @Override
            public void onFailure(Call<FrozenNote> call, Throwable t) {
                Log.e("Fetching Frozen Note", "Fetching Frozen Note has failed.");
                t.printStackTrace();
            }
        });
    }

    public void editFrozenNote(View v) {

        String flatshareId = sharedPreferencesData.getSharedPreferencesData("flatshareId",v);

        final Context context = v.getContext();
        Intent intent = new Intent(context, EditTextFrozenNoteActivity.class);
        intent.putExtra("frozenNoteId", frozenNoteId);
        context.startActivity(intent);

    }

    public void goBack(View v) {

        Context context = v.getContext();
        Intent i = new Intent(context, HomeActivity.class);
        context.startActivity(i);

    }

    public void openMenu(View v) {
        Context context = v.getContext();
        Intent i = new Intent(context, MenuDrawerActivity.class);
        context.startActivity(i);
    }

}
