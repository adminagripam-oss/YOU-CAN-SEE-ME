package s2;
/* loaded from: classes.dex */
final class a extends c {

    /* renamed from: a  reason: collision with root package name */
    private final Integer f11549a;

    /* renamed from: b  reason: collision with root package name */
    private final Object f11550b;

    /* renamed from: c  reason: collision with root package name */
    private final d f11551c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(Integer num, Object obj, d dVar) {
        this.f11549a = num;
        if (obj == null) {
            throw new NullPointerException("Null payload");
        }
        this.f11550b = obj;
        if (dVar == null) {
            throw new NullPointerException("Null priority");
        }
        this.f11551c = dVar;
    }

    @Override // s2.c
    public Integer a() {
        return this.f11549a;
    }

    @Override // s2.c
    public Object b() {
        return this.f11550b;
    }

    @Override // s2.c
    public d c() {
        return this.f11551c;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof c) {
            c cVar = (c) obj;
            Integer num = this.f11549a;
            if (num != null ? num.equals(cVar.a()) : cVar.a() == null) {
                if (this.f11550b.equals(cVar.b()) && this.f11551c.equals(cVar.c())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        Integer num = this.f11549a;
        return (((((num == null ? 0 : num.hashCode()) ^ 1000003) * 1000003) ^ this.f11550b.hashCode()) * 1000003) ^ this.f11551c.hashCode();
    }

    public String toString() {
        return "Event{code=" + this.f11549a + ", payload=" + this.f11550b + ", priority=" + this.f11551c + "}";
    }
}
