package myapp.kc.com.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by kuangcheng01 on 2016/2/25.
 */
public class TestTouchViewBelow extends View {
    public TestTouchViewBelow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean onTouchEvent = super.onTouchEvent(event);
        Log.i("kcc", "below  onTouchEvent" + event.getAction() + "   return "  +  onTouchEvent);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            return onTouchEvent;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i("kcc", "below  dispatchTouchEvent" + event.getAction());
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Log.i("kcc", "below  dispatchDraw");
        super.dispatchDraw(canvas);
    }

}
