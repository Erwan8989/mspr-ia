package com.mspr.arosaje.img;

import android.content.Context;
import android.net.Uri;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CloudinaryManager {

    private static CloudinaryManager instance;

    private final Context ctx;

    private CloudinaryManager(Context ctx) {
        this.ctx = ctx;
        this.init();
    }

    public static CloudinaryManager getInstance(Context ctx) {
        if (instance == null) {
            instance = new CloudinaryManager(ctx);
        }
        return instance;
    }

    private void init() {
        HashMap<String, Object> config = new HashMap<>();
        config.put("cloud_name", System.getenv("cloud_name"));
        config.put("secure", true);
        config.put("api_key", System.getenv("api_key"));
        config.put("api_secret", System.getenv("api_secret"));
        MediaManager.init(ctx, config);
    }

    public void uploadImage(File file) {
        Uri uri = this.getImgName(file);
        MediaManager
                .get()
                .upload(uri)
                .option("tags", "arosaje")
                .callback(new UploadCallback() {
                    @Override
                    public void onStart(String requestId) {

                    }

                    @Override
                    public void onProgress(String requestId, long bytes, long totalBytes) {

                    }

                    @Override
                    public void onSuccess(String requestId, Map resultData) {
                        ImageManager.setRemoteImg((String) resultData.get("url"));
                    }

                    @Override
                    public void onError(String requestId, ErrorInfo error) {

                    }

                    @Override
                    public void onReschedule(String requestId, ErrorInfo error) {

                    }
                })
                .dispatch();
    }

    private Uri getImgName(File file) {
        return Uri.fromFile(file);
    }

}
