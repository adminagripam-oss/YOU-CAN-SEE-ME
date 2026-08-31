package t;

import java.util.Objects;
import t.k;
/* loaded from: classes.dex */
abstract class p implements k.a {

    /* renamed from: a  reason: collision with root package name */
    final Object f11619a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public p(Object obj) {
        this.f11619a = obj;
    }

    @Override // t.k.a
    public void a(long j8) {
    }

    public boolean equals(Object obj) {
        if (obj instanceof p) {
            return Objects.equals(this.f11619a, ((p) obj).f11619a);
        }
        return false;
    }

    @Override // t.k.a
    public void h(int i8) {
    }

    public int hashCode() {
        return this.f11619a.hashCode();
    }
}
