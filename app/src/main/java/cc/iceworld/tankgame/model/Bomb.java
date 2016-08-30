package cc.iceworld.tankgame.model;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import cc.iceworld.tankgame.TankGame;
import cc.iceworld.tankgame.annotation.Camp;
import cc.iceworld.tankgame.annotation.Dir;
import cc.iceworld.tankgame.api.Explosive;
import cc.iceworld.tankgame.util.MediaService;
import cc.iceworld.tankgame.util.Point;

/**
 * Created by cxx on 16/5/19.
 * xx.ch@outlook.com
 */
public class Bomb extends BaseModel implements Explosive {
    protected boolean isDestroyed = false;
    protected int power;

    /**
     * @param p     模型中心点的坐标
     * @param skin  模型的外观，应包括 4 张图片，且朝向应依次为左、上、右、下。
     * @param camp  阵营，用以进行碰撞或攻击检测
     * @param dir   炮弹的朝向
     * @param power 炮弹所能造成的伤害值
     */
    protected Bomb(@NonNull Point p, @NonNull Bitmap[] skin, @Camp int camp, @Dir.Head int dir, int power) {
        super(p, skin, camp);
        this.cover.setHeadDir(dir);
        this.power = power;
    }

    @Override
    public void update() {
        if (TankGame.i().collideAny(this)) {
            destroy();
            explode();
        }
    }

    @Override
    protected final void reLive() {
        isDestroyed = false;
    }

    @Override
    public void destroy() {
        isDestroyed = true;
    }

    @Override
    public boolean isDestroyed() {
        return isDestroyed;
    }

    @NonNull
    @Override
    public Explosion explode() {
        MediaService.play();
        return EffectsFactory.explosion(cover.point().newCopy());
    }

    @Override
    public int power() {
        return power;
    }
}