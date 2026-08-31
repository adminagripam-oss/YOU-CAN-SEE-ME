package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.List;
/* loaded from: classes.dex */
abstract class yl {

    /* renamed from: a  reason: collision with root package name */
    private static final jm f5962a;

    /* renamed from: b  reason: collision with root package name */
    public static final /* synthetic */ int f5963b = 0;

    static {
        int i8 = tl.f5621d;
        f5962a = new lm();
    }

    public static void A(int i8, List list, xm xmVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        xmVar.c(i8, list);
    }

    public static void B(int i8, List list, xm xmVar, boolean z7) {
        if (list == null || list.isEmpty()) {
            return;
        }
        xmVar.t(i8, list, z7);
    }

    public static void C(int i8, List list, xm xmVar, boolean z7) {
        if (list == null || list.isEmpty()) {
            return;
        }
        xmVar.G(i8, list, z7);
    }

    public static void D(int i8, List list, xm xmVar, boolean z7) {
        if (list == null || list.isEmpty()) {
            return;
        }
        xmVar.v(i8, list, z7);
    }

    public static void E(int i8, List list, xm xmVar, boolean z7) {
        if (list == null || list.isEmpty()) {
            return;
        }
        xmVar.f(i8, list, z7);
    }

    public static void F(int i8, List list, xm xmVar, boolean z7) {
        if (list == null || list.isEmpty()) {
            return;
        }
        xmVar.x(i8, list, z7);
    }

    public static void G(int i8, List list, xm xmVar, wl wlVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i9 = 0; i9 < list.size(); i9++) {
            ((wj) xmVar).w(i8, list.get(i9), wlVar);
        }
    }

    public static void H(int i8, List list, xm xmVar, boolean z7) {
        if (list == null || list.isEmpty()) {
            return;
        }
        xmVar.A(i8, list, z7);
    }

    public static void I(int i8, List list, xm xmVar, boolean z7) {
        if (list == null || list.isEmpty()) {
            return;
        }
        xmVar.i(i8, list, z7);
    }

    public static void a(int i8, List list, xm xmVar, wl wlVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i9 = 0; i9 < list.size(); i9++) {
            ((wj) xmVar).n(i8, list.get(i9), wlVar);
        }
    }

    public static void b(int i8, List list, xm xmVar, boolean z7) {
        if (list == null || list.isEmpty()) {
            return;
        }
        xmVar.k(i8, list, z7);
    }

    public static void c(int i8, List list, xm xmVar, boolean z7) {
        if (list == null || list.isEmpty()) {
            return;
        }
        xmVar.y(i8, list, z7);
    }

    public static void d(int i8, List list, xm xmVar, boolean z7) {
        if (list == null || list.isEmpty()) {
            return;
        }
        xmVar.a(i8, list, z7);
    }

    public static void e(int i8, List list, xm xmVar, boolean z7) {
        if (list == null || list.isEmpty()) {
            return;
        }
        xmVar.s(i8, list, z7);
    }

    public static void f(int i8, List list, xm xmVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        xmVar.C(i8, list);
    }

    public static void g(int i8, List list, xm xmVar, boolean z7) {
        if (list == null || list.isEmpty()) {
            return;
        }
        xmVar.u(i8, list, z7);
    }

    public static void h(int i8, List list, xm xmVar, boolean z7) {
        if (list == null || list.isEmpty()) {
            return;
        }
        xmVar.e(i8, list, z7);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean i(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int j(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof pk) {
            pk pkVar = (pk) list;
            if (size <= 0) {
                return 0;
            }
            throw null;
        }
        int i8 = 0;
        for (int i9 = 0; i9 < size; i9++) {
            i8 += vj.a(((Integer) list.get(i9)).intValue());
        }
        return i8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int k(int i8, List list, boolean z7) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (vj.B(i8 << 3) + 4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int l(List list) {
        return list.size() * 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int m(int i8, List list, boolean z7) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (vj.B(i8 << 3) + 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int n(List list) {
        return list.size() * 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int o(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof pk) {
            pk pkVar = (pk) list;
            if (size <= 0) {
                return 0;
            }
            throw null;
        }
        int i8 = 0;
        for (int i9 = 0; i9 < size; i9++) {
            i8 += vj.a(((Integer) list.get(i9)).intValue());
        }
        return i8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int p(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof bl) {
            bl blVar = (bl) list;
            if (size <= 0) {
                return 0;
            }
            throw null;
        }
        int i8 = 0;
        for (int i9 = 0; i9 < size; i9++) {
            i8 += vj.a(((Long) list.get(i9)).longValue());
        }
        return i8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int q(int i8, Object obj, wl wlVar) {
        return vj.B(i8 << 3) + vj.z((ll) obj, wlVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int r(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof pk) {
            pk pkVar = (pk) list;
            if (size <= 0) {
                return 0;
            }
            throw null;
        }
        int i8 = 0;
        for (int i9 = 0; i9 < size; i9++) {
            int intValue = ((Integer) list.get(i9)).intValue();
            i8 += vj.B((intValue >> 31) ^ (intValue + intValue));
        }
        return i8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int s(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof bl) {
            bl blVar = (bl) list;
            if (size <= 0) {
                return 0;
            }
            throw null;
        }
        int i8 = 0;
        for (int i9 = 0; i9 < size; i9++) {
            long longValue = ((Long) list.get(i9)).longValue();
            i8 += vj.a((longValue >> 63) ^ (longValue + longValue));
        }
        return i8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int t(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof pk) {
            pk pkVar = (pk) list;
            if (size <= 0) {
                return 0;
            }
            throw null;
        }
        int i8 = 0;
        for (int i9 = 0; i9 < size; i9++) {
            i8 += vj.B(((Integer) list.get(i9)).intValue());
        }
        return i8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int u(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof bl) {
            bl blVar = (bl) list;
            if (size <= 0) {
                return 0;
            }
            throw null;
        }
        int i8 = 0;
        for (int i9 = 0; i9 < size; i9++) {
            i8 += vj.a(((Long) list.get(i9)).longValue());
        }
        return i8;
    }

    public static jm v() {
        return f5962a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:0:?, code lost:
        r5 = r5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Object w(java.lang.Object r2, int r3, int r4, java.lang.Object r5, com.google.android.gms.internal.mlkit_vision_face_bundled.jm r6) {
        /*
            if (r5 != 0) goto L12
            com.google.android.gms.internal.mlkit_vision_face_bundled.ok r2 = (com.google.android.gms.internal.mlkit_vision_face_bundled.ok) r2
            com.google.android.gms.internal.mlkit_vision_face_bundled.km r5 = r2.zzc
            com.google.android.gms.internal.mlkit_vision_face_bundled.km r6 = com.google.android.gms.internal.mlkit_vision_face_bundled.km.c()
            if (r5 != r6) goto L12
            com.google.android.gms.internal.mlkit_vision_face_bundled.km r5 = com.google.android.gms.internal.mlkit_vision_face_bundled.km.f()
            r2.zzc = r5
        L12:
            long r0 = (long) r4
            int r2 = r3 << 3
            r3 = r5
            com.google.android.gms.internal.mlkit_vision_face_bundled.km r3 = (com.google.android.gms.internal.mlkit_vision_face_bundled.km) r3
            java.lang.Long r4 = java.lang.Long.valueOf(r0)
            r3.j(r2, r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_face_bundled.yl.w(java.lang.Object, int, int, java.lang.Object, com.google.android.gms.internal.mlkit_vision_face_bundled.jm):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void x(bk bkVar, Object obj, Object obj2) {
        fk fkVar = ((lk) obj2).zzb;
        if (fkVar.f5233a.isEmpty()) {
            return;
        }
        ((lk) obj).A().i(fkVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void y(jm jmVar, Object obj, Object obj2) {
        ok okVar = (ok) obj;
        km kmVar = okVar.zzc;
        km kmVar2 = ((ok) obj2).zzc;
        if (!km.c().equals(kmVar2)) {
            if (km.c().equals(kmVar)) {
                kmVar = km.e(kmVar, kmVar2);
            } else {
                kmVar.d(kmVar2);
            }
        }
        okVar.zzc = kmVar;
    }

    public static void z(int i8, List list, xm xmVar, boolean z7) {
        if (list == null || list.isEmpty()) {
            return;
        }
        xmVar.r(i8, list, z7);
    }
}
