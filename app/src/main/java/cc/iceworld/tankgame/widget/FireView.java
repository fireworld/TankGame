package cc.iceworld.tankgame.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import cc.iceworld.tankgame.R;

/**
 * Created by cxx on 16-5-20.
 * xx.ch@outlook.com
 */
public class FireView extends FrameLayout {
    public static final int FIRE_A = 0;
    public static final int FIRE_B = 1;
    public static final int FIRE_S = 2;

    private Button mA;
    private Button mB;

    private FireListener mListener;

    public FireView(Context context) {
        super(context);
        init(context, null);
    }

    public FireView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public FireView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mA = createButton(context, Gravity.START | Gravity.TOP);
        addView(mA);
        mB = createButton(context, Gravity.END | Gravity.BOTTOM);
        addView(mB);
        if (attrs != null) {
            parseAttrs(context, attrs);
        }
    }

    private void parseAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SteeringView);
        int a = ta.getResourceId(R.styleable.FireView_a_background, R.drawable.btn_steer);
        mA.setBackgroundResource(a);
        int b = ta.getResourceId(R.styleable.FireView_b_background, R.drawable.btn_steer);
        mB.setBackgroundResource(b);
        ta.recycle();
    }

    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    dispatch(v);
                    break;
                case MotionEvent.ACTION_UP:
                    notifyFireStateChanged(FIRE_S);
                    break;
            }
            return false;
        }
    };

    private void dispatch(View v) {
        if (v == mA) {
            notifyFireStateChanged(FIRE_A);
        } else if (v == mB) {
            notifyFireStateChanged(FIRE_B);
        }
    }

    private Button createButton(Context context, int gravity) {
        Button result = new Button(context);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, gravity);
        result.setLayoutParams(lp);
        result.setOnTouchListener(mTouchListener);
        return result;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int size = (right - left) / 2;
        resetButtonSize(size);
    }

    private void resetButtonSize(int size) {
        mA.setWidth(size);
        mA.setHeight(size);
        mB.setWidth(size);
        mB.setHeight(size);
    }

    public void setBackGround(@DrawableRes int a, @DrawableRes int b) {
        mA.setBackgroundResource(a);
        mB.setBackgroundResource(b);
    }

    public void setFireListenerr(FireListener listener) {
        mListener = listener;
    }

    public void notifyFireStateChanged(@FireState int state) {
        if (mListener != null) {
            mListener.onFireStateChanged(state);
        }
    }

    public interface FireListener {
        void onFireStateChanged(@FireState int state);
    }

    @IntDef({FIRE_A, FIRE_B, FIRE_S})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FireState {
    }
}
