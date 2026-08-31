package m6;

import android.hardware.camera2.CameraCharacteristics;
import java.util.Objects;
import m6.t0;
/* loaded from: classes.dex */
public class i implements t0.f {

    /* renamed from: a  reason: collision with root package name */
    private final i6.b f9317a;

    /* renamed from: b  reason: collision with root package name */
    private final v4 f9318b;

    /* renamed from: c  reason: collision with root package name */
    private final a f9319c;

    /* loaded from: classes.dex */
    public static class a {
        public x.h a(y.p pVar) {
            return x.h.a(pVar);
        }

        public String b(x.h hVar) {
            return hVar.c();
        }

        public Long c(x.h hVar) {
            return Long.valueOf(((Integer) hVar.b(CameraCharacteristics.SENSOR_ORIENTATION)).intValue());
        }

        public Integer d(x.h hVar) {
            return (Integer) hVar.b(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
        }
    }

    public i(i6.b bVar, v4 v4Var) {
        this(bVar, v4Var, new a());
    }

    private x.h p(Long l8) {
        x.h hVar = (x.h) this.f9318b.h(l8.longValue());
        Objects.requireNonNull(hVar);
        return hVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void q(Void r02) {
    }

    @Override // m6.t0.f
    public Long c(Long l8) {
        return this.f9319c.c(p(l8));
    }

    @Override // m6.t0.f
    public Long f(Long l8) {
        return Long.valueOf(this.f9319c.d(p(l8)).intValue());
    }

    @Override // m6.t0.f
    public Long h(Long l8) {
        y.p pVar = (y.p) this.f9318b.h(l8.longValue());
        Objects.requireNonNull(pVar);
        x.h a8 = this.f9319c.a(pVar);
        new g(this.f9317a, this.f9318b).e(a8, new t0.e.a() { // from class: m6.h
            @Override // m6.t0.e.a
            public final void a(Object obj) {
                i.q((Void) obj);
            }
        });
        return this.f9318b.g(a8);
    }

    @Override // m6.t0.f
    public String l(Long l8) {
        return this.f9319c.b(p(l8));
    }

    i(i6.b bVar, v4 v4Var, a aVar) {
        this.f9318b = v4Var;
        this.f9317a = bVar;
        this.f9319c = aVar;
    }
}
