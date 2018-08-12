package edu.kit.pse.fridget.client.service;

import java.util.List;

import edu.kit.pse.fridget.client.datamodel.Member;
import edu.kit.pse.fridget.client.datamodel.ReadConfirmation;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ReadConfirmationService {

    /**
     * Diese Klasse synchronisasiert den gelesen Status mit dem Server
     */


    //Diese Methode ruft den gelesen Status vom Server ab
    @GET("/read-confirmations/users")
    Call<List<Member>> getReaders(@Query("cool-note") String coolNoteId);

    //Diese Methode setzt die Checkbox auf markiert
    @Headers("Content-Type: application/json")
    @POST("/read-confirmations")
    Call<ReadConfirmation> createReadStatus(@Body ReadConfirmation readStatus);


    //Diese Methode demarkiert die Checkbox
    @DELETE("/read-confirmations")
    Call<Void> deleteReadStatus(@Query("cool-note") String coolNoteId,
                                @Query("membership") String membershipId);
}


