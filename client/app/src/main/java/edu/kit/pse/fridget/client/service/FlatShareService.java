package edu.kit.pse.fridget.client.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FlatShareService {
    /**
     * Diese Klasse verwaltet die Synchronisation der Flatshare mit dem
     Server
     */

    //Erstellt neue Flatshare auf dem Server
    @Headers({"Content-Type: application/json",
            "Authorization:{JWT}"})
    @POST ("/flatshares")
    Call<Flatshare> createFlatshare(@Body Flatshare flatshare, String userId);

    //ruft Flatshare Daten vom Server ab
    @Headers("Authorization:{JWT}")
    @GET("/flatshares/{id}")
    Call<Flatshare> getFlatshare(@Path("id") String flatshareId);

}
