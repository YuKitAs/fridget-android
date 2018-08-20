package edu.kit.pse.fridget.client.service;

import com.google.gson.Gson;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.Test;

import java.io.IOException;

import edu.kit.pse.fridget.client.datamodel.Device;
import edu.kit.pse.fridget.client.datamodel.representation.UserWithJwtRepresentation;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;

public class DeviceServiceTest {
    Device testDevice = new Device("testId","testUserId","testInstanceToken");
    MockWebServer server = new MockWebServer();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(server.url("").toString())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    DeviceService service = retrofit.create(DeviceService.class);

    @Test
    public void sendDevice() throws IOException {
        server.enqueue(new MockResponse().setBody(new Gson().toJson(testDevice)));
        Call<Device> call = service.sendDevice(testDevice);
        assertTrue(call.execute() != null);
        server.shutdown();

    }


}