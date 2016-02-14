package myapp.kc.com.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.ArrayList;
import java.util.List;

import myapp.kc.com.kuang2016_go.MainActivity;

/**
 * Created by kuangcheng01 on 2016/2/14.
 */
public class ActivityFinder {

    private static final String MAIN_ACTIVITY_ACTION = "com.kc.main";

    public static List<ActivityInfo> getAllActivity(Context context) {

        PackageManager pm = context.getPackageManager();
        Intent intent = new Intent(MAIN_ACTIVITY_ACTION);
        intent.setPackage(context.getPackageName());
        List<ResolveInfo> infos = pm.queryIntentActivities(intent, 0);
        ArrayList<ActivityInfo> result = new ArrayList<>();
        for(int i=infos.size() -1 ; i >=0 ; i--) {
            result.add(infos.get(i).activityInfo);
        }
        return result;
    }

}
