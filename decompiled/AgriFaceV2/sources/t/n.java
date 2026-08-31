package t;

import android.hardware.camera2.params.OutputConfiguration;
import android.view.Surface;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class n extends m {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        final OutputConfiguration f11617a;

        /* renamed from: b  reason: collision with root package name */
        long f11618b = 1;

        a(OutputConfiguration outputConfiguration) {
            this.f11617a = outputConfiguration;
        }

        public boolean equals(Object obj) {
            if (obj instanceof a) {
                a aVar = (a) obj;
                return Objects.equals(this.f11617a, aVar.f11617a) && this.f11618b == aVar.f11618b;
            }
            return false;
        }

        public int hashCode() {
            int hashCode = this.f11617a.hashCode() ^ 31;
            return Long.hashCode(this.f11618b) ^ ((hashCode << 5) - hashCode);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(int i8, Surface surface) {
        this(new a(new OutputConfiguration(i8, surface)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static n j(OutputConfiguration outputConfiguration) {
        return new n(new a(outputConfiguration));
    }

    @Override // t.m, t.k.a
    public void c(long j8) {
        ((a) this.f11619a).f11618b = j8;
    }

    @Override // t.m, t.k.a
    public String d() {
        return null;
    }

    @Override // t.m, t.l, t.k.a
    public Object f() {
        g1.e.a(this.f11619a instanceof a);
        return ((a) this.f11619a).f11617a;
    }

    @Override // t.m, t.k.a
    public void g(String str) {
        ((OutputConfiguration) f()).setPhysicalCameraId(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(Object obj) {
        super(obj);
    }
}
