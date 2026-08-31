package r;

import android.hardware.camera2.CameraCharacteristics;
import android.util.Pair;
import android.util.Size;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import r.r0;
import y.r;
/* loaded from: classes.dex */
public final class r0 implements b0.g0 {

    /* renamed from: a  reason: collision with root package name */
    private final String f10729a;

    /* renamed from: b  reason: collision with root package name */
    private final s.b0 f10730b;

    /* renamed from: c  reason: collision with root package name */
    private final x.h f10731c;

    /* renamed from: e  reason: collision with root package name */
    private t f10733e;

    /* renamed from: h  reason: collision with root package name */
    private final a f10736h;

    /* renamed from: j  reason: collision with root package name */
    private final b0.l2 f10738j;

    /* renamed from: k  reason: collision with root package name */
    private final b0.g1 f10739k;

    /* renamed from: l  reason: collision with root package name */
    private final s.o0 f10740l;

    /* renamed from: d  reason: collision with root package name */
    private final Object f10732d = new Object();

    /* renamed from: f  reason: collision with root package name */
    private a f10734f = null;

    /* renamed from: g  reason: collision with root package name */
    private a f10735g = null;

    /* renamed from: i  reason: collision with root package name */
    private List f10737i = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a extends androidx.lifecycle.q {

        /* renamed from: m  reason: collision with root package name */
        private androidx.lifecycle.p f10741m;

        /* renamed from: n  reason: collision with root package name */
        private final Object f10742n;

        a(Object obj) {
            this.f10742n = obj;
        }

        @Override // androidx.lifecycle.p
        public Object e() {
            androidx.lifecycle.p pVar = this.f10741m;
            return pVar == null ? this.f10742n : pVar.e();
        }

        void r(androidx.lifecycle.p pVar) {
            androidx.lifecycle.p pVar2 = this.f10741m;
            if (pVar2 != null) {
                super.q(pVar2);
            }
            this.f10741m = pVar;
            super.p(pVar, new androidx.lifecycle.t() { // from class: r.q0
                @Override // androidx.lifecycle.t
                public final void a(Object obj) {
                    r0.a.this.o(obj);
                }
            });
        }
    }

    public r0(String str, s.o0 o0Var) {
        String str2 = (String) g1.e.j(str);
        this.f10729a = str2;
        this.f10740l = o0Var;
        s.b0 c8 = o0Var.c(str2);
        this.f10730b = c8;
        this.f10731c = new x.h(this);
        b0.l2 a8 = androidx.camera.camera2.internal.compat.quirk.a.a(str, c8);
        this.f10738j = a8;
        this.f10739k = new e2(str, a8);
        this.f10736h = new a(y.r.a(r.b.CLOSED));
    }

    private void u() {
        v();
    }

    private void v() {
        String str;
        int s8 = s();
        if (s8 == 0) {
            str = "INFO_SUPPORTED_HARDWARE_LEVEL_LIMITED";
        } else if (s8 == 1) {
            str = "INFO_SUPPORTED_HARDWARE_LEVEL_FULL";
        } else if (s8 == 2) {
            str = "INFO_SUPPORTED_HARDWARE_LEVEL_LEGACY";
        } else if (s8 == 3) {
            str = "INFO_SUPPORTED_HARDWARE_LEVEL_3";
        } else if (s8 != 4) {
            str = "Unknown value: " + s8;
        } else {
            str = "INFO_SUPPORTED_HARDWARE_LEVEL_EXTERNAL";
        }
        y.f1.e("Camera2CameraInfo", "Device Level: " + str);
    }

    @Override // b0.g0
    public Set b() {
        return t.g.a(this.f10730b).c();
    }

    @Override // y.p
    public int c() {
        return i(0);
    }

    @Override // y.p
    public int d() {
        Integer num = (Integer) this.f10730b.a(CameraCharacteristics.LENS_FACING);
        g1.e.b(num != null, "Unable to get the lens facing of the camera.");
        return c4.a(num.intValue());
    }

    @Override // b0.g0
    public b0.z2 e() {
        Integer num = (Integer) this.f10730b.a(CameraCharacteristics.SENSOR_INFO_TIMESTAMP_SOURCE);
        g1.e.j(num);
        return num.intValue() != 1 ? b0.z2.UPTIME : b0.z2.REALTIME;
    }

    @Override // b0.g0
    public boolean f() {
        int[] iArr = (int[]) this.f10730b.a(CameraCharacteristics.CONTROL_AVAILABLE_VIDEO_STABILIZATION_MODES);
        if (iArr != null) {
            for (int i8 : iArr) {
                if (i8 == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // b0.g0
    public String g() {
        return this.f10729a;
    }

    @Override // b0.g0
    public List h(int i8) {
        Size[] a8 = this.f10730b.b().a(i8);
        return a8 != null ? Arrays.asList(a8) : Collections.emptyList();
    }

    @Override // y.p
    public int i(int i8) {
        return d0.c.a(d0.c.b(i8), r(), 1 == d());
    }

    @Override // b0.g0
    public b0.g1 j() {
        return this.f10739k;
    }

    @Override // b0.g0
    public b0.l2 k() {
        return this.f10738j;
    }

    @Override // b0.g0
    public List l(int i8) {
        Size[] c8 = this.f10730b.b().c(i8);
        return c8 != null ? Arrays.asList(c8) : Collections.emptyList();
    }

    @Override // y.p
    public androidx.lifecycle.p m() {
        synchronized (this.f10732d) {
            t tVar = this.f10733e;
            if (tVar == null) {
                if (this.f10735g == null) {
                    this.f10735g = new a(h5.f(this.f10730b));
                }
                return this.f10735g;
            }
            a aVar = this.f10735g;
            if (aVar != null) {
                return aVar;
            }
            return tVar.V().h();
        }
    }

    @Override // y.p
    public y.b0 n() {
        synchronized (this.f10732d) {
            t tVar = this.f10733e;
            if (tVar == null) {
                return i3.e(this.f10730b);
            }
            return tVar.G().f();
        }
    }

    @Override // y.p
    public androidx.lifecycle.p o() {
        return this.f10736h;
    }

    public x.h p() {
        return this.f10731c;
    }

    public s.b0 q() {
        return this.f10730b;
    }

    int r() {
        Integer num = (Integer) this.f10730b.a(CameraCharacteristics.SENSOR_ORIENTATION);
        g1.e.j(num);
        return num.intValue();
    }

    int s() {
        Integer num = (Integer) this.f10730b.a(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
        g1.e.j(num);
        return num.intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void t(t tVar) {
        synchronized (this.f10732d) {
            this.f10733e = tVar;
            a aVar = this.f10735g;
            if (aVar != null) {
                aVar.r(tVar.V().h());
            }
            a aVar2 = this.f10734f;
            if (aVar2 != null) {
                aVar2.r(this.f10733e.T().f());
            }
            List<Pair> list = this.f10737i;
            if (list != null) {
                for (Pair pair : list) {
                    this.f10733e.B((Executor) pair.second, (b0.n) pair.first);
                }
                this.f10737i = null;
            }
        }
        u();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void w(androidx.lifecycle.p pVar) {
        this.f10736h.r(pVar);
    }
}
