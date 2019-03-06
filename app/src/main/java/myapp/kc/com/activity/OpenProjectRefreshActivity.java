package myapp.kc.com.activity;

import android.os.Bundle;
import com.kc.kuanglibrary.BaseActivity;

import myapp.kc.com.kuang2016_go.R;
import myapp.kc.com.view.recycle.other.OpenProjectTabPagerFragment;

public class OpenProjectRefreshActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, OpenProjectTabPagerFragment.newInstance())
                .commit();
    }
}
