package io.flutter.plugin.platform;

import android.view.Surface;
/* loaded from: classes.dex */
public interface o {
    long a();

    void b(int i8, int i9);

    int getHeight();

    Surface getSurface();

    int getWidth();

    void release();

    default void scheduleFrame() {
    }
}
