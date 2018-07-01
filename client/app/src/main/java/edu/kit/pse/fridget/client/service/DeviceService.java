package edu.kit.pse.fridget.client.service;

import android.bluetooth.BluetoothClass;

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
    @Headers({"Content-Type: application/json",
            "Authorization:{JWT}"})
    @POST("/devices")
    Call<BluetoothClass.Device> createDevice(@Body User user);

}
