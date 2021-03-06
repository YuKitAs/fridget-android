package edu.kit.pse.fridget.client.viewmodel.fragmentVM;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.util.List;

import edu.kit.pse.fridget.client.activity.HomeActivity;
import edu.kit.pse.fridget.client.datamodel.representation.UserMembershipRepresentation;
import edu.kit.pse.fridget.client.service.MembershipService;
import edu.kit.pse.fridget.client.service.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberListFragmentVM extends ViewModel {

    private static final String TAG = MemberListFragmentVM.class.getSimpleName();
    private static final int NUM_OF_MEMBERS = 15;


    public MutableLiveData<String[]> liveDataNameList = new MutableLiveData<String[]>();
    public MutableLiveData<Integer[]> liveDataMagnetList = new MutableLiveData<Integer[]>();
    public MutableLiveData<Boolean[]> liveDataVisibilityList = new MutableLiveData<Boolean[]>();

    private Boolean[] visibilityList = new Boolean[NUM_OF_MEMBERS];
    private String[] memberNameList = new String[NUM_OF_MEMBERS];
    private Integer[] magnetColorList = new Integer[NUM_OF_MEMBERS];

    private String flatshareId;

    public MemberListFragmentVM() {
        resetLists();

    }


    public void setFlatshareId(String f) {
        this.flatshareId = f;
    }

    public void fetchData(){
        fetchMemberList();
    }

    public void updateView(){
        Log.i(TAG, "updateLists");

        liveDataNameList.setValue(this.memberNameList);
        liveDataMagnetList.setValue(this.magnetColorList);
        liveDataVisibilityList.setValue(this.visibilityList);
    }


    public void fetchMemberList() {
        RetrofitClientInstance.getRetrofitInstance().create(MembershipService.class).getMemberList(flatshareId).enqueue(new Callback<List<UserMembershipRepresentation>>() {
            @Override
            public void onResponse(Call<List<UserMembershipRepresentation>> call, Response<List<UserMembershipRepresentation>> response) {
                List<UserMembershipRepresentation> memList = response.body();
                resetLists();

                if (memList != null) {
                    Log.i(TAG, String.format("Member list fetched: %s", new Gson().toJson(memList)));

                    for (int n = 0; n < memList.size(); n++) {
                        memberNameList[n] = memList.get(n).getGoogleName();
                        visibilityList[n] = true;
                        magnetColorList[n] = Color.parseColor("#" + memList.get(n).getMagnetColor());
                    }

                    Log.i(TAG, String.format("Member Name list fetched: %s", new Gson().toJson(memberNameList)));
                    Log.i(TAG, String.format("Member Magnet Color list fetched: %s", new Gson().toJson(magnetColorList)));
                    Log.i(TAG, String.format("Member Magnet Color list fetched: %s", new Gson().toJson(visibilityList)));

                    updateView();

                } else {
                    Log.e(TAG, "There are no Member.");
                }
                updateView();
            }

            @Override
            public void onFailure(Call<List<UserMembershipRepresentation>> call, Throwable t) {
                Log.e(TAG, "Fetching member list failed.");
                t.printStackTrace();
            }
        });
    }

    private void resetLists(){
        memberNameList = new String[15];
        magnetColorList = new Integer[15];

        for (int f = 0;  f < memberNameList.length; f++) {
            memberNameList[f] = "";
        }

        for (int f = 0;  f < visibilityList.length; f++) {
            visibilityList[f] = false;
        }
        for (int f = 0;  f < magnetColorList.length; f++) {
            magnetColorList[f] = Color.parseColor("#ffffff");
        }
    }

    public void onBackButtonClicked(View v) {
        Context context = v.getContext();
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }
}
