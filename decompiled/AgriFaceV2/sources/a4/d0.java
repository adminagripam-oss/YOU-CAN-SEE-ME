package a4;

import java.util.Map;
/* loaded from: classes.dex */
final class d0 extends r {

    /* renamed from: e  reason: collision with root package name */
    private final Object f350e;

    /* renamed from: f  reason: collision with root package name */
    private int f351f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ f0 f352g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d0(f0 f0Var, int i8) {
        this.f352g = f0Var;
        this.f350e = f0.i(f0Var, i8);
        this.f351f = i8;
    }

    private final void a() {
        int x7;
        int i8 = this.f351f;
        if (i8 == -1 || i8 >= this.f352g.size() || !ze.a(this.f350e, f0.i(this.f352g, this.f351f))) {
            x7 = this.f352g.x(this.f350e);
            this.f351f = x7;
        }
    }

    @Override // a4.r, java.util.Map.Entry
    public final Object getKey() {
        return this.f350e;
    }

    @Override // a4.r, java.util.Map.Entry
    public final Object getValue() {
        Map n8 = this.f352g.n();
        if (n8 != null) {
            return n8.get(this.f350e);
        }
        a();
        int i8 = this.f351f;
        if (i8 == -1) {
            return null;
        }
        return f0.l(this.f352g, i8);
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        Map n8 = this.f352g.n();
        if (n8 != null) {
            return n8.put(this.f350e, obj);
        }
        a();
        int i8 = this.f351f;
        if (i8 == -1) {
            this.f352g.put(this.f350e, obj);
            return null;
        }
        Object l8 = f0.l(this.f352g, i8);
        f0.o(this.f352g, this.f351f, obj);
        return l8;
    }
}
