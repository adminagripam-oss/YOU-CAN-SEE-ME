package z3;
/* loaded from: classes.dex */
final class kb extends qb {

    /* renamed from: a  reason: collision with root package name */
    private String f13836a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f13837b;

    /* renamed from: c  reason: collision with root package name */
    private int f13838c;

    /* renamed from: d  reason: collision with root package name */
    private byte f13839d;

    @Override // z3.qb
    public final qb a(boolean z7) {
        this.f13837b = true;
        this.f13839d = (byte) (1 | this.f13839d);
        return this;
    }

    @Override // z3.qb
    public final qb b(int i8) {
        this.f13838c = 1;
        this.f13839d = (byte) (this.f13839d | 2);
        return this;
    }

    @Override // z3.qb
    public final rb c() {
        String str;
        if (this.f13839d != 3 || (str = this.f13836a) == null) {
            StringBuilder sb = new StringBuilder();
            if (this.f13836a == null) {
                sb.append(" libraryName");
            }
            if ((this.f13839d & 1) == 0) {
                sb.append(" enableFirelog");
            }
            if ((this.f13839d & 2) == 0) {
                sb.append(" firelogEventType");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
        return new nb(str, this.f13837b, this.f13838c, null);
    }

    public final qb d(String str) {
        this.f13836a = "vision-common";
        return this;
    }
}
