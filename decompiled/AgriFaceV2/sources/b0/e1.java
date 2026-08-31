package b0;

import androidx.concurrent.futures.c;
import b0.b1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeoutException;
/* loaded from: classes.dex */
public abstract class e1 {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements f0.c {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ boolean f4173a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ c.a f4174b;

        a(boolean z7, c.a aVar) {
            this.f4173a = z7;
            this.f4174b = aVar;
        }

        @Override // f0.c
        public void b(Throwable th) {
            if (th instanceof TimeoutException) {
                this.f4174b.f(th);
            } else {
                this.f4174b.c(Collections.emptyList());
            }
        }

        @Override // f0.c
        /* renamed from: c */
        public void a(List list) {
            g1.e.j(list);
            ArrayList arrayList = new ArrayList(list);
            if (this.f4173a) {
                arrayList.removeAll(Collections.singleton(null));
            }
            this.f4174b.c(arrayList);
        }
    }

    public static void c(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((b1) it.next()).e();
        }
    }

    public static void d(List list) {
        if (list.isEmpty()) {
            return;
        }
        int i8 = 0;
        do {
            try {
                ((b1) list.get(i8)).l();
                i8++;
            } catch (b1.a e8) {
                for (int i9 = i8 - 1; i9 >= 0; i9--) {
                    ((b1) list.get(i9)).e();
                }
                throw e8;
            }
        } while (i8 < list.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object f(final i4.d dVar, Executor executor, boolean z7, Collection collection, c.a aVar) {
        aVar.a(new Runnable() { // from class: b0.d1
            @Override // java.lang.Runnable
            public final void run() {
                i4.d.this.cancel(true);
            }
        }, executor);
        f0.n.j(dVar, new a(z7, aVar), executor);
        return "surfaceList[" + collection + "]";
    }

    public static i4.d g(final Collection collection, final boolean z7, long j8, final Executor executor, ScheduledExecutorService scheduledExecutorService) {
        ArrayList arrayList = new ArrayList();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(f0.n.B(((b1) it.next()).j()));
        }
        final i4.d z8 = f0.n.z(j8, scheduledExecutorService, f0.n.F(arrayList));
        return androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: b0.c1
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar) {
                Object f8;
                f8 = e1.f(i4.d.this, executor, z7, collection, aVar);
                return f8;
            }
        });
    }
}
