package a0;

import android.graphics.Rect;
import android.util.Size;
import b0.l2;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Objects;
import k0.b;
/* loaded from: classes.dex */
final class w implements l0.y {

    /* renamed from: a  reason: collision with root package name */
    private final j0.d f132a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class a {
        /* JADX INFO: Access modifiers changed from: package-private */
        public static a c(l0.z zVar, int i8) {
            return new c(zVar, i8);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract int a();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract l0.z b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public w(l2 l2Var) {
        this.f132a = new j0.d(l2Var);
    }

    private static d0.g b(byte[] bArr) {
        try {
            return d0.g.k(new ByteArrayInputStream(bArr));
        } catch (IOException e8) {
            throw new y.v0(0, "Failed to extract Exif from YUV-generated JPEG", e8);
        }
    }

    private l0.z c(a aVar, int i8) {
        l0.z b8 = aVar.b();
        byte[] a8 = this.f132a.a((androidx.camera.core.n) b8.c());
        d0.g d8 = b8.d();
        Objects.requireNonNull(d8);
        return l0.z.m(a8, d8, i8, b8.h(), b8.b(), b8.f(), b8.g(), b8.a());
    }

    private l0.z d(a aVar) {
        l0.z b8 = aVar.b();
        androidx.camera.core.n nVar = (androidx.camera.core.n) b8.c();
        Rect b9 = b8.b();
        try {
            byte[] l8 = k0.b.l(nVar, b9, aVar.a(), b8.f());
            return l0.z.m(l8, b(l8), 256, new Size(b9.width(), b9.height()), new Rect(0, 0, b9.width(), b9.height()), b8.f(), d0.r.t(b8.g(), b9), b8.a());
        } catch (b.a e8) {
            throw new y.v0(1, "Failed to encode the image to JPEG.", e8);
        }
    }

    @Override // l0.y
    /* renamed from: a */
    public l0.z apply(a aVar) {
        l0.z d8;
        try {
            int e8 = aVar.b().e();
            if (e8 != 35) {
                if (e8 != 256 && e8 != 4101) {
                    throw new IllegalArgumentException("Unexpected format: " + e8);
                }
                d8 = c(aVar, e8);
            } else {
                d8 = d(aVar);
            }
            return d8;
        } finally {
            ((androidx.camera.core.n) aVar.b().c()).close();
        }
    }
}
