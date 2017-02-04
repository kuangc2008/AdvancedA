package myapp.kc.com.self_control;

import android.content.Intent;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.kc.kuanglibrary.BaseActivity;

import myapp.kc.com.kuang2016_go.R;

/**
 * Created by kuangcheng on 17/2/4.
 */

public class SelfControllActivity extends BaseActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, R.string.close_float_window).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 0) {
            if (item.getTitle().equals(getResources().getString(R.string.close_float_window))) {
                item.setTitle(R.string.open_float_window);
                closeFloatService();
            } else {
                item.setTitle(R.string.close_float_window);
                startFloatService();
            }
        }
        return super.onOptionsItemSelected(item);

    }


    public void startFloatService() {
        Intent intent = new Intent(this, SelfControllService.class);
        startService(intent);
    }

    public void closeFloatService() {
        Intent intent = new Intent(this, SelfControllService.class);
        stopService(intent);
    }


}
