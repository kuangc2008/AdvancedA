// IZuanServiceCallback.aidl
package com.qihoo.browser.plugin.zuan;

// Declare any non-default types here with import statements

interface IZuanServiceCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);

    void onWeixinInfoGet(int error, String json);


    void onLaunchMiniExtra(String openId, String extMsg, String errStr, int errCode);
}

