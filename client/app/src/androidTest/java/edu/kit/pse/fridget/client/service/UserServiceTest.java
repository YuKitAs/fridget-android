package edu.kit.pse.fridget.client.service;

import android.test.InstrumentationTestCase;

import com.google.gson.Gson;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import edu.kit.pse.fridget.client.datamodel.AccessCode;
import edu.kit.pse.fridget.client.datamodel.User;
import edu.kit.pse.fridget.client.datamodel.representation.UserMembershipRepresentation;
import edu.kit.pse.fridget.client.datamodel.representation.UserWithJwtRepresentation;
import edu.kit.pse.fridget.client.service.MembershipService;
import edu.kit.pse.fridget.client.service.UserService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

import static org.junit.Assert.*;

public class UserServiceTest extends InstrumentationTestCase {


    private MockRetrofit mockRetrofit;
    String idToken ="testIdToken";
    private Retrofit retrofit;

    @Override
    public void setUp() throws Exception{
        super.setUp();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://test.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NetworkBehavior behavior = NetworkBehavior.create();

        mockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build();
    }

    @Test
    public void testGenerateAccesscode() throws Exception {
        BehaviorDelegate<UserService> delegate = mockRetrofit.create(UserService.class);
      UserService userService = new MockUserService(delegate);

        //Actual Test
        Call<UserWithJwtRepresentation> quote = userService.sendIdToken(idToken);
        Response<UserWithJwtRepresentation> response = quote.execute();

        //Asserting response
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals("testJWT", response.body().getJwt());
        Assert.assertEquals("testId", response.body().getUser().getId());
        Assert.assertEquals("testGoogleName", response.body().getUser().getGoogleName());
        Assert.assertEquals("testGoogleUserId", response.body().getUser().getGoogleUserId());

    }




}