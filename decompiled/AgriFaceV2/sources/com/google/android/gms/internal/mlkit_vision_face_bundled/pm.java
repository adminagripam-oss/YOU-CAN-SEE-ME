package com.google.android.gms.internal.mlkit_vision_face_bundled;

import sun.misc.Unsafe;
/* loaded from: classes.dex */
final class pm extends qm {
    /* JADX INFO: Access modifiers changed from: package-private */
    public pm(Unsafe unsafe) {
        super(unsafe);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.qm
    public final double a(Object obj, long j8) {
        return Double.longBitsToDouble(this.f5563a.getLong(obj, j8));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.qm
    public final float b(Object obj, long j8) {
        return Float.intBitsToFloat(this.f5563a.getInt(obj, j8));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.qm
    public final void c(Object obj, long j8, boolean z7) {
        if (rm.f5584h) {
            rm.d(obj, j8, r3 ? (byte) 1 : (byte) 0);
        } else {
            rm.e(obj, j8, r3 ? (byte) 1 : (byte) 0);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.qm
    public final void d(Object obj, long j8, byte b8) {
        if (rm.f5584h) {
            rm.d(obj, j8, b8);
        } else {
            rm.e(obj, j8, b8);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.qm
    public final void e(Object obj, long j8, double d8) {
        this.f5563a.putLong(obj, j8, Double.doubleToLongBits(d8));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.qm
    public final void f(Object obj, long j8, float f8) {
        this.f5563a.putInt(obj, j8, Float.floatToIntBits(f8));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.qm
    public final boolean g(Object obj, long j8) {
        return rm.f5584h ? rm.y(obj, j8) : rm.z(obj, j8);
    }
}
