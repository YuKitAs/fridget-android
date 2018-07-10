package edu.kit.pse.fridget.client.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import edu.kit.pse.fridget.client.activity.StartActivity;

public class CreateFlatshareViewModel extends ViewModel {

    public void onClickCreate(View v){

        Context context = v.getContext();
        Intent intent = new Intent(context, StartActivity.class);
        context.startActivity(intent);

    }
}
