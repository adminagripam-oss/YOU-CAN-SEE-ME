package x0;

import android.util.Range;
import android.util.Size;
import b0.h1;
import b0.z2;
import java.util.Objects;
import r0.d2;
import y.a0;
import y.f1;
import y.g2;
import y0.o1;
/* loaded from: classes.dex */
public class m implements g1.f {

    /* renamed from: a  reason: collision with root package name */
    private final String f12641a;

    /* renamed from: b  reason: collision with root package name */
    private final z2 f12642b;

    /* renamed from: c  reason: collision with root package name */
    private final d2 f12643c;

    /* renamed from: d  reason: collision with root package name */
    private final Size f12644d;

    /* renamed from: e  reason: collision with root package name */
    private final h1.c f12645e;

    /* renamed from: f  reason: collision with root package name */
    private final a0 f12646f;

    /* renamed from: g  reason: collision with root package name */
    private final Range f12647g;

    public m(String str, z2 z2Var, d2 d2Var, Size size, h1.c cVar, a0 a0Var, Range range) {
        this.f12641a = str;
        this.f12642b = z2Var;
        this.f12643c = d2Var;
        this.f12644d = size;
        this.f12645e = cVar;
        this.f12646f = a0Var;
        this.f12647g = range;
    }

    private int b() {
        int f8 = this.f12645e.f();
        Range range = this.f12647g;
        Range range2 = g2.f12794p;
        int intValue = !Objects.equals(range, range2) ? ((Integer) this.f12647g.clamp(Integer.valueOf(f8))).intValue() : f8;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(intValue);
        objArr[1] = Integer.valueOf(f8);
        objArr[2] = Objects.equals(this.f12647g, range2) ? this.f12647g : "<UNSPECIFIED>";
        f1.a("VidEncVdPrflRslvr", String.format("Resolved frame rate %dfps [Video profile frame rate: %dfps, Expected operating range: %s]", objArr));
        return intValue;
    }

    @Override // g1.f
    /* renamed from: a */
    public o1 get() {
        int b8 = b();
        f1.a("VidEncVdPrflRslvr", "Resolved VIDEO frame rate: " + b8 + "fps");
        Range c8 = this.f12643c.c();
        f1.a("VidEncVdPrflRslvr", "Using resolved VIDEO bitrate from EncoderProfiles");
        int e8 = k.e(this.f12645e.c(), this.f12646f.a(), this.f12645e.b(), b8, this.f12645e.f(), this.f12644d.getWidth(), this.f12645e.k(), this.f12644d.getHeight(), this.f12645e.h(), c8);
        int j8 = this.f12645e.j();
        return o1.d().h(this.f12641a).g(this.f12642b).j(this.f12644d).b(e8).e(b8).i(j8).d(k.b(this.f12641a, j8)).a();
    }
}
