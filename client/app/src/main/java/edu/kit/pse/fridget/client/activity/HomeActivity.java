package edu.kit.pse.fridget.client.activity;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.databinding.HomeActivityBinding;

import edu.kit.pse.fridget.client.viewmodel.HomeViewModel;

public class HomeActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HomeActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.home_activity);

        HomeViewModel homeVM = new HomeViewModel();
        binding.setHomeVM(homeVM);

    }

    public void createCoolNoteView(View v){
        startActivity(new Intent(HomeActivity.this, CreateTextCoolNoteActivity.class));

    }
}
