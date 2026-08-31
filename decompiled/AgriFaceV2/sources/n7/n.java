package n7;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import r6.r;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class n extends o7.a implements j, c, o7.h {

    /* renamed from: e  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f9782e = AtomicReferenceFieldUpdater.newUpdater(n.class, Object.class, "_state");
    private volatile Object _state;

    /* renamed from: d  reason: collision with root package name */
    private int f9783d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class a extends v6.d {

        /* renamed from: h  reason: collision with root package name */
        Object f9784h;

        /* renamed from: i  reason: collision with root package name */
        Object f9785i;

        /* renamed from: j  reason: collision with root package name */
        Object f9786j;

        /* renamed from: k  reason: collision with root package name */
        Object f9787k;

        /* renamed from: l  reason: collision with root package name */
        Object f9788l;

        /* renamed from: m  reason: collision with root package name */
        /* synthetic */ Object f9789m;

        /* renamed from: o  reason: collision with root package name */
        int f9791o;

        a(t6.d dVar) {
            super(dVar);
        }

        @Override // v6.a
        public final Object l(Object obj) {
            this.f9789m = obj;
            this.f9791o |= Integer.MIN_VALUE;
            return n.this.a(null, this);
        }
    }

    public n(Object obj) {
        this._state = obj;
    }

    private final boolean k(Object obj, Object obj2) {
        int i8;
        o7.c[] h8;
        synchronized (this) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f9782e;
            Object obj3 = atomicReferenceFieldUpdater.get(this);
            if (obj != null && !d7.k.a(obj3, obj)) {
                return false;
            }
            if (d7.k.a(obj3, obj2)) {
                return true;
            }
            atomicReferenceFieldUpdater.set(this, obj2);
            int i9 = this.f9783d;
            if ((i9 & 1) != 0) {
                this.f9783d = i9 + 2;
                return true;
            }
            int i10 = i9 + 1;
            this.f9783d = i10;
            o7.c[] h9 = h();
            r rVar = r.f11372a;
            while (true) {
                p[] pVarArr = (p[]) h9;
                if (pVarArr != null) {
                    for (p pVar : pVarArr) {
                        if (pVar != null) {
                            pVar.g();
                        }
                    }
                }
                synchronized (this) {
                    i8 = this.f9783d;
                    if (i8 == i10) {
                        this.f9783d = i10 + 1;
                        return true;
                    }
                    h8 = h();
                    r rVar2 = r.f11372a;
                }
                h9 = h8;
                i10 = i8;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0096 A[Catch: all -> 0x0072, TryCatch #0 {all -> 0x0072, blocks: (B:15:0x003e, B:29:0x008e, B:31:0x0096, B:33:0x009b, B:43:0x00bc, B:45:0x00c2, B:35:0x00a1, B:39:0x00a8, B:20:0x005c, B:23:0x006e, B:28:0x007f), top: B:50:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x009b A[Catch: all -> 0x0072, TryCatch #0 {all -> 0x0072, blocks: (B:15:0x003e, B:29:0x008e, B:31:0x0096, B:33:0x009b, B:43:0x00bc, B:45:0x00c2, B:35:0x00a1, B:39:0x00a8, B:20:0x005c, B:23:0x006e, B:28:0x007f), top: B:50:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00ba A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00c2 A[Catch: all -> 0x0072, TRY_LEAVE, TryCatch #0 {all -> 0x0072, blocks: (B:15:0x003e, B:29:0x008e, B:31:0x0096, B:33:0x009b, B:43:0x00bc, B:45:0x00c2, B:35:0x00a1, B:39:0x00a8, B:20:0x005c, B:23:0x006e, B:28:0x007f), top: B:50:0x0023 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x00c0 -> B:29:0x008e). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:46:0x00d2 -> B:29:0x008e). Please submit an issue!!! */
    @Override // n7.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object a(n7.d r11, t6.d r12) {
        /*
            Method dump skipped, instructions count: 217
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: n7.n.a(n7.d, t6.d):java.lang.Object");
    }

    @Override // o7.h
    public c b(t6.g gVar, int i8, m7.a aVar) {
        return o.d(this, gVar, i8, aVar);
    }

    @Override // n7.d
    public Object c(Object obj, t6.d dVar) {
        setValue(obj);
        return r.f11372a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // o7.a
    /* renamed from: i */
    public p e() {
        return new p();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // o7.a
    /* renamed from: j */
    public p[] f(int i8) {
        return new p[i8];
    }

    @Override // n7.j
    public void setValue(Object obj) {
        if (obj == null) {
            obj = o7.i.f9899a;
        }
        k(null, obj);
    }
}
