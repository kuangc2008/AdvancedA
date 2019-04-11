package myapp.kc.com.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * @author kc create on 4/11/19.
 * @copyright litebrowser
 */
public class RevealFrameLayout extends FrameLayout {
    public static float MIN_RADIUS = 200f;
    public static float MAX_RADIUS;

    Paint p = new Paint();

    private float radius = 200f;
    public ObjectAnimator objectAnimator = null;


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        int width = getWidth();
        int height = getHeight();

        MAX_RADIUS = (float) Math.sqrt(  Math.pow(getWidth() - 130, 2) + Math.pow(getHeight() - 130, 2));


    }

    public RevealFrameLayout(@NonNull Context context) {
        super(context);
        setWillNotDraw(false);

        p.setColor(Color.YELLOW);
    }

    public RevealFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        p.setColor(Color.YELLOW);
    }

    public RevealFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);

        p.setColor(Color.YELLOW);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        canvas.drawCircle(getWidth() - 130 , getHeight() - 130,  radius, p );
    }

    public void setRadius(float radius) {
        this.radius = radius;
        invalidate();
    }

    public void toggle() {

        if (objectAnimator != null && objectAnimator.isRunning()) {
            return;
        }

        if (objectAnimator == null) {
            objectAnimator = ObjectAnimator.ofFloat(this, "radius", MAX_RADIUS, MIN_RADIUS);
            objectAnimator.setDuration(300);
        }

        if (radius  == MAX_RADIUS) {
            objectAnimator.start();
        } else {
            objectAnimator.reverse();
        }


    }

//    public void getRadius(float radius) {
//        thi
//    }


}
