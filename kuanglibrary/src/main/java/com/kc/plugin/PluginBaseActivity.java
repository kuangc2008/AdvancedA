package com.kc.plugin;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.kc.kuanglibrary.BaseActivity;
import com.kc.utils.Constant;

/**
 * Created by kuangcheng on 17/3/1.
 */

public class PluginBaseActivity extends BaseActivity{
    private String localPath;
    private boolean isInstall;
    protected LinearLayout viewRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getLayoutInflater().setFactory2(new WidgetLayoutInflaterFactory());
        super.onCreate(savedInstanceState);

        localPath = getIntent().getStringExtra(Constant.INTENT_KEY_APK_PATH);
        String fragment = getIntent().getStringExtra(Constant.INTENT_KEY_FRAGMENT);
        setContentView(getContentView());
        isInstall = installRunEnv(localPath);
        installPluginFragment(fragment);
    }


    protected View getContentView() {
        viewRoot = new LinearLayout(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            viewRoot.setFitsSystemWindows(true);
        }
        viewRoot.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        viewRoot.setId(android.R.id.primary);
        return viewRoot;
    }

    /**
     * 装载插件的运行环境,这个函数需要在Activity中运行，不能移动到Application中去
     *
     * @param localPath
     * @return
     */
    protected boolean installRunEnv(final String localPath) {
        PluginInstallUtils.getInstance(this).installRunEnv(localPath);
        return true;
    }



    private void installPluginFragment(String fragmentClass) {
        try {
            if (isFinishing()) {
                return;
            }
            ClassLoader classLoader = getClassLoader();
            Fragment fg = (Fragment) classLoader.loadClass(fragmentClass).newInstance();
            Bundle bundle = getIntent().getExtras();
            fg.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.primary, fg).commitAllowingStateLoss();
        } catch (Exception e) {
        }
    }

}
