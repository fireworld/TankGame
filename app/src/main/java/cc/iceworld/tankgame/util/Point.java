package cc.iceworld.tankgame.util;

/**
 * Created by cxx on 16-5-24.
 * xx.ch@outlook.com
 */
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void set(Point p) {
        x = p.x;
        y = p.y;
    }

    public int x() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int y() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point newCopy() {
        return new Point(x, y);
    }

    public static int distanceX(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x);
    }

    public static int distanceY(Point p1, Point p2) {
        return Math.abs(p1.y - p2.y);
    }
}
