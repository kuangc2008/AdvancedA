package myapp.kc.com.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import myapp.kc.com.kuang2016_go.R;

/**
 * @author kc create on 4/11/19.
 * @copyright litebrowser
 */
public class RevealFrameParent extends ViewGroup {

    private static final int MARGINT_LEFT_RIGHT =  26; //TODO:DP
    private static final int CHILD_HOR_PADDING =  26; //TODO:DP
    private static final int CHILD_VERTICAL_PADDING =  26; //TODO:DP
    private static final float CHILD_FAV = 1.125f;
    private static final int COUNT_HOR = 3;
    private static final int COUNT_VERTICAL = 3;

    public RevealFrameParent(Context context) {
        super(context);
    }

    public RevealFrameParent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RevealFrameParent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int childwidth = (width -  (COUNT_HOR - 1) * MARGINT_LEFT_RIGHT - (COUNT_HOR - 1) * CHILD_HOR_PADDING)/3;
        int childHeight = (int) (childwidth * CHILD_FAV);

        final int size = getChildCount();
        for (int i = 0; i < size; ++i) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                child.measure(MeasureSpec.makeMeasureSpec(childwidth, MeasureSpec.EXACTLY)
                        , MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY));
            }
        }

        int totalHeight = childHeight * COUNT_VERTICAL + (COUNT_VERTICAL - 1) * CHILD_VERTICAL_PADDING;
        super.onMeasure(widthMeasureSpec,  MeasureSpec.makeMeasureSpec(totalHeight, MeasureSpec.EXACTLY));
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int size = getChildCount();


        int childLeft, childTop;
        for (int i = 0; i < size; ++i) {
            View childView = getChildAt(i);
            childLeft = MARGINT_LEFT_RIGHT + (i%COUNT_HOR) * (childView.getMeasuredWidth() + CHILD_HOR_PADDING);
            childTop = (i/COUNT_HOR) * (childView.getMeasuredHeight() + CHILD_VERTICAL_PADDING);
            childView.layout(childLeft, childTop, childLeft + childView.getMeasuredWidth(), childTop + childView.getMeasuredHeight());

        }
    }




}
