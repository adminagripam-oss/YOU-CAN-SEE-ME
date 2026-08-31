package r;

import android.content.Context;
import b0.h3;
import b0.q2;
import b0.s0;
/* loaded from: classes.dex */
public final class g2 implements b0.h3 {

    /* renamed from: b  reason: collision with root package name */
    final d3 f10489b;

    public g2(Context context) {
        this.f10489b = d3.c(context);
    }

    @Override // b0.h3
    public b0.u0 a(h3.b bVar, int i8) {
        b0.a2 d02 = b0.a2.d0();
        q2.b bVar2 = new q2.b();
        bVar2.z(w4.b(bVar, i8));
        d02.Y(b0.g3.f4200t, bVar2.o());
        d02.Y(b0.g3.f4202v, f2.f10466a);
        s0.a aVar = new s0.a();
        aVar.v(w4.a(bVar, i8));
        d02.Y(b0.g3.f4201u, aVar.h());
        d02.Y(b0.g3.f4203w, bVar == h3.b.IMAGE_CAPTURE ? b4.f10402c : t0.f10789a);
        if (bVar == h3.b.PREVIEW) {
            d02.Y(b0.p1.f4315p, this.f10489b.f());
        }
        d02.Y(b0.p1.f4310k, Integer.valueOf(this.f10489b.d(true).getRotation()));
        if (bVar == h3.b.VIDEO_CAPTURE || bVar == h3.b.STREAM_SHARING) {
            d02.Y(b0.g3.f4206z, Boolean.TRUE);
        }
        return b0.f2.b0(d02);
    }
}
