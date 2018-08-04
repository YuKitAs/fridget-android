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
        context.startActivity(intent);}


    public void makeToast (View v, String message) {
        Toast.makeText(v.getContext(), message, Toast.LENGTH_LONG).show();
    }


    public void createMembership(EnterFlatshareCommand enterFlatshareCommand, View v,Context context) {

        //create Retrofit instance
        RetrofitClientInstance.getRetrofitInstance().create(MembershipService.class).createMembership(enterFlatshareCommand).enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                Member body =response.body();
                //Daten des Response speichern
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = sharedPref.edit();
                if (body != null) {
                    Log.i("createMember", String.format("Created Member %s.", new Gson().toJson(response.body())));
                    String flatshareid =body.getFlatshareId();
                    String ownMemberId =body.getId();
                    String ownmagnetColor =body.getMagnetColor();

                    editor.putString("flatshareId", flatshareid);
                    editor.putString("ownMemberId", ownMemberId);
                    editor.putString("ownMagnetColor", ownmagnetColor);
                    editor.commit();
                    changeView(v);
                }
                else makeToast(v, "AccessCode not valid!");
            }

            @Override
            public void onFailure(Call<Member> call, Throwable t) {
                Log.e("createMember", "Creating member failed.");
                makeToast(v,"Database Connection failed");

            }
        });



    }


        //Ruft die  eigene Magnetfarbe und eigene MemberID vom Server ab
    public void getMembership(String flatshareId,String ownUserId,Context context) {

        //create Retrofit instance
        RetrofitClientInstance.getRetrofitInstance().create(MembershipService.class).getMember(flatshareId,ownUserId).enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                Member body =response.body();
                //Daten des Response speichern
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = sharedPref.edit();
                if (body != null) {
                    Log.i("getMember", String.format("Created Member %s.", new Gson().toJson(response.body())));
                    String ownMemberId =body.getId();
                    String ownmagnetColor =body.getMagnetColor();

                    editor.putString("ownMemberId", ownMemberId);
                    editor.putString("ownMagnetColor", ownmagnetColor);

                    editor.commit();

                } else Log.e("getMember", "Get Member failed!");

            }

            @Override
            public void onFailure(Call<Member> call, Throwable t) {
                Log.e("getMember", "Connection to Database failed!");


            }
        });



    }






}
