package edu.kit.pse.fridget.client.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.databinding.FullTextCoolNoteActivityBinding;

public class LoginActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FullTextCoolNoteActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.login_activity);
    }
}
