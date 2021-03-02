package com.example.videodownloader.tik.ui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.videodownloader.tik.R;
import com.example.videodownloader.tik.file.FileManager;
import com.example.videodownloader.tik.model.Video;
import com.example.videodownloader.tik.ui.activity.PlayVideoActivity;

import java.io.File;
import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoHolder> {

    private LayoutInflater inflater;
    private ArrayList<Video> videoList;

    public VideoAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public void setVideoList(ArrayList<Video> videoList) {
        this.videoList = videoList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_video, parent, false);
        return new VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, int position) {
        holder.bindView(videoList.get(position));
    }

    @Override
    public int getItemCount() {
        return videoList == null ? 0 : videoList.size();
    }

    class VideoHolder extends RecyclerView.ViewHolder implements SurfaceHolder.Callback {

        ImageView imgVideo, imgShare, imgDelete;
//        SurfaceView sfvVideo;
//        SurfaceHolder surfaceHolder;
        public VideoHolder(@NonNull View itemView) {
            super(itemView);

            imgDelete = itemView.findViewById(R.id.img_delete);
            imgShare = itemView.findViewById(R.id.img_share);
            imgVideo = itemView.findViewById(R.id.img_video);
        }

        public void bindView(Video item) {


            Glide.with(itemView).load(Uri.fromFile(new File(item.getPath()))).centerCrop().into(imgVideo);


            MediaMetadataRetriever mmRetriever = new MediaMetadataRetriever();
            mmRetriever.setDataSource(item.getPath());

            // Array list to hold your frames
            ArrayList<Bitmap> frames = new ArrayList<Bitmap>();

            //Create a new Media Player
            MediaPlayer mp = MediaPlayer.create(itemView.getContext(), Uri.fromFile(new File(item.getPath())));

            // Some kind of iteration to retrieve the frames and add it to Array list
            Bitmap bitmap = mmRetriever.getFrameAtTime(6000);
            frames.add(bitmap);



            imgVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), PlayVideoActivity.class);
                    intent.putExtra("video", item);
                    itemView.getContext().startActivity(intent);
                }
            });

            imgShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                    builder.setMessage("Are you want to delete ?");
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            FileManager fileManager = new FileManager();
                            fileManager.deleteFile(item.getPath());
                            videoList.remove(videoList.indexOf(item));
                            notifyDataSetChanged();
                        }
                    });
                    builder.create().show();
                }
            });
        }

        @Override
        public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

        }

        @Override
        public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        }

        @Override
        public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

        }

    }
}
