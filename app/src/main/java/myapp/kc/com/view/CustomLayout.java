package myapp.kc.com.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kuangcheng01 on 2016/2/17.
 */
public class CustomLayout extends ViewGroup {
    public CustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        if (getChildCount() > 0) {
//            measureChildren(widthMeasureSpec, heightMeasureSpec);
//        }
//
        int parentDesireWith = 0;
        int parentDesireHeight = 0;

        if (getChildCount() > 0 ) {
            for(int i=0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                CustomLayoutParams clp = (CustomLayoutParams) child.getLayoutParams();
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
                parentDesireWith += child.getMeasuredWidth()+ clp.leftMargin + clp.rightMargin;
                parentDesireHeight += child.getMeasuredHeight()+ clp.topMargin + clp.bottomMargin;
            }

            parentDesireWith += getPaddingLeft() + getPaddingRight();
            parentDesireHeight += getPaddingTop() + getPaddingBottom();

            parentDesireWith = Math.max(parentDesireWith, getSuggestedMinimumWidth());
            parentDesireHeight = Math.max(parentDesireHeight, getSuggestedMinimumHeight());
        }

        setMeasuredDimension(resolveSize(parentDesireWith, widthMeasureSpec),
                resolveSize(parentDesireHeight, heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int parentPaddingLeft = getPaddingLeft();
        int parentPaddingTop = getPaddingTop();

        if (getChildCount() > 0) {
            int multiheight = 0;
            for (int i=0; i < getChildCount(); i++) {
                View child = getChildAt(i);

                CustomLayoutParams clp = (CustomLayoutParams) child.getLayoutParams();

                child.layout(parentPaddingLeft + clp.leftMargin, multiheight + parentPaddingTop +  clp.topMargin,
                        child.getMeasuredWidth() + parentPaddingLeft +  clp.leftMargin, child.getMeasuredHeight() + multiheight + parentPaddingTop +  clp.topMargin);
                multiheight += child.getMeasuredHeight() +  clp.topMargin + clp.bottomMargin;

            }
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new CustomLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new CustomLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new CustomLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof CustomLayoutParams;
    }

    public class CustomLayoutParams  extends MarginLayoutParams {

        public CustomLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public CustomLayoutParams(int width, int height) {
            super(width, height);
        }

        public CustomLayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public CustomLayoutParams(LayoutParams source) {
            super(source);
        }


    }
}
