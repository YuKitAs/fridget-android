package edu.kit.pse.fridget.client.service;

import java.util.List;

import edu.kit.pse.fridget.client.datamodel.FrozenNote;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FrozenNoteService {

    /**
     * Diese Klasse ist für die synchronisation der FrozenNotes mit dem Server zuständig
     */


    //Diese Methoderuft die FrozenNotes vom Server ab
    @GET("/frozen-notes")
    Call<List<FrozenNote>> getAllFrozenNote(@Query("flatshare") String flatshareId);

    //Diese Methode ruft die Großansicht einer FrozenNote ab, bzw den Inhalt
    @GET("/frozen-notes/{id}")
    Call<FrozenNote> getFrozenNote(@Path("id") String frozenNoteId);

    //Diese Methode speichert Änderungen  in einer FrozenNote
    @Headers("Content-Type: application/json")
    @PUT("/frozen-notes/{id}")
    Call<FrozenNote> updateFrozenNote(@Path("id") String frozenNoteId, @Body FrozenNote frozenNote);
}
