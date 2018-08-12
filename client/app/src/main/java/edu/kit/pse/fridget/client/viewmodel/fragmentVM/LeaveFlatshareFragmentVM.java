package edu.kit.pse.fridget.client.viewmodel.fragmentVM;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import edu.kit.pse.fridget.client.activity.HomeActivity;
import edu.kit.pse.fridget.client.activity.StartActivity;
import edu.kit.pse.fridget.client.service.MembershipService;
import edu.kit.pse.fridget.client.service.RetrofitClientInstance;
import edu.kit.pse.fridget.client.viewmodel.SharedPreferencesData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveFlatshareFragmentVM {
    private static final String TAG = LeaveFlatshareFragmentVM.class.getSimpleName();
    private SharedPreferencesData sharedPreferencesData =new SharedPreferencesData();

    public void deleteMembership(View v) {
        String userId =sharedPreferencesData.getSharedPreferencesData("ownMemberId",v);
        String flatshareId = sharedPreferencesData.getSharedPreferencesData("flatshareId", v);
        final Context context = v.getContext();
        Intent intent = new Intent(context, StartActivity.class);
        RetrofitClientInstance.getRetrofitInstance().create(MembershipService.class).deleteMember(flatshareId, userId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                SharedPreferences mySPrefs = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = mySPrefs.edit();
                editor.remove("ownMemberId");
                editor.remove("flatshareId");
                editor.remove("flatshareName");
                editor.remove("ownMagnetColor");
                editor.apply();
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

    public void onBackButtonClicked(View v) {
        Context context = v.getContext();
        Intent i = new Intent(context, HomeActivity.class);
        context.startActivity(i);
    }
}
