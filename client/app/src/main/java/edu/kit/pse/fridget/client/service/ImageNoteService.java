package edu.kit.pse.fridget.client.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ImageNoteService {

    /**
     * Diese Klasse dient zur Synchronisation der ImageCoolNotes mit dem Server
     */

      //Diese Methode ruft die ImageCoolNotes vom server ab

    @Headers("Authorization:{JWT}")
    @GET("/image-notes?flatshare={id}")
    Call<List<ImageNote>> getAllImageNotes(@Path("id") String flatshareId) ;

    //Diese Methode ruft eine Image-Cool-Note ab
    @Headers("Authorization:{JWT}")
    @GET("/image-notes/{id}")
    Call<ImageNote> getImageNote(@Path("id") String ImageNoteId);

    //Diese Methode schickt ein ImageNote an den Server/ speichert ein ImageNote
    @Headers({"Content-Type: application/json",
            "Authorization:{JWT}"})
    @POST("/image-notes")
    Call<ImageNote> createImageNote(@Body ImageNote imageNote) ;

    //Diese Methode löscht eine ImageNote
    @Headers("Authorization:{JWT}")
    @DELETE("/image-notes/{id}")
    Call<ImageNote> deleteImageNote(@Path("id") String ImageNoteId);


}
