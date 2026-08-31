package com.pichillilorenzo.flutter_inappwebview_android.webview;

import android.view.View;
import io.flutter.plugin.platform.k;
import java.util.HashMap;
/* loaded from: classes.dex */
public interface PlatformWebView extends k {
    @Override // io.flutter.plugin.platform.k
    /* synthetic */ void dispose();

    @Override // io.flutter.plugin.platform.k
    /* synthetic */ View getView();

    void makeInitialLoad(HashMap<String, Object> hashMap);

    @Override // io.flutter.plugin.platform.k
    /* bridge */ /* synthetic */ default void onFlutterViewAttached(View view) {
        super.onFlutterViewAttached(view);
    }

    @Override // io.flutter.plugin.platform.k
    /* bridge */ /* synthetic */ default void onFlutterViewDetached() {
        super.onFlutterViewDetached();
    }

    @Override // io.flutter.plugin.platform.k
    /* bridge */ /* synthetic */ default void onInputConnectionLocked() {
        super.onInputConnectionLocked();
    }

    @Override // io.flutter.plugin.platform.k
    /* bridge */ /* synthetic */ default void onInputConnectionUnlocked() {
        super.onInputConnectionUnlocked();
    }
}
