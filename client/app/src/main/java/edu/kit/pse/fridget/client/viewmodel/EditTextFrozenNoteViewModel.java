package edu.kit.pse.fridget.client.viewmodel;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import edu.kit.pse.fridget.client.activity.FullTextFrozenNoteActivity;
import edu.kit.pse.fridget.client.activity.HomeActivity;
import edu.kit.pse.fridget.client.datamodel.FrozenNote;
import edu.kit.pse.fridget.client.service.FrozenNoteService;
import edu.kit.pse.fridget.client.service.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditTextFrozenNoteViewModel extends ViewModel {
    private final MutableLiveData<String> title = new MutableLiveData<>();
    private final MutableLiveData<String> content = new MutableLiveData<>();
    private int position;
    private FrozenNote frozenNote;
    private String frozenNoteId;
    private SharedPreferencesData sharedPreferencesData = new SharedPreferencesData();

    public FrozenNote getFrozenNote() {
        return frozenNote;
    }

    public void setFrozenNoteId(String id) {
        this.frozenNoteId = id;
    }

    public MutableLiveData<String> getTitle() {
        return title;
    }

    public MutableLiveData<String> getContent() {
        return content;
    }

    public int setPosition(int position) { return this.position = position; }

    public void bold(View v) {
        String content = this.content.getValue();
        if(content == null){
            Toast.makeText(v.getContext(), "There is no text in the content box!", Toast.LENGTH_LONG).show();
        }
        else {
            SpannableStringBuilder str = new SpannableStringBuilder(content);
            str.setSpan(new StyleSpan(Typeface.BOLD), 0, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            this.content.setValue(str.toString());
        }
    }

    public void italic(View v) {
        String content = this.content.getValue();
        if(content == null){
            Toast.makeText(v.getContext(), "There is no text in the content box!", Toast.LENGTH_LONG).show();
        }
        else {
            SpannableStringBuilder str = new SpannableStringBuilder(content);
            str.setSpan(new StyleSpan(Typeface.ITALIC), 0, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            this.content.setValue(str.toString());
        }
    }

    public void underline(View v) {
        String content = this.content.getValue();
        if(content == null){
            Toast.makeText(v.getContext(), "There is no text in the content box!", Toast.LENGTH_LONG).show();
        }
        else {
            SpannableStringBuilder str = new SpannableStringBuilder(content);
            str.setSpan(new UnderlineSpan(), 0, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            this.content.setValue(str.toString());
        }
    }

    public void getFN(){
        RetrofitClientInstance.getRetrofitInstance().create(FrozenNoteService.class).getFrozenNote(frozenNoteId).enqueue(new Callback<FrozenNote>() {
            @Override
            public void onResponse(Call<FrozenNote> call, Response<FrozenNote> response) {
                frozenNote = response.body();
                if (frozenNote != null) {
                    Log.i("Fetching Frozen Note", String.format("Frozen Note has been fetched.", new Gson().toJson(frozenNote)));
                }
            }

            @Override
            public void onFailure(Call<FrozenNote> call, Throwable t) {
                Log.e("Fetching Frozen Note", "Fetching Frozen Note has failed.");
                t.printStackTrace();
            }
        });
    }

    //Editieren der Frozen Note, Viewwechsel zur FullTextFrozenNoteActivity
    public void updateFrozenNote(View v) {
        final Context context = v.getContext();
        Intent intent = new Intent(context, FullTextFrozenNoteActivity.class);
        String flatshareId = sharedPreferencesData.getSharedPreferencesData("flatshareId", v);

        frozenNote = new FrozenNote(frozenNoteId, title.getValue(), content.getValue(), flatshareId, position);

        RetrofitClientInstance.getRetrofitInstance().create(FrozenNoteService.class).updateFrozenNote(frozenNoteId, frozenNote).enqueue(new Callback<FrozenNote>() {
            @Override
            public void onResponse(Call<FrozenNote> call, Response<FrozenNote> response) {
                FrozenNote body = response.body();
                if (body != null) {
                    Log.i("Updated Frozen Note", String.format("Frozen Note has been updated.", new Gson().toJson(body)));
                    intent.putExtra("frozenNoteId", frozenNoteId);
                    context.startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<FrozenNote> call, Throwable t) {
                Log.e("Updating Frozen Note", "Updating Frozen Note has failed.");
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
