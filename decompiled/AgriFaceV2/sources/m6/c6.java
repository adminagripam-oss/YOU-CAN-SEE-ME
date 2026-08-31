package m6;

import android.content.Context;
import java.io.File;
import java.util.Objects;
import m6.t0;
import r0.q;
import r0.q0;
/* loaded from: classes.dex */
public class c6 implements t0.i1 {

    /* renamed from: a  reason: collision with root package name */
    private final i6.b f9247a;

    /* renamed from: b  reason: collision with root package name */
    private final v4 f9248b;

    /* renamed from: c  reason: collision with root package name */
    private Context f9249c;

    /* renamed from: d  reason: collision with root package name */
    public g0 f9250d = new g0();

    /* renamed from: e  reason: collision with root package name */
    public i5 f9251e;

    public c6(i6.b bVar, v4 v4Var, Context context) {
        this.f9247a = bVar;
        this.f9248b = v4Var;
        this.f9249c = context;
        this.f9251e = new i5(bVar, v4Var);
    }

    private r0.q0 p(Long l8) {
        Object h8 = this.f9248b.h(l8.longValue());
        Objects.requireNonNull(h8);
        return (r0.q0) h8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void q(Void r02) {
    }

    @Override // m6.t0.i1
    public void c(Long l8, Long l9, Long l10, Long l11) {
        if (this.f9249c == null) {
            throw new IllegalStateException("Context must be set to create Recorder instance.");
        }
        q0.i h8 = this.f9250d.h();
        if (l9 != null) {
            h8.h(l9.intValue());
        }
        if (l10 != null) {
            h8.k(l10.intValue());
        }
        if (l11 != null) {
            r0.y yVar = (r0.y) this.f9248b.h(l11.longValue());
            Objects.requireNonNull(yVar);
            h8.j(yVar);
        }
        this.f9248b.a(h8.i(androidx.core.content.a.g(this.f9249c)).d(), l8.longValue());
    }

    @Override // m6.t0.i1
    public Long d(Long l8) {
        return Long.valueOf(p(l8).K());
    }

    @Override // m6.t0.i1
    public Long f(Long l8, String str) {
        if (this.f9249c != null) {
            r0.u t02 = p(l8).t0(this.f9249c, new q.a(r(str)).a());
            if (androidx.core.content.a.a(this.f9249c, "android.permission.RECORD_AUDIO") == 0) {
                t02.i();
            }
            this.f9251e.h(t02, new t0.y0.a() { // from class: m6.b6
                @Override // m6.t0.y0.a
                public final void a(Object obj) {
                    c6.q((Void) obj);
                }
            });
            Long g8 = this.f9248b.g(t02);
            Objects.requireNonNull(g8);
            return g8;
        }
        throw new IllegalStateException("Context must be set to prepare recording.");
    }

    @Override // m6.t0.i1
    public Long m(Long l8) {
        return Long.valueOf(p(l8).G());
    }

    public File r(String str) {
        try {
            return new File(str);
        } catch (NullPointerException | SecurityException e8) {
            throw new RuntimeException(e8);
        }
    }

    public void s(Context context) {
        this.f9249c = context;
    }
}
