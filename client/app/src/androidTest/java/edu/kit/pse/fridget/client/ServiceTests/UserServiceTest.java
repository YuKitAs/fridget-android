package edu.kit.pse.fridget.client.ServiceTests;

import com.google.gson.Gson;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import edu.kit.pse.fridget.client.datamodel.User;
import edu.kit.pse.fridget.client.datamodel.representation.UserMembershipRepresentation;
import edu.kit.pse.fridget.client.datamodel.representation.UserWithJwtRepresentation;
import edu.kit.pse.fridget.client.service.MembershipService;
import edu.kit.pse.fridget.client.service.UserService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;

public class UserServiceTest {

    User testUser =new User("testId","testUserId","testGoogleName");
    UserWithJwtRepresentation userWithJwtRepresentation = new UserWithJwtRepresentation(testUser,"testjwt");
    String testIdToken ="abc";
    MockWebServer server = new MockWebServer();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(server.url("").toString())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    UserService service = retrofit.create(UserService.class);
    @Test
    public void sendIdToken() throws IOException {

        server.enqueue(new MockResponse().setBody(new Gson().toJson(testUser)));
        Call<UserWithJwtRepresentation> call = service.sendIdToken(testIdToken);
        assertTrue(call.execute() != null);
        server.shutdown();
    }
}