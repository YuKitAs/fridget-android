package edu.kit.pse.fridget.client.service;

import java.util.List;

import edu.kit.pse.fridget.client.datamodel.Member;
import edu.kit.pse.fridget.client.datamodel.command.EnterFlatshareCommand;
import edu.kit.pse.fridget.client.datamodel.representation.UserMembershipRepresentation;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MembershipService {

    /**
     * Diese Klasse verwaltet die Synchronisation der Members mit dem Server
     */


    //Ruft die Mitglieder einer Flatshare ab
    @GET("/memberships/users")
    Call<List<UserMembershipRepresentation>> getMemberList(@Query("flatshare") String flatshareId);

    //Ruft einen Benutzer und seine Magnetfarbe ab
    @GET("/memberships")
    Call<UserMembershipRepresentation> getMember(@Query("flatshare") String flatshareId,
                                                 @Query("user") String userId);

    //Fügt einen neues Member in bestehende flatshare hinzu
    @Headers("Content-Type: application/json")
    @POST("/memberships")
    Call<Member> createMembership(@Body EnterFlatshareCommand enterFlatshareCommand);

    //Löscht ein member aus einer flatshare
    @DELETE("/memberships")
    Call<Void> deleteMember(@Query("flatshare") String flatshareId,
                            @Query("user") String userId);
}


