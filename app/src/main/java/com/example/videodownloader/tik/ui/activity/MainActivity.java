package com.example.videodownloader.tik.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.videodownloader.tik.R;
import com.example.videodownloader.tik.api.ApiBuilder;
import com.example.videodownloader.tik.api.TrendingApiBuider;
import com.example.videodownloader.tik.file.DownloadAsync;
import com.example.videodownloader.tik.model.Trending;
import com.example.videodownloader.tik.model.TrendingResponse;
import com.example.videodownloader.tik.model.VideoResponse;
import com.example.videodownloader.tik.ui.fragment.BaseFragment;
import com.example.videodownloader.tik.ui.fragment.MyDownloadsFragment;
import com.example.videodownloader.tik.ui.fragment.SettingFragment;
import com.example.videodownloader.tik.ui.fragment.VideosFragment;
import com.example.videodownloader.tik.utils.Encryption;
import com.example.videodownloader.tik.utils.PermissionUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private Encryption encryption = new Encryption();

    private ArrayList<Trending> trendings;

    private VideosFragment fmVideos = new VideosFragment();
    private MyDownloadsFragment fmMyDownloads = new MyDownloadsFragment();
    private SettingFragment fmSetting = new SettingFragment();

    public VideosFragment getFmVideos() {
        return fmVideos;
    }

    public MyDownloadsFragment getFmMyDownloads() {
        return fmMyDownloads;
    }

    public SettingFragment getFmSetting() {
        return fmSetting;
    }

    BottomNavigationView navigation;

    private String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        if (PermissionUtils.checkPermissions(MainActivity.this, PERMISSIONS)) {
            navigation = findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(MainActivity.this);
            initFragment();
            showFragment(fmVideos);
        } else {
            PermissionUtils.requestPermissons(MainActivity.this, PERMISSIONS, 0);
        }

        //TrendingApiBuider.getInstance().get().enqueue(this);
    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_videos:
                showFragment(fmVideos);
                break;
            case R.id.nav_my_downloads:
                showFragment(fmMyDownloads);
                break;
            case R.id.nav_setting:
                showFragment(fmSetting);
                break;
        }
        return true;
    }

    private void initFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.add(R.id.container, fmVideos);
        transaction.add(R.id.container, fmMyDownloads);
        transaction.add(R.id.container, fmSetting);
        transaction.commit();
    }

    private void showFragment(Fragment fmShow) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);

        transaction.hide(fmVideos);
        transaction.hide(fmMyDownloads);
        transaction.hide(fmSetting);

        transaction.detach(fmShow);
        transaction.attach(fmShow);
        transaction.show(fmShow);

        transaction.commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (PermissionUtils.checkPermissions(MainActivity.this, permissions)) {
            initViews();
        } else {
            PermissionUtils.requestPermissons(MainActivity.this, PERMISSIONS, 0);
        }
    }

//    @Override
//    public void onResponse(Call<TrendingResponse> call, Response<TrendingResponse> response) {
//        TrendingResponse trendingResponse = response.body();
//        trendings = trendingResponse.getTrendings();
//
//        Log.e("onResponse: ", String.valueOf(trendings.get(1).getThumbnail()));
//
//        fmVideos.setData(trendings);
//
//    }
//
//    @Override
//    public void onFailure(Call<TrendingResponse> call, Throwable t) {
//
//    }
}