package com.suativa.easylearning.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class ImageHandler {
    public static final int HEIGHT_MATCH_PARENT = 1;
    public static final int WIDTH_MATCH_PARENT = 2;

    public static Bitmap getScaledDrawable(Bitmap bitmap, float screenWidth, float screenHeight, int matchType, int margin) {
        Matrix matrix = new Matrix();

        int originalWidth = bitmap.getWidth();
        int originalHeigth = bitmap.getHeight();

        float width;
        float height;

        if (matchType == HEIGHT_MATCH_PARENT) {
            float aspectRatio = (float) bitmap.getWidth() / (float) bitmap.getHeight();
            width = screenHeight * aspectRatio;
            height = screenHeight;
            height -= 200;
        } else {
            float aspectRatio = (float) bitmap.getHeight() / (float) bitmap.getWidth();
            height = screenWidth * aspectRatio;
            width = screenWidth;
        }

        matrix.postScale(width / (float) originalWidth, height / (float) originalHeigth);

        Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, originalWidth, originalHeigth, matrix, false);
        bitmap.recycle();
        return scaledBitmap;
    }
}
