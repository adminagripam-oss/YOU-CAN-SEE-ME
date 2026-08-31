package com.pichillilorenzo.flutter_inappwebview_android;

import android.app.Activity;
import android.content.Context;
import com.pichillilorenzo.flutter_inappwebview_android.chrome_custom_tabs.ChromeSafariBrowserManager;
import com.pichillilorenzo.flutter_inappwebview_android.chrome_custom_tabs.NoHistoryCustomTabsActivityCallbacks;
import com.pichillilorenzo.flutter_inappwebview_android.credential_database.CredentialDatabaseHandler;
import com.pichillilorenzo.flutter_inappwebview_android.headless_in_app_webview.HeadlessInAppWebViewManager;
import com.pichillilorenzo.flutter_inappwebview_android.in_app_browser.InAppBrowserManager;
import com.pichillilorenzo.flutter_inappwebview_android.print_job.PrintJobManager;
import com.pichillilorenzo.flutter_inappwebview_android.process_global_config.ProcessGlobalConfigManager;
import com.pichillilorenzo.flutter_inappwebview_android.proxy.ProxyManager;
import com.pichillilorenzo.flutter_inappwebview_android.service_worker.ServiceWorkerManager;
import com.pichillilorenzo.flutter_inappwebview_android.tracing.TracingControllerManager;
import com.pichillilorenzo.flutter_inappwebview_android.webview.FlutterWebViewFactory;
import com.pichillilorenzo.flutter_inappwebview_android.webview.InAppWebViewManager;
import e6.a;
import f6.c;
import i6.b;
import io.flutter.embedding.android.s;
import io.flutter.plugin.platform.m;
/* loaded from: classes.dex */
public class InAppWebViewFlutterPlugin implements e6.a, f6.a {
    protected static final String LOG_TAG = "InAppWebViewFlutterPL";
    public Activity activity;
    public c activityPluginBinding;
    public Context applicationContext;
    public ChromeSafariBrowserManager chromeSafariBrowserManager;
    public CredentialDatabaseHandler credentialDatabaseHandler;
    public a.InterfaceC0083a flutterAssets;
    public s flutterView;
    public FlutterWebViewFactory flutterWebViewFactory;
    public HeadlessInAppWebViewManager headlessInAppWebViewManager;
    public InAppBrowserManager inAppBrowserManager;
    public InAppWebViewManager inAppWebViewManager;
    public b messenger;
    public MyCookieManager myCookieManager;
    public MyWebStorage myWebStorage;
    public NoHistoryCustomTabsActivityCallbacks noHistoryCustomTabsActivityCallbacks;
    public PlatformUtil platformUtil;
    public PrintJobManager printJobManager;
    public ProcessGlobalConfigManager processGlobalConfigManager;
    public ProxyManager proxyManager;
    public ServiceWorkerManager serviceWorkerManager;
    public TracingControllerManager tracingControllerManager;
    public WebViewFeatureManager webViewFeatureManager;

    private void onAttachedToEngine(Context context, b bVar, Activity activity, m mVar, s sVar) {
        this.applicationContext = context;
        this.activity = activity;
        this.messenger = bVar;
        this.flutterView = sVar;
        this.inAppBrowserManager = new InAppBrowserManager(this);
        this.headlessInAppWebViewManager = new HeadlessInAppWebViewManager(this);
        this.chromeSafariBrowserManager = new ChromeSafariBrowserManager(this);
        this.noHistoryCustomTabsActivityCallbacks = new NoHistoryCustomTabsActivityCallbacks(this);
        FlutterWebViewFactory flutterWebViewFactory = new FlutterWebViewFactory(this);
        this.flutterWebViewFactory = flutterWebViewFactory;
        mVar.a(FlutterWebViewFactory.VIEW_TYPE_ID, flutterWebViewFactory);
        this.platformUtil = new PlatformUtil(this);
        this.inAppWebViewManager = new InAppWebViewManager(this);
        this.myCookieManager = new MyCookieManager(this);
        this.myWebStorage = new MyWebStorage(this);
        this.serviceWorkerManager = new ServiceWorkerManager(this);
        this.credentialDatabaseHandler = new CredentialDatabaseHandler(this);
        this.webViewFeatureManager = new WebViewFeatureManager(this);
        this.proxyManager = new ProxyManager(this);
        this.printJobManager = new PrintJobManager(this);
        this.tracingControllerManager = new TracingControllerManager(this);
        this.processGlobalConfigManager = new ProcessGlobalConfigManager(this);
    }

    @Override // f6.a
    public void onAttachedToActivity(c cVar) {
        this.activityPluginBinding = cVar;
        Activity activity = cVar.getActivity();
        this.activity = activity;
        if (this.noHistoryCustomTabsActivityCallbacks != null) {
            activity.getApplication().registerActivityLifecycleCallbacks(this.noHistoryCustomTabsActivityCallbacks.activityLifecycleCallbacks);
        }
    }

    @Override // f6.a
    public void onDetachedFromActivity() {
        Activity activity = this.activity;
        if (activity != null && this.noHistoryCustomTabsActivityCallbacks != null) {
            activity.getApplication().unregisterActivityLifecycleCallbacks(this.noHistoryCustomTabsActivityCallbacks.activityLifecycleCallbacks);
        }
        this.activityPluginBinding = null;
        this.activity = null;
    }

    @Override // f6.a
    public void onDetachedFromActivityForConfigChanges() {
        Activity activity = this.activity;
        if (activity != null && this.noHistoryCustomTabsActivityCallbacks != null) {
            activity.getApplication().unregisterActivityLifecycleCallbacks(this.noHistoryCustomTabsActivityCallbacks.activityLifecycleCallbacks);
        }
        this.activityPluginBinding = null;
        this.activity = null;
    }

    @Override // e6.a
    public void onDetachedFromEngine(a.b bVar) {
        PlatformUtil platformUtil = this.platformUtil;
        if (platformUtil != null) {
            platformUtil.dispose();
            this.platformUtil = null;
        }
        InAppBrowserManager inAppBrowserManager = this.inAppBrowserManager;
        if (inAppBrowserManager != null) {
            inAppBrowserManager.dispose();
            this.inAppBrowserManager = null;
        }
        HeadlessInAppWebViewManager headlessInAppWebViewManager = this.headlessInAppWebViewManager;
        if (headlessInAppWebViewManager != null) {
            headlessInAppWebViewManager.dispose();
            this.headlessInAppWebViewManager = null;
        }
        ChromeSafariBrowserManager chromeSafariBrowserManager = this.chromeSafariBrowserManager;
        if (chromeSafariBrowserManager != null) {
            chromeSafariBrowserManager.dispose();
            this.chromeSafariBrowserManager = null;
        }
        NoHistoryCustomTabsActivityCallbacks noHistoryCustomTabsActivityCallbacks = this.noHistoryCustomTabsActivityCallbacks;
        if (noHistoryCustomTabsActivityCallbacks != null) {
            noHistoryCustomTabsActivityCallbacks.dispose();
            this.noHistoryCustomTabsActivityCallbacks = null;
        }
        MyCookieManager myCookieManager = this.myCookieManager;
        if (myCookieManager != null) {
            myCookieManager.dispose();
            this.myCookieManager = null;
        }
        MyWebStorage myWebStorage = this.myWebStorage;
        if (myWebStorage != null) {
            myWebStorage.dispose();
            this.myWebStorage = null;
        }
        CredentialDatabaseHandler credentialDatabaseHandler = this.credentialDatabaseHandler;
        if (credentialDatabaseHandler != null) {
            credentialDatabaseHandler.dispose();
            this.credentialDatabaseHandler = null;
        }
        InAppWebViewManager inAppWebViewManager = this.inAppWebViewManager;
        if (inAppWebViewManager != null) {
            inAppWebViewManager.dispose();
            this.inAppWebViewManager = null;
        }
        ServiceWorkerManager serviceWorkerManager = this.serviceWorkerManager;
        if (serviceWorkerManager != null) {
            serviceWorkerManager.dispose();
            this.serviceWorkerManager = null;
        }
        WebViewFeatureManager webViewFeatureManager = this.webViewFeatureManager;
        if (webViewFeatureManager != null) {
            webViewFeatureManager.dispose();
            this.webViewFeatureManager = null;
        }
        ProxyManager proxyManager = this.proxyManager;
        if (proxyManager != null) {
            proxyManager.dispose();
            this.proxyManager = null;
        }
        PrintJobManager printJobManager = this.printJobManager;
        if (printJobManager != null) {
            printJobManager.dispose();
            this.printJobManager = null;
        }
        TracingControllerManager tracingControllerManager = this.tracingControllerManager;
        if (tracingControllerManager != null) {
            tracingControllerManager.dispose();
            this.tracingControllerManager = null;
        }
        ProcessGlobalConfigManager processGlobalConfigManager = this.processGlobalConfigManager;
        if (processGlobalConfigManager != null) {
            processGlobalConfigManager.dispose();
            this.processGlobalConfigManager = null;
        }
    }

    @Override // f6.a
    public void onReattachedToActivityForConfigChanges(c cVar) {
        this.activityPluginBinding = cVar;
        Activity activity = cVar.getActivity();
        this.activity = activity;
        if (this.noHistoryCustomTabsActivityCallbacks != null) {
            activity.getApplication().registerActivityLifecycleCallbacks(this.noHistoryCustomTabsActivityCallbacks.activityLifecycleCallbacks);
        }
    }

    @Override // e6.a
    public void onAttachedToEngine(a.b bVar) {
        this.flutterAssets = bVar.c();
        onAttachedToEngine(bVar.a(), bVar.b(), this.activity, bVar.d(), null);
    }
}
