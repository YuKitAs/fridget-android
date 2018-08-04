package edu.kit.pse.fridget.client.service;

import edu.kit.pse.fridget.client.datamodel.Device;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface DeviceService {

    /**
     * Dieses Interface synchronisiert die Device-Daten mit dem Server
     */

    @Headers("Content-Type: application/json")
    @POST("/devices")
    Call<Device> sendDevice(@Body Device device);
}
