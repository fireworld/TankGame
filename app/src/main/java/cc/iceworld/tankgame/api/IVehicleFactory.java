package cc.iceworld.tankgame.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import cc.iceworld.tankgame.annotation.Camp;
import cc.iceworld.tankgame.model.Vehicle;

/**
 * Created by cxx on 16-5-26.
 * xx.ch@outlook.com
 */
public interface IVehicleFactory<T extends Vehicle> {
    @NonNull
    T produce(@Camp int camp);

    @Nullable
    T search(@Camp int camp);

    @NonNull
    T create(@Camp int camp);
}
