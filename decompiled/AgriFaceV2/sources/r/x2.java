package r;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.DynamicRangeProfiles;
import android.hardware.camera2.params.OutputConfiguration;
import android.os.Build;
import android.view.Surface;
import androidx.camera.camera2.internal.compat.quirk.CaptureNoResponseQuirk;
import androidx.camera.core.impl.utils.SurfaceUtil;
import androidx.concurrent.futures.c;
import b0.q2;
import b0.s0;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import r.h2;
import r.k4;
import r.v4;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class x2 implements y2 {

    /* renamed from: a  reason: collision with root package name */
    final Object f10842a;

    /* renamed from: b  reason: collision with root package name */
    private final List f10843b;

    /* renamed from: c  reason: collision with root package name */
    private final d f10844c;

    /* renamed from: d  reason: collision with root package name */
    k4.a f10845d;

    /* renamed from: e  reason: collision with root package name */
    k4 f10846e;

    /* renamed from: f  reason: collision with root package name */
    b0.q2 f10847f;

    /* renamed from: g  reason: collision with root package name */
    private final Map f10848g;

    /* renamed from: h  reason: collision with root package name */
    List f10849h;

    /* renamed from: i  reason: collision with root package name */
    c f10850i;

    /* renamed from: j  reason: collision with root package name */
    i4.d f10851j;

    /* renamed from: k  reason: collision with root package name */
    c.a f10852k;

    /* renamed from: l  reason: collision with root package name */
    private Map f10853l;

    /* renamed from: m  reason: collision with root package name */
    private final v.v f10854m;

    /* renamed from: n  reason: collision with root package name */
    private final v.z f10855n;

    /* renamed from: o  reason: collision with root package name */
    private final v.s f10856o;

    /* renamed from: p  reason: collision with root package name */
    private final t.g f10857p;

    /* renamed from: q  reason: collision with root package name */
    private final v.y f10858q;

    /* renamed from: r  reason: collision with root package name */
    private final boolean f10859r;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements f0.c {
        a() {
        }

        @Override // f0.c
        public void b(Throwable th) {
            synchronized (x2.this.f10842a) {
                x2.this.f10845d.stop();
                int ordinal = x2.this.f10850i.ordinal();
                if ((ordinal == 3 || ordinal == 5 || ordinal == 6) && !(th instanceof CancellationException)) {
                    y.f1.m("CaptureSession", "Opening session with fail " + x2.this.f10850i, th);
                    x2.this.r();
                }
            }
        }

        @Override // f0.c
        /* renamed from: c */
        public void a(Void r12) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b extends CameraCaptureSession.CaptureCallback {
        b() {
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
            synchronized (x2.this.f10842a) {
                b0.q2 q2Var = x2.this.f10847f;
                if (q2Var == null) {
                    return;
                }
                b0.s0 j8 = q2Var.j();
                y.f1.a("CaptureSession", "Submit FLASH_MODE_OFF request");
                x2 x2Var = x2.this;
                x2Var.d(Collections.singletonList(x2Var.f10855n.a(j8)));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum c {
        UNINITIALIZED,
        INITIALIZED,
        GET_SURFACE,
        OPENING,
        OPENED,
        CLOSED,
        RELEASING,
        RELEASED
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class d extends k4.c {
        d() {
        }

        @Override // r.k4.c
        public void r(k4 k4Var) {
            synchronized (x2.this.f10842a) {
                switch (x2.this.f10850i.ordinal()) {
                    case 0:
                    case 1:
                    case 2:
                    case 4:
                        throw new IllegalStateException("onConfigureFailed() should not be possible in state: " + x2.this.f10850i);
                    case 3:
                    case 5:
                    case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                        x2.this.r();
                        break;
                    case 7:
                        y.f1.a("CaptureSession", "ConfigureFailed callback after change to RELEASED state");
                        break;
                }
                y.f1.c("CaptureSession", "CameraCaptureSession.onConfigureFailed() " + x2.this.f10850i);
            }
        }

        @Override // r.k4.c
        public void s(k4 k4Var) {
            synchronized (x2.this.f10842a) {
                switch (x2.this.f10850i.ordinal()) {
                    case 0:
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                        throw new IllegalStateException("onConfigured() should not be possible in state: " + x2.this.f10850i);
                    case 3:
                        x2 x2Var = x2.this;
                        x2Var.f10850i = c.OPENED;
                        x2Var.f10846e = k4Var;
                        y.f1.a("CaptureSession", "Attempting to send capture request onConfigured");
                        x2 x2Var2 = x2.this;
                        x2Var2.x(x2Var2.f10847f);
                        x2.this.w();
                        break;
                    case 5:
                        x2.this.f10846e = k4Var;
                        break;
                    case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                        k4Var.close();
                        break;
                }
                y.f1.a("CaptureSession", "CameraCaptureSession.onConfigured() mState=" + x2.this.f10850i);
            }
        }

        @Override // r.k4.c
        public void t(k4 k4Var) {
            synchronized (x2.this.f10842a) {
                if (x2.this.f10850i.ordinal() == 0) {
                    throw new IllegalStateException("onReady() should not be possible in state: " + x2.this.f10850i);
                }
                y.f1.a("CaptureSession", "CameraCaptureSession.onReady() " + x2.this.f10850i);
            }
        }

        @Override // r.k4.c
        public void u(k4 k4Var) {
            synchronized (x2.this.f10842a) {
                if (x2.this.f10850i == c.UNINITIALIZED) {
                    throw new IllegalStateException("onSessionFinished() should not be possible in state: " + x2.this.f10850i);
                }
                y.f1.a("CaptureSession", "onSessionFinished()");
                x2.this.r();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public x2(t.g gVar) {
        this(gVar, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object B(c.a aVar) {
        String str;
        synchronized (this.f10842a) {
            g1.e.m(this.f10852k == null, "Release completer expected to be null");
            this.f10852k = aVar;
            str = "Release[session=" + this + "]";
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: C */
    public i4.d A(List list, b0.q2 q2Var, CameraDevice cameraDevice) {
        synchronized (this.f10842a) {
            int ordinal = this.f10850i.ordinal();
            if (ordinal != 0 && ordinal != 1) {
                if (ordinal == 2) {
                    this.f10848g.clear();
                    for (int i8 = 0; i8 < list.size(); i8++) {
                        this.f10848g.put((b0.b1) this.f10849h.get(i8), (Surface) list.get(i8));
                    }
                    this.f10850i = c.OPENING;
                    y.f1.a("CaptureSession", "Opening capture session.");
                    k4.c w7 = v4.w(this.f10844c, new v4.a(q2Var.k()));
                    q.a aVar = new q.a(q2Var.f());
                    s0.a k8 = s0.a.k(q2Var.j());
                    Map hashMap = new HashMap();
                    if (this.f10859r && Build.VERSION.SDK_INT >= 35) {
                        hashMap = q(u(q2Var.h()), this.f10848g);
                    }
                    ArrayList arrayList = new ArrayList();
                    String d02 = aVar.d0(null);
                    for (q2.f fVar : q2Var.h()) {
                        t.k kVar = (!this.f10859r || Build.VERSION.SDK_INT < 35) ? null : (t.k) hashMap.get(fVar);
                        if (kVar == null) {
                            kVar = s(fVar, this.f10848g, d02);
                            if (this.f10853l.containsKey(fVar.f())) {
                                kVar.h(((Long) this.f10853l.get(fVar.f())).longValue());
                            }
                        }
                        arrayList.add(kVar);
                    }
                    t.q l8 = this.f10845d.l(q2Var.l(), t(arrayList), w7);
                    if (q2Var.o() == 5 && q2Var.g() != null) {
                        l8.f(t.j.b(q2Var.g()));
                    }
                    try {
                        CaptureRequest f8 = c2.f(k8.h(), cameraDevice, this.f10858q);
                        if (f8 != null) {
                            l8.g(f8);
                        }
                        return this.f10845d.i(cameraDevice, l8, this.f10849h);
                    } catch (CameraAccessException e8) {
                        return f0.n.n(e8);
                    }
                } else if (ordinal != 4) {
                    return f0.n.n(new CancellationException("openCaptureSession() not execute in state: " + this.f10850i));
                }
            }
            return f0.n.n(new IllegalStateException("openCaptureSession() should not be possible in state: " + this.f10850i));
        }
    }

    private CameraCaptureSession.CaptureCallback o(List list, CameraCaptureSession.CaptureCallback... captureCallbackArr) {
        ArrayList arrayList = new ArrayList(list.size() + captureCallbackArr.length);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(q2.a((b0.n) it.next()));
        }
        Collections.addAll(arrayList, captureCallbackArr);
        return s0.a(arrayList);
    }

    private static List p(List list, int i8) {
        try {
            return (List) OutputConfiguration.class.getMethod("createInstancesForMultiResolutionOutput", Collection.class, Integer.TYPE).invoke(null, list, Integer.valueOf(i8));
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e8) {
            y.f1.c("CaptureSession", "Failed to create instances for multi-resolution output, " + e8.getMessage());
            return null;
        }
    }

    private static Map q(Map map, Map map2) {
        HashMap hashMap = new HashMap();
        for (Integer num : map.keySet()) {
            int intValue = num.intValue();
            ArrayList arrayList = new ArrayList();
            int i8 = 0;
            for (q2.f fVar : (List) map.get(Integer.valueOf(intValue))) {
                SurfaceUtil.a a8 = SurfaceUtil.a((Surface) map2.get(fVar.f()));
                if (i8 == 0) {
                    i8 = a8.f2726a;
                }
                s2.a();
                int i9 = a8.f2727b;
                int i10 = a8.f2728c;
                String d8 = fVar.d();
                Objects.requireNonNull(d8);
                arrayList.add(r2.a(i9, i10, d8));
            }
            if (i8 == 0 || arrayList.isEmpty()) {
                y.f1.c("CaptureSession", "Skips to create instances for multi-resolution output. imageFormat: " + i8 + ", streamInfos size: " + arrayList.size());
            } else {
                List p8 = p(arrayList, i8);
                if (p8 != null) {
                    for (q2.f fVar2 : (List) map.get(Integer.valueOf(intValue))) {
                        OutputConfiguration outputConfiguration = (OutputConfiguration) p8.remove(0);
                        outputConfiguration.addSurface((Surface) map2.get(fVar2.f()));
                        hashMap.put(fVar2, new t.k(outputConfiguration));
                    }
                }
            }
        }
        return hashMap;
    }

    private t.k s(q2.f fVar, Map map, String str) {
        long j8;
        DynamicRangeProfiles d8;
        Surface surface = (Surface) map.get(fVar.f());
        g1.e.k(surface, "Surface in OutputConfig not found in configuredSurfaceMap.");
        t.k kVar = new t.k(fVar.g(), surface);
        if (str == null) {
            str = fVar.d();
        }
        kVar.g(str);
        if (fVar.c() == 0) {
            kVar.f(1);
        } else if (fVar.c() == 1) {
            kVar.f(2);
        }
        if (!fVar.e().isEmpty()) {
            kVar.b();
            for (b0.b1 b1Var : fVar.e()) {
                Surface surface2 = (Surface) map.get(b1Var);
                g1.e.k(surface2, "Surface in OutputConfig not found in configuredSurfaceMap.");
                kVar.a(surface2);
            }
        }
        if (Build.VERSION.SDK_INT >= 33 && (d8 = this.f10857p.d()) != null) {
            y.a0 b8 = fVar.b();
            Long a8 = t.d.a(b8, d8);
            if (a8 != null) {
                j8 = a8.longValue();
                kVar.e(j8);
                return kVar;
            }
            y.f1.c("CaptureSession", "Requested dynamic range is not supported. Defaulting to STANDARD dynamic range profile.\nRequested dynamic range:\n  " + b8);
        }
        j8 = 1;
        kVar.e(j8);
        return kVar;
    }

    private List t(List list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            t.k kVar = (t.k) it.next();
            if (!arrayList.contains(kVar.d())) {
                arrayList.add(kVar.d());
                arrayList2.add(kVar);
            }
        }
        return arrayList2;
    }

    private static Map u(Collection collection) {
        HashMap hashMap = new HashMap();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            q2.f fVar = (q2.f) it.next();
            if (fVar.g() > 0 && fVar.e().isEmpty()) {
                List list = (List) hashMap.get(Integer.valueOf(fVar.g()));
                if (list == null) {
                    list = new ArrayList();
                    hashMap.put(Integer.valueOf(fVar.g()), list);
                }
                list.add(fVar);
            }
        }
        HashMap hashMap2 = new HashMap();
        for (Integer num : hashMap.keySet()) {
            int intValue = num.intValue();
            if (((List) hashMap.get(Integer.valueOf(intValue))).size() >= 2) {
                hashMap2.put(Integer.valueOf(intValue), (List) hashMap.get(Integer.valueOf(intValue)));
            }
        }
        return hashMap2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void y(CameraCaptureSession cameraCaptureSession, int i8, boolean z7) {
        synchronized (this.f10842a) {
            if (this.f10850i == c.OPENED) {
                x(this.f10847f);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void z() {
        synchronized (this.f10842a) {
            if (this.f10843b.isEmpty()) {
                return;
            }
            v(this.f10843b);
            this.f10843b.clear();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x004e A[Catch: all -> 0x00a7, TryCatch #0 {, blocks: (B:4:0x0003, B:5:0x0009, B:28:0x00a0, B:7:0x000e, B:10:0x0014, B:14:0x0020, B:13:0x0019, B:15:0x0025, B:17:0x004e, B:18:0x0052, B:20:0x0056, B:21:0x0061, B:22:0x0063, B:24:0x0065, B:25:0x0082, B:26:0x0087, B:27:0x009f), top: B:34:0x0003, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0056 A[Catch: all -> 0x00a7, TryCatch #0 {, blocks: (B:4:0x0003, B:5:0x0009, B:28:0x00a0, B:7:0x000e, B:10:0x0014, B:14:0x0020, B:13:0x0019, B:15:0x0025, B:17:0x004e, B:18:0x0052, B:20:0x0056, B:21:0x0061, B:22:0x0063, B:24:0x0065, B:25:0x0082, B:26:0x0087, B:27:0x009f), top: B:34:0x0003, inners: #1 }] */
    @Override // r.y2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public i4.d a(boolean r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.f10842a
            monitor-enter(r0)
            r.x2$c r1 = r3.f10850i     // Catch: java.lang.Throwable -> La7
            int r1 = r1.ordinal()     // Catch: java.lang.Throwable -> La7
            switch(r1) {
                case 0: goto L87;
                case 1: goto L82;
                case 2: goto L65;
                case 3: goto L25;
                case 4: goto Le;
                case 5: goto Le;
                case 6: goto L52;
                default: goto Lc;
            }     // Catch: java.lang.Throwable -> La7
        Lc:
            goto La0
        Le:
            r.k4 r1 = r3.f10846e     // Catch: java.lang.Throwable -> La7
            if (r1 == 0) goto L25
            if (r4 == 0) goto L20
            r1.k()     // Catch: android.hardware.camera2.CameraAccessException -> L18 java.lang.Throwable -> La7
            goto L20
        L18:
            r4 = move-exception
            java.lang.String r1 = "CaptureSession"
            java.lang.String r2 = "Unable to abort captures."
            y.f1.d(r1, r2, r4)     // Catch: java.lang.Throwable -> La7
        L20:
            r.k4 r4 = r3.f10846e     // Catch: java.lang.Throwable -> La7
            r4.close()     // Catch: java.lang.Throwable -> La7
        L25:
            r.x2$c r4 = r.x2.c.RELEASING     // Catch: java.lang.Throwable -> La7
            r3.f10850i = r4     // Catch: java.lang.Throwable -> La7
            v.s r4 = r3.f10856o     // Catch: java.lang.Throwable -> La7
            r4.i()     // Catch: java.lang.Throwable -> La7
            r.k4$a r4 = r3.f10845d     // Catch: java.lang.Throwable -> La7
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La7
            r1.<init>()     // Catch: java.lang.Throwable -> La7
            java.lang.String r2 = "The Opener shouldn't null in state:"
            r1.append(r2)     // Catch: java.lang.Throwable -> La7
            r.x2$c r2 = r3.f10850i     // Catch: java.lang.Throwable -> La7
            r1.append(r2)     // Catch: java.lang.Throwable -> La7
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> La7
            g1.e.k(r4, r1)     // Catch: java.lang.Throwable -> La7
            r.k4$a r4 = r3.f10845d     // Catch: java.lang.Throwable -> La7
            boolean r4 = r4.stop()     // Catch: java.lang.Throwable -> La7
            if (r4 == 0) goto L52
            r3.r()     // Catch: java.lang.Throwable -> La7
            goto La0
        L52:
            i4.d r4 = r3.f10851j     // Catch: java.lang.Throwable -> La7
            if (r4 != 0) goto L61
            r.u2 r4 = new r.u2     // Catch: java.lang.Throwable -> La7
            r4.<init>()     // Catch: java.lang.Throwable -> La7
            i4.d r4 = androidx.concurrent.futures.c.a(r4)     // Catch: java.lang.Throwable -> La7
            r3.f10851j = r4     // Catch: java.lang.Throwable -> La7
        L61:
            i4.d r4 = r3.f10851j     // Catch: java.lang.Throwable -> La7
            monitor-exit(r0)     // Catch: java.lang.Throwable -> La7
            return r4
        L65:
            r.k4$a r4 = r3.f10845d     // Catch: java.lang.Throwable -> La7
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La7
            r1.<init>()     // Catch: java.lang.Throwable -> La7
            java.lang.String r2 = "The Opener shouldn't null in state:"
            r1.append(r2)     // Catch: java.lang.Throwable -> La7
            r.x2$c r2 = r3.f10850i     // Catch: java.lang.Throwable -> La7
            r1.append(r2)     // Catch: java.lang.Throwable -> La7
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> La7
            g1.e.k(r4, r1)     // Catch: java.lang.Throwable -> La7
            r.k4$a r4 = r3.f10845d     // Catch: java.lang.Throwable -> La7
            r4.stop()     // Catch: java.lang.Throwable -> La7
        L82:
            r.x2$c r4 = r.x2.c.RELEASED     // Catch: java.lang.Throwable -> La7
            r3.f10850i = r4     // Catch: java.lang.Throwable -> La7
            goto La0
        L87:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> La7
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La7
            r1.<init>()     // Catch: java.lang.Throwable -> La7
            java.lang.String r2 = "release() should not be possible in state: "
            r1.append(r2)     // Catch: java.lang.Throwable -> La7
            r.x2$c r2 = r3.f10850i     // Catch: java.lang.Throwable -> La7
            r1.append(r2)     // Catch: java.lang.Throwable -> La7
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> La7
            r4.<init>(r1)     // Catch: java.lang.Throwable -> La7
            throw r4     // Catch: java.lang.Throwable -> La7
        La0:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> La7
            r4 = 0
            i4.d r4 = f0.n.p(r4)
            return r4
        La7:
            r4 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> La7
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: r.x2.a(boolean):i4.d");
    }

    @Override // r.y2
    public List b() {
        List unmodifiableList;
        synchronized (this.f10842a) {
            unmodifiableList = Collections.unmodifiableList(this.f10843b);
        }
        return unmodifiableList;
    }

    @Override // r.y2
    public void c(b0.q2 q2Var) {
        synchronized (this.f10842a) {
            switch (this.f10850i.ordinal()) {
                case 0:
                    throw new IllegalStateException("setSessionConfig() should not be possible in state: " + this.f10850i);
                case 1:
                case 2:
                case 3:
                    this.f10847f = q2Var;
                    break;
                case 4:
                    this.f10847f = q2Var;
                    if (q2Var != null) {
                        if (!this.f10848g.keySet().containsAll(q2Var.n())) {
                            y.f1.c("CaptureSession", "Does not have the proper configured lists");
                            return;
                        }
                        y.f1.a("CaptureSession", "Attempting to submit CaptureRequest after setting");
                        x(this.f10847f);
                        break;
                    } else {
                        return;
                    }
                case 5:
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                case 7:
                    throw new IllegalStateException("Session configuration cannot be set on a closed/released session.");
            }
        }
    }

    @Override // r.y2
    public void close() {
        synchronized (this.f10842a) {
            int ordinal = this.f10850i.ordinal();
            if (ordinal == 0) {
                throw new IllegalStateException("close() should not be possible in state: " + this.f10850i);
            }
            if (ordinal != 1) {
                if (ordinal == 2) {
                    k4.a aVar = this.f10845d;
                    g1.e.k(aVar, "The Opener shouldn't null in state:" + this.f10850i);
                    this.f10845d.stop();
                } else if (ordinal == 3 || ordinal == 4) {
                    k4.a aVar2 = this.f10845d;
                    g1.e.k(aVar2, "The Opener shouldn't null in state:" + this.f10850i);
                    this.f10845d.stop();
                    this.f10850i = c.CLOSED;
                    this.f10856o.i();
                    this.f10847f = null;
                }
            }
            this.f10850i = c.RELEASED;
        }
    }

    @Override // r.y2
    public void d(List list) {
        synchronized (this.f10842a) {
            switch (this.f10850i.ordinal()) {
                case 0:
                    throw new IllegalStateException("issueCaptureRequests() should not be possible in state: " + this.f10850i);
                case 1:
                case 2:
                case 3:
                    this.f10843b.addAll(list);
                    break;
                case 4:
                    this.f10843b.addAll(list);
                    w();
                    break;
                case 5:
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                case 7:
                    throw new IllegalStateException("Cannot issue capture request on a closed/released session.");
            }
        }
    }

    @Override // r.y2
    public b0.q2 e() {
        b0.q2 q2Var;
        synchronized (this.f10842a) {
            q2Var = this.f10847f;
        }
        return q2Var;
    }

    @Override // r.y2
    public void f() {
        ArrayList<b0.s0> arrayList;
        synchronized (this.f10842a) {
            if (this.f10843b.isEmpty()) {
                arrayList = null;
            } else {
                arrayList = new ArrayList(this.f10843b);
                this.f10843b.clear();
            }
        }
        if (arrayList != null) {
            for (b0.s0 s0Var : arrayList) {
                for (b0.n nVar : s0Var.c()) {
                    nVar.a(s0Var.f());
                }
            }
        }
    }

    @Override // r.y2
    public boolean g() {
        boolean z7;
        synchronized (this.f10842a) {
            c cVar = this.f10850i;
            z7 = cVar == c.OPENED || cVar == c.OPENING;
        }
        return z7;
    }

    @Override // r.y2
    public void h(Map map) {
        synchronized (this.f10842a) {
            this.f10853l = map;
        }
    }

    @Override // r.y2
    public i4.d i(final b0.q2 q2Var, final CameraDevice cameraDevice, k4.a aVar) {
        synchronized (this.f10842a) {
            if (this.f10850i.ordinal() == 1) {
                this.f10850i = c.GET_SURFACE;
                ArrayList arrayList = new ArrayList(q2Var.n());
                this.f10849h = arrayList;
                this.f10845d = aVar;
                f0.d f8 = f0.d.a(aVar.e(arrayList, 5000L)).f(new f0.a() { // from class: r.v2
                    @Override // f0.a
                    public final i4.d apply(Object obj) {
                        i4.d A;
                        A = x2.this.A(q2Var, cameraDevice, (List) obj);
                        return A;
                    }
                }, this.f10845d.c());
                f0.n.j(f8, new a(), this.f10845d.c());
                return f0.n.B(f8);
            }
            y.f1.c("CaptureSession", "Open not allowed in state: " + this.f10850i);
            return f0.n.n(new IllegalStateException("open() should not allow the state: " + this.f10850i));
        }
    }

    void r() {
        c cVar = this.f10850i;
        c cVar2 = c.RELEASED;
        if (cVar == cVar2) {
            y.f1.a("CaptureSession", "Skipping finishClose due to being state RELEASED.");
            return;
        }
        this.f10850i = cVar2;
        this.f10846e = null;
        c.a aVar = this.f10852k;
        if (aVar != null) {
            aVar.c(null);
            this.f10852k = null;
        }
    }

    int v(List list) {
        h2 h2Var;
        ArrayList arrayList;
        boolean z7;
        boolean z8;
        synchronized (this.f10842a) {
            if (this.f10850i != c.OPENED) {
                y.f1.a("CaptureSession", "Skipping issueBurstCaptureRequest due to session closed");
                return -1;
            } else if (list.isEmpty()) {
                return -1;
            } else {
                try {
                    h2Var = new h2();
                    arrayList = new ArrayList();
                    y.f1.a("CaptureSession", "Issuing capture request.");
                    Iterator it = list.iterator();
                    z7 = false;
                    while (it.hasNext()) {
                        b0.s0 s0Var = (b0.s0) it.next();
                        if (s0Var.i().isEmpty()) {
                            y.f1.a("CaptureSession", "Skipping issuing empty capture request.");
                        } else {
                            Iterator it2 = s0Var.i().iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    z8 = true;
                                    break;
                                }
                                b0.b1 b1Var = (b0.b1) it2.next();
                                if (!this.f10848g.containsKey(b1Var)) {
                                    y.f1.a("CaptureSession", "Skipping capture request with invalid surface: " + b1Var);
                                    z8 = false;
                                    break;
                                }
                            }
                            if (z8) {
                                if (s0Var.k() == 2) {
                                    z7 = true;
                                }
                                s0.a k8 = s0.a.k(s0Var);
                                if (s0Var.k() == 5 && s0Var.d() != null) {
                                    k8.p(s0Var.d());
                                }
                                b0.q2 q2Var = this.f10847f;
                                if (q2Var != null) {
                                    k8.e(q2Var.j().g());
                                }
                                k8.e(s0Var.g());
                                CaptureRequest e8 = c2.e(k8.h(), this.f10846e.m(), this.f10848g, false, this.f10858q);
                                if (e8 == null) {
                                    y.f1.a("CaptureSession", "Skipping issuing request without surface.");
                                    return -1;
                                }
                                ArrayList arrayList2 = new ArrayList();
                                for (b0.n nVar : s0Var.c()) {
                                    q2.b(nVar, arrayList2);
                                }
                                h2Var.a(e8, arrayList2);
                                arrayList.add(e8);
                            }
                        }
                    }
                } catch (CameraAccessException e9) {
                    y.f1.c("CaptureSession", "Unable to access camera: " + e9.getMessage());
                    Thread.dumpStack();
                }
                if (arrayList.isEmpty()) {
                    y.f1.a("CaptureSession", "Skipping issuing burst request due to no valid request elements");
                    return -1;
                }
                if (this.f10854m.a(arrayList, z7)) {
                    this.f10846e.d();
                    h2Var.c(new h2.a() { // from class: r.w2
                        @Override // r.h2.a
                        public final void a(CameraCaptureSession cameraCaptureSession, int i8, boolean z9) {
                            x2.this.y(cameraCaptureSession, i8, z9);
                        }
                    });
                }
                if (this.f10855n.b(arrayList, z7)) {
                    h2Var.a((CaptureRequest) arrayList.get(arrayList.size() - 1), Collections.singletonList(new b()));
                }
                return this.f10846e.f(arrayList, h2Var);
            }
        }
    }

    void w() {
        this.f10856o.e().d(new Runnable() { // from class: r.t2
            @Override // java.lang.Runnable
            public final void run() {
                x2.this.z();
            }
        }, e0.c.b());
    }

    int x(b0.q2 q2Var) {
        synchronized (this.f10842a) {
            if (q2Var == null) {
                y.f1.a("CaptureSession", "Skipping issueRepeatingCaptureRequests for no configuration case.");
                return -1;
            } else if (this.f10850i != c.OPENED) {
                y.f1.a("CaptureSession", "Skipping issueRepeatingCaptureRequests due to session closed");
                return -1;
            } else {
                b0.s0 j8 = q2Var.j();
                if (j8.i().isEmpty()) {
                    y.f1.a("CaptureSession", "Skipping issueRepeatingCaptureRequests for no surface.");
                    try {
                        this.f10846e.d();
                    } catch (CameraAccessException e8) {
                        y.f1.c("CaptureSession", "Unable to access camera: " + e8.getMessage());
                        Thread.dumpStack();
                    }
                    return -1;
                }
                try {
                    y.f1.a("CaptureSession", "Issuing request for session.");
                    CaptureRequest e9 = c2.e(j8, this.f10846e.m(), this.f10848g, true, this.f10858q);
                    if (e9 == null) {
                        y.f1.a("CaptureSession", "Skipping issuing empty request for session.");
                        return -1;
                    }
                    return this.f10846e.n(e9, this.f10856o.d(o(j8.c(), new CameraCaptureSession.CaptureCallback[0])));
                } catch (CameraAccessException e10) {
                    y.f1.c("CaptureSession", "Unable to access camera: " + e10.getMessage());
                    Thread.dumpStack();
                    return -1;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public x2(t.g gVar, b0.l2 l2Var) {
        this(gVar, l2Var, false);
    }

    x2(t.g gVar, b0.l2 l2Var, boolean z7) {
        this.f10842a = new Object();
        this.f10843b = new ArrayList();
        this.f10848g = new HashMap();
        this.f10849h = Collections.emptyList();
        this.f10850i = c.UNINITIALIZED;
        this.f10853l = new HashMap();
        this.f10854m = new v.v();
        this.f10855n = new v.z();
        this.f10850i = c.INITIALIZED;
        this.f10857p = gVar;
        this.f10844c = new d();
        this.f10856o = new v.s(l2Var.a(CaptureNoResponseQuirk.class));
        this.f10858q = new v.y(l2Var);
        this.f10859r = z7;
    }

    x2(t.g gVar, boolean z7) {
        this(gVar, new b0.l2(Collections.emptyList()), z7);
    }
}
