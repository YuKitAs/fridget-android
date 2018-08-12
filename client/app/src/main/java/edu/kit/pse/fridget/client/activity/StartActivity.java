package edu.kit.pse.fridget.client.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.databinding.StartActivityBinding;
import edu.kit.pse.fridget.client.utility.SafeIntentExtrasExtractor;
import edu.kit.pse.fridget.client.viewmodel.StartViewModel;

public class StartActivity extends AppCompatActivity {

    private static final String TAG = StartActivity.class.getSimpleName();
    public static final String DEFAULT = "N/A";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Calling onCreate");
        StartActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.start_activity);
        StartViewModel start = new StartViewModel();
        binding.setStart(start);
        SharedPreferences sharedPreferences = getSharedPreferences("edu.kit.pse.fridget.client_preferences", MODE_PRIVATE);
        String flatshareId = sharedPreferences.getString("flatshareId", DEFAULT);

        redirectToNextActivity(flatshareId);
    }


    @Override
    protected void onStart() {
        super.onStart();
        //Daten flatshareiD von shared preferences wird abgefragt
        SharedPreferences sharedPreferences = getSharedPreferences("edu.kit.pse.fridget.client_preferences", MODE_PRIVATE);
        String flatshareId = sharedPreferences.getString("flatshareId", DEFAULT);

        redirectToNextActivity(flatshareId);

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
    private void redirectToNextActivity(String flatshareId) {
        if (!DEFAULT.equals(flatshareId)) {
            // If user clicks on the push notification, coolNoteId will be set in extras
            String coolNoteId = SafeIntentExtrasExtractor.getString(getIntent(), "coolNoteId");

            if (coolNoteId != null) {
                redirectToFullTextCoolNoteActivity(coolNoteId);
            } else {
                redirectToHomeActivity();
            }
        }
    }

    private void redirectToHomeActivity() {
        startActivity(new Intent(StartActivity.this, HomeActivity.class));
    }

    private void redirectToFullTextCoolNoteActivity(String coolNoteId) {
        Intent intent = new Intent(StartActivity.this, FullTextCoolNoteActivity.class);
        intent.putExtra("coolNoteId", coolNoteId);
        startActivity(intent);
    }
}
