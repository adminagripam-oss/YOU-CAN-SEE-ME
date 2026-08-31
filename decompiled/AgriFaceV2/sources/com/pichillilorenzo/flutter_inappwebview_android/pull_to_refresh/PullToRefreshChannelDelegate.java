package com.pichillilorenzo.flutter_inappwebview_android.pull_to_refresh;

import android.graphics.Color;
import androidx.browser.customtabs.b;
import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import i6.i;
import i6.j;
import java.util.HashMap;
/* loaded from: classes.dex */
public class PullToRefreshChannelDelegate extends ChannelDelegateImpl {
    private PullToRefreshLayout pullToRefreshView;

    public PullToRefreshChannelDelegate(PullToRefreshLayout pullToRefreshLayout, j jVar) {
        super(jVar);
        this.pullToRefreshView = pullToRefreshLayout;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        this.pullToRefreshView = null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.IChannelDelegate, i6.j.c
    public void onMethodCall(i iVar, j.d dVar) {
        char c8;
        int i8;
        String str = iVar.f7535a;
        str.hashCode();
        boolean z7 = true;
        switch (str.hashCode()) {
            case -1790841290:
                if (str.equals("setSlingshotDistance")) {
                    c8 = 0;
                    break;
                }
                c8 = 65535;
                break;
            case 154556713:
                if (str.equals("setRefreshing")) {
                    c8 = 1;
                    break;
                }
                c8 = 65535;
                break;
            case 1235582893:
                if (str.equals("getDefaultSlingshotDistance")) {
                    c8 = 2;
                    break;
                }
                c8 = 65535;
                break;
            case 1364071551:
                if (str.equals("setEnabled")) {
                    c8 = 3;
                    break;
                }
                c8 = 65535;
                break;
            case 1389555745:
                if (str.equals("setColor")) {
                    c8 = 4;
                    break;
                }
                c8 = 65535;
                break;
            case 1743806995:
                if (str.equals("setBackgroundColor")) {
                    c8 = 5;
                    break;
                }
                c8 = 65535;
                break;
            case 1807783361:
                if (str.equals("setDistanceToTriggerSync")) {
                    c8 = 6;
                    break;
                }
                c8 = 65535;
                break;
            case 1849446385:
                if (str.equals("isRefreshing")) {
                    c8 = 7;
                    break;
                }
                c8 = 65535;
                break;
            case 1984958339:
                if (str.equals("setSize")) {
                    c8 = '\b';
                    break;
                }
                c8 = 65535;
                break;
            case 2105594551:
                if (str.equals("isEnabled")) {
                    c8 = '\t';
                    break;
                }
                c8 = 65535;
                break;
            default:
                c8 = 65535;
                break;
        }
        switch (c8) {
            case 0:
                if (this.pullToRefreshView != null) {
                    this.pullToRefreshView.setSlingshotDistance(((Integer) iVar.a("slingshotDistance")).intValue());
                    i8 = Boolean.TRUE;
                    break;
                }
                i8 = Boolean.FALSE;
                break;
            case 1:
                if (this.pullToRefreshView != null) {
                    this.pullToRefreshView.setRefreshing(((Boolean) iVar.a("refreshing")).booleanValue());
                    i8 = Boolean.TRUE;
                    break;
                }
                i8 = Boolean.FALSE;
                break;
            case 2:
                i8 = -1;
                break;
            case 3:
                if (this.pullToRefreshView != null) {
                    Boolean bool = (Boolean) iVar.a("enabled");
                    PullToRefreshLayout pullToRefreshLayout = this.pullToRefreshView;
                    pullToRefreshLayout.settings.enabled = bool;
                    pullToRefreshLayout.setEnabled(bool.booleanValue());
                    i8 = Boolean.TRUE;
                    break;
                }
                i8 = Boolean.FALSE;
                break;
            case 4:
                if (this.pullToRefreshView != null) {
                    this.pullToRefreshView.setColorSchemeColors(Color.parseColor((String) iVar.a("color")));
                    i8 = Boolean.TRUE;
                    break;
                }
                i8 = Boolean.FALSE;
                break;
            case 5:
                if (this.pullToRefreshView != null) {
                    this.pullToRefreshView.setProgressBackgroundColorSchemeColor(Color.parseColor((String) iVar.a("color")));
                    i8 = Boolean.TRUE;
                    break;
                }
                i8 = Boolean.FALSE;
                break;
            case b.TAB_HIDDEN /* 6 */:
                if (this.pullToRefreshView != null) {
                    this.pullToRefreshView.setDistanceToTriggerSync(((Integer) iVar.a("distanceToTriggerSync")).intValue());
                    i8 = Boolean.TRUE;
                    break;
                }
                i8 = Boolean.FALSE;
                break;
            case 7:
                PullToRefreshLayout pullToRefreshLayout2 = this.pullToRefreshView;
                if (pullToRefreshLayout2 == null || !pullToRefreshLayout2.isRefreshing()) {
                    z7 = false;
                }
                i8 = Boolean.valueOf(z7);
                break;
            case '\b':
                if (this.pullToRefreshView != null) {
                    this.pullToRefreshView.setSize(((Integer) iVar.a("size")).intValue());
                    i8 = Boolean.TRUE;
                    break;
                }
                i8 = Boolean.FALSE;
                break;
            case '\t':
                PullToRefreshLayout pullToRefreshLayout3 = this.pullToRefreshView;
                if (pullToRefreshLayout3 != null) {
                    i8 = Boolean.valueOf(pullToRefreshLayout3.isEnabled());
                    break;
                }
                i8 = Boolean.FALSE;
                break;
            default:
                dVar.notImplemented();
                return;
        }
        dVar.success(i8);
    }

    public void onRefresh() {
        j channel = getChannel();
        if (channel == null) {
            return;
        }
        channel.c("onRefresh", new HashMap());
    }
}
