package myapp.kc.com.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by kuangcheng01 on 2016/2/14.
 */
public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("kcc", Build.CPU_ABI);
    }
}
