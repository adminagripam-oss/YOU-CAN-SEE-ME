package m6;

import android.app.Activity;
import h6.o;
import m6.i0;
import m6.t0;
/* loaded from: classes.dex */
public class m0 implements t0.x {

    /* renamed from: a  reason: collision with root package name */
    private final i6.b f9359a;

    /* renamed from: b  reason: collision with root package name */
    private final v4 f9360b;

    /* renamed from: c  reason: collision with root package name */
    public g0 f9361c = new g0();

    /* renamed from: d  reason: collision with root package name */
    public i0 f9362d;

    /* renamed from: e  reason: collision with root package name */
    public j0 f9363e;

    /* renamed from: f  reason: collision with root package name */
    private Activity f9364f;

    public m0(i6.b bVar, v4 v4Var) {
        this.f9359a = bVar;
        this.f9360b = v4Var;
        this.f9363e = new j0(bVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void q(Void r02) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void r(o.f fVar) {
        this.f9363e.e(s(fVar), new t0.w.a() { // from class: m6.l0
            @Override // m6.t0.w.a
            public final void a(Object obj) {
                m0.q((Void) obj);
            }
        });
    }

    @Override // m6.t0.x
    public void b() {
        i0 i0Var = this.f9362d;
        if (i0Var != null) {
            i0Var.g();
        }
    }

    @Override // m6.t0.x
    public Long d() {
        try {
            return Long.valueOf(this.f9362d.a());
        } catch (NullPointerException unused) {
            throw new IllegalStateException("startListeningForDeviceOrientationChange must first be called to subscribe to device orientation changes in order to retrieve default rotation.");
        }
    }

    @Override // m6.t0.x
    public String g() {
        return s(this.f9362d.c());
    }

    @Override // m6.t0.x
    public void k(Boolean bool, Long l8) {
        Activity activity = this.f9364f;
        if (activity == null) {
            throw new IllegalStateException("Activity must be set to start listening for device orientation changes.");
        }
        i0 c8 = this.f9361c.c(activity, bool, l8.intValue(), new i0.b() { // from class: m6.k0
            @Override // m6.i0.b
            public final void a(o.f fVar) {
                m0.this.r(fVar);
            }
        });
        this.f9362d = c8;
        c8.f();
    }

    String s(o.f fVar) {
        return fVar.toString();
    }

    public void t(Activity activity) {
        this.f9364f = activity;
    }
}
