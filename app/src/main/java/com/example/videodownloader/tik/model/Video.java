package com.example.videodownloader.tik.model;


import android.net.Uri;
import android.provider.MediaStore;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Video implements Serializable {

    private String path;

    public String getPath() {
        return path;
    }


    public Video(String path) {
        this.path = path;
    }
}
