package m6;

import androidx.camera.core.f;
import m6.d;
import m6.t0;
/* loaded from: classes.dex */
public class d implements t0.b {

    /* renamed from: a  reason: collision with root package name */
    private final i6.b f9252a;

    /* renamed from: b  reason: collision with root package name */
    private final v4 f9253b;

    /* renamed from: c  reason: collision with root package name */
    private final b f9254c;

    /* loaded from: classes.dex */
    public static class a implements f.a {

        /* renamed from: a  reason: collision with root package name */
        private i6.b f9255a;

        /* renamed from: b  reason: collision with root package name */
        private v4 f9256b;

        /* renamed from: c  reason: collision with root package name */
        private m6.a f9257c;

        /* renamed from: d  reason: collision with root package name */
        public r4 f9258d;

        public a(i6.b bVar, v4 v4Var) {
            this.f9255a = bVar;
            this.f9256b = v4Var;
            this.f9257c = new m6.a(bVar, v4Var);
            this.f9258d = new r4(bVar, v4Var);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void e(Void r02) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void f(Void r02) {
        }

        @Override // androidx.camera.core.f.a
        public void a(androidx.camera.core.n nVar) {
            this.f9258d.a(nVar, Long.valueOf(nVar.e()), Long.valueOf(nVar.getHeight()), Long.valueOf(nVar.getWidth()), new t0.j0.a() { // from class: m6.b
                @Override // m6.t0.j0.a
                public final void a(Object obj) {
                    d.a.e((Void) obj);
                }
            });
            this.f9257c.a(this, nVar, new t0.a.InterfaceC0127a() { // from class: m6.c
                @Override // m6.t0.a.InterfaceC0127a
                public final void a(Object obj) {
                    d.a.f((Void) obj);
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

    public d(i6.b bVar, v4 v4Var) {
        this(bVar, v4Var, new b());
    }

    @Override // m6.t0.b
    public void b(Long l8) {
        v4 v4Var = this.f9253b;
        v4Var.a(this.f9254c.a(this.f9252a, v4Var), l8.longValue());
    }

    d(i6.b bVar, v4 v4Var, b bVar2) {
        this.f9252a = bVar;
        this.f9253b = v4Var;
        this.f9254c = bVar2;
    }
}
