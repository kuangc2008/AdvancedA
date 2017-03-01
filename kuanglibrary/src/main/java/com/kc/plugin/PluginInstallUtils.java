package com.kc.plugin;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import com.kc.kuanglibrary.R;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;

import dalvik.system.DexClassLoader;

/**
 * Created by kuangcheng on 17/3/1.
 */

public class PluginInstallUtils {

    private String dexOutputPath;

    private Context mContext;

    private static PluginInstallUtils sInstance;
    public final static HashMap<String ,PluginEnv> mPackagesHolder = new HashMap<>();

    private PluginInstallUtils(Context context) {
        mContext = context.getApplicationContext();
    }

    public static PluginInstallUtils getInstance(Context context) {
        if (sInstance == null) {
            synchronized (PluginInstallUtils.class) {
                if (sInstance == null) {
                    sInstance = new PluginInstallUtils(context);
                }
            }
        }
        return sInstance;
    }

    public PluginEnv installRunEnv(String apkPath) {
        PluginEnv pluginPackage = mPackagesHolder.get(apkPath);
        if (pluginPackage != null) {
            return pluginPackage;
        }

        DexClassLoader dexClassLoader = createDexClassLoader(apkPath);
        AssetManager assetManager = createAssetManager(apkPath);
        Resources resources = createResources(assetManager);
        Resources.Theme theme = resources.newTheme();
        theme.applyStyle(R.style.AppTheme, false);

        // create pluginPkackage
        pluginPackage = new PluginEnv(apkPath, dexClassLoader, resources, assetManager, theme);
        mPackagesHolder.put(apkPath, pluginPackage);
        return pluginPackage;
    }

    private DexClassLoader createDexClassLoader(String dexPath) {
        File dexOutputDir = mContext.getDir("dex", Context.MODE_PRIVATE);
        dexOutputPath = dexOutputDir.getAbsolutePath();
        DexClassLoader loader = new DexClassLoader(dexPath, dexOutputPath, null, mContext.getClassLoader());
        return loader;
    }

    private AssetManager createAssetManager(String dexPath) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, dexPath);
            return assetManager;
        } catch (Exception e) {
            return null;
        }
    }

    private Resources createResources(AssetManager assetManager) {
        Resources superRes = mContext.getResources();
        Resources resource = new Resources(assetManager, superRes.getDisplayMetrics(), superRes.getConfiguration());
        return resource;
    }

    private Class<?> loadPluginClass(ClassLoader classLoader, String className) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className, true, classLoader);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }
}
