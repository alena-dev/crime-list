package com.g.e.criminalinternet.controller;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

public class PictureUtils {

    public static Bitmap getScaledBitmap(String path,
                                         int destWidth, int destHeight) {
        //read size of image on the disk
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        float srcWidth = options.outWidth;
        float srcHeght = options.outHeight;

        //calculate scaling
        int inSampleSize = 1;
        if (srcHeght > destHeight || srcWidth > destHeight) {
            float heightScale = srcHeght / destHeight;
            float widthScale = srcWidth / destWidth;
            inSampleSize = Math.round(heightScale > widthScale
                    ? heightScale : widthScale);
        }

        options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;

        // read data and create new image
        return BitmapFactory.decodeFile(path, options);
    }

//    public static Bitmap getScaledBitmap(String path, Activity activity) {
//        Point size = new Point();
//        activity.getWindowManager().getDefaultDisplay().getSize(size);
//
//        return getScaledBitmap(path, size.x, size.y);
//    }
}
