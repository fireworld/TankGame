package cc.iceworld.tankgame.util;

/**
 * Created by cxx on 16-5-19.
 * xx.ch@outlook.com
 */
public class Area {
    public final int l;
    public final int t;
    public final int r;
    public final int b;
    public final int h;
    public final int w;

    public Area(int l, int t, int r, int b) {
        this.l = l;
        this.t = t;
        this.r = r;
        this.b = b;
        this.h = this.b - this.t;
        this.w = this.r - this.l;
    }

    public Area(int width, int height, Area area) {
        int hw = width >> 1;
        int hh = height >> 1;
        this.l = area.l + hw;
        this.t = area.t + hh;
        this.r = area.r - hw;
        this.b = area.b - hh;
        this.h = b - t;
        this.w = r - l;
    }
}
