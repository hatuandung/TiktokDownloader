package com.example.videodownloader.tik.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrendingApiBuider {

    private static TrendingApi trendingApi;
    public static TrendingApi getInstance(){
        if (trendingApi == null){
            trendingApi = new Retrofit.Builder()
                    .baseUrl("http://45.63.5.77/TrendingTitok/public/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(TrendingApi.class);
        }
        return trendingApi;
    }

}
