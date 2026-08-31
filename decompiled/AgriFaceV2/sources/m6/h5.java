package m6;

import m6.h5;
import m6.t0;
/* loaded from: classes.dex */
public class h5 implements t0.x0 {

    /* renamed from: a  reason: collision with root package name */
    private final i6.b f9311a;

    /* renamed from: b  reason: collision with root package name */
    private final v4 f9312b;

    /* renamed from: c  reason: collision with root package name */
    private final b f9313c;

    /* loaded from: classes.dex */
    public static class a implements androidx.lifecycle.t {

        /* renamed from: a  reason: collision with root package name */
        private f5 f9314a;

        public a(i6.b bVar, v4 v4Var) {
            this.f9314a = new f5(bVar, v4Var);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void c(Void r02) {
        }

        @Override // androidx.lifecycle.t
        public void a(Object obj) {
            this.f9314a.g(this, obj, new t0.w0.a() { // from class: m6.g5
                @Override // m6.t0.w0.a
                public final void a(Object obj2) {
                    h5.a.c((Void) obj2);
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public static class b {
        public a a(i6.b bVar, v4 v4Var) {
            return new a(bVar, v4Var);
        }
    }

    public h5(i6.b bVar, v4 v4Var) {
        this(bVar, v4Var, new b());
    }

    @Override // m6.t0.x0
    public void b(Long l8) {
        v4 v4Var = this.f9312b;
        v4Var.a(this.f9313c.a(this.f9311a, v4Var), l8.longValue());
    }

    h5(i6.b bVar, v4 v4Var, b bVar2) {
        this.f9311a = bVar;
        this.f9312b = v4Var;
        this.f9313c = bVar2;
    }
}
