package x0;

import android.util.Range;
import android.util.Size;
import b0.z2;
import java.util.Objects;
import r0.d2;
import y.a0;
import y.f1;
import y.g2;
import y0.o1;
/* loaded from: classes.dex */
public class l implements g1.f {

    /* renamed from: g  reason: collision with root package name */
    private static final Size f12633g = new Size(1280, 720);

    /* renamed from: h  reason: collision with root package name */
    private static final Range f12634h = new Range(1, 60);

    /* renamed from: a  reason: collision with root package name */
    private final String f12635a;

    /* renamed from: b  reason: collision with root package name */
    private final z2 f12636b;

    /* renamed from: c  reason: collision with root package name */
    private final d2 f12637c;

    /* renamed from: d  reason: collision with root package name */
    private final Size f12638d;

    /* renamed from: e  reason: collision with root package name */
    private final a0 f12639e;

    /* renamed from: f  reason: collision with root package name */
    private final Range f12640f;

    public l(String str, z2 z2Var, d2 d2Var, Size size, a0 a0Var, Range range) {
        this.f12635a = str;
        this.f12636b = z2Var;
        this.f12637c = d2Var;
        this.f12638d = size;
        this.f12639e = a0Var;
        this.f12640f = range;
    }

    private int b() {
        Range range = this.f12640f;
        Range range2 = g2.f12794p;
        int intValue = !Objects.equals(range, range2) ? ((Integer) f12634h.clamp((Integer) this.f12640f.getUpper())).intValue() : 30;
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(intValue);
        objArr[1] = Objects.equals(this.f12640f, range2) ? this.f12640f : "<UNSPECIFIED>";
        f1.a("VidEncCfgDefaultRslvr", String.format("Default resolved frame rate: %dfps. [Expected operating range: %s]", objArr));
        return intValue;
    }

    @Override // g1.f
    /* renamed from: a */
    public o1 get() {
        int b8 = b();
        f1.a("VidEncCfgDefaultRslvr", "Resolved VIDEO frame rate: " + b8 + "fps");
        Range c8 = this.f12637c.c();
        f1.a("VidEncCfgDefaultRslvr", "Using fallback VIDEO bitrate");
        int a8 = this.f12639e.a();
        int width = this.f12638d.getWidth();
        Size size = f12633g;
        int e8 = k.e(14000000, a8, 8, b8, 30, width, size.getWidth(), this.f12638d.getHeight(), size.getHeight(), c8);
        int a9 = z0.b.a(this.f12635a, this.f12639e);
        return o1.d().h(this.f12635a).g(this.f12636b).j(this.f12638d).b(e8).e(b8).i(a9).d(k.b(this.f12635a, a9)).a();
    }
}
