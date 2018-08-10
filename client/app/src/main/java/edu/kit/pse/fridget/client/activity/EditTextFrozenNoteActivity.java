package edu.kit.pse.fridget.client.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.databinding.EditTextFrozenNoteActivityBinding;
import edu.kit.pse.fridget.client.viewmodel.EditTextFrozenNoteViewModel;

public class EditTextFrozenNoteActivity extends AppCompatActivity {

    private static final String TAG = FullTextFrozenNoteActivity.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Calling onCreate");
        EditTextFrozenNoteActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.edit_text_frozen_note_activity);
        final EditTextFrozenNoteViewModel editTextFrozenNoteViewModel = ViewModelProviders.of(this).get(EditTextFrozenNoteViewModel.class);
        binding.setFrozenNoteVM(editTextFrozenNoteViewModel);

        Intent i = getIntent();
        int position = i.getExtras().getInt("position");
        String frozenNoteId = i.getExtras().get("frozenNoteId").toString();
        editTextFrozenNoteViewModel.setFrozenNoteId(frozenNoteId);
        editTextFrozenNoteViewModel.setPosition(position);
        editTextFrozenNoteViewModel.getFN();


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "Calling onStart");
        EditTextFrozenNoteActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.edit_text_frozen_note_activity);
        final EditTextFrozenNoteViewModel editTextFrozenNoteViewModel = ViewModelProviders.of(this).get(EditTextFrozenNoteViewModel.class);
        binding.setFrozenNoteVM(editTextFrozenNoteViewModel);

        Intent i = getIntent();
        int position = i.getExtras().getInt("position");
        String frozenNoteId = i.getExtras().get("frozenNoteId").toString();
        editTextFrozenNoteViewModel.setFrozenNoteId(frozenNoteId);
        editTextFrozenNoteViewModel.setPosition(position);
        editTextFrozenNoteViewModel.getFN();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Calling onResume");
        EditTextFrozenNoteActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.edit_text_frozen_note_activity);
        final EditTextFrozenNoteViewModel editTextFrozenNoteViewModel = ViewModelProviders.of(this).get(EditTextFrozenNoteViewModel.class);
        binding.setFrozenNoteVM(editTextFrozenNoteViewModel);

        Intent i = getIntent();
        int position = i.getExtras().getInt("position");
        String frozenNoteId = i.getExtras().get("frozenNoteId").toString();
        editTextFrozenNoteViewModel.setFrozenNoteId(frozenNoteId);
        editTextFrozenNoteViewModel.setPosition(position);
        editTextFrozenNoteViewModel.getFN();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "Calling onPause");
        EditTextFrozenNoteActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.edit_text_frozen_note_activity);
        final EditTextFrozenNoteViewModel editTextFrozenNoteViewModel = ViewModelProviders.of(this).get(EditTextFrozenNoteViewModel.class);
        binding.setFrozenNoteVM(editTextFrozenNoteViewModel);

        Intent i = getIntent();
        int position = i.getExtras().getInt("position");
        String frozenNoteId = i.getExtras().get("frozenNoteId").toString();
        editTextFrozenNoteViewModel.setFrozenNoteId(frozenNoteId);
        editTextFrozenNoteViewModel.setPosition(position);
        editTextFrozenNoteViewModel.getFN();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "Calling onStop");
        EditTextFrozenNoteActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.edit_text_frozen_note_activity);
        final EditTextFrozenNoteViewModel editTextFrozenNoteViewModel = ViewModelProviders.of(this).get(EditTextFrozenNoteViewModel.class);
        binding.setFrozenNoteVM(editTextFrozenNoteViewModel);

        Intent i = getIntent();
        int position = i.getExtras().getInt("position");
        String frozenNoteId = i.getExtras().get("frozenNoteId").toString();
        editTextFrozenNoteViewModel.setFrozenNoteId(frozenNoteId);
        editTextFrozenNoteViewModel.setPosition(position);
        editTextFrozenNoteViewModel.getFN();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Calling onDestroy");
    }

}