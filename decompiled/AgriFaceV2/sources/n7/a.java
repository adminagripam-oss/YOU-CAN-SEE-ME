package n7;
/* loaded from: classes.dex */
final class a extends b {

    /* renamed from: e  reason: collision with root package name */
    private final c7.p f9769e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: n7.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0133a extends v6.d {

        /* renamed from: h  reason: collision with root package name */
        Object f9770h;

        /* renamed from: i  reason: collision with root package name */
        /* synthetic */ Object f9771i;

        /* renamed from: k  reason: collision with root package name */
        int f9773k;

        C0133a(t6.d dVar) {
            super(dVar);
        }

        @Override // v6.a
        public final Object l(Object obj) {
            this.f9771i = obj;
            this.f9773k |= Integer.MIN_VALUE;
            return a.this.e(null, this);
        }
    }

    public a(c7.p pVar, t6.g gVar, int i8, m7.a aVar) {
        super(pVar, gVar, i8, aVar);
        this.f9769e = pVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x004c  */
    @Override // n7.b, o7.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object e(m7.r r5, t6.d r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof n7.a.C0133a
            if (r0 == 0) goto L13
            r0 = r6
            n7.a$a r0 = (n7.a.C0133a) r0
            int r1 = r0.f9773k
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f9773k = r1
            goto L18
        L13:
            n7.a$a r0 = new n7.a$a
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.f9771i
            java.lang.Object r1 = u6.b.c()
            int r2 = r0.f9773k
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r5 = r0.f9770h
            m7.r r5 = (m7.r) r5
            r6.l.b(r6)
            goto L43
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L35:
            r6.l.b(r6)
            r0.f9770h = r5
            r0.f9773k = r3
            java.lang.Object r6 = super.e(r5, r0)
            if (r6 != r1) goto L43
            return r1
        L43:
            boolean r5 = r5.o()
            if (r5 == 0) goto L4c
            r6.r r5 = r6.r.f11372a
            return r5
        L4c:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "'awaitClose { yourCallbackOrListener.cancel() }' should be used in the end of callbackFlow block.\nOtherwise, a callback/listener may leak in case of external cancellation.\nSee callbackFlow API documentation for the details."
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: n7.a.e(m7.r, t6.d):java.lang.Object");
    }

    @Override // o7.d
    protected o7.d f(t6.g gVar, int i8, m7.a aVar) {
        return new a(this.f9769e, gVar, i8, aVar);
    }

    public /* synthetic */ a(c7.p pVar, t6.g gVar, int i8, m7.a aVar, int i9, d7.g gVar2) {
        this(pVar, (i9 & 2) != 0 ? t6.h.f11916e : gVar, (i9 & 4) != 0 ? -2 : i8, (i9 & 8) != 0 ? m7.a.SUSPEND : aVar);
    }
}
