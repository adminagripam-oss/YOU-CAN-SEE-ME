package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
final class fk {

    /* renamed from: c  reason: collision with root package name */
    private static final fk f5232c = new fk(true);

    /* renamed from: a  reason: collision with root package name */
    final gm f5233a = new zl();

    /* renamed from: b  reason: collision with root package name */
    private boolean f5234b;

    private fk() {
    }

    public static int a(ek ekVar, Object obj) {
        long longValue;
        int a8;
        int intValue;
        vm zzb = ekVar.zzb();
        ekVar.zza();
        ekVar.c();
        List list = (List) obj;
        int size = list.size();
        ekVar.b();
        int i8 = 0;
        for (int i9 = 0; i9 < size; i9++) {
            Object obj2 = list.get(i9);
            int B = vj.B(1616448016);
            if (zzb == vm.f5862o) {
                ll llVar = (ll) obj2;
                byte[] bArr = uk.f5674b;
                B += B;
            }
            wm wmVar = wm.INT;
            int i10 = 4;
            switch (zzb.ordinal()) {
                case 0:
                    ((Double) obj2).doubleValue();
                    i10 = 8;
                    break;
                case 1:
                    ((Float) obj2).floatValue();
                    break;
                case 2:
                case 3:
                    longValue = ((Long) obj2).longValue();
                    i10 = vj.a(longValue);
                    break;
                case 4:
                case 13:
                    longValue = ((Integer) obj2).intValue();
                    i10 = vj.a(longValue);
                    break;
                case 5:
                case 15:
                    ((Long) obj2).longValue();
                    i10 = 8;
                    break;
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                case 14:
                    ((Integer) obj2).intValue();
                    break;
                case 7:
                    ((Boolean) obj2).booleanValue();
                    i10 = 1;
                    break;
                case 8:
                    if (!(obj2 instanceof mj)) {
                        i10 = vj.A((String) obj2);
                        break;
                    }
                    a8 = ((mj) obj2).j();
                    i10 = vj.B(a8) + a8;
                    break;
                case 9:
                    i10 = ((ll) obj2).a();
                    break;
                case 10:
                    a8 = ((ll) obj2).a();
                    i10 = vj.B(a8) + a8;
                    break;
                case 11:
                    if (!(obj2 instanceof mj)) {
                        a8 = ((byte[]) obj2).length;
                        i10 = vj.B(a8) + a8;
                        break;
                    }
                    a8 = ((mj) obj2).j();
                    i10 = vj.B(a8) + a8;
                case 12:
                    intValue = ((Integer) obj2).intValue();
                    i10 = vj.B(intValue);
                    break;
                case 16:
                    int intValue2 = ((Integer) obj2).intValue();
                    intValue = (intValue2 >> 31) ^ (intValue2 + intValue2);
                    i10 = vj.B(intValue);
                    break;
                case 17:
                    long longValue2 = ((Long) obj2).longValue();
                    longValue = (longValue2 >> 63) ^ (longValue2 + longValue2);
                    i10 = vj.a(longValue);
                    break;
                default:
                    throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
            }
            i8 += B + i10;
        }
        return i8;
    }

    public static fk d() {
        return f5232c;
    }

    private final void m(Map.Entry entry) {
        ek ekVar = (ek) entry.getKey();
        Object value = entry.getValue();
        ekVar.c();
        Object e8 = e(ekVar);
        List list = (List) value;
        int size = list.size();
        if (e8 == null) {
            e8 = new ArrayList(size);
        }
        List list2 = (List) e8;
        for (int i8 = 0; i8 < size; i8++) {
            Object obj = list.get(i8);
            if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                int length = bArr.length;
                byte[] bArr2 = new byte[length];
                System.arraycopy(bArr, 0, bArr2, 0, length);
                obj = bArr2;
            }
            list2.add(obj);
        }
        this.f5233a.put(ekVar, e8);
    }

    private static boolean n(Map.Entry entry) {
        ek ekVar = (ek) entry.getKey();
        if (ekVar.a() == wm.MESSAGE) {
            ekVar.c();
            List list = (List) entry.getValue();
            int size = list.size();
            for (int i8 = 0; i8 < size; i8++) {
                Object obj = list.get(i8);
                if (!(obj instanceof ml)) {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
                if (!((ml) obj).g()) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    private static final int o(Map.Entry entry) {
        ek ekVar = (ek) entry.getKey();
        Object value = entry.getValue();
        if (ekVar.a() == wm.MESSAGE) {
            ekVar.c();
        }
        return a(ekVar, value);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void p(com.google.android.gms.internal.mlkit_vision_face_bundled.ek r2, java.lang.Object r3) {
        /*
            com.google.android.gms.internal.mlkit_vision_face_bundled.vm r0 = r2.zzb()
            byte[] r1 = com.google.android.gms.internal.mlkit_vision_face_bundled.uk.f5674b
            r3.getClass()
            com.google.android.gms.internal.mlkit_vision_face_bundled.vm r1 = com.google.android.gms.internal.mlkit_vision_face_bundled.vm.f5853f
            com.google.android.gms.internal.mlkit_vision_face_bundled.wm r1 = com.google.android.gms.internal.mlkit_vision_face_bundled.wm.INT
            com.google.android.gms.internal.mlkit_vision_face_bundled.wm r0 = r0.d()
            int r0 = r0.ordinal()
            switch(r0) {
                case 0: goto L3b;
                case 1: goto L38;
                case 2: goto L35;
                case 3: goto L32;
                case 4: goto L2f;
                case 5: goto L2c;
                case 6: goto L23;
                case 7: goto L1e;
                case 8: goto L19;
                default: goto L18;
            }
        L18:
            goto L40
        L19:
            boolean r0 = r3 instanceof com.google.android.gms.internal.mlkit_vision_face_bundled.ll
            if (r0 == 0) goto L40
            goto L3f
        L1e:
            boolean r0 = r3 instanceof java.lang.Integer
            if (r0 == 0) goto L40
            goto L3f
        L23:
            boolean r0 = r3 instanceof com.google.android.gms.internal.mlkit_vision_face_bundled.mj
            if (r0 != 0) goto L3f
            boolean r0 = r3 instanceof byte[]
            if (r0 == 0) goto L40
            goto L3f
        L2c:
            boolean r0 = r3 instanceof java.lang.String
            goto L3d
        L2f:
            boolean r0 = r3 instanceof java.lang.Boolean
            goto L3d
        L32:
            boolean r0 = r3 instanceof java.lang.Double
            goto L3d
        L35:
            boolean r0 = r3 instanceof java.lang.Float
            goto L3d
        L38:
            boolean r0 = r3 instanceof java.lang.Long
            goto L3d
        L3b:
            boolean r0 = r3 instanceof java.lang.Integer
        L3d:
            if (r0 == 0) goto L40
        L3f:
            return
        L40:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r2.zza()
            r1 = 202056002(0xc0b2142, float:1.0718179E-31)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            com.google.android.gms.internal.mlkit_vision_face_bundled.vm r2 = r2.zzb()
            com.google.android.gms.internal.mlkit_vision_face_bundled.wm r2 = r2.d()
            java.lang.Class r3 = r3.getClass()
            java.lang.String r3 = r3.getName()
            java.lang.Object[] r2 = new java.lang.Object[]{r1, r2, r3}
            java.lang.String r3 = "Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n"
            java.lang.String r2 = java.lang.String.format(r3, r2)
            r0.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_face_bundled.fk.p(com.google.android.gms.internal.mlkit_vision_face_bundled.ek, java.lang.Object):void");
    }

    public final int b() {
        int c8 = this.f5233a.c();
        int i8 = 0;
        for (int i9 = 0; i9 < c8; i9++) {
            i8 += o(this.f5233a.g(i9));
        }
        for (Map.Entry entry : this.f5233a.d()) {
            i8 += o(entry);
        }
        return i8;
    }

    /* renamed from: c */
    public final fk clone() {
        fk fkVar = new fk();
        int c8 = this.f5233a.c();
        for (int i8 = 0; i8 < c8; i8++) {
            Map.Entry g8 = this.f5233a.g(i8);
            fkVar.j((ek) ((am) g8).d(), g8.getValue());
        }
        for (Map.Entry entry : this.f5233a.d()) {
            fkVar.j((ek) entry.getKey(), entry.getValue());
        }
        return fkVar;
    }

    public final Object e(ek ekVar) {
        return this.f5233a.get(ekVar);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof fk) {
            return this.f5233a.equals(((fk) obj).f5233a);
        }
        return false;
    }

    public final Iterator f() {
        return this.f5233a.isEmpty() ? Collections.emptyIterator() : this.f5233a.entrySet().iterator();
    }

    public final void g(ek ekVar, Object obj) {
        List list;
        p(ekVar, obj);
        Object e8 = e(ekVar);
        if (e8 == null) {
            list = new ArrayList();
            this.f5233a.put(ekVar, list);
        } else {
            list = (List) e8;
        }
        list.add(obj);
    }

    public final void h() {
        if (this.f5234b) {
            return;
        }
        int c8 = this.f5233a.c();
        for (int i8 = 0; i8 < c8; i8++) {
            Map.Entry g8 = this.f5233a.g(i8);
            if (g8.getValue() instanceof ok) {
                ((ok) g8.getValue()).l();
            }
        }
        this.f5233a.a();
        this.f5234b = true;
    }

    public final int hashCode() {
        return this.f5233a.hashCode();
    }

    public final void i(fk fkVar) {
        int c8 = fkVar.f5233a.c();
        for (int i8 = 0; i8 < c8; i8++) {
            m(fkVar.f5233a.g(i8));
        }
        for (Map.Entry entry : fkVar.f5233a.d()) {
            m(entry);
        }
    }

    public final void j(ek ekVar, Object obj) {
        ekVar.c();
        if (!(obj instanceof List)) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        List list = (List) obj;
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i8 = 0; i8 < size; i8++) {
            Object obj2 = list.get(i8);
            p(ekVar, obj2);
            arrayList.add(obj2);
        }
        this.f5233a.put(ekVar, arrayList);
    }

    public final boolean k() {
        return this.f5234b;
    }

    public final boolean l() {
        int c8 = this.f5233a.c();
        for (int i8 = 0; i8 < c8; i8++) {
            if (!n(this.f5233a.g(i8))) {
                return false;
            }
        }
        for (Map.Entry entry : this.f5233a.d()) {
            if (!n(entry)) {
                return false;
            }
        }
        return true;
    }

    private fk(boolean z7) {
        h();
        h();
    }
}
