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
import edu.kit.pse.fridget.client.viewmodel.CreateCoolNoteViewModel;
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
        editTextFrozenNoteViewModel.setPosition(position);


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