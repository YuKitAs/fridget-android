package edu.kit.pse.fridget.client.service;

import java.util.List;

import edu.kit.pse.fridget.client.datamodel.Member;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TaggedMemberService {

    /**
     * Diese Klasse ruft die getaggten Members eier CoolNote ab
     */

    //Diese Methode ruft eine Liste der getaggten Members ab
    @GET("/tagged-members/users")
    Call<List<Member>> getTaggedUsers(@Query("cool-note") String coolNoteId);
}


