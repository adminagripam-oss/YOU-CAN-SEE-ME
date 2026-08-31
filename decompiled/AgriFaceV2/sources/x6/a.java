package x6;

import d7.k;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: x6.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0163a {

        /* renamed from: a  reason: collision with root package name */
        public static final C0163a f12725a = new C0163a();

        /* renamed from: b  reason: collision with root package name */
        public static final Method f12726b;

        /* renamed from: c  reason: collision with root package name */
        public static final Method f12727c;

        /* JADX WARN: Removed duplicated region for block: B:13:0x003d A[LOOP:0: B:3:0x0013->B:13:0x003d, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0041 A[EDGE_INSN: B:24:0x0041->B:15:0x0041 ?: BREAK  , SYNTHETIC] */
        static {
            /*
                x6.a$a r0 = new x6.a$a
                r0.<init>()
                x6.a.C0163a.f12725a = r0
                java.lang.Class<java.lang.Throwable> r0 = java.lang.Throwable.class
                java.lang.reflect.Method[] r1 = r0.getMethods()
                d7.k.b(r1)
                int r2 = r1.length
                r3 = 0
                r4 = r3
            L13:
                r5 = 0
                if (r4 >= r2) goto L40
                r6 = r1[r4]
                java.lang.String r7 = r6.getName()
                java.lang.String r8 = "addSuppressed"
                boolean r7 = d7.k.a(r7, r8)
                if (r7 == 0) goto L39
                java.lang.Class[] r7 = r6.getParameterTypes()
                java.lang.String r8 = "getParameterTypes(...)"
                d7.k.d(r7, r8)
                java.lang.Object r7 = s6.f.n(r7)
                boolean r7 = d7.k.a(r7, r0)
                if (r7 == 0) goto L39
                r7 = 1
                goto L3a
            L39:
                r7 = r3
            L3a:
                if (r7 == 0) goto L3d
                goto L41
            L3d:
                int r4 = r4 + 1
                goto L13
            L40:
                r6 = r5
            L41:
                x6.a.C0163a.f12726b = r6
                int r0 = r1.length
            L44:
                if (r3 >= r0) goto L59
                r2 = r1[r3]
                java.lang.String r4 = r2.getName()
                java.lang.String r6 = "getSuppressed"
                boolean r4 = d7.k.a(r4, r6)
                if (r4 == 0) goto L56
                r5 = r2
                goto L59
            L56:
                int r3 = r3 + 1
                goto L44
            L59:
                x6.a.C0163a.f12727c = r5
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: x6.a.C0163a.<clinit>():void");
        }

        private C0163a() {
        }
    }

    public void a(Throwable th, Throwable th2) {
        k.e(th, "cause");
        k.e(th2, "exception");
        Method method = C0163a.f12726b;
        if (method != null) {
            method.invoke(th, th2);
        }
    }

    public e7.c b() {
        return new e7.b();
    }
}
