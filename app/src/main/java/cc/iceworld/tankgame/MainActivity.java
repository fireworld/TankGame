package cc.iceworld.tankgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.CompoundButton;
import android.widget.Switch;

import cc.iceworld.tankgame.annotation.Camp;
import cc.iceworld.tankgame.annotation.Dir;
import cc.iceworld.tankgame.model.UserTank;
import cc.iceworld.tankgame.model.VehicleFactory;
import cc.iceworld.tankgame.widget.FireView;
import cc.iceworld.tankgame.widget.SteeringView;

public class MainActivity extends Activity implements CompoundButton.OnCheckedChangeListener, SteeringView.DirectionListener, FireView.FireListener {
    private UserTank mTank;
    private GameDrawer mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TankGame.i().init(getApplicationContext());

        SurfaceView sv = (SurfaceView) findViewById(R.id.surface_view);
        mDrawer = new GameDrawer();
        sv.getHolder().addCallback(mDrawer);

        mTank = VehicleFactory.userTank(Camp.USER);

        SteeringView steer = (SteeringView) findViewById(R.id.steering_view);
        steer.setDirectionListener(this);

        FireView fire = (FireView) findViewById(R.id.fire_view);
        fire.setFireListenerr(this);

        Switch sw = (Switch) findViewById(R.id.switch_button);
        sw.setOnCheckedChangeListener(this);
    }

    @Override
    protected void onPause() {
        mDrawer.pause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        TankGame.i().destroy();
        super.onDestroy();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            mDrawer.start();
            buttonView.setText("暂停");
        } else {
            mDrawer.pause();
            buttonView.setText("开始");
        }
    }

    @Override
    public void onDirectionChanged(@Dir.Move int dir) {
        if (!mTank.isDestroyed()) {
            mTank.setMoveDir(dir);
        }
    }

    @Override
    public void onFireStateChanged(@FireView.FireState int state) {
        if (state == FireView.FIRE_A) {
            if (!mTank.isDestroyed()) {
                mTank.fire(null);
            }
        } else if (state == FireView.FIRE_B) {
            if (mTank.isDestroyed()) {
                mTank = VehicleFactory.userTank(Camp.USER);
            }
        }
    }
}
