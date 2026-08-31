package w5;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class r implements p {

    /* renamed from: a  reason: collision with root package name */
    final String f12554a;

    /* renamed from: b  reason: collision with root package name */
    final int f12555b;

    /* renamed from: c  reason: collision with root package name */
    final int f12556c;

    /* renamed from: d  reason: collision with root package name */
    private final LinkedList f12557d = new LinkedList();

    /* renamed from: e  reason: collision with root package name */
    private final Set f12558e = new HashSet();

    /* renamed from: f  reason: collision with root package name */
    private final Set f12559f = new HashSet();

    /* renamed from: g  reason: collision with root package name */
    private final Map f12560g = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public r(String str, int i8, int i9) {
        this.f12554a = str;
        this.f12555b = i8;
        this.f12556c = i9;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x002b, code lost:
        r0.remove();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002f, code lost:
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private synchronized w5.m g(w5.o r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.util.LinkedList r0 = r4.f12557d     // Catch: java.lang.Throwable -> L32
            java.util.ListIterator r0 = r0.listIterator()     // Catch: java.lang.Throwable -> L32
        L7:
            boolean r1 = r0.hasNext()     // Catch: java.lang.Throwable -> L32
            r2 = 0
            if (r1 == 0) goto L30
            java.lang.Object r1 = r0.next()     // Catch: java.lang.Throwable -> L32
            w5.m r1 = (w5.m) r1     // Catch: java.lang.Throwable -> L32
            java.lang.Integer r3 = r1.a()     // Catch: java.lang.Throwable -> L32
            if (r3 == 0) goto L26
            java.util.Map r2 = r4.f12560g     // Catch: java.lang.Throwable -> L32
            java.lang.Integer r3 = r1.a()     // Catch: java.lang.Throwable -> L32
            java.lang.Object r2 = r2.get(r3)     // Catch: java.lang.Throwable -> L32
            w5.o r2 = (w5.o) r2     // Catch: java.lang.Throwable -> L32
        L26:
            if (r2 == 0) goto L2b
            if (r2 == r5) goto L2b
            goto L7
        L2b:
            r0.remove()     // Catch: java.lang.Throwable -> L32
            monitor-exit(r4)
            return r1
        L30:
            monitor-exit(r4)
            return r2
        L32:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: w5.r.g(w5.o):w5.m");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i */
    public synchronized void h(o oVar) {
        HashSet<o> hashSet = new HashSet(this.f12558e);
        this.f12559f.remove(oVar);
        this.f12558e.add(oVar);
        if (!oVar.b() && oVar.d() != null) {
            this.f12560g.remove(oVar.d());
        }
        j(oVar);
        for (o oVar2 : hashSet) {
            j(oVar2);
        }
    }

    private synchronized void j(o oVar) {
        m g8 = g(oVar);
        if (g8 != null) {
            this.f12559f.add(oVar);
            this.f12558e.remove(oVar);
            if (g8.a() != null) {
                this.f12560g.put(g8.a(), oVar);
            }
            oVar.e(g8);
        }
    }

    @Override // w5.p
    public synchronized void b() {
        for (o oVar : this.f12558e) {
            oVar.f();
        }
        for (o oVar2 : this.f12559f) {
            oVar2.f();
        }
    }

    @Override // w5.p
    public synchronized void c(m mVar) {
        this.f12557d.add(mVar);
        for (o oVar : new HashSet(this.f12558e)) {
            j(oVar);
        }
    }

    protected o f(String str, int i8) {
        return new o(str, i8);
    }

    @Override // w5.p
    public synchronized void start() {
        for (int i8 = 0; i8 < this.f12555b; i8++) {
            final o f8 = f(this.f12554a + i8, this.f12556c);
            f8.g(new Runnable() { // from class: w5.q
                @Override // java.lang.Runnable
                public final void run() {
                    r.this.h(f8);
                }
            });
            this.f12558e.add(f8);
        }
    }
}
