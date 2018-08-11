package edu.kit.pse.fridget.client.activity;

import android.arch.lifecycle.ViewModelProviders;
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

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.databinding.HomeActivityBinding;
import edu.kit.pse.fridget.client.databinding.NavHeaderBinding;
import edu.kit.pse.fridget.client.fragment.CreateAccessCodeFragment;
import edu.kit.pse.fridget.client.fragment.LeaveFlatshareFragment;
import edu.kit.pse.fridget.client.fragment.MemberListFragment;

import edu.kit.pse.fridget.client.viewmodel.HomeViewModel;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = HomeActivity.class.getSimpleName();

    private HomeViewModel homeVM = new HomeViewModel();

    private DrawerLayout drawerLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Calling onCreate");
        HomeActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.home_activity);

        SharedPreferences sharedPreferences = getSharedPreferences("edu.kit.pse.fridget.client_preferences", MODE_PRIVATE);
        String flatshareName = sharedPreferences.getString("flatshareName", "N/A");
        String flatshareId = sharedPreferences.getString("flatshareId", "N/A");

        homeVM = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeVM.setFlatshareId("1");
        homeVM.updateLists();

        binding.setHomeVM(homeVM);

        binding.setLifecycleOwner(this);


        //Navigation Drawer
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.menubutton_plain);

        drawerLayout = findViewById(R.id.drawer_layout);

        NavHeaderBinding _bind = DataBindingUtil.inflate(getLayoutInflater(), R.layout.nav_header, binding
                .navView, false);
        binding.navView.addHeaderView(_bind.getRoot());
        _bind.flatsharename.setText(flatshareName);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        homeVM.updateLists();

        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
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

       if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.placeholder_frame, fragment);
            ft.commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
        homeVM.updateLists();


    }

    @Override
    protected void onPause() {
        Log.i(TAG, "Calling onPause");
        super.onPause();
        homeVM.updateLists();


    }

    @Override
    protected void onStop() {
        Log.i(TAG, "Calling onStop");
        super.onStop();
        homeVM.updateLists();

    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "Calling onDestroy");
        super.onDestroy();
    }


}
