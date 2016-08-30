package cc.iceworld.tankgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.view.SurfaceHolder;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cc.iceworld.tankgame.annotation.Camp;
import cc.iceworld.tankgame.api.Destroyable;
import cc.iceworld.tankgame.model.VehicleFactory;
import cc.iceworld.tankgame.util.Rand;
import cc.iceworld.tankgame.util.ToolBox;

/**
 * Created by cxx on 16/5/28.
 * xx.ch@outlook.com
 */
public class GameDrawer implements SurfaceHolder.Callback {
    private ExecutorService mService;
    private DrawTask mTask;

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mService = Executors.newFixedThreadPool(1);
        mTask = new DrawTask(holder);
        mService.submit(mTask);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        destroy();
    }

    public void start() {
        mTask.mState = DrawTask.STATE_GOING;
    }

    public void pause() {
        mTask.mState = DrawTask.STATE_PAUSE;
    }

    private void destroy() {
        mTask.mState = DrawTask.STATE_DESTROY;
        mService.shutdown();
        mTask = null;
        mService = null;
    }

    private static class DrawTask implements Runnable {
        private static final long TIME_INTERCEPT = 20;
        private static final int COLOR_BACKGROUND = Color.GRAY;

        private static final int STATE_GOING = 0;
        private static final int STATE_PAUSE = 1;
        private static final int STATE_DESTROY = 2;
        private volatile int mState = STATE_PAUSE;

        private SurfaceHolder mHolder;
        private Paint mPaint;

        private DrawTask(@NonNull SurfaceHolder holder) {
            mHolder = holder;
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setTextSize(30);
        }

        @Override
        public void run() {
            Canvas canvas = null;
            while (mState != STATE_DESTROY) {
                if (mState == STATE_GOING) {
                    try {
                        canvas = mHolder.lockCanvas();
                        if (canvas == null) return;
                        canvas.drawColor(COLOR_BACKGROUND);
                        updateSurface(canvas, mPaint);
                        canvas.drawText(format("坦克", TankGame.i().vehicles()), 30, 30, mPaint);
                        canvas.drawText(format("炮弹", TankGame.i().bombs()), 30, 60, mPaint);
                        canvas.drawText(format("特效", TankGame.i().effects()), 30, 90, mPaint);
                    } finally {
                        if (canvas != null) {
                            mHolder.unlockCanvasAndPost(canvas);
                        }
                    }
                }
                SystemClock.sleep(TIME_INTERCEPT);
            }
        }

        private static String format(String name, Collection<? extends Destroyable> col) {
            int total = col.size();
            int live = ToolBox.liveStats(col);
            return String.format("当前%s数: %d  存活数: %d", name, total, live);
        }

        private void updateSurface(@NonNull Canvas canvas, @NonNull Paint paint) {
            while (ToolBox.liveStats(TankGame.i().vehicles()) < 5 && Rand.nextInt(0, 100) > 60) {
                VehicleFactory.robotTank(Camp.RED);
            }
            TankGame.i().drawAll(canvas, paint);
        }
    }
}
