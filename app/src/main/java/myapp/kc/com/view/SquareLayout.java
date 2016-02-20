package myapp.kc.com.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kuangcheng01 on 2016/2/20.
 */
public class SquareLayout extends ViewGroup {
    private static final int ORIENTATIOIN_HORIZONTAL = 0;
    private static final int ORIENTATION_VERTICAL = 1;
    private static final int DEFAULT_MAX_ROW =Integer.MAX_VALUE;
    private static final int DEFAULT_MAX_COLUMN = Integer.MAX_VALUE;


    private int mMaxRow = DEFAULT_MAX_ROW;
    private int mMaxColumn = DEFAULT_MAX_COLUMN;
    private int mOrientation = ORIENTATION_VERTICAL;

    public SquareLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int parentDesireWidth = 0;
        int parentDesireHeight = 0;
        int childMeasureState = 0;

        for (int i=0; i<getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
                int childMeasureSize = Math.max(child.getMeasuredWidth(), child.getMeasuredHeight());

                int childMasureSpec = MeasureSpec.makeMeasureSpec(childMeasureSize, MeasureSpec.EXACTLY);
                child.measure(childMasureSpec, childMasureSpec);

                MarginLayoutParams mlp = (MarginLayoutParams) child.getLayoutParams();

                int childActualWidth = child.getMeasuredWidth() + mlp.leftMargin + mlp.rightMargin;
                int childAcutalHeight = child.getMeasuredHeight() + mlp.topMargin + mlp.rightMargin;

                if (mOrientation == ORIENTATIOIN_HORIZONTAL) {
                    parentDesireWidth += childActualWidth;
                    parentDesireHeight = Math.max(parentDesireHeight, childAcutalHeight);
                } else if (mOrientation == ORIENTATION_VERTICAL) {
                    parentDesireHeight += childAcutalHeight;
                    parentDesireWidth = Math.max(parentDesireWidth, childActualWidth);
                }
            }
        }

        parentDesireWidth += getPaddingLeft() + getPaddingRight();
        parentDesireHeight += getPaddingTop() + getPaddingBottom();

        parentDesireWidth = Math.max(parentDesireWidth, getSuggestedMinimumWidth());
        parentDesireHeight = Math.max(parentDesireHeight, getSuggestedMinimumHeight());

        setMeasuredDimension(
                resolveSize(parentDesireWidth, widthMeasureSpec),
                resolveSize(parentDesireHeight, heightMeasureSpec)
        );
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (getChildCount() <= 0) {
            return;
        }

        int multi = 0;
        for (int i=0; i<getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                MarginLayoutParams mlp = (MarginLayoutParams) child.getLayoutParams();

                int childActualSize = child.getMeasuredWidth();
                if (mOrientation == ORIENTATIOIN_HORIZONTAL) {
                    child.layout(getPaddingLeft() + mlp.leftMargin + multi,
                            getPaddingTop() + mlp.topMargin,
                            childActualSize + getPaddingLeft() + mlp.leftMargin + multi,
                            childActualSize + getPaddingTop() + mlp.topMargin);
                    multi += childActualSize + mlp.leftMargin + mlp.rightMargin;
                }

                else if (mOrientation == ORIENTATION_VERTICAL) {
                    child.layout(getPaddingLeft() + mlp.leftMargin,
                            getPaddingTop() + mlp.topMargin + multi,
                            childActualSize + getPaddingLeft() + mlp.leftMargin,
                            childActualSize + getPaddingTop() + mlp.topMargin + multi);
                    multi += childActualSize + mlp.topMargin + mlp.bottomMargin;
                }
            }
        }
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return  new MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof MarginLayoutParams;
    }
}
