package z3;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class wc extends oc {

    /* renamed from: j  reason: collision with root package name */
    static final oc f14285j = new wc(null, new Object[0], 0);

    /* renamed from: h  reason: collision with root package name */
    final transient Object[] f14286h;

    /* renamed from: i  reason: collision with root package name */
    private final transient int f14287i;

    private wc(Object obj, Object[] objArr, int i8) {
        this.f14286h = objArr;
        this.f14287i = i8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static wc g(int i8, Object[] objArr, nc ncVar) {
        Object obj = objArr[0];
        obj.getClass();
        Object obj2 = objArr[1];
        obj2.getClass();
        i7.a(obj, obj2);
        return new wc(null, objArr, 1);
    }

    @Override // z3.oc
    final la a() {
        return new vc(this.f14286h, 1, this.f14287i);
    }

    @Override // z3.oc
    final pc d() {
        return new tc(this, this.f14286h, 0, this.f14287i);
    }

    @Override // z3.oc
    final pc e() {
        return new uc(this, new vc(this.f14286h, 0, this.f14287i));
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0020 A[RETURN] */
    @Override // z3.oc, java.util.Map
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object get(java.lang.Object r5) {
        /*
            r4 = this;
            java.lang.Object[] r0 = r4.f14286h
            int r1 = r4.f14287i
            r2 = 0
            if (r5 != 0) goto L9
        L7:
            r5 = r2
            goto L1d
        L9:
            r3 = 1
            if (r1 != r3) goto L7
            r1 = 0
            r1 = r0[r1]
            r1.getClass()
            boolean r5 = r1.equals(r5)
            if (r5 == 0) goto L7
            r5 = r0[r3]
            r5.getClass()
        L1d:
            if (r5 != 0) goto L20
            return r2
        L20:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: z3.wc.get(java.lang.Object):java.lang.Object");
    }

    @Override // java.util.Map
    public final int size() {
        return this.f14287i;
    }
}
