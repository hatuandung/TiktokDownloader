package com.example.videodownloader.tik.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.videodownloader.tik.R;

public class SettingFragment extends BaseFragment implements View.OnClickListener {

    private LinearLayout llRate, llShare, llOtherApp, llPolicy, llUse, llDemo, llNotWorking, llFeedback;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViews();
    }

    private void initViews() {
        llRate = findViewByID(R.id.ll_rate);
        llShare = findViewByID(R.id.ll_share);
        llOtherApp = findViewByID(R.id.ll_otherApps);
        llPolicy = findViewByID(R.id.ll_policy);
        llUse = findViewByID(R.id.ll_use);
        llDemo = findViewByID(R.id.ll_demo);
        llNotWorking = findViewByID(R.id.ll_notWorking);
        llFeedback = findViewByID(R.id.ll_feedback);

        llRate.setOnClickListener(this);
        llShare.setOnClickListener(this);
        llOtherApp.setOnClickListener(this);
        llPolicy.setOnClickListener(this);
        llUse.setOnClickListener(this);
        llDemo.setOnClickListener(this);
        llNotWorking.setOnClickListener(this);
        llFeedback.setOnClickListener(this);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_setting;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_rate:
                break;
            case R.id.ll_share:
                break;
            case R.id.ll_otherApps:
                break;
            case R.id.ll_policy:
                break;
            case R.id.ll_use:
                break;
            case R.id.ll_demo:
                break;
            case R.id.ll_notWorking:
                break;
            case R.id.ll_feedback:
                break;
        }
    }
}
