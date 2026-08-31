package d7;

import java.io.Serializable;
/* loaded from: classes.dex */
public abstract class l implements h, Serializable {

    /* renamed from: e  reason: collision with root package name */
    private final int f6479e;

    public l(int i8) {
        this.f6479e = i8;
    }

    @Override // d7.h
    public int c() {
        return this.f6479e;
    }

    public String toString() {
        String f8 = s.f(this);
        k.d(f8, "renderLambdaToString(...)");
        return f8;
    }
}
