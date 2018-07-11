package edu.kit.pse.fridget.client.service;

import java.util.List;

import edu.kit.pse.fridget.client.datamodel.ImageNote;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ImageNoteService {

    /**
     * Diese Klasse dient zur Synchronisation der ImageCoolNotes mit dem Server
     */

    //Diese Methode ruft die ImageCoolNotes vom server ab
    @GET("/image-notes")
    Call<List<ImageNote>> getAllImageNotes(@Query("flatshare") String flatshareId);

    //Diese Methode ruft eine Image-Cool-Note ab
    @GET("/image-notes/{id}")
    Call<ImageNote> getImageNote(@Path("id") String imageNoteId);

    //Diese Methode schickt ein ImageNote an den Server/ speichert ein ImageNote
    @Headers("Content-Type: application/json")
    @POST("/image-notes")
    Call<ImageNote> createImageNote(@Body ImageNote imageNote);

    //Diese Methode löscht eine ImageNote
    @DELETE("/image-notes/{id}")
    Call<Void> deleteImageNote(@Path("id") String ImageNoteId);
}
