package b1;

import java.util.LinkedHashMap;
import java.util.Locale;
/* loaded from: classes.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private final LinkedHashMap f4519a;

    /* renamed from: b  reason: collision with root package name */
    private int f4520b;

    /* renamed from: c  reason: collision with root package name */
    private int f4521c;

    /* renamed from: d  reason: collision with root package name */
    private int f4522d;

    /* renamed from: e  reason: collision with root package name */
    private int f4523e;

    /* renamed from: f  reason: collision with root package name */
    private int f4524f;

    /* renamed from: g  reason: collision with root package name */
    private int f4525g;

    /* renamed from: h  reason: collision with root package name */
    private int f4526h;

    public e(int i8) {
        if (i8 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.f4521c = i8;
        this.f4519a = new LinkedHashMap(0, 0.75f, true);
    }

    private int e(Object obj, Object obj2) {
        int f8 = f(obj, obj2);
        if (f8 >= 0) {
            return f8;
        }
        throw new IllegalStateException("Negative size: " + obj + "=" + obj2);
    }

    protected Object a(Object obj) {
        return null;
    }

    protected void b(boolean z7, Object obj, Object obj2, Object obj3) {
    }

    public final Object c(Object obj) {
        Object put;
        if (obj != null) {
            synchronized (this) {
                Object obj2 = this.f4519a.get(obj);
                if (obj2 != null) {
                    this.f4525g++;
                    return obj2;
                }
                this.f4526h++;
                Object a8 = a(obj);
                if (a8 == null) {
                    return null;
                }
                synchronized (this) {
                    this.f4523e++;
                    put = this.f4519a.put(obj, a8);
                    if (put != null) {
                        this.f4519a.put(obj, put);
                    } else {
                        this.f4520b += e(obj, a8);
                    }
                }
                if (put != null) {
                    b(false, obj, a8, put);
                    return put;
                }
                g(this.f4521c);
                return a8;
            }
        }
        throw new NullPointerException("key == null");
    }

    public final Object d(Object obj, Object obj2) {
        Object put;
        if (obj == null || obj2 == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.f4522d++;
            this.f4520b += e(obj, obj2);
            put = this.f4519a.put(obj, obj2);
            if (put != null) {
                this.f4520b -= e(obj, put);
            }
        }
        if (put != null) {
            b(false, obj, put, obj2);
        }
        g(this.f4521c);
        return put;
    }

    protected int f(Object obj, Object obj2) {
        return 1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0070, code lost:
        throw new java.lang.IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void g(int r5) {
        /*
            r4 = this;
        L0:
            monitor-enter(r4)
            int r0 = r4.f4520b     // Catch: java.lang.Throwable -> L71
            if (r0 < 0) goto L52
            java.util.LinkedHashMap r0 = r4.f4519a     // Catch: java.lang.Throwable -> L71
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L71
            if (r0 == 0) goto L11
            int r0 = r4.f4520b     // Catch: java.lang.Throwable -> L71
            if (r0 != 0) goto L52
        L11:
            int r0 = r4.f4520b     // Catch: java.lang.Throwable -> L71
            if (r0 <= r5) goto L50
            java.util.LinkedHashMap r0 = r4.f4519a     // Catch: java.lang.Throwable -> L71
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L71
            if (r0 == 0) goto L1e
            goto L50
        L1e:
            java.util.LinkedHashMap r0 = r4.f4519a     // Catch: java.lang.Throwable -> L71
            java.util.Set r0 = r0.entrySet()     // Catch: java.lang.Throwable -> L71
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L71
            java.lang.Object r0 = r0.next()     // Catch: java.lang.Throwable -> L71
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch: java.lang.Throwable -> L71
            java.lang.Object r1 = r0.getKey()     // Catch: java.lang.Throwable -> L71
            java.lang.Object r0 = r0.getValue()     // Catch: java.lang.Throwable -> L71
            java.util.LinkedHashMap r2 = r4.f4519a     // Catch: java.lang.Throwable -> L71
            r2.remove(r1)     // Catch: java.lang.Throwable -> L71
            int r2 = r4.f4520b     // Catch: java.lang.Throwable -> L71
            int r3 = r4.e(r1, r0)     // Catch: java.lang.Throwable -> L71
            int r2 = r2 - r3
            r4.f4520b = r2     // Catch: java.lang.Throwable -> L71
            int r2 = r4.f4524f     // Catch: java.lang.Throwable -> L71
            r3 = 1
            int r2 = r2 + r3
            r4.f4524f = r2     // Catch: java.lang.Throwable -> L71
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L71
            r2 = 0
            r4.b(r3, r1, r0, r2)
            goto L0
        L50:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L71
            return
        L52:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L71
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L71
            r0.<init>()     // Catch: java.lang.Throwable -> L71
            java.lang.Class r1 = r4.getClass()     // Catch: java.lang.Throwable -> L71
            java.lang.String r1 = r1.getName()     // Catch: java.lang.Throwable -> L71
            r0.append(r1)     // Catch: java.lang.Throwable -> L71
            java.lang.String r1 = ".sizeOf() is reporting inconsistent results!"
            r0.append(r1)     // Catch: java.lang.Throwable -> L71
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L71
            r5.<init>(r0)     // Catch: java.lang.Throwable -> L71
            throw r5     // Catch: java.lang.Throwable -> L71
        L71:
            r5 = move-exception
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L71
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: b1.e.g(int):void");
    }

    public final synchronized String toString() {
        int i8;
        int i9;
        i8 = this.f4525g;
        i9 = this.f4526h + i8;
        return String.format(Locale.US, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", Integer.valueOf(this.f4521c), Integer.valueOf(this.f4525g), Integer.valueOf(this.f4526h), Integer.valueOf(i9 != 0 ? (i8 * 100) / i9 : 0));
    }
}
