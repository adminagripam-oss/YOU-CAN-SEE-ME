package m7;
/* loaded from: classes.dex */
public abstract class h {

    /* renamed from: a  reason: collision with root package name */
    public static final b f9684a = new b(null);

    /* renamed from: b  reason: collision with root package name */
    private static final c f9685b = new c();

    /* loaded from: classes.dex */
    public static final class a extends c {

        /* renamed from: a  reason: collision with root package name */
        public final Throwable f9686a;

        public a(Throwable th) {
            this.f9686a = th;
        }

        public boolean equals(Object obj) {
            return (obj instanceof a) && d7.k.a(this.f9686a, ((a) obj).f9686a);
        }

        public int hashCode() {
            Throwable th = this.f9686a;
            if (th != null) {
                return th.hashCode();
            }
            return 0;
        }

        @Override // m7.h.c
        public String toString() {
            return "Closed(" + this.f9686a + ')';
        }
    }

    /* loaded from: classes.dex */
    public static final class b {
        private b() {
        }

        public /* synthetic */ b(d7.g gVar) {
            this();
        }

        public final Object a(Throwable th) {
            return h.b(new a(th));
        }

        public final Object b() {
            return h.b(h.f9685b);
        }

        public final Object c(Object obj) {
            return h.b(obj);
        }
    }

    /* loaded from: classes.dex */
    public static class c {
        public String toString() {
            return "Failed";
        }
    }

    public static Object b(Object obj) {
        return obj;
    }

    public static final Throwable c(Object obj) {
        a aVar = obj instanceof a ? (a) obj : null;
        if (aVar != null) {
            return aVar.f9686a;
        }
        return null;
    }

    public static final boolean d(Object obj) {
        return obj instanceof a;
    }

    public static final boolean e(Object obj) {
        return !(obj instanceof c);
    }
}
