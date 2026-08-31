package m6;

import android.util.Log;
import m6.t0;
/* loaded from: classes.dex */
public class f5 {

    /* renamed from: a  reason: collision with root package name */
    private final i6.b f9288a;

    /* renamed from: b  reason: collision with root package name */
    private final v4 f9289b;

    /* renamed from: c  reason: collision with root package name */
    private t0.w0 f9290c;

    /* renamed from: d  reason: collision with root package name */
    public f0 f9291d;

    /* renamed from: e  reason: collision with root package name */
    public o6 f9292e;

    public f5(i6.b bVar, v4 v4Var) {
        this.f9288a = bVar;
        this.f9289b = v4Var;
        this.f9290c = new t0.w0(bVar);
    }

    private void c(y.r rVar) {
        if (this.f9291d == null) {
            this.f9291d = new f0(this.f9288a, this.f9289b);
        }
        this.f9291d.b(rVar, f0.c(rVar.d()), rVar.c(), new t0.p.a() { // from class: m6.e5
            @Override // m6.t0.p.a
            public final void a(Object obj) {
                f5.e((Void) obj);
            }
        });
    }

    private void d(y.j2 j2Var) {
        if (this.f9292e == null) {
            this.f9292e = new o6(this.f9288a, this.f9289b);
        }
        this.f9292e.e(j2Var, new t0.c2.a() { // from class: m6.d5
            @Override // m6.t0.c2.a
            public final void a(Object obj) {
                f5.f((Void) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void e(Void r02) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void f(Void r02) {
    }

    public void g(androidx.lifecycle.t tVar, Object obj, t0.w0.a aVar) {
        if (obj instanceof y.r) {
            c((y.r) obj);
        } else if (!(obj instanceof y.j2)) {
            throw new UnsupportedOperationException("The type of value that was observed is not handled by this plugin.");
        } else {
            d((y.j2) obj);
        }
        Long g8 = this.f9289b.g(tVar);
        if (g8 == null) {
            Log.e("ObserverFlutterApi", "The Observer that received a callback has been garbage collected. Please create a new instance to receive any further data changes.");
        } else {
            this.f9290c.d(g8, this.f9289b.g(obj), aVar);
        }
    }
}
