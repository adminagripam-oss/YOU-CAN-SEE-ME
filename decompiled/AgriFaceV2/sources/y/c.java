package y;

import y.r;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class c extends r.a {

    /* renamed from: a  reason: collision with root package name */
    private final int f12744a;

    /* renamed from: b  reason: collision with root package name */
    private final Throwable f12745b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(int i8, Throwable th) {
        this.f12744a = i8;
        this.f12745b = th;
    }

    @Override // y.r.a
    public Throwable c() {
        return this.f12745b;
    }

    @Override // y.r.a
    public int d() {
        return this.f12744a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof r.a) {
            r.a aVar = (r.a) obj;
            if (this.f12744a == aVar.d()) {
                Throwable th = this.f12745b;
                Throwable c8 = aVar.c();
                if (th == null) {
                    if (c8 == null) {
                        return true;
                    }
                } else if (th.equals(c8)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int i8 = (this.f12744a ^ 1000003) * 1000003;
        Throwable th = this.f12745b;
        return i8 ^ (th == null ? 0 : th.hashCode());
    }

    public String toString() {
        return "StateError{code=" + this.f12744a + ", cause=" + this.f12745b + "}";
    }
}
