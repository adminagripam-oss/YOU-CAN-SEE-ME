package com.google.crypto.tink.shaded.protobuf;
/* loaded from: classes.dex */
abstract class d {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f6058a;

    /* renamed from: b  reason: collision with root package name */
    private static final Class f6059b = a("libcore.io.Memory");

    /* renamed from: c  reason: collision with root package name */
    private static final boolean f6060c;

    static {
        f6060c = (f6058a || a("org.robolectric.Robolectric") == null) ? false : true;
    }

    private static Class a(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Class b() {
        return f6059b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean c() {
        return f6058a || !(f6059b == null || f6060c);
    }
}
