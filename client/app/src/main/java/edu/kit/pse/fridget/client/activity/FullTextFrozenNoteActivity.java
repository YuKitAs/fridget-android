package edu.kit.pse.fridget.client.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.databinding.FullTextFrozenNoteActivityBinding;
import edu.kit.pse.fridget.client.viewmodel.FullFrozenNoteViewModel;


public class FullTextFrozenNoteActivity extends AppCompatActivity {

    private static final String TAG = FullTextFrozenNoteActivity.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Calling onCreate");
        FullTextFrozenNoteActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.full_text_frozen_note_activity);
        final FullFrozenNoteViewModel fullFrozenNoteViewModel = ViewModelProviders.of(this).get(FullFrozenNoteViewModel.class);
        binding.setFullFrozenNoteVM(fullFrozenNoteViewModel);

        Intent i = getIntent();
        String frozenNoteId = i.getExtras().getString("frozenNoteId");
        fullFrozenNoteViewModel.setFrozenNoteId(frozenNoteId);
        fullFrozenNoteViewModel.getFN();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "Calling onStart");
        FullTextFrozenNoteActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.full_text_frozen_note_activity);
        final FullFrozenNoteViewModel fullFrozenNoteViewModel = ViewModelProviders.of(this).get(FullFrozenNoteViewModel.class);
        binding.setFullFrozenNoteVM(fullFrozenNoteViewModel);

        Intent i = getIntent();
        String frozenNoteId = i.getExtras().getString("frozenNoteId");
        fullFrozenNoteViewModel.setFrozenNoteId(frozenNoteId);
        fullFrozenNoteViewModel.getFN();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Calling onResume");
        FullTextFrozenNoteActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.full_text_frozen_note_activity);
        final FullFrozenNoteViewModel fullFrozenNoteViewModel = ViewModelProviders.of(this).get(FullFrozenNoteViewModel.class);
        binding.setFullFrozenNoteVM(fullFrozenNoteViewModel);

        Intent i = getIntent();
        String frozenNoteId = i.getExtras().getString("frozenNoteId");
        fullFrozenNoteViewModel.setFrozenNoteId(frozenNoteId);
        fullFrozenNoteViewModel.getFN();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "Calling onPause");
        FullTextFrozenNoteActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.full_text_frozen_note_activity);
        final FullFrozenNoteViewModel fullFrozenNoteViewModel = ViewModelProviders.of(this).get(FullFrozenNoteViewModel.class);
        binding.setFullFrozenNoteVM(fullFrozenNoteViewModel);

        Intent i = getIntent();
        String frozenNoteId = i.getExtras().getString("frozenNoteId");
        fullFrozenNoteViewModel.setFrozenNoteId(frozenNoteId);
        fullFrozenNoteViewModel.getFN();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "Calling onStop");
        FullTextFrozenNoteActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.full_text_frozen_note_activity);
        final FullFrozenNoteViewModel fullFrozenNoteViewModel = ViewModelProviders.of(this).get(FullFrozenNoteViewModel.class);
        binding.setFullFrozenNoteVM(fullFrozenNoteViewModel);

        Intent i = getIntent();
        String frozenNoteId = i.getExtras().getString("frozenNoteId");
        fullFrozenNoteViewModel.setFrozenNoteId(frozenNoteId);
        fullFrozenNoteViewModel.getFN();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Calling onDestroy");
    }
}


