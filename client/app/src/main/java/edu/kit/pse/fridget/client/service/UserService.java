package edu.kit.pse.fridget.client.service;

import edu.kit.pse.fridget.client.datamodel.representation.UserWithJwtRepresentation;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserService {

    /**
     * Diese Klasse dient dazu, dem Nutzer zu ermöglichen sein bestehendes Google Account zum Login
     * zu verwenden.
     */

    @FormUrlEncoded
    @POST("/users/sign-in")
    Call<UserWithJwtRepresentation> sendIdToken(@Field("idToken") String idToken);
}
