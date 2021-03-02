package com.example.videodownloader.tik.api;

import com.example.videodownloader.tik.model.VideoResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {
    @POST("processUrl")
    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "User-Agent: TikTok Downloader1.0.46 (com.smartapps.videodownloaderfortiktok; build:46 Android SDK 23) okhttp/3.12.1 LGE Nexus 5"
    })
    @FormUrlEncoded
    Call<VideoResponse> links (@Field("tiktokurl") String url);

}
