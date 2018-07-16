package myapp.kc.com.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import myapp.kc.com.kuang2016_go.R;

/**
 * Created by kuangcheng01 on 2016/3/28.
 */
public class MyTextView2 extends TextView {
    public MyTextView2(Context context) {
        super(context);
    }

    public MyTextView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public String getTagS() {
        if (getTag(R.id.text1) == null ) {
            return "null";
        } else {
            return getTag(R.id.text1).toString();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e("kcc", "MyTextView2 onMeasure " + "  getTag-->" + getTagS());
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.e("kcc", "MyTextView2 onLayout " + "  getTag-->" + getTagS());
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("kcc", "MyTextView2 onDraw:" + getTagS());
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i("kcc", "MyTextView2 onDetachedFromWindow");
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i("kcc", "MyTextView2 onAttachedToWindow");
    }
}
