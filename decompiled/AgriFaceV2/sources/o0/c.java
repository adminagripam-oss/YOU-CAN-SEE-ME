package o0;
/* loaded from: classes.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    private final o0.a f9809a;

    /* renamed from: b  reason: collision with root package name */
    private final d f9810b;

    /* renamed from: c  reason: collision with root package name */
    private final b f9811c;

    /* renamed from: d  reason: collision with root package name */
    private final int f9812d;

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private o0.a f9813a;

        /* renamed from: b  reason: collision with root package name */
        private d f9814b;

        /* renamed from: c  reason: collision with root package name */
        private b f9815c;

        /* renamed from: d  reason: collision with root package name */
        private int f9816d;

        public a() {
            this.f9813a = o0.a.f9805c;
            this.f9814b = null;
            this.f9815c = null;
            this.f9816d = 0;
        }

        public static a b(c cVar) {
            return new a(cVar);
        }

        public c a() {
            return new c(this.f9813a, this.f9814b, this.f9815c, this.f9816d);
        }

        public a c(int i8) {
            this.f9816d = i8;
            return this;
        }

        public a d(o0.a aVar) {
            this.f9813a = aVar;
            return this;
        }

        public a e(b bVar) {
            this.f9815c = bVar;
            return this;
        }

        public a f(d dVar) {
            this.f9814b = dVar;
            return this;
        }

        private a(c cVar) {
            this.f9813a = o0.a.f9805c;
            this.f9814b = null;
            this.f9815c = null;
            this.f9816d = 0;
            this.f9813a = cVar.b();
            this.f9814b = cVar.d();
            this.f9815c = cVar.c();
            this.f9816d = cVar.a();
        }
    }

    c(o0.a aVar, d dVar, b bVar, int i8) {
        this.f9809a = aVar;
        this.f9810b = dVar;
        this.f9811c = bVar;
        this.f9812d = i8;
    }

    public int a() {
        return this.f9812d;
    }

    public o0.a b() {
        return this.f9809a;
    }

    public b c() {
        return this.f9811c;
    }

    public d d() {
        return this.f9810b;
    }
}
