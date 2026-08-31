package com.pichillilorenzo.flutter_inappwebview_android.process_global_config;

import androidx.webkit.ProcessGlobalConfig;
import com.pichillilorenzo.flutter_inappwebview_android.InAppWebViewFlutterPlugin;
import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import i6.i;
import i6.j;
import java.util.Map;
/* loaded from: classes.dex */
public class ProcessGlobalConfigManager extends ChannelDelegateImpl {
    protected static final String LOG_TAG = "ProcessGlobalConfigM";
    public static final String METHOD_CHANNEL_NAME = "com.pichillilorenzo/flutter_inappwebview_processglobalconfig";
    public InAppWebViewFlutterPlugin plugin;

    public ProcessGlobalConfigManager(InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin) {
        super(new j(inAppWebViewFlutterPlugin.messenger, METHOD_CHANNEL_NAME));
        this.plugin = inAppWebViewFlutterPlugin;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        this.plugin = null;
    }

    /* JADX WARN: Type inference failed for: r3v4, types: [com.pichillilorenzo.flutter_inappwebview_android.process_global_config.ProcessGlobalConfigSettings] */
    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.IChannelDelegate, i6.j.c
    public void onMethodCall(i iVar, j.d dVar) {
        String str = iVar.f7535a;
        str.hashCode();
        if (!str.equals("apply")) {
            dVar.notImplemented();
            return;
        }
        InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin = this.plugin;
        if (inAppWebViewFlutterPlugin == null || inAppWebViewFlutterPlugin.activity == null) {
            dVar.success(Boolean.FALSE);
            return;
        }
        try {
            ProcessGlobalConfig.a(new ProcessGlobalConfigSettings().parse2((Map) iVar.a("settings")).toProcessGlobalConfig(this.plugin.activity));
            dVar.success(Boolean.TRUE);
        } catch (Exception e8) {
            dVar.error(LOG_TAG, "", e8);
        }
    }
}
