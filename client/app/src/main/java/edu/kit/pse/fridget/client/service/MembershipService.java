package edu.kit.pse.fridget.client.service;

import java.util.List;

import edu.kit.pse.fridget.client.datamodel.Member;
import edu.kit.pse.fridget.client.datamodel.User;
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
    Call<List<Member>> getMemberList(@Path("flatshareID") String flatshareID);

    //Ruft einen Benutzer und seine Magnetfarbe ab
    @Headers("Authorization:{JWT}")
    @GET("memberships?flatshare={fid}&user ={uid}")
    Call<Member> getUser(@Path("fid") String flatshareId,
                             @Path("uid") String userid);

    //Fügt einen neues Member in bestehende flatshare hinzu
    @Headers({"Content-Type: application/json",
            "Authorization:{JWT}"})
    @POST("/memberships")
    Call<Member> createMembership(@Body User user);

    //Löscht ein member aus einer flatshare
    @Headers("Authorization:{JWT}")
    @DELETE("/memberships?flatshare={fid}&user={uid}")
    Call<Member> deleteMember(@Path("fid") String flatshareId,
                                  @Path("uid") String userId);



}


