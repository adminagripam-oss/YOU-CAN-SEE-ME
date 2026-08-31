package com.google.android.gms.internal.mlkit_vision_face_bundled;

import sun.misc.Unsafe;
/* loaded from: classes.dex */
final class nm extends qm {
    /* JADX INFO: Access modifiers changed from: package-private */
    public nm(Unsafe unsafe) {
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

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: com.google.android.gms.internal.mlkit_vision_face_bundled.rm.n(java.lang.Object, long, boolean):void
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:76)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:51)
        Caused by: java.util.ConcurrentModificationException
        	at java.base/java.util.ArrayList.removeIf(ArrayList.java:1685)
        	at java.base/java.util.ArrayList.removeIf(ArrayList.java:1660)
        	at jadx.core.dex.instructions.args.SSAVar.removeUse(SSAVar.java:130)
        	at jadx.core.dex.instructions.args.SSAVar.use(SSAVar.java:123)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:481)
        	at jadx.core.dex.instructions.mods.TernaryInsn.rebindArgs(TernaryInsn.java:92)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:484)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1079)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1088)
        	at jadx.core.dex.visitors.InlineMethods.inlineMethod(InlineMethods.java:115)
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:74)
        	... 1 more
        */
    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.qm
    public final void c(java.lang.Object r2, long r3, boolean r5) {
        /*
            r1 = this;
            boolean r0 = com.google.android.gms.internal.mlkit_vision_face_bundled.rm.f5584h
            if (r0 == 0) goto L8
            com.google.android.gms.internal.mlkit_vision_face_bundled.rm.n(r2, r3, r5)
            return
        L8:
            com.google.android.gms.internal.mlkit_vision_face_bundled.rm.o(r2, r3, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_face_bundled.nm.c(java.lang.Object, long, boolean):void");
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
