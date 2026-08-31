package m6;

import m6.t0;
import y.r;
/* loaded from: classes.dex */
public class d0 {

    /* renamed from: a  reason: collision with root package name */
    private final i6.b f9259a;

    /* renamed from: b  reason: collision with root package name */
    private final v4 f9260b;

    /* renamed from: c  reason: collision with root package name */
    private t0.o f9261c;

    public d0(i6.b bVar, v4 v4Var) {
        this.f9259a = bVar;
        this.f9260b = v4Var;
        this.f9261c = new t0.o(bVar);
    }

    public void a(r.a aVar, Long l8, t0.o.a aVar2) {
        if (this.f9260b.e(aVar)) {
            return;
        }
        this.f9261c.b(Long.valueOf(this.f9260b.b(aVar)), l8, aVar2);
    }
}
