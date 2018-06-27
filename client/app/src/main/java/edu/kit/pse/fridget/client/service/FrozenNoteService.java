package edu.kit.pse.fridget.client.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface FrozenNoteService {

    /**
     * Diese Klasse ist für die synchronisation der FrozenNotes mit dem Server zuständig
     */


    //Diese Methoderuft die FrozenNotes vom Server ab
    @Headers("Authorization:{JWT}")
    @GET("/frozen-notes?flatshare={id}")
    Call<List<FrozenNote>> getAllFrozenNote(@Path("id") String flatshareId);

    //Diese Methode ruft die Großansicht einer FrozenNote ab, bzw den Inhalt
    @Headers("Authorization:{JWT}")
    @GET("/frozen-notes/{id}")
    Call<FrozenNote> getFrozenNote(@Path("id") String frozenNoteId);

    //Diese Methode speichert Änderungen  in einer FrozenNote
    @Headers({"Content-Type: application/json",
            "Authorization:{JWT}"})
    @PUT("/frozen-notes/{id}")
    Call<FrozenNote> updateFrozenNote(@Path("id") String frozenNoteId, @Body FrozenNote frozenNote);

}
