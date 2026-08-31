package o7;

import c7.p;
import java.util.ArrayList;
import k7.d0;
import k7.e0;
import k7.f0;
import k7.h0;
import m7.s;
import r6.r;
import s6.v;
/* loaded from: classes.dex */
public abstract class d implements h {

    /* renamed from: a  reason: collision with root package name */
    public final t6.g f9885a;

    /* renamed from: b  reason: collision with root package name */
    public final int f9886b;

    /* renamed from: c  reason: collision with root package name */
    public final m7.a f9887c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class a extends v6.k implements p {

        /* renamed from: i  reason: collision with root package name */
        int f9888i;

        /* renamed from: j  reason: collision with root package name */
        private /* synthetic */ Object f9889j;

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ n7.d f9890k;

        /* renamed from: l  reason: collision with root package name */
        final /* synthetic */ d f9891l;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(n7.d dVar, d dVar2, t6.d dVar3) {
            super(2, dVar3);
            this.f9890k = dVar;
            this.f9891l = dVar2;
        }

        @Override // v6.a
        public final t6.d b(Object obj, t6.d dVar) {
            a aVar = new a(this.f9890k, this.f9891l, dVar);
            aVar.f9889j = obj;
            return aVar;
        }

        @Override // v6.a
        public final Object l(Object obj) {
            Object c8;
            c8 = u6.d.c();
            int i8 = this.f9888i;
            if (i8 == 0) {
                r6.l.b(obj);
                n7.d dVar = this.f9890k;
                s i9 = this.f9891l.i((d0) this.f9889j);
                this.f9888i = 1;
                if (n7.e.b(dVar, i9, this) == c8) {
                    return c8;
                }
            } else if (i8 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                r6.l.b(obj);
            }
            return r.f11372a;
        }

        @Override // c7.p
        /* renamed from: o */
        public final Object f(d0 d0Var, t6.d dVar) {
            return ((a) b(d0Var, dVar)).l(r.f11372a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class b extends v6.k implements p {

        /* renamed from: i  reason: collision with root package name */
        int f9892i;

        /* renamed from: j  reason: collision with root package name */
        /* synthetic */ Object f9893j;

        b(t6.d dVar) {
            super(2, dVar);
        }

        @Override // v6.a
        public final t6.d b(Object obj, t6.d dVar) {
            b bVar = new b(dVar);
            bVar.f9893j = obj;
            return bVar;
        }

        @Override // v6.a
        public final Object l(Object obj) {
            Object c8;
            c8 = u6.d.c();
            int i8 = this.f9892i;
            if (i8 == 0) {
                r6.l.b(obj);
                d dVar = d.this;
                this.f9892i = 1;
                if (dVar.e((m7.r) this.f9893j, this) == c8) {
                    return c8;
                }
            } else if (i8 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                r6.l.b(obj);
            }
            return r.f11372a;
        }

        @Override // c7.p
        /* renamed from: o */
        public final Object f(m7.r rVar, t6.d dVar) {
            return ((b) b(rVar, dVar)).l(r.f11372a);
        }
    }

    public d(t6.g gVar, int i8, m7.a aVar) {
        this.f9885a = gVar;
        this.f9886b = i8;
        this.f9887c = aVar;
    }

    static /* synthetic */ Object d(d dVar, n7.d dVar2, t6.d dVar3) {
        Object c8;
        Object b8 = e0.b(new a(dVar2, dVar, null), dVar3);
        c8 = u6.d.c();
        return b8 == c8 ? b8 : r.f11372a;
    }

    @Override // n7.c
    public Object a(n7.d dVar, t6.d dVar2) {
        return d(this, dVar, dVar2);
    }

    @Override // o7.h
    public n7.c b(t6.g gVar, int i8, m7.a aVar) {
        t6.g M = gVar.M(this.f9885a);
        if (aVar == m7.a.SUSPEND) {
            int i9 = this.f9886b;
            if (i9 != -3) {
                if (i8 != -3) {
                    if (i9 != -2) {
                        if (i8 != -2 && (i9 = i9 + i8) < 0) {
                            i8 = Integer.MAX_VALUE;
                        }
                    }
                }
                i8 = i9;
            }
            aVar = this.f9887c;
        }
        return (d7.k.a(M, this.f9885a) && i8 == this.f9886b && aVar == this.f9887c) ? this : f(M, i8, aVar);
    }

    protected String c() {
        return null;
    }

    protected abstract Object e(m7.r rVar, t6.d dVar);

    protected abstract d f(t6.g gVar, int i8, m7.a aVar);

    public final p g() {
        return new b(null);
    }

    public final int h() {
        int i8 = this.f9886b;
        if (i8 == -3) {
            return -2;
        }
        return i8;
    }

    public s i(d0 d0Var) {
        return m7.p.c(d0Var, this.f9885a, h(), this.f9887c, f0.ATOMIC, null, g(), 16, null);
    }

    public String toString() {
        String s8;
        ArrayList arrayList = new ArrayList(4);
        String c8 = c();
        if (c8 != null) {
            arrayList.add(c8);
        }
        if (this.f9885a != t6.h.f11916e) {
            arrayList.add("context=" + this.f9885a);
        }
        if (this.f9886b != -3) {
            arrayList.add("capacity=" + this.f9886b);
        }
        if (this.f9887c != m7.a.SUSPEND) {
            arrayList.add("onBufferOverflow=" + this.f9887c);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(h0.a(this));
        sb.append('[');
        s8 = v.s(arrayList, ", ", null, null, 0, null, null, 62, null);
        sb.append(s8);
        sb.append(']');
        return sb.toString();
    }
}
