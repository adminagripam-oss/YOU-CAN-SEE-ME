package com.google.crypto.tink.shaded.protobuf;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class s {

    /* renamed from: a  reason: collision with root package name */
    private static final q f6210a = new r();

    /* renamed from: b  reason: collision with root package name */
    private static final q f6211b = c();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static q a() {
        q qVar = f6211b;
        if (qVar != null) {
            return qVar;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static q b() {
        return f6210a;
    }

    private static q c() {
        if (z0.f6299d) {
            return null;
        }
        try {
            return (q) Class.forName("com.google.crypto.tink.shaded.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
