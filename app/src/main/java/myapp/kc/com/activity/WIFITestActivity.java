package myapp.kc.com.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.kc.kuanglibrary.BaseActivity;

import myapp.kc.com.kuang2016_go.R;

/**
 * Created by kc on 5/3/17.
 */

public class WIFITestActivity extends BaseActivity {

    private WifiManager mManager = null;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            mManager.startScan();
            mHandler.sendEmptyMessageDelayed(0, 10000);
        }
    };

    private BroadcastReceiver mReciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("kcc", "action-." + intent.getAction());
            Log.i("kcc",  "scan result" + mManager.getScanResults());
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(mReciver, intentFilter);


        mManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//        mHandler.sendEmptyMessageDelayed(0, 3000);


        setContentView(R.layout.wifi);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReciver);
    }
}
