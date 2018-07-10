package edu.kit.pse.fridget.client.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import edu.kit.pse.fridget.client.activity.CreateFlatshareActivity;

import edu.kit.pse.fridget.client.activity.EnterAccessCodeActivity;

import static android.support.v4.content.ContextCompat.startActivity;

public class StartViewModel extends ViewModel {

    public void onClickRegisterFlatshare(View v){
        Context context = v.getContext();
        Intent intent = new Intent(context, CreateFlatshareActivity.class);
        context.startActivity(intent);
    }

    public void onClickEnterAccessCode(View v){
        Context context = v.getContext();
        Intent intent = new Intent(context, EnterAccessCodeActivity.class);
        context.startActivity(intent);

    }
}
