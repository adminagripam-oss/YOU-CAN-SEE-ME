package m5;

import java.lang.ref.ReferenceQueue;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private final ReferenceQueue f9137a = new ReferenceQueue();

    /* renamed from: b  reason: collision with root package name */
    private final Set f9138b = Collections.synchronizedSet(new HashSet());

    /* renamed from: m5.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0122a {
        void a();
    }

    private a() {
    }

    public static a a() {
        a aVar = new a();
        aVar.b(aVar, new Runnable() { // from class: m5.o
            @Override // java.lang.Runnable
            public final void run() {
            }
        });
        final ReferenceQueue referenceQueue = aVar.f9137a;
        final Set set = aVar.f9138b;
        Thread thread = new Thread(new Runnable() { // from class: m5.p
            @Override // java.lang.Runnable
            public final void run() {
                ReferenceQueue referenceQueue2 = referenceQueue;
                while (!set.isEmpty()) {
                    try {
                        ((r) referenceQueue2.remove()).a();
                    } catch (InterruptedException unused) {
                    }
                }
            }
        }, "MlKitCleaner");
        thread.setDaemon(true);
        thread.start();
        return aVar;
    }

    public InterfaceC0122a b(Object obj, Runnable runnable) {
        r rVar = new r(obj, this.f9137a, this.f9138b, runnable, null);
        this.f9138b.add(rVar);
        return rVar;
    }
}
