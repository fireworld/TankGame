package cc.iceworld.tankgame.api;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;

/**
 * Created by cxx on 16-5-19.
 * xx.ch@outlook.com
 */
public interface Drawable {

    void draw(@NonNull Canvas canvas, @NonNull Paint paint);

    void update();
}
