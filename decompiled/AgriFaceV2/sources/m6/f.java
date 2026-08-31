package m6;

import android.content.Context;
import java.util.Objects;
import m6.t0;
/* loaded from: classes.dex */
public class f implements t0.d {

    /* renamed from: a  reason: collision with root package name */
    private final v4 f9275a;

    /* renamed from: b  reason: collision with root package name */
    private final a f9276b;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        Context f9277a;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: m6.f$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class C0123a implements i4.a {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ t0.r1 f9278a;

            C0123a(t0.r1 r1Var) {
                this.f9278a = r1Var;
            }

            @Override // i4.a
            public void b(Throwable th) {
                this.f9278a.a(th);
            }

            @Override // i4.a
            /* renamed from: c */
            public void a(Void r22) {
                this.f9278a.success(null);
            }
        }

        public void a(x.g gVar, x.j jVar, t0.r1 r1Var) {
            if (this.f9277a == null) {
                throw new IllegalStateException("Context must be set to add capture request options.");
            }
            i4.b.a(gVar.g(jVar), new C0123a(r1Var), androidx.core.content.a.g(this.f9277a));
        }

        public x.g b(y.k kVar) {
            return x.g.n(kVar);
        }
    }

    public f(v4 v4Var, Context context) {
        this(v4Var, new a(), context);
    }

    private x.g i(Long l8) {
        x.g gVar = (x.g) this.f9275a.h(l8.longValue());
        Objects.requireNonNull(gVar);
        return gVar;
    }

    @Override // m6.t0.d
    public void b(Long l8, Long l9) {
        v4 v4Var = this.f9275a;
        a aVar = this.f9276b;
        y.k kVar = (y.k) v4Var.h(l9.longValue());
        Objects.requireNonNull(kVar);
        v4Var.a(aVar.b(kVar), l8.longValue());
    }

    @Override // m6.t0.d
    public void h(Long l8, Long l9, t0.r1 r1Var) {
        a aVar = this.f9276b;
        x.g i8 = i(l8);
        x.j jVar = (x.j) this.f9275a.h(l9.longValue());
        Objects.requireNonNull(jVar);
        aVar.a(i8, jVar, r1Var);
    }

    public void j(Context context) {
        this.f9276b.f9277a = context;
    }

    f(v4 v4Var, a aVar, Context context) {
        this.f9275a = v4Var;
        this.f9276b = aVar;
        aVar.f9277a = context;
    }
}
