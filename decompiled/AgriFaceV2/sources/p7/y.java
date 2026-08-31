package p7;

import k7.o1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class y extends o1 implements k7.k0 {

    /* renamed from: g  reason: collision with root package name */
    private final Throwable f10200g;

    /* renamed from: h  reason: collision with root package name */
    private final String f10201h;

    public y(Throwable th, String str) {
        this.f10200g = th;
        this.f10201h = str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0023, code lost:
        if (r1 == null) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.Void V() {
        /*
            r4 = this;
            java.lang.Throwable r0 = r4.f10200g
            if (r0 == 0) goto L36
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Module with the Main dispatcher had failed to initialize"
            r0.append(r1)
            java.lang.String r1 = r4.f10201h
            if (r1 == 0) goto L25
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = ". "
            r2.append(r3)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            if (r1 != 0) goto L27
        L25:
            java.lang.String r1 = ""
        L27:
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.Throwable r2 = r4.f10200g
            r1.<init>(r0, r2)
            throw r1
        L36:
            p7.x.d()
            r6.d r0 = new r6.d
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p7.y.V():java.lang.Void");
    }

    @Override // k7.a0
    public boolean Q(t6.g gVar) {
        V();
        throw new r6.d();
    }

    @Override // k7.o1
    public o1 S() {
        return this;
    }

    @Override // k7.a0
    /* renamed from: U */
    public Void P(t6.g gVar, Runnable runnable) {
        V();
        throw new r6.d();
    }

    @Override // k7.a0
    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("Dispatchers.Main[missing");
        if (this.f10200g != null) {
            str = ", cause=" + this.f10200g;
        } else {
            str = "";
        }
        sb.append(str);
        sb.append(']');
        return sb.toString();
    }
}
