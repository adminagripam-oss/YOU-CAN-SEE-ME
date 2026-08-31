package m6;

import android.content.Context;
import androidx.camera.core.f;
import java.util.Objects;
import java.util.concurrent.Executor;
import m6.t0;
/* loaded from: classes.dex */
public class p4 implements t0.h0 {

    /* renamed from: a  reason: collision with root package name */
    private v4 f9426a;

    /* renamed from: b  reason: collision with root package name */
    private i6.b f9427b;

    /* renamed from: c  reason: collision with root package name */
    private Context f9428c;

    /* renamed from: d  reason: collision with root package name */
    public g0 f9429d = new g0();

    public p4(i6.b bVar, v4 v4Var, Context context) {
        this.f9427b = bVar;
        this.f9426a = v4Var;
        this.f9428c = context;
    }

    private androidx.camera.core.f o(Long l8) {
        androidx.camera.core.f fVar = (androidx.camera.core.f) this.f9426a.h(l8.longValue());
        Objects.requireNonNull(fVar);
        return fVar;
    }

    @Override // m6.t0.h0
    public void b(Long l8, Long l9) {
        o(l8).t0(l9.intValue());
    }

    @Override // m6.t0.h0
    public void c(Long l8, Long l9, Long l10) {
        f.c d8 = this.f9429d.d();
        if (l9 != null) {
            d8.a(l9.intValue());
        }
        if (l10 != null) {
            o0.c cVar = (o0.c) this.f9426a.h(l10.longValue());
            Objects.requireNonNull(cVar);
            d8.k(cVar);
        }
        this.f9426a.a(d8.e(), l8.longValue());
    }

    @Override // m6.t0.h0
    public void e(Long l8) {
        Object h8 = this.f9426a.h(l8.longValue());
        Objects.requireNonNull(h8);
        ((androidx.camera.core.f) h8).f0();
        this.f9426a.m(3000L);
    }

    @Override // m6.t0.h0
    public void m(Long l8, Long l9) {
        if (this.f9428c == null) {
            throw new IllegalStateException("Context must be set to set an Analyzer.");
        }
        this.f9426a.m(1000L);
        this.f9426a.k();
        androidx.camera.core.f o8 = o(l8);
        Executor g8 = androidx.core.content.a.g(this.f9428c);
        f.a aVar = (f.a) this.f9426a.h(l9.longValue());
        Objects.requireNonNull(aVar);
        o8.s0(g8, aVar);
    }

    public void p(Context context) {
        this.f9428c = context;
    }
}
