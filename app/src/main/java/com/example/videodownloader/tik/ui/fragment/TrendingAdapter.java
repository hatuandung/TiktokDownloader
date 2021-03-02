package com.example.videodownloader.tik.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.videodownloader.tik.R;
import com.example.videodownloader.tik.model.Trending;
import com.example.videodownloader.tik.model.Video;

import java.util.ArrayList;
import java.util.zip.Inflater;

import retrofit2.http.Url;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.TrendingHolder>{

    private LayoutInflater inflater;
    private ArrayList<Trending> videoList;
    private TrendingListener listener;

    public void setTrendingListener(TrendingListener listener) {
        this.listener = listener;
    }

    public TrendingAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public void setVideoList(ArrayList<Trending> videoList) {
        this.videoList = videoList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TrendingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_trending, parent, false);
        return new TrendingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingHolder holder, int position) {
        holder.bindView(videoList.get(position));

        if (listener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemTrendingClicked(position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onItemTrendingLongClicked(position);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return videoList == null ? 0 : videoList.size();
    }

    class TrendingHolder extends RecyclerView.ViewHolder{

        ImageView imgTrending;

        public TrendingHolder(@NonNull View itemView) {
            super(itemView);
            imgTrending = itemView.findViewById(R.id.img_trending);
        }

        public void bindView(Trending item){

            Glide.with(itemView)
                    .load(item.getThumbnail())
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(imgTrending)
            ;
        }
    }

    public interface TrendingListener{
        void onItemTrendingClicked(int position);
        void onItemTrendingLongClicked(int position);
    }
}
