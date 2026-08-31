package com.pichillilorenzo.flutter_inappwebview_android;

import android.webkit.ValueCallback;
import android.webkit.WebStorage;
import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import i6.i;
import i6.j;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class MyWebStorage extends ChannelDelegateImpl {
    protected static final String LOG_TAG = "MyWebStorage";
    public static final String METHOD_CHANNEL_NAME = "com.pichillilorenzo/flutter_inappwebview_webstoragemanager";
    public static WebStorage webStorageManager;
    public InAppWebViewFlutterPlugin plugin;

    public MyWebStorage(InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin) {
        super(new j(inAppWebViewFlutterPlugin.messenger, METHOD_CHANNEL_NAME));
        this.plugin = inAppWebViewFlutterPlugin;
    }

    public static void init() {
        if (webStorageManager == null) {
            webStorageManager = WebStorage.getInstance();
        }
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        this.plugin = null;
    }

    public void getOrigins(final j.d dVar) {
        WebStorage webStorage = webStorageManager;
        if (webStorage == null) {
            dVar.success(new ArrayList());
        } else {
            webStorage.getOrigins(new ValueCallback<Map>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.MyWebStorage.1
                @Override // android.webkit.ValueCallback
                public void onReceiveValue(Map map) {
                    ArrayList arrayList = new ArrayList();
                    for (Object obj : map.keySet()) {
                        WebStorage.Origin origin = (WebStorage.Origin) map.get(obj);
                        HashMap hashMap = new HashMap();
                        hashMap.put("origin", origin.getOrigin());
                        hashMap.put("quota", Long.valueOf(origin.getQuota()));
                        hashMap.put("usage", Long.valueOf(origin.getUsage()));
                        arrayList.add(hashMap);
                    }
                    dVar.success(arrayList);
                }
            });
        }
    }

    public void getQuotaForOrigin(String str, final j.d dVar) {
        WebStorage webStorage = webStorageManager;
        if (webStorage == null) {
            dVar.success(0);
        } else {
            webStorage.getQuotaForOrigin(str, new ValueCallback<Long>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.MyWebStorage.2
                @Override // android.webkit.ValueCallback
                public void onReceiveValue(Long l8) {
                    dVar.success(l8);
                }
            });
        }
    }

    public void getUsageForOrigin(String str, final j.d dVar) {
        WebStorage webStorage = webStorageManager;
        if (webStorage == null) {
            dVar.success(0);
        } else {
            webStorage.getUsageForOrigin(str, new ValueCallback<Long>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.MyWebStorage.3
                @Override // android.webkit.ValueCallback
                public void onReceiveValue(Long l8) {
                    dVar.success(l8);
                }
            });
        }
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.IChannelDelegate, i6.j.c
    public void onMethodCall(i iVar, j.d dVar) {
        Boolean bool;
        init();
        String str = iVar.f7535a;
        str.hashCode();
        char c8 = 65535;
        switch (str.hashCode()) {
            case -1308548435:
                if (str.equals("getQuotaForOrigin")) {
                    c8 = 0;
                    break;
                }
                break;
            case -1117417280:
                if (str.equals("deleteAllData")) {
                    c8 = 1;
                    break;
                }
                break;
            case -876677967:
                if (str.equals("deleteOrigin")) {
                    c8 = 2;
                    break;
                }
                break;
            case -165580329:
                if (str.equals("getOrigins")) {
                    c8 = 3;
                    break;
                }
                break;
            case 843309476:
                if (str.equals("getUsageForOrigin")) {
                    c8 = 4;
                    break;
                }
                break;
        }
        switch (c8) {
            case 0:
                getQuotaForOrigin((String) iVar.a("origin"), dVar);
                return;
            case 1:
                WebStorage webStorage = webStorageManager;
                if (webStorage != null) {
                    webStorage.deleteAllData();
                    bool = Boolean.TRUE;
                    dVar.success(bool);
                    return;
                }
                bool = Boolean.FALSE;
                dVar.success(bool);
                return;
            case 2:
                if (webStorageManager != null) {
                    webStorageManager.deleteOrigin((String) iVar.a("origin"));
                    bool = Boolean.TRUE;
                    dVar.success(bool);
                    return;
                }
                bool = Boolean.FALSE;
                dVar.success(bool);
                return;
            case 3:
                getOrigins(dVar);
                return;
            case 4:
                getUsageForOrigin((String) iVar.a("origin"), dVar);
                return;
            default:
                dVar.notImplemented();
                return;
        }
    }
}
