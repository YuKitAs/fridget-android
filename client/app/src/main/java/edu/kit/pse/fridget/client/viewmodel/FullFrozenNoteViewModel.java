package edu.kit.pse.fridget.client.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.text.Spanned;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import edu.kit.pse.fridget.client.activity.EditTextFrozenNoteActivity;
import edu.kit.pse.fridget.client.activity.HomeActivity;
import edu.kit.pse.fridget.client.datamodel.FrozenNote;
import edu.kit.pse.fridget.client.service.FrozenNoteService;
import edu.kit.pse.fridget.client.service.RetrofitClientInstance;
import edu.kit.pse.fridget.client.utility.StyledContentUtilities;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FullFrozenNoteViewModel extends ViewModel {
    private static final String TAG = FullFrozenNoteViewModel.class.getSimpleName();

    public MutableLiveData<String> liveDataTitle = new MutableLiveData<String>();
    public MutableLiveData<Spanned> liveDataStyledContent = new MutableLiveData<Spanned>();

    private String frozenNoteId;
    private FrozenNote frozenNote;

    public FullFrozenNoteViewModel() {
        liveDataTitle.setValue("");
        liveDataStyledContent.setValue(StyledContentUtilities.getDefaultSpanned());
    }

    public void setFrozenNoteId(String id) {
        this.frozenNoteId = id;
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
                    liveDataStyledContent.setValue(StyledContentUtilities.convertToSpanned(frozenNote.getContent()));
                }
            }

            @Override
            public void onFailure(Call<FrozenNote> call, Throwable t) {
                Log.e(TAG, "Fetching Frozen Note has failed.");
            }
        });
    }

    public void editFrozenNote(View v) {
        final Context context = v.getContext();
        Intent intent = new Intent(context, EditTextFrozenNoteActivity.class);
        intent.putExtra("frozenNoteId", frozenNoteId);
        context.startActivity(intent);

        RetrofitClientInstance.getRetrofitInstance().create(FrozenNoteService.class).updateFrozenNote(frozenNoteId, frozenNote).enqueue(new Callback<FrozenNote>() {
            @Override
            public void onResponse(Call<FrozenNote> call, Response<FrozenNote> response) {
                frozenNote = response.body();
                if (frozenNote != null) {
                    Log.i(TAG, String.format("Frozen Note %s edited.", new Gson().toJson(frozenNote)));

                    intent.putExtra("position", frozenNote.getPosition());
                    context.startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<FrozenNote> call, Throwable t) {
                Log.e(TAG, "Editing Frozen Note %s failed.");
            }
        });
    }

    public void goBack(View v) {
        Context context = v.getContext();
        Intent i = new Intent(context, HomeActivity.class);
        context.startActivity(i);
    }
}
