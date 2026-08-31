package x0;

import android.util.Range;
import b0.h1;
import y.f1;
/* loaded from: classes.dex */
public final class f implements g1.f {

    /* renamed from: a  reason: collision with root package name */
    private final r0.a f12616a;

    /* renamed from: b  reason: collision with root package name */
    private final h1.a f12617b;

    public f(r0.a aVar, h1.a aVar2) {
        this.f12616a = aVar;
        this.f12617b = aVar2;
    }

    @Override // g1.f
    /* renamed from: a */
    public u0.a get() {
        int f8 = b.f(this.f12616a);
        int g8 = b.g(this.f12616a);
        int c8 = this.f12616a.c();
        Range d8 = this.f12616a.d();
        int c9 = this.f12617b.c();
        if (c8 == -1) {
            f1.a("AudioSrcAdPrflRslvr", "Resolved AUDIO channel count from AudioProfile: " + c9);
            c8 = c9;
        } else {
            f1.a("AudioSrcAdPrflRslvr", "Media spec AUDIO channel count overrides AudioProfile [AudioProfile channel count: " + c9 + ", Resolved Channel Count: " + c8 + "]");
        }
        int g9 = this.f12617b.g();
        int i8 = b.i(d8, c8, g8, g9);
        f1.a("AudioSrcAdPrflRslvr", "Using resolved AUDIO sample rate or nearest supported from AudioProfile: " + i8 + "Hz. [AudioProfile sample rate: " + g9 + "Hz]");
        return u0.a.a().d(f8).c(g8).e(c8).f(i8).b();
    }
}
