package m6;

import java.util.Objects;
import m6.t0;
/* loaded from: classes.dex */
public class a5 implements t0.q0 {

    /* renamed from: a  reason: collision with root package name */
    private final i6.b f9223a;

    /* renamed from: b  reason: collision with root package name */
    private final v4 f9224b;

    /* renamed from: c  reason: collision with root package name */
    private androidx.lifecycle.m f9225c;

    /* loaded from: classes.dex */
    static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9226a;

        static {
            int[] iArr = new int[t0.s0.values().length];
            f9226a = iArr;
            try {
                iArr[t0.s0.CAMERA_STATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9226a[t0.s0.ZOOM_STATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public a5(i6.b bVar, v4 v4Var) {
        this.f9223a = bVar;
        this.f9224b = v4Var;
    }

    private Long n(y.r rVar) {
        new f0(this.f9223a, this.f9224b).b(rVar, f0.c(rVar.d()), rVar.c(), new t0.p.a() { // from class: m6.z4
            @Override // m6.t0.p.a
            public final void a(Object obj) {
                a5.q((Void) obj);
            }
        });
        return this.f9224b.g(rVar);
    }

    private Long o(y.j2 j2Var) {
        new o6(this.f9223a, this.f9224b).e(j2Var, new t0.c2.a() { // from class: m6.y4
            @Override // m6.t0.c2.a
            public final void a(Object obj) {
                a5.r((Void) obj);
            }
        });
        return this.f9224b.g(j2Var);
    }

    private androidx.lifecycle.p p(Long l8) {
        androidx.lifecycle.p pVar = (androidx.lifecycle.p) this.f9224b.h(l8.longValue());
        Objects.requireNonNull(pVar);
        return pVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void q(Void r02) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void r(Void r02) {
    }

    @Override // m6.t0.q0
    public Long b(Long l8, t0.C0128t0 c0128t0) {
        Object e8 = p(l8).e();
        if (e8 == null) {
            return null;
        }
        int i8 = a.f9226a[c0128t0.b().ordinal()];
        if (i8 != 1) {
            if (i8 == 2) {
                return o((y.j2) e8);
            }
            throw new IllegalArgumentException("The type of LiveData whose value was requested is not supported.");
        }
        return n((y.r) e8);
    }

    @Override // m6.t0.q0
    public void f(Long l8, Long l9) {
        if (this.f9225c == null) {
            throw new IllegalStateException("LifecycleOwner must be set to observe a LiveData instance.");
        }
        androidx.lifecycle.p p8 = p(l8);
        androidx.lifecycle.m mVar = this.f9225c;
        androidx.lifecycle.t tVar = (androidx.lifecycle.t) this.f9224b.h(l9.longValue());
        Objects.requireNonNull(tVar);
        p8.h(mVar, tVar);
    }

    @Override // m6.t0.q0
    public void g(Long l8) {
        if (this.f9225c == null) {
            throw new IllegalStateException("LifecycleOwner must be set to remove LiveData observers.");
        }
        p(l8).n(this.f9225c);
    }

    public void s(androidx.lifecycle.m mVar) {
        this.f9225c = mVar;
    }
}
