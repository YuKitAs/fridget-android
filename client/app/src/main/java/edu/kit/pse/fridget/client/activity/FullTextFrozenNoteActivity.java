package edu.kit.pse.fridget.client.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.view.GravityCompat;
import android.widget.ImageButton;
import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.databinding.FullTextFrozenNoteActivityBinding;
import android.view.View;


public class FullTextFrozenNoteActivity extends AppCompatActivity {
    private static final String TAG = FullTextFrozenNoteActivity.class.getSimpleName();

    final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

    final ImageButton btnOpenDrawer = (ImageButton) findViewById(R.id.home);



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Calling onCreate");
        FullTextFrozenNoteActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.full_text_frozen_note_activity);
        btnOpenDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

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


