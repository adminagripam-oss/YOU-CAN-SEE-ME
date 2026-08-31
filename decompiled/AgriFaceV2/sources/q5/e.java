package q5;

import a4.xe;
import a4.ye;
import java.util.concurrent.Executor;
import k3.p;
/* loaded from: classes.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private final int f10365a;

    /* renamed from: b  reason: collision with root package name */
    private final int f10366b;

    /* renamed from: c  reason: collision with root package name */
    private final int f10367c;

    /* renamed from: d  reason: collision with root package name */
    private final int f10368d;

    /* renamed from: e  reason: collision with root package name */
    private final boolean f10369e;

    /* renamed from: f  reason: collision with root package name */
    private final float f10370f;

    /* renamed from: g  reason: collision with root package name */
    private final Executor f10371g;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private int f10372a = 1;

        /* renamed from: b  reason: collision with root package name */
        private int f10373b = 1;

        /* renamed from: c  reason: collision with root package name */
        private int f10374c = 1;

        /* renamed from: d  reason: collision with root package name */
        private int f10375d = 1;

        /* renamed from: e  reason: collision with root package name */
        private boolean f10376e = false;

        /* renamed from: f  reason: collision with root package name */
        private float f10377f = 0.1f;

        /* renamed from: g  reason: collision with root package name */
        private Executor f10378g;

        public e a() {
            return new e(this.f10372a, this.f10373b, this.f10374c, this.f10375d, this.f10376e, this.f10377f, this.f10378g, null);
        }

        public a b() {
            this.f10376e = true;
            return this;
        }

        public a c(int i8) {
            this.f10374c = i8;
            return this;
        }

        public a d(int i8) {
            this.f10373b = i8;
            return this;
        }

        public a e(int i8) {
            this.f10372a = i8;
            return this;
        }

        public a f(float f8) {
            this.f10377f = f8;
            return this;
        }

        public a g(int i8) {
            this.f10375d = i8;
            return this;
        }
    }

    /* synthetic */ e(int i8, int i9, int i10, int i11, boolean z7, float f8, Executor executor, g gVar) {
        this.f10365a = i8;
        this.f10366b = i9;
        this.f10367c = i10;
        this.f10368d = i11;
        this.f10369e = z7;
        this.f10370f = f8;
        this.f10371g = executor;
    }

    public final float a() {
        return this.f10370f;
    }

    public final int b() {
        return this.f10367c;
    }

    public final int c() {
        return this.f10366b;
    }

    public final int d() {
        return this.f10365a;
    }

    public final int e() {
        return this.f10368d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof e) {
            e eVar = (e) obj;
            return Float.floatToIntBits(this.f10370f) == Float.floatToIntBits(eVar.f10370f) && p.a(Integer.valueOf(this.f10365a), Integer.valueOf(eVar.f10365a)) && p.a(Integer.valueOf(this.f10366b), Integer.valueOf(eVar.f10366b)) && p.a(Integer.valueOf(this.f10368d), Integer.valueOf(eVar.f10368d)) && p.a(Boolean.valueOf(this.f10369e), Boolean.valueOf(eVar.f10369e)) && p.a(Integer.valueOf(this.f10367c), Integer.valueOf(eVar.f10367c)) && p.a(this.f10371g, eVar.f10371g);
        }
        return false;
    }

    public final Executor f() {
        return this.f10371g;
    }

    public final boolean g() {
        return this.f10369e;
    }

    public int hashCode() {
        return p.b(Integer.valueOf(Float.floatToIntBits(this.f10370f)), Integer.valueOf(this.f10365a), Integer.valueOf(this.f10366b), Integer.valueOf(this.f10368d), Boolean.valueOf(this.f10369e), Integer.valueOf(this.f10367c), this.f10371g);
    }

    public String toString() {
        xe a8 = ye.a("FaceDetectorOptions");
        a8.b("landmarkMode", this.f10365a);
        a8.b("contourMode", this.f10366b);
        a8.b("classificationMode", this.f10367c);
        a8.b("performanceMode", this.f10368d);
        a8.d("trackingEnabled", this.f10369e);
        a8.a("minFaceSize", this.f10370f);
        return a8.toString();
    }
}
