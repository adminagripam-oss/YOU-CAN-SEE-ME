package x0;

import android.util.Range;
import y.f1;
/* loaded from: classes.dex */
public final class g implements g1.f {

    /* renamed from: a  reason: collision with root package name */
    private final r0.a f12618a;

    public g(r0.a aVar) {
        this.f12618a = aVar;
    }

    @Override // g1.f
    /* renamed from: a */
    public u0.a get() {
        int i8;
        StringBuilder sb;
        int f8 = b.f(this.f12618a);
        int g8 = b.g(this.f12618a);
        int c8 = this.f12618a.c();
        if (c8 == -1) {
            f1.a("DefAudioResolver", "Using fallback AUDIO channel count: 1");
            c8 = 1;
        } else {
            f1.a("DefAudioResolver", "Using supplied AUDIO channel count: " + c8);
        }
        Range d8 = this.f12618a.d();
        if (r0.a.f10967b.equals(d8)) {
            sb = new StringBuilder();
            sb.append("Using fallback AUDIO sample rate: ");
            i8 = 44100;
        } else {
            i8 = b.i(d8, c8, g8, ((Integer) d8.getUpper()).intValue());
            sb = new StringBuilder();
            sb.append("Using AUDIO sample rate resolved from AudioSpec: ");
        }
        sb.append(i8);
        sb.append("Hz");
        f1.a("DefAudioResolver", sb.toString());
        return u0.a.a().d(f8).c(g8).e(c8).f(i8).b();
    }
}
