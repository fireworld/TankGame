package cc.iceworld.tankgame.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Collection;

import cc.iceworld.tankgame.TankGame;
import cc.iceworld.tankgame.annotation.Camp;
import cc.iceworld.tankgame.annotation.Dir;
import cc.iceworld.tankgame.api.IVehicleFactory;
import cc.iceworld.tankgame.util.C;

/**
 * Created by cxx on 16-5-19.
 * xx.ch@outlook.com
 */
public class VehicleFactory {
    private enum Factory implements IVehicleFactory {
        Robot {
            @NonNull
            @Override
            public RobotTank produce(@Camp int camp) {
                RobotTank tank = search(camp);
                if (tank == null) {
                    tank = create(camp);
                    TankGame.i().add(tank);
                }
                while (TankGame.i().collideAny(tank)) {
                    tank.setPoint(C.TK.point());
                }
                return tank;
            }

            @Nullable
            @Override
            public RobotTank search(@Camp int camp) {
                Collection<Vehicle> col = TankGame.i().vehicles();
                for (Vehicle v : col) {
                    if (v.isDestroyed() && v instanceof RobotTank) {
                        RobotTank tank = (RobotTank) v;
                        if (tank.camp() != camp) {
                            tank.setCamp(camp);
                            tank.setSkin(C.TK.skin(camp));
                        }
                        tank.setPoint(C.TK.point());
                        tank.reLive();
                        return tank;
                    }
                }
                return null;
            }

            @NonNull
            @Override
            public RobotTank create(@Camp int camp) {
                return new RobotTank(C.TK.point(),
                        C.TK.skin(camp),
                        camp, C.TK.SPEED, C.TK.HP);
            }
        },

        User {
            @NonNull
            @Override
            public UserTank produce(@Camp int camp) {
                UserTank tank = search(camp);
                if (tank == null) {
                    tank = create(camp);
                    TankGame.i().add(tank);
                }
                while (TankGame.i().collideAny(tank)) {
                    tank.setPoint(C.TK.point());
                }
                return tank;
            }

            @Nullable
            @Override
            public UserTank search(@Camp int camp) {
                Collection<Vehicle> col = TankGame.i().vehicles();
                for (Vehicle v : col) {
                    if (v.isDestroyed() && v instanceof UserTank) {
                        UserTank tank = (UserTank) v;
                        if (tank.camp() != camp) {
                            tank.setCamp(camp);
                            tank.setSkin(C.TK.skin(camp));
                        }
                        tank.setMoveDir(Dir.S);
                        tank.setPoint(C.TK.point());
                        tank.reLive();
                        return tank;
                    }
                }
                return null;
            }

            @NonNull
            @Override
            public UserTank create(@Camp int camp) {
                return new UserTank(C.TK.point(),
                        C.TK.skin(camp),
                        camp, C.TK.SPEED, 50);
            }
        }
    }

    public static RobotTank robotTank(@Camp int camp) {
        return (RobotTank) Factory.Robot.produce(camp);
    }

    public static UserTank userTank(@Camp int camp) {
        return (UserTank) Factory.User.produce(camp);
    }
}
