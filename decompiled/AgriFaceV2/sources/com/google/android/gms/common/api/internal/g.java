package com.google.android.gms.common.api.internal;

import i3.a;
/* loaded from: classes.dex */
public abstract class g {

    /* renamed from: a  reason: collision with root package name */
    private final h3.c[] f5024a;

    /* renamed from: b  reason: collision with root package name */
    private final boolean f5025b;

    /* renamed from: c  reason: collision with root package name */
    private final int f5026c;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private j3.i f5027a;

        /* renamed from: c  reason: collision with root package name */
        private h3.c[] f5029c;

        /* renamed from: b  reason: collision with root package name */
        private boolean f5028b = true;

        /* renamed from: d  reason: collision with root package name */
        private int f5030d = 0;

        /* synthetic */ a(j3.b0 b0Var) {
        }

        public g a() {
            k3.q.b(this.f5027a != null, "execute parameter required");
            return new z(this, this.f5029c, this.f5028b, this.f5030d);
        }

        public a b(j3.i iVar) {
            this.f5027a = iVar;
            return this;
        }

        public a c(boolean z7) {
            this.f5028b = z7;
            return this;
        }

        public a d(h3.c... cVarArr) {
            this.f5029c = cVarArr;
            return this;
        }

        public a e(int i8) {
            this.f5030d = i8;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public g(h3.c[] cVarArr, boolean z7, int i8) {
        this.f5024a = cVarArr;
        boolean z8 = false;
        if (cVarArr != null && z7) {
            z8 = true;
        }
        this.f5025b = z8;
        this.f5026c = i8;
    }

    public static a a() {
        return new a(null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void b(a.b bVar, f4.k kVar);

    public boolean c() {
        return this.f5025b;
    }

    public final int d() {
        return this.f5026c;
    }

    public final h3.c[] e() {
        return this.f5024a;
    }
}
