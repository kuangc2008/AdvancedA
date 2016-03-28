package myapp.kc.com.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ScrollView;

/**
 * Created by kuangcheng01 on 2016/3/26.
 */
public class MyScrollView extends ScrollView {
    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    public void scrollTo(int x, int y) {
        Log.e("kcc", "MyScrollView scrollTo" + " y" + y);
        super.scrollTo(x, y);
    }

    @Override
    public void scrollBy(int x, int y) {
        Log.e("kcc", " MyScrollView scrollBy" + " y" + y);
        super.scrollBy(x, y);
    }

    @Override
    protected boolean awakenScrollBars() {
        Log.e("kcc", "MyScrollView awakenScrollBars: " + "height->" + getHeight());
        return super.awakenScrollBars();
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        Log.e("kcc", "MyScrollView awakenScrollBars: " + "scrollY->" + scrollY + "  deltaY" + deltaY + "  scrolRingY" + scrollRangeY);
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }
//
//    @Override
//    protected void onOverScrolled(int scrollX, int scrollY,
//                                  boolean clampedX, boolean clampedY) {
//        Log.e("kcc", "MyScrollView onOverScrolled: " + "scrollY->" + scrollY + "  clampedY" + clampedY);
//        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
//    }
//
//    @Override
//    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
//        Log.e("kcc", "MyScrollView onScrollChanged: " + "l->" + l + "  t" + t + " oldl" + oldl + " oldt" + oldt);
//        super.onScrollChanged(l, t, oldl, oldt);
//    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Log.e("kcc", "MyScrollView dispatchDraw:" + "  scroll" + getScrollY());
        super.dispatchDraw(canvas);
    }
}
