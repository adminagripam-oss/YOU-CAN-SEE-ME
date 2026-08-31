package h2;

import android.graphics.Rect;
import h2.c;
/* loaded from: classes.dex */
public final class d implements c {

    /* renamed from: d  reason: collision with root package name */
    public static final a f7179d = new a(null);

    /* renamed from: a  reason: collision with root package name */
    private final e2.b f7180a;

    /* renamed from: b  reason: collision with root package name */
    private final b f7181b;

    /* renamed from: c  reason: collision with root package name */
    private final c.b f7182c;

    /* loaded from: classes.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(d7.g gVar) {
            this();
        }

        public final void a(e2.b bVar) {
            d7.k.e(bVar, "bounds");
            boolean z7 = false;
            if (!((bVar.d() == 0 && bVar.a() == 0) ? false : true)) {
                throw new IllegalArgumentException("Bounds must be non zero".toString());
            }
            if (bVar.b() == 0 || bVar.c() == 0) {
                z7 = true;
            }
            if (!z7) {
                throw new IllegalArgumentException("Bounding rectangle must start at the top or left window edge for folding features".toString());
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: b  reason: collision with root package name */
        public static final a f7183b = new a(null);

        /* renamed from: c  reason: collision with root package name */
        private static final b f7184c = new b("FOLD");

        /* renamed from: d  reason: collision with root package name */
        private static final b f7185d = new b("HINGE");

        /* renamed from: a  reason: collision with root package name */
        private final String f7186a;

        /* loaded from: classes.dex */
        public static final class a {
            private a() {
            }

            public /* synthetic */ a(d7.g gVar) {
                this();
            }

            public final b a() {
                return b.f7184c;
            }

            public final b b() {
                return b.f7185d;
            }
        }

        private b(String str) {
            this.f7186a = str;
        }

        public String toString() {
            return this.f7186a;
        }
    }

    public d(e2.b bVar, b bVar2, c.b bVar3) {
        d7.k.e(bVar, "featureBounds");
        d7.k.e(bVar2, "type");
        d7.k.e(bVar3, "state");
        this.f7180a = bVar;
        this.f7181b = bVar2;
        this.f7182c = bVar3;
        f7179d.a(bVar);
    }

    @Override // h2.a
    public Rect a() {
        return this.f7180a.f();
    }

    @Override // h2.c
    public c.b b() {
        return this.f7182c;
    }

    @Override // h2.c
    public c.a c() {
        return (this.f7180a.d() == 0 || this.f7180a.a() == 0) ? c.a.f7172c : c.a.f7173d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (d7.k.a(d.class, obj != null ? obj.getClass() : null)) {
            d7.k.c(obj, "null cannot be cast to non-null type androidx.window.layout.HardwareFoldingFeature");
            d dVar = (d) obj;
            return d7.k.a(this.f7180a, dVar.f7180a) && d7.k.a(this.f7181b, dVar.f7181b) && d7.k.a(b(), dVar.b());
        }
        return false;
    }

    public int hashCode() {
        return (((this.f7180a.hashCode() * 31) + this.f7181b.hashCode()) * 31) + b().hashCode();
    }

    public String toString() {
        return d.class.getSimpleName() + " { " + this.f7180a + ", type=" + this.f7181b + ", state=" + b() + " }";
    }
}
