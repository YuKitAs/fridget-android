package edu.kit.pse.fridget.client.viewmodel.fragmentVM;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.util.List;

import edu.kit.pse.fridget.client.activity.HomeActivity;
import edu.kit.pse.fridget.client.activity.StartActivity;
import edu.kit.pse.fridget.client.datamodel.representation.UserMembershipRepresentation;
import edu.kit.pse.fridget.client.service.MembershipService;
import edu.kit.pse.fridget.client.service.RetrofitClientInstance;
import edu.kit.pse.fridget.client.viewmodel.SharedPreferencesData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveFlatshareFragmentVM {
    private static final String TAG = LeaveFlatshareFragmentVM.class.getSimpleName();
    private SharedPreferencesData sharedPreferencesData =new SharedPreferencesData();

    private String flatshareId;

    public LeaveFlatshareFragmentVM() {
        getMemberList(flatshareId);
    }

    public void setFlatshareId(String flat) {
        this.flatshareId = flat;
    }

    public void deleteMembership(View v) {
        String flatshareId =sharedPreferencesData.getSharedPreferencesData("flatshareId",v);
        String userId =sharedPreferencesData.getSharedPreferencesData("ownMemberId",v);
        final Context context = v.getContext();
        Intent intent = new Intent(context, StartActivity.class);
        RetrofitClientInstance.getRetrofitInstance().create(MembershipService.class).deleteMember(flatshareId, userId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i(TAG, "Member has been deleted.");
                context.startActivity(intent);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "Deleting Member failed.");
                t.printStackTrace();
            }
        });
    }

    public void getMemberList(String flatshareId) {
        RetrofitClientInstance.getRetrofitInstance().create(MembershipService.class).getMemberList(flatshareId).enqueue(new Callback<List<UserMembershipRepresentation>>() {
            @Override
            public void onResponse(Call<List<UserMembershipRepresentation>> call, Response<List<UserMembershipRepresentation>> response) {
                List<UserMembershipRepresentation> memList = response.body();
                if (memList != null) {
                    Log.i(TAG, String.format("Member list fetched: %s", new Gson().toJson(memList)));

                } else {
                    Log.e(TAG, "There are no Member.");

                }
            }

            @Override
            public void onFailure(Call<List<UserMembershipRepresentation>> call, Throwable t) {
                Log.e(TAG, "Fetching member list failed.");
                t.printStackTrace();
            }
        });
    }

    public void onBackButtonClicked(View v) {
        Context context = v.getContext();
        Intent i = new Intent(context, HomeActivity.class);
        context.startActivity(i);
    }
}
