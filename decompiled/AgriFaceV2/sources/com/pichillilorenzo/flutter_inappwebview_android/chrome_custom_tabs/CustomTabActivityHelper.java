package com.pichillilorenzo.flutter_inappwebview_android.chrome_custom_tabs;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.browser.customtabs.b;
import androidx.browser.customtabs.c;
import androidx.browser.customtabs.d;
import androidx.browser.customtabs.e;
import androidx.browser.customtabs.i;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class CustomTabActivityHelper implements ServiceConnectionCallback {
    private c mClient;
    private e mConnection;
    private ConnectionCallback mConnectionCallback;
    private b mCustomTabsCallback;
    private i mCustomTabsSession;

    /* loaded from: classes.dex */
    public interface ConnectionCallback {
        void onCustomTabsConnected();

        void onCustomTabsDisconnected();
    }

    public static boolean isAvailable(Activity activity) {
        return CustomTabsHelper.getPackageNameToUse(activity) != null;
    }

    public static void openCustomTab(Activity activity, Intent intent, Uri uri, Map<String, String> map, Uri uri2, int i8) {
        intent.setData(uri);
        if (map != null) {
            Bundle bundle = new Bundle();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                bundle.putString(entry.getKey(), entry.getValue());
            }
            intent.putExtra("com.android.browser.headers", bundle);
        }
        if (uri2 != null) {
            intent.putExtra("android.intent.extra.REFERRER", uri2);
        }
        activity.startActivityForResult(intent, i8);
    }

    public static void openTrustedWebActivity(Activity activity, o.b bVar, Uri uri, Map<String, String> map, Uri uri2, int i8) {
        openCustomTab(activity, bVar.a(), uri, map, uri2, i8);
    }

    public boolean bindCustomTabsService(Activity activity) {
        if (this.mClient != null) {
            return true;
        }
        String packageNameToUse = CustomTabsHelper.getPackageNameToUse(activity);
        if (packageNameToUse == null) {
            return false;
        }
        ServiceConnection serviceConnection = new ServiceConnection(this);
        this.mConnection = serviceConnection;
        return c.a(activity, packageNameToUse, serviceConnection);
    }

    public i getSession() {
        i d8;
        c cVar = this.mClient;
        if (cVar != null) {
            if (this.mCustomTabsSession == null) {
                d8 = cVar.d(this.mCustomTabsCallback);
            }
            return this.mCustomTabsSession;
        }
        d8 = null;
        this.mCustomTabsSession = d8;
        return this.mCustomTabsSession;
    }

    public boolean mayLaunchUrl(Uri uri, Bundle bundle, List<Bundle> list) {
        i session;
        if (this.mClient == null || (session = getSession()) == null) {
            return false;
        }
        return session.i(uri, bundle, list);
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.chrome_custom_tabs.ServiceConnectionCallback
    public void onServiceConnected(c cVar) {
        this.mClient = cVar;
        cVar.f(0L);
        ConnectionCallback connectionCallback = this.mConnectionCallback;
        if (connectionCallback != null) {
            connectionCallback.onCustomTabsConnected();
        }
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.chrome_custom_tabs.ServiceConnectionCallback
    public void onServiceDisconnected() {
        this.mClient = null;
        this.mCustomTabsSession = null;
        ConnectionCallback connectionCallback = this.mConnectionCallback;
        if (connectionCallback != null) {
            connectionCallback.onCustomTabsDisconnected();
        }
    }

    public void setConnectionCallback(ConnectionCallback connectionCallback) {
        this.mConnectionCallback = connectionCallback;
    }

    public void setCustomTabsCallback(b bVar) {
        this.mCustomTabsCallback = bVar;
    }

    public void unbindCustomTabsService(Activity activity) {
        e eVar = this.mConnection;
        if (eVar == null) {
            return;
        }
        activity.unbindService(eVar);
        this.mClient = null;
        this.mCustomTabsSession = null;
        this.mConnection = null;
    }

    public static void openCustomTab(Activity activity, d dVar, Uri uri, Map<String, String> map, Uri uri2, int i8) {
        openCustomTab(activity, dVar.f2596a, uri, map, uri2, i8);
    }
}
