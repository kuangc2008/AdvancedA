package myapp.kc.com.activity;

import android.app.Activity;
import android.os.Bundle;

import myapp.kc.com.view.InteractiveLineGraphView;

/**
 * Created by kuangcheng01 on 2016/2/28.
 */
public class LineGraphActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InteractiveLineGraphView igv = new InteractiveLineGraphView(this);
        setContentView(igv);

    }
}
