package edu.kit.pse.fridget.client.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReadConfirmationService {

    /**
     * Diese Klasse synchronisasiert den gelesen Status mit dem Server
     */


    //Diese Methode ruft den gelesen Status vom Server ab
    @Headers("Authorization:{JWT}")
    @GET("/read-confirmations/users?cool-note={id}")
    Call<List<CoolNote>> getReadStatus(@Path("id") String coolNoteId);

    //Diese Methode setzt die Checkbox auf markiert
    @Headers({"Content-Type: application/json",
            "Authorization:{JWT}"})
    @POST("/read-confirmations")
    Call<CoolNote> createReadStatus(@Body Readstatus readstatus);


    //Diese Methode demarkiert die Checkbox
    @Headers("Authorization:{JWT}")
    @DELETE("/read-confirmations?cool-note={cid}&user={uid}")
    Call<CoolNote> deleteReadStatus(@Path("cid") String coolNoteId,
                                    @Path("uid") String userID);


}


