package com.kc.volley_cache;

import android.os.DropBoxManager;

import java.util.Collections;
import java.util.Map;

/**
 * Created by kuangcheng on 17/2/28.
 */

public interface Cache {

    public Entry get(String key);

    public void put(String key, Entry entry);

    public void initialize();

    public void invalidate(String key, boolean fullExpire);

    public void remove(String key);

    public void clear();

    public static class Entry {
        public byte[] data;
        public String etag;
        public long serverData;
        public long lastModified;
        public long ttl;
        public long softTtl;
        public Map<String, String> responseHeaders = Collections.emptyMap();

        public boolean isExpired() {
            return this.ttl < System.currentTimeMillis();
        }

        public boolean refreshNeed() {
            return this.softTtl < System.currentTimeMillis();
        }
    }
}
