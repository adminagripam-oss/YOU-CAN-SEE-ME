package w5;
/* loaded from: classes.dex */
public interface p {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements l {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ k f12550a;

        a(k kVar) {
            this.f12550a = kVar;
        }

        @Override // w5.l
        public int a() {
            return this.f12550a.f12528c;
        }

        @Override // w5.l
        public boolean b() {
            return this.f12550a.F();
        }
    }

    static p a(String str, int i8, int i9) {
        return i8 == 1 ? new t(str, i9) : new r(str, i8, i9);
    }

    void b();

    void c(m mVar);

    default void d(k kVar, Runnable runnable) {
        c(new m(kVar == null ? null : new a(kVar), runnable));
    }

    void start();
}
