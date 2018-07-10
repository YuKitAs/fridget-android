package edu.kit.pse.fridget.client.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("flatshare")
            .addConverterFactory(GsonConverterFactory.create());

    static Retrofit retrofit=builder.build();


}
