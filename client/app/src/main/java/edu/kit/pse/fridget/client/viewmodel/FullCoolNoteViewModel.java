package edu.kit.pse.fridget.client.viewmodel;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import edu.kit.pse.fridget.client.R;
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

    private String title;
    private String content;
    private String createdAt;
    private String creatorMembershipId;
    private String id;
    //zum testen
    private int magnetColor = Color.parseColor("#000000");
    private List<Member> memberList;

    private int magnetColor1;
    private int magnetColor2;
    private int magnetColor3;
    private int magnetColor4;
    private int magnetColor5;
    private int magnetColor6;
    private int magnetColor7;
    private int magnetColor8;
    private int magnetColor9;
    private int magnetColor10;
    private int magnetColor11;
    private int magnetColor12;
    private int magnetColor13;
    private int magnetColor14;
    private int[] magnetColors = new int[]{
            magnetColor1,
            magnetColor2,
            magnetColor3,
            magnetColor4,
            magnetColor5,
            magnetColor6,
            magnetColor7,
            magnetColor8,
            magnetColor9,
            magnetColor10,
            magnetColor11,
            magnetColor12,
            magnetColor13,
            magnetColor14
    };

    private String tempUserId = "123";

    public int getMagnetColor() {
        return magnetColor;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedAt() {
        return createdAt;
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
                creatorMembershipId = body.getCreatorMembershipId();
                id = body.getId();
            }

            @Override
            public void onFailure(Call<CoolNote> call, Throwable t) {
                Log.e("Fetching Cool Note", "Fetching Cool Note %s failed.");
                t.printStackTrace();
            }
        });
    }

    //get member für die magnetfarbe
    public void getMemberList(String flatshareId) {
        RetrofitClientInstance.getRetrofitInstance().create(MembershipService.class).getMemberList(flatshareId).enqueue(new Callback<List<Member>>() {
            @Override
            public void onResponse(Call<List<Member>> call, Response<List<Member>> response) {
                List<Member> body = response.body();
                Log.i("Fetching member list", String.format("Member list %s fetched.", new Gson().toJson(body)));
                memberList = body;
            }

            @Override
            public void onFailure(Call<List<Member>> call, Throwable t) {
                Log.e("Fetching member list", "Fetching member list %s failed.");
            }
        });
    }

    //lesebestätigung
    public void getReadstatus(String coolNoteId) {
        RetrofitClientInstance.getRetrofitInstance().create(ReadConfirmationService.class).getReadStatus(coolNoteId).enqueue(new Callback<List<ReadConfirmation>>() {
            @Override
            public void onResponse(Call<List<ReadConfirmation>> call, Response<List<ReadConfirmation>> response) {
                List<ReadConfirmation> body = response.body();
                Log.i("getReadConfirmations", String.format("Read confirmations %s fetched.", new Gson().toJson(body)));

                for (int i = 0; i <= body.size(); i++) {
                    ReadConfirmation readConfirmation = body.get(i);
                    if(readConfirmation.getMembershipId().equals(memberList.get(i).getId())) {
                        magnetColors[i] = Color.parseColor(memberList.get(i).getMagnetColor());
                    }
                }

            }

            @Override
            public void onFailure(Call<List<ReadConfirmation>> call, Throwable t) {
                Log.e("getReadConfirmations", "Fetching read confirmations %s failed.");
            }
        });
    }

    public void popUp(View v) {
        Context context = v.getContext();

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Delete Cool Note")
                    .setMessage("Are you sure you want to delete this Cool Note?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            deleteCoolNote(v);
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setIcon(R.drawable.fridget_logo)
                    .show();
        //}
    }

    public void deleteCoolNote(View v) {

        final Context context = v.getContext();
        Intent intent = new Intent(context, HomeActivity.class);

        RetrofitClientInstance.getRetrofitInstance().create(CoolNoteService.class).deleteCoolNote(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                Void body = response.body();
                if(body == null) {
                    if (creatorMembershipId == tempUserId) {
                        Log.i("Deleted Cool Note", String.format("Cool Note %s deleted.", new Gson().toJson(null)));
                        context.startActivity(intent);
                    }
                }
                else{
                    Toast.makeText(context, "This is not your Cool Note! You are not allowed to delete it!", Toast.LENGTH_LONG).show();
                }
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
