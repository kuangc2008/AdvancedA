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
        p.setTextSize(50);

        canvas.drawLine(0, 99, 300, 100, p);
        canvas.drawLine(0, 199, 300, 200, p);

        p.setColor(Color.GREEN);

        float height = p.getFontMetrics().descent - p.getFontMetrics().ascent;


        Log.i("kcc", "d:" + p.getFontMetrics().descent + "   a:" + p.getFontMetrics().ascent
                + "  t:" + p.getFontMetrics().top + "  bottom:" + p.getFontMetrics().bottom
                + "   l:" + p.getFontMetrics().leading
                +"   dddd:" + p.descent()
                + "   aaaa:" + p.ascent());



//        canvas.drawText("faeffef我的我gy", 0, 100 + (100 - (p.getFontMetrics().descent - p.getFontMetrics().ascent))/2 - p.getFontMetrics().ascent, p);



        Rect r = new Rect();
        p.getTextBounds("f", 0, 1, r);
        float acent = Math.abs(p.ascent());
        float descent = Math.abs(p.descent());
        float y = 150 + r.top - p.ascent();

        Log.e("kcc", "r1->" + r);

        canvas.drawText("f", 0, 99 - r.top/2 - r.bottom/2, p);


        r = new Rect();
        p.getTextBounds("a", 0, 1, r);

        y = 150 + r.height() / 2f - r.bottom;
        canvas.drawText("a", 40, 99 - r.top/2 - r.bottom/2, p);

        Log.e("kcc", "r2->" + r);


        r = new Rect();
        p.getTextBounds("g", 0, 1, r);
        canvas.drawText("g", 80, 99 - r.top/2 - r.bottom/2, p);

        Log.e("kcc", "r3->" + r);

        r = new Rect();
        p.getTextBounds("我", 0, 1, r);
        canvas.drawText("我", 120, 99 - r.top/2 - r.bottom/2, p);

        Log.e("kcc", "r4->" + r);


//        canvas.drawText("faeffef我的我gy", 0, 100 - p.getFontMetrics().ascent, p);
    }
}
