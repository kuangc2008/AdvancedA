package myapp.kc.com.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by kuangcheng01 on 2016/3/28.
 */
public class MyTextView extends View {
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
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.YELLOW);
        p.setTextSize(30);

        canvas.drawLine(0, 99, 300, 100, p);
        canvas.drawLine(0, 199, 300, 200, p);

        p.setColor(Color.GREEN);

        float height = p.getFontMetrics().descent - p.getFontMetrics().ascent;


        canvas.drawText("faeffef我的我gy", 0, 150 - p.getFontMetrics().ascent/2, p);
    }
}
