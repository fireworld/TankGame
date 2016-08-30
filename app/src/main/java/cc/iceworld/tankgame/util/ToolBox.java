package cc.iceworld.tankgame.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

import cc.iceworld.tankgame.api.Destroyable;
import cc.iceworld.tankgame.api.Drawable;

/**
 * Created by cxx on 16-5-19.
 * xx.ch@outlook.com
 */
public class ToolBox {

    public static void draw(@NonNull Canvas canvas, @NonNull Paint paint, Collection<? extends Drawable> col) {
        try {
            for (Drawable d : col) {
                d.draw(canvas, paint);
            }
        } catch (ConcurrentModificationException ignored) {
            ignored.printStackTrace();
        }
    }

    public static int liveStats(@NonNull Collection<? extends Destroyable> col) {
        int size = 0;
        for (Destroyable e : col) {
            if (!e.isDestroyed()) {
                size++;
            }
        }
        return size;
    }

    public static void clearDestroyed(@NonNull Collection<? extends Destroyable> col, int limit) {
        if (limit < 1 || col.size() < limit) return;
        Iterator<? extends Destroyable> i = col.iterator();
        do {
            if (i.next().isDestroyed()) {
                i.remove();
            }
        } while (i.hasNext() && col.size() >= limit);
    }

    public static Bitmap decodeScaled(@NonNull Resources res, @DrawableRes int resId, final int reqShortSide) {
        Bitmap temp = BitmapFactory.decodeResource(res, resId);
        int w = temp.getWidth();
        int h = temp.getHeight();
        int ss = h < w ? h : w;
        double scale = (double) ss / reqShortSide;
        int rw = (int) (w / scale);
        int rh = (int) (h / scale);
        Bitmap result = Bitmap.createScaledBitmap(temp, rw, rh, true);
        if (temp != result) {
            temp.recycle();
        }
        return result;
    }

    public static void checkNull(Object o) {
        if (o == null) {
            throw new NullPointerException("can not be null");
        }
    }

    private ToolBox() {
        throw new AssertionError("no instance");
    }
}
