package a0;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import java.io.IOException;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class z implements l0.y {
    private Bitmap b(byte[] bArr, Rect rect) {
        try {
            return BitmapRegionDecoder.newInstance(bArr, 0, bArr.length, false).decodeRegion(rect, new BitmapFactory.Options());
        } catch (IOException e8) {
            throw new y.v0(1, "Failed to decode JPEG.", e8);
        }
    }

    @Override // l0.y
    /* renamed from: a */
    public l0.z apply(l0.z zVar) {
        Rect b8 = zVar.b();
        Bitmap b9 = b((byte[]) zVar.c(), b8);
        d0.g d8 = zVar.d();
        Objects.requireNonNull(d8);
        return l0.z.j(b9, d8, new Rect(0, 0, b9.getWidth(), b9.getHeight()), zVar.f(), d0.r.t(zVar.g(), b8), zVar.a());
    }
}
