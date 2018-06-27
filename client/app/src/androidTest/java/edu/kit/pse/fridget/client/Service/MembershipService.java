package edu.kit.pse.fridget.client.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MembershipService {

    /**
     * Diese Klasse verwaltet die Synchronisation der Members mit dem Server
     */


    //Ruft die Mitglieder einer Flatshare ab
    @Headers("Authorization:{JWT}")
    @GET("/memberships/users?flatshare={id}")
    Call<List<Membership>> getMemberList(@Path("flatshareID") String flatshareID);

    //Ruft einen Benutzer und seine Magnetfarbe ab
    @Headers("Authorization:{JWT}")
    @GET("memberships?flatshare={fid}&user ={uid}")
    Call<Membership> getUser(@Path("fid") String flatshareId,
                             @Path("uid") String userid);

    //Fügt einen neues Member in bestehende flatshare hinzu
    @Headers({"Content-Type: application/json",
            "Authorization:{JWT}"})
    @POST("/memberships")
    Call<Membership> createMembership(@Body User user);

    //Löscht ein member aus einer flatshare
    @Headers("Authorization:{JWT}")
    @DELETE("/memberships?flatshare={fid}&user={uid}")
    Call<Membership> deleteMember(@Path("fid") String flatshareId,
                                  @Path("uid") String userId);



}


