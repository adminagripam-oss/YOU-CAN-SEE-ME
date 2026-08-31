package a1;

import android.util.Range;
import android.util.Size;
import androidx.camera.video.internal.compat.quirk.MediaCodecInfoReportIncorrectInfoQuirk;
import java.util.HashSet;
import java.util.Set;
import y.f1;
import y0.q1;
/* loaded from: classes.dex */
public class e implements q1 {

    /* renamed from: a  reason: collision with root package name */
    private final q1 f162a;

    /* renamed from: b  reason: collision with root package name */
    private final Range f163b;

    /* renamed from: c  reason: collision with root package name */
    private final Range f164c;

    /* renamed from: d  reason: collision with root package name */
    private final Set f165d;

    private e(q1 q1Var) {
        HashSet hashSet = new HashSet();
        this.f165d = hashSet;
        this.f162a = q1Var;
        int b8 = q1Var.b();
        this.f163b = Range.create(Integer.valueOf(b8), Integer.valueOf(((int) Math.ceil(4096.0d / b8)) * b8));
        int f8 = q1Var.f();
        this.f164c = Range.create(Integer.valueOf(f8), Integer.valueOf(((int) Math.ceil(2160.0d / f8)) * f8));
        hashSet.addAll(MediaCodecInfoReportIncorrectInfoQuirk.f());
    }

    private void k(Size size) {
        this.f165d.add(size);
    }

    public static q1 l(q1 q1Var, Size size) {
        boolean z7 = false;
        if (!(q1Var instanceof e)) {
            if (androidx.camera.video.internal.compat.quirk.a.b(MediaCodecInfoReportIncorrectInfoQuirk.class) == null) {
                if (size != null && !q1Var.a(size.getWidth(), size.getHeight())) {
                    f1.l("VideoEncoderInfoWrapper", String.format("Detected that the device does not support a size %s that should be valid in widths/heights = %s/%s", size, q1Var.g(), q1Var.j()));
                }
            }
            z7 = true;
        }
        if (z7) {
            q1Var = new e(q1Var);
        }
        if (size != null && (q1Var instanceof e)) {
            ((e) q1Var).k(size);
        }
        return q1Var;
    }

    @Override // y0.q1
    public int b() {
        return this.f162a.b();
    }

    @Override // y0.q1
    public Range c() {
        return this.f162a.c();
    }

    @Override // y0.q1
    public Range d(int i8) {
        boolean z7 = this.f164c.contains((Range) Integer.valueOf(i8)) && i8 % this.f162a.f() == 0;
        g1.e.b(z7, "Not supported height: " + i8 + " which is not in " + this.f164c + " or can not be divided by alignment " + this.f162a.f());
        return this.f163b;
    }

    @Override // y0.q1
    public Range e(int i8) {
        boolean z7 = this.f163b.contains((Range) Integer.valueOf(i8)) && i8 % this.f162a.b() == 0;
        g1.e.b(z7, "Not supported width: " + i8 + " which is not in " + this.f163b + " or can not be divided by alignment " + this.f162a.b());
        return this.f164c;
    }

    @Override // y0.q1
    public int f() {
        return this.f162a.f();
    }

    @Override // y0.q1
    public Range g() {
        return this.f163b;
    }

    @Override // y0.q1
    public boolean h(int i8, int i9) {
        if (this.f162a.h(i8, i9)) {
            return true;
        }
        for (Size size : this.f165d) {
            if (size.getWidth() == i8 && size.getHeight() == i9) {
                return true;
            }
        }
        return this.f163b.contains((Range) Integer.valueOf(i8)) && this.f164c.contains((Range) Integer.valueOf(i9)) && i8 % this.f162a.b() == 0 && i9 % this.f162a.f() == 0;
    }

    @Override // y0.q1
    public boolean i() {
        return this.f162a.i();
    }

    @Override // y0.q1
    public Range j() {
        return this.f164c;
    }
}
