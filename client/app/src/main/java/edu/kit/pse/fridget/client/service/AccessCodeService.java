package edu.kit.pse.fridget.client.service;

import edu.kit.pse.fridget.client.datamodel.AccessCode;
import edu.kit.pse.fridget.client.datamodel.Flatshare;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AccessCodeService {

    /**
     * Diese Klasse verwaltet die Synchronisation des Access Code mit dem Server
     */


    //Diese Methode fordert den AccessCode einer Flatshare an
    @Headers("Content-Type: application/json")
    @POST("/access-codes")
    Call<AccessCode> generateAccessCode(@Body Flatshare flatshare);
}
