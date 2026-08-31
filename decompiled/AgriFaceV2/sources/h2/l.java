package h2;

import android.app.Activity;
/* loaded from: classes.dex */
public interface l {

    /* renamed from: a  reason: collision with root package name */
    public static final a f7213a = a.f7214a;

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ a f7214a = new a();

        /* renamed from: b  reason: collision with root package name */
        private static c7.l f7215b = C0093a.f7216f;

        /* renamed from: h2.l$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        static final class C0093a extends d7.l implements c7.l {

            /* renamed from: f  reason: collision with root package name */
            public static final C0093a f7216f = new C0093a();

            C0093a() {
                super(1);
            }

            @Override // c7.l
            /* renamed from: b */
            public final l g(l lVar) {
                d7.k.e(lVar, "it");
                return lVar;
            }
        }

        private a() {
        }

        public final l a() {
            return (l) f7215b.g(o.f7217b);
        }
    }

    static l b() {
        return f7213a.a();
    }

    k a(Activity activity);
}
