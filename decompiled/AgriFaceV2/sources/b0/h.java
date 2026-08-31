package b0;
/* loaded from: classes.dex */
final class h extends l1 {

    /* renamed from: a  reason: collision with root package name */
    private final Object f4207a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(Object obj) {
        if (obj == null) {
            throw new NullPointerException("Null value");
        }
        this.f4207a = obj;
    }

    @Override // b0.l1
    public Object b() {
        return this.f4207a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof l1) {
            return this.f4207a.equals(((l1) obj).b());
        }
        return false;
    }

    public int hashCode() {
        return this.f4207a.hashCode() ^ 1000003;
    }

    public String toString() {
        return "Identifier{value=" + this.f4207a + "}";
    }
}
