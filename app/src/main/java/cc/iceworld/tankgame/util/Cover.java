package cc.iceworld.tankgame.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;

import cc.iceworld.tankgame.annotation.Dir;

/**
 * Created by cxx on 16-5-24.
 * xx.ch@outlook.com
 */
public class Cover {
    @Dir.Head
    private int headDir = Dir.nextHeadDir();

    private BitmapBox[] boxes;
    private Point center;

    /**
     * @param center 模型中心点的坐标
     * @param skin   模型的外观，应包括 4 张图片，且朝向应依次为左、上、右、下。
     */
    public Cover(@NonNull Point center, @NonNull Bitmap[] skin) {
        this.center = center;
        boxes = BitmapBox.fromBitmaps(this.center, skin);
    }

    /**
     * @param center 模型中心点的坐标
     * @param skin   模型的外观，应包括 4 张图片，且朝向应依次为左、上、右、下。
     * @param dir    朝向
     */
    public Cover(@NonNull Point center, @NonNull Bitmap[] skin, @Dir.Head int dir) {
        this(center, skin);
        headDir = dir;
    }

    public void draw(@NonNull Canvas canvas, @NonNull Paint paint) {
        BitmapBox box = boxes[headDir];
        canvas.drawBitmap(box.asBitmap(), box.left(), box.top(), paint);
    }

    public void setSkin(@NonNull Bitmap[] skin) {
        boxes = BitmapBox.fromBitmaps(center, skin);
    }

    public BitmapBox box() {
        return boxes[headDir];
    }

    public void setHeadDir(@Dir.Head int dir) {
        headDir = dir;
    }

    public void setPoint(@NonNull Point p) {
        center.set(p);
    }

    public void setPoint(int x, int y) {
        center.set(x, y);
    }

    public Point point() {
        return center;
    }

    public int x() {
        return center.x();
    }

    public int y() {
        return center.y();
    }

    public int left() {
        return boxes[headDir].left();
    }

    public void setLeft(int x) {
        boxes[headDir].setLeft(x);
    }

    public int right() {
        return boxes[headDir].right();
    }

    public void setRight(int x) {
        boxes[headDir].setRight(x);
    }

    public int top() {
        return boxes[headDir].top();
    }

    public void setTop(int y) {
        boxes[headDir].setTop(y);
    }

    public int bottom() {
        return boxes[headDir].bottom();
    }

    public void setBottom(int y) {
        boxes[headDir].setBottom(y);
    }

    public Point offsetPoint() {
        return new Point(offsetX(), offsetY());
    }

    public int offsetX() {
        return boxes[headDir].offsetX(headDir);
    }

    public int offsetY() {
        return boxes[headDir].offsetY(headDir);
    }

    @Dir.Head
    public int headDir() {
        return headDir;
    }
}
