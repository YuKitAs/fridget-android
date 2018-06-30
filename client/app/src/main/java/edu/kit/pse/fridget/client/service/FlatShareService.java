package edu.kit.pse.fridget.client.service;

import edu.kit.pse.fridget.client.datamodel.FlatShare;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FlatShareService {
    /**
     * Diese Klasse verwaltet die Synchronisation der FlatShare mit dem
     Server
     */

    //Erstellt neue FlatShare auf dem Server
    @Headers({"Content-Type: application/json",
            "Authorization:{JWT}"})
    @POST ("/flatshares")
    Call<FlatShare> createFlatShare(@Body FlatShare flatShare, String userId);

    //ruft FlatShare Daten vom Server ab
    @Headers("Authorization:{JWT}")
    @GET("/flatshares/{id}")
    Call<FlatShare> getFlatShare(@Path("id") String flatShareId);

}
