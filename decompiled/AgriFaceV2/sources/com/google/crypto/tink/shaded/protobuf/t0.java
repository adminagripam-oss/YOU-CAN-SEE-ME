package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.e;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes.dex */
final class t0 implements d1 {

    /* renamed from: a  reason: collision with root package name */
    private final p0 f6230a;

    /* renamed from: b  reason: collision with root package name */
    private final j1 f6231b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f6232c;

    /* renamed from: d  reason: collision with root package name */
    private final q f6233d;

    private t0(j1 j1Var, q qVar, p0 p0Var) {
        this.f6231b = j1Var;
        this.f6232c = qVar.e(p0Var);
        this.f6233d = qVar;
        this.f6230a = p0Var;
    }

    private int k(j1 j1Var, Object obj) {
        return j1Var.i(j1Var.g(obj));
    }

    private void l(j1 j1Var, q qVar, Object obj, c1 c1Var, p pVar) {
        Object f8 = j1Var.f(obj);
        t d8 = qVar.d(obj);
        do {
            try {
                if (c1Var.r() == Integer.MAX_VALUE) {
                    return;
                }
            } finally {
                j1Var.o(obj, f8);
            }
        } while (n(c1Var, pVar, qVar, d8, j1Var, f8));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static t0 m(j1 j1Var, q qVar, p0 p0Var) {
        return new t0(j1Var, qVar, p0Var);
    }

    private boolean n(c1 c1Var, p pVar, q qVar, t tVar, j1 j1Var, Object obj) {
        int d8 = c1Var.d();
        int i8 = 0;
        if (d8 != o1.f6165a) {
            if (o1.b(d8) == 2) {
                Object b8 = qVar.b(pVar, this.f6230a, o1.a(d8));
                if (b8 != null) {
                    qVar.h(c1Var, b8, pVar, tVar);
                    return true;
                }
                return j1Var.m(obj, c1Var, 0);
            }
            return c1Var.B();
        }
        Object obj2 = null;
        h hVar = null;
        while (c1Var.r() != Integer.MAX_VALUE) {
            int d9 = c1Var.d();
            if (d9 != o1.f6167c) {
                if (d9 != o1.f6168d) {
                    if (d9 == o1.f6166b || !c1Var.B()) {
                        break;
                    }
                } else if (obj2 != null) {
                    qVar.h(c1Var, obj2, pVar, tVar);
                } else {
                    hVar = c1Var.x();
                }
            } else {
                i8 = c1Var.z();
                obj2 = qVar.b(pVar, this.f6230a, i8);
            }
        }
        if (c1Var.d() == o1.f6166b) {
            if (hVar != null) {
                if (obj2 != null) {
                    qVar.i(hVar, obj2, pVar, tVar);
                } else {
                    j1Var.d(obj, i8, hVar);
                }
            }
            return true;
        }
        throw a0.b();
    }

    private void o(j1 j1Var, Object obj, p1 p1Var) {
        j1Var.s(j1Var.g(obj), p1Var);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.d1
    public void a(Object obj, Object obj2) {
        f1.H(this.f6231b, obj, obj2);
        if (this.f6232c) {
            f1.F(this.f6233d, obj, obj2);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.d1
    public void b(Object obj, byte[] bArr, int i8, int i9, e.a aVar) {
        x xVar = (x) obj;
        if (xVar.unknownFields == k1.c()) {
            xVar.unknownFields = k1.k();
        }
        androidx.appcompat.app.w.a(obj);
        throw null;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.d1
    public void c(Object obj, p1 p1Var) {
        Iterator f8 = this.f6233d.c(obj).f();
        if (f8.hasNext()) {
            androidx.appcompat.app.w.a(((Map.Entry) f8.next()).getKey());
            throw null;
        } else {
            o(this.f6231b, obj, p1Var);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.d1
    public void d(Object obj, c1 c1Var, p pVar) {
        l(this.f6231b, this.f6233d, obj, c1Var, pVar);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.d1
    public void e(Object obj) {
        this.f6231b.j(obj);
        this.f6233d.f(obj);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.d1
    public final boolean f(Object obj) {
        return this.f6233d.c(obj).e();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.d1
    public boolean g(Object obj, Object obj2) {
        if (this.f6231b.g(obj).equals(this.f6231b.g(obj2))) {
            if (this.f6232c) {
                return this.f6233d.c(obj).equals(this.f6233d.c(obj2));
            }
            return true;
        }
        return false;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.d1
    public int h(Object obj) {
        int k8 = k(this.f6231b, obj) + 0;
        return this.f6232c ? k8 + this.f6233d.c(obj).b() : k8;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.d1
    public Object i() {
        p0 p0Var = this.f6230a;
        return p0Var instanceof x ? ((x) p0Var).J() : p0Var.e().f();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.d1
    public int j(Object obj) {
        int hashCode = this.f6231b.g(obj).hashCode();
        return this.f6232c ? (hashCode * 53) + this.f6233d.c(obj).hashCode() : hashCode;
    }
}
