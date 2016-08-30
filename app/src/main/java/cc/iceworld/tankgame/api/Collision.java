package cc.iceworld.tankgame.api;

import android.support.annotation.NonNull;

import cc.iceworld.tankgame.util.BoundingBox;


/**
 * Created by cxx on 16-5-19.
 * xx.ch@outlook.com
 */
public interface Collision {

    boolean collide(@NonNull Collision collision);

    @NonNull
    BoundingBox box();
}
