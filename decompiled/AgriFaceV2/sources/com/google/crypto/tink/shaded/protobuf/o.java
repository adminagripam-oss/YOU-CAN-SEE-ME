package com.google.crypto.tink.shaded.protobuf;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class o {

    /* renamed from: a  reason: collision with root package name */
    static final Class f6164a = c();

    public static p a() {
        p b8 = b("getEmptyRegistry");
        return b8 != null ? b8 : p.f6202c;
    }

    private static final p b(String str) {
        Class cls = f6164a;
        if (cls == null) {
            return null;
        }
        try {
            return (p) cls.getDeclaredMethod(str, new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    static Class c() {
        try {
            return Class.forName("com.google.crypto.tink.shaded.protobuf.ExtensionRegistry");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }
}
