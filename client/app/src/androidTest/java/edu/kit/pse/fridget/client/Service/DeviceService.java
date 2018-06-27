package edu.kit.pse.fridget.client.Service;

import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface DeviceService {

    /**
     * Dieses Interface synchronisiert die Device-Daten mit dem Server

     */


    //Diese Methode fügt ein Device zu einer Flatshare hinzu
    @Headers({"Content-Type: application/json",
            "Authorization:{JWT}"})
    @POST("/devices")
    Call<Device> createDevice(@Body User user);

}
