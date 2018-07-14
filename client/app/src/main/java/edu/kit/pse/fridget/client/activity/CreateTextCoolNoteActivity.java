package edu.kit.pse.fridget.client.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.databinding.CreateTextCoolNoteActivityBinding;
import edu.kit.pse.fridget.client.datamodel.CoolNote;
import edu.kit.pse.fridget.client.viewmodel.CreateCoolNoteViewModel;

public class CreateTextCoolNoteActivity extends AppCompatActivity {

    private static final String TAG = FullTextCoolNoteActivity.class.getSimpleName();
    int i = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Calling onCreate");
        CreateTextCoolNoteActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.create_text_cool_note_activity);

        final CreateCoolNoteViewModel createCoolNoteViewModel = ViewModelProviders.of(this).get(CreateCoolNoteViewModel.class);
        final EditText title = binding.enterTitle;
        final EditText content = binding.enterContent;
        final Button boldButton = binding.boldButton;
        final Button italicButton = binding.italicButton;
        final Button underlineButton = binding.underlineButton;
        final String tempCreatorMembershipId = "3879a0a8-546d-41cb-b26e-eb1324d0e72c";


        int position = getIntent().getIntExtra("position", 0);

        binding.setCoolNoteVM(createCoolNoteViewModel);

        CoolNote coolNote = new CoolNote(null, title.getText().toString(), content.getText().toString(), tempCreatorMembershipId, createCoolNoteViewModel.getCurrentDate(), position, null, null);

        createCoolNoteViewModel.createCoolNote(coolNote);

        boldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectionStart = content.getSelectionStart();
                int selectionEnd = content.getSelectionEnd();
                String selectedText = content.getText().toString().substring(selectionStart, selectionEnd);
                Spannable s = content.getText();

                if (i == 0) {
                    s.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), selectionStart, selectionEnd, 0);
                    content.setText(s);
                    content.setSelection(selectionStart, selectionEnd);
                    coolNote.setContent(s.toString());
                    i++;
                }
                if (i == 1){
                    s.setSpan(new StyleSpan(Typeface.NORMAL), selectionStart, selectionEnd, 0);
                    content.setText(s);
                    content.setSelection(selectionStart, selectionEnd);
                    coolNote.setContent(s.toString());
                    i = 0;
                }
            }
        });

        italicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectionStart = content.getSelectionStart();
                int selectionEnd = content.getSelectionEnd();
                String selectedText = content.getText().toString().substring(selectionStart, selectionEnd);
                Spannable s = content.getText();

                if (i == 0) {
                    s.setSpan(new StyleSpan(Typeface.ITALIC), selectionStart, selectionEnd, 0);
                    content.setText(s);
                    content.setSelection(selectionStart, selectionEnd);
                    coolNote.setContent(s.toString());
                    i++;
                }
                if (i == 1){
                    s.setSpan(new StyleSpan(Typeface.NORMAL), selectionStart, selectionEnd, 0);
                    content.setText(s);
                    content.setSelection(selectionStart, selectionEnd);
                    coolNote.setContent(s.toString());
                    i = 0;
                }
            }
        });

        underlineButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                int startSelection = content.getSelectionStart();
                int endSelection = content.getSelectionEnd();

                String selectedText = content.getText().toString().substring(startSelection, endSelection);
                Spannable s = content.getText();

                if (i == 0) {
                    s.setSpan(new UnderlineSpan(), startSelection, endSelection, 0);
                    content.setText(s);
                    content.setSelection(startSelection, endSelection);
                    coolNote.setContent(s.toString());
                    i++;
                }
                if (i == 1){
                    s.setSpan(new UnderlineSpan(), startSelection, endSelection, 0);
                    content.setText(s);
                    content.setSelection(startSelection, endSelection);
                    coolNote.setContent(s.toString());
                    i = 0;
                }
            }
        });

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
