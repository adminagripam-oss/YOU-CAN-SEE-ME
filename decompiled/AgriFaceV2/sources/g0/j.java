package g0;

import r6.r;
import y.f1;
import y.u0;
/* loaded from: classes.dex */
public final class j implements u0.i {

    /* renamed from: e  reason: collision with root package name */
    public static final a f7085e = new a(null);

    /* renamed from: a  reason: collision with root package name */
    private final u0.i f7086a;

    /* renamed from: b  reason: collision with root package name */
    private final Object f7087b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f7088c;

    /* renamed from: d  reason: collision with root package name */
    private u0.j f7089d;

    /* loaded from: classes.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(d7.g gVar) {
            this();
        }

        public final j a(u0.i iVar) {
            return new j(iVar, null);
        }
    }

    private j(u0.i iVar) {
        this.f7086a = iVar;
        this.f7087b = new Object();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void c(j jVar) {
        d7.k.e(jVar, "this$0");
        synchronized (jVar.f7087b) {
            if (jVar.f7089d == null) {
                f1.l("ScreenFlashWrapper", "apply: pendingListener is null!");
            }
            jVar.e();
            r rVar = r.f11372a;
        }
    }

    private final void d() {
        r rVar;
        synchronized (this.f7087b) {
            if (this.f7088c) {
                u0.i iVar = this.f7086a;
                if (iVar != null) {
                    iVar.clear();
                    rVar = r.f11372a;
                } else {
                    rVar = null;
                }
                if (rVar == null) {
                    f1.c("ScreenFlashWrapper", "completePendingScreenFlashClear: screenFlash is null!");
                }
            } else {
                f1.l("ScreenFlashWrapper", "completePendingScreenFlashClear: none pending!");
            }
            this.f7088c = false;
            r rVar2 = r.f11372a;
        }
    }

    private final void e() {
        synchronized (this.f7087b) {
            u0.j jVar = this.f7089d;
            if (jVar != null) {
                jVar.a();
            }
            this.f7089d = null;
            r rVar = r.f11372a;
        }
    }

    public static final j g(u0.i iVar) {
        return f7085e.a(iVar);
    }

    @Override // y.u0.i
    public void a(long j8, u0.j jVar) {
        r rVar;
        d7.k.e(jVar, "screenFlashListener");
        synchronized (this.f7087b) {
            this.f7088c = true;
            this.f7089d = jVar;
            r rVar2 = r.f11372a;
        }
        u0.i iVar = this.f7086a;
        if (iVar != null) {
            iVar.a(j8, new u0.j() { // from class: g0.i
                @Override // y.u0.j
                public final void a() {
                    j.c(j.this);
                }
            });
            rVar = r.f11372a;
        } else {
            rVar = null;
        }
        if (rVar == null) {
            f1.c("ScreenFlashWrapper", "apply: screenFlash is null!");
            e();
        }
    }

    @Override // y.u0.i
    public void clear() {
        d();
    }

    public final void f() {
        e();
        d();
    }

    public final u0.i h() {
        return this.f7086a;
    }

    public /* synthetic */ j(u0.i iVar, d7.g gVar) {
        this(iVar);
    }
}
