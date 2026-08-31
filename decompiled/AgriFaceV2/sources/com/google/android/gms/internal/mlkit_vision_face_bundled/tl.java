package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* loaded from: classes.dex */
final class tl {

    /* renamed from: c  reason: collision with root package name */
    private static final tl f5620c = new tl();

    /* renamed from: d  reason: collision with root package name */
    public static final /* synthetic */ int f5621d = 0;

    /* renamed from: b  reason: collision with root package name */
    private final ConcurrentMap f5623b = new ConcurrentHashMap();

    /* renamed from: a  reason: collision with root package name */
    private final xl f5622a = new el();

    private tl() {
    }

    public static tl a() {
        return f5620c;
    }

    public final wl b(Class cls) {
        uk.c(cls, "messageType");
        wl wlVar = (wl) this.f5623b.get(cls);
        if (wlVar == null) {
            wlVar = this.f5622a.a(cls);
            uk.c(cls, "messageType");
            wl wlVar2 = (wl) this.f5623b.putIfAbsent(cls, wlVar);
            if (wlVar2 != null) {
                return wlVar2;
            }
        }
        return wlVar;
    }
}
