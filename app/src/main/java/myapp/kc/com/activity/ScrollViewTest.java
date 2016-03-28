package myapp.kc.com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import myapp.kc.com.kuang2016_go.R;

/**
 * Created by kuangcheng01 on 2016/3/26.
 */
public class ScrollViewTest extends Activity{
    View mRoot = null;
    View mScrollView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.scrollview_test);

        mScrollView = findViewById(R.id.root);
        mRoot = findViewById(R.id.root2);
        // 如果不再scrollview中，高度是1134

        // 如果在scorllview中，则linearLayout的高度很高，但scrollview仍然不高


        mRoot.post(new Runnable() {
            @Override
            public void run() {
                Log.e("kcc", "root4height->" + mRoot.getHeight());
                Log.e("kcc", "mScrollView height->" + mScrollView.getHeight());
            }
        });
    }
}
