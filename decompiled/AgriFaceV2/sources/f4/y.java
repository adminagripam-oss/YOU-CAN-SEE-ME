package f4;

import java.util.ArrayDeque;
import java.util.Queue;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class y {

    /* renamed from: a  reason: collision with root package name */
    private final Object f6768a = new Object();

    /* renamed from: b  reason: collision with root package name */
    private Queue f6769b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f6770c;

    public final void a(x xVar) {
        synchronized (this.f6768a) {
            if (this.f6769b == null) {
                this.f6769b = new ArrayDeque();
            }
            this.f6769b.add(xVar);
        }
    }

    public final void b(j jVar) {
        x xVar;
        synchronized (this.f6768a) {
            if (this.f6769b != null && !this.f6770c) {
                this.f6770c = true;
                while (true) {
                    synchronized (this.f6768a) {
                        xVar = (x) this.f6769b.poll();
                        if (xVar == null) {
                            this.f6770c = false;
                            return;
                        }
                    }
                    xVar.a(jVar);
                }
            }
        }
    }
}
