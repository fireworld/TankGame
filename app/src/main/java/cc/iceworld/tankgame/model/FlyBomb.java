package cc.iceworld.tankgame.model;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import cc.iceworld.tankgame.annotation.Camp;
import cc.iceworld.tankgame.annotation.Dir;
import cc.iceworld.tankgame.api.Movable;
import cc.iceworld.tankgame.util.Point;
import cc.iceworld.tankgame.util.Speed;

/**
 * Created by cxx on 16-5-19.
 * xx.ch@outlook.com
 */
public class FlyBomb extends Bomb implements Movable {
    private Speed speed;

    /**
     * @param p     模型中心点的坐标
     * @param skin  模型的外观，应包括 4 张图片，且朝向应依次为左、上、右、下。
     * @param camp  阵营，用以进行碰撞或攻击检测
     * @param dir   炮弹的朝向
     * @param power 炮弹所能造成的伤害值
     * @param speed 炮弹飞行的速度
     */
    protected FlyBomb(@NonNull Point p, @NonNull Bitmap[] skin, @Camp int camp, @Dir.Head int dir, int power, int speed) {
        super(p, skin, camp, dir, power);
        this.speed = new Speed(speed, speed, dir);
    }

    @Override
    public void update() {
        if (isOutSide()) {
            destroy();
        } else {
            move();
        }
        super.update();
    }

    @Override
    public void move() {
        int x = cover.x() + speed.x();
        int y = cover.y() + speed.y();
        cover.setPoint(x, y);
    }

    public void setHeadDir(@Dir.Head int dir) {
        cover.setHeadDir(dir);
        speed.setMoveDir(dir);
    }
}
