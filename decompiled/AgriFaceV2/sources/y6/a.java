package y6;

import d7.k;
/* loaded from: classes.dex */
public abstract class a extends x6.a {

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y6.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0169a {

        /* renamed from: a  reason: collision with root package name */
        public static final C0169a f13365a = new C0169a();

        /* renamed from: b  reason: collision with root package name */
        public static final Integer f13366b;

        /* JADX WARN: Removed duplicated region for block: B:9:0x0022  */
        static {
            /*
                y6.a$a r0 = new y6.a$a
                r0.<init>()
                y6.a.C0169a.f13365a = r0
                r0 = 0
                java.lang.String r1 = "android.os.Build$VERSION"
                java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch: java.lang.Throwable -> L1f
                java.lang.String r2 = "SDK_INT"
                java.lang.reflect.Field r1 = r1.getField(r2)     // Catch: java.lang.Throwable -> L1f
                java.lang.Object r1 = r1.get(r0)     // Catch: java.lang.Throwable -> L1f
                boolean r2 = r1 instanceof java.lang.Integer     // Catch: java.lang.Throwable -> L1f
                if (r2 == 0) goto L1f
                java.lang.Integer r1 = (java.lang.Integer) r1     // Catch: java.lang.Throwable -> L1f
                goto L20
            L1f:
                r1 = r0
            L20:
                if (r1 == 0) goto L2e
                int r2 = r1.intValue()
                if (r2 <= 0) goto L2a
                r2 = 1
                goto L2b
            L2a:
                r2 = 0
            L2b:
                if (r2 == 0) goto L2e
                r0 = r1
            L2e:
                y6.a.C0169a.f13366b = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: y6.a.C0169a.<clinit>():void");
        }

        private C0169a() {
        }
    }

    private final boolean c(int i8) {
        Integer num = C0169a.f13366b;
        return num == null || num.intValue() >= i8;
    }

    @Override // x6.a
    public void a(Throwable th, Throwable th2) {
        k.e(th, "cause");
        k.e(th2, "exception");
        if (c(19)) {
            th.addSuppressed(th2);
        } else {
            super.a(th, th2);
        }
    }
}
