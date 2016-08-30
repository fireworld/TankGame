package cc.iceworld.tankgame.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Collection;

import cc.iceworld.tankgame.TankGame;
import cc.iceworld.tankgame.annotation.Camp;
import cc.iceworld.tankgame.annotation.Dir;
import cc.iceworld.tankgame.api.IBombFactory;
import cc.iceworld.tankgame.util.C;
import cc.iceworld.tankgame.util.Point;

/**
 * Created by cxx on 16-5-20.
 * xx.ch@outlook.com
 */

public class BombFactory {
    private enum Factory implements IBombFactory {
        FLY {
            @NonNull
            @Override
            public FlyBomb produce(@NonNull Point p, @Camp int camp, @Dir.Head int dir) {
                FlyBomb bomb = search(p, camp, dir);
                if (bomb == null) {
                    bomb = create(p, camp, dir);
                    TankGame.i().add(bomb);
                }
                return bomb;
            }

            @Nullable
            @Override
            public FlyBomb search(@NonNull Point p, @Camp int camp, @Dir.Head int dir) {
                Collection<Bomb> col = TankGame.i().bombs();
                for (Bomb b : col) {
                    if (b.isDestroyed() && b instanceof FlyBomb) {
                        FlyBomb bomb = (FlyBomb) b;
                        if (bomb.camp() != camp) {
                            bomb.setCamp(camp);
                            bomb.setSkin(C.FB.skin(camp));
                        }
                        bomb.setPoint(p);
                        bomb.setHeadDir(dir);
                        bomb.reLive();
                        return bomb;
                    }
                }
                return null;
            }

            @NonNull
            @Override
            public FlyBomb create(@NonNull Point p, @Camp int camp, @Dir.Head int dir) {
                return new FlyBomb(p, C.FB.skin(camp), camp, dir, C.FB.POWER, C.FB.SPEED);
            }
        }
    }

    public static FlyBomb flyBomb(@NonNull Point p, @Camp int camp, @Dir.Head int dir) {
        return (FlyBomb) Factory.FLY.produce(p, camp, dir);
    }
}
