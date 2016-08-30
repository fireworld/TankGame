package cc.iceworld.tankgame.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.annotation.NonNull;

import cc.iceworld.tankgame.R;

/**
 * Created by cxx on 16/5/26.
 * xx.ch@outlook.com
 */
public class MediaService {
    private static SoundPool soundPool;
    private static int soundId;

    public static void init(@NonNull Context ctx) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundPool = new SoundPool.Builder().build();
        } else {
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        }
        soundId = soundPool.load(ctx, R.raw.explosion, 1);
    }

    public static void play() {
        soundPool.play(soundId, 0.3F, 0.5F, 0, 0, 1.0F);
    }

    public static void release() {
        soundPool.release();
    }
}
