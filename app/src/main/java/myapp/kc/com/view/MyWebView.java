package myapp.kc.com.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowInsets;
import android.webkit.WebView;

/**
 * Created by kuangcheng on 2018/1/15.
 */

public class MyWebView extends WebView {
    public MyWebView(Context context) {
        super(context);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int computeVerticalScrollOffset() {
        int size = super.computeVerticalScrollOffset();;
        Log.i("kcc", "computeVerticalScrollOffset" + size);
        return size;
    }

    @Override
    protected int computeVerticalScrollRange() {
        int value = super.computeVerticalScrollRange();;
        Log.i("kcc", "computeVerticalScrollRange" + value);
        return value;
    }

    @Override
    protected int computeVerticalScrollExtent() {
        int value = super.computeVerticalScrollExtent();;
        Log.i("kcc", "computeVerticalScrollExtent" + value);
        return value;
    }

    @Override
    public WindowInsets computeSystemWindowInsets(WindowInsets in, Rect outLocalInsets) {
        Log.i("kcc", "computeSystemWindowInsets" + outLocalInsets);
        return super.computeSystemWindowInsets(in, outLocalInsets);
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
        Log.i("kcc", "scrollTo");
    }

    @Override
    public void computeScroll() {
        super.computeScroll();


//        Log.i("kcc", "computeScroll");
    }

    @Override
    public void scrollBy(int x, int y) {
        super.scrollBy(x, y);
        Log.i("kcc", "scrollBy");
    }

    @Override
    public void flingScroll(int vx, int vy) {
        super.flingScroll(vx, vy);
        Log.i("kcc", "vy");
    }

    @Override
    public void stopNestedScroll() {
        super.stopNestedScroll();
        Log.i("kcc", "stopNestedScroll");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("kcc", "onTouchEvent");
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            scrollBy(0, 0);
            stopNestedScroll();
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        Log.i("kcc", " d" + deltaY + " s" + scrollY + " r-" + scrollRangeY  + "   m" + maxOverScrollY + "  isT" + isTouchEvent, new Exception());
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
//        Log.i("kcc", " scrollY-" + scrollY + " caY-" + clampedY, new Exception());
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
