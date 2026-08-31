package y3;
/* loaded from: classes.dex */
final class y extends d0 {

    /* renamed from: a  reason: collision with root package name */
    private String f13272a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f13273b;

    /* renamed from: c  reason: collision with root package name */
    private int f13274c;

    /* renamed from: d  reason: collision with root package name */
    private byte f13275d;

    @Override // y3.d0
    public final d0 a(boolean z7) {
        this.f13273b = true;
        this.f13275d = (byte) (1 | this.f13275d);
        return this;
    }

    @Override // y3.d0
    public final d0 b(int i8) {
        this.f13274c = 1;
        this.f13275d = (byte) (this.f13275d | 2);
        return this;
    }

    @Override // y3.d0
    public final e0 c() {
        String str;
        if (this.f13275d != 3 || (str = this.f13272a) == null) {
            StringBuilder sb = new StringBuilder();
            if (this.f13272a == null) {
                sb.append(" libraryName");
            }
            if ((this.f13275d & 1) == 0) {
                sb.append(" enableFirelog");
            }
            if ((this.f13275d & 2) == 0) {
                sb.append(" firelogEventType");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
        return new a0(str, this.f13273b, this.f13274c, null);
    }

    public final d0 d(String str) {
        this.f13272a = "common";
        return this;
    }
}
