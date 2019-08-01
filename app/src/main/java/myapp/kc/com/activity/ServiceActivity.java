package myapp.kc.com.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.kc.kuanglibrary.BaseActivity;
import com.qihoo.browser.api.WeiXinInfo;
import com.qihoo.browser.api.WeixinApi;
import com.qihoo.browser.plugin.zuan.IZuanServiceCallback;

import myapp.kc.com.kuang2016_go.R;

/**
 * @author kc create on 5/28/19.
 */

public class ServiceActivity extends Activity {


    public final static int SHARE_IMG = 1;
    public final static int SHARE_TEXT = 1 << 2;
    public final static int SHARE_IMG_AND_TEXT = 1 << 3;
    public final static int SHARE_IMG_Logcal = 1 << 4;

    private WeixinApi mManager = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity);
        mManager = new WeixinApi(this, new WeixinApi.ApiCallback() {
            @Override
            public void onWeixinInfoGet(int error, WeiXinInfo weiXinInfo) {
                Log.i("kcc", "error->" + error + "  weixin-->" + weiXinInfo);
            }

            @Override
            public void onLaunchMiniExtra(String s, String s1, String s2, int i) {
                Log.i("kcc", "error->" + s + "  s1-->" + s1 + "  s2->" + s1 + "  i->" + i);
            }

            @Override
            public void onShareResult(int i) {
                Log.i("kcc", "onShareResult->" + i);
            }
        });
        findViewById(R.id.weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mManager.weixinLogin();
            }
        });

        findViewById(R.id.weixin2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mManager.startApplet("gh_e3c59df1779c", "", 0);
            }
        });

        findViewById(R.id.weixin3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("kcc", "weixin3");
                mManager.share(1, "8-37-remote-api.jar", "fdfdsafsaf", "http://www.baidu.com", "", SHARE_IMG_AND_TEXT, "");
            }
        });
        findViewById(R.id.weixin4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("kcc", "weixin4");
                mManager.share(2, "fdsafds", "fdfdsafsaf", "http://www.baidu.com", "", SHARE_IMG_AND_TEXT, "");
            }
        });
        findViewById(R.id.weixin5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("kcc", "weixin4");
                mManager.share(4, "fdsafds", "fdfdsafsaf", "http://www.baidu.com", "", SHARE_IMG_AND_TEXT, "");
            }
        });

//
//        IntentFilter intentFilter = new IntentFilter(ZuanConstants.RESULT_WEIXIN_LOGIN);
////            context.registerReceiver(mReceiver, intentFilter, ZuanConstants.PERMISSTION, null);
//        this.registerReceiver(new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                Log.i("kcc", "intent-3333->" + intent);
//            }
//        }, intentFilter, null, null);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        mManager.destory();
    }
}


