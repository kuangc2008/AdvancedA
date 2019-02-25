package myapp.kc.com.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.kc.kuanglibrary.BaseActivity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import myapp.kc.com.kuang2016_go.R;
import myapp.kc.com.view.DetailWebView;
import myapp.kc.com.view.MyWebView;

/**
 * @author kc create on 1/2/18.
 * @copyright litebrowser
 */
public class WebViewFlashActivity extends BaseActivity {

    private DetailWebView wwebView;
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.web_veiew_flash_test);
        wwebView = (DetailWebView) findViewById(R.id.heheda);

        com.qihoo.webkit.WebSettings ws = wwebView.getSettings();
        try {
            ws.setJavaScriptEnabled(true);
        } catch (Exception e) {
            ws.setJavaScriptEnabled(true);
        }
        ws.setDomStorageEnabled(true);
//        ws.setSupportZoom(false);
        ws.setDefaultTextEncodingName("UTF-8");
        ws.setUserAgentString("Mozilla/5.0 (Linux; Android 7.1.1; PRO 6 Build/NMF26O; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/63.0.3239.83 Mobile Safari/537.36 T7/11.1 SearchCraft/3.4.2 (Baidu; P1 7.1.1)Mozilla/5.0 (Linux; Android 7.1.1; PRO 6 Build/NMF26O; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/63.0.3239.83 Mobile Safari/537.36 T7/11.1 SearchCraft/3.4.2 (Baidu; P1 7.1.1)");
        ws.setLayoutAlgorithm(com.qihoo.webkit.WebSettings.LayoutAlgorithm.SINGLE_COLUMN);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
//            enablecrossdomain41();
//            ws.setAllowUniversalAccessFromFileURLs(true);
//            ws.setAllowFileAccessFromFileURLs(true);
        } else {
//            enablecrossdomain();
        }


//        if (Build.VERSION.SDK_INT >= 19) {
//            wwebView.setWebContentsDebuggingEnabled(true);
//        }
//        ws.setLoadWithOverviewMode(true);


//        String mUa = ws.getUserAgentString() + " 360 Alitephone Qihoo NewsSDK" + "/" + 1.01 + "/" + 1 + "  mso_sdk(1.0.0)";
//        ws.setUserAgentString(mUa);


        final LinearLayout sv = (LinearLayout) findViewById(R.id.hehe);

        sv.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

//                wwebView.loadUrl("javascript:scrollCallback(" + scrollY + ")");
                Log.e("kcc", "javascript:scrollCallback()" + scrollY);
            }
        });
//        sv.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        wwebView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.e("kcc", "vvv1111-");
            }
        });

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                wwebView.flingScroll(0, 10000);
            }
        });



        wwebView.setWebViewClient(new com.qihoo.webkit.WebViewClient() {
            @Override
            public void onPageFinished(com.qihoo.webkit.WebView view, String url) {
                super.onPageFinished(view, url);


//                wwebView.loadUrl("javascript:$NATIVECALLBACK101(1)");
//                wwebView.loadUrl("javascript:$NATIVECALLBACK102({\"support\":\"1\",\"wid\":\"6ac7941b787ccd94bb992c2b686fc773\",\"token\":\"1514889884|7246966b2353fb2b08c311d58fbc956f\"})");
//                wwebView.loadUrl("javascript:$NATIVECALLBACK104(1)");
//                wwebView.loadUrl("javascript:$NATIVECALLBACK103(1)");
//                wwebView.loadUrl("javascript:$NATIVECALLBACK105({\"wid\":\"6ac7941b787ccd94bb992c2b686fc773\",\"sign\":\"contents\",\"network_type\":\"4\",\"refer_scene\":\"0\",\"source\":\"multi,test_filter_V24,normal_op,sts0,fts3,constellation,,nbbn_cconstellation_S:5:10964:横财:生肖,nbbn_cconstellation_S:5:14522:财运:生肖,nbbn_cconstellation_S:5:8760:生肖:财神,nbbn_kconstellation_1:横财,nbbn_kconstellation_1:生肖,nbbn_kconstellation_1:运势,nbbn_kconstellation_2:横财,nbbn_kconstellation_2:生肖,nbbn_kconstellation_2:运势,nbbn_sconstellation_星座,nbbn_tconstellation,,nbbc_tconstellation_V151,nbbr_tconstellation_V151__100__1320__132000__27__22__100,tag,tagt_01110,uusdkother_0,nbbh_merger105.se.bjyt,uusdkother,,wvalidllquid,wlocal_BeiJing,wqiku,wdpv0,wdclk0,wspuser_3beijing,wspuser_2dasha,nbbs_31:1\",\"refer_subscene\":\"0\"})");
//                wwebView.loadUrl("javascript:$NATIVECALLBACK106(1)");
//                wwebView.loadUrl("javascript:$NATIVECALLBACK107(1)");
//                wwebView.loadUrl("javascript:$NATIVECALLBACK109(1)");
//                wwebView.loadUrl("javascript:$NATIVECALLBACK110(1)");
//                wwebView.loadUrl("javascript:$NATIVECALLBACK111(1)");
//                wwebView.loadUrl("javascript:$NATIVECALLBACK112(1)");
//                wwebView.loadUrl("javascript:sdk.trigger()");
//
//
//                sv.setLayerType(View.LAYER_TYPE_HARDWARE, null);

            }
        });

        wwebView.setWebChromeClient(new com.qihoo.webkit.WebChromeClient(){
            @Override
            public boolean onConsoleMessage(com.qihoo.webkit.ConsoleMessage consoleMessage) {
                Log.e("kcc", "3331-" + consoleMessage.message());
                return super.onConsoleMessage(consoleMessage);
            }

            @Override
            public void onConsoleMessage(String message, int lineNumber, String sourceID) {
                Log.e("kcc", "212121-" + message);
                super.onConsoleMessage(message, lineNumber, sourceID);
            }

        });



//        wwebView.loadUrl("http://m.news.so.com/transcoding?url=http%3A%2F%2Fnews.ynet.com%2F2017%2F12%2F30%2F818341t70.html&check=a3013b7ecbc0ad8c&uid=a8a586af3ce5a7d687a920838fd00ee9&sign=contents&market=nh00001&stype=null&sdkv=3&v=1&sv=12&templetctl=7&360newsdetail=1&hsitetype=1&ucheck=32d03a907f48baab4c284dd054ff2797&hscmt=1&swidth=1080&act=share&to=copy_link&device=1&relatestyle=3");
        wwebView.loadUrl("http://www.baidu.com");


    }


    public void enablecrossdomain()
    {
        try
        {
            Field field = WebView.class.getDeclaredField("mWebViewCore");
            field.setAccessible(true);
            Object webviewcore=field.get(this);
            Method method = webviewcore.getClass().getDeclaredMethod("nativeRegisterURLSchemeAsLocal", String.class);
            method.setAccessible(true);
            method.invoke(webviewcore, "http");
            method.invoke(webviewcore, "https");
        }
        catch(Exception e)
        {
            Log.d("wokao","enablecrossdomain error");
            e.printStackTrace();
        }
    }

    //for android 4.1+
    public void enablecrossdomain41()
    {
        try
        {
            Field webviewclassic_field = WebView.class.getDeclaredField("mProvider");
            webviewclassic_field.setAccessible(true);
            Object webviewclassic=webviewclassic_field.get(this);
            Field webviewcore_field = webviewclassic.getClass().getDeclaredField("mWebViewCore");
            webviewcore_field.setAccessible(true);
            Object mWebViewCore=webviewcore_field.get(webviewclassic);
            Field nativeclass_field = webviewclassic.getClass().getDeclaredField("mNativeClass");
            nativeclass_field.setAccessible(true);
            Object mNativeClass=nativeclass_field.get(webviewclassic);

            Method method = mWebViewCore.getClass().getDeclaredMethod("nativeRegisterURLSchemeAsLocal",new Class[] {int.class,String.class});
            method.setAccessible(true);
            method.invoke(mWebViewCore,mNativeClass, "http");
            method.invoke(mWebViewCore,mNativeClass, "https");
        }
        catch(Exception e)
        {
            Log.d("wokao","enablecrossdomain error");
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            wwebView.scrollBy(0, 0);
        }
        return super.onTouchEvent(event);
    }
}



