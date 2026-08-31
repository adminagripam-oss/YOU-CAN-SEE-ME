package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes.dex */
public abstract class vj extends ej {

    /* renamed from: b  reason: collision with root package name */
    private static final Logger f5846b = Logger.getLogger(vj.class.getName());

    /* renamed from: c  reason: collision with root package name */
    private static final boolean f5847c = rm.C();

    /* renamed from: a  reason: collision with root package name */
    wj f5848a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ vj(uj ujVar) {
    }

    public static int A(String str) {
        int length;
        try {
            length = um.c(str);
        } catch (tm unused) {
            length = str.getBytes(uk.f5673a).length;
        }
        return B(length) + length;
    }

    public static int B(int i8) {
        return (352 - (Integer.numberOfLeadingZeros(i8) * 9)) >>> 6;
    }

    public static int a(long j8) {
        return (640 - (Long.numberOfLeadingZeros(j8) * 9)) >>> 6;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int y(int i8, ll llVar, wl wlVar) {
        int B = B(i8 << 3);
        return B + B + ((yi) llVar).f(wlVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int z(ll llVar, wl wlVar) {
        int f8 = ((yi) llVar).f(wlVar);
        return B(f8) + f8;
    }

    public final void b() {
        if (e() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void c(String str, tm tmVar) {
        f5846b.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) tmVar);
        byte[] bytes = str.getBytes(uk.f5673a);
        try {
            int length = bytes.length;
            v(length);
            o(bytes, 0, length);
        } catch (IndexOutOfBoundsException e8) {
            throw new tj(e8);
        }
    }

    public abstract int e();

    public abstract void f(byte b8);

    public abstract void g(int i8, boolean z7);

    public abstract void h(int i8, mj mjVar);

    public abstract void i(int i8, int i9);

    public abstract void j(int i8);

    public abstract void k(int i8, long j8);

    public abstract void l(long j8);

    public abstract void m(int i8, int i9);

    public abstract void n(int i8);

    public abstract void o(byte[] bArr, int i8, int i9);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void p(int i8, ll llVar, wl wlVar);

    public abstract void q(int i8, ll llVar);

    public abstract void r(int i8, mj mjVar);

    public abstract void s(int i8, String str);

    public abstract void t(int i8, int i9);

    public abstract void u(int i8, int i9);

    public abstract void v(int i8);

    public abstract void w(int i8, long j8);

    public abstract void x(long j8);
}
