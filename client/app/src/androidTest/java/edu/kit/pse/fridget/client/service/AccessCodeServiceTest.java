package edu.kit.pse.fridget.client.service;

import android.app.Instrumentation;
import android.test.InstrumentationTestCase;

import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import edu.kit.pse.fridget.client.datamodel.AccessCode;
import edu.kit.pse.fridget.client.datamodel.Device;
import edu.kit.pse.fridget.client.datamodel.command.GenerateAccessCodeCommand;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

import static org.junit.Assert.*;

public class AccessCodeServiceTest extends InstrumentationTestCase {


   private MockRetrofit mockRetrofit;
   GenerateAccessCodeCommand generateAccessCodeCommand = new GenerateAccessCodeCommand("test");

  public Retrofit retrofit;


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
        BehaviorDelegate<AccessCodeService> delegate = mockRetrofit.create(AccessCodeService.class);
      AccessCodeService accessCodeService = new MockAccessCodeService(delegate);

        //Actual Test
        Call<AccessCode> quote = accessCodeService.generateAccessCode(generateAccessCodeCommand);
        Response<AccessCode> response = quote.execute();

        //Asserting response
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals("testContent", response.body().getContent());
        Assert.assertEquals("testId", response.body().getId());
        Assert.assertEquals("flatshareId", response.body().getFlatshareId());

    }


   }





