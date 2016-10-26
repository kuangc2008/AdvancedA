package myapp.kc.com.activity;

import android.app.ActionBar;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kc.kuanglibrary.BaseActivity;

/**
 * Created by kuangcheng on 16/10/26.
 */

public class _1026_KeyboardShowTestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        setContentView(ll);

        LinearLayout.LayoutParams topLP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        topLP.weight = 1;
        TextView tv = new TextView(this);
        tv.setText("早睡早起,精神百倍");
        ll.addView(tv, topLP);


        LinearLayout.LayoutParams BottomLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ActionBar
                .LayoutParams.WRAP_CONTENT);
        EditText mEt = new EditText(this);
        mEt.setTextSize(20);
        ll.addView(mEt, BottomLp);


        addGlobalLayoutListener();
    }

    @Override
    protected void onKeyboardVisibilityChanged(boolean isVisible) {
        super.onKeyboardVisibilityChanged(isVisible);
        Log.i("kcc", "isVis->" +isVisible);
    }
}
