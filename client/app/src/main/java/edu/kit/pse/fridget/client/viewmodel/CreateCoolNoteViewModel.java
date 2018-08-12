package edu.kit.pse.fridget.client.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Collections;

import edu.kit.pse.fridget.client.activity.FullTextCoolNoteActivity;
import edu.kit.pse.fridget.client.activity.HomeActivity;
import edu.kit.pse.fridget.client.datamodel.CoolNote;
import edu.kit.pse.fridget.client.service.CoolNoteService;
import edu.kit.pse.fridget.client.service.RetrofitClientInstance;
import edu.kit.pse.fridget.client.viewmodel.common.StyledContentViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCoolNoteViewModel extends ViewModel {
    private static final String TAG = FullFrozenNoteViewModel.class.getSimpleName();

    public MutableLiveData<String> liveDataTitle = new MutableLiveData<String>();
    public final StyledContentViewModel styledContent = new StyledContentViewModel("");

    private String ownMembershipId;
    private int position;

    public void setOwnMembershipId(String ownMembershipId) {
        this.ownMembershipId = ownMembershipId;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void postCoolNote(View v) {
        final Context context = v.getContext();

        // TODO: move validation to another method to keep this method only do one thing
        if (liveDataTitle.getValue() == null) {
            Toast.makeText(context, "Title cannot be empty!", Toast.LENGTH_LONG).show();
            return;
        }

        CoolNote coolNote = new CoolNote(null, liveDataTitle.getValue(), styledContent.getHtmlContent(), ownMembershipId, position, 0, null, Collections.emptyList());

        RetrofitClientInstance.getRetrofitInstance().create(CoolNoteService.class).createCoolNote(coolNote).enqueue(new Callback<CoolNote>() {
            @Override
            public void onResponse(Call<CoolNote> call, Response<CoolNote> response) {
                CoolNote createdCoolNote = response.body();

                if (createdCoolNote != null) {
                    Log.i(TAG, String.format("Cool Note %s created.", new Gson().toJson(response.body())));

                    // Here we have to wait for the new cool note ID
                    Intent intent = new Intent(context, FullTextCoolNoteActivity.class);
                    intent.putExtra("coolNoteId", createdCoolNote.getId());
                    context.startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<CoolNote> call, Throwable t) {
                Log.e(TAG, "Creating Cool Note failed.");
            }
        });
    }

    //Viewwechsel zur HomeActivity
    public void goBack(View v) {
        Context context = v.getContext();
        Intent i = new Intent(context, HomeActivity.class);
        context.startActivity(i);
    }
}
