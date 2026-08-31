package h4;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class a {

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        private final String f7260a;

        /* renamed from: b  reason: collision with root package name */
        private final C0095a f7261b;

        /* renamed from: c  reason: collision with root package name */
        private C0095a f7262c;

        /* renamed from: d  reason: collision with root package name */
        private boolean f7263d;

        /* renamed from: e  reason: collision with root package name */
        private boolean f7264e;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: h4.a$b$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static class C0095a {

            /* renamed from: a  reason: collision with root package name */
            String f7265a;

            /* renamed from: b  reason: collision with root package name */
            Object f7266b;

            /* renamed from: c  reason: collision with root package name */
            C0095a f7267c;

            C0095a() {
            }
        }

        private b(String str) {
            C0095a c0095a = new C0095a();
            this.f7261b = c0095a;
            this.f7262c = c0095a;
            this.f7263d = false;
            this.f7264e = false;
            this.f7260a = (String) h4.b.a(str);
        }

        private C0095a a() {
            C0095a c0095a = new C0095a();
            this.f7262c.f7267c = c0095a;
            this.f7262c = c0095a;
            return c0095a;
        }

        private b b(Object obj) {
            a().f7266b = obj;
            return this;
        }

        private static boolean d(Object obj) {
            return obj instanceof CharSequence ? ((CharSequence) obj).length() == 0 : obj instanceof Collection ? ((Collection) obj).isEmpty() : obj instanceof Map ? ((Map) obj).isEmpty() : obj.getClass().isArray() && Array.getLength(obj) == 0;
        }

        public b c(Object obj) {
            return b(obj);
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0033  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.String toString() {
            /*
                r7 = this;
                boolean r0 = r7.f7263d
                boolean r1 = r7.f7264e
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r3 = 32
                r2.<init>(r3)
                java.lang.String r3 = r7.f7260a
                r2.append(r3)
                r3 = 123(0x7b, float:1.72E-43)
                r2.append(r3)
                h4.a$b$a r3 = r7.f7261b
                h4.a$b$a r3 = r3.f7267c
                java.lang.String r4 = ""
            L1b:
                if (r3 == 0) goto L61
                java.lang.Object r5 = r3.f7266b
                if (r5 != 0) goto L24
                if (r0 != 0) goto L5e
                goto L2c
            L24:
                if (r1 == 0) goto L2c
                boolean r6 = d(r5)
                if (r6 != 0) goto L5e
            L2c:
                r2.append(r4)
                java.lang.String r4 = r3.f7265a
                if (r4 == 0) goto L3b
                r2.append(r4)
                r4 = 61
                r2.append(r4)
            L3b:
                if (r5 == 0) goto L59
                java.lang.Class r4 = r5.getClass()
                boolean r4 = r4.isArray()
                if (r4 == 0) goto L59
                java.lang.Object[] r4 = new java.lang.Object[]{r5}
                java.lang.String r4 = java.util.Arrays.deepToString(r4)
                int r5 = r4.length()
                r6 = 1
                int r5 = r5 - r6
                r2.append(r4, r6, r5)
                goto L5c
            L59:
                r2.append(r5)
            L5c:
                java.lang.String r4 = ", "
            L5e:
                h4.a$b$a r3 = r3.f7267c
                goto L1b
            L61:
                r0 = 125(0x7d, float:1.75E-43)
                r2.append(r0)
                java.lang.String r0 = r2.toString()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: h4.a.b.toString():java.lang.String");
        }
    }

    public static b a(Object obj) {
        return new b(obj.getClass().getSimpleName());
    }
}
