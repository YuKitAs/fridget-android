package edu.kit.pse.fridget.client.activity;


import android.support.v7.app.AppCompatActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.view.GravityCompat;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.view.MenuItem;
import edu.kit.pse.fridget.client.R;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;
import android.view.MenuItem;

import edu.kit.pse.fridget.client.fragment.CreateAccessCodeFragment;
import edu.kit.pse.fridget.client.fragment.LeaveFlatshareFragment;
import edu.kit.pse.fridget.client.fragment.MemberListFragment;

public class MenuDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;

    public void openDrawer(){
        mDrawer.openDrawer(GravityCompat.START);
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        if (id == R.id.memlist) {
            fragment = new MemberListFragment();
        } else if (id == R.id.add_member) {
            fragment = new CreateAccessCodeFragment();
        } else if (id == R.id.leave) {
            fragment = new LeaveFlatshareFragment();
        }
        Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.flContent, fragment);
            transaction.commit();
            mDrawer.closeDrawers();
            return true;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigationView.setNavigationItemSelectedListener(this);
        setContentView(R.layout.menu_drawer_activity);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
