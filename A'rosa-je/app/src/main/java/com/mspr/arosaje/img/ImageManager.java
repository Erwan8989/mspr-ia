package com.mspr.arosaje.img;

import android.graphics.Bitmap;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageManager {
    private static String remoteImg;

    public static File createFile(Bitmap photo, File dir) {
        File f = new File(dir, "test.png");
        try {
            f.createNewFile();

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();

            FileOutputStream fos = new FileOutputStream(f);
            Log.d("PHOTO", "onActivityResult: " + f.getAbsoluteFile());
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
            return f;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getRemoteImg() {
        return remoteImg;
    }

    public static void setRemoteImg(String remoteImg) {
        ImageManager.remoteImg = remoteImg;
    }
}
