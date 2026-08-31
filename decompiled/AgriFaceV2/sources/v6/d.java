package v6;

import t6.g;
/* loaded from: classes.dex */
public abstract class d extends a {

    /* renamed from: f  reason: collision with root package name */
    private final t6.g f12433f;

    /* renamed from: g  reason: collision with root package name */
    private transient t6.d f12434g;

    public d(t6.d dVar) {
        this(dVar, dVar != null ? dVar.getContext() : null);
    }

    @Override // t6.d
    public t6.g getContext() {
        t6.g gVar = this.f12433f;
        d7.k.b(gVar);
        return gVar;
    }

    @Override // v6.a
    protected void m() {
        t6.d dVar = this.f12434g;
        if (dVar != null && dVar != this) {
            g.b c8 = getContext().c(t6.e.f11913d);
            d7.k.b(c8);
            ((t6.e) c8).j(dVar);
        }
        this.f12434g = c.f12432e;
    }

    public final t6.d n() {
        t6.d dVar = this.f12434g;
        if (dVar == null) {
            t6.e eVar = (t6.e) getContext().c(t6.e.f11913d);
            if (eVar == null || (dVar = eVar.q(this)) == null) {
                dVar = this;
            }
            this.f12434g = dVar;
        }
        return dVar;
    }

    public d(t6.d dVar, t6.g gVar) {
        super(dVar);
        this.f12433f = gVar;
    }
}
