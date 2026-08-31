package com.google.crypto.tink.shaded.protobuf;
/* loaded from: classes.dex */
class l1 extends j1 {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.crypto.tink.shaded.protobuf.j1
    /* renamed from: A */
    public k1 g(Object obj) {
        return ((x) obj).unknownFields;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.crypto.tink.shaded.protobuf.j1
    /* renamed from: B */
    public int h(k1 k1Var) {
        return k1Var.d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.crypto.tink.shaded.protobuf.j1
    /* renamed from: C */
    public int i(k1 k1Var) {
        return k1Var.e();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.crypto.tink.shaded.protobuf.j1
    /* renamed from: D */
    public k1 k(k1 k1Var, k1 k1Var2) {
        return k1.c().equals(k1Var2) ? k1Var : k1.c().equals(k1Var) ? k1.j(k1Var, k1Var2) : k1Var.i(k1Var2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.crypto.tink.shaded.protobuf.j1
    /* renamed from: E */
    public k1 n() {
        return k1.k();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.crypto.tink.shaded.protobuf.j1
    /* renamed from: F */
    public void o(Object obj, k1 k1Var) {
        p(obj, k1Var);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.crypto.tink.shaded.protobuf.j1
    /* renamed from: G */
    public void p(Object obj, k1 k1Var) {
        ((x) obj).unknownFields = k1Var;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.crypto.tink.shaded.protobuf.j1
    /* renamed from: H */
    public k1 r(k1 k1Var) {
        k1Var.h();
        return k1Var;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.crypto.tink.shaded.protobuf.j1
    /* renamed from: I */
    public void s(k1 k1Var, p1 p1Var) {
        k1Var.p(p1Var);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.crypto.tink.shaded.protobuf.j1
    /* renamed from: J */
    public void t(k1 k1Var, p1 p1Var) {
        k1Var.r(p1Var);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.crypto.tink.shaded.protobuf.j1
    public void j(Object obj) {
        g(obj).h();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.crypto.tink.shaded.protobuf.j1
    public boolean q(c1 c1Var) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.crypto.tink.shaded.protobuf.j1
    /* renamed from: u */
    public void a(k1 k1Var, int i8, int i9) {
        k1Var.n(o1.c(i8, 5), Integer.valueOf(i9));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.crypto.tink.shaded.protobuf.j1
    /* renamed from: v */
    public void b(k1 k1Var, int i8, long j8) {
        k1Var.n(o1.c(i8, 1), Long.valueOf(j8));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.crypto.tink.shaded.protobuf.j1
    /* renamed from: w */
    public void c(k1 k1Var, int i8, k1 k1Var2) {
        k1Var.n(o1.c(i8, 3), k1Var2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.crypto.tink.shaded.protobuf.j1
    /* renamed from: x */
    public void d(k1 k1Var, int i8, h hVar) {
        k1Var.n(o1.c(i8, 2), hVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.crypto.tink.shaded.protobuf.j1
    /* renamed from: y */
    public void e(k1 k1Var, int i8, long j8) {
        k1Var.n(o1.c(i8, 0), Long.valueOf(j8));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.crypto.tink.shaded.protobuf.j1
    /* renamed from: z */
    public k1 f(Object obj) {
        k1 g8 = g(obj);
        if (g8 == k1.c()) {
            k1 k8 = k1.k();
            p(obj, k8);
            return k8;
        }
        return g8;
    }
}
