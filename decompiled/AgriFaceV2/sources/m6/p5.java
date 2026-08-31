package m6;

import androidx.camera.core.n;
import m6.t0;
/* loaded from: classes.dex */
public class p5 {

    /* renamed from: a  reason: collision with root package name */
    private final i6.b f9430a;

    /* renamed from: b  reason: collision with root package name */
    private final v4 f9431b;

    /* renamed from: c  reason: collision with root package name */
    private t0.b1 f9432c;

    public p5(i6.b bVar, v4 v4Var) {
        this.f9430a = bVar;
        this.f9431b = v4Var;
        this.f9432c = new t0.b1(bVar);
    }

    public void a(n.a aVar, byte[] bArr, Long l8, Long l9, t0.b1.a aVar2) {
        if (this.f9431b.e(aVar)) {
            return;
        }
        this.f9432c.b(Long.valueOf(this.f9431b.b(aVar)), bArr, l8, l9, aVar2);
    }
}
