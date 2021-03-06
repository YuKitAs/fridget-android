package edu.kit.pse.fridget.client.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.databinding.EditTextFrozenNoteActivityBinding;
import edu.kit.pse.fridget.client.databinding.NavHeaderBinding;
import edu.kit.pse.fridget.client.fragment.CreateAccessCodeFragment;
import edu.kit.pse.fridget.client.fragment.LeaveFlatshareFragment;
import edu.kit.pse.fridget.client.fragment.MemberListFragment;
import edu.kit.pse.fridget.client.viewmodel.EditTextFrozenNoteViewModel;

public class EditTextFrozenNoteActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = FullTextFrozenNoteActivity.class.getSimpleName();

    private DrawerLayout drawerLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Calling onCreate");
        EditTextFrozenNoteActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.edit_text_frozen_note_activity);

        final EditTextFrozenNoteViewModel editTextFrozenNoteViewModel = ViewModelProviders.of(this).get(EditTextFrozenNoteViewModel.class);

        SharedPreferences sharedPreferences = getSharedPreferences("edu.kit.pse.fridget.client_preferences", MODE_PRIVATE);

        String flatshareId = sharedPreferences.getString("flatshareId", "N/A");
        Intent i = getIntent();
        int position = i.getExtras().getInt("position");
        String frozenNoteId = i.getExtras().get("frozenNoteId").toString();

        editTextFrozenNoteViewModel.setFlatshareId(flatshareId);
        editTextFrozenNoteViewModel.setFrozenNoteId(frozenNoteId);
        editTextFrozenNoteViewModel.setPosition(position);
        editTextFrozenNoteViewModel.fetchData();

        binding.setFrozenNoteVM(editTextFrozenNoteViewModel);
        binding.setLifecycleOwner(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.menubutton_plain);

        drawerLayout = findViewById(R.id.drawer_layout);

        String flatshareName = sharedPreferences.getString("flatshareName", "N/A");
        NavHeaderBinding _bind = DataBindingUtil.inflate(getLayoutInflater(), R.layout.nav_header, binding
                .navView, false);
        binding.navView.addHeaderView(_bind.getRoot());
        _bind.flatsharename.setText(flatshareName);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.memlist) {
            fragment = new MemberListFragment();
        } else if (id == R.id.add_member) {
            fragment = new CreateAccessCodeFragment();
        } else if (id == R.id.leave_flatshare) {
            fragment = new LeaveFlatshareFragment();
        }

        //Übergeben von FlatshareId mit Bundle
        SharedPreferences sharedPreferences = getSharedPreferences("edu.kit.pse.fridget.client_preferences", MODE_PRIVATE);
        String fsId = sharedPreferences.getString("flatshareId", "N/A");
        Bundle bundle = new Bundle();
        bundle.putString("FlatshareId", fsId);

        if (fragment != null) {
            fragment.setArguments(bundle);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.placeholder_frame, fragment);
            setNoteInvisible();
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

    private void setNoteInvisible() {
        findViewById(R.id.rootRL).setVisibility(View.INVISIBLE);
    }

}