package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class wj implements xm {

    /* renamed from: a  reason: collision with root package name */
    private final vj f5892a;

    private wj(vj vjVar) {
        byte[] bArr = uk.f5674b;
        this.f5892a = vjVar;
        vjVar.f5848a = this;
    }

    public static wj L(vj vjVar) {
        wj wjVar = vjVar.f5848a;
        return wjVar != null ? wjVar : new wj(vjVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void A(int i8, List list, boolean z7) {
        if (list instanceof pk) {
            pk pkVar = (pk) list;
            if (!z7) {
                throw null;
            }
            this.f5892a.t(i8, 2);
            throw null;
        }
        int i9 = 0;
        if (!z7) {
            while (i9 < list.size()) {
                this.f5892a.m(i8, ((Integer) list.get(i9)).intValue());
                i9++;
            }
            return;
        }
        this.f5892a.t(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            i10 += vj.a(((Integer) list.get(i11)).intValue());
        }
        this.f5892a.v(i10);
        while (i9 < list.size()) {
            this.f5892a.n(((Integer) list.get(i9)).intValue());
            i9++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void B(int i8, long j8) {
        this.f5892a.k(i8, j8);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void C(int i8, List list) {
        int i9 = 0;
        if (!(list instanceof yk)) {
            while (i9 < list.size()) {
                this.f5892a.s(i8, (String) list.get(i9));
                i9++;
            }
            return;
        }
        yk ykVar = (yk) list;
        while (i9 < list.size()) {
            Object zza = ykVar.zza();
            if (zza instanceof String) {
                this.f5892a.s(i8, (String) zza);
            } else {
                this.f5892a.h(i8, (mj) zza);
            }
            i9++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void D(int i8, long j8) {
        this.f5892a.k(i8, j8);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void E(int i8, int i9) {
        this.f5892a.m(i8, i9);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void F(int i8, long j8) {
        this.f5892a.w(i8, j8);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void G(int i8, List list, boolean z7) {
        if (list instanceof pk) {
            pk pkVar = (pk) list;
            if (!z7) {
                throw null;
            }
            this.f5892a.t(i8, 2);
            throw null;
        }
        int i9 = 0;
        if (!z7) {
            while (i9 < list.size()) {
                this.f5892a.m(i8, ((Integer) list.get(i9)).intValue());
                i9++;
            }
            return;
        }
        this.f5892a.t(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            i10 += vj.a(((Integer) list.get(i11)).intValue());
        }
        this.f5892a.v(i10);
        while (i9 < list.size()) {
            this.f5892a.n(((Integer) list.get(i9)).intValue());
            i9++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void H(int i8, mj mjVar) {
        this.f5892a.h(i8, mjVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void I(int i8) {
        this.f5892a.t(i8, 4);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void J(int i8) {
        this.f5892a.t(i8, 3);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void K(int i8, int i9) {
        this.f5892a.m(i8, i9);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void a(int i8, List list, boolean z7) {
        if (list instanceof pk) {
            pk pkVar = (pk) list;
            if (!z7) {
                throw null;
            }
            this.f5892a.t(i8, 2);
            throw null;
        }
        int i9 = 0;
        if (!z7) {
            while (i9 < list.size()) {
                vj vjVar = this.f5892a;
                int intValue = ((Integer) list.get(i9)).intValue();
                vjVar.u(i8, (intValue >> 31) ^ (intValue + intValue));
                i9++;
            }
            return;
        }
        this.f5892a.t(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            int intValue2 = ((Integer) list.get(i11)).intValue();
            i10 += vj.B((intValue2 >> 31) ^ (intValue2 + intValue2));
        }
        this.f5892a.v(i10);
        while (i9 < list.size()) {
            vj vjVar2 = this.f5892a;
            int intValue3 = ((Integer) list.get(i9)).intValue();
            vjVar2.v((intValue3 >> 31) ^ (intValue3 + intValue3));
            i9++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void b(int i8, double d8) {
        this.f5892a.k(i8, Double.doubleToRawLongBits(d8));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void c(int i8, List list) {
        for (int i9 = 0; i9 < list.size(); i9++) {
            this.f5892a.h(i8, (mj) list.get(i9));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void d(int i8, long j8) {
        this.f5892a.w(i8, (j8 >> 63) ^ (j8 + j8));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void e(int i8, List list, boolean z7) {
        if (list instanceof bl) {
            bl blVar = (bl) list;
            if (!z7) {
                throw null;
            }
            this.f5892a.t(i8, 2);
            throw null;
        }
        int i9 = 0;
        if (!z7) {
            while (i9 < list.size()) {
                this.f5892a.w(i8, ((Long) list.get(i9)).longValue());
                i9++;
            }
            return;
        }
        this.f5892a.t(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            i10 += vj.a(((Long) list.get(i11)).longValue());
        }
        this.f5892a.v(i10);
        while (i9 < list.size()) {
            this.f5892a.x(((Long) list.get(i9)).longValue());
            i9++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void f(int i8, List list, boolean z7) {
        if (list instanceof bl) {
            bl blVar = (bl) list;
            if (!z7) {
                throw null;
            }
            this.f5892a.t(i8, 2);
            throw null;
        }
        int i9 = 0;
        if (!z7) {
            while (i9 < list.size()) {
                this.f5892a.k(i8, ((Long) list.get(i9)).longValue());
                i9++;
            }
            return;
        }
        this.f5892a.t(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            ((Long) list.get(i11)).longValue();
            i10 += 8;
        }
        this.f5892a.v(i10);
        while (i9 < list.size()) {
            this.f5892a.l(((Long) list.get(i9)).longValue());
            i9++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void g(int i8, String str) {
        this.f5892a.s(i8, str);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void h(int i8, float f8) {
        this.f5892a.i(i8, Float.floatToRawIntBits(f8));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void i(int i8, List list, boolean z7) {
        if (list instanceof bl) {
            bl blVar = (bl) list;
            if (!z7) {
                throw null;
            }
            this.f5892a.t(i8, 2);
            throw null;
        }
        int i9 = 0;
        if (!z7) {
            while (i9 < list.size()) {
                this.f5892a.w(i8, ((Long) list.get(i9)).longValue());
                i9++;
            }
            return;
        }
        this.f5892a.t(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            i10 += vj.a(((Long) list.get(i11)).longValue());
        }
        this.f5892a.v(i10);
        while (i9 < list.size()) {
            this.f5892a.x(((Long) list.get(i9)).longValue());
            i9++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void j(int i8, int i9) {
        this.f5892a.u(i8, i9);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void k(int i8, List list, boolean z7) {
        if (list instanceof pk) {
            pk pkVar = (pk) list;
            if (!z7) {
                throw null;
            }
            this.f5892a.t(i8, 2);
            throw null;
        }
        int i9 = 0;
        if (!z7) {
            while (i9 < list.size()) {
                this.f5892a.i(i8, ((Integer) list.get(i9)).intValue());
                i9++;
            }
            return;
        }
        this.f5892a.t(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            ((Integer) list.get(i11)).intValue();
            i10 += 4;
        }
        this.f5892a.v(i10);
        while (i9 < list.size()) {
            this.f5892a.j(((Integer) list.get(i9)).intValue());
            i9++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void l(int i8, int i9) {
        this.f5892a.i(i8, i9);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void m(int i8, Object obj) {
        if (obj instanceof mj) {
            this.f5892a.r(i8, (mj) obj);
        } else {
            this.f5892a.q(i8, (ll) obj);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void n(int i8, Object obj, wl wlVar) {
        this.f5892a.p(i8, (ll) obj, wlVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void o(int i8, boolean z7) {
        this.f5892a.g(i8, z7);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void p(int i8, int i9) {
        this.f5892a.i(i8, i9);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void q(int i8, long j8) {
        this.f5892a.w(i8, j8);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void r(int i8, List list, boolean z7) {
        if (list instanceof dj) {
            dj djVar = (dj) list;
            if (!z7) {
                throw null;
            }
            this.f5892a.t(i8, 2);
            throw null;
        }
        int i9 = 0;
        if (!z7) {
            while (i9 < list.size()) {
                this.f5892a.g(i8, ((Boolean) list.get(i9)).booleanValue());
                i9++;
            }
            return;
        }
        this.f5892a.t(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            ((Boolean) list.get(i11)).booleanValue();
            i10++;
        }
        this.f5892a.v(i10);
        while (i9 < list.size()) {
            this.f5892a.f(((Boolean) list.get(i9)).booleanValue() ? (byte) 1 : (byte) 0);
            i9++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void s(int i8, List list, boolean z7) {
        if (list instanceof bl) {
            bl blVar = (bl) list;
            if (!z7) {
                throw null;
            }
            this.f5892a.t(i8, 2);
            throw null;
        }
        int i9 = 0;
        if (!z7) {
            while (i9 < list.size()) {
                vj vjVar = this.f5892a;
                long longValue = ((Long) list.get(i9)).longValue();
                vjVar.w(i8, (longValue >> 63) ^ (longValue + longValue));
                i9++;
            }
            return;
        }
        this.f5892a.t(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            long longValue2 = ((Long) list.get(i11)).longValue();
            i10 += vj.a((longValue2 >> 63) ^ (longValue2 + longValue2));
        }
        this.f5892a.v(i10);
        while (i9 < list.size()) {
            vj vjVar2 = this.f5892a;
            long longValue3 = ((Long) list.get(i9)).longValue();
            vjVar2.x((longValue3 >> 63) ^ (longValue3 + longValue3));
            i9++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void t(int i8, List list, boolean z7) {
        if (list instanceof xj) {
            xj xjVar = (xj) list;
            if (!z7) {
                throw null;
            }
            this.f5892a.t(i8, 2);
            throw null;
        }
        int i9 = 0;
        if (!z7) {
            while (i9 < list.size()) {
                this.f5892a.k(i8, Double.doubleToRawLongBits(((Double) list.get(i9)).doubleValue()));
                i9++;
            }
            return;
        }
        this.f5892a.t(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            ((Double) list.get(i11)).doubleValue();
            i10 += 8;
        }
        this.f5892a.v(i10);
        while (i9 < list.size()) {
            this.f5892a.l(Double.doubleToRawLongBits(((Double) list.get(i9)).doubleValue()));
            i9++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void u(int i8, List list, boolean z7) {
        if (list instanceof pk) {
            pk pkVar = (pk) list;
            if (!z7) {
                throw null;
            }
            this.f5892a.t(i8, 2);
            throw null;
        }
        int i9 = 0;
        if (!z7) {
            while (i9 < list.size()) {
                this.f5892a.u(i8, ((Integer) list.get(i9)).intValue());
                i9++;
            }
            return;
        }
        this.f5892a.t(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            i10 += vj.B(((Integer) list.get(i11)).intValue());
        }
        this.f5892a.v(i10);
        while (i9 < list.size()) {
            this.f5892a.v(((Integer) list.get(i9)).intValue());
            i9++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void v(int i8, List list, boolean z7) {
        if (list instanceof pk) {
            pk pkVar = (pk) list;
            if (!z7) {
                throw null;
            }
            this.f5892a.t(i8, 2);
            throw null;
        }
        int i9 = 0;
        if (!z7) {
            while (i9 < list.size()) {
                this.f5892a.i(i8, ((Integer) list.get(i9)).intValue());
                i9++;
            }
            return;
        }
        this.f5892a.t(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            ((Integer) list.get(i11)).intValue();
            i10 += 4;
        }
        this.f5892a.v(i10);
        while (i9 < list.size()) {
            this.f5892a.j(((Integer) list.get(i9)).intValue());
            i9++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void w(int i8, Object obj, wl wlVar) {
        vj vjVar = this.f5892a;
        vjVar.t(i8, 3);
        wlVar.d((ll) obj, vjVar.f5848a);
        vjVar.t(i8, 4);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void x(int i8, List list, boolean z7) {
        if (list instanceof hk) {
            hk hkVar = (hk) list;
            if (!z7) {
                throw null;
            }
            this.f5892a.t(i8, 2);
            throw null;
        }
        int i9 = 0;
        if (!z7) {
            while (i9 < list.size()) {
                this.f5892a.i(i8, Float.floatToRawIntBits(((Float) list.get(i9)).floatValue()));
                i9++;
            }
            return;
        }
        this.f5892a.t(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            ((Float) list.get(i11)).floatValue();
            i10 += 4;
        }
        this.f5892a.v(i10);
        while (i9 < list.size()) {
            this.f5892a.j(Float.floatToRawIntBits(((Float) list.get(i9)).floatValue()));
            i9++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void y(int i8, List list, boolean z7) {
        if (list instanceof bl) {
            bl blVar = (bl) list;
            if (!z7) {
                throw null;
            }
            this.f5892a.t(i8, 2);
            throw null;
        }
        int i9 = 0;
        if (!z7) {
            while (i9 < list.size()) {
                this.f5892a.k(i8, ((Long) list.get(i9)).longValue());
                i9++;
            }
            return;
        }
        this.f5892a.t(i8, 2);
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            ((Long) list.get(i11)).longValue();
            i10 += 8;
        }
        this.f5892a.v(i10);
        while (i9 < list.size()) {
            this.f5892a.l(((Long) list.get(i9)).longValue());
            i9++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xm
    public final void z(int i8, int i9) {
        this.f5892a.u(i8, (i9 >> 31) ^ (i9 + i9));
    }
}
