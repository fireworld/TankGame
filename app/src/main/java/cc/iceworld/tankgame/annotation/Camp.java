package cc.iceworld.tankgame.annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by cxx on 16-5-19.
 * xx.ch@outlook.com
 */
@IntDef({Camp.RED, Camp.BLUE, Camp.USER})
@Retention(RetentionPolicy.SOURCE)
public @interface Camp {
    int RED = 0;
    int BLUE = 4;
    int USER = 8;
}
