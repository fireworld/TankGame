package cc.iceworld.tankgame.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import cc.iceworld.tankgame.annotation.Camp;
import cc.iceworld.tankgame.annotation.Dir;
import cc.iceworld.tankgame.model.Bomb;
import cc.iceworld.tankgame.util.Point;

/**
 * Created by cxx on 16-5-26.
 * xx.ch@outlook.com
 */
public interface IBombFactory<T extends Bomb> {

    @NonNull
    T produce(@NonNull Point p, @Camp int camp, @Dir.Head int dir);

    @Nullable
    T search(@NonNull Point p, @Camp int camp, @Dir.Head int dir);

    @NonNull
    T create(@NonNull Point p, @Camp int camp, @Dir.Head int dir);
}
