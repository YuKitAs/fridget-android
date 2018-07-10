package edu.kit.pse.fridget.client.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.databinding.*;
import edu.kit.pse.fridget.client.databinding.CreateFlatshareActivityBinding;
import edu.kit.pse.fridget.client.viewmodel.CreateFlatshareViewModel;

public class CreateFlatshareActivity extends AppCompatActivity {

    private static final String TAG = CreateFlatshareActivity.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Calling onCreate");
        CreateFlatshareActivityBinding binding = DataBindingUtil.setContentView(this,R.layout.create_flatshare_activity);
        CreateFlatshareViewModel createFlatshare = new CreateFlatshareViewModel();
        binding.setCreateFlatshare(createFlatshare);


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
