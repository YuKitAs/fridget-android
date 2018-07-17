package edu.kit.pse.fridget.client.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.databinding.FullTextCoolNoteActivityBinding;
import edu.kit.pse.fridget.client.viewmodel.CreateCoolNoteViewModel;
import edu.kit.pse.fridget.client.viewmodel.FullCoolNoteViewModel;
import edu.kit.pse.fridget.client.viewmodel.HomeViewModel;

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
        final CreateCoolNoteViewModel createCoolNoteViewModel = ViewModelProviders.of(this).get(CreateCoolNoteViewModel.class);
        final HomeViewModel homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        Intent i = getIntent();
        String coolNoteId = i.getStringExtra("coolNoteId");
        fullCoolNoteViewModel.getCoolNote(coolNoteId);

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
