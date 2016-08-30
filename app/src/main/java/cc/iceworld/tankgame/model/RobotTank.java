package cc.iceworld.tankgame.model;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import cc.iceworld.tankgame.annotation.Camp;
import cc.iceworld.tankgame.annotation.Dir;
import cc.iceworld.tankgame.util.Point;
import cc.iceworld.tankgame.util.Rand;

/**
 * Created by cxx on 16-5-19.
 * xx.ch@outlook.com
 */
public class RobotTank extends Tank {
    private static final int PERCENT_MOVE = 2;
    private static final int PERCENT_FIRE = 3;

    /**
     * @param p       模型中心点的坐标
     * @param skin    模型的外观，应包括 4 张图片，且朝向应依次为左、上、右、下。
     * @param camp    阵营，用以进行碰撞或攻击检测
     * @param speed   坦克行进速度
     * @param totalHp 坦克的生命值
     */
    protected RobotTank(@NonNull Point p, @NonNull Bitmap[] skin, @Camp int camp, int speed, int totalHp) {
        super(p, skin, camp, speed, totalHp);
    }

    @Override
    public void update() {
        if (Rand.nextInt(0, 100) <= PERCENT_MOVE) {
            setMoveDir(Dir.nextMoveDir());
        }
        if (Rand.nextInt(0, 100) <= PERCENT_FIRE) {
            fire(null);
        }
        super.update();
    }
}
