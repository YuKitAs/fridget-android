package edu.kit.pse.fridget.client.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.databinding.StartActivityBinding;

public class StartActivity extends AppCompatActivity {


    private static final String TAG = StartActivity.class.getSimpleName();
    Button btn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Calling onCreate");
        StartActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.start_activity);
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
