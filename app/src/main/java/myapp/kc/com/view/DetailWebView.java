package myapp.kc.com.view;

import android.content.Context;
import android.support.v4.view.NestedScrollingChildHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.WebView;


public class DetailWebView extends WebView implements IDetailWebView {


    private int mLastMotionY;
    private final int[] mScrollConsumed = new int[2];
    private final int[] mScrollOffset = new int[2];

    private float mTouchSlop;
    private float mMaximumVelocity;
    private float mMinimumVelocity;

    private int mNestedYOffset;
    private NestedScrollingChildHelper mChildHelper;

    private boolean mIsDisalbeListenerShort = false;


    public DetailWebView(Context context) {
        super(context);
        init();
    }

    public DetailWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public DetailWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i("kcc", "onLayout 33333");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);      Log.i("kcc", "onMeasure 2222222");

    }

    protected void init() {
//        setOverScrollMode(OVER_SCROLL_ALWAYS);

        setOverScrollMode(OVER_SCROLL_NEVER);

//
//        setVerticalScrollBarEnabled(false);
//        setHorizontalScrollBarEnabled(false);
//        setSysWebViewVerticalScrollBarEnabled(false);
//
//        mChildHelper = new NestedScrollingChildHelper(this);
//        setNestedScrollingEnabled(true); // 设置支持 NestedScrolling
//
//        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
//        mMaximumVelocity = ViewConfiguration.get(getContext()).getScaledMaximumFlingVelocity();
//        mMinimumVelocity = ViewConfiguration.get(getContext()).getScaledMinimumFlingVelocity();
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        if (QhAdapter.useSyeWebView()) {
//            return true;
//        }

//        mHelper.stopNestedTouchEvent(ev);
//        Log.w("kcc", "22222222 onTouchEvent");
//        if (mHelper == null)
//            return super.onTouchEvent(ev);
//        if (!mHelper.onTouchEvent(ev)) {
//            return true;
//        }
        return super.onTouchEvent(ev);
    }



    @Override
    protected int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    @Override
    protected int computeVerticalScrollOffset() {
        return super.computeVerticalScrollOffset();
    }

    @Override
    protected int computeVerticalScrollRange() {
        return super.computeVerticalScrollRange();
    }


    @Override
    public void customScrollBy(int dy) {
        scrollBy(0, dy);
    }


    long time22222 = 0;


    private int mToY = 0;


    @Override
    public void customScrollTo(int toY) {
        long current = System.currentTimeMillis();
//        if (current - time > 20) {
            scrollTo(0, toY);
        time22222 = current;
//        }

        mToY = toY;

    }

    public void customScrollTo(int toY, boolean needSlop) {
        long current = System.currentTimeMillis();
        if (needSlop) {
            customScrollTo(toY);
                Log.e("kcc", "==========================================================");
            Log.e("kcc", "$$$$$$$$$$$$$$$$$");
        } else {
            customScrollTo(toY);
            Log.e("kcc", "----------------------------");
        }
    }

    @Override
    public int customGetContentHeight() {
        Log.i("kcc", "getHeight-");
        return (int) (getContentHeight() * getScale());
    }

    @Override
    public int customGetWebScrollY() {
        return getScrollY();
    }


    long time = 0;
    int scrollRang = 0;

    @Override
    public int customComputeVerticalScrollRange() {
        Log.i("kcc", "customComputeVerticalScrollRange-");
        if (scrollRang > 0 && System.currentTimeMillis() - time > 2000) {
            return scrollRang;
        }
        int scrollRang1 = computeVerticalScrollRange();
        if (scrollRang1 > scrollRang) {
            time = System.currentTimeMillis();
            scrollRang = scrollRang1;
        }
        return scrollRang1;
    }

    @Override
    public int customComputeVerticalScrolllOffset() {
//        long time = System.currentTimeMillis();

//        int a =  super.computeVerticalScrollOffset();
//        Log.i("kcc", "customComputeVerticalScrolllOffset time->" +(System.currentTimeMillis() - time));
//        return a ;

        return mToY;
    }
}

