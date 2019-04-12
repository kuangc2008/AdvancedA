package myapp.kc.com.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.kc.kuanglibrary.BaseActivity;

import myapp.kc.com.kuang2016_go.R;
import myapp.kc.com.view.RevealFrameLayout;
import myapp.kc.com.view.RevealFrameParent;

/**
 * @author kc create on 4/11/19.
 * @copyright litebrowser
 */
public class XingquTags extends BaseActivity {


    /**
     *   <myapp.kc.com.view.RevealFrameLayout
     *             android:layout_width="wrap_content"
     *             android:layout_height="wrap_content"
     *             app:cardCornerRadius="8dp"
     *             app:cardBackgroundColor="#0F0"
     *             app:cardElevation="0dp"
     *
     *             android:layout_marginRight="10dp"
     *             android:id="@+id/shehui"
     *             app:src="@drawable/hugh"
     *             app:tagsBg="@color/blue"
     *             app:tagsTitle="啦啦啦"
     *             />
     * @param savedInstanceState
     */
    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xingqu_tags);

        RevealFrameParent parent = (RevealFrameParent) findViewById(R.id.tags_layout);


        for (int i = 0; i <= 8 ; i++) {
            RevealFrameLayout aaa =new RevealFrameLayout(this);
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            aaa.setCardElevation(0);
            aaa.setCardBackgroundColor(Color.YELLOW);
            aaa.setRadius(16);  // dp

            if (i % 3 != 1) {
                lp.rightMargin = 10; //dp
            }

            aaa.setSrcResource(R.drawable.hugh);
            aaa.setTagsBg(getResources().getColor(R.color.blue));
            aaa.setTagsTitle("lalala");

            parent.addView(aaa);
        }



//        final RevealFrameLayout rfl = (RevealFrameLayout) findViewById(R.id.shehui);
//        rfl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                rfl.toggle();
//            }
//        });







    }

}
