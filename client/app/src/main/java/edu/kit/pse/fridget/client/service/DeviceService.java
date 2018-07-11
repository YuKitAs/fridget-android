package edu.kit.pse.fridget.client.service;

import edu.kit.pse.fridget.client.datamodel.Device;
import edu.kit.pse.fridget.client.datamodel.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface DeviceService {

    /**
     * Dieses Interface synchronisiert die Device-Daten mit dem Server
     */


    //Diese Methode fügt ein Device zu einer Flatshare hinzu
    @Headers("Content-Type: application/json")
    @POST("/devices")
    Call<Device> createDevice(@Body User user);
}
