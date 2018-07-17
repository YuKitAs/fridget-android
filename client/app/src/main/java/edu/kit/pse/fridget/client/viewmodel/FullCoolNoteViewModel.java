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
import edu.kit.pse.fridget.client.datamodel.Member;
import edu.kit.pse.fridget.client.datamodel.ReadConfirmation;
import edu.kit.pse.fridget.client.service.CoolNoteService;
import edu.kit.pse.fridget.client.service.MembershipService;
import edu.kit.pse.fridget.client.service.ReadConfirmationService;
import edu.kit.pse.fridget.client.service.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FullCoolNoteViewModel extends ViewModel {

    private String id;
    private String title;
    private String content;
    private String createdAt;
    private CoolNote coolNote;
    private int magnetColor;

    public CoolNote getCoolNote() {
        return coolNote;
    }

    public int getMagnetColor() {
        return magnetColor;
    }

    public void getMember(String flatshareId, String userId) {
        RetrofitClientInstance.getRetrofitInstance().create(MembershipService.class).getMember(flatshareId, userId).enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                Member body = response.body();
                Log.i("Fetching Member", String.format("Member %s fetched.", new Gson().toJson(body)));
                magnetColor = Color.parseColor(body.getMagnetColor());
            }

            @Override
            public void onFailure(Call<Member> call, Throwable t) {
                Log.e("Fetching Member", "Fetching Member %s failed.");
            }
        });
    }

    public void getReadstatus(String coolNoteId) {
        RetrofitClientInstance.getRetrofitInstance().create(ReadConfirmationService.class).getReadStatus(coolNoteId).enqueue(new Callback<List<ReadConfirmation>>() {
            @Override
            public void onResponse(Call<List<ReadConfirmation>> call, Response<List<ReadConfirmation>> response) {
                List<ReadConfirmation> body = response.body();
                Log.i("getReadConfirmations", String.format("Read confirmations %s fetched.", new Gson().toJson(body)));

                for (int i = 0; i <= body.size(); i++) {
                    ReadConfirmation readConfirmation = body.get(i);
                    String membershipId = readConfirmation.getMembershipId();
                }

            }

            @Override
            public void onFailure(Call<List<ReadConfirmation>> call, Throwable t) {
                Log.e("getReadConfirmations", "Fetching read confirmations %s failed.");
            }
        });
    }

    public void getCoolNote(String coolNoteId) {
        RetrofitClientInstance.getRetrofitInstance().create(CoolNoteService.class).getCoolNote(coolNoteId).enqueue(new Callback<CoolNote>() {
            @Override
            public void onResponse(Call<CoolNote> call, Response<CoolNote> response) {
                CoolNote body = response.body();
                Log.i("Fetching Cool Note", String.format("Cool Note %s fetched.", new Gson().toJson(body)));

                title = body.getTitle();
                content = body.getContent();
                createdAt = body.getCreatedAt();
                id = body.getId();
                coolNote = body;

            }

            @Override
            public void onFailure(Call<CoolNote> call, Throwable t) {
                Log.e("Fetching Cool Note", "Fetching Cool Note %s failed.");
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
                title = "";
                content = "";
                createdAt = "";
                magnetColor = Color.parseColor("#80" + magnetColor);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Delete Cool Note", "Deleting Cool Note %s failed.");
            }
        });
    }

    public void goBack(View v) {

        Context context = v.getContext();
        Intent i = new Intent(context, HomeActivity.class);
        context.startActivity(i);

    }

}
