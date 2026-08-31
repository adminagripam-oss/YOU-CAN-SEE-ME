package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.i0;
import com.google.crypto.tink.shaded.protobuf.o1;
import com.google.crypto.tink.shaded.protobuf.p1;
import java.util.List;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class l implements p1 {

    /* renamed from: a  reason: collision with root package name */
    private final k f6135a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f6136a;

        static {
            int[] iArr = new int[o1.b.values().length];
            f6136a = iArr;
            try {
                iArr[o1.b.f6176n.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f6136a[o1.b.f6175m.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f6136a[o1.b.f6173k.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f6136a[o1.b.f6183u.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f6136a[o1.b.f6185w.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f6136a[o1.b.f6181s.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f6136a[o1.b.f6174l.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f6136a[o1.b.f6171i.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f6136a[o1.b.f6184v.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f6136a[o1.b.f6186x.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f6136a[o1.b.f6172j.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f6136a[o1.b.f6177o.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    private l(k kVar) {
        k kVar2 = (k) z.b(kVar, "output");
        this.f6135a = kVar2;
        kVar2.f6123a = this;
    }

    public static l P(k kVar) {
        l lVar = kVar.f6123a;
        return lVar != null ? lVar : new l(kVar);
    }

    private void Q(int i8, f fVar, boolean z7) {
        int i9 = 0;
        if (!z7) {
            while (i9 < fVar.size()) {
                this.f6135a.W(i8, fVar.j(i9));
                i9++;
            }
            return;
        }
        this.f6135a.A0(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < fVar.size(); i11++) {
            i10 += k.e(fVar.j(i11));
        }
        this.f6135a.C0(i10);
        while (i9 < fVar.size()) {
            this.f6135a.X(fVar.j(i9));
            i9++;
        }
    }

    private void R(int i8, List list, boolean z7) {
        int i9 = 0;
        if (!z7) {
            while (i9 < list.size()) {
                this.f6135a.W(i8, ((Boolean) list.get(i9)).booleanValue());
                i9++;
            }
            return;
        }
        this.f6135a.A0(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            i10 += k.e(((Boolean) list.get(i11)).booleanValue());
        }
        this.f6135a.C0(i10);
        while (i9 < list.size()) {
            this.f6135a.X(((Boolean) list.get(i9)).booleanValue());
            i9++;
        }
    }

    private void S(int i8, i0.a aVar, Map map) {
        int[] iArr = a.f6136a;
        throw null;
    }

    private void T(int i8, m mVar, boolean z7) {
        int i9 = 0;
        if (!z7) {
            while (i9 < mVar.size()) {
                this.f6135a.Z(i8, mVar.k(i9));
                i9++;
            }
            return;
        }
        this.f6135a.A0(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < mVar.size(); i11++) {
            i10 += k.i(mVar.k(i11));
        }
        this.f6135a.C0(i10);
        while (i9 < mVar.size()) {
            this.f6135a.a0(mVar.k(i9));
            i9++;
        }
    }

    private void U(int i8, List list, boolean z7) {
        int i9 = 0;
        if (!z7) {
            while (i9 < list.size()) {
                this.f6135a.Z(i8, ((Double) list.get(i9)).doubleValue());
                i9++;
            }
            return;
        }
        this.f6135a.A0(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            i10 += k.i(((Double) list.get(i11)).doubleValue());
        }
        this.f6135a.C0(i10);
        while (i9 < list.size()) {
            this.f6135a.a0(((Double) list.get(i9)).doubleValue());
            i9++;
        }
    }

    private void V(int i8, y yVar, boolean z7) {
        int i9 = 0;
        if (!z7) {
            while (i9 < yVar.size()) {
                this.f6135a.b0(i8, yVar.k(i9));
                i9++;
            }
            return;
        }
        this.f6135a.A0(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < yVar.size(); i11++) {
            i10 += k.k(yVar.k(i11));
        }
        this.f6135a.C0(i10);
        while (i9 < yVar.size()) {
            this.f6135a.c0(yVar.k(i9));
            i9++;
        }
    }

    private void W(int i8, List list, boolean z7) {
        int i9 = 0;
        if (!z7) {
            while (i9 < list.size()) {
                this.f6135a.b0(i8, ((Integer) list.get(i9)).intValue());
                i9++;
            }
            return;
        }
        this.f6135a.A0(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            i10 += k.k(((Integer) list.get(i11)).intValue());
        }
        this.f6135a.C0(i10);
        while (i9 < list.size()) {
            this.f6135a.c0(((Integer) list.get(i9)).intValue());
            i9++;
        }
    }

    private void X(int i8, y yVar, boolean z7) {
        int i9 = 0;
        if (!z7) {
            while (i9 < yVar.size()) {
                this.f6135a.d0(i8, yVar.k(i9));
                i9++;
            }
            return;
        }
        this.f6135a.A0(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < yVar.size(); i11++) {
            i10 += k.m(yVar.k(i11));
        }
        this.f6135a.C0(i10);
        while (i9 < yVar.size()) {
            this.f6135a.e0(yVar.k(i9));
            i9++;
        }
    }

    private void Y(int i8, List list, boolean z7) {
        int i9 = 0;
        if (!z7) {
            while (i9 < list.size()) {
                this.f6135a.d0(i8, ((Integer) list.get(i9)).intValue());
                i9++;
            }
            return;
        }
        this.f6135a.A0(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            i10 += k.m(((Integer) list.get(i11)).intValue());
        }
        this.f6135a.C0(i10);
        while (i9 < list.size()) {
            this.f6135a.e0(((Integer) list.get(i9)).intValue());
            i9++;
        }
    }

    private void Z(int i8, g0 g0Var, boolean z7) {
        int i9 = 0;
        if (!z7) {
            while (i9 < g0Var.size()) {
                this.f6135a.f0(i8, g0Var.k(i9));
                i9++;
            }
            return;
        }
        this.f6135a.A0(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < g0Var.size(); i11++) {
            i10 += k.o(g0Var.k(i11));
        }
        this.f6135a.C0(i10);
        while (i9 < g0Var.size()) {
            this.f6135a.g0(g0Var.k(i9));
            i9++;
        }
    }

    private void a0(int i8, List list, boolean z7) {
        int i9 = 0;
        if (!z7) {
            while (i9 < list.size()) {
                this.f6135a.f0(i8, ((Long) list.get(i9)).longValue());
                i9++;
            }
            return;
        }
        this.f6135a.A0(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            i10 += k.o(((Long) list.get(i11)).longValue());
        }
        this.f6135a.C0(i10);
        while (i9 < list.size()) {
            this.f6135a.g0(((Long) list.get(i9)).longValue());
            i9++;
        }
    }

    private void b0(int i8, v vVar, boolean z7) {
        int i9 = 0;
        if (!z7) {
            while (i9 < vVar.size()) {
                this.f6135a.h0(i8, vVar.k(i9));
                i9++;
            }
            return;
        }
        this.f6135a.A0(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < vVar.size(); i11++) {
            i10 += k.q(vVar.k(i11));
        }
        this.f6135a.C0(i10);
        while (i9 < vVar.size()) {
            this.f6135a.i0(vVar.k(i9));
            i9++;
        }
    }

    private void c0(int i8, List list, boolean z7) {
        int i9 = 0;
        if (!z7) {
            while (i9 < list.size()) {
                this.f6135a.h0(i8, ((Float) list.get(i9)).floatValue());
                i9++;
            }
            return;
        }
        this.f6135a.A0(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            i10 += k.q(((Float) list.get(i11)).floatValue());
        }
        this.f6135a.C0(i10);
        while (i9 < list.size()) {
            this.f6135a.i0(((Float) list.get(i9)).floatValue());
            i9++;
        }
    }

    private void d0(int i8, y yVar, boolean z7) {
        int i9 = 0;
        if (!z7) {
            while (i9 < yVar.size()) {
                this.f6135a.l0(i8, yVar.k(i9));
                i9++;
            }
            return;
        }
        this.f6135a.A0(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < yVar.size(); i11++) {
            i10 += k.u(yVar.k(i11));
        }
        this.f6135a.C0(i10);
        while (i9 < yVar.size()) {
            this.f6135a.m0(yVar.k(i9));
            i9++;
        }
    }

    private void e0(int i8, List list, boolean z7) {
        int i9 = 0;
        if (!z7) {
            while (i9 < list.size()) {
                this.f6135a.l0(i8, ((Integer) list.get(i9)).intValue());
                i9++;
            }
            return;
        }
        this.f6135a.A0(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            i10 += k.u(((Integer) list.get(i11)).intValue());
        }
        this.f6135a.C0(i10);
        while (i9 < list.size()) {
            this.f6135a.m0(((Integer) list.get(i9)).intValue());
            i9++;
        }
    }

    private void f0(int i8, g0 g0Var, boolean z7) {
        int i9 = 0;
        if (!z7) {
            while (i9 < g0Var.size()) {
                this.f6135a.n0(i8, g0Var.k(i9));
                i9++;
            }
            return;
        }
        this.f6135a.A0(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < g0Var.size(); i11++) {
            i10 += k.w(g0Var.k(i11));
        }
        this.f6135a.C0(i10);
        while (i9 < g0Var.size()) {
            this.f6135a.o0(g0Var.k(i9));
            i9++;
        }
    }

    private void g0(int i8, List list, boolean z7) {
        int i9 = 0;
        if (!z7) {
            while (i9 < list.size()) {
                this.f6135a.n0(i8, ((Long) list.get(i9)).longValue());
                i9++;
            }
            return;
        }
        this.f6135a.A0(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            i10 += k.w(((Long) list.get(i11)).longValue());
        }
        this.f6135a.C0(i10);
        while (i9 < list.size()) {
            this.f6135a.o0(((Long) list.get(i9)).longValue());
            i9++;
        }
    }

    private void h0(int i8, Object obj) {
        if (obj instanceof String) {
            this.f6135a.z0(i8, (String) obj);
        } else {
            this.f6135a.Y(i8, (h) obj);
        }
    }

    private void i0(int i8, y yVar, boolean z7) {
        int i9 = 0;
        if (!z7) {
            while (i9 < yVar.size()) {
                this.f6135a.r0(i8, yVar.k(i9));
                i9++;
            }
            return;
        }
        this.f6135a.A0(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < yVar.size(); i11++) {
            i10 += k.A(yVar.k(i11));
        }
        this.f6135a.C0(i10);
        while (i9 < yVar.size()) {
            this.f6135a.s0(yVar.k(i9));
            i9++;
        }
    }

    private void j0(int i8, List list, boolean z7) {
        int i9 = 0;
        if (!z7) {
            while (i9 < list.size()) {
                this.f6135a.r0(i8, ((Integer) list.get(i9)).intValue());
                i9++;
            }
            return;
        }
        this.f6135a.A0(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            i10 += k.A(((Integer) list.get(i11)).intValue());
        }
        this.f6135a.C0(i10);
        while (i9 < list.size()) {
            this.f6135a.s0(((Integer) list.get(i9)).intValue());
            i9++;
        }
    }

    private void k0(int i8, g0 g0Var, boolean z7) {
        int i9 = 0;
        if (!z7) {
            while (i9 < g0Var.size()) {
                this.f6135a.t0(i8, g0Var.k(i9));
                i9++;
            }
            return;
        }
        this.f6135a.A0(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < g0Var.size(); i11++) {
            i10 += k.C(g0Var.k(i11));
        }
        this.f6135a.C0(i10);
        while (i9 < g0Var.size()) {
            this.f6135a.u0(g0Var.k(i9));
            i9++;
        }
    }

    private void l0(int i8, List list, boolean z7) {
        int i9 = 0;
        if (!z7) {
            while (i9 < list.size()) {
                this.f6135a.t0(i8, ((Long) list.get(i9)).longValue());
                i9++;
            }
            return;
        }
        this.f6135a.A0(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            i10 += k.C(((Long) list.get(i11)).longValue());
        }
        this.f6135a.C0(i10);
        while (i9 < list.size()) {
            this.f6135a.u0(((Long) list.get(i9)).longValue());
            i9++;
        }
    }

    private void m0(int i8, y yVar, boolean z7) {
        int i9 = 0;
        if (!z7) {
            while (i9 < yVar.size()) {
                this.f6135a.v0(i8, yVar.k(i9));
                i9++;
            }
            return;
        }
        this.f6135a.A0(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < yVar.size(); i11++) {
            i10 += k.E(yVar.k(i11));
        }
        this.f6135a.C0(i10);
        while (i9 < yVar.size()) {
            this.f6135a.w0(yVar.k(i9));
            i9++;
        }
    }

    private void o0(int i8, g0 g0Var, boolean z7) {
        int i9 = 0;
        if (!z7) {
            while (i9 < g0Var.size()) {
                this.f6135a.x0(i8, g0Var.k(i9));
                i9++;
            }
            return;
        }
        this.f6135a.A0(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < g0Var.size(); i11++) {
            i10 += k.G(g0Var.k(i11));
        }
        this.f6135a.C0(i10);
        while (i9 < g0Var.size()) {
            this.f6135a.y0(g0Var.k(i9));
            i9++;
        }
    }

    private void p0(int i8, List list, boolean z7) {
        int i9 = 0;
        if (!z7) {
            while (i9 < list.size()) {
                this.f6135a.x0(i8, ((Long) list.get(i9)).longValue());
                i9++;
            }
            return;
        }
        this.f6135a.A0(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            i10 += k.G(((Long) list.get(i11)).longValue());
        }
        this.f6135a.C0(i10);
        while (i9 < list.size()) {
            this.f6135a.y0(((Long) list.get(i9)).longValue());
            i9++;
        }
    }

    private void q0(int i8, y yVar, boolean z7) {
        int i9 = 0;
        if (!z7) {
            while (i9 < yVar.size()) {
                this.f6135a.B0(i8, yVar.k(i9));
                i9++;
            }
            return;
        }
        this.f6135a.A0(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < yVar.size(); i11++) {
            i10 += k.L(yVar.k(i11));
        }
        this.f6135a.C0(i10);
        while (i9 < yVar.size()) {
            this.f6135a.C0(yVar.k(i9));
            i9++;
        }
    }

    private void s0(int i8, g0 g0Var, boolean z7) {
        int i9 = 0;
        if (!z7) {
            while (i9 < g0Var.size()) {
                this.f6135a.D0(i8, g0Var.k(i9));
                i9++;
            }
            return;
        }
        this.f6135a.A0(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < g0Var.size(); i11++) {
            i10 += k.N(g0Var.k(i11));
        }
        this.f6135a.C0(i10);
        while (i9 < g0Var.size()) {
            this.f6135a.E0(g0Var.k(i9));
            i9++;
        }
    }

    private void t0(int i8, List list, boolean z7) {
        int i9 = 0;
        if (!z7) {
            while (i9 < list.size()) {
                this.f6135a.D0(i8, ((Long) list.get(i9)).longValue());
                i9++;
            }
            return;
        }
        this.f6135a.A0(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            i10 += k.N(((Long) list.get(i11)).longValue());
        }
        this.f6135a.C0(i10);
        while (i9 < list.size()) {
            this.f6135a.E0(((Long) list.get(i9)).longValue());
            i9++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public p1.a A() {
        return p1.a.ASCENDING;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void B(int i8, long j8) {
        this.f6135a.x0(i8, j8);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void C(int i8, float f8) {
        this.f6135a.h0(i8, f8);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void D(int i8, List list) {
        int i9 = 0;
        if (!(list instanceof c0)) {
            while (i9 < list.size()) {
                this.f6135a.z0(i8, (String) list.get(i9));
                i9++;
            }
            return;
        }
        c0 c0Var = (c0) list;
        while (i9 < list.size()) {
            h0(i8, c0Var.h(i9));
            i9++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void E(int i8) {
        this.f6135a.A0(i8, 4);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void F(int i8, String str) {
        this.f6135a.z0(i8, str);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void G(int i8, List list, boolean z7) {
        if (list instanceof y) {
            m0(i8, (y) list, z7);
        } else {
            n0(i8, list, z7);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void H(int i8, long j8) {
        this.f6135a.D0(i8, j8);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void I(int i8, int i9) {
        this.f6135a.b0(i8, i9);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void J(int i8, List list, boolean z7) {
        if (list instanceof g0) {
            f0(i8, (g0) list, z7);
        } else {
            g0(i8, list, z7);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void K(int i8, List list, boolean z7) {
        if (list instanceof y) {
            V(i8, (y) list, z7);
        } else {
            W(i8, list, z7);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void L(int i8, List list, boolean z7) {
        if (list instanceof m) {
            T(i8, (m) list, z7);
        } else {
            U(i8, list, z7);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void M(int i8, Object obj, d1 d1Var) {
        this.f6135a.j0(i8, (p0) obj, d1Var);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void N(int i8, int i9) {
        this.f6135a.v0(i8, i9);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void O(int i8, List list) {
        for (int i9 = 0; i9 < list.size(); i9++) {
            this.f6135a.Y(i8, (h) list.get(i9));
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void a(int i8, List list, d1 d1Var) {
        for (int i9 = 0; i9 < list.size(); i9++) {
            M(i8, list.get(i9), d1Var);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void b(int i8, List list, boolean z7) {
        if (list instanceof y) {
            d0(i8, (y) list, z7);
        } else {
            e0(i8, list, z7);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void c(int i8, List list, boolean z7) {
        if (list instanceof v) {
            b0(i8, (v) list, z7);
        } else {
            c0(i8, list, z7);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void d(int i8, long j8) {
        this.f6135a.n0(i8, j8);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void e(int i8, boolean z7) {
        this.f6135a.W(i8, z7);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void f(int i8, int i9) {
        this.f6135a.B0(i8, i9);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void g(int i8, List list, d1 d1Var) {
        for (int i9 = 0; i9 < list.size(); i9++) {
            s(i8, list.get(i9), d1Var);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public final void h(int i8, Object obj) {
        if (obj instanceof h) {
            this.f6135a.q0(i8, (h) obj);
        } else {
            this.f6135a.p0(i8, (p0) obj);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void i(int i8, int i9) {
        this.f6135a.r0(i8, i9);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void j(int i8) {
        this.f6135a.A0(i8, 3);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void k(int i8, int i9) {
        this.f6135a.l0(i8, i9);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void l(int i8, List list, boolean z7) {
        if (list instanceof g0) {
            Z(i8, (g0) list, z7);
        } else {
            a0(i8, list, z7);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void m(int i8, List list, boolean z7) {
        if (list instanceof y) {
            i0(i8, (y) list, z7);
        } else {
            j0(i8, list, z7);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void n(int i8, int i9) {
        this.f6135a.d0(i8, i9);
    }

    public void n0(int i8, List list, boolean z7) {
        int i9 = 0;
        if (!z7) {
            while (i9 < list.size()) {
                this.f6135a.v0(i8, ((Integer) list.get(i9)).intValue());
                i9++;
            }
            return;
        }
        this.f6135a.A0(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            i10 += k.E(((Integer) list.get(i11)).intValue());
        }
        this.f6135a.C0(i10);
        while (i9 < list.size()) {
            this.f6135a.w0(((Integer) list.get(i9)).intValue());
            i9++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void o(int i8, double d8) {
        this.f6135a.Z(i8, d8);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void p(int i8, long j8) {
        this.f6135a.t0(i8, j8);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void q(int i8, List list, boolean z7) {
        if (list instanceof g0) {
            k0(i8, (g0) list, z7);
        } else {
            l0(i8, list, z7);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void r(int i8, List list, boolean z7) {
        if (list instanceof y) {
            X(i8, (y) list, z7);
        } else {
            Y(i8, list, z7);
        }
    }

    public void r0(int i8, List list, boolean z7) {
        int i9 = 0;
        if (!z7) {
            while (i9 < list.size()) {
                this.f6135a.B0(i8, ((Integer) list.get(i9)).intValue());
                i9++;
            }
            return;
        }
        this.f6135a.A0(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            i10 += k.L(((Integer) list.get(i11)).intValue());
        }
        this.f6135a.C0(i10);
        while (i9 < list.size()) {
            this.f6135a.C0(((Integer) list.get(i9)).intValue());
            i9++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void s(int i8, Object obj, d1 d1Var) {
        com.google.crypto.tink.shaded.protobuf.a aVar = (com.google.crypto.tink.shaded.protobuf.a) obj;
        this.f6135a.A0(i8, 2);
        this.f6135a.C0(aVar.b(d1Var));
        d1Var.c(aVar, this);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void t(int i8, List list, boolean z7) {
        if (list instanceof g0) {
            s0(i8, (g0) list, z7);
        } else {
            t0(i8, list, z7);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void u(int i8, List list, boolean z7) {
        if (list instanceof f) {
            Q(i8, (f) list, z7);
        } else {
            R(i8, list, z7);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void v(int i8, h hVar) {
        this.f6135a.Y(i8, hVar);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void w(int i8, List list, boolean z7) {
        if (list instanceof y) {
            q0(i8, (y) list, z7);
        } else {
            r0(i8, list, z7);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void x(int i8, i0.a aVar, Map map) {
        if (this.f6135a.R()) {
            S(i8, aVar, map);
            return;
        }
        for (Map.Entry entry : map.entrySet()) {
            this.f6135a.A0(i8, 2);
            this.f6135a.C0(i0.a(aVar, entry.getKey(), entry.getValue()));
            i0.b(this.f6135a, aVar, entry.getKey(), entry.getValue());
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void y(int i8, List list, boolean z7) {
        if (list instanceof g0) {
            o0(i8, (g0) list, z7);
        } else {
            p0(i8, list, z7);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p1
    public void z(int i8, long j8) {
        this.f6135a.f0(i8, j8);
    }
}
