package com.google.crypto.tink.shaded.protobuf;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class j1 {

    /* renamed from: a  reason: collision with root package name */
    private static volatile int f6120a = 100;

    private final void l(Object obj, c1 c1Var, int i8) {
        while (c1Var.r() != Integer.MAX_VALUE && m(obj, c1Var, i8)) {
        }
    }

    abstract void a(Object obj, int i8, int i9);

    abstract void b(Object obj, int i8, long j8);

    abstract void c(Object obj, int i8, Object obj2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void d(Object obj, int i8, h hVar);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void e(Object obj, int i8, long j8);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Object f(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Object g(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int h(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int i(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void j(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Object k(Object obj, Object obj2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean m(Object obj, c1 c1Var, int i8) {
        int d8 = c1Var.d();
        int a8 = o1.a(d8);
        int b8 = o1.b(d8);
        if (b8 == 0) {
            e(obj, a8, c1Var.J());
            return true;
        } else if (b8 == 1) {
            b(obj, a8, c1Var.g());
            return true;
        } else if (b8 == 2) {
            d(obj, a8, c1Var.x());
            return true;
        } else if (b8 != 3) {
            if (b8 == 4) {
                if (i8 != 0) {
                    return false;
                }
                throw a0.b();
            } else if (b8 == 5) {
                a(obj, a8, c1Var.p());
                return true;
            } else {
                throw a0.e();
            }
        } else {
            Object n8 = n();
            int c8 = o1.c(a8, 4);
            int i9 = i8 + 1;
            if (i9 < f6120a) {
                l(n8, c1Var, i9);
                if (c8 == c1Var.d()) {
                    c(obj, a8, r(n8));
                    return true;
                }
                throw a0.b();
            }
            throw a0.i();
        }
    }

    abstract Object n();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void o(Object obj, Object obj2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void p(Object obj, Object obj2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean q(c1 c1Var);

    abstract Object r(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void s(Object obj, p1 p1Var);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void t(Object obj, p1 p1Var);
}
