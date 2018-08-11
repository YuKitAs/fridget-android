package edu.kit.pse.fridget.client.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.databinding.FullTextCoolNoteActivityBinding;
import edu.kit.pse.fridget.client.viewmodel.FullCoolNoteViewModel;

public class FullTextCoolNoteActivity extends AppCompatActivity {

    private static final String TAG = FullTextCoolNoteActivity.class.getSimpleName();

    private DrawerLayout drawerLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Calling onCreate");
        FullTextCoolNoteActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.full_text_cool_note_activity);
        Intent i = getIntent();
        final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);

        //viewmodels
        final FullCoolNoteViewModel fullCoolNoteViewModel = ViewModelProviders.of(this).get(FullCoolNoteViewModel.class);
        binding.setFullNoteVM(fullCoolNoteViewModel);
        SharedPreferences sharedPreferences = getSharedPreferences("edu.kit.pse.fridget.client_preferences",MODE_PRIVATE);
        String flatshareId = sharedPreferences.getString("flatshareId", "N/A");
        String ownMemberId = sharedPreferences.getString("ownMemberId", "N/A");
        String ownMagnetColor = sharedPreferences.getString("ownMagnetColor", "N/A");

        fullCoolNoteViewModel.setFlatshareId(flatshareId);
        fullCoolNoteViewModel.setOwnMemberId(ownMemberId);
        fullCoolNoteViewModel.setMagnetColor(ownMagnetColor);

        String coolNoteId = i.getExtras().get("coolNoteId").toString();
        fullCoolNoteViewModel.getCoolNote(coolNoteId, viewGroup);
        fullCoolNoteViewModel.getMemberList(viewGroup);
        fullCoolNoteViewModel.readConfirmation();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.menubutton_plain);

        drawerLayout = findViewById(R.id.drawer_layout);
        ImageButton menuButton = findViewById(R.id.menu_button);

        menuButton.setOnClickListener((v) -> {
            drawerLayout.openDrawer(GravityCompat.START);
            menuButton.setVisibility(View.INVISIBLE);
        });

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
        FullTextCoolNoteActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.full_text_cool_note_activity);
        Intent i = getIntent();
        final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);

        //viewmodels
        final FullCoolNoteViewModel fullCoolNoteViewModel = ViewModelProviders.of(this).get(FullCoolNoteViewModel.class);
        binding.setFullNoteVM(fullCoolNoteViewModel);
        SharedPreferences sharedPreferences = getSharedPreferences("edu.kit.pse.fridget.client_preferences",MODE_PRIVATE);
        String flatshareId = sharedPreferences.getString("flatshareId", "N/A");
        String ownMemberId = sharedPreferences.getString("ownMemberId", "N/A");
        String ownMagnetColor = sharedPreferences.getString("ownMagnetColor", "N/A");

        fullCoolNoteViewModel.setFlatshareId(flatshareId);
        fullCoolNoteViewModel.setOwnMemberId(ownMemberId);
        fullCoolNoteViewModel.setMagnetColor(ownMagnetColor);

        String coolNoteId = i.getExtras().get("coolNoteId").toString();
        fullCoolNoteViewModel.getCoolNote(coolNoteId, viewGroup);
        fullCoolNoteViewModel.getMemberList(viewGroup);
        fullCoolNoteViewModel.readConfirmation();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Calling onResume");
        FullTextCoolNoteActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.full_text_cool_note_activity);
        Intent i = getIntent();
        final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);

        //viewmodels
        final FullCoolNoteViewModel fullCoolNoteViewModel = ViewModelProviders.of(this).get(FullCoolNoteViewModel.class);
        binding.setFullNoteVM(fullCoolNoteViewModel);
        SharedPreferences sharedPreferences = getSharedPreferences("edu.kit.pse.fridget.client_preferences",MODE_PRIVATE);
        String flatshareId = sharedPreferences.getString("flatshareId", "N/A");
        String ownMemberId = sharedPreferences.getString("ownMemberId", "N/A");
        String ownMagnetColor = sharedPreferences.getString("ownMagnetColor", "N/A");

        fullCoolNoteViewModel.setFlatshareId(flatshareId);
        fullCoolNoteViewModel.setOwnMemberId(ownMemberId);
        fullCoolNoteViewModel.setMagnetColor(ownMagnetColor);

        String coolNoteId = i.getExtras().get("coolNoteId").toString();
        fullCoolNoteViewModel.getCoolNote(coolNoteId, viewGroup);
        fullCoolNoteViewModel.getMemberList(viewGroup);
        fullCoolNoteViewModel.readConfirmation();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "Calling onPause");
        FullTextCoolNoteActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.full_text_cool_note_activity);
        Intent i = getIntent();
        final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);

        //viewmodels
        final FullCoolNoteViewModel fullCoolNoteViewModel = ViewModelProviders.of(this).get(FullCoolNoteViewModel.class);
        binding.setFullNoteVM(fullCoolNoteViewModel);
        SharedPreferences sharedPreferences = getSharedPreferences("edu.kit.pse.fridget.client_preferences",MODE_PRIVATE);
        String flatshareId = sharedPreferences.getString("flatshareId", "N/A");
        String ownMemberId = sharedPreferences.getString("ownMemberId", "N/A");
        String ownMagnetColor = sharedPreferences.getString("ownMagnetColor", "N/A");

        fullCoolNoteViewModel.setFlatshareId(flatshareId);
        fullCoolNoteViewModel.setOwnMemberId(ownMemberId);
        fullCoolNoteViewModel.setMagnetColor(ownMagnetColor);

        String coolNoteId = i.getExtras().get("coolNoteId").toString();
        fullCoolNoteViewModel.getCoolNote(coolNoteId, viewGroup);
        fullCoolNoteViewModel.getMemberList(viewGroup);
        fullCoolNoteViewModel.readConfirmation();

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "Calling onStop");
        FullTextCoolNoteActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.full_text_cool_note_activity);
        Intent i = getIntent();
        final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);

        //viewmodels
        final FullCoolNoteViewModel fullCoolNoteViewModel = ViewModelProviders.of(this).get(FullCoolNoteViewModel.class);
        binding.setFullNoteVM(fullCoolNoteViewModel);
        SharedPreferences sharedPreferences = getSharedPreferences("edu.kit.pse.fridget.client_preferences",MODE_PRIVATE);
        String flatshareId = sharedPreferences.getString("flatshareId", "N/A");
        String ownMemberId = sharedPreferences.getString("ownMemberId", "N/A");
        String ownMagnetColor = sharedPreferences.getString("ownMagnetColor", "N/A");

        fullCoolNoteViewModel.setFlatshareId(flatshareId);
        fullCoolNoteViewModel.setOwnMemberId(ownMemberId);
        fullCoolNoteViewModel.setMagnetColor(ownMagnetColor);

        String coolNoteId = i.getExtras().get("coolNoteId").toString();
        fullCoolNoteViewModel.getCoolNote(coolNoteId, viewGroup);
        fullCoolNoteViewModel.getMemberList(viewGroup);
        fullCoolNoteViewModel.readConfirmation();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Calling onDestroy");
    }
}
