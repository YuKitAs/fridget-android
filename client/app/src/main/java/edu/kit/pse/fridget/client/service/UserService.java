package edu.kit.pse.fridget.client.service;

import edu.kit.pse.fridget.client.datamodel.User;
import edu.kit.pse.fridget.client.datamodel.representation.UserWithJwtRepresentation;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserService {

    /**
     * Diese Klasse dient dazu, dem Nutzer zu ermöglichen sein bestehendes Google Account zum Login
     * zu verwenden.
     */

    @Headers("Content-Type: application/json")
    @POST("/users")
    Call<UserWithJwtRepresentation> sendUser(@Body User user);
}
