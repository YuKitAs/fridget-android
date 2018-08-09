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

import java.util.ArrayList;
import java.util.List;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.activity.HomeActivity;
import edu.kit.pse.fridget.client.datamodel.CoolNote;
import edu.kit.pse.fridget.client.datamodel.ReadConfirmation;
import edu.kit.pse.fridget.client.datamodel.command.GetMemberCommand;
import edu.kit.pse.fridget.client.datamodel.command.ReadConfirmationCommand;
import edu.kit.pse.fridget.client.service.CoolNoteService;
import edu.kit.pse.fridget.client.service.MembershipService;
import edu.kit.pse.fridget.client.service.ReadConfirmationService;
import edu.kit.pse.fridget.client.service.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FullCoolNoteViewModel extends ViewModel {

    private CoolNote coolNote;

    public CoolNote getCoolNote() {
        return coolNote;
    }

    private int magnetColor;

    public int getMagnetColor() {
        return magnetColor;
    }

    private String flatshareId;
    private String ownMemberId;

    private List<GetMemberCommand> memberList = new ArrayList<>(15);

    public List<GetMemberCommand> getMemberList() {
        return memberList;
    }

    private SharedPreferencesData sharedPreferencesData =new SharedPreferencesData();

    public void setFlatshareId(String id) {
        this.flatshareId = id;
    }

    public void setOwnMemberId(String id) {
        this.ownMemberId = id;
    }

    private int[] magnetColors = new int[14];

    public int[] getMagnetColors() {
        return magnetColors;
    }

    /*private int magnetColor1 = Color.parseColor("#" + magnetColors[0]);
    private int magnetColor2 = Color.parseColor("#" + magnetColors[1]);
    private int magnetColor3 = Color.parseColor("#" + magnetColors[2]);
    private int magnetColor4 = Color.parseColor("#" + magnetColors[3]);
    private int magnetColor5 = Color.parseColor("#" + magnetColors[4]);
    private int magnetColor6 = Color.parseColor("#" + magnetColors[5]);
    private int magnetColor7 = Color.parseColor("#" + magnetColors[6]);
    private int magnetColor8 = Color.parseColor("#" + magnetColors[7]);
    private int magnetColor9 = Color.parseColor("#" + magnetColors[8]);
    private int magnetColor10 = Color.parseColor("#" + magnetColors[9]);
    private int magnetColor11 = Color.parseColor("#" + magnetColors[10]);
    private int magnetColor12 = Color.parseColor("#" + magnetColors[11]);
    private int magnetColor13 = Color.parseColor("#" + magnetColors[12]);
    private int magnetColor14 = Color.parseColor("#" + magnetColors[13]);*/

    /*public int getMagnetColor1() {
        return magnetColor1;
    }

    public int getMagnetColor2() {
        return magnetColor2;
    }

    public int getMagnetColor3() {
        return magnetColor3;
    }

    public int getMagnetColor4() {
        return magnetColor4;
    }

    public int getMagnetColor5() {
        return magnetColor5;
    }

    public int getMagnetColor6() {
        return magnetColor6;
    }

    public int getMagnetColor7() {
        return magnetColor7;
    }

    public int getMagnetColor8() {
        return magnetColor8;
    }

    public int getMagnetColor9() {
        return magnetColor9;
    }

    public int getMagnetColor10() {
        return magnetColor10;
    }

    public int getMagnetColor11() {
        return magnetColor11;
    }

    public int getMagnetColor12() {
        return magnetColor12;
    }

    public int getMagnetColor13() {
        return magnetColor13;
    }

    public int getMagnetColor14() {
        return magnetColor14;
    }*/

    public void getCoolNote(String coolNoteId, View v) {
        RetrofitClientInstance.getRetrofitInstance().create(CoolNoteService.class).getCoolNote(coolNoteId).enqueue(new Callback<CoolNote>() {
            @Override
            public void onResponse(Call<CoolNote> call, Response<CoolNote> response) {
                coolNote = response.body();
                if(coolNote != null) {
                    Log.i("Fetching Cool Note", String.format("Cool Note %s fetched.", new Gson().toJson(coolNote)));
                    List<GetMemberCommand> mList = getMemberList(v);
                    for (int i = 0; i < mList.size(); i++) {
                        if (coolNote.getCreatorMembershipId().equals(mList.get(i).getMemberId())) {
                            magnetColor = Color.parseColor("#"+ mList.get(i).getMagnetColor());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<CoolNote> call, Throwable t) {
                Log.e("Fetching Cool Note", "Fetching Cool Note %s failed.");
                t.printStackTrace();
            }
        });
    }

    //get member für die magnetfarbe
    public List<GetMemberCommand> getMemberList(View v) {
        //String flatshareId = sharedPreferencesData.getSharedPreferencesData("flatshareId",v);
        RetrofitClientInstance.getRetrofitInstance().create(MembershipService.class).getMemberList("54381329-7fe5-45b9-8941-944c60111007").enqueue(new Callback<List<GetMemberCommand>>() {
            @Override
            public void onResponse(Call<List<GetMemberCommand>> call, Response<List<GetMemberCommand>> response) {
                memberList = new ArrayList<>(15);
                List<GetMemberCommand> members = response.body();
                if (members != null) {
                    for (int m = 0; m < members.size(); m++) {
                        memberList.add(members.get(m));
                    }
                }
                Log.i("Fetching Member List", String.format("Member list %s fetched.", new Gson().toJson(memberList)));

            }

            @Override
            public void onFailure(Call<List<GetMemberCommand>> call, Throwable t) {
                Log.e("Fetching Memberlist", "There are no Members.");
            }
        });

        return memberList;
    }

    /*private int[] getMagnetColors(Member[] memberlist) {
        memberlist = getMemberList();
        magnetColors = new int[15];
        for(int i = 0; i < memberlist.length; i++) {
            if (coolNote != null) {
                magnetColors[i] = Color.parseColor("#" + memberlist[i].getMagnetColor());
            }
        }
        return magnetColors;
    }

    private int getMemberColorbyUserId(String id, Member[] memberlist) {
        memberlist = this.getMemberList();
        for (Member m : memberlist) {
            if (m.getId().equals(id)) {
                return Color.parseColor(m.getMagnetColor());
            }
        }
        return Color.parseColor("#000000");
    }*/

    //lesebestätigung
    public void getReadstatus(String coolNoteId, View v) {
        RetrofitClientInstance.getRetrofitInstance().create(ReadConfirmationService.class).getReadStatus(coolNoteId).enqueue(new Callback<List<ReadConfirmationCommand>>() {
            @Override
            public void onResponse(Call<List<ReadConfirmationCommand>> call, Response<List<ReadConfirmationCommand>> response) {
                List<ReadConfirmationCommand> readConfirmations = response.body();
                if(readConfirmations != null) {
                    Log.i("getReadConfirmations", String.format("Read confirmations %s fetched.", new Gson().toJson(readConfirmations)));
                    for (int i = 0; i < readConfirmations.size(); i++) {
                        magnetColors[i] = Color.parseColor("#" + readConfirmations.get(i).getMagnetColor());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ReadConfirmationCommand>> call, Throwable t) {
                Log.e("getReadConfirmations", "Fetching read confirmations %s failed.");
            }
        });
    }

    //save checkbox status
    public void saveReadStatus(View v) {
        ReadConfirmation readConfirmation = new ReadConfirmation(null, coolNote.getId(), coolNote.getCreatorMembershipId());
        RetrofitClientInstance.getRetrofitInstance().create(ReadConfirmationService.class).createReadStatus(readConfirmation).enqueue(new Callback<ReadConfirmation>() {
            @Override
            public void onResponse(Call<ReadConfirmation> call, Response<ReadConfirmation> response) {
                ReadConfirmation body = response.body();
                Log.i("saveReadConfirmation", String.format("Read confirmation %s saved.", new Gson().toJson(body)));
            }

            @Override
            public void onFailure(Call<ReadConfirmation> call, Throwable t) {
                Log.e("saveReadConfirmation", "Saving read confirmation %s failed.");
            }
        });
    }

    //delete checkbox status
    public void deleteReadStatus(View v) {
        RetrofitClientInstance.getRetrofitInstance().create(ReadConfirmationService.class).deleteReadStatus(coolNote.getId(), coolNote.getCreatorMembershipId()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Void body = response.body();
                Log.i("deleteReadConfirmation", String.format("Read confirmation %s deleted.", new Gson().toJson(body)));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("deleteReadConfirmation", "Deleting read confirmation %s failed.");
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

        if (coolNote.getCreatorMembershipId().equals(ownMemberId)) {
            RetrofitClientInstance.getRetrofitInstance().create(CoolNoteService.class).deleteCoolNote(coolNote.getId()).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {

                    Void body = response.body();
                    if (body == null) {
                        Log.i("Deleted Cool Note", String.format("Cool Note %s deleted.", new Gson().toJson(null)));
                        context.startActivity(intent);
                    }

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("Delete Cool Note", "Deleting Cool Note %s failed.");
                }
            });
        }
        else{
            Toast.makeText(context, "This is not your Cool Note! You are not allowed to delete it!", Toast.LENGTH_LONG).show();
        }
    }

    public void goBack(View v) {
        Context context = v.getContext();
        Intent i = new Intent(context, HomeActivity.class);
        context.startActivity(i);
    }

}