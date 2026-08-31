package r0;

import android.util.Size;
import b0.h1;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import r0.v;
/* loaded from: classes.dex */
public class o {

    /* renamed from: a  reason: collision with root package name */
    private final Map f11105a = new LinkedHashMap();

    /* renamed from: b  reason: collision with root package name */
    private final TreeMap f11106b = new TreeMap(new d0.e());

    /* renamed from: c  reason: collision with root package name */
    private final t0.g f11107c;

    /* renamed from: d  reason: collision with root package name */
    private final t0.g f11108d;

    public o(b0.g1 g1Var) {
        for (v vVar : v.b()) {
            b0.h1 d8 = d(vVar, g1Var);
            if (d8 != null) {
                y.f1.a("CapabilitiesByQuality", "profiles = " + d8);
                t0.g g8 = g(d8);
                if (g8 == null) {
                    y.f1.l("CapabilitiesByQuality", "EncoderProfiles of quality " + vVar + " has no video validated profiles.");
                } else {
                    h1.c k8 = g8.k();
                    this.f11106b.put(new Size(k8.k(), k8.h()), vVar);
                    this.f11105a.put(vVar, g8);
                }
            }
        }
        if (this.f11105a.isEmpty()) {
            y.f1.c("CapabilitiesByQuality", "No supported EncoderProfiles");
            this.f11108d = null;
            this.f11107c = null;
            return;
        }
        ArrayDeque arrayDeque = new ArrayDeque(this.f11105a.values());
        this.f11107c = (t0.g) arrayDeque.peekFirst();
        this.f11108d = (t0.g) arrayDeque.peekLast();
    }

    private static void a(v vVar) {
        boolean a8 = v.a(vVar);
        g1.e.b(a8, "Unknown quality: " + vVar);
    }

    private b0.h1 d(v vVar, b0.g1 g1Var) {
        g1.e.m(vVar instanceof v.b, "Currently only support ConstantQuality");
        return g1Var.a(((v.b) vVar).e());
    }

    private t0.g g(b0.h1 h1Var) {
        if (h1Var.c().isEmpty()) {
            return null;
        }
        return t0.g.i(h1Var);
    }

    public t0.g b(Size size) {
        v c8 = c(size);
        y.f1.a("CapabilitiesByQuality", "Using supported quality of " + c8 + " for size " + size);
        if (c8 != v.f11273g) {
            t0.g e8 = e(c8);
            if (e8 != null) {
                return e8;
            }
            throw new AssertionError("Camera advertised available quality but did not produce EncoderProfiles for advertised quality.");
        }
        return null;
    }

    public v c(Size size) {
        v vVar = (v) k0.d.a(size, this.f11106b);
        return vVar != null ? vVar : v.f11273g;
    }

    public t0.g e(v vVar) {
        a(vVar);
        return vVar == v.f11272f ? this.f11107c : vVar == v.f11271e ? this.f11108d : (t0.g) this.f11105a.get(vVar);
    }

    public List f() {
        return new ArrayList(this.f11105a.keySet());
    }
}
