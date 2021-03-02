package com.example.videodownloader.tik.model;

import com.google.gson.annotations.SerializedName;

public class Trending {
    @SerializedName("url")
    private String url;

    @SerializedName("thumbnail")
    private String thumbnail;

    public String getThumbnail() {
        return thumbnail;
    }

    public String getUrl() {
        return url;
    }
}
