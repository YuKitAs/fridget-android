package edu.kit.pse.fridget.client.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseUser;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.databinding.StartActivityBinding;
import edu.kit.pse.fridget.client.viewmodel.StartViewModel;

public class StartActivity extends AppCompatActivity {


    private static final String TAG = StartActivity.class.getSimpleName();
    public static final String DEFAULT="N/A";



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Calling onCreate");
        StartActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.start_activity);
        StartViewModel start = new StartViewModel();
        binding.setStart(start);
    }


    @Override
    protected void onStart() {
        super.onStart();
        //Daten flatshareiD von shared preferences wird abgefragt
        SharedPreferences sharedPreferences =getSharedPreferences("edu.kit.pse.fridget.client_preferences",MODE_PRIVATE);
        String flatshareId =sharedPreferences.getString("flatshareId", DEFAULT);
       updateUI(flatshareId);


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

    //Es wird geprüft ob schon eine flatshareid vorhanden ist, wenn ja wird automatisch in HomeActivity gewechselt
    private void updateUI(String flatshareId) {
        if (flatshareId !=DEFAULT) {
            startActivity(new Intent(StartActivity.this, HomeActivity.class));
        }
    }
}
