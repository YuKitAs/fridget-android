package edu.kit.pse.fridget.client.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.databinding.CreateTextCoolNoteActivityBinding;
import edu.kit.pse.fridget.client.viewmodel.CreateCoolNoteViewModel;

public class CreateTextCoolNoteActivity extends AppCompatActivity {

    private static final String TAG = FullTextCoolNoteActivity.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Calling onCreate");

        CreateTextCoolNoteActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.create_text_cool_note_activity);
        final CreateCoolNoteViewModel createCoolNoteViewModel = ViewModelProviders.of(this).get(CreateCoolNoteViewModel.class);
        binding.setCoolNoteVM(createCoolNoteViewModel);


/*        final EditText title = binding.enterTitle;
        final EditText content = binding.enterContent;
        final Button boldButton = binding.boldButton;
        final Button italicButton = binding.italicButton;
        final Button underlineButton = binding.underlineButton;
        final String tempCreatorMembershipId = "5cd8d207-39d7-4de1-aa84-64e59804ab70";

        int position = getIntent().getIntExtra("position", 0);*/


/*        boldButton.setOnClickListener(v -> {
                int selectionStart = content.getSelectionStart();
                int selectionEnd = content.getSelectionEnd();
                // was ist das?
                String selectedText = content.getText().toString().substring(selectionStart, selectionEnd);
                Spannable s = content.getText();

                if (i == 0) {
                    s.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), selectionStart, selectionEnd, 0);
                    content.setText(s);
                    content.setSelection(selectionStart, selectionEnd);
                    i++;
                }
                if (i == 1){
                    s.setSpan(new StyleSpan(Typeface.NORMAL), selectionStart, selectionEnd, 0);
                    content.setText(s);
                    content.setSelection(selectionStart, selectionEnd);
                    i = 0;
            }
        });

        italicButton.setOnClickListener(v -> {
            int selectionStart = content.getSelectionStart();
            int selectionEnd = content.getSelectionEnd();
            String selectedText = content.getText().toString().substring(selectionStart, selectionEnd);
            Spannable s = content.getText();

            if (i == 0) {
                s.setSpan(new StyleSpan(Typeface.ITALIC), selectionStart, selectionEnd, 0);
                content.setText(s);
                content.setSelection(selectionStart, selectionEnd);
                i++;
            }
            if (i == 1){
                s.setSpan(new StyleSpan(Typeface.NORMAL), selectionStart, selectionEnd, 0);
                content.setText(s);
                content.setSelection(selectionStart, selectionEnd);
                i = 0;
            }
        });

        underlineButton.setOnClickListener(v -> {
            int startSelection = content.getSelectionStart();
            int endSelection = content.getSelectionEnd();

            String selectedText = content.getText().toString().substring(startSelection, endSelection);
            Spannable s = content.getText();

            if (i == 0) {
                s.setSpan(new UnderlineSpan(), startSelection, endSelection, 0);
                content.setText(s);
                content.setSelection(startSelection, endSelection);
                i++;
            }
            if (i == 1){
                s.setSpan(new UnderlineSpan(), startSelection, endSelection, 0);
                content.setText(s);
                content.setSelection(startSelection, endSelection);
                i = 0;
            }
        });*/

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
