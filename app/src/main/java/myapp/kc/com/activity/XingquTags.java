package myapp.kc.com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.kc.kuanglibrary.BaseActivity;

import myapp.kc.com.kuang2016_go.R;
import myapp.kc.com.view.RevealFrameLayout;

/**
 * @author kc create on 4/11/19.
 * @copyright litebrowser
 */
public class XingquTags extends BaseActivity {

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xingqu_tags);
        final RevealFrameLayout rfl = (RevealFrameLayout) findViewById(R.id.shehui);

        rfl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rfl.toggle();
            }
        });

    }

}
