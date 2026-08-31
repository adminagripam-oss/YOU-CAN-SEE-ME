package u5;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import e6.a;
import i6.j;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import u5.w;
/* loaded from: classes.dex */
public class w implements j.c, e6.a {

    /* renamed from: a  reason: collision with root package name */
    private i6.j f12258a;

    /* renamed from: b  reason: collision with root package name */
    private Context f12259b;

    /* renamed from: c  reason: collision with root package name */
    private final Map f12260c = new HashMap();

    /* renamed from: d  reason: collision with root package name */
    private HandlerThread f12261d;

    /* renamed from: e  reason: collision with root package name */
    private Handler f12262e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a implements j.d {

        /* renamed from: a  reason: collision with root package name */
        private final j.d f12263a;

        /* renamed from: b  reason: collision with root package name */
        private final Handler f12264b = new Handler(Looper.getMainLooper());

        a(j.d dVar) {
            this.f12263a = dVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void c(String str, String str2, Object obj) {
            this.f12263a.error(str, str2, obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void d(Object obj) {
            this.f12263a.success(obj);
        }

        @Override // i6.j.d
        public void error(final String str, final String str2, final Object obj) {
            this.f12264b.post(new Runnable() { // from class: u5.u
                @Override // java.lang.Runnable
                public final void run() {
                    w.a.this.c(str, str2, obj);
                }
            });
        }

        @Override // i6.j.d
        public void notImplemented() {
            Handler handler = this.f12264b;
            final j.d dVar = this.f12263a;
            Objects.requireNonNull(dVar);
            handler.post(new Runnable() { // from class: u5.t
                @Override // java.lang.Runnable
                public final void run() {
                    j.d.this.notImplemented();
                }
            });
        }

        @Override // i6.j.d
        public void success(final Object obj) {
            this.f12264b.post(new Runnable() { // from class: u5.v
                @Override // java.lang.Runnable
                public final void run() {
                    w.a.this.d(obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements Runnable {

        /* renamed from: e  reason: collision with root package name */
        private final i6.i f12265e;

        /* renamed from: f  reason: collision with root package name */
        private final j.d f12266f;

        /* loaded from: classes.dex */
        class a implements z {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ p f12268a;

            /* renamed from: b  reason: collision with root package name */
            final /* synthetic */ s f12269b;

            a(p pVar, s sVar) {
                this.f12268a = pVar;
                this.f12269b = sVar;
            }

            @Override // u5.z
            public void b(Exception exc) {
                b.this.d(exc);
            }

            @Override // u5.z
            /* renamed from: c */
            public void a(Void r42) {
                char c8;
                j.d dVar;
                Object Z;
                j.d dVar2;
                try {
                    String str = b.this.f12265e.f7535a;
                    switch (str.hashCode()) {
                        case -1703690313:
                            if (str.equals("isDeviceSecure")) {
                                c8 = 7;
                                break;
                            }
                            c8 = 65535;
                            break;
                        case -1413382213:
                            if (str.equals("isBiometricAvailable")) {
                                c8 = 6;
                                break;
                            }
                            c8 = 65535;
                            break;
                        case -1335458389:
                            if (str.equals("delete")) {
                                c8 = 4;
                                break;
                            }
                            c8 = 65535;
                            break;
                        case -358737930:
                            if (str.equals("deleteAll")) {
                                c8 = 5;
                                break;
                            }
                            c8 = 65535;
                            break;
                        case 3496342:
                            if (str.equals("read")) {
                                c8 = 1;
                                break;
                            }
                            c8 = 65535;
                            break;
                        case 113399775:
                            if (str.equals("write")) {
                                c8 = 0;
                                break;
                            }
                            c8 = 65535;
                            break;
                        case 208013248:
                            if (str.equals("containsKey")) {
                                c8 = 3;
                                break;
                            }
                            c8 = 65535;
                            break;
                        case 1080375339:
                            if (str.equals("readAll")) {
                                c8 = 2;
                                break;
                            }
                            c8 = 65535;
                            break;
                        default:
                            c8 = 65535;
                            break;
                    }
                    Map map = null;
                    switch (c8) {
                        case 0:
                            String d8 = w.d(this.f12268a, b.this.f12265e);
                            String f8 = w.f(b.this.f12265e);
                            if (f8 == null) {
                                b.this.f12266f.error("null", null, null);
                                return;
                            }
                            this.f12268a.f0(d8, f8);
                            dVar = b.this.f12266f;
                            dVar.success(map);
                            return;
                        case 1:
                            String d9 = w.d(this.f12268a, b.this.f12265e);
                            if (!this.f12268a.r(d9)) {
                                dVar = b.this.f12266f;
                                dVar.success(map);
                                return;
                            }
                            Z = this.f12268a.Z(d9);
                            dVar2 = b.this.f12266f;
                            dVar2.success(Z);
                            return;
                        case 2:
                            dVar = b.this.f12266f;
                            map = this.f12268a.a0();
                            dVar.success(map);
                            return;
                        case 3:
                            boolean r8 = this.f12268a.r(w.d(this.f12268a, b.this.f12265e));
                            dVar2 = b.this.f12266f;
                            Z = Boolean.valueOf(r8);
                            dVar2.success(Z);
                            return;
                        case 4:
                            this.f12268a.v(w.d(this.f12268a, b.this.f12265e));
                            dVar = b.this.f12266f;
                            dVar.success(map);
                            return;
                        case 5:
                            this.f12268a.w();
                            dVar = b.this.f12266f;
                            dVar.success(map);
                            return;
                        case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                            boolean J = this.f12268a.J();
                            dVar2 = b.this.f12266f;
                            Z = Boolean.valueOf(J);
                            dVar2.success(Z);
                            return;
                        case 7:
                            boolean K = this.f12268a.K();
                            dVar2 = b.this.f12266f;
                            Z = Boolean.valueOf(K);
                            dVar2.success(Z);
                            return;
                        default:
                            b.this.f12266f.notImplemented();
                            return;
                    }
                } catch (Exception e8) {
                    e = e8;
                    if (this.f12269b.t()) {
                        try {
                            this.f12268a.w();
                            b.this.f12266f.success("Data has been reset");
                            return;
                        } catch (Exception e9) {
                            e = e9;
                            b.this.d(e);
                        }
                    }
                    b.this.d(e);
                }
            }
        }

        b(i6.i iVar, j.d dVar) {
            this.f12265e = iVar;
            this.f12266f = dVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d(Exception exc) {
            StringWriter stringWriter = new StringWriter();
            exc.printStackTrace(new PrintWriter(stringWriter));
            this.f12266f.error("Exception encountered", exc.getMessage() != null ? exc.getMessage() : "Unknown error", stringWriter.toString());
        }

        @Override // java.lang.Runnable
        public void run() {
            Object obj;
            try {
                i6.i iVar = this.f12265e;
                if (iVar != null && (obj = iVar.f7536b) != null) {
                    if (!(obj instanceof Map)) {
                        d(new IllegalArgumentException("Method call arguments must be a Map"));
                        return;
                    }
                    Object obj2 = ((Map) obj).get("options");
                    s sVar = new s(obj2 instanceof Map ? (Map) obj2 : new HashMap());
                    p e8 = w.this.e(sVar);
                    e8.F(sVar, new a(e8, sVar));
                    return;
                }
                d(new IllegalArgumentException("Method call arguments are null"));
            } catch (Exception e9) {
                d(e9);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String d(p pVar, i6.i iVar) {
        return pVar.p((String) ((Map) iVar.f7536b).get("key"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public p e(s sVar) {
        String m8;
        if (sVar.q()) {
            m8 = "ns:" + sVar.n();
        } else {
            m8 = sVar.m();
        }
        synchronized (this.f12260c) {
            p pVar = (p) this.f12260c.get(m8);
            if (pVar != null) {
                return pVar;
            }
            p pVar2 = new p(this.f12259b);
            this.f12260c.put(m8, pVar2);
            return pVar2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String f(i6.i iVar) {
        return (String) ((Map) iVar.f7536b).get("value");
    }

    public void g(i6.b bVar, Context context) {
        try {
            this.f12259b = context.getApplicationContext();
            HandlerThread handlerThread = new HandlerThread("com.it_nomads.fluttersecurestorage.worker");
            this.f12261d = handlerThread;
            handlerThread.start();
            this.f12262e = new Handler(this.f12261d.getLooper());
            i6.j jVar = new i6.j(bVar, "plugins.it_nomads.com/flutter_secure_storage");
            this.f12258a = jVar;
            jVar.e(this);
        } catch (Exception e8) {
            Log.e("FlutterSecureStoragePlugin", "Registration failed", e8);
        }
    }

    @Override // e6.a
    public void onAttachedToEngine(a.b bVar) {
        g(bVar.b(), bVar.a());
    }

    @Override // e6.a
    public void onDetachedFromEngine(a.b bVar) {
        if (this.f12258a != null) {
            this.f12261d.quitSafely();
            this.f12261d = null;
            this.f12258a.e(null);
            this.f12258a = null;
        }
        synchronized (this.f12260c) {
            this.f12260c.clear();
        }
        this.f12259b = null;
    }

    @Override // i6.j.c
    public void onMethodCall(i6.i iVar, j.d dVar) {
        this.f12262e.post(new b(iVar, new a(dVar)));
    }
}
