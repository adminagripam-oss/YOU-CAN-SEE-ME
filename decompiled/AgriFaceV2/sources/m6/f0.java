package m6;

import m6.t0;
import y.r;
/* loaded from: classes.dex */
public class f0 {

    /* renamed from: a  reason: collision with root package name */
    private final i6.b f9280a;

    /* renamed from: b  reason: collision with root package name */
    private final v4 f9281b;

    /* renamed from: c  reason: collision with root package name */
    private t0.p f9282c;

    /* loaded from: classes.dex */
    static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9283a;

        static {
            int[] iArr = new int[r.b.values().length];
            f9283a = iArr;
            try {
                iArr[r.b.CLOSED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9283a[r.b.CLOSING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9283a[r.b.OPEN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9283a[r.b.OPENING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9283a[r.b.PENDING_OPEN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public f0(i6.b bVar, v4 v4Var) {
        this.f9280a = bVar;
        this.f9281b = v4Var;
        this.f9282c = new t0.p(bVar);
    }

    public static t0.r c(r.b bVar) {
        int i8 = a.f9283a[bVar.ordinal()];
        t0.r rVar = i8 != 1 ? i8 != 2 ? i8 != 3 ? i8 != 4 ? i8 != 5 ? null : t0.r.PENDING_OPEN : t0.r.OPENING : t0.r.OPEN : t0.r.CLOSING : t0.r.CLOSED;
        if (rVar != null) {
            return rVar;
        }
        throw new IllegalArgumentException("The CameraState.Type passed to this method was not recognized.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void d(Void r02) {
    }

    public void b(y.r rVar, t0.r rVar2, r.a aVar, t0.p.a aVar2) {
        if (this.f9281b.e(rVar)) {
            return;
        }
        if (aVar != null) {
            new d0(this.f9280a, this.f9281b).a(aVar, Long.valueOf(aVar.d()), new t0.o.a() { // from class: m6.e0
                @Override // m6.t0.o.a
                public final void a(Object obj) {
                    f0.d((Void) obj);
                }
            });
        }
        this.f9282c.b(Long.valueOf(this.f9281b.b(rVar)), new t0.s.a().b(rVar2).a(), this.f9281b.g(aVar), aVar2);
    }
}
