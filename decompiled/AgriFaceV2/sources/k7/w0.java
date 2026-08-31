package k7;

import java.io.Closeable;
import t6.g;
/* loaded from: classes.dex */
public abstract class w0 extends a0 implements Closeable {

    /* renamed from: g  reason: collision with root package name */
    public static final a f8796g = new a(null);

    /* loaded from: classes.dex */
    public static final class a extends t6.b {

        /* renamed from: k7.w0$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        static final class C0118a extends d7.l implements c7.l {

            /* renamed from: f  reason: collision with root package name */
            public static final C0118a f8797f = new C0118a();

            C0118a() {
                super(1);
            }

            @Override // c7.l
            /* renamed from: b */
            public final w0 g(g.b bVar) {
                if (bVar instanceof w0) {
                    return (w0) bVar;
                }
                return null;
            }
        }

        private a() {
            super(a0.f8716f, C0118a.f8797f);
        }

        public /* synthetic */ a(d7.g gVar) {
            this();
        }
    }
}
