package com.example.videodownloader.tik.ui.fragment;

import android.os.Bundle;
import android.os.Environment;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.videodownloader.tik.R;
import com.example.videodownloader.tik.model.Video;

import java.io.File;
import java.util.ArrayList;


public class MyDownloadsFragment extends BaseFragment {

    private RecyclerView rvVideo;
    private ArrayList<Video> data = new ArrayList<>();
    private VideoAdapter adapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        setData();
    }

    public void setData() {
        data.clear();
        data.addAll(getVideos());
        adapter = new VideoAdapter(getLayoutInflater());
        rvVideo.setAdapter(adapter);
        adapter.setVideoList(data);
        if (adapter != null) {
            adapter.setVideoList(data);
        }
    }

    public ArrayList<Video> getVideos() {
        ArrayList<Video> videoList = new ArrayList<>();
        String path = Environment.getExternalStorageDirectory().toString() + "/TikTok Downloader";
        File directory = new File(path);
        File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                String itemPath = files[i].getAbsolutePath();
                videoList.add(new Video(itemPath));
            }
        }
        return videoList;
    }


    public void initViews() {
        rvVideo = getActivity().findViewById(R.id.rv_video);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_mydownload;
    }

    @Override
    public String getTitle() {
        return null;
    }
}
