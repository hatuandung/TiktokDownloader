package com.example.videodownloader.tik.file;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.videodownloader.tik.R;
import com.example.videodownloader.tik.api.TrendingApiBuider;
import com.example.videodownloader.tik.model.VideoResponse;
import com.example.videodownloader.tik.ui.activity.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownloadAsync extends AsyncTask<String , Void, String> {
    public Context context;
    public Dialog dialog;
    public String name;

    public DownloadAsync(Context context, String name) {
        this.context = context;
        this.name = name;
        dialog = new Dialog(context, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar_MinWidth);
        dialog.setContentView(R.layout.dialog_progress_loading);
        dialog.setCancelable(false);
    }

    @Override
    protected String doInBackground(String... strings) {
        FileManager manager = new FileManager();

        return manager.download(strings[0], name);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        dialog.dismiss();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
