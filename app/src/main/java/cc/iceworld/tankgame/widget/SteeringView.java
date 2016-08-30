package cc.iceworld.tankgame.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import cc.iceworld.tankgame.R;
import cc.iceworld.tankgame.annotation.Dir;

/**
 * Created by cxx on 16/5/7.
 * xx.ch@outlook.com
 */
public class SteeringView extends FrameLayout {
    private Button mLeft;
    private Button mRight;
    private Button mUp;
    private Button mDown;

    private DirectionListener mListener;

    public SteeringView(Context context) {
        super(context);
        init(context, null);
    }

    public SteeringView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SteeringView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mLeft = createButton(context, Gravity.START | Gravity.CENTER_VERTICAL);
        addView(mLeft);
        mRight = createButton(context, Gravity.END | Gravity.CENTER_VERTICAL);
        addView(mRight);
        mUp = createButton(context, Gravity.TOP | Gravity.CENTER_HORIZONTAL);
        addView(mUp);
        mDown = createButton(context, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        addView(mDown);
        if (attrs != null) {
            parseAttrs(context, attrs);
        }
    }

    private void parseAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SteeringView);
        int left = ta.getResourceId(R.styleable.SteeringView_left_background, R.drawable.btn_steer);
        mLeft.setBackgroundResource(left);
        int right = ta.getResourceId(R.styleable.SteeringView_right_background, R.drawable.btn_steer);
        mRight.setBackgroundResource(right);
        int up = ta.getResourceId(R.styleable.SteeringView_up_background, R.drawable.btn_steer);
        mUp.setBackgroundResource(up);
        int down = ta.getResourceId(R.styleable.SteeringView_down_background, R.drawable.btn_steer);
        mDown.setBackgroundResource(down);
        ta.recycle();
    }

    private OnTouchListener mTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    dispatch(v);
                    break;
                case MotionEvent.ACTION_UP:
                    notifyDirectionChanged(Dir.S);
                    break;
            }
            return false;
        }
    };

    private void dispatch(View v) {
        if (v == mLeft) {
            notifyDirectionChanged(Dir.L);
        } else if (v == mUp) {
            notifyDirectionChanged(Dir.U);
        } else if (v == mRight) {
            notifyDirectionChanged(Dir.R);
        } else if (v == mDown) {
            notifyDirectionChanged(Dir.D);
        }
    }

    private Button createButton(Context context, int gravity) {
        Button result = new Button(context);
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, gravity);
        result.setLayoutParams(lp);
        result.setOnTouchListener(mTouchListener);
        return result;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int size = (right - left) / 3;
        resetButtonSize(size);
    }

    private void resetButtonSize(int size) {
        mLeft.setWidth(size);
        mLeft.setHeight(size);
        mRight.setWidth(size);
        mRight.setHeight(size);
        mUp.setWidth(size);
        mUp.setHeight(size);
        mDown.setWidth(size);
        mDown.setHeight(size);
    }

    public void setBackGround(@DrawableRes int left, @DrawableRes int up, @DrawableRes int right, @DrawableRes int down) {
        mLeft.setBackgroundResource(left);
        mUp.setBackgroundResource(up);
        mRight.setBackgroundResource(right);
        mDown.setBackgroundResource(down);
    }

    public void setDirectionListener(DirectionListener listener) {
        mListener = listener;
    }

    public void notifyDirectionChanged(@Dir.Move int dir) {
        if (mListener != null) {
            mListener.onDirectionChanged(dir);
        }
    }

    public interface DirectionListener {
        void onDirectionChanged(@Dir.Move int dir);
    }
}

