package edu.kit.pse.fridget.client.service;

import java.util.List;

import edu.kit.pse.fridget.client.datamodel.CoolNote;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CoolNoteService {

    /**
     * Diese Klasse isr für die Synchronisation der CoolNotes mit dem Server zuständig
     */


    //Diese Methode ruft alle CoolNotes ab
    @GET("/cool-notes")
    Call<List<CoolNote>> getAllCoolNotes(@Query("flatshare") String flatshareId);

    //Diese Methode ruft den Inhalt/Großansicht einer CoolNote ab
    @GET("/cool-notes/{id}")
    Call<CoolNote> getCoolNote(@Path("id") String coolNoteId);

    //Diese Methode sendet eine CoolNote an den Server bzw. speichert eine CoolNote
    @Headers("Content-Type: application/json")
    @POST("/cool-notes")
    Call<CoolNote> createCoolNote(@Body CoolNote coolNote);

    //Diese Methode löscht eine CoolNote
    @DELETE("/cool-notes/{id}")
    Call<Void> deleteCoolNote(@Path("id") String coolNoteId);
}
