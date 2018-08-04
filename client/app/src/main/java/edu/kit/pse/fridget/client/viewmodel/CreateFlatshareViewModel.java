package edu.kit.pse.fridget.client.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import edu.kit.pse.fridget.client.activity.HomeActivity;
import edu.kit.pse.fridget.client.datamodel.Flatshare;
import edu.kit.pse.fridget.client.datamodel.command.CreateFlatshareCommand;
import edu.kit.pse.fridget.client.service.FlatshareService;
import edu.kit.pse.fridget.client.service.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CreateFlatshareViewModel extends ViewModel {


    public void changeView(View v) {
        Context context = v.getContext();
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);


    }
    public void makeToast (View v, String message) {
        Toast.makeText(v.getContext(), message, Toast.LENGTH_LONG).show();

    }

        //Methode zum senden von dem Namen der Flatshare an den Server
    public void createFlatshare(CreateFlatshareCommand createFlatshareCommand, View v, Context context) {

        //create Retrofit instance
        RetrofitClientInstance.getRetrofitInstance().create(FlatshareService.class).createFlatshare(createFlatshareCommand).enqueue(new Callback<Flatshare>(){
            @Override
            public void onResponse(Call<Flatshare> call, Response<Flatshare> response) {
                //Daten des Response speichern
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = sharedPref.edit();
                Flatshare body =response.body();

                if (body != null) {
                         Log.i("createFlatshare", String.format("Created Flatshare %s.", new Gson().toJson(response.body())));
                         String flatshareid =body.getId();
                         String flatshareName =body.getName();
                         editor.putString("flatshareId", flatshareid);
                         editor.putString("flatshareName", flatshareName);
                         editor.commit();
                         changeView(v);
                }
                else makeToast(v, "You are not an registred User");

            }
            @Override
            public void onFailure(Call<Flatshare> call, Throwable t) {
                Log.e("createFlatshare", "Creating flatshare failed.");
                makeToast(v,"Creating flatshare not possible");



            }

        });

    }

    //Methode um Flatshare Daten vom Server zu holen
    public void getFlatshare(String flatshareId,Context context) {
        //create Retrofit instance
        RetrofitClientInstance.getRetrofitInstance().create(FlatshareService.class).getFlatshare(flatshareId).enqueue(new Callback<Flatshare>(){
            @Override
            public void onResponse(Call<Flatshare> call, Response<Flatshare> response) {
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = sharedPref.edit();
                Flatshare body =response.body();
                if (body != null) {
                    Log.i("getFlatshare", String.format("Get flatsharename successful", new Gson().toJson(response.body())));
                    String flatshareName =body.getName();
                    editor.putString("flatshareName", flatshareName);
                    editor.commit();
                }
            }

            @Override
            public void onFailure(Call<Flatshare> call, Throwable t) {
                Log.e("getFlatshare", "Get flatsharename failed.");
                t.printStackTrace();
            }

        });
    }


}
