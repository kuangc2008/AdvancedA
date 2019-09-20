package myapp.kc.com.kuang2016_go;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.bun.miitmdid.core.JLibrary;
import com.bun.miitmdid.core.MdidSdkHelper;

import myapp.kc.com.utils.MiitHelper;

/**
 * @author kc create on 9/20/19.
 */
public class KApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        new MiitHelper(new MiitHelper.AppIdsUpdater() {
            @Override
            public void OnIdsAvalid(@NonNull String ids) {
                Log.i("kcc", "ids->" + ids);
            }
        }).getDeviceIds(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        JLibrary.InitEntry(base);
    }
}

