package edu.kit.pse.fridget.client.Service;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AccessCodeService  {

    /**
     * Diese Klasse verwaltet die Synchronisation des Access Code mit dem Server
     */




    //Diese Methode fordert den AccessCode einer Flatshare an
    @Headers({"Content-Type: application/json",
            "Authorization:{JWT}"})
    @POST("/access-code")
    Call<AccessCode>  getAccessCode(String flatshareID);



}
