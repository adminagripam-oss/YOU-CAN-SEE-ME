package y3;
/* loaded from: classes.dex */
final class a0 extends e0 {

    /* renamed from: a  reason: collision with root package name */
    private final String f13213a;

    /* renamed from: b  reason: collision with root package name */
    private final boolean f13214b;

    /* renamed from: c  reason: collision with root package name */
    private final int f13215c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ a0(String str, boolean z7, int i8, z zVar) {
        this.f13213a = str;
        this.f13214b = z7;
        this.f13215c = i8;
    }

    @Override // y3.e0
    public final int a() {
        return this.f13215c;
    }

    @Override // y3.e0
    public final String b() {
        return this.f13213a;
    }

    @Override // y3.e0
    public final boolean c() {
        return this.f13214b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof e0) {
            e0 e0Var = (e0) obj;
            if (this.f13213a.equals(e0Var.b()) && this.f13214b == e0Var.c() && this.f13215c == e0Var.a()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((this.f13213a.hashCode() ^ 1000003) * 1000003) ^ (true != this.f13214b ? 1237 : 1231)) * 1000003) ^ this.f13215c;
    }

    public final String toString() {
        return "MLKitLoggingOptions{libraryName=" + this.f13213a + ", enableFirelog=" + this.f13214b + ", firelogEventType=" + this.f13215c + "}";
    }
}
