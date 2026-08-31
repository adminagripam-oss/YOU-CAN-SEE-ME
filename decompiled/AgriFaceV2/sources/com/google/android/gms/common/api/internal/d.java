package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.api.internal.c;
/* loaded from: classes.dex */
public abstract class d {
    public static c a(Object obj, Looper looper, String str) {
        k3.q.h(obj, "Listener must not be null");
        k3.q.h(looper, "Looper must not be null");
        k3.q.h(str, "Listener type must not be null");
        return new c(looper, obj, str);
    }

    public static c.a b(Object obj, String str) {
        k3.q.h(obj, "Listener must not be null");
        k3.q.h(str, "Listener type must not be null");
        k3.q.f(str, "Listener type must not be empty");
        return new c.a(obj, str);
    }
}
