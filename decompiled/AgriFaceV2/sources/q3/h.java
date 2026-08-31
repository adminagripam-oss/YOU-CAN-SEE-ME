package q3;

import android.os.Process;
import android.os.WorkSource;
import android.util.Log;
import java.lang.reflect.Method;
import k3.q;
/* loaded from: classes.dex */
public abstract class h {

    /* renamed from: a  reason: collision with root package name */
    private static final int f10326a = Process.myUid();

    /* renamed from: b  reason: collision with root package name */
    private static final Method f10327b;

    /* renamed from: c  reason: collision with root package name */
    private static final Method f10328c;

    /* renamed from: d  reason: collision with root package name */
    private static final Method f10329d;

    /* renamed from: e  reason: collision with root package name */
    private static final Method f10330e;

    /* renamed from: f  reason: collision with root package name */
    private static final Method f10331f;

    /* renamed from: g  reason: collision with root package name */
    private static final Method f10332g;

    /* renamed from: h  reason: collision with root package name */
    private static final Method f10333h;

    /* renamed from: i  reason: collision with root package name */
    private static final Method f10334i;

    /* renamed from: j  reason: collision with root package name */
    private static Boolean f10335j;

    /* JADX WARN: Can't wrap try/catch for region: R(25:1|(2:2|3)|4|(22:54|55|7|8|9|10|11|12|13|(13:46|47|16|(10:41|42|19|(7:36|37|22|(6:28|29|30|31|25|26)|24|25|26)|21|22|(0)|24|25|26)|18|19|(0)|21|22|(0)|24|25|26)|15|16|(0)|18|19|(0)|21|22|(0)|24|25|26)|6|7|8|9|10|11|12|13|(0)|15|16|(0)|18|19|(0)|21|22|(0)|24|25|26) */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x003d, code lost:
        r0 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004d, code lost:
        r0 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0086 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x006e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00aa A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0056 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    static {
        /*
            java.lang.String r0 = "add"
            java.lang.Class<android.os.WorkSource> r1 = android.os.WorkSource.class
            int r2 = android.os.Process.myUid()
            q3.h.f10326a = r2
            r2 = 1
            r3 = 0
            r4 = 0
            java.lang.Class[] r5 = new java.lang.Class[r2]     // Catch: java.lang.Exception -> L18
            java.lang.Class r6 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L18
            r5[r3] = r6     // Catch: java.lang.Exception -> L18
            java.lang.reflect.Method r5 = r1.getMethod(r0, r5)     // Catch: java.lang.Exception -> L18
            goto L19
        L18:
            r5 = r4
        L19:
            q3.h.f10327b = r5
            boolean r5 = q3.g.c()
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            r7 = 2
            if (r5 == 0) goto L31
            java.lang.Class[] r5 = new java.lang.Class[r7]     // Catch: java.lang.Exception -> L31
            java.lang.Class r8 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L31
            r5[r3] = r8     // Catch: java.lang.Exception -> L31
            r5[r2] = r6     // Catch: java.lang.Exception -> L31
            java.lang.reflect.Method r0 = r1.getMethod(r0, r5)     // Catch: java.lang.Exception -> L31
            goto L32
        L31:
            r0 = r4
        L32:
            q3.h.f10328c = r0
            java.lang.String r0 = "size"
            java.lang.Class[] r5 = new java.lang.Class[r3]     // Catch: java.lang.Exception -> L3d
            java.lang.reflect.Method r0 = r1.getMethod(r0, r5)     // Catch: java.lang.Exception -> L3d
            goto L3e
        L3d:
            r0 = r4
        L3e:
            q3.h.f10329d = r0
            java.lang.String r0 = "get"
            java.lang.Class[] r5 = new java.lang.Class[r2]     // Catch: java.lang.Exception -> L4d
            java.lang.Class r8 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L4d
            r5[r3] = r8     // Catch: java.lang.Exception -> L4d
            java.lang.reflect.Method r0 = r1.getMethod(r0, r5)     // Catch: java.lang.Exception -> L4d
            goto L4e
        L4d:
            r0 = r4
        L4e:
            q3.h.f10330e = r0
            boolean r0 = q3.g.c()
            if (r0 == 0) goto L63
            java.lang.String r0 = "getName"
            java.lang.Class[] r5 = new java.lang.Class[r2]     // Catch: java.lang.Exception -> L63
            java.lang.Class r8 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L63
            r5[r3] = r8     // Catch: java.lang.Exception -> L63
            java.lang.reflect.Method r0 = r1.getMethod(r0, r5)     // Catch: java.lang.Exception -> L63
            goto L64
        L63:
            r0 = r4
        L64:
            q3.h.f10331f = r0
            boolean r0 = q3.g.h()
            java.lang.String r5 = "WorkSourceUtil"
            if (r0 == 0) goto L7d
            java.lang.String r0 = "createWorkChain"
            java.lang.Class[] r8 = new java.lang.Class[r3]     // Catch: java.lang.Exception -> L77
            java.lang.reflect.Method r0 = r1.getMethod(r0, r8)     // Catch: java.lang.Exception -> L77
            goto L7e
        L77:
            r0 = move-exception
            java.lang.String r8 = "Missing WorkChain API createWorkChain"
            android.util.Log.w(r5, r8, r0)
        L7d:
            r0 = r4
        L7e:
            q3.h.f10332g = r0
            boolean r0 = q3.g.h()
            if (r0 == 0) goto La1
            java.lang.String r0 = "android.os.WorkSource$WorkChain"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch: java.lang.Exception -> L9b
            java.lang.String r8 = "addNode"
            java.lang.Class[] r7 = new java.lang.Class[r7]     // Catch: java.lang.Exception -> L9b
            java.lang.Class r9 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L9b
            r7[r3] = r9     // Catch: java.lang.Exception -> L9b
            r7[r2] = r6     // Catch: java.lang.Exception -> L9b
            java.lang.reflect.Method r0 = r0.getMethod(r8, r7)     // Catch: java.lang.Exception -> L9b
            goto La2
        L9b:
            r0 = move-exception
            java.lang.String r6 = "Missing WorkChain class"
            android.util.Log.w(r5, r6, r0)
        La1:
            r0 = r4
        La2:
            q3.h.f10333h = r0
            boolean r0 = q3.g.h()
            if (r0 == 0) goto Lb6
            java.lang.String r0 = "isEmpty"
            java.lang.Class[] r3 = new java.lang.Class[r3]     // Catch: java.lang.Exception -> Lb6
            java.lang.reflect.Method r0 = r1.getMethod(r0, r3)     // Catch: java.lang.Exception -> Lb6
            r0.setAccessible(r2)     // Catch: java.lang.Exception -> Lb7
            goto Lb7
        Lb6:
            r0 = r4
        Lb7:
            q3.h.f10334i = r0
            q3.h.f10335j = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: q3.h.<clinit>():void");
    }

    public static void a(WorkSource workSource, int i8, String str) {
        Method method = f10328c;
        if (method != null) {
            if (str == null) {
                str = "";
            }
            try {
                method.invoke(workSource, Integer.valueOf(i8), str);
                return;
            } catch (Exception e8) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e8);
                return;
            }
        }
        Method method2 = f10327b;
        if (method2 != null) {
            try {
                method2.invoke(workSource, Integer.valueOf(i8));
            } catch (Exception e9) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e9);
            }
        }
    }

    public static boolean b(WorkSource workSource) {
        Method method = f10334i;
        if (method != null) {
            try {
                Object invoke = method.invoke(workSource, new Object[0]);
                q.g(invoke);
                return ((Boolean) invoke).booleanValue();
            } catch (Exception e8) {
                Log.e("WorkSourceUtil", "Unable to check WorkSource emptiness", e8);
            }
        }
        return c(workSource) == 0;
    }

    public static int c(WorkSource workSource) {
        Method method = f10329d;
        if (method != null) {
            try {
                Object invoke = method.invoke(workSource, new Object[0]);
                q.g(invoke);
                return ((Integer) invoke).intValue();
            } catch (Exception e8) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e8);
            }
        }
        return 0;
    }
}
