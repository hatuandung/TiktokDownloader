package com.example.videodownloader.tik.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.videodownloader.tik.R;
import com.example.videodownloader.tik.model.Video;

import java.io.File;

public class PlayVideoActivity extends AppCompatActivity {

    private Video video;
    private MediaController controller;
    private VideoView vdv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        
        init();
    }

    private void init() {
        controller = new MediaController(this);
        vdv = findViewById(R.id.vdv);
        controller.setAnchorView(vdv);
        Intent intent = getIntent();
        video = (Video) intent.getSerializableExtra("video");
        Uri uri = Uri.fromFile(new File(video.getPath()));
        vdv.requestFocus();
        vdv.setMediaController(controller);
        vdv.setVideoURI(uri);
        vdv.start();
    }
}