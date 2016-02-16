package myapp.kc.com.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by kuangcheng01 on 2016/2/16.
 */
public class ImgView extends View {
    private Bitmap mBitmap;

    public ImgView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int resultWidth = 0;

        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);

        if (modeWidth == MeasureSpec.EXACTLY) {
            resultWidth = sizeWidth;
        } else {
            resultWidth = mBitmap.getWidth();

            if (modeWidth == MeasureSpec.AT_MOST) {
                resultWidth = Math.min(resultWidth, sizeWidth);
            }
        }

        int resultHeight = 0;
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        if (modeHeight == MeasureSpec.EXACTLY) {
            resultHeight = sizeHeight;
        } else {
            resultHeight = mBitmap.getHeight();
            if (modeHeight == MeasureSpec.AT_MOST) {
                resultHeight = Math.min(resultHeight, sizeHeight);
            }
        }

        setMeasuredDimension(resultWidth + getPaddingLeft() + getPaddingRight(),
                resultHeight + getPaddingTop() + getPaddingBottom());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap, getPaddingLeft(), getPaddingTop(), null);
    }

    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
    }
}
