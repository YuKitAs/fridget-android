package edu.kit.pse.fridget.client.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.databinding.FullTextFrozenNoteActivityBinding;
import edu.kit.pse.fridget.client.databinding.NavHeaderBinding;
import edu.kit.pse.fridget.client.viewmodel.FullFrozenNoteViewModel;


public class FullTextFrozenNoteActivity extends AppCompatActivity {

    private static final String TAG = FullTextFrozenNoteActivity.class.getSimpleName();

    private DrawerLayout drawerLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Calling onCreate");
        FullTextFrozenNoteActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.full_text_frozen_note_activity);
        final FullFrozenNoteViewModel fullFrozenNoteViewModel = ViewModelProviders.of(this).get(FullFrozenNoteViewModel.class);

        String frozenNoteId = getIntent().getExtras().getString("frozenNoteId");

        fullFrozenNoteViewModel.setFrozenNoteId(frozenNoteId);

        fullFrozenNoteViewModel.fetchData();

        binding.setFullFrozenNoteVM(fullFrozenNoteViewModel);
        binding.setLifecycleOwner(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.menubutton_plain);

        drawerLayout = findViewById(R.id.drawer_layout);

        SharedPreferences sharedPreferences = getSharedPreferences("edu.kit.pse.fridget.client_preferences", MODE_PRIVATE);
        String flatshareName = sharedPreferences.getString("flatshareName", "N/A");
        NavHeaderBinding _bind = DataBindingUtil.inflate(getLayoutInflater(), R.layout.nav_header, binding
                .navView, false);
        binding.navView.addHeaderView(_bind.getRoot());
        _bind.flatsharename.setText(flatshareName);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
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
}


