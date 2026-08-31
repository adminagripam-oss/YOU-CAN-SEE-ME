package t4;
/* loaded from: classes.dex */
public abstract class g {

    /* renamed from: a  reason: collision with root package name */
    private final Class f11843a;

    /* renamed from: b  reason: collision with root package name */
    private final Class f11844b;

    /* loaded from: classes.dex */
    class a extends g {

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ b f11845c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Class cls, Class cls2, b bVar) {
            super(cls, cls2, null);
            this.f11845c = bVar;
        }

        @Override // t4.g
        public h0 d(j4.h hVar, j4.y yVar) {
            return this.f11845c.a(hVar, yVar);
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        h0 a(j4.h hVar, j4.y yVar);
    }

    private g(Class cls, Class cls2) {
        this.f11843a = cls;
        this.f11844b = cls2;
    }

    public static g a(b bVar, Class cls, Class cls2) {
        return new a(cls, cls2, bVar);
    }

    public Class b() {
        return this.f11843a;
    }

    public Class c() {
        return this.f11844b;
    }

    public abstract h0 d(j4.h hVar, j4.y yVar);

    /* synthetic */ g(Class cls, Class cls2, a aVar) {
        this(cls, cls2);
    }
}
