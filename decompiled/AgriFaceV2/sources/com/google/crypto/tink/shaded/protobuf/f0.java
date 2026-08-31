package com.google.crypto.tink.shaded.protobuf;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class f0 {

    /* renamed from: a  reason: collision with root package name */
    private static final d0 f6067a = c();

    /* renamed from: b  reason: collision with root package name */
    private static final d0 f6068b = new e0();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static d0 a() {
        return f6067a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static d0 b() {
        return f6068b;
    }

    private static d0 c() {
        if (z0.f6299d) {
            return null;
        }
        try {
            return (d0) Class.forName("com.google.crypto.tink.shaded.protobuf.ListFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
