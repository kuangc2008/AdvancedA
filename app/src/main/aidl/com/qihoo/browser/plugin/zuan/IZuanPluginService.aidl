// IZuanPluginService.aidl
package com.qihoo.browser.plugin.zuan;

import com.qihoo.browser.plugin.zuan.IZuanServiceCallback;


// Declare any non-default types here with import statements

interface IZuanPluginService {

    void weixinLogin();

    void startApplet(String keyId, String path, int type);

    void share(int shareTo, String title, String content, String url, String imageLocalUrl, int shareType, String shareFrom);

    void registerCallback(IZuanServiceCallback callback);

    void unregisterCallback(IZuanServiceCallback callback);
}
