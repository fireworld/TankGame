package cc.iceworld.tankgame.api;

import android.support.annotation.NonNull;

import cc.iceworld.tankgame.model.Explosion;

/**
 * Created by cxx on 16/5/19.
 * xx.ch@outlook.com
 */
public interface Explosive {

    @NonNull
    Explosion explode();

    int power();
}
