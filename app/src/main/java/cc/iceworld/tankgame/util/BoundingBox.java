package cc.iceworld.tankgame.util;

import android.support.annotation.NonNull;

import cc.iceworld.tankgame.annotation.Dir;

/**
 * Created by cxx on 16-5-24.
 * xx.ch@outlook.com
 */
public class BoundingBox {
    protected int radiusX;
    protected int radiusY;
    protected Point center;

    /**
     * @param p      中心点坐标
     * @param width  宽
     * @param height 高
     */
    public BoundingBox(@NonNull Point p, int width, int height) {
        this.center = p;
        radiusX = width >> 1;
        radiusY = height >> 1;
    }

    public void setPoint(@NonNull Point p) {
        center.set(p);
    }

    public void setPoint(int x, int y) {
        center.set(x, y);
    }

    public int x() {
        return center.x();
    }

    public int y() {
        return center.y();
    }

    public Point point() {
        return center;
    }

    public int offsetX(@Dir.Head int dir) {
        switch (dir) {
            case Dir.L:
                return center.x() - radiusX;
            case Dir.R:
                return center.x() + radiusX;
            case Dir.U:
            case Dir.D:
                return center.x();
            default:
                throw new IllegalArgumentException(String.format("dir[%d, %d] = %d", Dir.L, Dir.D, dir));
        }
    }

    public int offsetY(@Dir.Head int dir) {
        switch (dir) {
            case Dir.U:
                return center.y() - radiusY;
            case Dir.D:
                return center.y() + radiusY;
            case Dir.L:
            case Dir.R:
                return center.y();
            default:
                throw new IllegalArgumentException(String.format("dir[%d, %d] = %d", Dir.L, Dir.D, dir));
        }
    }

    public int left() {
        return center.x() - radiusX;
    }

    public void setLeft(int x) {
        center.setX(x + radiusX);
    }

    public int right() {
        return center.x() + radiusX;
    }

    public void setRight(int x) {
        center.setX(x - radiusX);
    }

    public int top() {
        return center.y() - radiusY;
    }

    public void setTop(int y) {
        center.setY(y + radiusY);
    }

    public int bottom() {
        return center.y() + radiusY;
    }

    public void setBottom(int y) {
        center.setY(y - radiusY);
    }

    public final boolean intersect(@NonNull BoundingBox box) {
        return Point.distanceX(center, box.center) <= radiusX + box.radiusX
                && Point.distanceY(center, box.center) <= radiusY + box.radiusY;
    }
}
