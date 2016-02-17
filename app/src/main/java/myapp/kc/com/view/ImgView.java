package myapp.kc.com.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.provider.MediaStore;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import myapp.kc.com.kuang2016_go.R;

/**
 * Created by kuangcheng01 on 2016/2/16.
 */
public class ImgView extends View {
    private Bitmap mBitmap;
    private TextPaint mPaint;
    private String mStr;

    private float mTextSize;

    private enum Ratio {
        WIDTH, HEIGHT
    }

    public ImgView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.YELLOW);
        calArgs(context);
        init();
    }

    private void init() {
        if (mBitmap == null) {
            mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.run);
        }

        if (mStr == null) {
            mStr = "I am the best!";
        }

        mPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.LINEAR_TEXT_FLAG);
        mPaint.setColor(Color.LTGRAY);
        mPaint.setTextSize(mTextSize);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
    }

    private void calArgs(Context context) {
        mTextSize = 50;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        masure1(widthMeasureSpec, heightMeasureSpec);


        setMeasuredDimension(getMeasureSize(widthMeasureSpec, Ratio.WIDTH),
                getMeasureSize(heightMeasureSpec, Ratio.HEIGHT));
    }

    private int getMeasureSize(int measureSpec, Ratio radio) {
        int result = 0;

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            case MeasureSpec.EXACTLY :
                result = size;
                break;
            default:    //默认情况下将at_most与Unspecified一并处理
                if (radio == Ratio.WIDTH) {
                    float textWidth = mPaint.measureText(mStr);
                    float innerWidth = textWidth >= mBitmap.getWidth() ? textWidth : mBitmap.getWidth();
                    result = (int) (innerWidth + getPaddingLeft() + getPaddingRight());
                } else if (radio == Ratio.HEIGHT) {
                    result = (int) ((mPaint.descent() - mPaint.ascent())*2 + mBitmap.getHeight() + getPaddingTop() + getPaddingBottom());
                }

                if (mode == MeasureSpec.AT_MOST) {
                    result = Math.min(result, size);
                }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawBitmap(mBitmap, getPaddingLeft(), getPaddingTop(), null);


        canvas.drawBitmap(mBitmap, getWidth()/2 - mBitmap.getWidth()/2 ,
                getHeight()/2 - mBitmap.getHeight()/2, null);
        canvas.drawText(mStr, getWidth()/2,
                getHeight() / 2 + mBitmap.getHeight() / 2 - mPaint.ascent(),
                mPaint);
    }

    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
    }


    private void masure1(int widthMeasureSpec, int heightMeasureSpec) {
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
}
