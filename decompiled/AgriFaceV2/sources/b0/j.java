package b0;

import b0.t2;
/* loaded from: classes.dex */
final class j extends t2.a {

    /* renamed from: a  reason: collision with root package name */
    private final Throwable f4246a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(Throwable th) {
        if (th == null) {
            throw new NullPointerException("Null error");
        }
        this.f4246a = th;
    }

    @Override // b0.t2.a
    public Throwable a() {
        return this.f4246a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof t2.a) {
            return this.f4246a.equals(((t2.a) obj).a());
        }
        return false;
    }

    public int hashCode() {
        return this.f4246a.hashCode() ^ 1000003;
    }

    public String toString() {
        return "ErrorWrapper{error=" + this.f4246a + "}";
    }
}
