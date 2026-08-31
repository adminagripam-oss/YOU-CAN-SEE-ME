package h6;

import h6.q;
import i6.j;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class q {

    /* renamed from: a  reason: collision with root package name */
    private final i6.j f7362a;

    /* renamed from: b  reason: collision with root package name */
    private g f7363b;

    /* renamed from: c  reason: collision with root package name */
    private final j.c f7364c;

    /* loaded from: classes.dex */
    class a implements j.c {
        a() {
        }

        private void b(i6.i iVar, j.d dVar) {
            try {
                q.this.f7363b.f(((Integer) iVar.b()).intValue());
                dVar.success(null);
            } catch (IllegalStateException e8) {
                dVar.error("error", q.c(e8), null);
            }
        }

        private void c(i6.i iVar, j.d dVar) {
            Map map = (Map) iVar.b();
            boolean z7 = true;
            boolean z8 = map.containsKey("hybrid") && ((Boolean) map.get("hybrid")).booleanValue();
            ByteBuffer wrap = map.containsKey("params") ? ByteBuffer.wrap((byte[]) map.get("params")) : null;
            try {
                if (z8) {
                    q.this.f7363b.g(new d(((Integer) map.get("id")).intValue(), (String) map.get("viewType"), 0.0d, 0.0d, 0.0d, 0.0d, ((Integer) map.get("direction")).intValue(), d.a.HYBRID_ONLY, wrap));
                } else {
                    if (!map.containsKey("hybridFallback") || !((Boolean) map.get("hybridFallback")).booleanValue()) {
                        z7 = false;
                    }
                    long e8 = q.this.f7363b.e(new d(((Integer) map.get("id")).intValue(), (String) map.get("viewType"), map.containsKey("top") ? ((Double) map.get("top")).doubleValue() : 0.0d, map.containsKey("left") ? ((Double) map.get("left")).doubleValue() : 0.0d, ((Double) map.get("width")).doubleValue(), ((Double) map.get("height")).doubleValue(), ((Integer) map.get("direction")).intValue(), z7 ? d.a.TEXTURE_WITH_HYBRID_FALLBACK : d.a.TEXTURE_WITH_VIRTUAL_FALLBACK, wrap));
                    if (e8 != -2) {
                        dVar.success(Long.valueOf(e8));
                        return;
                    } else if (!z7) {
                        throw new AssertionError("Platform view attempted to fall back to hybrid mode when not requested.");
                    }
                }
                dVar.success(null);
            } catch (IllegalStateException e9) {
                dVar.error("error", q.c(e9), null);
            }
        }

        private void d(i6.i iVar, j.d dVar) {
            try {
                q.this.f7363b.h(((Integer) ((Map) iVar.b()).get("id")).intValue());
                dVar.success(null);
            } catch (IllegalStateException e8) {
                dVar.error("error", q.c(e8), null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void e(j.d dVar, c cVar) {
            if (cVar == null) {
                dVar.error("error", "Failed to resize the platform view", null);
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("width", Double.valueOf(cVar.f7366a));
            hashMap.put("height", Double.valueOf(cVar.f7367b));
            dVar.success(hashMap);
        }

        private void f(i6.i iVar, j.d dVar) {
            Map map = (Map) iVar.b();
            try {
                q.this.f7363b.b(((Integer) map.get("id")).intValue(), ((Double) map.get("top")).doubleValue(), ((Double) map.get("left")).doubleValue());
                dVar.success(null);
            } catch (IllegalStateException e8) {
                dVar.error("error", q.c(e8), null);
            }
        }

        private void g(i6.i iVar, final j.d dVar) {
            Map map = (Map) iVar.b();
            try {
                q.this.f7363b.i(new e(((Integer) map.get("id")).intValue(), ((Double) map.get("width")).doubleValue(), ((Double) map.get("height")).doubleValue()), new b() { // from class: h6.p
                    @Override // h6.q.b
                    public final void a(q.c cVar) {
                        q.a.e(j.d.this, cVar);
                    }
                });
            } catch (IllegalStateException e8) {
                dVar.error("error", q.c(e8), null);
            }
        }

        private void h(i6.i iVar, j.d dVar) {
            Map map = (Map) iVar.b();
            try {
                q.this.f7363b.c(((Integer) map.get("id")).intValue(), ((Integer) map.get("direction")).intValue());
                dVar.success(null);
            } catch (IllegalStateException e8) {
                dVar.error("error", q.c(e8), null);
            }
        }

        private void i(i6.i iVar, j.d dVar) {
            try {
                q.this.f7363b.a(((Boolean) iVar.b()).booleanValue());
                dVar.success(null);
            } catch (IllegalStateException e8) {
                dVar.error("error", q.c(e8), null);
            }
        }

        private void j(i6.i iVar, j.d dVar) {
            j.d dVar2;
            List list = (List) iVar.b();
            try {
                q.this.f7363b.d(new f(((Integer) list.get(0)).intValue(), (Number) list.get(1), (Number) list.get(2), ((Integer) list.get(3)).intValue(), ((Integer) list.get(4)).intValue(), list.get(5), list.get(6), ((Integer) list.get(7)).intValue(), ((Integer) list.get(8)).intValue(), (float) ((Double) list.get(9)).doubleValue(), (float) ((Double) list.get(10)).doubleValue(), ((Integer) list.get(11)).intValue(), ((Integer) list.get(12)).intValue(), ((Integer) list.get(13)).intValue(), ((Integer) list.get(14)).intValue(), ((Number) list.get(15)).longValue()));
                dVar2 = dVar;
            } catch (IllegalStateException e8) {
                e = e8;
                dVar2 = dVar;
            }
            try {
                dVar2.success(null);
            } catch (IllegalStateException e9) {
                e = e9;
                dVar2.error("error", q.c(e), null);
            }
        }

        @Override // i6.j.c
        public void onMethodCall(i6.i iVar, j.d dVar) {
            if (q.this.f7363b == null) {
                return;
            }
            z5.b.f("PlatformViewsChannel", "Received '" + iVar.f7535a + "' message.");
            String str = iVar.f7535a;
            str.hashCode();
            char c8 = 65535;
            switch (str.hashCode()) {
                case -1352294148:
                    if (str.equals("create")) {
                        c8 = 0;
                        break;
                    }
                    break;
                case -1019779949:
                    if (str.equals("offset")) {
                        c8 = 1;
                        break;
                    }
                    break;
                case -934437708:
                    if (str.equals("resize")) {
                        c8 = 2;
                        break;
                    }
                    break;
                case -756050293:
                    if (str.equals("clearFocus")) {
                        c8 = 3;
                        break;
                    }
                    break;
                case -308988850:
                    if (str.equals("synchronizeToNativeViewHierarchy")) {
                        c8 = 4;
                        break;
                    }
                    break;
                case 110550847:
                    if (str.equals("touch")) {
                        c8 = 5;
                        break;
                    }
                    break;
                case 576796989:
                    if (str.equals("setDirection")) {
                        c8 = 6;
                        break;
                    }
                    break;
                case 1671767583:
                    if (str.equals("dispose")) {
                        c8 = 7;
                        break;
                    }
                    break;
            }
            switch (c8) {
                case 0:
                    c(iVar, dVar);
                    return;
                case 1:
                    f(iVar, dVar);
                    return;
                case 2:
                    g(iVar, dVar);
                    return;
                case 3:
                    b(iVar, dVar);
                    return;
                case 4:
                    i(iVar, dVar);
                    return;
                case 5:
                    j(iVar, dVar);
                    return;
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                    h(iVar, dVar);
                    return;
                case 7:
                    d(iVar, dVar);
                    return;
                default:
                    dVar.notImplemented();
                    return;
            }
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        void a(c cVar);
    }

    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public final int f7366a;

        /* renamed from: b  reason: collision with root package name */
        public final int f7367b;

        public c(int i8, int i9) {
            this.f7366a = i8;
            this.f7367b = i9;
        }
    }

    /* loaded from: classes.dex */
    public static class d {

        /* renamed from: a  reason: collision with root package name */
        public final int f7368a;

        /* renamed from: b  reason: collision with root package name */
        public final String f7369b;

        /* renamed from: c  reason: collision with root package name */
        public final double f7370c;

        /* renamed from: d  reason: collision with root package name */
        public final double f7371d;

        /* renamed from: e  reason: collision with root package name */
        public final double f7372e;

        /* renamed from: f  reason: collision with root package name */
        public final double f7373f;

        /* renamed from: g  reason: collision with root package name */
        public final int f7374g;

        /* renamed from: h  reason: collision with root package name */
        public final a f7375h;

        /* renamed from: i  reason: collision with root package name */
        public final ByteBuffer f7376i;

        /* loaded from: classes.dex */
        public enum a {
            TEXTURE_WITH_VIRTUAL_FALLBACK,
            TEXTURE_WITH_HYBRID_FALLBACK,
            HYBRID_ONLY
        }

        public d(int i8, String str, double d8, double d9, double d10, double d11, int i9, a aVar, ByteBuffer byteBuffer) {
            this.f7368a = i8;
            this.f7369b = str;
            this.f7372e = d8;
            this.f7373f = d9;
            this.f7370c = d10;
            this.f7371d = d11;
            this.f7374g = i9;
            this.f7375h = aVar;
            this.f7376i = byteBuffer;
        }
    }

    /* loaded from: classes.dex */
    public static class e {

        /* renamed from: a  reason: collision with root package name */
        public final int f7381a;

        /* renamed from: b  reason: collision with root package name */
        public final double f7382b;

        /* renamed from: c  reason: collision with root package name */
        public final double f7383c;

        public e(int i8, double d8, double d9) {
            this.f7381a = i8;
            this.f7382b = d8;
            this.f7383c = d9;
        }
    }

    /* loaded from: classes.dex */
    public static class f {

        /* renamed from: a  reason: collision with root package name */
        public final int f7384a;

        /* renamed from: b  reason: collision with root package name */
        public final Number f7385b;

        /* renamed from: c  reason: collision with root package name */
        public final Number f7386c;

        /* renamed from: d  reason: collision with root package name */
        public final int f7387d;

        /* renamed from: e  reason: collision with root package name */
        public final int f7388e;

        /* renamed from: f  reason: collision with root package name */
        public final Object f7389f;

        /* renamed from: g  reason: collision with root package name */
        public final Object f7390g;

        /* renamed from: h  reason: collision with root package name */
        public final int f7391h;

        /* renamed from: i  reason: collision with root package name */
        public final int f7392i;

        /* renamed from: j  reason: collision with root package name */
        public final float f7393j;

        /* renamed from: k  reason: collision with root package name */
        public final float f7394k;

        /* renamed from: l  reason: collision with root package name */
        public final int f7395l;

        /* renamed from: m  reason: collision with root package name */
        public final int f7396m;

        /* renamed from: n  reason: collision with root package name */
        public final int f7397n;

        /* renamed from: o  reason: collision with root package name */
        public final int f7398o;

        /* renamed from: p  reason: collision with root package name */
        public final long f7399p;

        public f(int i8, Number number, Number number2, int i9, int i10, Object obj, Object obj2, int i11, int i12, float f8, float f9, int i13, int i14, int i15, int i16, long j8) {
            this.f7384a = i8;
            this.f7385b = number;
            this.f7386c = number2;
            this.f7387d = i9;
            this.f7388e = i10;
            this.f7389f = obj;
            this.f7390g = obj2;
            this.f7391h = i11;
            this.f7392i = i12;
            this.f7393j = f8;
            this.f7394k = f9;
            this.f7395l = i13;
            this.f7396m = i14;
            this.f7397n = i15;
            this.f7398o = i16;
            this.f7399p = j8;
        }
    }

    /* loaded from: classes.dex */
    public interface g {
        void a(boolean z7);

        void b(int i8, double d8, double d9);

        void c(int i8, int i9);

        void d(f fVar);

        long e(d dVar);

        void f(int i8);

        void g(d dVar);

        void h(int i8);

        void i(e eVar, b bVar);
    }

    public q(a6.a aVar) {
        a aVar2 = new a();
        this.f7364c = aVar2;
        i6.j jVar = new i6.j(aVar, "flutter/platform_views", i6.o.f7550b);
        this.f7362a = jVar;
        jVar.e(aVar2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String c(Exception exc) {
        return z5.b.d(exc);
    }

    public void d(int i8) {
        i6.j jVar = this.f7362a;
        if (jVar == null) {
            return;
        }
        jVar.c("viewFocused", Integer.valueOf(i8));
    }

    public void e(g gVar) {
        this.f7363b = gVar;
    }
}
