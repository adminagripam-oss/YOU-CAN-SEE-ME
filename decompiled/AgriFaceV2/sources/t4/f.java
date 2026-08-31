package t4;
/* loaded from: classes.dex */
public abstract class f {

    /* renamed from: a  reason: collision with root package name */
    private final a5.a f11839a;

    /* renamed from: b  reason: collision with root package name */
    private final Class f11840b;

    /* loaded from: classes.dex */
    class a extends f {

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ b f11841c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(a5.a aVar, Class cls, b bVar) {
            super(aVar, cls, null);
            this.f11841c = bVar;
        }

        @Override // t4.f
        public j4.h d(h0 h0Var, j4.y yVar) {
            return this.f11841c.a(h0Var, yVar);
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        j4.h a(h0 h0Var, j4.y yVar);
    }

    private f(a5.a aVar, Class cls) {
        this.f11839a = aVar;
        this.f11840b = cls;
    }

    public static f a(b bVar, a5.a aVar, Class cls) {
        return new a(aVar, cls, bVar);
    }

    public final a5.a b() {
        return this.f11839a;
    }

    public final Class c() {
        return this.f11840b;
    }

    public abstract j4.h d(h0 h0Var, j4.y yVar);

    /* synthetic */ f(a5.a aVar, Class cls, a aVar2) {
        this(aVar, cls);
    }
}
