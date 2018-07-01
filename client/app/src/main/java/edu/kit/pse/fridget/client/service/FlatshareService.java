package edu.kit.pse.fridget.client.service;

import edu.kit.pse.fridget.client.datamodel.Flatshare;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FlatshareService {
    /**
     * Diese Klasse verwaltet die Synchronisation der FlatShare mit dem
     Server
     */

    //Erstellt neue FlatShare auf dem Server
    @Headers({"Content-Type: application/json",
            "Authorization:{JWT}"})
    @POST ("/flatshares")
    Call<Flatshare> createFlatShare(@Body Flatshare flatshare, String userId);

    //ruft FlatShare Daten vom Server ab
    @Headers("Authorization:{JWT}")
    @GET("/flatshares/{id}")
    Call<Flatshare> getFlatShare(@Path("id") String flatShareId);

}
