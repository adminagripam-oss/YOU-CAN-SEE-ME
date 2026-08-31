package t;

import android.hardware.camera2.params.OutputConfiguration;
import android.os.Build;
import android.view.Surface;
/* loaded from: classes.dex */
public final class k {

    /* renamed from: a  reason: collision with root package name */
    private final a f11613a;

    /* loaded from: classes.dex */
    interface a {
        void a(long j8);

        void b(Surface surface);

        void c(long j8);

        String d();

        void e();

        Object f();

        void g(String str);

        Surface getSurface();

        void h(int i8);
    }

    public k(int i8, Surface surface) {
        int i9 = Build.VERSION.SDK_INT;
        this.f11613a = i9 >= 33 ? new o(i8, surface) : i9 >= 28 ? new n(i8, surface) : new m(i8, surface);
    }

    public static k j(Object obj) {
        a j8;
        if (obj == null) {
            return null;
        }
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 33) {
            j8 = o.k((OutputConfiguration) obj);
        } else {
            OutputConfiguration outputConfiguration = (OutputConfiguration) obj;
            j8 = i8 >= 28 ? n.j(outputConfiguration) : m.i(outputConfiguration);
        }
        if (j8 == null) {
            return null;
        }
        return new k(j8);
    }

    public void a(Surface surface) {
        this.f11613a.b(surface);
    }

    public void b() {
        this.f11613a.e();
    }

    public String c() {
        return this.f11613a.d();
    }

    public Surface d() {
        return this.f11613a.getSurface();
    }

    public void e(long j8) {
        this.f11613a.c(j8);
    }

    public boolean equals(Object obj) {
        if (obj instanceof k) {
            return this.f11613a.equals(((k) obj).f11613a);
        }
        return false;
    }

    public void f(int i8) {
        this.f11613a.h(i8);
    }

    public void g(String str) {
        this.f11613a.g(str);
    }

    public void h(long j8) {
        this.f11613a.a(j8);
    }

    public int hashCode() {
        return this.f11613a.hashCode();
    }

    public Object i() {
        return this.f11613a.f();
    }

    public k(OutputConfiguration outputConfiguration) {
        this.f11613a = o.k(outputConfiguration);
    }

    private k(a aVar) {
        this.f11613a = aVar;
    }
}
