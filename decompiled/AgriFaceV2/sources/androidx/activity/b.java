package androidx.activity;
/* loaded from: classes.dex */
public final class b {

    /* renamed from: e  reason: collision with root package name */
    public static final a f1399e = new a(null);

    /* renamed from: a  reason: collision with root package name */
    private final float f1400a;

    /* renamed from: b  reason: collision with root package name */
    private final float f1401b;

    /* renamed from: c  reason: collision with root package name */
    private final float f1402c;

    /* renamed from: d  reason: collision with root package name */
    private final int f1403d;

    /* loaded from: classes.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(d7.g gVar) {
            this();
        }
    }

    public b(float f8, float f9, float f10, int i8) {
        this.f1400a = f8;
        this.f1401b = f9;
        this.f1402c = f10;
        this.f1403d = i8;
    }

    public final float a() {
        return this.f1402c;
    }

    public String toString() {
        return "BackEventCompat{touchX=" + this.f1400a + ", touchY=" + this.f1401b + ", progress=" + this.f1402c + ", swipeEdge=" + this.f1403d + '}';
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public b(android.window.BackEvent r5) {
        /*
            r4 = this;
            java.lang.String r0 = "backEvent"
            d7.k.e(r5, r0)
            androidx.activity.a r0 = androidx.activity.a.f1398a
            float r1 = r0.d(r5)
            float r2 = r0.e(r5)
            float r3 = r0.b(r5)
            int r5 = r0.c(r5)
            r4.<init>(r1, r2, r3, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.activity.b.<init>(android.window.BackEvent):void");
    }
}
