package com.example.videodownloader.tik.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VideoResponse {

    @SerializedName("play")
    private String play;

    @SerializedName("desc")
    private String title;

    @SerializedName("thumbnail")
    private String thumbnail;

    public String getThumbnail() {
        return thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getPlay() {
        return play;
    }

}
