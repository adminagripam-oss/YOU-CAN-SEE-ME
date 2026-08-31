package androidx.core.os;

import android.os.CancellationSignal;
/* loaded from: classes.dex */
public final class d {

    /* renamed from: a  reason: collision with root package name */
    private boolean f3045a;

    /* renamed from: b  reason: collision with root package name */
    private Object f3046b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f3047c;

    public void a() {
        synchronized (this) {
            if (this.f3045a) {
                return;
            }
            this.f3045a = true;
            this.f3047c = true;
            Object obj = this.f3046b;
            if (obj != null) {
                try {
                    ((CancellationSignal) obj).cancel();
                } catch (Throwable th) {
                    synchronized (this) {
                        this.f3047c = false;
                        notifyAll();
                        throw th;
                    }
                }
            }
            synchronized (this) {
                this.f3047c = false;
                notifyAll();
            }
        }
    }
}
