package edu.kit.pse.fridget.client.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.databinding.FullTextCoolNoteActivityBinding;
import edu.kit.pse.fridget.client.datamodel.CoolNote;
import edu.kit.pse.fridget.client.viewmodel.FullCoolNoteViewModel;

public class FullTextCoolNoteActivity extends AppCompatActivity {

    private static final String TAG = FullTextCoolNoteActivity.class.getSimpleName();
    CoolNote coolNote;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Calling onCreate");
        FullTextCoolNoteActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.full_text_cool_note_activity);
        final FullCoolNoteViewModel fullCoolNoteViewModel = ViewModelProviders.of(this).get(FullCoolNoteViewModel.class);
        binding.setFullNoteVM(fullCoolNoteViewModel);

        TextView titleTV = binding.title;
        TextView contentTV = binding.content;
        //Intent i = getIntent();
        //String title = i.getStringExtra("coolNoteTitle");
        //String content = i.getStringExtra("coolNoteContent");
        coolNote = fullCoolNoteViewModel.getCoolNote();
        titleTV.setText(coolNote.getTitle());
        contentTV.setText(coolNote.getContent());
        binding.setCoolNote(coolNote);
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
