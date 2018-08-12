package edu.kit.pse.fridget.client.viewmodel;

import android.app.AlertDialog;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.DialogInterface;
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
import edu.kit.pse.fridget.client.datamodel.ReadConfirmation;
import edu.kit.pse.fridget.client.datamodel.command.ReadConfirmationCommand;
import edu.kit.pse.fridget.client.service.CoolNoteService;
import edu.kit.pse.fridget.client.service.ReadConfirmationService;
import edu.kit.pse.fridget.client.service.RetrofitClientInstance;
import edu.kit.pse.fridget.client.utility.DateTimeUtilities;
import edu.kit.pse.fridget.client.utility.MagnetColorUtilities;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FullCoolNoteViewModel extends ViewModel {
    private static final String TAG = FullCoolNoteViewModel.class.getSimpleName();

    public MutableLiveData<String> liveDataCreateTime = new MutableLiveData<String>();
    public MutableLiveData<String> liveDataTitle = new MutableLiveData<String>();
    public MutableLiveData<Integer> liveDataOwnMagnetColor = new MutableLiveData<Integer>();
    public MutableLiveData<Spanned> liveDataStyledContent = new MutableLiveData<Spanned>();
    public MutableLiveData<ReadConfirmationsList> liveDataReaderMagnetColors = new MutableLiveData<ReadConfirmationsList>();

    private String coolNoteId;
    private String ownMemberId;

    private CoolNote coolNote;
    private List<ReadConfirmationCommand> readConfirmations = new ArrayList<>();


    public FullCoolNoteViewModel() {
        liveDataCreateTime.setValue("");
        liveDataTitle.setValue("");
        liveDataOwnMagnetColor.setValue(0);
        liveDataStyledContent.setValue(Html.fromHtml("<p></p>"));
        liveDataReaderMagnetColors.setValue(new ReadConfirmationsList(Collections.emptyList()));
    }

    public void fetchData() {
        fetchCoolNote();
        fetchReadConfirmations();
    }

    public void setCoolNoteId(String id) {
        this.coolNoteId = id;
    }

    public void setOwnMagnetColor(String magnetColor) {
        liveDataOwnMagnetColor.setValue(MagnetColorUtilities.convertMagnetColor(magnetColor));
    }

    public void setOwnMemberId(String id) {
        this.ownMemberId = id;
    }

    private void fetchCoolNote() {
        RetrofitClientInstance.getRetrofitInstance().create(CoolNoteService.class).getCoolNote(coolNoteId).enqueue(new Callback<CoolNote>() {
            @Override
            public void onResponse(Call<CoolNote> call, Response<CoolNote> response) {
                coolNote = response.body();

                if (coolNote != null) {
                    Log.i(TAG, String.format("Cool Note %s fetched.", new Gson().toJson(coolNote)));

                    liveDataCreateTime.setValue(DateTimeUtilities.convertToLocalTime(coolNote.getCreatedAt()));
                    liveDataTitle.setValue(coolNote.getTitle());
                    liveDataStyledContent.setValue(coolNote.getContent() != null
                            ? Html.fromHtml(coolNote.getContent())
                            : Html.fromHtml("<p></p>"));

                    sendReadConfirmation();
                }
            }

            @Override
            public void onFailure(Call<CoolNote> call, Throwable t) {
                Log.e(TAG, "Fetching Cool Note %s failed.");
            }
        });
    }

    //lesebestätigung
    private void fetchReadConfirmations() {
        RetrofitClientInstance.getRetrofitInstance().create(ReadConfirmationService.class).getReadStatus(coolNoteId).enqueue(new Callback<List<ReadConfirmationCommand>>() {
            @Override
            public void onResponse(Call<List<ReadConfirmationCommand>> call, Response<List<ReadConfirmationCommand>> response) {
                readConfirmations = response.body();
                if (readConfirmations != null) {
                    Log.i(TAG, String.format("Read confirmations %s fetched.", new Gson().toJson(readConfirmations)));

                    liveDataReaderMagnetColors.setValue(new ReadConfirmationsList(readConfirmations));
                }
            }

            @Override
            public void onFailure(Call<List<ReadConfirmationCommand>> call, Throwable t) {
                Log.e(TAG, "Fetching read confirmations %s failed.");
            }
        });
    }

    //save checkbox status
    private void sendReadConfirmation() {
        if (coolNote == null || coolNote.getCreatorMembershipId() == null || coolNote.getCreatorMembershipId().equals(ownMemberId)) {
            return;
        }

        ReadConfirmation readConfirmation = new ReadConfirmation(null, coolNoteId, ownMemberId);
        RetrofitClientInstance.getRetrofitInstance().create(ReadConfirmationService.class).createReadStatus(readConfirmation).enqueue(new Callback<ReadConfirmation>() {
            @Override
            public void onResponse(Call<ReadConfirmation> call, Response<ReadConfirmation> response) {
                ReadConfirmation body = response.body();
                Log.i(TAG, String.format("Read confirmation %s saved.", new Gson().toJson(body)));
            }

            @Override
            public void onFailure(Call<ReadConfirmation> call, Throwable t) {
                Log.e(TAG, "Saving read confirmation %s failed.");
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
                        if (!coolNote.getCreatorMembershipId().equals(ownMemberId)) {
                            Toast.makeText(context, "This is not your Cool Note! You are not allowed to delete it!", Toast.LENGTH_LONG).show();
                            return;
                        }

                        deleteCoolNote();
                        dialog.cancel();
                        goBack(v);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(R.drawable.fridget_logo)
                .show();
    }

    private void deleteCoolNote() {
        RetrofitClientInstance.getRetrofitInstance().create(CoolNoteService.class).deleteCoolNote(coolNote.getId()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i(TAG, String.format("Cool Note %s deleted.", coolNoteId));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "Deleting Cool Note %s failed.");
            }
        });

    }

    public void goBack(View v) {
        Context context = v.getContext();
        Intent i = new Intent(context, HomeActivity.class);
        context.startActivity(i);
    }

    public static class ReadConfirmationsList {
        private final List<Integer> magnetColors = new ArrayList<Integer>();

        private ReadConfirmationsList(List<ReadConfirmationCommand> readConfirmations) {
            for (ReadConfirmationCommand readConfirmation : readConfirmations) {
                magnetColors.add(MagnetColorUtilities.convertMagnetColor(readConfirmation.getMagnetColor()));
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