package p7;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
/* loaded from: classes.dex */
public final class u {

    /* renamed from: e  reason: collision with root package name */
    public static final a f10187e = new a(null);

    /* renamed from: f  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f10188f = AtomicReferenceFieldUpdater.newUpdater(u.class, Object.class, "_next");

    /* renamed from: g  reason: collision with root package name */
    private static final AtomicLongFieldUpdater f10189g = AtomicLongFieldUpdater.newUpdater(u.class, "_state");

    /* renamed from: h  reason: collision with root package name */
    public static final h0 f10190h = new h0("REMOVE_FROZEN");
    private volatile Object _next;
    private volatile long _state;

    /* renamed from: a  reason: collision with root package name */
    private final int f10191a;

    /* renamed from: b  reason: collision with root package name */
    private final boolean f10192b;

    /* renamed from: c  reason: collision with root package name */
    private final int f10193c;

    /* renamed from: d  reason: collision with root package name */
    private final AtomicReferenceArray f10194d;

    /* loaded from: classes.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(d7.g gVar) {
            this();
        }

        public final int a(long j8) {
            return (j8 & 2305843009213693952L) != 0 ? 2 : 1;
        }

        public final long b(long j8, int i8) {
            return d(j8, 1073741823L) | (i8 << 0);
        }

        public final long c(long j8, int i8) {
            return d(j8, 1152921503533105152L) | (i8 << 30);
        }

        public final long d(long j8, long j9) {
            return j8 & (~j9);
        }
    }

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        public final int f10195a;

        public b(int i8) {
            this.f10195a = i8;
        }
    }

    public u(int i8, boolean z7) {
        this.f10191a = i8;
        this.f10192b = z7;
        int i9 = i8 - 1;
        this.f10193c = i9;
        this.f10194d = new AtomicReferenceArray(i8);
        if (!(i9 <= 1073741823)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        if (!((i8 & i9) == 0)) {
            throw new IllegalStateException("Check failed.".toString());
        }
    }

    private final u b(long j8) {
        u uVar = new u(this.f10191a * 2, this.f10192b);
        int i8 = (int) ((1073741823 & j8) >> 0);
        int i9 = (int) ((1152921503533105152L & j8) >> 30);
        while (true) {
            int i10 = this.f10193c;
            if ((i8 & i10) == (i9 & i10)) {
                f10189g.set(uVar, f10187e.d(j8, 1152921504606846976L));
                return uVar;
            }
            Object obj = this.f10194d.get(i10 & i8);
            if (obj == null) {
                obj = new b(i8);
            }
            uVar.f10194d.set(uVar.f10193c & i8, obj);
            i8++;
        }
    }

    private final u c(long j8) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f10188f;
        while (true) {
            u uVar = (u) atomicReferenceFieldUpdater.get(this);
            if (uVar != null) {
                return uVar;
            }
            androidx.concurrent.futures.b.a(f10188f, this, null, b(j8));
        }
    }

    private final u e(int i8, Object obj) {
        Object obj2 = this.f10194d.get(this.f10193c & i8);
        if ((obj2 instanceof b) && ((b) obj2).f10195a == i8) {
            this.f10194d.set(i8 & this.f10193c, obj);
            return this;
        }
        return null;
    }

    private final long h() {
        long j8;
        long j9;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f10189g;
        do {
            j8 = atomicLongFieldUpdater.get(this);
            if ((j8 & 1152921504606846976L) != 0) {
                return j8;
            }
            j9 = j8 | 1152921504606846976L;
        } while (!atomicLongFieldUpdater.compareAndSet(this, j8, j9));
        return j9;
    }

    private final u k(int i8, int i9) {
        long j8;
        int i10;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f10189g;
        do {
            j8 = atomicLongFieldUpdater.get(this);
            i10 = (int) ((1073741823 & j8) >> 0);
            if ((1152921504606846976L & j8) != 0) {
                return i();
            }
        } while (!f10189g.compareAndSet(this, j8, f10187e.b(j8, i9)));
        this.f10194d.set(this.f10193c & i10, null);
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0050, code lost:
        return 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int a(java.lang.Object r14) {
        /*
            r13 = this;
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = p7.u.f10189g
        L2:
            long r3 = r0.get(r13)
            r1 = 3458764513820540928(0x3000000000000000, double:1.727233711018889E-77)
            long r1 = r1 & r3
            r7 = 0
            int r1 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r1 == 0) goto L16
            p7.u$a r14 = p7.u.f10187e
            int r14 = r14.a(r3)
            return r14
        L16:
            r1 = 1073741823(0x3fffffff, double:5.304989472E-315)
            long r1 = r1 & r3
            r9 = 0
            long r1 = r1 >> r9
            int r1 = (int) r1
            r5 = 1152921503533105152(0xfffffffc0000000, double:1.2882296003504729E-231)
            long r5 = r5 & r3
            r2 = 30
            long r5 = r5 >> r2
            int r10 = (int) r5
            int r11 = r13.f10193c
            int r2 = r10 + 2
            r2 = r2 & r11
            r5 = r1 & r11
            r6 = 1
            if (r2 != r5) goto L32
            return r6
        L32:
            boolean r2 = r13.f10192b
            r5 = 1073741823(0x3fffffff, float:1.9999999)
            if (r2 != 0) goto L51
            java.util.concurrent.atomic.AtomicReferenceArray r2 = r13.f10194d
            r12 = r10 & r11
            java.lang.Object r2 = r2.get(r12)
            if (r2 == 0) goto L51
            int r2 = r13.f10191a
            r3 = 1024(0x400, float:1.435E-42)
            if (r2 < r3) goto L50
            int r10 = r10 - r1
            r1 = r10 & r5
            int r2 = r2 >> 1
            if (r1 <= r2) goto L2
        L50:
            return r6
        L51:
            int r1 = r10 + 1
            r1 = r1 & r5
            java.util.concurrent.atomic.AtomicLongFieldUpdater r2 = p7.u.f10189g
            p7.u$a r5 = p7.u.f10187e
            long r5 = r5.c(r3, r1)
            r1 = r2
            r2 = r13
            boolean r1 = r1.compareAndSet(r2, r3, r5)
            if (r1 == 0) goto L2
            java.util.concurrent.atomic.AtomicReferenceArray r0 = r13.f10194d
            r1 = r10 & r11
            r0.set(r1, r14)
            r0 = r13
        L6c:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r1 = p7.u.f10189g
            long r1 = r1.get(r0)
            r3 = 1152921504606846976(0x1000000000000000, double:1.2882297539194267E-231)
            long r1 = r1 & r3
            int r1 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r1 == 0) goto L83
            p7.u r0 = r0.i()
            p7.u r0 = r0.e(r10, r14)
            if (r0 != 0) goto L6c
        L83:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: p7.u.a(java.lang.Object):int");
    }

    public final boolean d() {
        long j8;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f10189g;
        do {
            j8 = atomicLongFieldUpdater.get(this);
            if ((j8 & 2305843009213693952L) != 0) {
                return true;
            }
            if ((1152921504606846976L & j8) != 0) {
                return false;
            }
        } while (!atomicLongFieldUpdater.compareAndSet(this, j8, j8 | 2305843009213693952L));
        return true;
    }

    public final int f() {
        long j8 = f10189g.get(this);
        return (((int) ((j8 & 1152921503533105152L) >> 30)) - ((int) ((1073741823 & j8) >> 0))) & 1073741823;
    }

    public final boolean g() {
        long j8 = f10189g.get(this);
        return ((int) ((1073741823 & j8) >> 0)) == ((int) ((j8 & 1152921503533105152L) >> 30));
    }

    public final u i() {
        return c(h());
    }

    public final Object j() {
        AtomicLongFieldUpdater atomicLongFieldUpdater = f10189g;
        while (true) {
            long j8 = atomicLongFieldUpdater.get(this);
            if ((1152921504606846976L & j8) != 0) {
                return f10190h;
            }
            int i8 = (int) ((1073741823 & j8) >> 0);
            int i9 = this.f10193c;
            if ((((int) ((1152921503533105152L & j8) >> 30)) & i9) == (i8 & i9)) {
                return null;
            }
            Object obj = this.f10194d.get(i9 & i8);
            if (obj == null) {
                if (this.f10192b) {
                    return null;
                }
            } else if (obj instanceof b) {
                return null;
            } else {
                int i10 = (i8 + 1) & 1073741823;
                if (f10189g.compareAndSet(this, j8, f10187e.b(j8, i10))) {
                    this.f10194d.set(this.f10193c & i8, null);
                    return obj;
                } else if (this.f10192b) {
                    u uVar = this;
                    do {
                        uVar = uVar.k(i8, i10);
                    } while (uVar != null);
                    return obj;
                }
            }
        }
    }
}
