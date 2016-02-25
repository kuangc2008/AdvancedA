package myapp.kc.com.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import myapp.kc.com.kuang2016_go.R;

/**
 * Created by kuangcheng01 on 2016/2/20.
 */
public class SquareLayout extends ViewGroup {
    private static final int ORIENTATION_HORIZONTAL = 0;
    private static final int ORIENTATION_VERTICAL = 1;
    private static final int DEFAULT_MAX_ROW =Integer.MAX_VALUE;
    private static final int DEFAULT_MAX_COLUMN = Integer.MAX_VALUE;


    private int mMaxRow = DEFAULT_MAX_ROW;
    private int mMaxColumn = DEFAULT_MAX_COLUMN;
    private int mOrientation = ORIENTATION_HORIZONTAL;

    public SquareLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mMaxRow = mMaxColumn = 2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int parentDesireWidth = 0;
        int parentDesireHeight = 0;
        int childMeasureState = 0;

        int[] childWiths = new int[getChildCount()];
        int[] childHeights = new int[getChildCount()];

        for (int i=0; i<getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
                int childMeasureSize = Math.max(child.getMeasuredWidth(), child.getMeasuredHeight());

                int childMasureSpec = MeasureSpec.makeMeasureSpec(childMeasureSize, MeasureSpec.EXACTLY);
                child.measure(childMasureSpec, childMasureSpec);

                MyMarginLayoutParams mlp = (MyMarginLayoutParams) child.getLayoutParams();

                childWiths[i] = child.getMeasuredWidth()  + mlp.leftMargin + mlp.rightMargin;
                childHeights[i] = child.getMeasuredHeight() + mlp.topMargin + mlp.bottomMargin;

                Log.i("kcc", "meausre1" + child.getMeasuredHeight() + "   height->" + childHeights[i]
                    +"  left->" + mlp.topMargin + "  right->" + mlp.bottomMargin);
            }
        }

        int indexMultiWidth = 0, indexMultiHeight = 0;

        if (mOrientation == ORIENTATION_HORIZONTAL) {

            if (getChildCount() > mMaxColumn) {
                int row = getChildCount() / mMaxColumn;
                int remainder = getChildCount() % mMaxColumn;
                int index = 0;

                for (int x = 0; x < row; x++) {
                    for (int y = 0; y < mMaxColumn; y++) {
                        indexMultiWidth += childWiths[index];
                        indexMultiHeight = Math.max(indexMultiHeight, childHeights[index++]);
                    }

                    Log.i("kcc",  x + " line->" + indexMultiHeight);

                    parentDesireWidth = Math.max(parentDesireWidth, indexMultiWidth);
                    parentDesireHeight += indexMultiHeight;
                    indexMultiHeight = indexMultiWidth = 0;
                }

                if (remainder != 0) {
                    for (int i = getChildCount() - remainder; i < getChildCount(); i++) {
                        indexMultiWidth += childWiths[i];
                        indexMultiHeight = Math.max(indexMultiHeight, childHeights[i]);
                    }

                    Log.i("kcc", "last line->" + indexMultiHeight);
                    parentDesireWidth = Math.max(parentDesireWidth, indexMultiWidth);
                    parentDesireHeight += indexMultiHeight;
                    indexMultiWidth = indexMultiHeight = 0;
                }

            } else {
                for (int i = 0; i < getChildCount(); i++) {
                    parentDesireHeight += childHeights[i];
                    parentDesireWidth = Math.max(parentDesireWidth, childWiths[i]);
                }
            }
    }

        Log.i("kcc", "wheight->" + parentDesireHeight + "  width->" + parentDesireWidth);
        parentDesireWidth += getPaddingLeft() + getPaddingRight();
        parentDesireHeight += getPaddingTop() + getPaddingBottom();
        Log.i("kcc", "wheight->" + parentDesireHeight + "  width->" + parentDesireWidth);
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

//        int multi = 0;
//        for (int i=0; i<getChildCount(); i++) {
//            View child = getChildAt(i);
//            if (child.getVisibility() != View.GONE) {
//                MarginLayoutParams mlp = (MarginLayoutParams) child.getLayoutParams();
//
//                int childActualSize = child.getMeasuredWidth();
//                if (mOrientation == ORIENTATION_HORIZONTAL) {
//                    child.layout(getPaddingLeft() + mlp.leftMargin + multi,
//                            getPaddingTop() + mlp.topMargin,
//                            childActualSize + getPaddingLeft() + mlp.leftMargin + multi,
//                            childActualSize + getPaddingTop() + mlp.topMargin);
//                    multi += childActualSize + mlp.leftMargin + mlp.rightMargin;
//                }
//
//                else if (mOrientation == ORIENTATION_VERTICAL) {
//                    child.layout(getPaddingLeft() + mlp.leftMargin,
//                            getPaddingTop() + mlp.topMargin + multi,
//                            childActualSize + getPaddingLeft() + mlp.leftMargin,
//                            childActualSize + getPaddingTop() + mlp.topMargin + multi);
//                    multi += childActualSize + mlp.topMargin + mlp.bottomMargin;
//                }
//            }
//        }


        int multi = 0;
        int indexMulti = 1;

        int indexMultiWidth = 0;
        int indexMultiHeight = 0;

        int tempHeight = 0, tempWidth = 0;


        for( int i=0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {

                MarginLayoutParams mlp = (MarginLayoutParams) child.getLayoutParams();
                int childActualSize = child.getMeasuredWidth();  // child.getMeasureHeight();

                if (mOrientation == ORIENTATION_HORIZONTAL) {
                    if (getChildCount() > mMaxColumn) {
                        if ( i < mMaxColumn * indexMulti) {
                            Log.i("kcc", "top->" + (getPaddingTop() + mlp.topMargin + indexMultiHeight)
                                + "height->" + childActualSize);
                            child.layout(getPaddingLeft() + mlp.leftMargin + indexMultiWidth,
                                    getPaddingTop() + mlp.topMargin + indexMultiHeight,
                                    getPaddingLeft() + mlp.leftMargin + indexMultiWidth + childActualSize,
                                    getPaddingTop() + mlp.topMargin + indexMultiHeight + childActualSize);
                            indexMultiWidth += childActualSize + mlp.leftMargin + mlp.rightMargin;
                            tempHeight = Math.max(tempHeight, childActualSize) + mlp.topMargin + mlp.bottomMargin;
                            Log.i("kcc", "actual-----" + tempHeight);


                            if (i + 1 >= mMaxColumn * indexMulti) {
                                indexMultiHeight += tempHeight;
                                indexMultiWidth = 0;
                                indexMulti++;
                            }
                        }
                    } else {
                        child.layout(
                                getPaddingLeft() + mlp.leftMargin + multi,
                                getPaddingTop() + mlp.topMargin,
                                getPaddingLeft() + mlp.leftMargin + multi + childActualSize,
                                getPaddingTop() + mlp.topMargin + childActualSize
                        );
                        multi += childActualSize +mlp.leftMargin + mlp.rightMargin;
                    }
                }


                else if (true) {

                }
            }


        }

    }


    public static class MyMarginLayoutParams extends MarginLayoutParams {
        public int mGravity;

        public MyMarginLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);

            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.SquareLayout);
            mGravity = a.getInt(R.styleable.SquareLayout_my_gravity, 0);
        }

        public MyMarginLayoutParams(int width, int height) {
            super(width, height);
        }

        public MyMarginLayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public MyMarginLayoutParams(LayoutParams source) {
            super(source);
        }
    }




    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MyMarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MyMarginLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return  new MyMarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof MyMarginLayoutParams;
    }
}
