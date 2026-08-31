package a4;
/* loaded from: classes.dex */
final class dd extends gd {

    /* renamed from: a  reason: collision with root package name */
    private final String f480a;

    /* renamed from: b  reason: collision with root package name */
    private final boolean f481b;

    /* renamed from: c  reason: collision with root package name */
    private final int f482c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ dd(String str, boolean z7, int i8, cd cdVar) {
        this.f480a = str;
        this.f481b = z7;
        this.f482c = i8;
    }

    @Override // a4.gd
    public final int a() {
        return this.f482c;
    }

    @Override // a4.gd
    public final String b() {
        return this.f480a;
    }

    @Override // a4.gd
    public final boolean c() {
        return this.f481b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof gd) {
            gd gdVar = (gd) obj;
            if (this.f480a.equals(gdVar.b()) && this.f481b == gdVar.c() && this.f482c == gdVar.a()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((this.f480a.hashCode() ^ 1000003) * 1000003) ^ (true != this.f481b ? 1237 : 1231)) * 1000003) ^ this.f482c;
    }

    public final String toString() {
        String str = this.f480a;
        boolean z7 = this.f481b;
        int i8 = this.f482c;
        return "MLKitLoggingOptions{libraryName=" + str + ", enableFirelog=" + z7 + ", firelogEventType=" + i8 + "}";
    }
}
