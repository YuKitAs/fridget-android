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
import edu.kit.pse.fridget.client.service.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CreateFlatshareViewModel extends ViewModel {

        //Methode zum senden von dem Namen der Flatshare an den Server
    public void createFlatshare(CreateFlatshareCommand createFlatshareCommand) {
        //create Retrofit instance
        RetrofitClientInstance.getRetrofitInstance().create(FlatshareService.class).createFlatshare(createFlatshareCommand).enqueue(new Callback<Flatshare>(){
            @Override
            public void onResponse(Call<Flatshare> call, Response<Flatshare> response) {
                Log.i("createFlatshare", String.format("Created Flatshare %s.", new Gson().toJson(response.body())));
            }

            @Override
            public void onFailure(Call<Flatshare> call, Throwable t) {
                Log.e("createFlatshare", "Creating flatshare failed.");
                t.printStackTrace();
            }

        });
    }

    //Methode um Flatshare Daten vom Server zu holen
    public void getFlatshare(String flatshareId) {
        //create Retrofit instance
        RetrofitClientInstance.getRetrofitInstance().create(FlatshareService.class).getFlatshare(flatshareId).enqueue(new Callback<Flatshare>(){
            @Override
            public void onResponse(Call<Flatshare> call, Response<Flatshare> response) {
                Log.i("getFlatshare", String.format("Get flatsharename successful", new Gson().toJson(response.body())));
            }

            @Override
            public void onFailure(Call<Flatshare> call, Throwable t) {
                Log.e("getFlatshare", "Get flatsharename failed.");
                t.printStackTrace();
            }

        });
    }

}
