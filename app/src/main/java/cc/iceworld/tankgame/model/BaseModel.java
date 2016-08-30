package cc.iceworld.tankgame.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;

import cc.iceworld.tankgame.annotation.Camp;
import cc.iceworld.tankgame.annotation.Dir;
import cc.iceworld.tankgame.api.Collision;
import cc.iceworld.tankgame.api.Destroyable;
import cc.iceworld.tankgame.api.Drawable;
import cc.iceworld.tankgame.util.BoundingBox;
import cc.iceworld.tankgame.util.C;
import cc.iceworld.tankgame.util.Cover;
import cc.iceworld.tankgame.util.Point;

/**
 * Created by cxx on 16-5-25.
 * xx.ch@outlook.com
 */
public abstract class BaseModel implements Drawable, Destroyable, Collision {
    protected Cover cover;
    @Camp
    protected int camp;

    /**
     * @param center 模型中心点的坐标
     * @param skin   模型的外观，应包括 4 张图片，且朝向应依次为左、上、右、下。
     * @param camp   阵营，用以进行碰撞或攻击检测
     */
    protected BaseModel(@NonNull Point center, @NonNull Bitmap[] skin, @Camp int camp) {
        cover = new Cover(center, checkedSkin(skin));
        this.camp = camp;
    }

    @Override
    public final void draw(@NonNull Canvas canvas, @NonNull Paint paint) {
        if (!isDestroyed()) {
            update();
            cover.draw(canvas, paint);
        }
    }

    @Override
    public final boolean collide(@NonNull Collision c) {
        return box().intersect(c.box());
    }

    @NonNull
    @Override
    public final BoundingBox box() {
        return cover.box();
    }

    @Camp
    public final int camp() {
        return camp;
    }

    public final void setCamp(@Camp int camp) {
        this.camp = camp;
    }

    /**
     * @param skin 模型的外观，应包括 4 张图片，且朝向应依次为左、上、右、下。
     */
    public final void setSkin(@NonNull Bitmap[] skin) {
        cover.setSkin(checkedSkin(skin));
    }

    public final void setPoint(int x, int y) {
        cover.setPoint(x, y);
    }

    public final void setPoint(@NonNull Point p) {
        cover.setPoint(p);
    }

    @Dir.Head
    public int headDir() {
        return cover.headDir();
    }

    protected final void fixLocation() {
        if (cover.left() < C.AREA.l) {
            cover.setLeft(C.AREA.l);
        } else if (cover.right() > C.AREA.r) {
            cover.setRight(C.AREA.r);
        }
        if (cover.top() < C.AREA.t) {
            cover.setTop(C.AREA.t);
        } else if (cover.bottom() > C.AREA.b) {
            cover.setBottom(C.AREA.b);
        }
    }

    protected final boolean isOutSide() {
        return cover.left() > C.AREA.r
                || cover.right() < C.AREA.l
                || cover.top() > C.AREA.b
                || cover.bottom() < C.AREA.t;
    }

    protected abstract void reLive();

    private static Bitmap[] checkedSkin(Bitmap[] skin) {
        if (skin.length != 4) {
            throw new IllegalArgumentException("skin's length must be 4, skin.length = " + skin.length);
        }
        return skin;
    }
}
