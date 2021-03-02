package com.example.videodownloader.tik.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiBuilder {
    private static Api api;
    public static Api getInstance(){
        if (api == null){
            api = new Retrofit.Builder()
                    .baseUrl("https://www.katarmal.in/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(Api.class);
        }
        return api;
    }
}
