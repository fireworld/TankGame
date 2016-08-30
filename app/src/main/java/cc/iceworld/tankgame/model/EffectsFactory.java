package cc.iceworld.tankgame.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Collection;

import cc.iceworld.tankgame.TankGame;
import cc.iceworld.tankgame.api.IEffectsFactory;
import cc.iceworld.tankgame.util.C;
import cc.iceworld.tankgame.util.Point;

/**
 * Created by cxx on 16/5/26.
 * xx.ch@outlook.com
 */
public class EffectsFactory {
    private enum Factory implements IEffectsFactory {
        explosion {
            @NonNull
            @Override
            public Explosion produce(@NonNull Point p) {
                Explosion e = search(p);
                if (e == null) {
                    e = create(p);
                    TankGame.i().add(e);
                }
                return e;
            }

            @Nullable
            @Override
            public Explosion search(@NonNull Point p) {
                Collection<Effects> col = TankGame.i().effects();
                for (Effects e : col) {
                    if (e.isDestroyed() && e instanceof Explosion) {
                        Explosion ep = (Explosion) e;
                        ep.setPoint(p);
                        ep.reLive();
                        return ep;
                    }
                }
                return null;
            }

            @NonNull
            @Override
            public Explosion create(@NonNull Point p) {
                return new Explosion(p, C.EP.skin());
            }
        }
    }

    public static Explosion explosion(@NonNull Point p) {
        return (Explosion) Factory.explosion.produce(p);
    }
}
