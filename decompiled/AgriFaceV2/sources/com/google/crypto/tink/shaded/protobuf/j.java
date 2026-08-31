package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.i0;
import java.util.List;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class j implements c1 {

    /* renamed from: a  reason: collision with root package name */
    private final i f6114a;

    /* renamed from: b  reason: collision with root package name */
    private int f6115b;

    /* renamed from: c  reason: collision with root package name */
    private int f6116c;

    /* renamed from: d  reason: collision with root package name */
    private int f6117d = 0;

    private j(i iVar) {
        i iVar2 = (i) z.b(iVar, "input");
        this.f6114a = iVar2;
        iVar2.f6094e = this;
    }

    public static j O(i iVar) {
        j jVar = iVar.f6094e;
        return jVar != null ? jVar : new j(iVar);
    }

    private void P(Object obj, d1 d1Var, p pVar) {
        int i8 = this.f6116c;
        this.f6116c = o1.c(o1.a(this.f6115b), 4);
        try {
            d1Var.d(obj, this, pVar);
            if (this.f6115b == this.f6116c) {
                return;
            }
            throw a0.h();
        } finally {
            this.f6116c = i8;
        }
    }

    private void Q(Object obj, d1 d1Var, p pVar) {
        i iVar;
        int E = this.f6114a.E();
        this.f6114a.b();
        int n8 = this.f6114a.n(E);
        this.f6114a.f6090a++;
        d1Var.d(obj, this, pVar);
        this.f6114a.a(0);
        iVar.f6090a--;
        this.f6114a.m(n8);
    }

    private Object R(d1 d1Var, p pVar) {
        Object i8 = d1Var.i();
        P(i8, d1Var, pVar);
        d1Var.e(i8);
        return i8;
    }

    private Object S(d1 d1Var, p pVar) {
        Object i8 = d1Var.i();
        Q(i8, d1Var, pVar);
        d1Var.e(i8);
        return i8;
    }

    private void U(int i8) {
        if (this.f6114a.f() != i8) {
            throw a0.m();
        }
    }

    private void V(int i8) {
        if (o1.b(this.f6115b) != i8) {
            throw a0.e();
        }
    }

    private void W(int i8) {
        if ((i8 & 3) != 0) {
            throw a0.h();
        }
    }

    private void X(int i8) {
        if ((i8 & 7) != 0) {
            throw a0.h();
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public int A() {
        V(0);
        return this.f6114a.v();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public boolean B() {
        int i8;
        if (this.f6114a.g() || (i8 = this.f6115b) == this.f6116c) {
            return false;
        }
        return this.f6114a.G(i8);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public int C() {
        V(5);
        return this.f6114a.x();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public void D(List list) {
        int D;
        if (o1.b(this.f6115b) != 2) {
            throw a0.e();
        }
        do {
            list.add(x());
            if (this.f6114a.g()) {
                return;
            }
            D = this.f6114a.D();
        } while (D == this.f6115b);
        this.f6117d = D;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public void E(Map map, i0.a aVar, p pVar) {
        V(2);
        this.f6114a.n(this.f6114a.E());
        throw null;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public void F(List list) {
        int D;
        int D2;
        if (!(list instanceof m)) {
            int b8 = o1.b(this.f6115b);
            if (b8 == 1) {
                do {
                    list.add(Double.valueOf(this.f6114a.q()));
                    if (this.f6114a.g()) {
                        return;
                    }
                    D = this.f6114a.D();
                } while (D == this.f6115b);
                this.f6117d = D;
                return;
            } else if (b8 != 2) {
                throw a0.e();
            } else {
                int E = this.f6114a.E();
                X(E);
                int f8 = this.f6114a.f() + E;
                do {
                    list.add(Double.valueOf(this.f6114a.q()));
                } while (this.f6114a.f() < f8);
                return;
            }
        }
        m mVar = (m) list;
        int b9 = o1.b(this.f6115b);
        if (b9 == 1) {
            do {
                mVar.f(this.f6114a.q());
                if (this.f6114a.g()) {
                    return;
                }
                D2 = this.f6114a.D();
            } while (D2 == this.f6115b);
            this.f6117d = D2;
        } else if (b9 != 2) {
            throw a0.e();
        } else {
            int E2 = this.f6114a.E();
            X(E2);
            int f9 = this.f6114a.f() + E2;
            do {
                mVar.f(this.f6114a.q());
            } while (this.f6114a.f() < f9);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public void G(List list) {
        int D;
        int f8;
        int D2;
        if (list instanceof g0) {
            g0 g0Var = (g0) list;
            int b8 = o1.b(this.f6115b);
            if (b8 == 0) {
                do {
                    g0Var.f(this.f6114a.w());
                    if (this.f6114a.g()) {
                        return;
                    }
                    D2 = this.f6114a.D();
                } while (D2 == this.f6115b);
                this.f6117d = D2;
                return;
            } else if (b8 != 2) {
                throw a0.e();
            } else {
                f8 = this.f6114a.f() + this.f6114a.E();
                do {
                    g0Var.f(this.f6114a.w());
                } while (this.f6114a.f() < f8);
            }
        } else {
            int b9 = o1.b(this.f6115b);
            if (b9 == 0) {
                do {
                    list.add(Long.valueOf(this.f6114a.w()));
                    if (this.f6114a.g()) {
                        return;
                    }
                    D = this.f6114a.D();
                } while (D == this.f6115b);
                this.f6117d = D;
                return;
            } else if (b9 != 2) {
                throw a0.e();
            } else {
                f8 = this.f6114a.f() + this.f6114a.E();
                do {
                    list.add(Long.valueOf(this.f6114a.w()));
                } while (this.f6114a.f() < f8);
            }
        }
        U(f8);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public void H(List list) {
        int D;
        int D2;
        if (!(list instanceof g0)) {
            int b8 = o1.b(this.f6115b);
            if (b8 == 1) {
                do {
                    list.add(Long.valueOf(this.f6114a.y()));
                    if (this.f6114a.g()) {
                        return;
                    }
                    D = this.f6114a.D();
                } while (D == this.f6115b);
                this.f6117d = D;
                return;
            } else if (b8 != 2) {
                throw a0.e();
            } else {
                int E = this.f6114a.E();
                X(E);
                int f8 = this.f6114a.f() + E;
                do {
                    list.add(Long.valueOf(this.f6114a.y()));
                } while (this.f6114a.f() < f8);
                return;
            }
        }
        g0 g0Var = (g0) list;
        int b9 = o1.b(this.f6115b);
        if (b9 == 1) {
            do {
                g0Var.f(this.f6114a.y());
                if (this.f6114a.g()) {
                    return;
                }
                D2 = this.f6114a.D();
            } while (D2 == this.f6115b);
            this.f6117d = D2;
        } else if (b9 != 2) {
            throw a0.e();
        } else {
            int E2 = this.f6114a.E();
            X(E2);
            int f9 = this.f6114a.f() + E2;
            do {
                g0Var.f(this.f6114a.y());
            } while (this.f6114a.f() < f9);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public void I(List list, d1 d1Var, p pVar) {
        int D;
        if (o1.b(this.f6115b) != 3) {
            throw a0.e();
        }
        int i8 = this.f6115b;
        do {
            list.add(R(d1Var, pVar));
            if (this.f6114a.g() || this.f6117d != 0) {
                return;
            }
            D = this.f6114a.D();
        } while (D == i8);
        this.f6117d = D;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public long J() {
        V(0);
        return this.f6114a.w();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public String K() {
        V(2);
        return this.f6114a.C();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public void L(List list) {
        int D;
        int D2;
        if (!(list instanceof g0)) {
            int b8 = o1.b(this.f6115b);
            if (b8 == 1) {
                do {
                    list.add(Long.valueOf(this.f6114a.t()));
                    if (this.f6114a.g()) {
                        return;
                    }
                    D = this.f6114a.D();
                } while (D == this.f6115b);
                this.f6117d = D;
                return;
            } else if (b8 != 2) {
                throw a0.e();
            } else {
                int E = this.f6114a.E();
                X(E);
                int f8 = this.f6114a.f() + E;
                do {
                    list.add(Long.valueOf(this.f6114a.t()));
                } while (this.f6114a.f() < f8);
                return;
            }
        }
        g0 g0Var = (g0) list;
        int b9 = o1.b(this.f6115b);
        if (b9 == 1) {
            do {
                g0Var.f(this.f6114a.t());
                if (this.f6114a.g()) {
                    return;
                }
                D2 = this.f6114a.D();
            } while (D2 == this.f6115b);
            this.f6117d = D2;
        } else if (b9 != 2) {
            throw a0.e();
        } else {
            int E2 = this.f6114a.E();
            X(E2);
            int f9 = this.f6114a.f() + E2;
            do {
                g0Var.f(this.f6114a.t());
            } while (this.f6114a.f() < f9);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public void M(List list) {
        int D;
        int f8;
        int D2;
        if (list instanceof y) {
            y yVar = (y) list;
            int b8 = o1.b(this.f6115b);
            if (b8 == 0) {
                do {
                    yVar.f(this.f6114a.v());
                    if (this.f6114a.g()) {
                        return;
                    }
                    D2 = this.f6114a.D();
                } while (D2 == this.f6115b);
                this.f6117d = D2;
                return;
            } else if (b8 != 2) {
                throw a0.e();
            } else {
                f8 = this.f6114a.f() + this.f6114a.E();
                do {
                    yVar.f(this.f6114a.v());
                } while (this.f6114a.f() < f8);
            }
        } else {
            int b9 = o1.b(this.f6115b);
            if (b9 == 0) {
                do {
                    list.add(Integer.valueOf(this.f6114a.v()));
                    if (this.f6114a.g()) {
                        return;
                    }
                    D = this.f6114a.D();
                } while (D == this.f6115b);
                this.f6117d = D;
                return;
            } else if (b9 != 2) {
                throw a0.e();
            } else {
                f8 = this.f6114a.f() + this.f6114a.E();
                do {
                    list.add(Integer.valueOf(this.f6114a.v()));
                } while (this.f6114a.f() < f8);
            }
        }
        U(f8);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public void N(List list) {
        int D;
        int f8;
        int D2;
        if (list instanceof y) {
            y yVar = (y) list;
            int b8 = o1.b(this.f6115b);
            if (b8 == 0) {
                do {
                    yVar.f(this.f6114a.r());
                    if (this.f6114a.g()) {
                        return;
                    }
                    D2 = this.f6114a.D();
                } while (D2 == this.f6115b);
                this.f6117d = D2;
                return;
            } else if (b8 != 2) {
                throw a0.e();
            } else {
                f8 = this.f6114a.f() + this.f6114a.E();
                do {
                    yVar.f(this.f6114a.r());
                } while (this.f6114a.f() < f8);
            }
        } else {
            int b9 = o1.b(this.f6115b);
            if (b9 == 0) {
                do {
                    list.add(Integer.valueOf(this.f6114a.r()));
                    if (this.f6114a.g()) {
                        return;
                    }
                    D = this.f6114a.D();
                } while (D == this.f6115b);
                this.f6117d = D;
                return;
            } else if (b9 != 2) {
                throw a0.e();
            } else {
                f8 = this.f6114a.f() + this.f6114a.E();
                do {
                    list.add(Integer.valueOf(this.f6114a.r()));
                } while (this.f6114a.f() < f8);
            }
        }
        U(f8);
    }

    public void T(List list, boolean z7) {
        int D;
        int D2;
        if (o1.b(this.f6115b) != 2) {
            throw a0.e();
        }
        if (!(list instanceof c0) || z7) {
            do {
                list.add(z7 ? K() : n());
                if (this.f6114a.g()) {
                    return;
                }
                D = this.f6114a.D();
            } while (D == this.f6115b);
            this.f6117d = D;
            return;
        }
        c0 c0Var = (c0) list;
        do {
            c0Var.g(x());
            if (this.f6114a.g()) {
                return;
            }
            D2 = this.f6114a.D();
        } while (D2 == this.f6115b);
        this.f6117d = D2;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public void a(List list) {
        int D;
        int f8;
        int D2;
        if (list instanceof y) {
            y yVar = (y) list;
            int b8 = o1.b(this.f6115b);
            if (b8 == 0) {
                do {
                    yVar.f(this.f6114a.z());
                    if (this.f6114a.g()) {
                        return;
                    }
                    D2 = this.f6114a.D();
                } while (D2 == this.f6115b);
                this.f6117d = D2;
                return;
            } else if (b8 != 2) {
                throw a0.e();
            } else {
                f8 = this.f6114a.f() + this.f6114a.E();
                do {
                    yVar.f(this.f6114a.z());
                } while (this.f6114a.f() < f8);
            }
        } else {
            int b9 = o1.b(this.f6115b);
            if (b9 == 0) {
                do {
                    list.add(Integer.valueOf(this.f6114a.z()));
                    if (this.f6114a.g()) {
                        return;
                    }
                    D = this.f6114a.D();
                } while (D == this.f6115b);
                this.f6117d = D;
                return;
            } else if (b9 != 2) {
                throw a0.e();
            } else {
                f8 = this.f6114a.f() + this.f6114a.E();
                do {
                    list.add(Integer.valueOf(this.f6114a.z()));
                } while (this.f6114a.f() < f8);
            }
        }
        U(f8);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public int b() {
        V(0);
        return this.f6114a.r();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public void c(Object obj, d1 d1Var, p pVar) {
        V(2);
        Q(obj, d1Var, pVar);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public int d() {
        return this.f6115b;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public long e() {
        V(0);
        return this.f6114a.F();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public void f(List list) {
        int D;
        int D2;
        if (!(list instanceof y)) {
            int b8 = o1.b(this.f6115b);
            if (b8 == 2) {
                int E = this.f6114a.E();
                W(E);
                int f8 = this.f6114a.f() + E;
                do {
                    list.add(Integer.valueOf(this.f6114a.s()));
                } while (this.f6114a.f() < f8);
                return;
            } else if (b8 != 5) {
                throw a0.e();
            } else {
                do {
                    list.add(Integer.valueOf(this.f6114a.s()));
                    if (this.f6114a.g()) {
                        return;
                    }
                    D = this.f6114a.D();
                } while (D == this.f6115b);
                this.f6117d = D;
                return;
            }
        }
        y yVar = (y) list;
        int b9 = o1.b(this.f6115b);
        if (b9 == 2) {
            int E2 = this.f6114a.E();
            W(E2);
            int f9 = this.f6114a.f() + E2;
            do {
                yVar.f(this.f6114a.s());
            } while (this.f6114a.f() < f9);
        } else if (b9 != 5) {
            throw a0.e();
        } else {
            do {
                yVar.f(this.f6114a.s());
                if (this.f6114a.g()) {
                    return;
                }
                D2 = this.f6114a.D();
            } while (D2 == this.f6115b);
            this.f6117d = D2;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public long g() {
        V(1);
        return this.f6114a.t();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public void h(List list) {
        int D;
        int D2;
        if (!(list instanceof y)) {
            int b8 = o1.b(this.f6115b);
            if (b8 == 2) {
                int E = this.f6114a.E();
                W(E);
                int f8 = this.f6114a.f() + E;
                do {
                    list.add(Integer.valueOf(this.f6114a.x()));
                } while (this.f6114a.f() < f8);
                return;
            } else if (b8 != 5) {
                throw a0.e();
            } else {
                do {
                    list.add(Integer.valueOf(this.f6114a.x()));
                    if (this.f6114a.g()) {
                        return;
                    }
                    D = this.f6114a.D();
                } while (D == this.f6115b);
                this.f6117d = D;
                return;
            }
        }
        y yVar = (y) list;
        int b9 = o1.b(this.f6115b);
        if (b9 == 2) {
            int E2 = this.f6114a.E();
            W(E2);
            int f9 = this.f6114a.f() + E2;
            do {
                yVar.f(this.f6114a.x());
            } while (this.f6114a.f() < f9);
        } else if (b9 != 5) {
            throw a0.e();
        } else {
            do {
                yVar.f(this.f6114a.x());
                if (this.f6114a.g()) {
                    return;
                }
                D2 = this.f6114a.D();
            } while (D2 == this.f6115b);
            this.f6117d = D2;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public int i() {
        V(0);
        return this.f6114a.z();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public void j(List list) {
        int D;
        int f8;
        int D2;
        if (list instanceof g0) {
            g0 g0Var = (g0) list;
            int b8 = o1.b(this.f6115b);
            if (b8 == 0) {
                do {
                    g0Var.f(this.f6114a.A());
                    if (this.f6114a.g()) {
                        return;
                    }
                    D2 = this.f6114a.D();
                } while (D2 == this.f6115b);
                this.f6117d = D2;
                return;
            } else if (b8 != 2) {
                throw a0.e();
            } else {
                f8 = this.f6114a.f() + this.f6114a.E();
                do {
                    g0Var.f(this.f6114a.A());
                } while (this.f6114a.f() < f8);
            }
        } else {
            int b9 = o1.b(this.f6115b);
            if (b9 == 0) {
                do {
                    list.add(Long.valueOf(this.f6114a.A()));
                    if (this.f6114a.g()) {
                        return;
                    }
                    D = this.f6114a.D();
                } while (D == this.f6115b);
                this.f6117d = D;
                return;
            } else if (b9 != 2) {
                throw a0.e();
            } else {
                f8 = this.f6114a.f() + this.f6114a.E();
                do {
                    list.add(Long.valueOf(this.f6114a.A()));
                } while (this.f6114a.f() < f8);
            }
        }
        U(f8);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public long k() {
        V(0);
        return this.f6114a.A();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public void l(List list) {
        int D;
        int f8;
        int D2;
        if (list instanceof y) {
            y yVar = (y) list;
            int b8 = o1.b(this.f6115b);
            if (b8 == 0) {
                do {
                    yVar.f(this.f6114a.E());
                    if (this.f6114a.g()) {
                        return;
                    }
                    D2 = this.f6114a.D();
                } while (D2 == this.f6115b);
                this.f6117d = D2;
                return;
            } else if (b8 != 2) {
                throw a0.e();
            } else {
                f8 = this.f6114a.f() + this.f6114a.E();
                do {
                    yVar.f(this.f6114a.E());
                } while (this.f6114a.f() < f8);
            }
        } else {
            int b9 = o1.b(this.f6115b);
            if (b9 == 0) {
                do {
                    list.add(Integer.valueOf(this.f6114a.E()));
                    if (this.f6114a.g()) {
                        return;
                    }
                    D = this.f6114a.D();
                } while (D == this.f6115b);
                this.f6117d = D;
                return;
            } else if (b9 != 2) {
                throw a0.e();
            } else {
                f8 = this.f6114a.f() + this.f6114a.E();
                do {
                    list.add(Integer.valueOf(this.f6114a.E()));
                } while (this.f6114a.f() < f8);
            }
        }
        U(f8);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public void m(List list) {
        int D;
        int f8;
        int D2;
        if (list instanceof f) {
            f fVar = (f) list;
            int b8 = o1.b(this.f6115b);
            if (b8 == 0) {
                do {
                    fVar.f(this.f6114a.o());
                    if (this.f6114a.g()) {
                        return;
                    }
                    D2 = this.f6114a.D();
                } while (D2 == this.f6115b);
                this.f6117d = D2;
                return;
            } else if (b8 != 2) {
                throw a0.e();
            } else {
                f8 = this.f6114a.f() + this.f6114a.E();
                do {
                    fVar.f(this.f6114a.o());
                } while (this.f6114a.f() < f8);
            }
        } else {
            int b9 = o1.b(this.f6115b);
            if (b9 == 0) {
                do {
                    list.add(Boolean.valueOf(this.f6114a.o()));
                    if (this.f6114a.g()) {
                        return;
                    }
                    D = this.f6114a.D();
                } while (D == this.f6115b);
                this.f6117d = D;
                return;
            } else if (b9 != 2) {
                throw a0.e();
            } else {
                f8 = this.f6114a.f() + this.f6114a.E();
                do {
                    list.add(Boolean.valueOf(this.f6114a.o()));
                } while (this.f6114a.f() < f8);
            }
        }
        U(f8);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public String n() {
        V(2);
        return this.f6114a.B();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public void o(List list, d1 d1Var, p pVar) {
        int D;
        if (o1.b(this.f6115b) != 2) {
            throw a0.e();
        }
        int i8 = this.f6115b;
        do {
            list.add(S(d1Var, pVar));
            if (this.f6114a.g() || this.f6117d != 0) {
                return;
            }
            D = this.f6114a.D();
        } while (D == i8);
        this.f6117d = D;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public int p() {
        V(5);
        return this.f6114a.s();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public boolean q() {
        V(0);
        return this.f6114a.o();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public int r() {
        int i8 = this.f6117d;
        if (i8 != 0) {
            this.f6115b = i8;
            this.f6117d = 0;
        } else {
            this.f6115b = this.f6114a.D();
        }
        int i9 = this.f6115b;
        if (i9 == 0 || i9 == this.f6116c) {
            return Integer.MAX_VALUE;
        }
        return o1.a(i9);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public double readDouble() {
        V(1);
        return this.f6114a.q();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public float readFloat() {
        V(5);
        return this.f6114a.u();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public void s(Object obj, d1 d1Var, p pVar) {
        V(3);
        P(obj, d1Var, pVar);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public void t(List list) {
        T(list, false);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public long u() {
        V(1);
        return this.f6114a.y();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public void v(List list) {
        int D;
        int f8;
        int D2;
        if (list instanceof g0) {
            g0 g0Var = (g0) list;
            int b8 = o1.b(this.f6115b);
            if (b8 == 0) {
                do {
                    g0Var.f(this.f6114a.F());
                    if (this.f6114a.g()) {
                        return;
                    }
                    D2 = this.f6114a.D();
                } while (D2 == this.f6115b);
                this.f6117d = D2;
                return;
            } else if (b8 != 2) {
                throw a0.e();
            } else {
                f8 = this.f6114a.f() + this.f6114a.E();
                do {
                    g0Var.f(this.f6114a.F());
                } while (this.f6114a.f() < f8);
            }
        } else {
            int b9 = o1.b(this.f6115b);
            if (b9 == 0) {
                do {
                    list.add(Long.valueOf(this.f6114a.F()));
                    if (this.f6114a.g()) {
                        return;
                    }
                    D = this.f6114a.D();
                } while (D == this.f6115b);
                this.f6117d = D;
                return;
            } else if (b9 != 2) {
                throw a0.e();
            } else {
                f8 = this.f6114a.f() + this.f6114a.E();
                do {
                    list.add(Long.valueOf(this.f6114a.F()));
                } while (this.f6114a.f() < f8);
            }
        }
        U(f8);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public void w(List list) {
        T(list, true);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public h x() {
        V(2);
        return this.f6114a.p();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public void y(List list) {
        int D;
        int D2;
        if (!(list instanceof v)) {
            int b8 = o1.b(this.f6115b);
            if (b8 == 2) {
                int E = this.f6114a.E();
                W(E);
                int f8 = this.f6114a.f() + E;
                do {
                    list.add(Float.valueOf(this.f6114a.u()));
                } while (this.f6114a.f() < f8);
                return;
            } else if (b8 != 5) {
                throw a0.e();
            } else {
                do {
                    list.add(Float.valueOf(this.f6114a.u()));
                    if (this.f6114a.g()) {
                        return;
                    }
                    D = this.f6114a.D();
                } while (D == this.f6115b);
                this.f6117d = D;
                return;
            }
        }
        v vVar = (v) list;
        int b9 = o1.b(this.f6115b);
        if (b9 == 2) {
            int E2 = this.f6114a.E();
            W(E2);
            int f9 = this.f6114a.f() + E2;
            do {
                vVar.f(this.f6114a.u());
            } while (this.f6114a.f() < f9);
        } else if (b9 != 5) {
            throw a0.e();
        } else {
            do {
                vVar.f(this.f6114a.u());
                if (this.f6114a.g()) {
                    return;
                }
                D2 = this.f6114a.D();
            } while (D2 == this.f6115b);
            this.f6117d = D2;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.c1
    public int z() {
        V(0);
        return this.f6114a.E();
    }
}
