package myapp.kc.com.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import myapp.kc.com.kuang2016_go.R;

/**
 * @author kc create on 4/11/19.
 * @copyright litebrowser
 */
public class RevealFrameLayout extends CardView {
    public static float MIN_RADIUS ;
    public static float MAX_RADIUS;

    private float radiusFac = 0.35f;
    private Point center = new Point();

    Paint p = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

    private float coRadius;
    public ObjectAnimator objectAnimator = null;


    public ObjectAnimator objectAnimator1 = null;
    public ObjectAnimator objectAnimator2 = null;
    public AnimatorSet tagsAnimator = null;


    private ImageView mTagImgView = null;
    private TextView mTagTextView = null;



    public RevealFrameLayout(@NonNull Context context) {
        super(context);

        init(null);

    }

    private void init(AttributeSet attrs) {
        setWillNotDraw(false);

        LayoutInflater.from(getContext()).inflate(R.layout.tags_item_layout, this);

        mTagTextView = (TextView) findViewById(R.id.tag_title);
        mTagImgView = (ImageView) findViewById(R.id.tag_img);


        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.RevealFrameLayout);

            int src = a.getResourceId(R.styleable.RevealFrameLayout_src, -1);
            mTagImgView.setImageResource(src);

            int tagsBg = a.getResourceId(R.styleable.RevealFrameLayout_tagsBg, 0);
            if (tagsBg == 0) {
                tagsBg = a.getColor(R.styleable.RevealFrameLayout_tagsBg, 0);
            } else {
                tagsBg = a.getResources().getColor(tagsBg);
            }
            p.setColor(tagsBg);


            int tagsStringResource = a.getResourceId(R.styleable.RevealFrameLayout_tagsTitle, 0);
            String tagsStr = "";
            if (tagsStringResource == 0) {
                tagsStr = a.getString(R.styleable.RevealFrameLayout_tagsTitle);
            } else {
                tagsStr = a.getResources().getString(tagsStringResource);
            }
            mTagTextView.setText(tagsStr);

            a.recycle();
        }
    }

    public RevealFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public RevealFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.drawCircle(center.x , center.y , coRadius, p );
        super.dispatchDraw(canvas);

    }

    public void setCoRadius(float radius) {
        Log.i("kcc", "setRadius ::::::::::::::" + radius);
        this.coRadius = radius;
        invalidate();
    }

    public void toggle() {
        if (objectAnimator == null) {
            objectAnimator = ObjectAnimator.ofFloat(this, "coRadius", MIN_RADIUS, MAX_RADIUS);
            objectAnimator.setDuration(300);
        }

        if (tagsAnimator == null) {
            objectAnimator1 = ObjectAnimator.ofFloat(mTagImgView, "scaleX", 1, 0.9f);
            objectAnimator2 = ObjectAnimator.ofFloat(mTagImgView, "scaleY", 1, 0.9f);
            objectAnimator1.setDuration(300);
            objectAnimator2.setDuration(300);
        }

        if (objectAnimator.isRunning() || objectAnimator.isStarted()) {
            return;
        }

        Log.i("kcc", "radis" + coRadius);
        if (Math.floor(coRadius)  <= MIN_RADIUS) {
            objectAnimator.start();
            objectAnimator1.start();
            objectAnimator2.start();
        } else {
            objectAnimator.reverse();
            objectAnimator1.reverse();
            objectAnimator2.reverse();
        }
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        coRadius = getWidth() *radiusFac;
        MIN_RADIUS = coRadius;

        Log.i("kcc", "onLayout->" + coRadius + "  mian->" + MIN_RADIUS);

        center.x = (int) (getWidth() - MIN_RADIUS * 0.7f);
        center.y = (int) (getHeight() - MIN_RADIUS * 0.7f);

        MAX_RADIUS = (float) Math.sqrt(  Math.pow(center.x, 2) + Math.pow(center.y, 2));

        objectAnimator = null;
        tagsAnimator = null;
    }




}
