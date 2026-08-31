package t4;
/* loaded from: classes.dex */
public abstract class z {

    /* renamed from: a  reason: collision with root package name */
    private final Class f11898a;

    /* renamed from: b  reason: collision with root package name */
    private final Class f11899b;

    /* loaded from: classes.dex */
    class a extends z {

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ b f11900c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Class cls, Class cls2, b bVar) {
            super(cls, cls2, null);
            this.f11900c = bVar;
        }

        @Override // t4.z
        public Object a(j4.h hVar) {
            return this.f11900c.a(hVar);
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        Object a(j4.h hVar);
    }

    private z(Class cls, Class cls2) {
        this.f11898a = cls;
        this.f11899b = cls2;
    }

    public static z b(b bVar, Class cls, Class cls2) {
        return new a(cls, cls2, bVar);
    }

    public abstract Object a(j4.h hVar);

    public Class c() {
        return this.f11898a;
    }

    public Class d() {
        return this.f11899b;
    }

    /* synthetic */ z(Class cls, Class cls2, a aVar) {
        this(cls, cls2);
    }
}
