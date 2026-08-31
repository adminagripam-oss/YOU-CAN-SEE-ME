package a3;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import v2.g;
/* loaded from: classes.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    private final Context f204a;

    /* renamed from: b  reason: collision with root package name */
    private final v2.e f205b;

    /* renamed from: c  reason: collision with root package name */
    private final b3.c f206c;

    /* renamed from: d  reason: collision with root package name */
    private final p f207d;

    /* renamed from: e  reason: collision with root package name */
    private final Executor f208e;

    /* renamed from: f  reason: collision with root package name */
    private final c3.b f209f;

    /* renamed from: g  reason: collision with root package name */
    private final d3.a f210g;

    public j(Context context, v2.e eVar, b3.c cVar, p pVar, Executor executor, c3.b bVar, d3.a aVar) {
        this.f204a = context;
        this.f205b = eVar;
        this.f206c = cVar;
        this.f207d = pVar;
        this.f208e = executor;
        this.f209f = bVar;
        this.f210g = aVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object c(j jVar, v2.g gVar, Iterable iterable, u2.m mVar, int i8) {
        if (gVar.c() == g.a.TRANSIENT_ERROR) {
            jVar.f206c.J(iterable);
            jVar.f207d.a(mVar, i8 + 1);
            return null;
        }
        jVar.f206c.k(iterable);
        if (gVar.c() == g.a.OK) {
            jVar.f206c.K(mVar, jVar.f210g.a() + gVar.b());
        }
        if (jVar.f206c.m(mVar)) {
            jVar.f207d.b(mVar, 1, true);
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object d(j jVar, u2.m mVar, int i8) {
        jVar.f207d.a(mVar, i8 + 1);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void e(j jVar, u2.m mVar, int i8, Runnable runnable) {
        try {
            try {
                c3.b bVar = jVar.f209f;
                b3.c cVar = jVar.f206c;
                cVar.getClass();
                bVar.b(h.b(cVar));
                if (jVar.a()) {
                    jVar.f(mVar, i8);
                } else {
                    jVar.f209f.b(i.b(jVar, mVar, i8));
                }
            } catch (c3.a unused) {
                jVar.f207d.a(mVar, i8 + 1);
            }
        } finally {
            runnable.run();
        }
    }

    boolean a() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.f204a.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    void f(u2.m mVar, int i8) {
        v2.g a8;
        v2.m a9 = this.f205b.a(mVar.b());
        Iterable<b3.i> iterable = (Iterable) this.f209f.b(f.b(this, mVar));
        if (iterable.iterator().hasNext()) {
            if (a9 == null) {
                x2.a.a("Uploader", "Unknown backend for %s, deleting event batch for it...", mVar);
                a8 = v2.g.a();
            } else {
                ArrayList arrayList = new ArrayList();
                for (b3.i iVar : iterable) {
                    arrayList.add(iVar.b());
                }
                a8 = a9.a(v2.f.a().b(arrayList).c(mVar.c()).a());
            }
            this.f209f.b(g.b(this, a8, iterable, mVar, i8));
        }
    }

    public void g(u2.m mVar, int i8, Runnable runnable) {
        this.f208e.execute(e.a(this, mVar, i8, runnable));
    }
}
