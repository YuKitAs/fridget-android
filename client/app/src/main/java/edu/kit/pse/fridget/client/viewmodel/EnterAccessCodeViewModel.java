package edu.kit.pse.fridget.client.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import edu.kit.pse.fridget.client.activity.HomeActivity;
import edu.kit.pse.fridget.client.datamodel.Flatshare;
import edu.kit.pse.fridget.client.datamodel.Member;
import edu.kit.pse.fridget.client.datamodel.command.CreateFlatshareCommand;
import edu.kit.pse.fridget.client.datamodel.command.EnterFlatshareCommand;
import edu.kit.pse.fridget.client.service.AccessCodeService;
import edu.kit.pse.fridget.client.service.FlatshareService;
import edu.kit.pse.fridget.client.service.MembershipService;
import edu.kit.pse.fridget.client.service.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EnterAccessCodeViewModel extends ViewModel {

    public void changeView(View v) {
        Context context = v.getContext();
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }

    public void makeToast (View v, String message) {
        Toast.makeText(v.getContext(), message, Toast.LENGTH_LONG).show();
    }


    public void createMembership(EnterFlatshareCommand enterFlatshareCommand, View v) {

        //create Retrofit instance
        RetrofitClientInstance.getRetrofitInstance().create(MembershipService.class).createMembership(enterFlatshareCommand).enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                Member body =response.body();
                if (body != null) {
                    Log.i("createMember", String.format("Created Member %s.", new Gson().toJson(response.body())));
                    changeView(v);
                }
                else makeToast(v, "AccessCode not valid!");
            }

            @Override
            public void onFailure(Call<Member> call, Throwable t) {
                Log.e("createMember", "Creating member failed.");
                makeToast(v,"Creating member not possible");

            }
        });


    }



}
