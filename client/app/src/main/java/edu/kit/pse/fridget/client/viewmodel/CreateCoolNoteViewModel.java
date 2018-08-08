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

import java.util.ArrayList;

import edu.kit.pse.fridget.client.activity.FullTextCoolNoteActivity;
import edu.kit.pse.fridget.client.activity.HomeActivity;
import edu.kit.pse.fridget.client.datamodel.CoolNote;
import edu.kit.pse.fridget.client.service.CoolNoteService;
import edu.kit.pse.fridget.client.service.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCoolNoteViewModel extends ViewModel {
    private final MutableLiveData<String> title = new MutableLiveData<>();
    private final MutableLiveData<String> content = new MutableLiveData<>();
    SharedPreferencesData sharedPreferencesData =new SharedPreferencesData();
    private int position;

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

    //Erstellen der Cool Note, Viewwechsel zur FullCoolNoteActivity
    public void postCoolNote(View v) {
        String ownMembershipId =sharedPreferencesData.getSharedPreferencesData("ownMemberId",v);
        final Context context = v.getContext();
        Intent intent = new Intent(context, FullTextCoolNoteActivity.class);

        if (title.getValue() == null) {
            Toast.makeText(context, "Title cannot be empty!", Toast.LENGTH_LONG).show();
        }
        else {
            CoolNote coolNote = new CoolNote(null, title.getValue(), content.getValue(), ownMembershipId, position, 0, null, new ArrayList<>());

            RetrofitClientInstance.getRetrofitInstance().create(CoolNoteService.class).createCoolNote(coolNote).enqueue(new Callback<CoolNote>() {
                @Override
                public void onResponse(Call<CoolNote> call, Response<CoolNote> response) {
                    CoolNote body = response.body();
                    if (body != null) {
                        Log.i("Created Cool Note", String.format("Cool Note %s created.", new Gson().toJson(body)));

                        intent.putExtra("coolNoteId", body.getId());
                        context.startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<CoolNote> call, Throwable t) {
                    Log.e("Creating Cool Note", "Creating Cool Note %s failed.");
                    t.printStackTrace();
                }
            });
        }
    }

    //Viewwechsel zur HomeActivity
    public void goBack(View v) {
        Context context = v.getContext();
        Intent i = new Intent(context, HomeActivity.class);
        context.startActivity(i);
    }

}
