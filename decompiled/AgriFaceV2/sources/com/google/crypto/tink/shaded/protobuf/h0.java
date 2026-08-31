package com.google.crypto.tink.shaded.protobuf;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class h0 implements e1 {

    /* renamed from: b  reason: collision with root package name */
    private static final o0 f6084b = new a();

    /* renamed from: a  reason: collision with root package name */
    private final o0 f6085a;

    /* loaded from: classes.dex */
    class a implements o0 {
        a() {
        }

        @Override // com.google.crypto.tink.shaded.protobuf.o0
        public n0 a(Class cls) {
            throw new IllegalStateException("This should never be called.");
        }

        @Override // com.google.crypto.tink.shaded.protobuf.o0
        public boolean b(Class cls) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class b {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f6086a;

        static {
            int[] iArr = new int[y0.values().length];
            f6086a = iArr;
            try {
                iArr[y0.PROTO3.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c implements o0 {

        /* renamed from: a  reason: collision with root package name */
        private o0[] f6087a;

        c(o0... o0VarArr) {
            this.f6087a = o0VarArr;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.o0
        public n0 a(Class cls) {
            o0[] o0VarArr;
            for (o0 o0Var : this.f6087a) {
                if (o0Var.b(cls)) {
                    return o0Var.a(cls);
                }
            }
            throw new UnsupportedOperationException("No factory is available for message type: " + cls.getName());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.o0
        public boolean b(Class cls) {
            for (o0 o0Var : this.f6087a) {
                if (o0Var.b(cls)) {
                    return true;
                }
            }
            return false;
        }
    }

    public h0() {
        this(c());
    }

    private static boolean b(n0 n0Var) {
        return b.f6086a[n0Var.b().ordinal()] != 1;
    }

    private static o0 c() {
        return new c(w.c(), d());
    }

    private static o0 d() {
        if (z0.f6299d) {
            return f6084b;
        }
        try {
            return (o0) Class.forName("com.google.crypto.tink.shaded.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return f6084b;
        }
    }

    private static d1 e(Class cls, n0 n0Var) {
        if (f(cls)) {
            return s0.R(cls, n0Var, w0.b(), f0.b(), f1.M(), b(n0Var) ? s.b() : null, m0.b());
        }
        return s0.R(cls, n0Var, w0.a(), f0.a(), f1.L(), b(n0Var) ? s.a() : null, m0.a());
    }

    private static boolean f(Class cls) {
        return z0.f6299d || x.class.isAssignableFrom(cls);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.e1
    public d1 a(Class cls) {
        j1 L;
        q a8;
        f1.I(cls);
        n0 a9 = this.f6085a.a(cls);
        if (a9.a()) {
            if (f(cls)) {
                L = f1.M();
                a8 = s.b();
            } else {
                L = f1.L();
                a8 = s.a();
            }
            return t0.m(L, a8, a9.c());
        }
        return e(cls, a9);
    }

    private h0(o0 o0Var) {
        this.f6085a = (o0) z.b(o0Var, "messageInfoFactory");
    }
}
