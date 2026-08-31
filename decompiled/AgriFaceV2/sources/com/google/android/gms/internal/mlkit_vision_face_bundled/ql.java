package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes.dex */
final class ql implements wl {

    /* renamed from: a  reason: collision with root package name */
    private final ll f5559a;

    /* renamed from: b  reason: collision with root package name */
    private final jm f5560b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f5561c;

    /* renamed from: d  reason: collision with root package name */
    private final bk f5562d;

    private ql(jm jmVar, bk bkVar, ll llVar) {
        this.f5560b = jmVar;
        this.f5561c = llVar instanceof lk;
        this.f5562d = bkVar;
        this.f5559a = llVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ql j(jm jmVar, bk bkVar, ll llVar) {
        return new ql(jmVar, bkVar, llVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.wl
    public final void a(Object obj) {
        this.f5560b.a(obj);
        this.f5562d.a(obj);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.wl
    public final int b(Object obj) {
        int b8 = ((ok) obj).zzc.b();
        return this.f5561c ? b8 + ((lk) obj).zzb.b() : b8;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.wl
    public final Object c() {
        ll llVar = this.f5559a;
        return llVar instanceof ok ? ((ok) llVar).y() : llVar.d().b();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.wl
    public final void d(Object obj, xm xmVar) {
        Iterator f8 = ((lk) obj).zzb.f();
        if (!f8.hasNext()) {
            ((ok) obj).zzc.k(xmVar);
            return;
        }
        ek ekVar = (ek) ((Map.Entry) f8.next()).getKey();
        if (ekVar.a() == wm.MESSAGE) {
            ekVar.c();
        }
        throw new IllegalStateException("Found invalid MessageSet item.");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.wl
    public final void e(Object obj, Object obj2) {
        yl.y(this.f5560b, obj, obj2);
        if (this.f5561c) {
            yl.x(this.f5562d, obj, obj2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00b5 A[EDGE_INSN: B:55:0x00b5->B:33:0x00b5 ?: BREAK  , SYNTHETIC] */
    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.wl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void f(java.lang.Object r11, byte[] r12, int r13, int r14, com.google.android.gms.internal.mlkit_vision_face_bundled.bj r15) {
        /*
            r10 = this;
            r0 = r11
            com.google.android.gms.internal.mlkit_vision_face_bundled.ok r0 = (com.google.android.gms.internal.mlkit_vision_face_bundled.ok) r0
            com.google.android.gms.internal.mlkit_vision_face_bundled.km r1 = r0.zzc
            com.google.android.gms.internal.mlkit_vision_face_bundled.km r2 = com.google.android.gms.internal.mlkit_vision_face_bundled.km.c()
            if (r1 != r2) goto L11
            com.google.android.gms.internal.mlkit_vision_face_bundled.km r1 = com.google.android.gms.internal.mlkit_vision_face_bundled.km.f()
            r0.zzc = r1
        L11:
            com.google.android.gms.internal.mlkit_vision_face_bundled.lk r11 = (com.google.android.gms.internal.mlkit_vision_face_bundled.lk) r11
            com.google.android.gms.internal.mlkit_vision_face_bundled.fk r11 = r11.A()
            r0 = 0
            r2 = r0
        L19:
            if (r13 >= r14) goto Lc0
            int r4 = com.google.android.gms.internal.mlkit_vision_face_bundled.cj.j(r12, r13, r15)
            int r13 = r15.f5147a
            r3 = 11
            r5 = 2
            if (r13 == r3) goto L60
            r3 = r13 & 7
            if (r3 != r5) goto L5b
            com.google.android.gms.internal.mlkit_vision_face_bundled.ak r2 = r15.f5150d
            com.google.android.gms.internal.mlkit_vision_face_bundled.ll r3 = r10.f5559a
            int r5 = r13 >>> 3
            com.google.android.gms.internal.mlkit_vision_face_bundled.nk r8 = r2.b(r3, r5)
            if (r8 == 0) goto L50
            com.google.android.gms.internal.mlkit_vision_face_bundled.ll r13 = r8.f5492c
            com.google.android.gms.internal.mlkit_vision_face_bundled.tl r2 = com.google.android.gms.internal.mlkit_vision_face_bundled.tl.a()
            java.lang.Class r13 = r13.getClass()
            com.google.android.gms.internal.mlkit_vision_face_bundled.wl r13 = r2.b(r13)
            int r13 = com.google.android.gms.internal.mlkit_vision_face_bundled.cj.e(r13, r12, r4, r14, r15)
            java.lang.Object r2 = r15.f5149c
            com.google.android.gms.internal.mlkit_vision_face_bundled.mk r3 = r8.f5493d
            r11.j(r3, r2)
            goto L59
        L50:
            r2 = r13
            r3 = r12
            r5 = r14
            r6 = r1
            r7 = r15
            int r13 = com.google.android.gms.internal.mlkit_vision_face_bundled.cj.i(r2, r3, r4, r5, r6, r7)
        L59:
            r2 = r8
            goto L19
        L5b:
            int r13 = com.google.android.gms.internal.mlkit_vision_face_bundled.cj.p(r13, r12, r4, r14, r15)
            goto L19
        L60:
            r13 = 0
            r3 = r0
        L62:
            if (r4 >= r14) goto Lb5
            int r4 = com.google.android.gms.internal.mlkit_vision_face_bundled.cj.j(r12, r4, r15)
            int r6 = r15.f5147a
            int r7 = r6 >>> 3
            r8 = r6 & 7
            if (r7 == r5) goto L9b
            r9 = 3
            if (r7 == r9) goto L74
            goto Lac
        L74:
            if (r2 == 0) goto L90
            com.google.android.gms.internal.mlkit_vision_face_bundled.ll r6 = r2.f5492c
            com.google.android.gms.internal.mlkit_vision_face_bundled.tl r7 = com.google.android.gms.internal.mlkit_vision_face_bundled.tl.a()
            java.lang.Class r6 = r6.getClass()
            com.google.android.gms.internal.mlkit_vision_face_bundled.wl r6 = r7.b(r6)
            int r4 = com.google.android.gms.internal.mlkit_vision_face_bundled.cj.e(r6, r12, r4, r14, r15)
            java.lang.Object r6 = r15.f5149c
            com.google.android.gms.internal.mlkit_vision_face_bundled.mk r7 = r2.f5493d
            r11.j(r7, r6)
            goto L62
        L90:
            if (r8 != r5) goto Lac
            int r4 = com.google.android.gms.internal.mlkit_vision_face_bundled.cj.a(r12, r4, r15)
            java.lang.Object r3 = r15.f5149c
            com.google.android.gms.internal.mlkit_vision_face_bundled.mj r3 = (com.google.android.gms.internal.mlkit_vision_face_bundled.mj) r3
            goto L62
        L9b:
            if (r8 != 0) goto Lac
            int r4 = com.google.android.gms.internal.mlkit_vision_face_bundled.cj.j(r12, r4, r15)
            int r13 = r15.f5147a
            com.google.android.gms.internal.mlkit_vision_face_bundled.ak r2 = r15.f5150d
            com.google.android.gms.internal.mlkit_vision_face_bundled.ll r6 = r10.f5559a
            com.google.android.gms.internal.mlkit_vision_face_bundled.nk r2 = r2.b(r6, r13)
            goto L62
        Lac:
            r7 = 12
            if (r6 == r7) goto Lb5
            int r4 = com.google.android.gms.internal.mlkit_vision_face_bundled.cj.p(r6, r12, r4, r14, r15)
            goto L62
        Lb5:
            if (r3 == 0) goto Lbd
            int r13 = r13 << 3
            r13 = r13 | r5
            r1.j(r13, r3)
        Lbd:
            r13 = r4
            goto L19
        Lc0:
            if (r13 != r14) goto Lc3
            return
        Lc3:
            com.google.android.gms.internal.mlkit_vision_face_bundled.wk r11 = new com.google.android.gms.internal.mlkit_vision_face_bundled.wk
            java.lang.String r12 = "Failed to parse the message."
            r11.<init>(r12)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_face_bundled.ql.f(java.lang.Object, byte[], int, int, com.google.android.gms.internal.mlkit_vision_face_bundled.bj):void");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.wl
    public final boolean g(Object obj, Object obj2) {
        if (((ok) obj).zzc.equals(((ok) obj2).zzc)) {
            if (this.f5561c) {
                return ((lk) obj).zzb.equals(((lk) obj2).zzb);
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.wl
    public final boolean h(Object obj) {
        return ((lk) obj).zzb.l();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.wl
    public final int i(Object obj) {
        int hashCode = ((ok) obj).zzc.hashCode();
        return this.f5561c ? (hashCode * 53) + ((lk) obj).zzb.f5233a.hashCode() : hashCode;
    }
}
