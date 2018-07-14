package edu.kit.pse.fridget.client.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.activity.CreateFlatshareActivity;
import edu.kit.pse.fridget.client.activity.StartActivity;
import edu.kit.pse.fridget.client.datamodel.Flatshare;
import edu.kit.pse.fridget.client.datamodel.command.CreateFlatshareCommand;
import edu.kit.pse.fridget.client.service.FlatshareService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CreateFlatshareViewModel extends ViewModel {


    // public void onClickCreate(View v){

    // sendNetworkRequest();

    //}
    private static Retrofit retrofit;


    public void sendNetworkRequest(CreateFlatshareCommand createFlatshareCommand) {
        //create Retrofit instance

        Retrofit.Builder builder =new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        //get client & call object for the request
        FlatshareService flatshareService =retrofit.create(FlatshareService.class);
        Call<Flatshare> call =flatshareService.createFlatshare(createFlatshareCommand);
        call.enqueue(new Callback<Flatshare>() {
            @Override
            public void onResponse(Call<Flatshare> call, Response<Flatshare> response) {
                Log.i("createFlatshare", String.format("Created Flatshare.", new Gson().toJson(response.body())));
            }

            @Override
            public void onFailure(Call<Flatshare> call, Throwable t) {
                Log.e("createFlatshare", "Ceating flatshare failed.");
                t.printStackTrace();
            }
        });
    }
}
