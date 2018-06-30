package edu.kit.pse.fridget.client.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.databinding.FullTextCoolNoteActivityBinding;

public class FullTextCoolNoteActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        FullTextCoolNoteActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.full_text_cool_note_activity);
    }
}
