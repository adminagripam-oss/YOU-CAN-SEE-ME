package com.pichillilorenzo.flutter_inappwebview_android.in_app_browser;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.pichillilorenzo.flutter_inappwebview_android.InAppWebViewFlutterPlugin;
import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import i6.i;
import i6.j;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
/* loaded from: classes.dex */
public class InAppBrowserManager extends ChannelDelegateImpl {
    protected static final String LOG_TAG = "InAppBrowserManager";
    public static final String METHOD_CHANNEL_NAME = "com.pichillilorenzo/flutter_inappbrowser";
    public static final Map<String, InAppBrowserManager> shared = new HashMap();
    public String id;
    public InAppWebViewFlutterPlugin plugin;

    public InAppBrowserManager(InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin) {
        super(new j(inAppWebViewFlutterPlugin.messenger, METHOD_CHANNEL_NAME));
        String uuid = UUID.randomUUID().toString();
        this.id = uuid;
        this.plugin = inAppWebViewFlutterPlugin;
        shared.put(uuid, this);
    }

    public static String getMimeType(String str) {
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
        if (fileExtensionFromUrl != null) {
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
        }
        return null;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        shared.remove(this.id);
        this.plugin = null;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.IChannelDelegate, i6.j.c
    public void onMethodCall(i iVar, j.d dVar) {
        Boolean bool;
        Activity activity;
        String str = iVar.f7535a;
        str.hashCode();
        if (str.equals("openWithSystemBrowser")) {
            InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin = this.plugin;
            if (inAppWebViewFlutterPlugin != null && inAppWebViewFlutterPlugin.activity != null) {
                openWithSystemBrowser(this.plugin.activity, (String) iVar.a("url"), dVar);
                return;
            }
        } else if (!str.equals("open")) {
            dVar.notImplemented();
            return;
        } else {
            InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin2 = this.plugin;
            if (inAppWebViewFlutterPlugin2 != null && (activity = inAppWebViewFlutterPlugin2.activity) != null) {
                open(activity, (Map) iVar.b());
                bool = Boolean.TRUE;
                dVar.success(bool);
            }
        }
        bool = Boolean.FALSE;
        dVar.success(bool);
    }

    public void open(Activity activity, Map<String, Object> map) {
        Integer num = (Integer) map.get("windowId");
        List list = (List) map.get("initialUserScripts");
        Map map2 = (Map) map.get("pullToRefreshSettings");
        List list2 = (List) map.get("menuItems");
        Bundle bundle = new Bundle();
        bundle.putString("fromActivity", activity.getClass().getName());
        bundle.putSerializable("initialUrlRequest", (Serializable) ((Map) map.get("urlRequest")));
        bundle.putString("initialFile", (String) map.get("assetFilePath"));
        bundle.putString("initialData", (String) map.get("data"));
        bundle.putString("initialMimeType", (String) map.get("mimeType"));
        bundle.putString("initialEncoding", (String) map.get("encoding"));
        bundle.putString("initialBaseUrl", (String) map.get("baseUrl"));
        bundle.putString("initialHistoryUrl", (String) map.get("historyUrl"));
        bundle.putString("id", (String) map.get("id"));
        bundle.putString("managerId", this.id);
        bundle.putSerializable("settings", (Serializable) ((Map) map.get("settings")));
        bundle.putSerializable("contextMenu", (Serializable) ((Map) map.get("contextMenu")));
        bundle.putInt("windowId", num != null ? num.intValue() : -1);
        bundle.putSerializable("initialUserScripts", (Serializable) list);
        bundle.putSerializable("pullToRefreshInitialSettings", (Serializable) map2);
        bundle.putSerializable("menuItems", (Serializable) list2);
        startInAppBrowserActivity(activity, bundle);
    }

    public void openExternalExcludeCurrentApp(Activity activity, Intent intent) {
        String packageName = activity.getPackageName();
        List<ResolveInfo> queryIntentActivities = activity.getPackageManager().queryIntentActivities(intent, 0);
        ArrayList arrayList = new ArrayList();
        boolean z7 = false;
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            if (packageName.equals(resolveInfo.activityInfo.packageName)) {
                z7 = true;
            } else {
                Intent intent2 = (Intent) intent.clone();
                intent2.setPackage(resolveInfo.activityInfo.packageName);
                arrayList.add(intent2);
            }
        }
        if (z7 && arrayList.size() != 0) {
            if (arrayList.size() == 1) {
                intent = (Intent) arrayList.get(0);
            } else if (arrayList.size() <= 0) {
                return;
            } else {
                intent = Intent.createChooser((Intent) arrayList.remove(arrayList.size() - 1), null);
                intent.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[0]));
            }
        }
        activity.startActivity(intent);
    }

    public void openWithSystemBrowser(Activity activity, String str, j.d dVar) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            Uri parse = Uri.parse(str);
            if ("file".equals(parse.getScheme())) {
                intent.setDataAndType(parse, getMimeType(str));
            } else {
                intent.setData(parse);
            }
            intent.putExtra("com.android.browser.application_id", activity.getPackageName());
            openExternalExcludeCurrentApp(activity, intent);
            dVar.success(Boolean.TRUE);
        } catch (RuntimeException e8) {
            Log.d(LOG_TAG, str + " cannot be opened: " + e8.toString());
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" cannot be opened!");
            dVar.error(LOG_TAG, sb.toString(), null);
        }
    }

    public void startInAppBrowserActivity(Activity activity, Bundle bundle) {
        Intent intent = new Intent(activity, InAppBrowserActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
    }
}
