package cc.iceworld.tankgame.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import cc.iceworld.tankgame.model.Effects;
import cc.iceworld.tankgame.util.Point;

/**
 * Created by cxx on 16/5/26.
 * xx.ch@outlook.com
 */
public interface IEffectsFactory<T extends Effects> {
    @NonNull
    T produce(@NonNull Point p);

    @Nullable
    T search(@NonNull Point p);

    @NonNull
    T create(@NonNull Point p);
}
