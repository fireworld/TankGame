package cc.iceworld.tankgame.util;

import java.util.Random;

/**
 * Created by cxx on 16-5-19.
 * xx.ch@outlook.com
 */
public class Rand {
    public static final Random R = new Random();

    /**
     * 随机返回 [min, max] 中的一个整数
     */
    public static int nextInt(int min, int max) {
        return R.nextInt(max + 1 - min) + min;
    }

    /**
     * 随机返回 [0, max) 中的一个整数
     */
    public static int nextInt(int max) {
        return R.nextInt(max);
    }

    private Rand() {
        throw new AssertionError("no instance");
    }
}
