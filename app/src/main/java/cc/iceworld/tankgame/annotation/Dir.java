package cc.iceworld.tankgame.annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import cc.iceworld.tankgame.util.Rand;

/**
 * Created by cxx on 16-5-27.
 * xx.ch@outlook.com
 */
public class Dir {
    public static final int L = 0;
    public static final int U = 1;
    public static final int R = 2;
    public static final int D = 3;
    public static final int S = 4;

    /**
     * 随机返回 [0, 3] 中的一个整数，表示朝向，分别为左、上、右、下
     */
    @SuppressWarnings("WrongConstant")
    @Head
    public static int nextHeadDir() {
        return Rand.nextInt(Dir.L, Dir.D);
    }

    /**
     * 随机返回 [0, 4] 中的一个整数，表示移动方向，分别为左、上、右、下和停止
     */
    @SuppressWarnings("WrongConstant")
    @Move
    public static int nextMoveDir() {
        return Rand.nextInt(Dir.L, Dir.S);
    }

    private Dir() {
        throw new AssertionError("no instance");
    }

    @IntDef({Dir.L, Dir.U, Dir.R, Dir.D})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Head {
    }

    @IntDef({Dir.L, Dir.U, Dir.R, Dir.D, Dir.S})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Move {
    }
}
