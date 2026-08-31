package j0;

import androidx.camera.core.internal.compat.quirk.IncorrectJpegMetadataQuirk;
import androidx.camera.core.n;
import b0.l2;
import java.nio.ByteBuffer;
/* loaded from: classes.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private final IncorrectJpegMetadataQuirk f8183a;

    public d(l2 l2Var) {
        this.f8183a = (IncorrectJpegMetadataQuirk) l2Var.b(IncorrectJpegMetadataQuirk.class);
    }

    public byte[] a(n nVar) {
        IncorrectJpegMetadataQuirk incorrectJpegMetadataQuirk = this.f8183a;
        if (incorrectJpegMetadataQuirk == null) {
            ByteBuffer c8 = nVar.i()[0].c();
            byte[] bArr = new byte[c8.capacity()];
            c8.rewind();
            c8.get(bArr);
            return bArr;
        }
        return incorrectJpegMetadataQuirk.i(nVar);
    }
}
