package com.pichillilorenzo.flutter_inappwebview_android.chrome_custom_tabs;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.pichillilorenzo.flutter_inappwebview_android.InAppWebViewFlutterPlugin;
import com.pichillilorenzo.flutter_inappwebview_android.types.Disposable;
import io.flutter.embedding.android.e;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class NoHistoryCustomTabsActivityCallbacks implements Disposable {
    public InAppWebViewFlutterPlugin plugin;
    public final Map<String, String> noHistoryBrowserIDs = new HashMap();
    public Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = new Application.ActivityLifecycleCallbacks() { // from class: com.pichillilorenzo.flutter_inappwebview_android.chrome_custom_tabs.NoHistoryCustomTabsActivityCallbacks.1
        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            NoHistoryCustomTabsActivityCallbacks noHistoryCustomTabsActivityCallbacks;
            InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin;
            if (!(activity instanceof e) || (inAppWebViewFlutterPlugin = (noHistoryCustomTabsActivityCallbacks = NoHistoryCustomTabsActivityCallbacks.this).plugin) == null || inAppWebViewFlutterPlugin.chromeSafariBrowserManager == null) {
                return;
            }
            for (String str : noHistoryCustomTabsActivityCallbacks.noHistoryBrowserIDs.values()) {
                if (str != null) {
                    NoHistoryCustomTabsActivityCallbacks.this.noHistoryBrowserIDs.put(str, null);
                    ChromeCustomTabsActivity chromeCustomTabsActivity = NoHistoryCustomTabsActivityCallbacks.this.plugin.chromeSafariBrowserManager.browsers.get(str);
                    if (chromeCustomTabsActivity != null) {
                        chromeCustomTabsActivity.close();
                        chromeCustomTabsActivity.dispose();
                    }
                }
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }
    };

    public NoHistoryCustomTabsActivityCallbacks(InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin) {
        this.plugin = inAppWebViewFlutterPlugin;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        this.noHistoryBrowserIDs.clear();
        this.plugin = null;
    }
}
