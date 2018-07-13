package edu.kit.pse.fridget.client.activity;

import android.arch.lifecycle.LifecycleOwner;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.databinding.HomeActivityBinding;

import edu.kit.pse.fridget.client.datamodel.CoolNote;
import edu.kit.pse.fridget.client.datamodel.FrozenNote;
import edu.kit.pse.fridget.client.datamodel.Member;
import edu.kit.pse.fridget.client.viewmodel.HomeViewModel;

public class HomeActivity extends AppCompatActivity implements LifecycleOwner {

    private static final String TAG = HomeActivity.class.getSimpleName();
    private HomeViewModel homeVM = new HomeViewModel();

    /*
    private  final Observer<CoolNote[]> cnListObserver = new Observer<CoolNote[]>() {
        @Override
        public void onChanged(@Nullable CoolNote[] coolNotes) {
            homeVM.updateLists();
        }
    };

    private  final Observer<FrozenNote[]> fnListObserver = new Observer<FrozenNote[]>() {
        @Override
        public void onChanged(@Nullable FrozenNote[] frozenNotes) {
            homeVM.updateLists();
        }
    };

    private  final Observer<Member[]> memberListObserver = new Observer<Member[]>() {
        @Override
        public void onChanged(@Nullable Member[] member) {
            homeVM.updateLists();
        }
    };
*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Calling onCreate");

        HomeActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.home_activity);

        homeVM = ViewModelProviders.of(this).get(HomeViewModel.class);
        binding.setHomeVM(homeVM);

        /*
        homeVM.liveDataCNList.observe(this,cnListObserver);
        homeVM.liveDataFNList.observe(this,fnListObserver);
        homeVM.liveDataMemberList.observe(this,memberListObserver); */



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

/*
    public void createCoolNoteView(View v){
        startActivity(new Intent(HomeActivity.this, CreateTextCoolNoteActivity.class));

    }

    // Problem: woher weiß FullTextCoolNoteActivity welche coolNote er öffnen soll..
   public void openFullCoolNote(View v) {
        int cNNumber = Integer.parseInt(v.getTag().toString());
        startActivity(new Intent(HomeActivity.this, FullTextCoolNoteActivity.class));
    } */


}
