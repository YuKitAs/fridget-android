package edu.kit.pse.fridget.client.viewmodel.fragmentVM;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import edu.kit.pse.fridget.client.activity.HomeActivity;
import edu.kit.pse.fridget.client.datamodel.AccessCode;
import edu.kit.pse.fridget.client.datamodel.command.GenerateAccessCodeCommand;
import edu.kit.pse.fridget.client.service.AccessCodeService;
import edu.kit.pse.fridget.client.service.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class CreateAccessCodeFragmentVM {

        public final MutableLiveData<String> accesscode = new MutableLiveData<>();
        private AccessCode code;

        public void update(String flatshareId) {
            getAccessCode(flatshareId);
        }

        private void getAccessCode(String flatshareId) {
            RetrofitClientInstance.getRetrofitInstance().create(AccessCodeService.class).generateAccessCode(new GenerateAccessCodeCommand(flatshareId)).enqueue(new Callback<AccessCode>() {
                @Override
                public void onResponse(Call<AccessCode> call, Response<AccessCode> response) {
                    code = response.body();
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
