package myapp.kc.com.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * Created by kuangcheng01 on 2016/3/26.
 */
public class MyLinearLayout extends LinearLayout{

    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        boolean a = false;
        setWillNotDraw(a);
        Log.e("kcc", "setWillNotDraw " + a);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);

        if (mode == MeasureSpec.AT_MOST) {
            Log.e("kcc", "mode is atmost");
        } else if (mode == MeasureSpec.EXACTLY) {
            Log.e("kcc", "mode is exactily");
        } else if (mode == MeasureSpec.UNSPECIFIED) {
            Log.e("kcc", "mode is unspecied");
        }

        Log.e("kcc", "size->" + size);



    }

    @Override
    public boolean isNestedScrollingEnabled() {
        Log.e("kcc", "MyLinearLayout isNestedScrollingEnabled->" , new Exception());
        return super.isNestedScrollingEnabled();

    }

    @Override
    public void scrollTo(int x, int y) {
        Log.e("kcc", "MyLinearLayout scrollTo");
        super.scrollTo(x, y);
    }

    @Override
    public void scrollBy(int x, int y) {
        Log.e("kcc", "MyLinearLayout scrollBy");
        super.scrollBy(x, y);
    }

    @Override
    protected boolean awakenScrollBars() {
        Log.e("kcc", "awakenScrollBars: " + "height->" + getHeight());
        return super.awakenScrollBars();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Log.e("kcc", "MyLinearLayout dispatchDraw");
        super.dispatchDraw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("kcc", "myLinearLyou onDraw");
    }
}
