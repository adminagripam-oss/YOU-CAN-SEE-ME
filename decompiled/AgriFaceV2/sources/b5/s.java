package b5;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
class s implements g5.c, g5.b {

    /* renamed from: a  reason: collision with root package name */
    private final Map f4676a = new HashMap();

    /* renamed from: b  reason: collision with root package name */
    private Queue f4677b = new ArrayDeque();

    /* renamed from: c  reason: collision with root package name */
    private final Executor f4678c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public s(Executor executor) {
        this.f4678c = executor;
    }

    private synchronized Set b(g5.a aVar) {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void c(Map.Entry entry, g5.a aVar) {
        androidx.appcompat.app.w.a(entry.getKey());
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        Queue<Object> queue;
        synchronized (this) {
            queue = this.f4677b;
            if (queue != null) {
                this.f4677b = null;
            } else {
                queue = null;
            }
        }
        if (queue != null) {
            for (Object obj : queue) {
                androidx.appcompat.app.w.a(obj);
                d(null);
            }
        }
    }

    public void d(g5.a aVar) {
        a0.b(aVar);
        synchronized (this) {
            Queue queue = this.f4677b;
            if (queue != null) {
                queue.add(aVar);
                return;
            }
            for (Map.Entry entry : b(aVar)) {
                ((Executor) entry.getValue()).execute(r.a(entry, aVar));
            }
        }
    }
}
