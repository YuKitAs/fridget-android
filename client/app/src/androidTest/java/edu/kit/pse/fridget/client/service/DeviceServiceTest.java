package edu.kit.pse.fridget.client.service;

import android.test.InstrumentationTestCase;

import com.google.gson.Gson;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import edu.kit.pse.fridget.client.datamodel.AccessCode;
import edu.kit.pse.fridget.client.datamodel.Device;
import edu.kit.pse.fridget.client.datamodel.representation.UserWithJwtRepresentation;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

import static org.junit.Assert.*;

public class DeviceServiceTest extends InstrumentationTestCase {

    private MockRetrofit mockRetrofit;
    Device device = new Device (null,"testUserId","testInstanceToken");
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
        BehaviorDelegate<DeviceService> delegate = mockRetrofit.create(DeviceService.class);
        DeviceService deviceService = new MockDeviceService(delegate);

        //Actual Test
        Call<Device> quote = deviceService.sendDevice(device);
        Response<Device> response = quote.execute();

        //Asserting response
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals("testId", response.body().getId());
        Assert.assertEquals("testInstanceToken", response.body().getInstanceIdToken());
        Assert.assertEquals("testUserId", response.body().getUserId());
    }


}


