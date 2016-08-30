package cc.iceworld.tankgame.model;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import cc.iceworld.tankgame.TankGame;
import cc.iceworld.tankgame.annotation.Camp;
import cc.iceworld.tankgame.annotation.Dir;
import cc.iceworld.tankgame.api.Movable;
import cc.iceworld.tankgame.util.Point;
import cc.iceworld.tankgame.util.Speed;

/**
 * Created by cxx on 16-5-19.
 * xx.ch@outlook.com
 */
public class Vehicle extends BaseModel implements Movable {
    private Point last;
    private Speed speed;
    private int totalHp;
    private int hp;

    /**
     * @param p       模型中心点的坐标
     * @param skin    模型的外观，应包括 4 张图片，且朝向应依次为左、上、右、下。
     * @param camp    阵营，用以进行碰撞或攻击检测
     * @param speed   截具行进速度
     * @param totalHp 截具的生命值
     */
    protected Vehicle(@NonNull Point p, @NonNull Bitmap[] skin, @Camp int camp, int speed, int totalHp) {
        super(p, skin, camp);
        this.last = p.newCopy();
        this.speed = new Speed(speed);
        this.totalHp = totalHp;
        this.hp = this.totalHp;
    }

    @Override
    public void update() {
        move();
    }

    @Override
    public void move() {
        int x = cover.x() + speed.x();
        int y = cover.y() + speed.y();
        forward(x, y);
        fixLocation();
        if (TankGame.i().collideAny(this)) {
            back();
        }
    }

    private void forward(int x, int y) {
        last.set(cover.point());
        cover.setPoint(x, y);
    }

    private void back() {
        cover.setPoint(last);
    }

    public final void setMoveDir(@Dir.Move int dir) {
        speed.setMoveDir(dir);
        if (dir != Dir.S) {
            cover.setHeadDir(dir);
        }
    }

    @Dir.Move
    public int moveDir() {
        return speed.moveDir();
    }

    @Override
    public boolean isDestroyed() {
        return hp <= 0;
    }

    @Override
    public void destroy() {
        hp = 0;
    }

    public void breakDown(int hp) {
        this.hp -= hp;
        if (this.hp < 0) {
            this.hp = 0;
        }
    }

    public void repair(int hp) {
        this.hp += hp;
        if (this.hp > totalHp) {
            this.hp = totalHp;
        }
    }

    @Override
    protected final void reLive() {
        this.hp = totalHp;
    }

    public final int hp() {
        return hp;
    }
}
