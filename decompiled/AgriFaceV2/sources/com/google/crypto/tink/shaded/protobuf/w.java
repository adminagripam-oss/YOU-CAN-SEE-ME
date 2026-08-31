package com.google.crypto.tink.shaded.protobuf;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class w implements o0 {

    /* renamed from: a  reason: collision with root package name */
    private static final w f6274a = new w();

    private w() {
    }

    public static w c() {
        return f6274a;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.o0
    public n0 a(Class cls) {
        if (!x.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Unsupported message type: " + cls.getName());
        }
        try {
            return (n0) x.u(cls.asSubclass(x.class)).k();
        } catch (Exception e8) {
            throw new RuntimeException("Unable to get message info for " + cls.getName(), e8);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.o0
    public boolean b(Class cls) {
        return x.class.isAssignableFrom(cls);
    }
}
