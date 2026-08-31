package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class f0 extends x {

    /* renamed from: h  reason: collision with root package name */
    final transient Object[] f5218h;

    private f0(Object obj, Object[] objArr, int i8) {
        this.f5218h = objArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static f0 g(int i8, Object[] objArr, w wVar) {
        Object obj = objArr[0];
        Objects.requireNonNull(obj);
        Object obj2 = objArr[1];
        Objects.requireNonNull(obj2);
        o.a(obj, obj2);
        return new f0(null, objArr, 1);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.x
    final r a() {
        return new e0(this.f5218h, 1, 1);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.x
    final y d() {
        return new c0(this, this.f5218h, 0, 1);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.x
    final y e() {
        return new d0(this, new e0(this.f5218h, 0, 1));
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001b A[RETURN] */
    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.x, java.util.Map
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object get(java.lang.Object r4) {
        /*
            r3 = this;
            r0 = 0
            if (r4 != 0) goto L5
        L3:
            r4 = r0
            goto L19
        L5:
            java.lang.Object[] r1 = r3.f5218h
            r2 = 0
            r2 = r1[r2]
            java.util.Objects.requireNonNull(r2)
            boolean r4 = r2.equals(r4)
            if (r4 == 0) goto L3
            r4 = 1
            r4 = r1[r4]
            java.util.Objects.requireNonNull(r4)
        L19:
            if (r4 != 0) goto L1c
            return r0
        L1c:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_face_bundled.f0.get(java.lang.Object):java.lang.Object");
    }

    @Override // java.util.Map
    public final int size() {
        return 1;
    }
}
