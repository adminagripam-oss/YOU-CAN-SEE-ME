package k7;
/* loaded from: classes.dex */
final class t {

    /* renamed from: a  reason: collision with root package name */
    public final Object f8784a;

    /* renamed from: b  reason: collision with root package name */
    public final c7.l f8785b;

    /* renamed from: c  reason: collision with root package name */
    public final Object f8786c;

    /* renamed from: d  reason: collision with root package name */
    public final Throwable f8787d;

    public t(Object obj, h hVar, c7.l lVar, Object obj2, Throwable th) {
        this.f8784a = obj;
        this.f8785b = lVar;
        this.f8786c = obj2;
        this.f8787d = th;
    }

    public static /* synthetic */ t b(t tVar, Object obj, h hVar, c7.l lVar, Object obj2, Throwable th, int i8, Object obj3) {
        if ((i8 & 1) != 0) {
            obj = tVar.f8784a;
        }
        if ((i8 & 2) != 0) {
            tVar.getClass();
            hVar = null;
        }
        h hVar2 = hVar;
        if ((i8 & 4) != 0) {
            lVar = tVar.f8785b;
        }
        c7.l lVar2 = lVar;
        if ((i8 & 8) != 0) {
            obj2 = tVar.f8786c;
        }
        Object obj4 = obj2;
        if ((i8 & 16) != 0) {
            th = tVar.f8787d;
        }
        return tVar.a(obj, hVar2, lVar2, obj4, th);
    }

    public final t a(Object obj, h hVar, c7.l lVar, Object obj2, Throwable th) {
        return new t(obj, hVar, lVar, obj2, th);
    }

    public final boolean c() {
        return this.f8787d != null;
    }

    public final void d(k kVar, Throwable th) {
        c7.l lVar = this.f8785b;
        if (lVar != null) {
            kVar.m(lVar, th);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof t) {
            t tVar = (t) obj;
            return d7.k.a(this.f8784a, tVar.f8784a) && d7.k.a(null, null) && d7.k.a(this.f8785b, tVar.f8785b) && d7.k.a(this.f8786c, tVar.f8786c) && d7.k.a(this.f8787d, tVar.f8787d);
        }
        return false;
    }

    public int hashCode() {
        Object obj = this.f8784a;
        int hashCode = (((obj == null ? 0 : obj.hashCode()) * 31) + 0) * 31;
        c7.l lVar = this.f8785b;
        int hashCode2 = (hashCode + (lVar == null ? 0 : lVar.hashCode())) * 31;
        Object obj2 = this.f8786c;
        int hashCode3 = (hashCode2 + (obj2 == null ? 0 : obj2.hashCode())) * 31;
        Throwable th = this.f8787d;
        return hashCode3 + (th != null ? th.hashCode() : 0);
    }

    public String toString() {
        return "CompletedContinuation(result=" + this.f8784a + ", cancelHandler=" + ((Object) null) + ", onCancellation=" + this.f8785b + ", idempotentResume=" + this.f8786c + ", cancelCause=" + this.f8787d + ')';
    }

    public /* synthetic */ t(Object obj, h hVar, c7.l lVar, Object obj2, Throwable th, int i8, d7.g gVar) {
        this(obj, (i8 & 2) != 0 ? null : hVar, (i8 & 4) != 0 ? null : lVar, (i8 & 8) != 0 ? null : obj2, (i8 & 16) != 0 ? null : th);
    }
}
