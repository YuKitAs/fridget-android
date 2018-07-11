package edu.kit.pse.fridget.client.service;

import edu.kit.pse.fridget.client.datamodel.Flatshare;
import edu.kit.pse.fridget.client.datamodel.command.CreateFlatshareCommand;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FlatshareService {
    /**
     * Diese Klasse verwaltet die Synchronisation der FlatShare mit dem
     * Server
     */

    //Erstellt neue FlatShare auf dem Server
    @Headers("Content-Type: application/json")
    @POST("/flatshares")
    Call<Flatshare> createFlatshare(@Body CreateFlatshareCommand createFlatshareCommand);

    //ruft FlatShare Daten vom Server ab
    @GET("/flatshares/{id}")
    Call<Flatshare> getFlatshare(@Path("id") String flatshareId);
}
