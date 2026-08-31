package t;

import android.hardware.camera2.params.OutputConfiguration;
import android.view.Surface;
/* loaded from: classes.dex */
public class o extends n {
    /* JADX INFO: Access modifiers changed from: package-private */
    public o(int i8, Surface surface) {
        this(new OutputConfiguration(i8, surface));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static o k(OutputConfiguration outputConfiguration) {
        return new o(outputConfiguration);
    }

    @Override // t.p, t.k.a
    public void a(long j8) {
        if (j8 == -1) {
            return;
        }
        ((OutputConfiguration) f()).setStreamUseCase(j8);
    }

    @Override // t.m, t.k.a
    public /* bridge */ /* synthetic */ void b(Surface surface) {
        super.b(surface);
    }

    @Override // t.n, t.m, t.k.a
    public void c(long j8) {
        ((OutputConfiguration) f()).setDynamicRangeProfile(j8);
    }

    @Override // t.n, t.m, t.k.a
    public /* bridge */ /* synthetic */ String d() {
        return super.d();
    }

    @Override // t.m, t.k.a
    public /* bridge */ /* synthetic */ void e() {
        super.e();
    }

    @Override // t.p
    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // t.n, t.m, t.l, t.k.a
    public Object f() {
        g1.e.a(this.f11619a instanceof OutputConfiguration);
        return this.f11619a;
    }

    @Override // t.n, t.m, t.k.a
    public /* bridge */ /* synthetic */ void g(String str) {
        super.g(str);
    }

    @Override // t.l, t.k.a
    public /* bridge */ /* synthetic */ Surface getSurface() {
        return super.getSurface();
    }

    @Override // t.p, t.k.a
    public void h(int i8) {
        ((OutputConfiguration) f()).setMirrorMode(i8);
    }

    @Override // t.p
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    o(Object obj) {
        super(obj);
    }
}
