package myapp.kc.com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import myapp.kc.com.kuang2016_go.R;

/**
 * Created by kuangcheng01 on 2016/3/26.
 */
public class ScrollViewTest2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.scrollview_test2);

        //默认的调用scrollBy方法是会把屏幕滑动出去的
        final View root = findViewById(R.id.root);

        //该button的位置固定了，scroll它时要考虑onDraw是怎么写的哦
        final View view = findViewById(R.id.button);
        view.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
//                root.scrollBy(0, -10);

            }
        });

    }
}
