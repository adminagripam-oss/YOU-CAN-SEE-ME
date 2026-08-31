package com.google.crypto.tink.shaded.protobuf;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class w0 {

    /* renamed from: a  reason: collision with root package name */
    private static final u0 f6275a = c();

    /* renamed from: b  reason: collision with root package name */
    private static final u0 f6276b = new v0();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static u0 a() {
        return f6275a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static u0 b() {
        return f6276b;
    }

    private static u0 c() {
        if (z0.f6299d) {
            return null;
        }
        try {
            return (u0) Class.forName("com.google.crypto.tink.shaded.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
