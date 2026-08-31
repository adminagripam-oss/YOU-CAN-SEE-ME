package i5;

import k3.q;
/* loaded from: classes.dex */
public class a extends Exception {

    /* renamed from: e  reason: collision with root package name */
    private final int f7510e;

    public a(String str, int i8) {
        super(q.f(str, "Provided message must not be empty."));
        this.f7510e = i8;
    }

    public int a() {
        return this.f7510e;
    }

    public a(String str, int i8, Throwable th) {
        super(q.f(str, "Provided message must not be empty."), th);
        this.f7510e = i8;
    }
}
