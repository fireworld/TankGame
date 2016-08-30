package cc.iceworld.tankgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.LinkedList;

import cc.iceworld.tankgame.model.Bomb;
import cc.iceworld.tankgame.model.Effects;
import cc.iceworld.tankgame.model.Explosion;
import cc.iceworld.tankgame.model.Vehicle;
import cc.iceworld.tankgame.util.C;
import cc.iceworld.tankgame.util.MediaService;
import cc.iceworld.tankgame.util.ToolBox;

/**
 * Created by cxx on 16-5-19.
 * xx.ch@outlook.com
 */
public class TankGame {
    public static final int LIMIT_VEHICLES = 60;
    public static final int LIMIT_BOMB = 200;

    private static TankGame instance = new TankGame();

    private boolean isInitialized = false;
    private final Collection<Vehicle> vehicles = new LinkedList<>();
    private final Collection<Bomb> bombs = new LinkedList<>();
    private final Collection<Effects> effects = new LinkedList<>();

    public static TankGame i() {
        return instance;
    }

    public Collection<Vehicle> vehicles() {
        return vehicles;
    }

    public Collection<Bomb> bombs() {
        return bombs;
    }

    public Collection<Effects> effects() {
        return effects;
    }

    public boolean collideAny(@NonNull Vehicle vehicle) {
        for (Vehicle v : vehicles) {
            if (!v.isDestroyed() && vehicle != v && vehicle.collide(v)) {
                return true;
            }
        }
        return false;
    }

    public boolean collideAny(@NonNull Bomb bomb) {
        for (Vehicle v : vehicles) {
            if (!v.isDestroyed() && v.camp() != bomb.camp() && bomb.collide(v)) {
                v.breakDown(bomb.power());
                return true;
            }
        }
        return false;
    }

    public boolean add(Vehicle v) {
        return vehicles.add(v);
    }

    public boolean add(Bomb bomb) {
        return bombs.add(bomb);
    }

    public boolean add(Explosion e) {
        return effects.add(e);
    }

    public void drawAll(@NonNull Canvas canvas, @NonNull Paint paint) {
        ToolBox.clearDestroyed(vehicles, LIMIT_VEHICLES);
        ToolBox.clearDestroyed(bombs, LIMIT_BOMB);
        ToolBox.draw(canvas, paint, vehicles);
        ToolBox.draw(canvas, paint, bombs);
        ToolBox.draw(canvas, paint, effects);
    }

    public void init(@NonNull Context appContext) {
        if (!isInitialized) {
            ToolBox.checkNull(appContext);
            C.init(appContext);
            MediaService.init(appContext);
            isInitialized = true;
        }
    }

    public void destroy() {
        vehicles.clear();
        bombs.clear();
        effects.clear();
    }
}
