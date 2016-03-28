package myapp.kc.com.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class MyCusViewGroup extends ViewGroup {

    public MyCusViewGroup(Context context) {
        super(context);
    }

    public MyCusViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        if (count != 0) {
            View view = getChildAt(0);
            view.layout(l, t, r, b);
        }
    }
}