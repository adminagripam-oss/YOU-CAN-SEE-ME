package com.google.android.gms.common.api.internal;
/* loaded from: classes.dex */
final class m implements Runnable {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ q f5047e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(q qVar) {
        this.f5047e = qVar;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: com.google.android.gms.common.api.internal.q.z(com.google.android.gms.common.api.internal.q):void
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:76)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:51)
        Caused by: java.lang.ArrayIndexOutOfBoundsException: Index 16 out of bounds for length 15
        	at java.base/java.util.ArrayList.add(ArrayList.java:455)
        	at java.base/java.util.ArrayList.add(ArrayList.java:467)
        	at jadx.core.utils.ListUtils.safeReplace(ListUtils.java:82)
        	at jadx.core.dex.visitors.InlineMethods.replaceClsUsage(InlineMethods.java:179)
        	at jadx.core.dex.visitors.InlineMethods.lambda$updateUsageInfo$0(InlineMethods.java:158)
        	at jadx.core.dex.nodes.InsnNode.visitInsns(InsnNode.java:280)
        	at jadx.core.dex.visitors.InlineMethods.updateUsageInfo(InlineMethods.java:149)
        	at jadx.core.dex.visitors.InlineMethods.inlineMethod(InlineMethods.java:122)
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:74)
        	... 1 more
        */
    @Override // java.lang.Runnable
    public final void run() {
        /*
            r1 = this;
            com.google.android.gms.common.api.internal.q r0 = r1.f5047e
            com.google.android.gms.common.api.internal.q.z(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.m.run():void");
    }
}
