package m7;
/* loaded from: classes.dex */
public interface t {

    /* loaded from: classes.dex */
    public static final class a {
        public static /* synthetic */ boolean a(t tVar, Throwable th, int i8, Object obj) {
            if (obj == null) {
                if ((i8 & 1) != 0) {
                    th = null;
                }
                return tVar.f(th);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: close");
        }
    }

    void a(c7.l lVar);

    boolean f(Throwable th);

    Object l(Object obj);

    Object m(Object obj, t6.d dVar);

    boolean o();
}
