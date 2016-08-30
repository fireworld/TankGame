package cc.iceworld.tankgame.util;

import cc.iceworld.tankgame.annotation.Dir;

/**
 * Created by cxx on 16-5-19.
 * xx.ch@outlook.com
 */
public class Speed {
    private final int x;
    private final int y;
    @Dir.Move
    private int moveDir = Dir.S;

    public Speed(int speed) {
        this(speed, speed);
    }

    public Speed(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Speed(int x, int y, @Dir.Move int dir) {
        this.x = x;
        this.y = y;
        this.moveDir = dir;
    }

    public int x() {
        switch (moveDir) {
            case Dir.L:
                return -x;
            case Dir.R:
                return x;
            case Dir.U:
            case Dir.D:
            case Dir.S:
                return 0;
            default:
                throw new IllegalStateException(String.format("dir[%d, %d] = %d", Dir.L, Dir.S, moveDir));
        }
    }

    public int y() {
        switch (moveDir) {
            case Dir.U:
                return -y;
            case Dir.D:
                return y;
            case Dir.L:
            case Dir.R:
            case Dir.S:
                return 0;
            default:
                throw new IllegalStateException(String.format("dir[%d, %d] = %d", Dir.L, Dir.S, moveDir));
        }
    }

    public void setMoveDir(@Dir.Move int dir) {
        moveDir = dir;
    }

    @Dir.Move
    public int moveDir() {
        return moveDir;
    }
}
