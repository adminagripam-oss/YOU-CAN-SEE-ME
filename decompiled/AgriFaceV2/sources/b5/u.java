package b5;
/* loaded from: classes.dex */
public class u implements h5.b {

    /* renamed from: c  reason: collision with root package name */
    private static final Object f4679c = new Object();

    /* renamed from: a  reason: collision with root package name */
    private volatile Object f4680a = f4679c;

    /* renamed from: b  reason: collision with root package name */
    private volatile h5.b f4681b;

    public u(h5.b bVar) {
        this.f4681b = bVar;
    }

    @Override // h5.b
    public Object get() {
        Object obj = this.f4680a;
        Object obj2 = f4679c;
        if (obj == obj2) {
            synchronized (this) {
                obj = this.f4680a;
                if (obj == obj2) {
                    obj = this.f4681b.get();
                    this.f4680a = obj;
                    this.f4681b = null;
                }
            }
        }
        return obj;
    }
}
