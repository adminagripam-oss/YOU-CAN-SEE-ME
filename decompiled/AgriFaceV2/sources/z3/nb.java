package z3;
/* loaded from: classes.dex */
final class nb extends rb {

    /* renamed from: a  reason: collision with root package name */
    private final String f13945a;

    /* renamed from: b  reason: collision with root package name */
    private final boolean f13946b;

    /* renamed from: c  reason: collision with root package name */
    private final int f13947c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ nb(String str, boolean z7, int i8, lb lbVar) {
        this.f13945a = str;
        this.f13946b = z7;
        this.f13947c = i8;
    }

    @Override // z3.rb
    public final int a() {
        return this.f13947c;
    }

    @Override // z3.rb
    public final String b() {
        return this.f13945a;
    }

    @Override // z3.rb
    public final boolean c() {
        return this.f13946b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof rb) {
            rb rbVar = (rb) obj;
            if (this.f13945a.equals(rbVar.b()) && this.f13946b == rbVar.c() && this.f13947c == rbVar.a()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((this.f13945a.hashCode() ^ 1000003) * 1000003) ^ (true != this.f13946b ? 1237 : 1231)) * 1000003) ^ this.f13947c;
    }

    public final String toString() {
        String str = this.f13945a;
        boolean z7 = this.f13946b;
        int i8 = this.f13947c;
        return "MLKitLoggingOptions{libraryName=" + str + ", enableFirelog=" + z7 + ", firelogEventType=" + i8 + "}";
    }
}
