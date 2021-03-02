package com.example.videodownloader.tik.file;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class FileManager {
    String TAG = "FileManager";
    String rootPath = Environment.getExternalStorageDirectory().getPath();

    public String getRootPath() {
        return rootPath;
    }

    public File[] getFile(String path) {
        File file = new File(path);
        return file.listFiles();
    }

    public void deleteFile(String path) {
        File file = new File(path);
        file.delete();
    }

    public String download(String link, String name) {
        try {

            rootPath +=  "/TikTok Downloader/" + name + ".mp4";

            URL url = new URL(link);
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            byte[] b = new byte[1024];
            int count = inputStream.read(b);

            File file = new File(rootPath);
            file.getParentFile().mkdirs();
            file.createNewFile();

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            while (count > 0) {
                fileOutputStream.write(b, 0, count);
                count = inputStream.read(b);
            }
            inputStream.close();
            fileOutputStream.close();
            return rootPath;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
