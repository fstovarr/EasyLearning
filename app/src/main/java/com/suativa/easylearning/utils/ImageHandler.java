package com.suativa.easylearning.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class ImageHandler {
    public static Bitmap getScaledDrawable(Bitmap bitmap, float screenWidth) {
        Matrix matrix = new Matrix();

        int originalWidth = bitmap.getWidth();
        int originalHeigth = bitmap.getHeight();

        float aspectRatio = (float) bitmap.getHeight() / (float) bitmap.getWidth();
        float width = screenWidth;
        float height = screenWidth * aspectRatio;

        matrix.postScale(width / (float) originalWidth, height / (float) originalHeigth);

        Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, originalWidth, originalHeigth, matrix, false);
        bitmap.recycle();
        return scaledBitmap;
    }
}
