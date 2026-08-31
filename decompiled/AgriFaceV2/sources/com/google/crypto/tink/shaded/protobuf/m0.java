package com.google.crypto.tink.shaded.protobuf;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class m0 {

    /* renamed from: a  reason: collision with root package name */
    private static final k0 f6137a = c();

    /* renamed from: b  reason: collision with root package name */
    private static final k0 f6138b = new l0();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static k0 a() {
        return f6137a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static k0 b() {
        return f6138b;
    }

    private static k0 c() {
        if (z0.f6299d) {
            return null;
        }
        try {
            return (k0) Class.forName("com.google.crypto.tink.shaded.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
