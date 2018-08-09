package edu.kit.pse.fridget.client.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import edu.kit.pse.fridget.client.activity.GetAccessCodeActivity;

public class GetAccessCodeViewModel extends ViewModel {
    public void onClickGetAccessCode(View v){
        Context context = v.getContext();
        Intent intent = new Intent(context, GetAccessCodeActivity.class);
        context.startActivity(intent);
    }
}
