package a4;
/* loaded from: classes.dex */
final class bd extends fd {

    /* renamed from: a  reason: collision with root package name */
    private String f294a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f295b;

    /* renamed from: c  reason: collision with root package name */
    private int f296c;

    /* renamed from: d  reason: collision with root package name */
    private byte f297d;

    @Override // a4.fd
    public final fd a(boolean z7) {
        this.f295b = true;
        this.f297d = (byte) (1 | this.f297d);
        return this;
    }

    @Override // a4.fd
    public final fd b(int i8) {
        this.f296c = 1;
        this.f297d = (byte) (this.f297d | 2);
        return this;
    }

    @Override // a4.fd
    public final gd c() {
        String str;
        if (this.f297d != 3 || (str = this.f294a) == null) {
            StringBuilder sb = new StringBuilder();
            if (this.f294a == null) {
                sb.append(" libraryName");
            }
            if ((this.f297d & 1) == 0) {
                sb.append(" enableFirelog");
            }
            if ((this.f297d & 2) == 0) {
                sb.append(" firelogEventType");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
        return new dd(str, this.f295b, this.f296c, null);
    }

    public final fd d(String str) {
        this.f294a = str;
        return this;
    }
}
