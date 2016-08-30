package cc.iceworld.tankgame.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;

import cc.iceworld.tankgame.api.Destroyable;
import cc.iceworld.tankgame.api.Drawable;
import cc.iceworld.tankgame.util.BitmapBox;
import cc.iceworld.tankgame.util.Point;

/**
 * Created by cxx on 16/5/28.
 * xx.ch@outlook.com
 */
public class Effects implements Drawable, Destroyable {
    private Point point;
    private BitmapBox[] skin;
    private int count = 0;

    /**
     * @param p    特效中心点的坐标
     * @param skin 特效的一系列图片
     */
    protected Effects(@NonNull Point p, @NonNull Bitmap[] skin) {
        this.point = p;
        this.skin = BitmapBox.fromBitmaps(this.point, skin);
    }

    @Override
    public void draw(@NonNull Canvas canvas, @NonNull Paint paint) {
        if (!isDestroyed()) {
            BitmapBox box = skin[count];
            canvas.drawBitmap(box.asBitmap(), box.left(), box.top(), paint);
            update();
        }
    }

    @Override
    public void update() {
        ++count;
    }

    @Override
    public void destroy() {
        count = skin.length;
    }

    @Override
    public boolean isDestroyed() {
        return count >= skin.length;
    }

    public void setSkin(@NonNull Bitmap[] skin) {
        this.skin = BitmapBox.fromBitmaps(point, skin);
    }

    protected final void reLive() {
        count = 0;
    }

    public void setPoint(int x, int y) {
        point.set(x, y);
    }

    public void setPoint(@NonNull Point p) {
        point.set(p);
    }
}
