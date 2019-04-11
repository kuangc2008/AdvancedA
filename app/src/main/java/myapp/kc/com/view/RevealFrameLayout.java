package myapp.kc.com.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

/**
 * @author kc create on 4/11/19.
 * @copyright litebrowser
 */
public class RevealFrameLayout extends CardView {
    public static float MIN_RADIUS ;
    public static float MAX_RADIUS;

    private float radiusFac = 0.35f;
    private Point center = new Point();

    Paint p = new Paint();

    private float coRadius;
    public ObjectAnimator objectAnimator = null;


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        coRadius = getWidth() *radiusFac;
        MIN_RADIUS = coRadius;

        Log.i("kcc", "onLayout->" + coRadius + "  mian->" + MIN_RADIUS);

        center.x = (int) (getWidth() - MIN_RADIUS * 0.7f);
        center.y = (int) (getHeight() - MIN_RADIUS * 0.7f);

        MAX_RADIUS = (float) Math.sqrt(  Math.pow(center.x, 2) + Math.pow(center.y, 2));

        objectAnimator = null;
    }

    public RevealFrameLayout(@NonNull Context context) {
        super(context);
        setWillNotDraw(false);

        p.setColor(Color.RED);
    }

    public RevealFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        p.setColor(Color.RED);
    }

    public RevealFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);

        p.setColor(Color.RED);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        canvas.drawCircle(center.x , center.y , coRadius, p );
    }

    public void setCoRadius(float radius) {
        Log.i("kcc", "setRadius ::::::::::::::" + radius);
        this.coRadius = radius;
        invalidate();
    }

    public void toggle() {
        if (objectAnimator == null) {
            objectAnimator = ObjectAnimator.ofFloat(this, "coRadius", MIN_RADIUS, MAX_RADIUS);
            objectAnimator.setDuration(300);
        }

        if (objectAnimator.isRunning() || objectAnimator.isStarted()) {
            return;
        }

        Log.i("kcc", "radis" + coRadius);
        if (Math.floor(coRadius)  <= MIN_RADIUS) {
            objectAnimator.start();
        } else {
            objectAnimator.reverse();
        }


    }




}
