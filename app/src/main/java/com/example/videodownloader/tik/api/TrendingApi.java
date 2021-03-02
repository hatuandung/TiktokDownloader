package com.example.videodownloader.tik.api;

import com.example.videodownloader.tik.model.TrendingResponse;
import com.example.videodownloader.tik.model.VideoResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface TrendingApi {

    @POST("putVideo")
    @FormUrlEncoded
    Call<TrendingResponse> post(
            @Field("url") String url,
            @Field("thumbnail") String thumbnail
    );

    @GET("getVideo")
    Call<TrendingResponse> get(@Query("page") int page);
//    @GET("getVideo")
//    Call<TrendingResponse> get();
}
