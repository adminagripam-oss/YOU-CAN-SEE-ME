package k7;

import java.util.concurrent.locks.LockSupport;
/* loaded from: classes.dex */
public abstract class s0 extends q0 {
    protected abstract Thread b0();

    /* JADX INFO: Access modifiers changed from: protected */
    public final void c0() {
        Thread b02 = b0();
        if (Thread.currentThread() != b02) {
            c.a();
            LockSupport.unpark(b02);
        }
    }
}
