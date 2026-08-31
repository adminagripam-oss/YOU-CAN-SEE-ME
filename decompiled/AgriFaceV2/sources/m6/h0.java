package m6;

import android.hardware.camera2.CaptureRequest;
import java.util.HashMap;
import java.util.Map;
import m6.t0;
import x.j;
/* loaded from: classes.dex */
public class h0 implements t0.u {

    /* renamed from: a  reason: collision with root package name */
    private final v4 f9304a;

    /* renamed from: b  reason: collision with root package name */
    private final b f9305b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9306a;

        static {
            int[] iArr = new int[t0.t.values().length];
            f9306a = iArr;
            try {
                iArr[t0.t.CONTROL_AE_LOCK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static class b {
        private CaptureRequest.Key b(t0.t tVar) {
            if (a.f9306a[tVar.ordinal()] == 1) {
                return CaptureRequest.CONTROL_AE_LOCK;
            }
            throw new IllegalArgumentException("The capture request key is not currently supported by the plugin.");
        }

        public x.j a(Map map) {
            j.a c8 = c();
            for (Map.Entry entry : map.entrySet()) {
                t0.t tVar = (t0.t) entry.getKey();
                CaptureRequest.Key b8 = b(tVar);
                Object value = entry.getValue();
                if (value == null) {
                    c8.e(b8);
                } else if (a.f9306a[tVar.ordinal()] != 1) {
                    throw new IllegalArgumentException("The capture request key " + tVar.toString() + "is not currently supported by the plugin.");
                } else {
                    c8.h(b8, (Boolean) value);
                }
            }
            return c8.b();
        }

        public j.a c() {
            return new j.a();
        }
    }

    public h0(v4 v4Var) {
        this(v4Var, new b());
    }

    @Override // m6.t0.u
    public void e(Long l8, Map map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry entry : map.entrySet()) {
            hashMap.put(t0.t.values()[Integer.valueOf(((Number) entry.getKey()).intValue()).intValue()], entry.getValue());
        }
        this.f9304a.a(this.f9305b.a(hashMap), l8.longValue());
    }

    h0(v4 v4Var, b bVar) {
        this.f9304a = v4Var;
        this.f9305b = bVar;
    }
}
