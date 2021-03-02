package com.example.videodownloader.tik.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.videodownloader.tik.R;
import com.example.videodownloader.tik.api.ApiBuilder;
import com.example.videodownloader.tik.model.Trending;
import com.example.videodownloader.tik.api.TrendingApiBuider;
import com.example.videodownloader.tik.file.DownloadAsync;
import com.example.videodownloader.tik.model.TrendingResponse;
import com.example.videodownloader.tik.model.VideoResponse;
import com.example.videodownloader.tik.ui.activity.PlayTrendingActivity;
import com.example.videodownloader.tik.ui.activity.PlayVideoActivity;
import com.example.videodownloader.tik.utils.Encryption;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideosFragment extends BaseFragment implements View.OnClickListener, Callback<VideoResponse>, TrendingAdapter.TrendingListener {
    private NestedScrollView nestedScrollView;
    private EditText edtLink;
    private Button btnDownload;
    private RecyclerView rvTrending;
    private ProgressBar progressBar;

    private Encryption encryption = new Encryption();

    private String downloadUrl;
    private String name;
    private String thumbnail;

    private ArrayList<Trending> trendings = new ArrayList<>();
    private int page = 1 ;
    private TrendingAdapter adapter;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
    }

    private void initViews() {
        edtLink = findViewByID(R.id.edt_link);
        btnDownload = findViewByID(R.id.btn_download);
        rvTrending = findViewByID(R.id.rv_trending);
        nestedScrollView = findViewByID(R.id.scroll_view);
        progressBar = findViewByID(R.id.progress_bar);

        btnDownload.setOnClickListener(this);

        adapter = new TrendingAdapter(getLayoutInflater());
        rvTrending.setAdapter(adapter);
        adapter.setVideoList(trendings);
        adapter.setTrendingListener(this);

        getData(page);

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()){
                    page++;
                    progressBar.setVisibility(View.VISIBLE);
                    getData(page);
                }
            }
        });
    }

    private void getData(int page) {

        TrendingApiBuider.getInstance().get(page).enqueue(new Callback<TrendingResponse>() {
            @Override
            public void onResponse(Call<TrendingResponse> call, Response<TrendingResponse> response) {

                if (response.isSuccessful() && response.body() != null){
                    progressBar.setVisibility(View.GONE);

                    TrendingResponse trendingResponse = response.body();
                    trendings = trendingResponse.getTrendings();
                    Log.e("onResponse: ", String.valueOf(trendings.size()));
                    setData(trendings);
                }
            }

            @Override
            public void onFailure(Call<TrendingResponse> call, Throwable t) {

            }
        });
    }

    public void setData(List<Trending> trendings){
        this.trendings.clear();
        this.trendings.addAll(trendings);
        if (adapter != null){
            adapter.setVideoList(this.trendings);
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_videos;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void onClick(View view) {
        String urlTiktok = edtLink.getText().toString();

        if (!urlTiktok.contains("http") && !urlTiktok.contains("tiktok")) {
            Toast.makeText(getActivity(), "Please insert Tik Tok Link", Toast.LENGTH_SHORT).show();
        } else {
            try {
                getLink(urlTiktok);
                Log.e("onClick: ", thumbnail);

                //Log.e( "onClick: ",encryption.encryption( urlTiktok) );
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void getLink(String url){
        ApiBuilder.getInstance().links(encryption.encryption( url)).enqueue(this);
    }

    @Override
    public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
        VideoResponse videoResponse = response.body();
        downloadUrl = videoResponse.getPlay();
        thumbnail = videoResponse.getThumbnail();
        name = videoResponse.getTitle();

        download(downloadUrl, name);
        String urlTiktok = edtLink.getText().toString();

        Log.e("onResponse: ", thumbnail + "\n" + urlTiktok);
        TrendingApiBuider.getInstance().post(urlTiktok, thumbnail).enqueue(new Callback<TrendingResponse>() {
            @Override
            public void onResponse(Call<TrendingResponse> call, Response<TrendingResponse> response) {
                Toast.makeText(getContext(), "done", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<TrendingResponse> call, Throwable t) {
                Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
            }
        });
        edtLink.setText("");

    }

    @Override
    public void onFailure(Call<VideoResponse> call, Throwable t) {
        Toast.makeText(getActivity(), "Can't download", Toast.LENGTH_SHORT).show();
        Log.e("error", t.getMessage()+"_"+t.toString());
    }

    public void download(String downloadUrl, String name) {
        new DownloadAsync(getContext(), name).execute(downloadUrl);
    }

    @Override
    public void onItemTrendingClicked(int position) {
        Intent intent = new Intent(getContext(), PlayTrendingActivity.class);
        intent.putExtra("trending", trendings.get(position).getUrl());
        getContext().startActivity(intent);

    }

    @Override
    public void onItemTrendingLongClicked(int position) {
        getLink(trendings.get(position).getUrl());
    }
}
