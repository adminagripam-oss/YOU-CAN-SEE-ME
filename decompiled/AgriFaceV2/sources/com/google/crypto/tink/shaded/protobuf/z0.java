package com.google.crypto.tink.shaded.protobuf;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class z0 {

    /* renamed from: c  reason: collision with root package name */
    private static final z0 f6298c = new z0();

    /* renamed from: d  reason: collision with root package name */
    static boolean f6299d = false;

    /* renamed from: b  reason: collision with root package name */
    private final ConcurrentMap f6301b = new ConcurrentHashMap();

    /* renamed from: a  reason: collision with root package name */
    private final e1 f6300a = new h0();

    private z0() {
    }

    public static z0 a() {
        return f6298c;
    }

    public d1 b(Class cls, d1 d1Var) {
        z.b(cls, "messageType");
        z.b(d1Var, "schema");
        return (d1) this.f6301b.putIfAbsent(cls, d1Var);
    }

    public d1 c(Class cls) {
        z.b(cls, "messageType");
        d1 d1Var = (d1) this.f6301b.get(cls);
        if (d1Var == null) {
            d1 a8 = this.f6300a.a(cls);
            d1 b8 = b(cls, a8);
            return b8 != null ? b8 : a8;
        }
        return d1Var;
    }

    public d1 d(Object obj) {
        return c(obj.getClass());
    }
}
