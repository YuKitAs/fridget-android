package edu.kit.pse.fridget.client.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.List;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.datamodel.representation.UserMembershipRepresentation;
import edu.kit.pse.fridget.client.service.MembershipService;
import edu.kit.pse.fridget.client.service.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MemberListFragment extends Fragment {

    private static final String TAG = MemberListFragment.class.getSimpleName();
    String flatshareID = null;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_member_list, container, false);

    }


    public void updateLists() {
        getMemberList(flatshareID);
    }

    public void getMemberList(String flatshareId) {
        RetrofitClientInstance.getRetrofitInstance().create(MembershipService.class).getMemberList(flatshareId).enqueue(new Callback<List<UserMembershipRepresentation>>() {
            @Override
            public void onResponse(Call<List<UserMembershipRepresentation>> call, Response<List<UserMembershipRepresentation>> response) {
                List<UserMembershipRepresentation> body = response.body();
                if (body != null) {
                    Log.i(TAG, String.format("Member list fetched: %s", new Gson().toJson(body)));
                }
            }

            @Override
            public void onFailure(Call<List<UserMembershipRepresentation>> call, Throwable t) {
                Log.e(TAG, "Fetching member list failed.");
                t.printStackTrace();
            }
        });
    }

}