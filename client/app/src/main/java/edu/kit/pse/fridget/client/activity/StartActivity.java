package edu.kit.pse.fridget.client.activity;

import android.content.Intent;
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
    private static final String DEFAULT = "N/A";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Calling onCreate");

        // Redirect to next activity before loading the full view
        String flatshareId = restoreFlatshareId();
        if (!DEFAULT.equals(flatshareId)) {
            redirectToNextActivity(flatshareId);
            return;
        }

        StartActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.start_activity);
        StartViewModel start = new StartViewModel();
        binding.setStart(start);
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
        super.onPause();
        Log.i(TAG, "Calling onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "Calling onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Calling onDestroy");
    }

    private String restoreFlatshareId() {
        return getSharedPreferences("edu.kit.pse.fridget.client_preferences", MODE_PRIVATE)
                .getString("flatshareId", DEFAULT);
    }

    private void redirectToNextActivity(String flatshareId) {
        // If user clicks on the push notification, coolNoteId will be set in extras
        String coolNoteId = SafeIntentExtrasExtractor.getString(getIntent(), "coolNoteId");

        if (coolNoteId != null) {
            redirectToFullTextCoolNoteActivity(coolNoteId);
        } else {
            redirectToHomeActivity();
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
