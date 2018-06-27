package edu.kit.pse.fridget.client.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface TaggedMemberService {

    /**
     * Diese Klasse ruft die getaggten Members eier CoolNote ab
     */

    //Diese Methode ruft eine Liste der getaggten Members ab
    @Headers("Authorization:{JWT}")
    @GET("/tagged-members/users?cool-note={cid}")
    Call<List<CoolNote>> getTaggedUsers(@Path("cid") String coolNoteId);
}


