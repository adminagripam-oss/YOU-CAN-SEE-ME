package t2;

import java.util.List;
/* loaded from: classes.dex */
final class d extends j {

    /* renamed from: a  reason: collision with root package name */
    private final List f11716a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(List list) {
        if (list == null) {
            throw new NullPointerException("Null logRequests");
        }
        this.f11716a = list;
    }

    @Override // t2.j
    public List c() {
        return this.f11716a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof j) {
            return this.f11716a.equals(((j) obj).c());
        }
        return false;
    }

    public int hashCode() {
        return this.f11716a.hashCode() ^ 1000003;
    }

    public String toString() {
        return "BatchedLogRequest{logRequests=" + this.f11716a + "}";
    }
}
