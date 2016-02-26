package myapp.kc.com.activity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by kuangcheng01 on 2016/2/26.
 */
public class SnackBarTestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        final CoordinatorLayout cl = new CoordinatorLayout(this);

        final LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setBackgroundColor(Color.YELLOW);
        final Button button = new Button(this);
        button.setBackgroundColor(Color.CYAN);
        button.setText("I love my eye");
        button.setLayoutParams(new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ll.addView(button);

        View vv = new View(this);
        vv.setBackgroundColor(Color.LTGRAY);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        llp.weight = 1;
        ll.addView(vv, llp);

        Button button2 = new Button(this);
        button2.setText("I love my eye2222222222222222");
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        button2.setLayoutParams(lp);
        ll.addView(button2);


        cl.addView(ll, new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(cl);

        button.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                Snackbar.make(cl, "goodjob", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
