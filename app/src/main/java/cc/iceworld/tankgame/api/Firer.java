package cc.iceworld.tankgame.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import cc.iceworld.tankgame.model.FlyBomb;

/**
 * Created by cxx on 16-5-19.
 * xx.ch@outlook.com
 */
public interface Firer {

    @NonNull
    FlyBomb fire(@Nullable FlyBomb bomb);
}
