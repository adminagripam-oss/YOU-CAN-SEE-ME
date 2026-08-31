package x0;

import android.util.Range;
import android.util.Rational;
import b0.h1;
import b0.z2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import y.f1;
/* loaded from: classes.dex */
public abstract class b {
    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int b(int i8, Integer num, Integer num2) {
        int abs = Math.abs(num.intValue() - i8) - Math.abs(num2.intValue() - i8);
        return (int) Math.signum(abs == 0 ? num.intValue() - num2.intValue() : abs);
    }

    public static y0.a c(e eVar, z2 z2Var, u0.a aVar, r0.a aVar2) {
        h1.a d8 = eVar.d();
        return (y0.a) (d8 != null ? new c(eVar.a(), eVar.b(), z2Var, aVar2, aVar, d8) : new d(eVar.a(), eVar.b(), z2Var, aVar2, aVar)).get();
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00c1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static x0.e d(r0.r r8, t0.g r9) {
        /*
            int r0 = r8.c()
            java.lang.String r0 = r0.r.e(r0)
            int r1 = r8.c()
            int r1 = r0.r.f(r1)
            if (r9 == 0) goto Lb4
            b0.h1$a r2 = r9.j()
            if (r2 == 0) goto Lb4
            b0.h1$a r9 = r9.j()
            java.lang.String r2 = r9.e()
            int r3 = r9.f()
            java.lang.String r4 = "audio/none"
            boolean r4 = java.util.Objects.equals(r2, r4)
            java.lang.String r5 = ")]"
            java.lang.String r6 = "AudioConfigUtil"
            java.lang.String r7 = "(profile: "
            if (r4 == 0) goto L50
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "EncoderProfiles contains undefined AUDIO mime type so cannot be used. May rely on fallback defaults to derive settings [chosen mime type: "
        L39:
            r8.append(r9)
            r8.append(r0)
            r8.append(r7)
            r8.append(r1)
            r8.append(r5)
            java.lang.String r8 = r8.toString()
            y.f1.a(r6, r8)
            goto Lb4
        L50:
            int r8 = r8.c()
            r4 = -1
            if (r8 != r4) goto L77
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r0 = "MediaSpec contains OUTPUT_FORMAT_AUTO. Using EncoderProfiles to derive AUDIO settings [mime type: "
            r8.append(r0)
            r8.append(r2)
            r8.append(r7)
            r8.append(r3)
            r8.append(r5)
            java.lang.String r8 = r8.toString()
            y.f1.a(r6, r8)
            r0 = r2
            r1 = r3
            goto Lb5
        L77:
            boolean r8 = java.util.Objects.equals(r0, r2)
            if (r8 == 0) goto L9e
            if (r1 != r3) goto L9e
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r0 = "MediaSpec audio mime/profile matches EncoderProfiles. Using EncoderProfiles to derive AUDIO settings [mime type: "
            r8.append(r0)
            r8.append(r2)
            r8.append(r7)
            r8.append(r1)
            r8.append(r5)
            java.lang.String r8 = r8.toString()
            y.f1.a(r6, r8)
            r0 = r2
            goto Lb5
        L9e:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "MediaSpec audio mime or profile does not match EncoderProfiles, so EncoderProfiles settings cannot be used. May rely on fallback defaults to derive AUDIO settings [EncoderProfiles mime type: "
            r8.append(r9)
            r8.append(r2)
            r8.append(r7)
            r8.append(r3)
            java.lang.String r9 = "), chosen mime type: "
            goto L39
        Lb4:
            r9 = 0
        Lb5:
            x0.e$a r8 = x0.e.c(r0)
            java.lang.Object r8 = r8.a(r1)
            x0.e$a r8 = (x0.e.a) r8
            if (r9 == 0) goto Lc4
            r8.c(r9)
        Lc4:
            x0.e r8 = r8.b()
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: x0.b.d(r0.r, t0.g):x0.e");
    }

    public static u0.a e(e eVar, r0.a aVar) {
        h1.a d8 = eVar.d();
        return (u0.a) (d8 != null ? new f(aVar, d8) : new g(aVar)).get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int f(r0.a aVar) {
        int e8 = aVar.e();
        if (e8 == -1) {
            f1.a("AudioConfigUtil", "Using default AUDIO source: 5");
            return 5;
        }
        f1.a("AudioConfigUtil", "Using provided AUDIO source: " + e8);
        return e8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int g(r0.a aVar) {
        int f8 = aVar.f();
        if (f8 == -1) {
            f1.a("AudioConfigUtil", "Using default AUDIO source format: 2");
            return 2;
        }
        f1.a("AudioConfigUtil", "Using provided AUDIO source format: " + f8);
        return f8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int h(int i8, int i9, int i10, int i11, int i12, Range range) {
        int doubleValue = (int) (i8 * new Rational(i9, i10).doubleValue() * new Rational(i11, i12).doubleValue());
        String format = f1.f("AudioConfigUtil") ? String.format("Base Bitrate(%dbps) * Channel Count Ratio(%d / %d) * Sample Rate Ratio(%d / %d) = %d", Integer.valueOf(i8), Integer.valueOf(i9), Integer.valueOf(i10), Integer.valueOf(i11), Integer.valueOf(i12), Integer.valueOf(doubleValue)) : "";
        if (!r0.a.f10966a.equals(range)) {
            doubleValue = ((Integer) range.clamp(Integer.valueOf(doubleValue))).intValue();
            if (f1.f("AudioConfigUtil")) {
                format = format + String.format("\nClamped to range %s -> %dbps", range, Integer.valueOf(doubleValue));
            }
        }
        f1.a("AudioConfigUtil", format);
        return doubleValue;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int i(Range range, int i8, int i9, final int i10) {
        StringBuilder sb;
        ArrayList arrayList = null;
        int i11 = 0;
        int i12 = i10;
        while (true) {
            if (!range.contains((Range) Integer.valueOf(i12))) {
                sb = new StringBuilder();
                sb.append("Sample rate ");
                sb.append(i12);
                sb.append("Hz is not in target range ");
                sb.append(range);
            } else if (u0.n.o(i12, i8, i9)) {
                return i12;
            } else {
                sb = new StringBuilder();
                sb.append("Sample rate ");
                sb.append(i12);
                sb.append("Hz is not supported by audio source with channel count ");
                sb.append(i8);
                sb.append(" and source format ");
                sb.append(i9);
            }
            f1.a("AudioConfigUtil", sb.toString());
            if (arrayList == null) {
                f1.a("AudioConfigUtil", "Trying common sample rates in proximity order to target " + i10 + "Hz");
                arrayList = new ArrayList(u0.a.f11918a);
                Collections.sort(arrayList, new Comparator() { // from class: x0.a
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        int b8;
                        b8 = b.b(i10, (Integer) obj, (Integer) obj2);
                        return b8;
                    }
                });
            }
            if (i11 >= arrayList.size()) {
                f1.a("AudioConfigUtil", "No sample rate found in target range or supported by audio source. Falling back to default sample rate of 44100Hz");
                return 44100;
            }
            i12 = ((Integer) arrayList.get(i11)).intValue();
            i11++;
        }
    }
}
