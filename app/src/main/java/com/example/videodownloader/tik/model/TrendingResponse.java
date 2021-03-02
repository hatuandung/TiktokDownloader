package com.example.videodownloader.tik.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TrendingResponse {
    @SerializedName("data")
    private ArrayList<Trending> trendings;

    public ArrayList<Trending> getTrendings() {
        return trendings;
    }
}
