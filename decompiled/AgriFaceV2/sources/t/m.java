package t;

import android.hardware.camera2.params.OutputConfiguration;
import android.view.Surface;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class m extends l {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        final OutputConfiguration f11614a;

        /* renamed from: b  reason: collision with root package name */
        String f11615b;

        /* renamed from: c  reason: collision with root package name */
        long f11616c = 1;

        a(OutputConfiguration outputConfiguration) {
            this.f11614a = outputConfiguration;
        }

        public boolean equals(Object obj) {
            if (obj instanceof a) {
                a aVar = (a) obj;
                return Objects.equals(this.f11614a, aVar.f11614a) && this.f11616c == aVar.f11616c && Objects.equals(this.f11615b, aVar.f11615b);
            }
            return false;
        }

        public int hashCode() {
            int hashCode = this.f11614a.hashCode() ^ 31;
            int i8 = (hashCode << 5) - hashCode;
            String str = this.f11615b;
            int hashCode2 = (str == null ? 0 : str.hashCode()) ^ i8;
            return Long.hashCode(this.f11616c) ^ ((hashCode2 << 5) - hashCode2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(int i8, Surface surface) {
        this(new a(new OutputConfiguration(i8, surface)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static m i(OutputConfiguration outputConfiguration) {
        return new m(new a(outputConfiguration));
    }

    @Override // t.k.a
    public void b(Surface surface) {
        ((OutputConfiguration) f()).addSurface(surface);
    }

    @Override // t.k.a
    public void c(long j8) {
        ((a) this.f11619a).f11616c = j8;
    }

    @Override // t.k.a
    public String d() {
        return ((a) this.f11619a).f11615b;
    }

    @Override // t.k.a
    public void e() {
        ((OutputConfiguration) f()).enableSurfaceSharing();
    }

    @Override // t.l, t.k.a
    public Object f() {
        g1.e.a(this.f11619a instanceof a);
        return ((a) this.f11619a).f11614a;
    }

    @Override // t.k.a
    public void g(String str) {
        ((a) this.f11619a).f11615b = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(Object obj) {
        super(obj);
    }
}
