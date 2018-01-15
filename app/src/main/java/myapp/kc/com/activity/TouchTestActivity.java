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
        Log.i("kcc", "TouchTestActivity onCreate 5");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("kcc", "TouchTestActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("kcc", "TouchTestActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("kcc", "TouchTestActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("kcc", "TouchTestActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("kcc", "TouchTestActivity onDestroy");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Log.i("kcc", "activity onTouch " + event.getAction(), new Exception());
        boolean resuslt = super.onTouchEvent(event);
//        Log.i("kcc", "activity onTouch " + event.getAction() + "  resul: " + resuslt, new Exception());
        Log.i("kcc", "activity onTouch " + event.getAction() + "  resul: " + resuslt);
        return resuslt;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("kcc", "activity dispatchTouchEvent " + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }


}
