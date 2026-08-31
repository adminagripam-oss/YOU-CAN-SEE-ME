package x0;

import android.util.Range;
import b0.z2;
import y.f1;
/* loaded from: classes.dex */
public final class d implements g1.f {

    /* renamed from: a  reason: collision with root package name */
    private final String f12611a;

    /* renamed from: b  reason: collision with root package name */
    private final int f12612b;

    /* renamed from: c  reason: collision with root package name */
    private final r0.a f12613c;

    /* renamed from: d  reason: collision with root package name */
    private final u0.a f12614d;

    /* renamed from: e  reason: collision with root package name */
    private final z2 f12615e;

    public d(String str, int i8, z2 z2Var, r0.a aVar, u0.a aVar2) {
        this.f12611a = str;
        this.f12612b = i8;
        this.f12615e = z2Var;
        this.f12613c = aVar;
        this.f12614d = aVar2;
    }

    @Override // g1.f
    /* renamed from: a */
    public y0.a get() {
        Range b8 = this.f12613c.b();
        f1.a("AudioEncCfgDefaultRslvr", "Using fallback AUDIO bitrate");
        return y0.a.d().f(this.f12611a).g(this.f12612b).e(this.f12615e).d(this.f12614d.e()).h(this.f12614d.f()).c(b.h(156000, this.f12614d.e(), 2, this.f12614d.f(), 48000, b8)).b();
    }
}
