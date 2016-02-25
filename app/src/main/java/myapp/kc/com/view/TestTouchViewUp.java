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
public class TestTouchViewUp extends View {
    public TestTouchViewUp(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("kcc", "up  onTouchEvent" + event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i("kcc", "up  onTouchEvent" + event.getAction());
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Log.i("kcc", "up  dispatchDraw");
        super.dispatchDraw(canvas);
    }

}
