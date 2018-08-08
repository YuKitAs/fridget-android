package edu.kit.pse.fridget.client.activity;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.databinding.HomeActivityBinding;
import edu.kit.pse.fridget.client.viewmodel.HomeViewModel;

public class HomeActivity extends AppCompatActivity implements LifecycleOwner {

    private static final String TAG = HomeActivity.class.getSimpleName();

    private HomeViewModel homeVM = new HomeViewModel();



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Calling onCreate");

        HomeActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.home_activity);

        Context c = this.getApplicationContext();

        homeVM = ViewModelProviders.of(this).get(HomeViewModel.class);
        binding.setHomeVM(homeVM);

        binding.setLifecycleOwner(this);

        homeVM.setHomeContext(c);
        homeVM.updateLists();


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "Calling onStart");
        homeVM.updateLists();
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
