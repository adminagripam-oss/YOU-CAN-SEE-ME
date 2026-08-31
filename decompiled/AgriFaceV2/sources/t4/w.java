package t4;
/* loaded from: classes.dex */
public abstract class w {

    /* renamed from: a  reason: collision with root package name */
    private final a5.a f11884a;

    /* renamed from: b  reason: collision with root package name */
    private final Class f11885b;

    /* loaded from: classes.dex */
    class a extends w {

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ b f11886c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(a5.a aVar, Class cls, b bVar) {
            super(aVar, cls, null);
            this.f11886c = bVar;
        }

        @Override // t4.w
        public j4.w d(h0 h0Var) {
            return this.f11886c.a(h0Var);
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        j4.w a(h0 h0Var);
    }

    private w(a5.a aVar, Class cls) {
        this.f11884a = aVar;
        this.f11885b = cls;
    }

    public static w a(b bVar, a5.a aVar, Class cls) {
        return new a(aVar, cls, bVar);
    }

    public final a5.a b() {
        return this.f11884a;
    }

    public final Class c() {
        return this.f11885b;
    }

    public abstract j4.w d(h0 h0Var);

    /* synthetic */ w(a5.a aVar, Class cls, a aVar2) {
        this(aVar, cls);
    }
}
