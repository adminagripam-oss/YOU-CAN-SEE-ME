package m7;

import java.util.concurrent.CancellationException;
import k7.v0;
/* loaded from: classes.dex */
abstract /* synthetic */ class l {
    public static final void a(s sVar, Throwable th) {
        if (th != null) {
            r0 = th instanceof CancellationException ? (CancellationException) th : null;
            if (r0 == null) {
                r0 = v0.a("Channel was consumed, consumer had failed", th);
            }
        }
        sVar.d(r0);
    }
}
