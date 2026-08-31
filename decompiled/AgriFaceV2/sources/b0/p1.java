package b0;

import android.util.Size;
import b0.u0;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public interface p1 extends m2 {

    /* renamed from: j  reason: collision with root package name */
    public static final u0.a f4309j = u0.a.a("camerax.core.imageOutput.targetAspectRatio", y.a.class);

    /* renamed from: k  reason: collision with root package name */
    public static final u0.a f4310k;

    /* renamed from: l  reason: collision with root package name */
    public static final u0.a f4311l;

    /* renamed from: m  reason: collision with root package name */
    public static final u0.a f4312m;

    /* renamed from: n  reason: collision with root package name */
    public static final u0.a f4313n;

    /* renamed from: o  reason: collision with root package name */
    public static final u0.a f4314o;

    /* renamed from: p  reason: collision with root package name */
    public static final u0.a f4315p;

    /* renamed from: q  reason: collision with root package name */
    public static final u0.a f4316q;

    /* renamed from: r  reason: collision with root package name */
    public static final u0.a f4317r;

    /* renamed from: s  reason: collision with root package name */
    public static final u0.a f4318s;

    /* loaded from: classes.dex */
    public interface a {
        Object a(int i8);

        Object b(Size size);
    }

    static {
        Class cls = Integer.TYPE;
        f4310k = u0.a.a("camerax.core.imageOutput.targetRotation", cls);
        f4311l = u0.a.a("camerax.core.imageOutput.appTargetRotation", cls);
        f4312m = u0.a.a("camerax.core.imageOutput.mirrorMode", cls);
        f4313n = u0.a.a("camerax.core.imageOutput.targetResolution", Size.class);
        f4314o = u0.a.a("camerax.core.imageOutput.defaultResolution", Size.class);
        f4315p = u0.a.a("camerax.core.imageOutput.maxResolution", Size.class);
        f4316q = u0.a.a("camerax.core.imageOutput.supportedResolutions", List.class);
        f4317r = u0.a.a("camerax.core.imageOutput.resolutionSelector", o0.c.class);
        f4318s = u0.a.a("camerax.core.imageOutput.customOrderedResolutions", List.class);
    }

    static void j(p1 p1Var) {
        boolean t8 = p1Var.t();
        boolean z7 = p1Var.S(null) != null;
        if (t8 && z7) {
            throw new IllegalArgumentException("Cannot use both setTargetResolution and setTargetAspectRatio on the same config.");
        }
        if (p1Var.E(null) != null) {
            if (t8 || z7) {
                throw new IllegalArgumentException("Cannot use setTargetResolution or setTargetAspectRatio with setResolutionSelector on the same config.");
            }
        }
    }

    default List A(List list) {
        List list2 = (List) f(f4318s, list);
        if (list2 != null) {
            return new ArrayList(list2);
        }
        return null;
    }

    default o0.c E(o0.c cVar) {
        return (o0.c) f(f4317r, cVar);
    }

    default Size L(Size size) {
        return (Size) f(f4314o, size);
    }

    default Size S(Size size) {
        return (Size) f(f4313n, size);
    }

    default int T(int i8) {
        return ((Integer) f(f4311l, Integer.valueOf(i8))).intValue();
    }

    default int V(int i8) {
        return ((Integer) f(f4310k, Integer.valueOf(i8))).intValue();
    }

    default int W(int i8) {
        return ((Integer) f(f4312m, Integer.valueOf(i8))).intValue();
    }

    default Size i(Size size) {
        return (Size) f(f4315p, size);
    }

    default List q(List list) {
        return (List) f(f4316q, list);
    }

    default boolean t() {
        return a(f4309j);
    }

    default int w() {
        return ((Integer) b(f4309j)).intValue();
    }

    default o0.c y() {
        return (o0.c) b(f4317r);
    }
}
