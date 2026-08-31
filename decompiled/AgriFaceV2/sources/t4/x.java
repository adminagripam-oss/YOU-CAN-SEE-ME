package t4;
/* loaded from: classes.dex */
public abstract class x {

    /* renamed from: a  reason: collision with root package name */
    private final Class f11887a;

    /* renamed from: b  reason: collision with root package name */
    private final Class f11888b;

    /* loaded from: classes.dex */
    class a extends x {

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ b f11889c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Class cls, Class cls2, b bVar) {
            super(cls, cls2, null);
            this.f11889c = bVar;
        }

        @Override // t4.x
        public h0 d(j4.w wVar) {
            return this.f11889c.a(wVar);
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        h0 a(j4.w wVar);
    }

    private x(Class cls, Class cls2) {
        this.f11887a = cls;
        this.f11888b = cls2;
    }

    public static x a(b bVar, Class cls, Class cls2) {
        return new a(cls, cls2, bVar);
    }

    public Class b() {
        return this.f11887a;
    }

    public Class c() {
        return this.f11888b;
    }

    public abstract h0 d(j4.w wVar);

    /* synthetic */ x(Class cls, Class cls2, a aVar) {
        this(cls, cls2);
    }
}
