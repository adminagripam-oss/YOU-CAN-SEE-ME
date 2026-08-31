package y;

import android.view.Display;
/* loaded from: classes.dex */
public final class z extends j1 {

    /* renamed from: b  reason: collision with root package name */
    private final float f12997b;

    /* renamed from: c  reason: collision with root package name */
    private final float f12998c;

    /* renamed from: d  reason: collision with root package name */
    private final Display f12999d;

    /* renamed from: e  reason: collision with root package name */
    private final p f13000e;

    public z(Display display, p pVar, float f8, float f9) {
        this.f12997b = f8;
        this.f12998c = f9;
        this.f12999d = display;
        this.f13000e = pVar;
    }

    private int e(boolean z7) {
        try {
            int i8 = this.f13000e.i(this.f12999d.getRotation());
            return z7 ? (360 - i8) % 360 : i8;
        } catch (Exception unused) {
            return 0;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0034  */
    @Override // y.j1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected android.graphics.PointF a(float r9, float r10) {
        /*
            r8 = this;
            float r0 = r8.f12997b
            float r1 = r8.f12998c
            y.p r2 = r8.f13000e
            int r2 = r2.d()
            if (r2 != 0) goto Le
            r2 = 1
            goto Lf
        Le:
            r2 = 0
        Lf:
            int r3 = r8.e(r2)
            r4 = 270(0x10e, float:3.78E-43)
            r5 = 90
            if (r3 == r5) goto L22
            if (r3 != r4) goto L1c
            goto L22
        L1c:
            r6 = r10
            r10 = r9
            r9 = r6
            r7 = r1
            r1 = r0
            r0 = r7
        L22:
            if (r3 == r5) goto L30
            r5 = 180(0xb4, float:2.52E-43)
            if (r3 == r5) goto L2e
            if (r3 == r4) goto L2b
            goto L32
        L2b:
            float r10 = r1 - r10
            goto L32
        L2e:
            float r10 = r1 - r10
        L30:
            float r9 = r0 - r9
        L32:
            if (r2 == 0) goto L36
            float r10 = r1 - r10
        L36:
            float r10 = r10 / r1
            float r9 = r9 / r0
            android.graphics.PointF r0 = new android.graphics.PointF
            r0.<init>(r10, r9)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: y.z.a(float, float):android.graphics.PointF");
    }
}
