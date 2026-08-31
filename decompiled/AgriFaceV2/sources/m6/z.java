package m6;

import java.util.Objects;
import m6.t0;
/* loaded from: classes.dex */
public class z implements t0.l {

    /* renamed from: a  reason: collision with root package name */
    private final i6.b f9629a;

    /* renamed from: b  reason: collision with root package name */
    private final v4 f9630b;

    /* renamed from: c  reason: collision with root package name */
    public x4 f9631c;

    public z(i6.b bVar, v4 v4Var) {
        this.f9629a = bVar;
        this.f9630b = v4Var;
        this.f9631c = new x4(bVar, v4Var);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void r(Void r02) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void s(Void r02) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void t(Void r02) {
    }

    @Override // m6.t0.l
    public Long c(Long l8) {
        Object h8 = this.f9630b.h(l8.longValue());
        Objects.requireNonNull(h8);
        androidx.lifecycle.p o8 = ((y.p) h8).o();
        this.f9631c.a(o8, t0.s0.CAMERA_STATE, new t0.o0.a() { // from class: m6.w
            @Override // m6.t0.o0.a
            public final void a(Object obj) {
                z.r((Void) obj);
            }
        });
        return this.f9630b.g(o8);
    }

    @Override // m6.t0.l
    public Long e(Long l8) {
        Object h8 = this.f9630b.h(l8.longValue());
        Objects.requireNonNull(h8);
        androidx.lifecycle.p m8 = ((y.p) h8).m();
        new x4(this.f9629a, this.f9630b).a(m8, t0.s0.ZOOM_STATE, new t0.o0.a() { // from class: m6.y
            @Override // m6.t0.o0.a
            public final void a(Object obj) {
                z.t((Void) obj);
            }
        });
        return this.f9630b.g(m8);
    }

    @Override // m6.t0.l
    public Long i(Long l8) {
        Object h8 = this.f9630b.h(l8.longValue());
        Objects.requireNonNull(h8);
        y.b0 n8 = ((y.p) h8).n();
        new n0(this.f9629a, this.f9630b).e(n8, new t0.z.a() { // from class: m6.x
            @Override // m6.t0.z.a
            public final void a(Object obj) {
                z.s((Void) obj);
            }
        });
        return this.f9630b.g(n8);
    }

    @Override // m6.t0.l
    public Long l(Long l8) {
        Object h8 = this.f9630b.h(l8.longValue());
        Objects.requireNonNull(h8);
        return Long.valueOf(((y.p) h8).c());
    }
}
