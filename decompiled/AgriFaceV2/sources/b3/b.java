package b3;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class b extends i {

    /* renamed from: a  reason: collision with root package name */
    private final long f4566a;

    /* renamed from: b  reason: collision with root package name */
    private final u2.m f4567b;

    /* renamed from: c  reason: collision with root package name */
    private final u2.h f4568c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(long j8, u2.m mVar, u2.h hVar) {
        this.f4566a = j8;
        if (mVar == null) {
            throw new NullPointerException("Null transportContext");
        }
        this.f4567b = mVar;
        if (hVar == null) {
            throw new NullPointerException("Null event");
        }
        this.f4568c = hVar;
    }

    @Override // b3.i
    public u2.h b() {
        return this.f4568c;
    }

    @Override // b3.i
    public long c() {
        return this.f4566a;
    }

    @Override // b3.i
    public u2.m d() {
        return this.f4567b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof i) {
            i iVar = (i) obj;
            return this.f4566a == iVar.c() && this.f4567b.equals(iVar.d()) && this.f4568c.equals(iVar.b());
        }
        return false;
    }

    public int hashCode() {
        long j8 = this.f4566a;
        return ((((((int) (j8 ^ (j8 >>> 32))) ^ 1000003) * 1000003) ^ this.f4567b.hashCode()) * 1000003) ^ this.f4568c.hashCode();
    }

    public String toString() {
        return "PersistedEvent{id=" + this.f4566a + ", transportContext=" + this.f4567b + ", event=" + this.f4568c + "}";
    }
}
