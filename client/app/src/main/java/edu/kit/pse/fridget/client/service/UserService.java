package edu.kit.pse.fridget.client.service;

import edu.kit.pse.fridget.client.datamodel.User;
import retrofit2.Call;
import retrofit2.http.POST;

public interface UserService {

    /**
     * Diese Klasse dient dazu, dem Nutzer zu ermöglichen sein bestehendes Google Account zum Login
     * zu verwenden. Dabei sendet der Client beim Login ein Token, welches er vom GoogleServer
     * erhält. Dort wird der Token an den GoogleServer gesendet, dadurch erhält der Server die
     * Google Client Id. Diese wird dauerhaft gespeichert.
     */



    //Diese Methode sendet den GoogleIdToken an den Server
    @POST("/users")
    Call<User> sendGoogleIdToken(String googleIdToken);



}
