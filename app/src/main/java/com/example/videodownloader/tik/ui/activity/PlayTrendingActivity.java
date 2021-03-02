package com.example.videodownloader.tik.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.videodownloader.tik.R;
import com.example.videodownloader.tik.api.ApiBuilder;
import com.example.videodownloader.tik.model.Trending;
import com.example.videodownloader.tik.model.VideoResponse;
import com.example.videodownloader.tik.utils.Encryption;

import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayTrendingActivity extends AppCompatActivity implements Callback<VideoResponse> {
    Encryption encryption = new Encryption();
    private MediaController controller;
    private VideoView vdvTrending;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_trending);

        init();
    }

    private void init() {

        controller = new MediaController(this);
        vdvTrending = findViewById(R.id.vdv_trending);
        controller.setAnchorView(vdvTrending);

        Intent intent = getIntent();
        String url = intent.getStringExtra("trending");

        ApiBuilder.getInstance().links(encryption.encryption( url)).enqueue(this);


    }


    @Override
    public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {

        VideoResponse videoResponse = response.body();
        String url = videoResponse.getPlay();

        vdvTrending.requestFocus();
        vdvTrending.setMediaController(controller);
        vdvTrending.setVideoPath(url);
        vdvTrending.start();
    }

    @Override
    public void onFailure(Call<VideoResponse> call, Throwable t) {

    }
}