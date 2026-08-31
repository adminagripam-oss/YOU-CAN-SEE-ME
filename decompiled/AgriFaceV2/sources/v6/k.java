package v6;

import d7.s;
/* loaded from: classes.dex */
public abstract class k extends d implements d7.h {

    /* renamed from: h  reason: collision with root package name */
    private final int f12441h;

    public k(int i8, t6.d dVar) {
        super(dVar);
        this.f12441h = i8;
    }

    @Override // d7.h
    public int c() {
        return this.f12441h;
    }

    @Override // v6.a
    public String toString() {
        if (h() == null) {
            String e8 = s.e(this);
            d7.k.d(e8, "renderLambdaToString(...)");
            return e8;
        }
        return super.toString();
    }
}
