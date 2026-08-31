package com.pichillilorenzo.flutter_inappwebview_android.print_job;

import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import com.pichillilorenzo.flutter_inappwebview_android.types.PrintJobInfoExt;
import i6.i;
import i6.j;
import java.util.Map;
/* loaded from: classes.dex */
public class PrintJobChannelDelegate extends ChannelDelegateImpl {
    private PrintJobController printJobController;

    public PrintJobChannelDelegate(PrintJobController printJobController, j jVar) {
        super(jVar);
        this.printJobController = printJobController;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        this.printJobController = null;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.IChannelDelegate, i6.j.c
    public void onMethodCall(i iVar, j.d dVar) {
        PrintJobInfoExt info;
        Boolean bool;
        String str = iVar.f7535a;
        str.hashCode();
        char c8 = 65535;
        switch (str.hashCode()) {
            case -1367724422:
                if (str.equals("cancel")) {
                    c8 = 0;
                    break;
                }
                break;
            case -75444956:
                if (str.equals("getInfo")) {
                    c8 = 1;
                    break;
                }
                break;
            case 1097506319:
                if (str.equals("restart")) {
                    c8 = 2;
                    break;
                }
                break;
            case 1671767583:
                if (str.equals("dispose")) {
                    c8 = 3;
                    break;
                }
                break;
        }
        switch (c8) {
            case 0:
                PrintJobController printJobController = this.printJobController;
                if (printJobController != null) {
                    printJobController.cancel();
                    bool = Boolean.TRUE;
                    dVar.success(bool);
                    return;
                }
                bool = Boolean.FALSE;
                dVar.success(bool);
                return;
            case 1:
                PrintJobController printJobController2 = this.printJobController;
                Map<String, Object> map = null;
                if (printJobController2 != null && (info = printJobController2.getInfo()) != null) {
                    map = info.toMap();
                }
                dVar.success(map);
                return;
            case 2:
                PrintJobController printJobController3 = this.printJobController;
                if (printJobController3 != null) {
                    printJobController3.restart();
                    bool = Boolean.TRUE;
                    dVar.success(bool);
                    return;
                }
                bool = Boolean.FALSE;
                dVar.success(bool);
                return;
            case 3:
                PrintJobController printJobController4 = this.printJobController;
                if (printJobController4 != null) {
                    printJobController4.dispose();
                    bool = Boolean.TRUE;
                    dVar.success(bool);
                    return;
                }
                bool = Boolean.FALSE;
                dVar.success(bool);
                return;
            default:
                dVar.notImplemented();
                return;
        }
    }
}
