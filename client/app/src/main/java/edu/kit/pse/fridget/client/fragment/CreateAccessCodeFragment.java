package edu.kit.pse.fridget.client.fragment;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.activity.EnterAccessCodeActivity;
import edu.kit.pse.fridget.client.activity.HomeActivity;
import edu.kit.pse.fridget.client.databinding.FragmentCreateAccessCodeBinding;
import edu.kit.pse.fridget.client.datamodel.AccessCode;
import edu.kit.pse.fridget.client.datamodel.command.GenerateAccessCodeCommand;
import edu.kit.pse.fridget.client.service.AccessCodeService;
import edu.kit.pse.fridget.client.service.RetrofitClientInstance;
import edu.kit.pse.fridget.client.viewmodel.CreateAccessCode;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CreateAccessCodeFragment extends Fragment {


    private final MutableLiveData<String> accesscode = new MutableLiveData<>();

    public MutableLiveData<String> getAccesscode() {
        return accesscode;
    }






    private String flatshareId;


    private static final String TAG = CreateAccessCodeFragment.class.getSimpleName();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "Calling onCreateView");
        // Inflate the layout for this fragment

        FragmentCreateAccessCodeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_access_code, container, false);
        binding.setCreateAccesscodeFR(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.i(TAG, "Calling onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        this.flatshareId = getArguments().getString("FlatshareId");
        getAccessCode(flatshareId);
    }

    public void getAccessCode(String flatshareid) {
        RetrofitClientInstance.getRetrofitInstance().create(AccessCodeService.class).generateAccessCode(new GenerateAccessCodeCommand(flatshareid)).enqueue(new Callback<AccessCode>() {
            @Override
            public void onResponse(Call<AccessCode> call, Response<AccessCode> response) {
                AccessCode code = response.body();
                if (response.body() != null) {
                    Log.i(TAG, String.format("Access Code generated: %s", new Gson().toJson(code)));

                    accesscode.setValue(code.getContent());
                } else Log.e(TAG, "Connection to Server did not work");
            }

            @Override
            public void onFailure(Call<AccessCode> call, Throwable t) {
                Log.e(TAG, "Getting access code failed.");
                t.printStackTrace();
            }
        });
    }

    public void onClickOK(View v) {
        Context context = v.getContext();
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);

    }

}





