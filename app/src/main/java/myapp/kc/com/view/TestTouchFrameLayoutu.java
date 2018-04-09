package myapp.kc.com.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by kuangcheng01 on 2016/2/25.
 */
public class TestTouchFrameLayoutu extends FrameLayout {

    float mdownX = 0;

    public TestTouchFrameLayoutu(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.w("kcc", " TestTouchFrameLayoutu onInterceptTouchEvent " + ev.getAction(), new Exception());
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            mdownX = ev.getX();
            return false;
        }

        if(ev.getX() - mdownX > 200) {
            return false;
        }

        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.w("kcc", " TestTouchFrameLayoutu dispatchTouchEvent " + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Log.w("kcc", " TestTouchFrameLayoutu dispatchDraw " );
        super.dispatchDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean s = super.onTouchEvent(event);;
        Log.w("kcc", " TestTouchFrameLayoutu onTouchEvent " + event.getAction() + "  aaa-" + s);
        return true;
    }
}
