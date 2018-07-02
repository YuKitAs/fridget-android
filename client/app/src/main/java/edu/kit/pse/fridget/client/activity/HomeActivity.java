package edu.kit.pse.fridget.client.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.databinding.HomeActivityBinding;

public class HomeActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HomeActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.home_activity);

    }
}
