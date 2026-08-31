package x0;

import android.util.Range;
import android.util.Rational;
import android.util.Size;
import b0.h1;
import b0.z2;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import r0.d2;
import r0.r;
import x0.n;
import y.a0;
import y.f1;
import y0.o1;
import y0.p1;
/* loaded from: classes.dex */
public abstract class k {

    /* renamed from: a  reason: collision with root package name */
    private static final Map f12631a;

    /* renamed from: b  reason: collision with root package name */
    private static final z2 f12632b;

    static {
        HashMap hashMap = new HashMap();
        f12631a = hashMap;
        f12632b = z2.UPTIME;
        HashMap hashMap2 = new HashMap();
        p1 p1Var = p1.f13161a;
        hashMap2.put(1, p1Var);
        p1 p1Var2 = p1.f13163c;
        hashMap2.put(2, p1Var2);
        p1 p1Var3 = p1.f13164d;
        hashMap2.put(4096, p1Var3);
        hashMap2.put(8192, p1Var3);
        HashMap hashMap3 = new HashMap();
        hashMap3.put(1, p1Var);
        hashMap3.put(2, p1Var2);
        hashMap3.put(4096, p1Var3);
        hashMap3.put(8192, p1Var3);
        HashMap hashMap4 = new HashMap();
        hashMap4.put(1, p1Var);
        hashMap4.put(4, p1Var2);
        hashMap4.put(4096, p1Var3);
        hashMap4.put(16384, p1Var3);
        hashMap4.put(2, p1Var);
        hashMap4.put(8, p1Var2);
        hashMap4.put(8192, p1Var3);
        hashMap4.put(32768, p1Var3);
        HashMap hashMap5 = new HashMap();
        hashMap5.put(256, p1Var2);
        hashMap5.put(512, p1.f13162b);
        hashMap.put("video/hevc", hashMap2);
        hashMap.put("video/av01", hashMap3);
        hashMap.put("video/x-vnd.on2.vp9", hashMap4);
        hashMap.put("video/dolby-vision", hashMap5);
    }

    private static String a(a0 a0Var) {
        int b8 = a0Var.b();
        if (b8 != 1) {
            if (b8 == 3 || b8 == 4 || b8 == 5) {
                return "video/hevc";
            }
            if (b8 == 6) {
                return "video/dolby-vision";
            }
            throw new UnsupportedOperationException("Unsupported dynamic range: " + a0Var + "\nNo supported default mime type available.");
        }
        return "video/avc";
    }

    public static p1 b(String str, int i8) {
        p1 p1Var;
        Map map = (Map) f12631a.get(str);
        if (map == null || (p1Var = (p1) map.get(Integer.valueOf(i8))) == null) {
            f1.l("VideoConfigUtil", String.format("Unsupported mime type %s or profile level %d. Data space is unspecified.", str, Integer.valueOf(i8)));
            return p1.f13161a;
        }
        return p1Var;
    }

    public static o1 c(n nVar, z2 z2Var, d2 d2Var, Size size, a0 a0Var, Range range) {
        h1.c d8 = nVar.d();
        return (o1) (d8 != null ? new m(nVar.a(), z2Var, d2Var, size, d8, a0Var, range) : new l(nVar.a(), z2Var, d2Var, size, a0Var, range)).get();
    }

    public static n d(r rVar, a0 a0Var, t0.g gVar) {
        StringBuilder sb;
        String str;
        StringBuilder sb2;
        boolean e8 = a0Var.e();
        g1.e.m(e8, "Dynamic range must be a fully specified dynamic range [provided dynamic range: " + a0Var + "]");
        String h8 = r.h(rVar.c());
        if (gVar != null) {
            Set c8 = z0.b.c(a0Var);
            Set b8 = z0.b.b(a0Var);
            for (h1.c cVar : gVar.c()) {
                if (c8.contains(Integer.valueOf(cVar.g())) && b8.contains(Integer.valueOf(cVar.b()))) {
                    String i8 = cVar.i();
                    if (Objects.equals(h8, i8)) {
                        sb2 = new StringBuilder();
                        sb2.append("MediaSpec video mime matches EncoderProfiles. Using EncoderProfiles to derive VIDEO settings [mime type: ");
                        sb2.append(h8);
                    } else if (rVar.c() == -1) {
                        sb2 = new StringBuilder();
                        sb2.append("MediaSpec contains OUTPUT_FORMAT_AUTO. Using CamcorderProfile to derive VIDEO settings [mime type: ");
                        sb2.append(h8);
                        sb2.append(", dynamic range: ");
                        sb2.append(a0Var);
                    }
                    sb2.append("]");
                    f1.a("VideoConfigUtil", sb2.toString());
                    h8 = i8;
                    break;
                }
            }
        }
        cVar = null;
        if (cVar == null) {
            if (rVar.c() == -1) {
                h8 = a(a0Var);
            }
            if (gVar == null) {
                sb = new StringBuilder();
                str = "No EncoderProfiles present. May rely on fallback defaults to derive VIDEO settings [chosen mime type: ";
            } else {
                sb = new StringBuilder();
                str = "No video EncoderProfile is compatible with requested output format and dynamic range. May rely on fallback defaults to derive VIDEO settings [chosen mime type: ";
            }
            sb.append(str);
            sb.append(h8);
            sb.append(", dynamic range: ");
            sb.append(a0Var);
            sb.append("]");
            f1.a("VideoConfigUtil", sb.toString());
        }
        n.a c9 = n.c(h8);
        if (cVar != null) {
            c9.c(cVar);
        }
        return c9.b();
    }

    public static int e(int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, Range range) {
        int doubleValue = (int) (i8 * new Rational(i9, i10).doubleValue() * new Rational(i11, i12).doubleValue() * new Rational(i13, i14).doubleValue() * new Rational(i15, i16).doubleValue());
        String format = f1.f("VideoConfigUtil") ? String.format("Base Bitrate(%dbps) * Bit Depth Ratio (%d / %d) * Frame Rate Ratio(%d / %d) * Width Ratio(%d / %d) * Height Ratio(%d / %d) = %d", Integer.valueOf(i8), Integer.valueOf(i9), Integer.valueOf(i10), Integer.valueOf(i11), Integer.valueOf(i12), Integer.valueOf(i13), Integer.valueOf(i14), Integer.valueOf(i15), Integer.valueOf(i16), Integer.valueOf(doubleValue)) : "";
        if (!d2.f11033b.equals(range)) {
            doubleValue = ((Integer) range.clamp(Integer.valueOf(doubleValue))).intValue();
            if (f1.f("VideoConfigUtil")) {
                format = format + String.format("\nClamped to range %s -> %dbps", range, Integer.valueOf(doubleValue));
            }
        }
        f1.a("VideoConfigUtil", format);
        return doubleValue;
    }

    public static o1 f(h1.c cVar) {
        return o1.d().h(cVar.i()).i(cVar.j()).j(new Size(cVar.k(), cVar.h())).e(cVar.f()).b(cVar.c()).g(f12632b).a();
    }
}
