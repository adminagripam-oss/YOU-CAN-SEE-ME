package a0;

import android.graphics.Bitmap;
import androidx.camera.core.ImageProcessingUtil;
import java.nio.ByteBuffer;
/* loaded from: classes.dex */
public class v implements l0.y {
    @Override // l0.y
    /* renamed from: a */
    public Bitmap apply(l0.z zVar) {
        androidx.camera.core.q qVar;
        Bitmap k8;
        androidx.camera.core.q qVar2 = null;
        try {
            try {
                if (zVar.e() == 35) {
                    androidx.camera.core.n nVar = (androidx.camera.core.n) zVar.c();
                    boolean z7 = zVar.f() % 180 != 0;
                    qVar = new androidx.camera.core.q(androidx.camera.core.o.a(z7 ? nVar.getHeight() : nVar.getWidth(), z7 ? nVar.getWidth() : nVar.getHeight(), 1, 2));
                    try {
                        androidx.camera.core.n g8 = ImageProcessingUtil.g(nVar, qVar, ByteBuffer.allocateDirect(nVar.getWidth() * nVar.getHeight() * 4), zVar.f(), false);
                        nVar.close();
                        if (g8 == null) {
                            throw new y.v0(0, "Can't covert YUV to RGB", null);
                        }
                        k8 = k0.b.b(g8);
                        g8.close();
                    } catch (UnsupportedOperationException e8) {
                        e = e8;
                        throw new y.v0(0, "Can't convert " + (zVar.e() == 35 ? "YUV" : "JPEG") + " to bitmap", e);
                    } catch (Throwable th) {
                        th = th;
                        qVar2 = qVar;
                        if (qVar2 != null) {
                            qVar2.close();
                        }
                        throw th;
                    }
                } else if (zVar.e() != 256) {
                    throw new IllegalArgumentException("Invalid postview image format : " + zVar.e());
                } else {
                    androidx.camera.core.n nVar2 = (androidx.camera.core.n) zVar.c();
                    Bitmap b8 = k0.b.b(nVar2);
                    nVar2.close();
                    qVar = null;
                    k8 = k0.b.k(b8, zVar.f());
                }
                if (qVar != null) {
                    qVar.close();
                }
                return k8;
            } catch (UnsupportedOperationException e9) {
                e = e9;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
