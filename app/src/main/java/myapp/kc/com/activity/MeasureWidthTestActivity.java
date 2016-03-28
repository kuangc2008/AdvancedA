package myapp.kc.com.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import myapp.kc.com.kuang2016_go.R;

/**
 * Created by kuangcheng01 on 2016/3/28.
 */
public class MeasureWidthTestActivity  extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.measurewidthtest);

        final View vie = findViewById(R.id.textview);
        vie.post(new Runnable() {
            @Override
            public void run() {
                Log.e("kcc", "width->" + vie.getMeasuredHeight());
                Log.e("kcc", "width->" + vie.getHeight());
            }
        });
    }



}
