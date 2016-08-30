package cc.iceworld.tankgame.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

import java.util.Arrays;

import cc.iceworld.tankgame.R;
import cc.iceworld.tankgame.annotation.Camp;
import cc.iceworld.tankgame.annotation.Dir;

/**
 * Created by cxx on 16-5-19.
 * xx.ch@outlook.com
 */
public class C {
    public static final Area AREA;
    private static Context appContext;

    static {
        DisplayMetrics display = Resources.getSystem().getDisplayMetrics();
        AREA = new Area(0, 0, display.widthPixels, display.heightPixels);
    }

    public static void init(@NonNull Context appContext) {
        C.appContext = appContext;
    }

    public static class TK {
        public static final int HP = 10;
        public static final int SPEED;
        private static Area area;
        private static Bitmap[] skins;

        static {
            SPEED = C.AREA.h >> 7;
            final int ss = C.AREA.h >> 3;
            Resources res = C.appContext.getResources();
            skins = new Bitmap[12];
            skins[Camp.RED + Dir.L] = ToolBox.decodeScaled(res, R.mipmap.red_l, ss);
            skins[Camp.RED + Dir.U] = ToolBox.decodeScaled(res, R.mipmap.red_u, ss);
            skins[Camp.RED + Dir.R] = ToolBox.decodeScaled(res, R.mipmap.red_r, ss);
            skins[Camp.RED + Dir.D] = ToolBox.decodeScaled(res, R.mipmap.red_d, ss);
            skins[Camp.BLUE + Dir.L] = ToolBox.decodeScaled(res, R.mipmap.blue_l, ss);
            skins[Camp.BLUE + Dir.U] = ToolBox.decodeScaled(res, R.mipmap.blue_u, ss);
            skins[Camp.BLUE + Dir.R] = ToolBox.decodeScaled(res, R.mipmap.blue_r, ss);
            skins[Camp.BLUE + Dir.D] = ToolBox.decodeScaled(res, R.mipmap.blue_d, ss);
            skins[Camp.USER + Dir.L] = ToolBox.decodeScaled(res, R.mipmap.user_l, ss);
            skins[Camp.USER + Dir.U] = ToolBox.decodeScaled(res, R.mipmap.user_u, ss);
            skins[Camp.USER + Dir.R] = ToolBox.decodeScaled(res, R.mipmap.user_r, ss);
            skins[Camp.USER + Dir.D] = ToolBox.decodeScaled(res, R.mipmap.user_d, ss);
            int width = skins[0].getWidth();
            int height = skins[0].getHeight();
            final int ls = width > height ? width : height;
            area = new Area(ls, ls, C.AREA);
        }

        public static Bitmap[] skin(@Camp int camp) {
            return Arrays.copyOfRange(skins, camp, camp + 4);
        }

        public static Point point() {
            return new Point(nextX(), nextY());
        }

        public static int nextX() {
            return Rand.nextInt(area.l, area.r);
        }

        public static int nextY() {
            return Rand.nextInt(area.t, area.b);
        }
    }

    public static class FB {
        public static final int POWER = 10;
        public static final int SPEED;
        private static Bitmap[] skins;

        static {
            SPEED = C.AREA.h >> 5;
            final int ss = C.AREA.h >> 6;
            Resources res = C.appContext.getResources();
            skins = new Bitmap[12];
            skins[Camp.RED + Dir.L] = ToolBox.decodeScaled(res, R.mipmap.shell_l, ss);
            skins[Camp.RED + Dir.U] = ToolBox.decodeScaled(res, R.mipmap.shell_u, ss);
            skins[Camp.RED + Dir.R] = ToolBox.decodeScaled(res, R.mipmap.shell_r, ss);
            skins[Camp.RED + Dir.D] = ToolBox.decodeScaled(res, R.mipmap.shell_d, ss);
            skins[Camp.BLUE + Dir.L] = skins[0];
            skins[Camp.BLUE + Dir.U] = skins[1];
            skins[Camp.BLUE + Dir.R] = skins[2];
            skins[Camp.BLUE + Dir.D] = skins[3];
            skins[Camp.USER + Dir.L] = skins[0];
            skins[Camp.USER + Dir.U] = skins[1];
            skins[Camp.USER + Dir.R] = skins[2];
            skins[Camp.USER + Dir.D] = skins[3];
        }

        public static Bitmap[] skin(@Camp int camp) {
            return Arrays.copyOfRange(skins, camp, camp + 4);
        }
    }

    public static class EP {
        private static Bitmap[] skins;

        static {
            final int ss1 = C.AREA.h >> 7;
            final int ss2 = C.AREA.h >> 6;
            final int ss3 = C.AREA.h >> 5;
            final int ss4 = C.AREA.h >> 4;
            final int ss5 = C.AREA.h >> 3;
            Resources res = C.appContext.getResources();
            skins = new Bitmap[9];
            skins[0] = ToolBox.decodeScaled(res, R.mipmap.explosion, ss1);
            skins[1] = ToolBox.decodeScaled(res, R.mipmap.explosion, ss2);
            skins[2] = ToolBox.decodeScaled(res, R.mipmap.explosion, ss3);
            skins[3] = ToolBox.decodeScaled(res, R.mipmap.explosion, ss4);
            skins[4] = ToolBox.decodeScaled(res, R.mipmap.explosion, ss5);
            skins[5] = ToolBox.decodeScaled(res, R.mipmap.explosion, ss4);
            skins[6] = ToolBox.decodeScaled(res, R.mipmap.explosion, ss3);
            skins[7] = ToolBox.decodeScaled(res, R.mipmap.explosion, ss2);
            skins[8] = ToolBox.decodeScaled(res, R.mipmap.explosion, ss1);
        }

        public static Bitmap[] skin() {
            return skins;
        }
    }
}
