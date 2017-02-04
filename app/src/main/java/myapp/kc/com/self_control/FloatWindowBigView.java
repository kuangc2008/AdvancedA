package myapp.kc.com.self_control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import myapp.kc.com.kuang2016_go.R;

/**
 * Created by kuangcheng on 17/2/4.
 */
public class FloatWindowBigView extends LinearLayout {

    /**
     * 记录大悬浮窗的宽度
     */
    public static int viewWidth;

    /**
     * 记录大悬浮窗的高度
     */
    public static int viewHeight;

    public TextView mTotalDurView;
    public TextView mCurrentDurView;
    public TextView mText;

    public FloatWindowBigView(final Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.float_window_big, this);

        initSize();
        initViews();
    }

    private void initViews() {
        mTotalDurView = (TextView) findViewById(R.id.total_duration);
        mCurrentDurView = (TextView) findViewById(R.id.current_duration);
        mText = (TextView) findViewById(R.id.text);
    }

    private void initSize() {
        View view = findViewById(R.id.big_window_layout);


        viewWidth = view.getLayoutParams().width;
        viewHeight = view.getLayoutParams().height;


        final WindowManager windowManager = MyWindowManager.getWindowManager(getContext());
        final int screenWidth = windowManager.getDefaultDisplay().getWidth();
        final int screenHeight = windowManager.getDefaultDisplay().getHeight();
        if (viewWidth == -1) {
            viewWidth = screenWidth;
        }
        if (viewHeight == -1) {
            viewHeight = screenHeight;
        }
    }

}