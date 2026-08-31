package com.pichillilorenzo.flutter_inappwebview_android.tracing;

import c2.l;
import c2.t;
import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import i6.i;
import i6.j;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.concurrent.Executors;
/* loaded from: classes.dex */
public class TracingControllerChannelDelegate extends ChannelDelegateImpl {
    private TracingControllerManager tracingControllerManager;

    public TracingControllerChannelDelegate(TracingControllerManager tracingControllerManager, j jVar) {
        super(jVar);
        this.tracingControllerManager = tracingControllerManager;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        this.tracingControllerManager = null;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.IChannelDelegate, i6.j.c
    public void onMethodCall(i iVar, j.d dVar) {
        Boolean valueOf;
        FileOutputStream fileOutputStream;
        TracingControllerManager.init();
        l lVar = TracingControllerManager.tracingController;
        String str = iVar.f7535a;
        str.hashCode();
        char c8 = 65535;
        switch (str.hashCode()) {
            case -1647175624:
                if (str.equals("isTracing")) {
                    c8 = 0;
                    break;
                }
                break;
            case 3540994:
                if (str.equals("stop")) {
                    c8 = 1;
                    break;
                }
                break;
            case 109757538:
                if (str.equals("start")) {
                    c8 = 2;
                    break;
                }
                break;
        }
        switch (c8) {
            case 0:
                if (lVar != null) {
                    valueOf = Boolean.valueOf(lVar.b());
                    break;
                }
                valueOf = Boolean.FALSE;
                break;
            case 1:
                if (lVar != null && t.a("TRACING_CONTROLLER_BASIC_USAGE")) {
                    String str2 = (String) iVar.a("filePath");
                    if (str2 != null) {
                        try {
                            fileOutputStream = new FileOutputStream(str2);
                        } catch (FileNotFoundException e8) {
                            e8.printStackTrace();
                        }
                    } else {
                        fileOutputStream = null;
                    }
                    dVar.success(Boolean.valueOf(lVar.d(fileOutputStream, Executors.newSingleThreadExecutor())));
                    return;
                }
                valueOf = Boolean.FALSE;
                break;
            case 2:
                if (lVar != null && t.a("TRACING_CONTROLLER_BASIC_USAGE")) {
                    TracingSettings tracingSettings = new TracingSettings();
                    tracingSettings.parse2((Map) iVar.a("settings"));
                    lVar.c(TracingControllerManager.buildTracingConfig(tracingSettings));
                    valueOf = Boolean.TRUE;
                    break;
                }
                valueOf = Boolean.FALSE;
                break;
            default:
                dVar.notImplemented();
                return;
        }
        dVar.success(valueOf);
    }
}
