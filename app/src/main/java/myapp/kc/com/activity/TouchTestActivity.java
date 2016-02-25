package myapp.kc.com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import myapp.kc.com.kuang2016_go.R;

/**
 * Created by kuangcheng01 on 2016/2/25.
 */
public class TouchTestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touch_test_view);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("kcc", "activity onTouch " + event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("kcc", "activity dispatchTouchEvent " + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }
}
