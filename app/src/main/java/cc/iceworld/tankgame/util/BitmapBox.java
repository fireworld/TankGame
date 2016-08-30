package cc.iceworld.tankgame.util;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

/**
 * Created by cxx on 16-5-24.
 * xx.ch@outlook.com
 */
public class BitmapBox extends BoundingBox {
    private Bitmap bitmap;

    /**
     * @param center 中心点坐标
     * @param bitmap 图片
     */
    public BitmapBox(@NonNull Point center, @NonNull Bitmap bitmap) {
        super(center, bitmap.getWidth(), bitmap.getHeight());
        this.bitmap = bitmap;
    }

    @NonNull
    public Bitmap asBitmap() {
        return bitmap;
    }

    public void setBitmap(@NonNull Bitmap bitmap) {
        this.bitmap = bitmap;
        radiusX = bitmap.getWidth() >> 1;
        radiusY = bitmap.getHeight() >> 1;
    }

    public static BitmapBox[] fromBitmaps(@NonNull Point center, @NonNull Bitmap[] bitmaps) {
        int length = bitmaps.length;
        BitmapBox[] result = new BitmapBox[length];
        for (int i = 0; i < length; i++) {
            result[i] = new BitmapBox(center, bitmaps[i]);
        }
        return result;
    }
}
