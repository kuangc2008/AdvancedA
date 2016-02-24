package myapp.kc.com.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by kuangcheng01 on 2016/2/14.
 */
public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("kcc", Build.CPU_ABI);


        ArrayList<Integer> value = new ArrayList<Integer>();
//        value.add(3.96f);
//        value.add(3.80f);
//        value.add(3.44f);
//        value.add(3.72f);
//        value.add(3.81f);
//
//        value.add(3.54f);
//        value.add(3.42f);
//        value.add(3.52f);
//        value.add(3.52f);
//        value.add(3.49f);


        value.add(1100);
        value.add(1130);
        value.add(1230);

        value.add(1120);
        value.add(1030);
        value.add(1030);

        value.add(1050);
        value.add(1050);
        value.add(1060);
        value.add(1130);


        float result = 0;
        for(float f  : value) {
            result += f;
        }
        Log.i("kcc", result/value.size() +"");

    }


    public static void Main() {

    }
}
