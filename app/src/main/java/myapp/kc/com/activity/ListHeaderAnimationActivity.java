package myapp.kc.com.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.kc.kuanglibrary.BaseActivity;

/**
 * Created by kc on 10/12/17.
 */

public class ListHeaderAnimationActivity extends BaseActivity {
    private static int MAX = 800;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final MyHeaderView view = new MyHeaderView(this);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(800, 800);
        setContentView(view, lp);
    }

    public static class MyHeaderView extends View {

        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        int height = 0;

        public MyHeaderView(Context context) {
            super(context);

            p.setColor(Color.RED);
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(8);
        }



        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawColor(Color.GRAY);

//            Path path = RoundedRect(100, 100, 500, 500, 30, 30, true);
            Path path = composeRoundedRectPath(new RectF(100, 100, 500, 500), 30, height);
            canvas.drawPath(path, p);


            canvas.drawRect(new Rect());





            height++;
            if (height >= MAX) {
                height = 0;
            }
            invalidate();
        }

        public static Path composeRoundedRectPath(RectF rect, int radius, int progress){
            Path path = new Path();
            path.moveTo(rect.right , rect.bottom);

            float sideLength = MAX/4;

            if (progress <= sideLength) {
                float leftPos = (rect.left + radius/2) +  (rect.right - rect.left - radius/2) * (sideLength - progress)/sideLength;
                path.lineTo(leftPos, rect.bottom);

                Log.i("kcc", "11111--" + leftPos);

            } else if (progress <= sideLength * 2) {

                Log.i("kcc", "22222--" + (rect.left + radius/2));

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
}
