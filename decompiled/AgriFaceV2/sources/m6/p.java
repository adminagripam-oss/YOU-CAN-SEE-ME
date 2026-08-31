package m6;

import android.content.Context;
import java.util.Objects;
import m6.p;
import m6.t0;
import y.k;
/* loaded from: classes.dex */
public class p implements t0.h {

    /* renamed from: a  reason: collision with root package name */
    private final v4 f9406a;

    /* renamed from: b  reason: collision with root package name */
    private final a f9407b;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        Context f9408a;

        /* renamed from: b  reason: collision with root package name */
        i6.b f9409b;

        /* renamed from: c  reason: collision with root package name */
        v4 f9410c;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: m6.p$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class C0125a implements i4.a {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ t0.r1 f9411a;

            C0125a(t0.r1 r1Var) {
                this.f9411a = r1Var;
            }

            @Override // i4.a
            public void b(Throwable th) {
                this.f9411a.a(th);
            }

            @Override // i4.a
            /* renamed from: c */
            public void a(Void r22) {
                this.f9411a.success(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class b implements i4.a {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ t0.r1 f9413a;

            b(t0.r1 r1Var) {
                this.f9413a = r1Var;
            }

            @Override // i4.a
            public void b(Throwable th) {
                if (th instanceof k.a) {
                    this.f9413a.success(null);
                } else {
                    this.f9413a.a(th);
                }
            }

            @Override // i4.a
            /* renamed from: c */
            public void a(Void r22) {
                this.f9413a.success(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class c implements i4.a {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ t0.r1 f9415a;

            c(t0.r1 r1Var) {
                this.f9415a = r1Var;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static /* synthetic */ void d(Void r02) {
            }

            @Override // i4.a
            public void b(Throwable th) {
                if (th instanceof k.a) {
                    this.f9415a.success(null);
                } else {
                    this.f9415a.a(th);
                }
            }

            @Override // i4.a
            /* renamed from: e */
            public void a(y.e0 e0Var) {
                a aVar = a.this;
                new q0(aVar.f9409b, aVar.f9410c).a(e0Var, new t0.f0.a() { // from class: m6.q
                    @Override // m6.t0.f0.a
                    public final void a(Object obj) {
                        p.a.c.d((Void) obj);
                    }
                });
                this.f9415a.success(a.this.f9410c.g(e0Var));
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class d implements i4.a {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ t0.r1 f9417a;

            d(t0.r1 r1Var) {
                this.f9417a = r1Var;
            }

            @Override // i4.a
            public void b(Throwable th) {
                this.f9417a.a(th);
            }

            @Override // i4.a
            /* renamed from: c */
            public void a(Void r22) {
                this.f9417a.success(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class e implements i4.a {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ t0.r1 f9419a;

            e(t0.r1 r1Var) {
                this.f9419a = r1Var;
            }

            @Override // i4.a
            public void b(Throwable th) {
                if (th instanceof k.a) {
                    this.f9419a.success(null);
                } else {
                    this.f9419a.a(th);
                }
            }

            @Override // i4.a
            /* renamed from: c */
            public void a(Integer num) {
                this.f9419a.success(Long.valueOf(num.longValue()));
            }
        }

        public void a(y.k kVar, t0.r1 r1Var) {
            i4.b.a(kVar.i(), new d(r1Var), androidx.core.content.a.g(this.f9408a));
        }

        public void b(y.k kVar, Boolean bool, t0.r1 r1Var) {
            if (this.f9408a == null) {
                throw new IllegalStateException("Context must be set to enable the torch.");
            }
            i4.b.a(kVar.q(bool.booleanValue()), new C0125a(r1Var), androidx.core.content.a.g(this.f9408a));
        }

        public void c(y.k kVar, Long l8, t0.r1 r1Var) {
            i4.b.a(kVar.g(l8.intValue()), new e(r1Var), androidx.core.content.a.g(this.f9408a));
        }

        public void d(y.k kVar, Double d8, t0.r1 r1Var) {
            if (this.f9408a == null) {
                throw new IllegalStateException("Context must be set to set zoom ratio.");
            }
            i4.b.a(kVar.k(d8.floatValue()), new b(r1Var), androidx.core.content.a.g(this.f9408a));
        }

        public void e(y.k kVar, y.d0 d0Var, t0.r1 r1Var) {
            if (this.f9408a == null) {
                throw new IllegalStateException("Context must be set to set zoom ratio.");
            }
            i4.b.a(kVar.o(d0Var), new c(r1Var), androidx.core.content.a.g(this.f9408a));
        }
    }

    public p(i6.b bVar, v4 v4Var, Context context) {
        this(bVar, v4Var, new a(), context);
    }

    private y.k r(Long l8) {
        y.k kVar = (y.k) this.f9406a.h(l8.longValue());
        Objects.requireNonNull(kVar);
        return kVar;
    }

    @Override // m6.t0.h
    public void e(Long l8, Double d8, t0.r1 r1Var) {
        this.f9407b.d(r(l8), d8, r1Var);
    }

    @Override // m6.t0.h
    public void g(Long l8, t0.r1 r1Var) {
        this.f9407b.a(r(l8), r1Var);
    }

    @Override // m6.t0.h
    public void i(Long l8, Long l9, t0.r1 r1Var) {
        a aVar = this.f9407b;
        y.k r8 = r(l8);
        y.d0 d0Var = (y.d0) this.f9406a.h(l9.longValue());
        Objects.requireNonNull(d0Var);
        aVar.e(r8, d0Var, r1Var);
    }

    @Override // m6.t0.h
    public void p(Long l8, Long l9, t0.r1 r1Var) {
        this.f9407b.c(r(l8), l9, r1Var);
    }

    @Override // m6.t0.h
    public void q(Long l8, Boolean bool, t0.r1 r1Var) {
        this.f9407b.b(r(l8), bool, r1Var);
    }

    public void s(Context context) {
        this.f9407b.f9408a = context;
    }

    p(i6.b bVar, v4 v4Var, a aVar, Context context) {
        this.f9406a = v4Var;
        this.f9407b = aVar;
        aVar.f9408a = context;
        aVar.f9410c = v4Var;
        aVar.f9409b = bVar;
    }
}
