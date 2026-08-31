package b5;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class z implements h5.b {

    /* renamed from: c  reason: collision with root package name */
    private static final h5.a f4686c = x.b();

    /* renamed from: d  reason: collision with root package name */
    private static final h5.b f4687d = y.a();

    /* renamed from: a  reason: collision with root package name */
    private h5.a f4688a;

    /* renamed from: b  reason: collision with root package name */
    private volatile h5.b f4689b;

    private z(h5.a aVar, h5.b bVar) {
        this.f4688a = aVar;
        this.f4689b = bVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static z a() {
        return new z(f4686c, f4687d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void b(h5.b bVar) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object c() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(h5.b bVar) {
        h5.a aVar;
        if (this.f4689b != f4687d) {
            throw new IllegalStateException("provide() can be called only once.");
        }
        synchronized (this) {
            aVar = this.f4688a;
            this.f4688a = null;
            this.f4689b = bVar;
        }
        aVar.a(bVar);
    }

    @Override // h5.b
    public Object get() {
        return this.f4689b.get();
    }
}
