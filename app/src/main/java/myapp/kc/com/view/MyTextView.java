package myapp.kc.com.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by kuangcheng01 on 2016/3/28.
 */
public class MyTextView extends TextView {
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e("kcc", "onMeasure " + getMeasuredHeight() + "  heigh2->" + getHeight());
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("kcc", "onMeasure2 " + getMeasuredHeight() + "  heigh2->" + getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.e("kcc", "onDraw" + getMeasuredHeight() + "  heigh->" + getHeight());
    }
}
