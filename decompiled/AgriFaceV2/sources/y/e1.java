package y;
/* loaded from: classes.dex */
public class e1 {

    /* renamed from: f  reason: collision with root package name */
    public static final e1 f12769f = new b().b(1.0f).d(0.0f).e(0.0f).f(1.0f).c(1.0f).a();

    /* renamed from: a  reason: collision with root package name */
    private final float f12770a;

    /* renamed from: b  reason: collision with root package name */
    private final float f12771b;

    /* renamed from: c  reason: collision with root package name */
    private final float f12772c;

    /* renamed from: d  reason: collision with root package name */
    private final float f12773d;

    /* renamed from: e  reason: collision with root package name */
    private final float f12774e;

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        private float f12775a = 1.0f;

        /* renamed from: b  reason: collision with root package name */
        private float f12776b = 0.0f;

        /* renamed from: c  reason: collision with root package name */
        private float f12777c = 0.0f;

        /* renamed from: d  reason: collision with root package name */
        private float f12778d = 0.0f;

        /* renamed from: e  reason: collision with root package name */
        private float f12779e = 0.0f;

        public e1 a() {
            return new e1(this.f12775a, this.f12776b, this.f12777c, this.f12778d, this.f12779e);
        }

        public b b(float f8) {
            this.f12775a = f8;
            return this;
        }

        public b c(float f8) {
            this.f12779e = f8;
            return this;
        }

        public b d(float f8) {
            this.f12776b = f8;
            return this;
        }

        public b e(float f8) {
            this.f12777c = f8;
            return this;
        }

        public b f(float f8) {
            this.f12778d = f8;
            return this;
        }
    }

    private e1(float f8, float f9, float f10, float f11, float f12) {
        this.f12770a = f8;
        this.f12771b = f9;
        this.f12772c = f10;
        this.f12773d = f11;
        this.f12774e = f12;
    }

    public float a() {
        return this.f12770a;
    }

    public float b() {
        return this.f12774e;
    }

    public float c() {
        return this.f12771b;
    }

    public float d() {
        return this.f12772c;
    }

    public float e() {
        return this.f12773d;
    }
}
