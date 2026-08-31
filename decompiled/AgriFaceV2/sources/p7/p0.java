package p7;

import k7.u1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class p0 {

    /* renamed from: a  reason: collision with root package name */
    public final t6.g f10176a;

    /* renamed from: b  reason: collision with root package name */
    private final Object[] f10177b;

    /* renamed from: c  reason: collision with root package name */
    private final u1[] f10178c;

    /* renamed from: d  reason: collision with root package name */
    private int f10179d;

    public p0(t6.g gVar, int i8) {
        this.f10176a = gVar;
        this.f10177b = new Object[i8];
        this.f10178c = new u1[i8];
    }

    public final void a(u1 u1Var, Object obj) {
        Object[] objArr = this.f10177b;
        int i8 = this.f10179d;
        objArr[i8] = obj;
        u1[] u1VarArr = this.f10178c;
        this.f10179d = i8 + 1;
        d7.k.c(u1Var, "null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
        u1VarArr[i8] = u1Var;
    }

    public final void b(t6.g gVar) {
        int length = this.f10178c.length - 1;
        if (length < 0) {
            return;
        }
        while (true) {
            int i8 = length - 1;
            u1 u1Var = this.f10178c[length];
            d7.k.b(u1Var);
            u1Var.n(gVar, this.f10177b[length]);
            if (i8 < 0) {
                return;
            }
            length = i8;
        }
    }
}
