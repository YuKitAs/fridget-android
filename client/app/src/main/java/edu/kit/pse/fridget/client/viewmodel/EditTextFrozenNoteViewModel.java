package edu.kit.pse.fridget.client.viewmodel;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import edu.kit.pse.fridget.client.activity.FullTextFrozenNoteActivity;
import edu.kit.pse.fridget.client.activity.HomeActivity;
import edu.kit.pse.fridget.client.datamodel.FrozenNote;
import edu.kit.pse.fridget.client.service.FrozenNoteService;
import edu.kit.pse.fridget.client.service.RetrofitClientInstance;
import edu.kit.pse.fridget.client.viewmodel.common.StyledContentViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditTextFrozenNoteViewModel extends ViewModel {
    private static final String TAG = FullFrozenNoteViewModel.class.getSimpleName();

    public MutableLiveData<String> liveDataTitle = new MutableLiveData<String>();
    public final StyledContentViewModel styledContent = new StyledContentViewModel("");

    private String flatshareId;
    private String frozenNoteId;
    private FrozenNote frozenNote;
    private int position;

    public void setFlatshareId(String flatshareId) {
        this.flatshareId = flatshareId;
    }

    public void setFrozenNoteId(String frozenNoteId) {
        this.frozenNoteId = frozenNoteId;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void fetchData() {
        fetchFrozenNote();
    }

    private void fetchFrozenNote() {
        RetrofitClientInstance.getRetrofitInstance().create(FrozenNoteService.class).getFrozenNote(frozenNoteId).enqueue(new Callback<FrozenNote>() {
            @Override
            public void onResponse(Call<FrozenNote> call, Response<FrozenNote> response) {
                frozenNote = response.body();

                if (frozenNote != null) {
                    Log.i(TAG, String.format("Frozen Note %s has been fetched.", new Gson().toJson(frozenNote)));

                    liveDataTitle.setValue(frozenNote.getTitle());
                    styledContent.setHtmlContent(frozenNote.getContent());
                }
            }

            @Override
            public void onFailure(Call<FrozenNote> call, Throwable t) {
                Log.e(TAG, "Fetching Frozen Note has failed.");
            }
        });
    }

    public void updateFrozenNote(View v) {
        final Context context = v.getContext();

        frozenNote = new FrozenNote(frozenNoteId, liveDataTitle.getValue(), styledContent.getHtmlContent(), flatshareId, position);

        RetrofitClientInstance.getRetrofitInstance().create(FrozenNoteService.class).updateFrozenNote(frozenNoteId, frozenNote).enqueue(new Callback<FrozenNote>() {
            @Override
            public void onResponse(Call<FrozenNote> call, Response<FrozenNote> response) {
                frozenNote = response.body();
                if (frozenNote != null) {
                    Log.i(TAG, String.format("Frozen Note %s edited.", new Gson().toJson(frozenNote)));
                }
            }

            @Override
            public void onFailure(Call<FrozenNote> call, Throwable t) {
                Log.e(TAG, "Editing Frozen Note %s failed.");
            }
        });

        // Start another activity after started sending data to server
        Intent intent = new Intent(context, FullTextFrozenNoteActivity.class);
        intent.putExtra("frozenNoteId", frozenNoteId);
        context.startActivity(intent);
    }

    public void goBack(View v) {
        Context context = v.getContext();
        Intent i = new Intent(context, HomeActivity.class);
        context.startActivity(i);
    }
}
