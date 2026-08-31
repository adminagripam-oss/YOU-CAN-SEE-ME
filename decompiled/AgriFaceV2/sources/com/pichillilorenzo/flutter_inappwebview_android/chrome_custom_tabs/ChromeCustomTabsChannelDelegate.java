package com.pichillilorenzo.flutter_inappwebview_android.chrome_custom_tabs;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.browser.customtabs.b;
import com.pichillilorenzo.flutter_inappwebview_android.InAppWebViewFlutterPlugin;
import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import com.pichillilorenzo.flutter_inappwebview_android.types.CustomTabsSecondaryToolbar;
import i6.i;
import i6.j;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class ChromeCustomTabsChannelDelegate extends ChannelDelegateImpl {
    private ChromeCustomTabsActivity chromeCustomTabsActivity;

    public ChromeCustomTabsChannelDelegate(ChromeCustomTabsActivity chromeCustomTabsActivity, j jVar) {
        super(jVar);
        this.chromeCustomTabsActivity = chromeCustomTabsActivity;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        this.chromeCustomTabsActivity = null;
    }

    public void onClosed() {
        j channel = getChannel();
        if (channel == null) {
            return;
        }
        channel.c("onClosed", new HashMap());
    }

    public void onCompletedInitialLoad() {
        j channel = getChannel();
        if (channel == null) {
            return;
        }
        channel.c("onCompletedInitialLoad", new HashMap());
    }

    public void onGreatestScrollPercentageIncreased(int i8) {
        j channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("scrollPercentage", Integer.valueOf(i8));
        channel.c("onGreatestScrollPercentageIncreased", hashMap);
    }

    public void onItemActionPerform(int i8, String str, String str2) {
        j channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("id", Integer.valueOf(i8));
        hashMap.put("url", str);
        hashMap.put("title", str2);
        channel.c("onItemActionPerform", hashMap);
    }

    public void onMessageChannelReady() {
        j channel = getChannel();
        if (channel == null) {
            return;
        }
        channel.c("onMessageChannelReady", new HashMap());
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.IChannelDelegate, i6.j.c
    public void onMethodCall(i iVar, j.d dVar) {
        androidx.browser.customtabs.i iVar2;
        String str;
        Object obj;
        InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin;
        Activity activity;
        boolean o8;
        String str2 = iVar.f7535a;
        str2.hashCode();
        char c8 = 65535;
        switch (str2.hashCode()) {
            case -1526944655:
                if (str2.equals("isEngagementSignalsApiAvailable")) {
                    c8 = 0;
                    break;
                }
                break;
            case -675108676:
                if (str2.equals("launchUrl")) {
                    c8 = 1;
                    break;
                }
                break;
            case -334843312:
                if (str2.equals("updateSecondaryToolbar")) {
                    c8 = 2;
                    break;
                }
                break;
            case 50870385:
                if (str2.equals("updateActionButton")) {
                    c8 = 3;
                    break;
                }
                break;
            case 94756344:
                if (str2.equals("close")) {
                    c8 = 4;
                    break;
                }
                break;
            case 1256059502:
                if (str2.equals("validateRelationship")) {
                    c8 = 5;
                    break;
                }
                break;
            case 1392239787:
                if (str2.equals("requestPostMessageChannel")) {
                    c8 = 6;
                    break;
                }
                break;
            case 1490029383:
                if (str2.equals("postMessage")) {
                    c8 = 7;
                    break;
                }
                break;
            case 2000053463:
                if (str2.equals("mayLaunchUrl")) {
                    c8 = '\b';
                    break;
                }
                break;
        }
        switch (c8) {
            case 0:
                ChromeCustomTabsActivity chromeCustomTabsActivity = this.chromeCustomTabsActivity;
                if (chromeCustomTabsActivity != null && (iVar2 = chromeCustomTabsActivity.customTabsSession) != null) {
                    try {
                        dVar.success(Boolean.valueOf(iVar2.h(new Bundle())));
                        return;
                    } catch (Throwable unused) {
                    }
                }
                obj = Boolean.FALSE;
                break;
            case 1:
                if (this.chromeCustomTabsActivity != null && (str = (String) iVar.a("url")) != null) {
                    this.chromeCustomTabsActivity.launchUrl(str, (Map) iVar.a("headers"), (String) iVar.a("referrer"), (List) iVar.a("otherLikelyURLs"));
                    obj = Boolean.TRUE;
                    break;
                }
                obj = Boolean.FALSE;
                break;
            case 2:
                if (this.chromeCustomTabsActivity != null) {
                    this.chromeCustomTabsActivity.updateSecondaryToolbar(CustomTabsSecondaryToolbar.fromMap((Map) iVar.a("secondaryToolbar")));
                    obj = Boolean.TRUE;
                    break;
                }
                obj = Boolean.FALSE;
                break;
            case 3:
                if (this.chromeCustomTabsActivity != null) {
                    this.chromeCustomTabsActivity.updateActionButton((byte[]) iVar.a("icon"), (String) iVar.a("description"));
                    obj = Boolean.TRUE;
                    break;
                }
                obj = Boolean.FALSE;
                break;
            case 4:
                ChromeCustomTabsActivity chromeCustomTabsActivity2 = this.chromeCustomTabsActivity;
                if (chromeCustomTabsActivity2 != null) {
                    chromeCustomTabsActivity2.onStop();
                    this.chromeCustomTabsActivity.onDestroy();
                    this.chromeCustomTabsActivity.close();
                    ChromeSafariBrowserManager chromeSafariBrowserManager = this.chromeCustomTabsActivity.manager;
                    if (chromeSafariBrowserManager != null && (inAppWebViewFlutterPlugin = chromeSafariBrowserManager.plugin) != null && (activity = inAppWebViewFlutterPlugin.activity) != null) {
                        Intent intent = new Intent(activity, activity.getClass());
                        intent.addFlags(67108864);
                        intent.addFlags(536870912);
                        activity.startActivity(intent);
                    }
                    this.chromeCustomTabsActivity.dispose();
                    obj = Boolean.TRUE;
                    break;
                }
                obj = Boolean.FALSE;
                break;
            case 5:
                ChromeCustomTabsActivity chromeCustomTabsActivity3 = this.chromeCustomTabsActivity;
                if (chromeCustomTabsActivity3 != null && chromeCustomTabsActivity3.customTabsSession != null) {
                    o8 = this.chromeCustomTabsActivity.customTabsSession.o(((Integer) iVar.a("relation")).intValue(), Uri.parse((String) iVar.a("origin")), null);
                    obj = Boolean.valueOf(o8);
                    break;
                }
                obj = Boolean.FALSE;
                break;
            case b.TAB_HIDDEN /* 6 */:
                ChromeCustomTabsActivity chromeCustomTabsActivity4 = this.chromeCustomTabsActivity;
                if (chromeCustomTabsActivity4 != null && chromeCustomTabsActivity4.customTabsSession != null) {
                    String str3 = (String) iVar.a("targetOrigin");
                    o8 = this.chromeCustomTabsActivity.customTabsSession.k(Uri.parse((String) iVar.a("sourceOrigin")), str3 != null ? Uri.parse(str3) : null, new Bundle());
                    obj = Boolean.valueOf(o8);
                    break;
                }
                obj = Boolean.FALSE;
                break;
            case 7:
                ChromeCustomTabsActivity chromeCustomTabsActivity5 = this.chromeCustomTabsActivity;
                obj = Integer.valueOf((chromeCustomTabsActivity5 == null || chromeCustomTabsActivity5.customTabsSession == null) ? -3 : this.chromeCustomTabsActivity.customTabsSession.j((String) iVar.a("message"), new Bundle()));
                break;
            case '\b':
                if (this.chromeCustomTabsActivity != null) {
                    o8 = this.chromeCustomTabsActivity.mayLaunchUrl((String) iVar.a("url"), (List) iVar.a("otherLikelyURLs"));
                    obj = Boolean.valueOf(o8);
                    break;
                }
                obj = Boolean.FALSE;
                break;
            default:
                dVar.notImplemented();
                return;
        }
        dVar.success(obj);
    }

    public void onNavigationEvent(int i8) {
        j channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("navigationEvent", Integer.valueOf(i8));
        channel.c("onNavigationEvent", hashMap);
    }

    public void onOpened() {
        j channel = getChannel();
        if (channel == null) {
            return;
        }
        channel.c("onOpened", new HashMap());
    }

    public void onPostMessage(String str) {
        j channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("message", str);
        channel.c("onPostMessage", hashMap);
    }

    public void onRelationshipValidationResult(int i8, Uri uri, boolean z7) {
        j channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("relation", Integer.valueOf(i8));
        hashMap.put("requestedOrigin", uri.toString());
        hashMap.put("result", Boolean.valueOf(z7));
        channel.c("onRelationshipValidationResult", hashMap);
    }

    public void onSecondaryItemActionPerform(String str, String str2) {
        j channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("name", str);
        hashMap.put("url", str2);
        channel.c("onSecondaryItemActionPerform", hashMap);
    }

    public void onServiceConnected() {
        j channel = getChannel();
        if (channel == null) {
            return;
        }
        channel.c("onServiceConnected", new HashMap());
    }

    public void onSessionEnded(boolean z7) {
        j channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("didUserInteract", Boolean.valueOf(z7));
        channel.c("onSessionEnded", hashMap);
    }

    public void onVerticalScrollEvent(boolean z7) {
        j channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("isDirectionUp", Boolean.valueOf(z7));
        channel.c("onVerticalScrollEvent", hashMap);
    }
}
