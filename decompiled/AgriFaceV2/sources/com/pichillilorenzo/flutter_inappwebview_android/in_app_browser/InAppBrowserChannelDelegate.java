package com.pichillilorenzo.flutter_inappwebview_android.in_app_browser;

import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import com.pichillilorenzo.flutter_inappwebview_android.types.InAppBrowserMenuItem;
import i6.j;
import java.util.HashMap;
/* loaded from: classes.dex */
public class InAppBrowserChannelDelegate extends ChannelDelegateImpl {
    public InAppBrowserChannelDelegate(j jVar) {
        super(jVar);
    }

    public void onBrowserCreated() {
        j channel = getChannel();
        if (channel == null) {
            return;
        }
        channel.c("onBrowserCreated", new HashMap());
    }

    public void onExit() {
        j channel = getChannel();
        if (channel == null) {
            return;
        }
        channel.c("onExit", new HashMap());
    }

    public void onMenuItemClicked(InAppBrowserMenuItem inAppBrowserMenuItem) {
        j channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("id", Integer.valueOf(inAppBrowserMenuItem.getId()));
        channel.c("onMenuItemClicked", hashMap);
    }
}
