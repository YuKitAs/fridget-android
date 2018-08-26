package edu.kit.pse.fridget.client.service;

import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.test.InstrumentationTestCase;

import com.google.gson.Gson;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import edu.kit.pse.fridget.client.activity.CreateFlatshareActivity;
import edu.kit.pse.fridget.client.activity.HomeActivity;
import edu.kit.pse.fridget.client.datamodel.Flatshare;
import edu.kit.pse.fridget.client.datamodel.command.CreateFlatshareCommand;
import edu.kit.pse.fridget.client.service.FlatshareService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static junit.framework.Assert.assertTrue;


/**
 * Diese Klasse testet die Flatshareservice Methoden mit einem MockWebServer
 */
public class FlatshareServiceTest  {


    private MockRetrofit mockRetrofit;
    private Retrofit retrofit;

    CreateFlatshareCommand createFlatshareCommand =new CreateFlatshareCommand("testName","testUserId");

    @Before
    public void setUp() {

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
    public void createFlatshare() throws Exception{
        BehaviorDelegate<FlatshareService> delegate =mockRetrofit.create(FlatshareService.class);
        FlatshareService flatshareService =new MockFlatshareService(delegate);

        //ActualTest
        Call<Flatshare> quote =flatshareService.createFlatshare(createFlatshareCommand);
        Response<Flatshare> response = quote.execute();

        //Asserting response
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals("testId", response.body().getId());
        Assert.assertEquals("testName", response.body().getName());


    }


    @Test
    public void getFlatshare() throws Exception{
        String flatshareId ="testId";
        BehaviorDelegate<FlatshareService> delegate =mockRetrofit.create(FlatshareService.class);
        FlatshareService flatshareService =new MockFlatshareService(delegate);

        //ActualTest
        Call<Flatshare> quote =flatshareService.getFlatshare(flatshareId);
        Response<Flatshare> response = quote.execute();

        //Asserting response
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals("testId", response.body().getId());
        Assert.assertEquals("testName", response.body().getName());


    }


}
