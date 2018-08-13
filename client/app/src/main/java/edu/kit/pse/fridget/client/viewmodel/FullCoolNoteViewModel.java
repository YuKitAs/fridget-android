package edu.kit.pse.fridget.client.viewmodel;

import android.app.AlertDialog;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.activity.HomeActivity;
import edu.kit.pse.fridget.client.datamodel.CoolNote;
import edu.kit.pse.fridget.client.datamodel.Member;
import edu.kit.pse.fridget.client.datamodel.ReadConfirmation;
import edu.kit.pse.fridget.client.datamodel.representation.UserMembershipRepresentation;
import edu.kit.pse.fridget.client.service.CoolNoteService;
import edu.kit.pse.fridget.client.service.MembershipService;
import edu.kit.pse.fridget.client.service.ReadConfirmationService;
import edu.kit.pse.fridget.client.service.RetrofitClientInstance;
import edu.kit.pse.fridget.client.utility.DateTimeUtilities;
import edu.kit.pse.fridget.client.utility.MagnetColorUtilities;
import edu.kit.pse.fridget.client.utility.StyledContentUtilities;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FullCoolNoteViewModel extends ViewModel {
    private static final String TAG = FullCoolNoteViewModel.class.getSimpleName();

    public MutableLiveData<String> liveDataCreateTime = new MutableLiveData<String>();
    public MutableLiveData<String> liveDataTitle = new MutableLiveData<String>();
    public MutableLiveData<Integer> liveDataCreatorMagnetColor = new MutableLiveData<Integer>();
    public MutableLiveData<Spanned> liveDataStyledContent = new MutableLiveData<Spanned>();
    public MutableLiveData<ReaderList> liveDataReaderMagnetColors = new MutableLiveData<ReaderList>();

    private String flatshareId;
    private String coolNoteId;
    private String ownMembershipId;

    private CoolNote coolNote;
    private List<Member> readers = new ArrayList<>();


    public FullCoolNoteViewModel() {
        liveDataCreateTime.setValue("");
        liveDataTitle.setValue("");
        liveDataCreatorMagnetColor.setValue(0);
        liveDataStyledContent.setValue(Html.fromHtml("<p></p>"));
        liveDataReaderMagnetColors.setValue(new ReaderList(Collections.emptyList()));
    }

    public void fetchData() {
        fetchCoolNote();
        fetchReadConfirmations();
    }

    public void setFlatshareId(String id) {
        this.flatshareId = id;
    }

    public void setCoolNoteId(String id) {
        this.coolNoteId = id;
    }

    public void setOwnMembershipId(String id) {
        this.ownMembershipId = id;
    }

    private void fetchCoolNote() {
        RetrofitClientInstance.getRetrofitInstance().create(CoolNoteService.class).getCoolNote(coolNoteId).enqueue(new Callback<CoolNote>() {
            @Override
            public void onResponse(Call<CoolNote> call, Response<CoolNote> response) {
                coolNote = response.body();

                if (coolNote != null) {
                    Log.i(TAG, String.format("Cool Note fetched: %s", new Gson().toJson(coolNote)));

                    fetchCreatorMagnetColor();
                    sendReadConfirmation();

                    liveDataCreateTime.setValue(DateTimeUtilities.convertToLocalTime(coolNote.getCreatedAt()));
                    liveDataTitle.setValue(coolNote.getTitle());
                    liveDataStyledContent.setValue(StyledContentUtilities.convertToSpanned(coolNote.getContent()));
                }
            }

            @Override
            public void onFailure(Call<CoolNote> call, Throwable t) {
                Log.e(TAG, "Fetching Cool Note failed.");
            }
        });
    }

    private void fetchCreatorMagnetColor() {
        RetrofitClientInstance.getRetrofitInstance().create(MembershipService.class).getMemberList(flatshareId).enqueue(new Callback<List<UserMembershipRepresentation>>() {
            @Override
            public void onResponse(Call<List<UserMembershipRepresentation>> call, Response<List<UserMembershipRepresentation>> response) {
                List<UserMembershipRepresentation> memberships = response.body();
                if (memberships != null) {
                    for (UserMembershipRepresentation membership : memberships) {
                        if (membership.getMemberId().equals(coolNote.getCreatorMembershipId())) {
                            liveDataCreatorMagnetColor.setValue(MagnetColorUtilities.convertMagnetColor(membership.getMagnetColor()));
                            return;
                        }
                    }

                    liveDataCreatorMagnetColor.setValue(MagnetColorUtilities.getDefaultMagnetColor());
                }
            }

            @Override
            public void onFailure(Call<List<UserMembershipRepresentation>> call, Throwable t) {
                Log.e("Fetching Memberlist", "onFailure: There are no Members.");
            }
        });
    }

    private void sendReadConfirmation() {
        if (coolNote == null || coolNote.getCreatorMembershipId() == null || coolNote.getCreatorMembershipId().equals(ownMembershipId)) {
            return;
        }

        ReadConfirmation readConfirmation = new ReadConfirmation(null, coolNoteId, ownMembershipId);
        RetrofitClientInstance.getRetrofitInstance().create(ReadConfirmationService.class).createReadStatus(readConfirmation).enqueue(new Callback<ReadConfirmation>() {
            @Override
            public void onResponse(Call<ReadConfirmation> call, Response<ReadConfirmation> response) {
                ReadConfirmation body = response.body();
                Log.i(TAG, String.format("Read confirmation saved: %s", new Gson().toJson(body)));

                fetchReadConfirmations();
            }

            @Override
            public void onFailure(Call<ReadConfirmation> call, Throwable t) {
                Log.e(TAG, "Saving read confirmation failed.");
            }
        });
    }

    private void fetchReadConfirmations() {
        RetrofitClientInstance.getRetrofitInstance().create(ReadConfirmationService.class).getReaders(coolNoteId).enqueue(new Callback<List<Member>>() {
            @Override
            public void onResponse(Call<List<Member>> call, Response<List<Member>> response) {
                readers = response.body();
                if (readers != null) {
                    Log.i(TAG, String.format("Read confirmations fetched: %s", new Gson().toJson(readers)));

                    liveDataReaderMagnetColors.setValue(new ReaderList(readers));
                }
            }

            @Override
            public void onFailure(Call<List<Member>> call, Throwable t) {
                Log.e(TAG, "Fetching read confirmations failed.");
            }
        });
    }

    public void popUp(View v) {
        Context context = v.getContext();
        if (!coolNote.getCreatorMembershipId().equals(ownMembershipId)) {
            Toast.makeText(context, "This is not your Cool Note! You are not allowed to delete it!", Toast.LENGTH_LONG).show();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete Cool Note")
                .setMessage("Are you sure you want to delete this Cool Note?")
                .setPositiveButton("YES", (dialog, which) -> {
                    deleteCoolNote();
                    dialog.cancel();
                    goBack(v);
                })
                .setNegativeButton("NO", (dialog, which) -> dialog.cancel())
                .setIcon(R.drawable.fridget_logo)
                .show();
    }

    private void deleteCoolNote() {
        RetrofitClientInstance.getRetrofitInstance().create(CoolNoteService.class).deleteCoolNote(coolNote.getId()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i(TAG, String.format("Cool Note id=%s deleted.", coolNoteId));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "Deleting Cool Note failed.");
            }
        });

    }

    public void goBack(View v) {
        Context context = v.getContext();
        Intent i = new Intent(context, HomeActivity.class);
        context.startActivity(i);
    }

    public static class ReaderList {
        private final List<Integer> magnetColors = new ArrayList<Integer>();

        private ReaderList(List<Member> readers) {
            for (Member member : readers) {
                magnetColors.add(MagnetColorUtilities.convertMagnetColor(member.getMagnetColor()));
            }
        }

        public int getMagnetColor(int i) {
            return i < magnetColors.size() ? magnetColors.get(i) : MagnetColorUtilities.getDefaultMagnetColor();
        }

        public int isVisible(int i) {
            return i < magnetColors.size() ? View.VISIBLE : View.INVISIBLE;
        }
    }
}