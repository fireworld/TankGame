package cc.iceworld.tankgame.model;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import cc.iceworld.tankgame.annotation.Camp;
import cc.iceworld.tankgame.util.Point;

/**
 * Created by cxx on 16-5-19.
 * xx.ch@outlook.com
 */
public class UserTank extends Tank {

    /**
     * @param p       模型中心点的坐标
     * @param skin    模型的外观，应包括 4 张图片，且朝向应依次为左、上、右、下。
     * @param camp    阵营，用以进行碰撞或攻击检测
     * @param speed   坦克行进速度
     * @param totalHp 坦克的生命值
     */
    protected UserTank(@NonNull Point p, @NonNull Bitmap[] skin, @Camp int camp, int speed, int totalHp) {
        super(p, skin, camp, speed, totalHp);
    }
}
