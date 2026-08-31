package androidx.appcompat.widget;
/* loaded from: classes.dex */
class u0 {

    /* renamed from: a  reason: collision with root package name */
    private int f2490a = 0;

    /* renamed from: b  reason: collision with root package name */
    private int f2491b = 0;

    /* renamed from: c  reason: collision with root package name */
    private int f2492c = Integer.MIN_VALUE;

    /* renamed from: d  reason: collision with root package name */
    private int f2493d = Integer.MIN_VALUE;

    /* renamed from: e  reason: collision with root package name */
    private int f2494e = 0;

    /* renamed from: f  reason: collision with root package name */
    private int f2495f = 0;

    /* renamed from: g  reason: collision with root package name */
    private boolean f2496g = false;

    /* renamed from: h  reason: collision with root package name */
    private boolean f2497h = false;

    public int a() {
        return this.f2496g ? this.f2490a : this.f2491b;
    }

    public int b() {
        return this.f2490a;
    }

    public int c() {
        return this.f2491b;
    }

    public int d() {
        return this.f2496g ? this.f2491b : this.f2490a;
    }

    public void e(int i8, int i9) {
        this.f2497h = false;
        if (i8 != Integer.MIN_VALUE) {
            this.f2494e = i8;
            this.f2490a = i8;
        }
        if (i9 != Integer.MIN_VALUE) {
            this.f2495f = i9;
            this.f2491b = i9;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x001a, code lost:
        if (r2 != Integer.MIN_VALUE) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0028, code lost:
        if (r2 != Integer.MIN_VALUE) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void f(boolean r2) {
        /*
            r1 = this;
            boolean r0 = r1.f2496g
            if (r2 != r0) goto L5
            return
        L5:
            r1.f2496g = r2
            boolean r0 = r1.f2497h
            if (r0 == 0) goto L2b
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r2 == 0) goto L1d
            int r2 = r1.f2493d
            if (r2 == r0) goto L14
            goto L16
        L14:
            int r2 = r1.f2494e
        L16:
            r1.f2490a = r2
            int r2 = r1.f2492c
            if (r2 == r0) goto L2f
            goto L31
        L1d:
            int r2 = r1.f2492c
            if (r2 == r0) goto L22
            goto L24
        L22:
            int r2 = r1.f2494e
        L24:
            r1.f2490a = r2
            int r2 = r1.f2493d
            if (r2 == r0) goto L2f
            goto L31
        L2b:
            int r2 = r1.f2494e
            r1.f2490a = r2
        L2f:
            int r2 = r1.f2495f
        L31:
            r1.f2491b = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.u0.f(boolean):void");
    }

    public void g(int i8, int i9) {
        this.f2492c = i8;
        this.f2493d = i9;
        this.f2497h = true;
        if (this.f2496g) {
            if (i9 != Integer.MIN_VALUE) {
                this.f2490a = i9;
            }
            if (i8 != Integer.MIN_VALUE) {
                this.f2491b = i8;
                return;
            }
            return;
        }
        if (i8 != Integer.MIN_VALUE) {
            this.f2490a = i8;
        }
        if (i9 != Integer.MIN_VALUE) {
            this.f2491b = i9;
        }
    }
}
