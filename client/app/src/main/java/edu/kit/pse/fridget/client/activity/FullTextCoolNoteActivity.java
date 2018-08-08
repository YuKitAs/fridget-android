package edu.kit.pse.fridget.client.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.databinding.FullTextCoolNoteActivityBinding;
import edu.kit.pse.fridget.client.viewmodel.FullCoolNoteViewModel;

public class FullTextCoolNoteActivity extends AppCompatActivity {

    private static final String TAG = FullTextCoolNoteActivity.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Calling onCreate");
        FullTextCoolNoteActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.full_text_cool_note_activity);

        //viewmodels
        final FullCoolNoteViewModel fullCoolNoteViewModel = ViewModelProviders.of(this).get(FullCoolNoteViewModel.class);
        binding.setFullNoteVM(fullCoolNoteViewModel);

        Intent i = getIntent();
        final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);
        //String coolNoteId = i.getExtras().get("coolNoteId").toString();
        fullCoolNoteViewModel.getCoolNote("2eb5a862-9a17-4ac1-b07b-d81337df111f", viewGroup);
        //fullCoolNoteViewModel.getMemberList("c48a9caa-1c98-4f15-a2ca-5048c497b0f5");
        //fullCoolNoteViewModel.getReadstatus("72f795d8-a746-4db7-860a-98a24704d571");
        //GradientDrawable drawable = (GradientDrawable) getDrawable(R.drawable.magnet);
        //drawable.setColor(Color.parseColor("#000000"));

    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "Calling onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Calling onResume");
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "Calling onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "Calling onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "Calling onDestroy");
        super.onDestroy();
    }
}
