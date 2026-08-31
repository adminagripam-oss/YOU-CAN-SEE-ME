package x0;

import b0.h1;
import b0.z2;
import y.f1;
/* loaded from: classes.dex */
public final class c implements g1.f {

    /* renamed from: a  reason: collision with root package name */
    private final String f12605a;

    /* renamed from: b  reason: collision with root package name */
    private final z2 f12606b;

    /* renamed from: c  reason: collision with root package name */
    private final int f12607c;

    /* renamed from: d  reason: collision with root package name */
    private final r0.a f12608d;

    /* renamed from: e  reason: collision with root package name */
    private final u0.a f12609e;

    /* renamed from: f  reason: collision with root package name */
    private final h1.a f12610f;

    public c(String str, int i8, z2 z2Var, r0.a aVar, u0.a aVar2, h1.a aVar3) {
        this.f12605a = str;
        this.f12607c = i8;
        this.f12606b = z2Var;
        this.f12608d = aVar;
        this.f12609e = aVar2;
        this.f12610f = aVar3;
    }

    @Override // g1.f
    /* renamed from: a */
    public y0.a get() {
        f1.a("AudioEncAdPrflRslvr", "Using resolved AUDIO bitrate from AudioProfile");
        return y0.a.d().f(this.f12605a).g(this.f12607c).e(this.f12606b).d(this.f12609e.e()).h(this.f12609e.f()).c(b.h(this.f12610f.b(), this.f12609e.e(), this.f12610f.c(), this.f12609e.f(), this.f12610f.g(), this.f12608d.b())).b();
    }
}
