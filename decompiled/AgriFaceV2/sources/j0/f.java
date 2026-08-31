package j0;

import android.media.MediaCodec;
import androidx.camera.core.internal.compat.quirk.SurfaceOrderQuirk;
import b0.b1;
import b0.q2;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import y.p1;
/* loaded from: classes.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    private final boolean f8185a;

    public f() {
        this.f8185a = androidx.camera.core.internal.compat.quirk.a.b(SurfaceOrderQuirk.class) != null;
    }

    private int b(b1 b1Var) {
        if (b1Var.g() == MediaCodec.class) {
            return 2;
        }
        return b1Var.g() == p1.class ? 0 : 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ int c(q2.f fVar, q2.f fVar2) {
        return b(fVar.f()) - b(fVar2.f());
    }

    public void d(List list) {
        if (this.f8185a) {
            Collections.sort(list, new Comparator() { // from class: j0.e
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    int c8;
                    c8 = f.this.c((q2.f) obj, (q2.f) obj2);
                    return c8;
                }
            });
        }
    }
}
