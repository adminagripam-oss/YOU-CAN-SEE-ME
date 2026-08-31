package com.pichillilorenzo.flutter_inappwebview_android.print_job;

import android.print.PrintJob;
import com.pichillilorenzo.flutter_inappwebview_android.InAppWebViewFlutterPlugin;
import com.pichillilorenzo.flutter_inappwebview_android.types.Disposable;
import com.pichillilorenzo.flutter_inappwebview_android.types.PrintJobInfoExt;
import i6.b;
import i6.j;
/* loaded from: classes.dex */
public class PrintJobController implements Disposable {
    protected static final String LOG_TAG = "PrintJob";
    public static final String METHOD_CHANNEL_NAME_PREFIX = "com.pichillilorenzo/flutter_inappwebview_printjobcontroller_";
    public PrintJobChannelDelegate channelDelegate;
    public String id;
    public PrintJob job;
    public InAppWebViewFlutterPlugin plugin;
    public PrintJobSettings settings;

    public PrintJobController(String str, PrintJob printJob, PrintJobSettings printJobSettings, InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin) {
        this.id = str;
        this.plugin = inAppWebViewFlutterPlugin;
        this.job = printJob;
        this.settings = printJobSettings;
        b bVar = inAppWebViewFlutterPlugin.messenger;
        this.channelDelegate = new PrintJobChannelDelegate(this, new j(bVar, METHOD_CHANNEL_NAME_PREFIX + str));
    }

    public void cancel() {
        PrintJob printJob = this.job;
        if (printJob != null) {
            printJob.cancel();
        }
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        PrintJobManager printJobManager;
        PrintJobChannelDelegate printJobChannelDelegate = this.channelDelegate;
        if (printJobChannelDelegate != null) {
            printJobChannelDelegate.dispose();
            this.channelDelegate = null;
        }
        InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin = this.plugin;
        if (inAppWebViewFlutterPlugin != null && (printJobManager = inAppWebViewFlutterPlugin.printJobManager) != null && printJobManager.jobs.containsKey(this.id)) {
            printJobManager.jobs.put(this.id, null);
        }
        PrintJob printJob = this.job;
        if (printJob != null) {
            printJob.cancel();
            this.job = null;
        }
        this.plugin = null;
    }

    public void disposeNoCancel() {
        PrintJobManager printJobManager;
        PrintJobChannelDelegate printJobChannelDelegate = this.channelDelegate;
        if (printJobChannelDelegate != null) {
            printJobChannelDelegate.dispose();
            this.channelDelegate = null;
        }
        InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin = this.plugin;
        if (inAppWebViewFlutterPlugin != null && (printJobManager = inAppWebViewFlutterPlugin.printJobManager) != null && printJobManager.jobs.containsKey(this.id)) {
            printJobManager.jobs.put(this.id, null);
        }
        if (this.job != null) {
            this.job = null;
        }
        this.plugin = null;
    }

    public PrintJobInfoExt getInfo() {
        PrintJob printJob = this.job;
        if (printJob != null) {
            return PrintJobInfoExt.fromPrintJobInfo(printJob.getInfo());
        }
        return null;
    }

    public void restart() {
        PrintJob printJob = this.job;
        if (printJob != null) {
            printJob.restart();
        }
    }
}
