package k3;
/* loaded from: classes.dex */
public final class r {

    /* renamed from: b  reason: collision with root package name */
    private static r f8466b;

    /* renamed from: c  reason: collision with root package name */
    private static final s f8467c = new s(0, false, false, 0, 0);

    /* renamed from: a  reason: collision with root package name */
    private s f8468a;

    private r() {
    }

    public static synchronized r b() {
        r rVar;
        synchronized (r.class) {
            if (f8466b == null) {
                f8466b = new r();
            }
            rVar = f8466b;
        }
        return rVar;
    }

    public s a() {
        return this.f8468a;
    }

    public final synchronized void c(s sVar) {
        if (sVar == null) {
            this.f8468a = f8467c;
            return;
        }
        s sVar2 = this.f8468a;
        if (sVar2 == null || sVar2.e() < sVar.e()) {
            this.f8468a = sVar;
        }
    }
}
