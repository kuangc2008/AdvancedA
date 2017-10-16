package myapp.kc.com.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import myapp.kc.com.kuang2016_go.R;


/**
 * Created by kc on 10/13/17.
 */

public class MyHeaderView extends FrameLayout {
    private static int MAX = 800;
    Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
    int height = 0;

    private View mLine1;
    private View mLine2;
    private View mLine3;


    public MyHeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public MyHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        setWillNotDraw(false);
        p.setColor(Color.parseColor("#b1b1b1"));
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(8);

        mLine1 = findViewById(R.id.header_line1);
        mLine2 = findViewById(R.id.header_line2);
        mLine3 = findViewById(R.id.header_line3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = composeRoundedRectPath(new RectF(0, 0, getWidth(), getHeight()), 6, height);
        canvas.drawPath(path, p);
        drawLine();


        height++;
        if (height >= MAX) {
            height = 0;
        }
        invalidate();
    }

    private void drawLine() {

        float sideLength = MAX/4;

        if (height <= sideLength * 1.1) {
            mLine1.setVisibility(View.INVISIBLE);
            mLine2.setVisibility(View.INVISIBLE);
            mLine3.setVisibility(View.INVISIBLE);
        } else if (height <= sideLength * 1.5) {

            float percent = (float) ((1.5 * sideLength - height) / (1.5 * sideLength  - 1.2 * sideLength));

            mLine1.setVisibility(View.VISIBLE);
            mLine1.setTranslationX(  - percent * (getWidth() + mLine1.getWidth()/2));

            mLine2.setVisibility(View.INVISIBLE);
            mLine3.setVisibility(View.INVISIBLE);
        } else if (height <= sideLength * 2.1) {
            mLine1.setVisibility(View.VISIBLE);
            mLine1.setTranslationX(0);
            mLine2.setVisibility(View.INVISIBLE);
            mLine3.setVisibility(View.INVISIBLE);
        } else if (height <= sideLength * 2.5) {
            mLine1.setVisibility(View.VISIBLE);
            mLine1.setTranslationX(0);
            mLine2.setVisibility(View.VISIBLE);
            float percent = (float) ((2.5 * sideLength - height) / (2.5 * sideLength  - 2.1 * sideLength));
            mLine2.setTranslationX(  - percent * (getWidth() + mLine1.getWidth()/2));

            mLine3.setVisibility(View.INVISIBLE);
        } else if (height <= sideLength * 3.1) {
            mLine1.setVisibility(View.VISIBLE);
            mLine1.setTranslationX(0);
            mLine2.setVisibility(View.VISIBLE);
            mLine1.setTranslationX(0);
            mLine3.setVisibility(View.INVISIBLE);
        } else if (height <= sideLength * 3.5) {
            mLine1.setVisibility(View.VISIBLE);
            mLine1.setTranslationX(0);
            mLine2.setVisibility(View.VISIBLE);
            mLine1.setTranslationX(0);
            mLine3.setVisibility(View.VISIBLE);

            float percent = (float) ((3.5 * sideLength - height) / (3.5 * sideLength  - 3.1 * sideLength));
            mLine3.setTranslationX(  - percent * (getWidth() + mLine1.getWidth()/2));
        } else {
            mLine1.setVisibility(View.VISIBLE);
            mLine1.setTranslationX(0);
            mLine2.setVisibility(View.VISIBLE);
            mLine1.setTranslationX(0);
            mLine3.setVisibility(View.VISIBLE);
            mLine3.setTranslationX(0);
        }


    }

    public static Path composeRoundedRectPath(RectF rect, int radius, int progress){
        Path path = new Path();
        path.moveTo(rect.right , rect.bottom);

        float sideLength = MAX/4;

        if (progress <= sideLength) {
            float leftPos = (rect.left + radius/2) +  (rect.right - rect.left - radius/2) * (sideLength - progress)/sideLength;
            path.lineTo(leftPos, rect.bottom);
        } else if (progress <= sideLength * 2) {
            path.lineTo(rect.left + radius/2, rect.bottom);
            path.quadTo(rect.left, rect.bottom, rect.left, rect.bottom - radius/2);

            float topPos = ((rect.top + radius/2) +  (rect.bottom - rect.top - radius) * (2*sideLength - progress) / sideLength);
            path.lineTo(rect.left, topPos);

        } else if (progress <= sideLength * 3) {
            path.lineTo(rect.left + radius/2, rect.bottom);
            path.quadTo(rect.left, rect.bottom, rect.left, rect.bottom - radius/2);
            path.lineTo(rect.left, rect.top + radius/2);
            path.quadTo(rect.left, rect.top, rect.left + radius/2, rect.top);

            float rightPos = ((rect.right - radius/2)  - (rect.right - rect.left - radius) * (3*sideLength - progress) / sideLength);
            path.lineTo(rightPos, rect.top);
        } else if (progress < MAX) {
            path.lineTo(rect.left + radius/2, rect.bottom);
            path.quadTo(rect.left, rect.bottom, rect.left, rect.bottom - radius/2);
            path.lineTo(rect.left, rect.top + radius/2);
            path.quadTo(rect.left, rect.top, rect.left + radius/2, rect.top);
            path.lineTo(rect.right - radius/2, rect.top);
            path.quadTo(rect.right, rect.top, rect.right, rect.top + radius/2);

            float bottomPos = ((rect.bottom - radius)  - (rect.bottom - rect.top - radius - radius/2) * (MAX - progress) / sideLength);
            path.lineTo(rect.right ,bottomPos);

        } else {
            path.lineTo(rect.left + radius/2, rect.bottom);
            path.quadTo(rect.left, rect.bottom, rect.left, rect.bottom - radius/2);
            path.lineTo(rect.left, rect.top + radius/2);
            path.quadTo(rect.left, rect.top, rect.left + radius/2, rect.top);
            path.lineTo(rect.right - radius/2, rect.top);
            path.quadTo(rect.right, rect.top, rect.right, rect.top + radius/2);
            path.lineTo(rect.right ,rect.bottom - radius);
        }
        return path;
    }
}
