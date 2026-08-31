package io.flutter.plugin.platform;

import android.view.View;
/* loaded from: classes.dex */
public interface k {
    void dispose();

    View getView();

    default void onFlutterViewAttached(View view) {
    }

    default void onFlutterViewDetached() {
    }

    default void onInputConnectionLocked() {
    }

    default void onInputConnectionUnlocked() {
    }
}
