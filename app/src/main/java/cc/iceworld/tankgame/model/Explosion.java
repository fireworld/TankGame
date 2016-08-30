package cc.iceworld.tankgame.model;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import cc.iceworld.tankgame.util.Point;

/**
 * Created by cxx on 16/5/19.
 * xx.ch@outlook.com
 * <p/>
 * 爆炸效果，在爆炸产生时绘制一系列由小变大再变小的图片
 */
public class Explosion extends Effects {

    /**
     * @param p    爆炸效果中心点的坐标
     * @param skin 爆炸效果的一系列图片
     */
    protected Explosion(@NonNull Point p, @NonNull Bitmap[] skin) {
        super(p, skin);
    }
}
