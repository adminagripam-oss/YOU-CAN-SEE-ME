package m6;

import android.util.Log;
import i6.a;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import m6.t0;
/* loaded from: classes.dex */
public abstract class t0 {

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final i6.b f9470a;

        /* renamed from: m6.t0$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public interface InterfaceC0127a {
            void a(Object obj);
        }

        public a(i6.b bVar) {
            this.f9470a = bVar;
        }

        static i6.h c() {
            return new i6.n();
        }

        public void b(Long l8, Long l9, final InterfaceC0127a interfaceC0127a) {
            new i6.a(this.f9470a, "dev.flutter.pigeon.AnalyzerFlutterApi.analyze", c()).d(new ArrayList(Arrays.asList(l8, l9)), new a.e() { // from class: m6.s0
                @Override // i6.a.e
                public final void a(Object obj) {
                    t0.a.InterfaceC0127a.this.a(null);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a0 extends i6.n {

        /* renamed from: d  reason: collision with root package name */
        public static final a0 f9471d = new a0();

        private a0() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // i6.n
        public Object g(byte b8, ByteBuffer byteBuffer) {
            return b8 != Byte.MIN_VALUE ? super.g(b8, byteBuffer) : y.a((ArrayList) f(byteBuffer));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // i6.n
        public void p(ByteArrayOutputStream byteArrayOutputStream, Object obj) {
            if (!(obj instanceof y)) {
                super.p(byteArrayOutputStream, obj);
                return;
            }
            byteArrayOutputStream.write(128);
            p(byteArrayOutputStream, ((y) obj).d());
        }
    }

    /* loaded from: classes.dex */
    public interface a1 {
        static i6.h a() {
            return new i6.n();
        }

        static void c(i6.b bVar, final a1 a1Var) {
            new i6.a(bVar, "dev.flutter.pigeon.PendingRecordingHostApi.start", a()).e(a1Var != null ? new a.d() { // from class: m6.e3
                @Override // i6.a.d
                public final void a(Object obj, a.e eVar) {
                    t0.a1.e(t0.a1.this, obj, eVar);
                }
            } : null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void e(a1 a1Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            arrayList.add(0, a1Var.b(valueOf));
            eVar.a(arrayList);
        }

        Long b(Long l8);
    }

    /* loaded from: classes.dex */
    public static final class a2 {

        /* renamed from: a  reason: collision with root package name */
        private z1 f9472a;

        /* loaded from: classes.dex */
        public static final class a {

            /* renamed from: a  reason: collision with root package name */
            private z1 f9473a;

            public a2 a() {
                a2 a2Var = new a2();
                a2Var.b(this.f9473a);
                return a2Var;
            }

            public a b(z1 z1Var) {
                this.f9473a = z1Var;
                return this;
            }
        }

        a2() {
        }

        static a2 a(ArrayList arrayList) {
            a2 a2Var = new a2();
            Object obj = arrayList.get(0);
            a2Var.b(obj == null ? null : z1.values()[((Integer) obj).intValue()]);
            return a2Var;
        }

        public void b(z1 z1Var) {
            if (z1Var == null) {
                throw new IllegalStateException("Nonnull field \"value\" is null.");
            }
            this.f9472a = z1Var;
        }

        ArrayList c() {
            ArrayList arrayList = new ArrayList(1);
            z1 z1Var = this.f9472a;
            arrayList.add(z1Var == null ? null : Integer.valueOf(z1Var.f9575e));
            return arrayList;
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        static i6.h a() {
            return new i6.n();
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void c(b bVar, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            bVar.b(valueOf);
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        static void d(i6.b bVar, final b bVar2) {
            new i6.a(bVar, "dev.flutter.pigeon.AnalyzerHostApi.create", a()).e(bVar2 != null ? new a.d() { // from class: m6.u0
                @Override // i6.a.d
                public final void a(Object obj, a.e eVar) {
                    t0.b.c(t0.b.this, obj, eVar);
                }
            } : null);
        }

        void b(Long l8);
    }

    /* loaded from: classes.dex */
    public interface b0 {
        static i6.h a() {
            return new i6.n();
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void c(b0 b0Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            x1 x1Var = arrayList2.get(1) == null ? null : x1.values()[((Integer) arrayList2.get(1)).intValue()];
            b2 b2Var = arrayList2.get(2) == null ? null : b2.values()[((Integer) arrayList2.get(2)).intValue()];
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            b0Var.b(valueOf, x1Var, b2Var);
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        static void e(i6.b bVar, final b0 b0Var) {
            new i6.a(bVar, "dev.flutter.pigeon.FallbackStrategyHostApi.create", a()).e(b0Var != null ? new a.d() { // from class: m6.c2
                @Override // i6.a.d
                public final void a(Object obj, a.e eVar) {
                    t0.b0.c(t0.b0.this, obj, eVar);
                }
            } : null);
        }

        void b(Long l8, x1 x1Var, b2 b2Var);
    }

    /* loaded from: classes.dex */
    public static class b1 {

        /* renamed from: a  reason: collision with root package name */
        private final i6.b f9474a;

        /* loaded from: classes.dex */
        public interface a {
            void a(Object obj);
        }

        public b1(i6.b bVar) {
            this.f9474a = bVar;
        }

        static i6.h c() {
            return new i6.n();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void b(Long l8, byte[] bArr, Long l9, Long l10, final a aVar) {
            new i6.a(this.f9474a, "dev.flutter.pigeon.PlaneProxyFlutterApi.create", c()).d(new ArrayList(Arrays.asList(l8, bArr, l9, l10)), new a.e() { // from class: m6.f3
                @Override // i6.a.e
                public final void a(Object obj) {
                    t0.b1.a.this.a(null);
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public enum b2 {
        HIGHER_QUALITY_OR_LOWER_THAN(0),
        HIGHER_QUALITY_THAN(1),
        LOWER_QUALITY_OR_HIGHER_THAN(2),
        LOWER_QUALITY_THAN(3);
        

        /* renamed from: e  reason: collision with root package name */
        final int f9480e;

        b2(int i8) {
            this.f9480e = i8;
        }
    }

    /* loaded from: classes.dex */
    public interface c {
        static i6.h a() {
            return new i6.n();
        }

        static void d(i6.b bVar, final c cVar) {
            new i6.a(bVar, "dev.flutter.pigeon.AspectRatioStrategyHostApi.create", a()).e(cVar != null ? new a.d() { // from class: m6.v0
                @Override // i6.a.d
                public final void a(Object obj, a.e eVar) {
                    t0.c.f(t0.c.this, obj, eVar);
                }
            } : null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void f(c cVar, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            Number number2 = (Number) arrayList2.get(1);
            Number number3 = (Number) arrayList2.get(2);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            cVar.c(valueOf, number2 == null ? null : Long.valueOf(number2.longValue()), number3 == null ? null : Long.valueOf(number3.longValue()));
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        void c(Long l8, Long l9, Long l10);
    }

    /* loaded from: classes.dex */
    public static class c0 extends RuntimeException {

        /* renamed from: e  reason: collision with root package name */
        public final String f9481e;

        /* renamed from: f  reason: collision with root package name */
        public final Object f9482f;

        public c0(String str, String str2, Object obj) {
            super(str2);
            this.f9481e = str;
            this.f9482f = obj;
        }
    }

    /* loaded from: classes.dex */
    public interface c1 {
        static i6.h a() {
            return d1.f9486d;
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void e(c1 c1Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            Number number2 = (Number) arrayList2.get(1);
            Number number3 = (Number) arrayList2.get(2);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            c1Var.c(valueOf, number2 == null ? null : Long.valueOf(number2.longValue()), number3 == null ? null : Long.valueOf(number3.longValue()));
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void h(c1 c1Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            Number number2 = (Number) arrayList2.get(1);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            c1Var.b(valueOf, number2 == null ? null : Long.valueOf(number2.longValue()));
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        static void i(i6.b bVar, final c1 c1Var) {
            i6.a aVar = new i6.a(bVar, "dev.flutter.pigeon.PreviewHostApi.create", a());
            if (c1Var != null) {
                aVar.e(new a.d() { // from class: m6.g3
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.c1.e(t0.c1.this, obj, eVar);
                    }
                });
            } else {
                aVar.e(null);
            }
            i6.a aVar2 = new i6.a(bVar, "dev.flutter.pigeon.PreviewHostApi.setSurfaceProvider", a());
            if (c1Var != null) {
                aVar2.e(new a.d() { // from class: m6.h3
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.c1.j(t0.c1.this, obj, eVar);
                    }
                });
            } else {
                aVar2.e(null);
            }
            i6.a aVar3 = new i6.a(bVar, "dev.flutter.pigeon.PreviewHostApi.releaseFlutterSurfaceTexture", a());
            if (c1Var != null) {
                aVar3.e(new a.d() { // from class: m6.i3
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.c1.l(t0.c1.this, obj, eVar);
                    }
                });
            } else {
                aVar3.e(null);
            }
            i6.a aVar4 = new i6.a(bVar, "dev.flutter.pigeon.PreviewHostApi.getResolutionInfo", a());
            if (c1Var != null) {
                aVar4.e(new a.d() { // from class: m6.j3
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.c1.q(t0.c1.this, obj, eVar);
                    }
                });
            } else {
                aVar4.e(null);
            }
            i6.a aVar5 = new i6.a(bVar, "dev.flutter.pigeon.PreviewHostApi.setTargetRotation", a());
            if (c1Var != null) {
                aVar5.e(new a.d() { // from class: m6.k3
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.c1.h(t0.c1.this, obj, eVar);
                    }
                });
            } else {
                aVar5.e(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void j(c1 c1Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            arrayList.add(0, c1Var.g(valueOf));
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void l(c1 c1Var, Object obj, a.e eVar) {
            ArrayList arrayList = new ArrayList();
            try {
                c1Var.k();
                arrayList.add(0, null);
            } catch (Throwable th) {
                arrayList = t0.a(th);
            }
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void q(c1 c1Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            arrayList.add(0, c1Var.m(valueOf));
            eVar.a(arrayList);
        }

        void b(Long l8, Long l9);

        void c(Long l8, Long l9, Long l10);

        Long g(Long l8);

        void k();

        n1 m(Long l8);
    }

    /* loaded from: classes.dex */
    public static class c2 {

        /* renamed from: a  reason: collision with root package name */
        private final i6.b f9483a;

        /* loaded from: classes.dex */
        public interface a {
            void a(Object obj);
        }

        public c2(i6.b bVar) {
            this.f9483a = bVar;
        }

        static i6.h c() {
            return new i6.n();
        }

        public void b(Long l8, Double d8, Double d9, final a aVar) {
            new i6.a(this.f9483a, "dev.flutter.pigeon.ZoomStateFlutterApi.create", c()).d(new ArrayList(Arrays.asList(l8, d8, d9)), new a.e() { // from class: m6.o4
                @Override // i6.a.e
                public final void a(Object obj) {
                    t0.c2.a.this.a(null);
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public interface d {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class a implements r1 {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ ArrayList f9484a;

            /* renamed from: b  reason: collision with root package name */
            final /* synthetic */ a.e f9485b;

            a(ArrayList arrayList, a.e eVar) {
                this.f9484a = arrayList;
                this.f9485b = eVar;
            }

            @Override // m6.t0.r1
            public void a(Throwable th) {
                this.f9485b.a(t0.a(th));
            }

            @Override // m6.t0.r1
            /* renamed from: b */
            public void success(Void r32) {
                this.f9484a.add(0, null);
                this.f9485b.a(this.f9484a);
            }
        }

        static i6.h a() {
            return new i6.n();
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void c(d dVar, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            Number number2 = (Number) arrayList2.get(1);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            dVar.b(valueOf, number2 == null ? null : Long.valueOf(number2.longValue()));
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void d(d dVar, Object obj, a.e eVar) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            Number number2 = (Number) arrayList2.get(1);
            dVar.h(number == null ? null : Long.valueOf(number.longValue()), number2 != null ? Long.valueOf(number2.longValue()) : null, new a(arrayList, eVar));
        }

        static void f(i6.b bVar, final d dVar) {
            i6.a aVar = new i6.a(bVar, "dev.flutter.pigeon.Camera2CameraControlHostApi.create", a());
            if (dVar != null) {
                aVar.e(new a.d() { // from class: m6.w0
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.d.c(t0.d.this, obj, eVar);
                    }
                });
            } else {
                aVar.e(null);
            }
            i6.a aVar2 = new i6.a(bVar, "dev.flutter.pigeon.Camera2CameraControlHostApi.addCaptureRequestOptions", a());
            if (dVar != null) {
                aVar2.e(new a.d() { // from class: m6.x0
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.d.d(t0.d.this, obj, eVar);
                    }
                });
            } else {
                aVar2.e(null);
            }
        }

        void b(Long l8, Long l9);

        void h(Long l8, Long l9, r1 r1Var);
    }

    /* loaded from: classes.dex */
    public interface d0 {
        static i6.h a() {
            return e0.f9488d;
        }

        static void c(i6.b bVar, final d0 d0Var) {
            new i6.a(bVar, "dev.flutter.pigeon.FocusMeteringActionHostApi.create", a()).e(d0Var != null ? new a.d() { // from class: m6.d2
                @Override // i6.a.d
                public final void a(Object obj, a.e eVar) {
                    t0.d0.d(t0.d0.this, obj, eVar);
                }
            } : null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void d(d0 d0Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            List list = (List) arrayList2.get(1);
            Boolean bool = (Boolean) arrayList2.get(2);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            d0Var.b(valueOf, list, bool);
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        void b(Long l8, List list, Boolean bool);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class d1 extends i6.n {

        /* renamed from: d  reason: collision with root package name */
        public static final d1 f9486d = new d1();

        private d1() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // i6.n
        public Object g(byte b8, ByteBuffer byteBuffer) {
            return b8 != Byte.MIN_VALUE ? super.g(b8, byteBuffer) : n1.a((ArrayList) f(byteBuffer));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // i6.n
        public void p(ByteArrayOutputStream byteArrayOutputStream, Object obj) {
            if (!(obj instanceof n1)) {
                super.p(byteArrayOutputStream, obj);
                return;
            }
            byteArrayOutputStream.write(128);
            p(byteArrayOutputStream, ((n1) obj).f());
        }
    }

    /* loaded from: classes.dex */
    public static class e {

        /* renamed from: a  reason: collision with root package name */
        private final i6.b f9487a;

        /* loaded from: classes.dex */
        public interface a {
            void a(Object obj);
        }

        public e(i6.b bVar) {
            this.f9487a = bVar;
        }

        static i6.h c() {
            return new i6.n();
        }

        public void b(Long l8, final a aVar) {
            new i6.a(this.f9487a, "dev.flutter.pigeon.Camera2CameraInfoFlutterApi.create", c()).d(new ArrayList(Collections.singletonList(l8)), new a.e() { // from class: m6.y0
                @Override // i6.a.e
                public final void a(Object obj) {
                    t0.e.a.this.a(null);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class e0 extends i6.n {

        /* renamed from: d  reason: collision with root package name */
        public static final e0 f9488d = new e0();

        private e0() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // i6.n
        public Object g(byte b8, ByteBuffer byteBuffer) {
            return b8 != Byte.MIN_VALUE ? super.g(b8, byteBuffer) : v0.a((ArrayList) f(byteBuffer));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // i6.n
        public void p(ByteArrayOutputStream byteArrayOutputStream, Object obj) {
            if (!(obj instanceof v0)) {
                super.p(byteArrayOutputStream, obj);
                return;
            }
            byteArrayOutputStream.write(128);
            p(byteArrayOutputStream, ((v0) obj).f());
        }
    }

    /* loaded from: classes.dex */
    public static class e1 {

        /* renamed from: a  reason: collision with root package name */
        private final i6.b f9489a;

        /* loaded from: classes.dex */
        public interface a {
            void a(Object obj);
        }

        public e1(i6.b bVar) {
            this.f9489a = bVar;
        }

        static i6.h c() {
            return new i6.n();
        }

        public void b(Long l8, final a aVar) {
            new i6.a(this.f9489a, "dev.flutter.pigeon.ProcessCameraProviderFlutterApi.create", c()).d(new ArrayList(Collections.singletonList(l8)), new a.e() { // from class: m6.l3
                @Override // i6.a.e
                public final void a(Object obj) {
                    t0.e1.a.this.a(null);
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public interface f {
        static i6.h a() {
            return new i6.n();
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void b(f fVar, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            arrayList.add(0, fVar.h(valueOf));
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void i(f fVar, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            arrayList.add(0, fVar.l(valueOf));
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void k(f fVar, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            arrayList.add(0, fVar.c(valueOf));
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void m(f fVar, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            arrayList.add(0, fVar.f(valueOf));
            eVar.a(arrayList);
        }

        static void n(i6.b bVar, final f fVar) {
            i6.a aVar = new i6.a(bVar, "dev.flutter.pigeon.Camera2CameraInfoHostApi.createFrom", a());
            if (fVar != null) {
                aVar.e(new a.d() { // from class: m6.z0
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.f.b(t0.f.this, obj, eVar);
                    }
                });
            } else {
                aVar.e(null);
            }
            i6.a aVar2 = new i6.a(bVar, "dev.flutter.pigeon.Camera2CameraInfoHostApi.getSupportedHardwareLevel", a());
            if (fVar != null) {
                aVar2.e(new a.d() { // from class: m6.a1
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.f.m(t0.f.this, obj, eVar);
                    }
                });
            } else {
                aVar2.e(null);
            }
            i6.a aVar3 = new i6.a(bVar, "dev.flutter.pigeon.Camera2CameraInfoHostApi.getCameraId", a());
            if (fVar != null) {
                aVar3.e(new a.d() { // from class: m6.b1
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.f.i(t0.f.this, obj, eVar);
                    }
                });
            } else {
                aVar3.e(null);
            }
            i6.a aVar4 = new i6.a(bVar, "dev.flutter.pigeon.Camera2CameraInfoHostApi.getSensorOrientation", a());
            if (fVar != null) {
                aVar4.e(new a.d() { // from class: m6.c1
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.f.k(t0.f.this, obj, eVar);
                    }
                });
            } else {
                aVar4.e(null);
            }
        }

        Long c(Long l8);

        Long f(Long l8);

        Long h(Long l8);

        String l(Long l8);
    }

    /* loaded from: classes.dex */
    public static class f0 {

        /* renamed from: a  reason: collision with root package name */
        private final i6.b f9490a;

        /* loaded from: classes.dex */
        public interface a {
            void a(Object obj);
        }

        public f0(i6.b bVar) {
            this.f9490a = bVar;
        }

        static i6.h c() {
            return new i6.n();
        }

        public void b(Long l8, final a aVar) {
            new i6.a(this.f9490a, "dev.flutter.pigeon.FocusMeteringResultFlutterApi.create", c()).d(new ArrayList(Collections.singletonList(l8)), new a.e() { // from class: m6.e2
                @Override // i6.a.e
                public final void a(Object obj) {
                    t0.f0.a.this.a(null);
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public interface f1 {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class a implements r1 {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ ArrayList f9491a;

            /* renamed from: b  reason: collision with root package name */
            final /* synthetic */ a.e f9492b;

            a(ArrayList arrayList, a.e eVar) {
                this.f9491a = arrayList;
                this.f9492b = eVar;
            }

            @Override // m6.t0.r1
            public void a(Throwable th) {
                this.f9492b.a(t0.a(th));
            }

            @Override // m6.t0.r1
            /* renamed from: b */
            public void success(Long l8) {
                this.f9491a.add(0, l8);
                this.f9492b.a(this.f9491a);
            }
        }

        static i6.h a() {
            return new i6.n();
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void f(f1 f1Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            Number number2 = (Number) arrayList2.get(1);
            List list = (List) arrayList2.get(2);
            Long l8 = null;
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            if (number2 != null) {
                l8 = Long.valueOf(number2.longValue());
            }
            arrayList.add(0, f1Var.q(valueOf, l8, list));
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void i(f1 f1Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            Number number2 = (Number) arrayList2.get(1);
            Long l8 = null;
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            if (number2 != null) {
                l8 = Long.valueOf(number2.longValue());
            }
            arrayList.add(0, f1Var.m(valueOf, l8));
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void k(f1 f1Var, Object obj, a.e eVar) {
            f1Var.j(new a(new ArrayList(), eVar));
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void n(f1 f1Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            List list = (List) arrayList2.get(1);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            f1Var.l(valueOf, list);
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void p(f1 f1Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            f1Var.o(valueOf);
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void s(f1 f1Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            arrayList.add(0, f1Var.e(valueOf));
            eVar.a(arrayList);
        }

        static void t(i6.b bVar, final f1 f1Var) {
            i6.a aVar = new i6.a(bVar, "dev.flutter.pigeon.ProcessCameraProviderHostApi.getInstance", a());
            if (f1Var != null) {
                aVar.e(new a.d() { // from class: m6.m3
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.f1.k(t0.f1.this, obj, eVar);
                    }
                });
            } else {
                aVar.e(null);
            }
            i6.a aVar2 = new i6.a(bVar, "dev.flutter.pigeon.ProcessCameraProviderHostApi.getAvailableCameraInfos", a());
            if (f1Var != null) {
                aVar2.e(new a.d() { // from class: m6.n3
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.f1.s(t0.f1.this, obj, eVar);
                    }
                });
            } else {
                aVar2.e(null);
            }
            i6.a aVar3 = new i6.a(bVar, "dev.flutter.pigeon.ProcessCameraProviderHostApi.bindToLifecycle", a());
            if (f1Var != null) {
                aVar3.e(new a.d() { // from class: m6.o3
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.f1.f(t0.f1.this, obj, eVar);
                    }
                });
            } else {
                aVar3.e(null);
            }
            i6.a aVar4 = new i6.a(bVar, "dev.flutter.pigeon.ProcessCameraProviderHostApi.isBound", a());
            if (f1Var != null) {
                aVar4.e(new a.d() { // from class: m6.p3
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.f1.i(t0.f1.this, obj, eVar);
                    }
                });
            } else {
                aVar4.e(null);
            }
            i6.a aVar5 = new i6.a(bVar, "dev.flutter.pigeon.ProcessCameraProviderHostApi.unbind", a());
            if (f1Var != null) {
                aVar5.e(new a.d() { // from class: m6.q3
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.f1.n(t0.f1.this, obj, eVar);
                    }
                });
            } else {
                aVar5.e(null);
            }
            i6.a aVar6 = new i6.a(bVar, "dev.flutter.pigeon.ProcessCameraProviderHostApi.unbindAll", a());
            if (f1Var != null) {
                aVar6.e(new a.d() { // from class: m6.r3
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.f1.p(t0.f1.this, obj, eVar);
                    }
                });
            } else {
                aVar6.e(null);
            }
        }

        List e(Long l8);

        void j(r1 r1Var);

        void l(Long l8, List list);

        Boolean m(Long l8, Long l9);

        void o(Long l8);

        Long q(Long l8, Long l9, List list);
    }

    /* loaded from: classes.dex */
    public static class g {

        /* renamed from: a  reason: collision with root package name */
        private final i6.b f9493a;

        /* loaded from: classes.dex */
        public interface a {
            void a(Object obj);
        }

        public g(i6.b bVar) {
            this.f9493a = bVar;
        }

        static i6.h c() {
            return new i6.n();
        }

        public void b(Long l8, final a aVar) {
            new i6.a(this.f9493a, "dev.flutter.pigeon.CameraControlFlutterApi.create", c()).d(new ArrayList(Collections.singletonList(l8)), new a.e() { // from class: m6.d1
                @Override // i6.a.e
                public final void a(Object obj) {
                    t0.g.a.this.a(null);
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public interface g0 {
        static i6.h a() {
            return new i6.n();
        }

        static void b(i6.b bVar, final g0 g0Var) {
            new i6.a(bVar, "dev.flutter.pigeon.FocusMeteringResultHostApi.isFocusSuccessful", a()).e(g0Var != null ? new a.d() { // from class: m6.f2
                @Override // i6.a.d
                public final void a(Object obj, a.e eVar) {
                    t0.g0.d(t0.g0.this, obj, eVar);
                }
            } : null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void d(g0 g0Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            arrayList.add(0, g0Var.e(valueOf));
            eVar.a(arrayList);
        }

        Boolean e(Long l8);
    }

    /* loaded from: classes.dex */
    public interface g1 {
        static i6.h a() {
            return h1.f9504d;
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void f(g1 g1Var, Object obj, a.e eVar) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            Long l8 = null;
            x1 x1Var = arrayList2.get(1) == null ? null : x1.values()[((Integer) arrayList2.get(1)).intValue()];
            if (number != null) {
                try {
                    l8 = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            arrayList.add(0, g1Var.b(l8, x1Var));
            eVar.a(arrayList);
        }

        static void g(i6.b bVar, final g1 g1Var) {
            i6.a aVar = new i6.a(bVar, "dev.flutter.pigeon.QualitySelectorHostApi.create", a());
            if (g1Var != null) {
                aVar.e(new a.d() { // from class: m6.s3
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.g1.h(t0.g1.this, obj, eVar);
                    }
                });
            } else {
                aVar.e(null);
            }
            i6.a aVar2 = new i6.a(bVar, "dev.flutter.pigeon.QualitySelectorHostApi.getResolution", a());
            if (g1Var != null) {
                aVar2.e(new a.d() { // from class: m6.t3
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.g1.f(t0.g1.this, obj, eVar);
                    }
                });
            } else {
                aVar2.e(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void h(g1 g1Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            List list = (List) arrayList2.get(1);
            Number number2 = (Number) arrayList2.get(2);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            g1Var.c(valueOf, list, number2 == null ? null : Long.valueOf(number2.longValue()));
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        n1 b(Long l8, x1 x1Var);

        void c(Long l8, List list, Long l9);
    }

    /* loaded from: classes.dex */
    public interface h {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class a implements r1 {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ ArrayList f9494a;

            /* renamed from: b  reason: collision with root package name */
            final /* synthetic */ a.e f9495b;

            a(ArrayList arrayList, a.e eVar) {
                this.f9494a = arrayList;
                this.f9495b = eVar;
            }

            @Override // m6.t0.r1
            public void a(Throwable th) {
                this.f9495b.a(t0.a(th));
            }

            @Override // m6.t0.r1
            /* renamed from: b */
            public void success(Void r32) {
                this.f9494a.add(0, null);
                this.f9495b.a(this.f9494a);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class b implements r1 {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ ArrayList f9496a;

            /* renamed from: b  reason: collision with root package name */
            final /* synthetic */ a.e f9497b;

            b(ArrayList arrayList, a.e eVar) {
                this.f9496a = arrayList;
                this.f9497b = eVar;
            }

            @Override // m6.t0.r1
            public void a(Throwable th) {
                this.f9497b.a(t0.a(th));
            }

            @Override // m6.t0.r1
            /* renamed from: b */
            public void success(Void r32) {
                this.f9496a.add(0, null);
                this.f9497b.a(this.f9496a);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class c implements r1 {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ ArrayList f9498a;

            /* renamed from: b  reason: collision with root package name */
            final /* synthetic */ a.e f9499b;

            c(ArrayList arrayList, a.e eVar) {
                this.f9498a = arrayList;
                this.f9499b = eVar;
            }

            @Override // m6.t0.r1
            public void a(Throwable th) {
                this.f9499b.a(t0.a(th));
            }

            @Override // m6.t0.r1
            /* renamed from: b */
            public void success(Long l8) {
                this.f9498a.add(0, l8);
                this.f9499b.a(this.f9498a);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class d implements r1 {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ ArrayList f9500a;

            /* renamed from: b  reason: collision with root package name */
            final /* synthetic */ a.e f9501b;

            d(ArrayList arrayList, a.e eVar) {
                this.f9500a = arrayList;
                this.f9501b = eVar;
            }

            @Override // m6.t0.r1
            public void a(Throwable th) {
                this.f9501b.a(t0.a(th));
            }

            @Override // m6.t0.r1
            /* renamed from: b */
            public void success(Void r32) {
                this.f9500a.add(0, null);
                this.f9501b.a(this.f9500a);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class e implements r1 {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ ArrayList f9502a;

            /* renamed from: b  reason: collision with root package name */
            final /* synthetic */ a.e f9503b;

            e(ArrayList arrayList, a.e eVar) {
                this.f9502a = arrayList;
                this.f9503b = eVar;
            }

            @Override // m6.t0.r1
            public void a(Throwable th) {
                this.f9503b.a(t0.a(th));
            }

            @Override // m6.t0.r1
            /* renamed from: b */
            public void success(Long l8) {
                this.f9502a.add(0, l8);
                this.f9503b.a(this.f9502a);
            }
        }

        static i6.h a() {
            return new i6.n();
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void b(h hVar, Object obj, a.e eVar) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            Number number2 = (Number) arrayList2.get(1);
            hVar.i(number == null ? null : Long.valueOf(number.longValue()), number2 != null ? Long.valueOf(number2.longValue()) : null, new c(arrayList, eVar));
        }

        static void c(i6.b bVar, final h hVar) {
            i6.a aVar = new i6.a(bVar, "dev.flutter.pigeon.CameraControlHostApi.enableTorch", a());
            if (hVar != null) {
                aVar.e(new a.d() { // from class: m6.e1
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.h.m(t0.h.this, obj, eVar);
                    }
                });
            } else {
                aVar.e(null);
            }
            i6.a aVar2 = new i6.a(bVar, "dev.flutter.pigeon.CameraControlHostApi.setZoomRatio", a());
            if (hVar != null) {
                aVar2.e(new a.d() { // from class: m6.f1
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.h.o(t0.h.this, obj, eVar);
                    }
                });
            } else {
                aVar2.e(null);
            }
            i6.a aVar3 = new i6.a(bVar, "dev.flutter.pigeon.CameraControlHostApi.startFocusAndMetering", a());
            if (hVar != null) {
                aVar3.e(new a.d() { // from class: m6.g1
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.h.b(t0.h.this, obj, eVar);
                    }
                });
            } else {
                aVar3.e(null);
            }
            i6.a aVar4 = new i6.a(bVar, "dev.flutter.pigeon.CameraControlHostApi.cancelFocusAndMetering", a());
            if (hVar != null) {
                aVar4.e(new a.d() { // from class: m6.h1
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.h.h(t0.h.this, obj, eVar);
                    }
                });
            } else {
                aVar4.e(null);
            }
            i6.a aVar5 = new i6.a(bVar, "dev.flutter.pigeon.CameraControlHostApi.setExposureCompensationIndex", a());
            if (hVar != null) {
                aVar5.e(new a.d() { // from class: m6.i1
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.h.k(t0.h.this, obj, eVar);
                    }
                });
            } else {
                aVar5.e(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void h(h hVar, Object obj, a.e eVar) {
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            hVar.g(number == null ? null : Long.valueOf(number.longValue()), new d(arrayList, eVar));
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void k(h hVar, Object obj, a.e eVar) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            Number number2 = (Number) arrayList2.get(1);
            hVar.p(number == null ? null : Long.valueOf(number.longValue()), number2 != null ? Long.valueOf(number2.longValue()) : null, new e(arrayList, eVar));
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void m(h hVar, Object obj, a.e eVar) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            hVar.q(number == null ? null : Long.valueOf(number.longValue()), (Boolean) arrayList2.get(1), new a(arrayList, eVar));
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void o(h hVar, Object obj, a.e eVar) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            hVar.e(number == null ? null : Long.valueOf(number.longValue()), (Double) arrayList2.get(1), new b(arrayList, eVar));
        }

        void e(Long l8, Double d8, r1 r1Var);

        void g(Long l8, r1 r1Var);

        void i(Long l8, Long l9, r1 r1Var);

        void p(Long l8, Long l9, r1 r1Var);

        void q(Long l8, Boolean bool, r1 r1Var);
    }

    /* loaded from: classes.dex */
    public interface h0 {
        static i6.h a() {
            return new i6.n();
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void f(h0 h0Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            Number number2 = (Number) arrayList2.get(1);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            h0Var.m(valueOf, number2 == null ? null : Long.valueOf(number2.longValue()));
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void g(h0 h0Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            Number number2 = (Number) arrayList2.get(1);
            Number number3 = (Number) arrayList2.get(2);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            h0Var.c(valueOf, number2 == null ? null : Long.valueOf(number2.longValue()), number3 == null ? null : Long.valueOf(number3.longValue()));
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        static void h(i6.b bVar, final h0 h0Var) {
            i6.a aVar = new i6.a(bVar, "dev.flutter.pigeon.ImageAnalysisHostApi.create", a());
            if (h0Var != null) {
                aVar.e(new a.d() { // from class: m6.g2
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.h0.g(t0.h0.this, obj, eVar);
                    }
                });
            } else {
                aVar.e(null);
            }
            i6.a aVar2 = new i6.a(bVar, "dev.flutter.pigeon.ImageAnalysisHostApi.setAnalyzer", a());
            if (h0Var != null) {
                aVar2.e(new a.d() { // from class: m6.h2
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.h0.f(t0.h0.this, obj, eVar);
                    }
                });
            } else {
                aVar2.e(null);
            }
            i6.a aVar3 = new i6.a(bVar, "dev.flutter.pigeon.ImageAnalysisHostApi.clearAnalyzer", a());
            if (h0Var != null) {
                aVar3.e(new a.d() { // from class: m6.i2
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.h0.n(t0.h0.this, obj, eVar);
                    }
                });
            } else {
                aVar3.e(null);
            }
            i6.a aVar4 = new i6.a(bVar, "dev.flutter.pigeon.ImageAnalysisHostApi.setTargetRotation", a());
            if (h0Var != null) {
                aVar4.e(new a.d() { // from class: m6.j2
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.h0.l(t0.h0.this, obj, eVar);
                    }
                });
            } else {
                aVar4.e(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void l(h0 h0Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            Number number2 = (Number) arrayList2.get(1);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            h0Var.b(valueOf, number2 == null ? null : Long.valueOf(number2.longValue()));
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void n(h0 h0Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            h0Var.e(valueOf);
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        void b(Long l8, Long l9);

        void c(Long l8, Long l9, Long l10);

        void e(Long l8);

        void m(Long l8, Long l9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class h1 extends i6.n {

        /* renamed from: d  reason: collision with root package name */
        public static final h1 f9504d = new h1();

        private h1() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // i6.n
        public Object g(byte b8, ByteBuffer byteBuffer) {
            return b8 != Byte.MIN_VALUE ? b8 != -127 ? super.g(b8, byteBuffer) : y1.a((ArrayList) f(byteBuffer)) : n1.a((ArrayList) f(byteBuffer));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // i6.n
        public void p(ByteArrayOutputStream byteArrayOutputStream, Object obj) {
            ArrayList d8;
            if (obj instanceof n1) {
                byteArrayOutputStream.write(128);
                d8 = ((n1) obj).f();
            } else if (!(obj instanceof y1)) {
                super.p(byteArrayOutputStream, obj);
                return;
            } else {
                byteArrayOutputStream.write(129);
                d8 = ((y1) obj).d();
            }
            p(byteArrayOutputStream, d8);
        }
    }

    /* loaded from: classes.dex */
    public static class i {

        /* renamed from: a  reason: collision with root package name */
        private final i6.b f9505a;

        /* loaded from: classes.dex */
        public interface a {
            void a(Object obj);
        }

        public i(i6.b bVar) {
            this.f9505a = bVar;
        }

        static i6.h c() {
            return new i6.n();
        }

        public void b(Long l8, final a aVar) {
            new i6.a(this.f9505a, "dev.flutter.pigeon.CameraFlutterApi.create", c()).d(new ArrayList(Collections.singletonList(l8)), new a.e() { // from class: m6.j1
                @Override // i6.a.e
                public final void a(Object obj) {
                    t0.i.a.this.a(null);
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public interface i0 {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class a implements r1 {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ ArrayList f9506a;

            /* renamed from: b  reason: collision with root package name */
            final /* synthetic */ a.e f9507b;

            a(ArrayList arrayList, a.e eVar) {
                this.f9506a = arrayList;
                this.f9507b = eVar;
            }

            @Override // m6.t0.r1
            public void a(Throwable th) {
                this.f9507b.a(t0.a(th));
            }

            @Override // m6.t0.r1
            /* renamed from: b */
            public void success(String str) {
                this.f9506a.add(0, str);
                this.f9507b.a(this.f9506a);
            }
        }

        static i6.h a() {
            return new i6.n();
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void d(i0 i0Var, Object obj, a.e eVar) {
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            i0Var.j(number == null ? null : Long.valueOf(number.longValue()), new a(arrayList, eVar));
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void g(i0 i0Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            Number number2 = (Number) arrayList2.get(1);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            i0Var.l(valueOf, number2 == null ? null : Long.valueOf(number2.longValue()));
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void i(i0 i0Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            Number number2 = (Number) arrayList2.get(1);
            Number number3 = (Number) arrayList2.get(2);
            Number number4 = (Number) arrayList2.get(3);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            i0Var.c(valueOf, number2 == null ? null : Long.valueOf(number2.longValue()), number3 == null ? null : Long.valueOf(number3.longValue()), number4 == null ? null : Long.valueOf(number4.longValue()));
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        static void k(i6.b bVar, final i0 i0Var) {
            i6.a aVar = new i6.a(bVar, "dev.flutter.pigeon.ImageCaptureHostApi.create", a());
            if (i0Var != null) {
                aVar.e(new a.d() { // from class: m6.k2
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.i0.i(t0.i0.this, obj, eVar);
                    }
                });
            } else {
                aVar.e(null);
            }
            i6.a aVar2 = new i6.a(bVar, "dev.flutter.pigeon.ImageCaptureHostApi.setFlashMode", a());
            if (i0Var != null) {
                aVar2.e(new a.d() { // from class: m6.l2
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.i0.g(t0.i0.this, obj, eVar);
                    }
                });
            } else {
                aVar2.e(null);
            }
            i6.a aVar3 = new i6.a(bVar, "dev.flutter.pigeon.ImageCaptureHostApi.takePicture", a());
            if (i0Var != null) {
                aVar3.e(new a.d() { // from class: m6.m2
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.i0.d(t0.i0.this, obj, eVar);
                    }
                });
            } else {
                aVar3.e(null);
            }
            i6.a aVar4 = new i6.a(bVar, "dev.flutter.pigeon.ImageCaptureHostApi.setTargetRotation", a());
            if (i0Var != null) {
                aVar4.e(new a.d() { // from class: m6.n2
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.i0.n(t0.i0.this, obj, eVar);
                    }
                });
            } else {
                aVar4.e(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void n(i0 i0Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            Number number2 = (Number) arrayList2.get(1);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            i0Var.b(valueOf, number2 == null ? null : Long.valueOf(number2.longValue()));
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        void b(Long l8, Long l9);

        void c(Long l8, Long l9, Long l10, Long l11);

        void j(Long l8, r1 r1Var);

        void l(Long l8, Long l9);
    }

    /* loaded from: classes.dex */
    public interface i1 {
        static i6.h a() {
            return new i6.n();
        }

        static void g(i6.b bVar, final i1 i1Var) {
            i6.a aVar = new i6.a(bVar, "dev.flutter.pigeon.RecorderHostApi.create", a());
            if (i1Var != null) {
                aVar.e(new a.d() { // from class: m6.u3
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.i1.n(t0.i1.this, obj, eVar);
                    }
                });
            } else {
                aVar.e(null);
            }
            i6.a aVar2 = new i6.a(bVar, "dev.flutter.pigeon.RecorderHostApi.getAspectRatio", a());
            if (i1Var != null) {
                aVar2.e(new a.d() { // from class: m6.v3
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.i1.l(t0.i1.this, obj, eVar);
                    }
                });
            } else {
                aVar2.e(null);
            }
            i6.a aVar3 = new i6.a(bVar, "dev.flutter.pigeon.RecorderHostApi.getTargetVideoEncodingBitRate", a());
            if (i1Var != null) {
                aVar3.e(new a.d() { // from class: m6.w3
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.i1.j(t0.i1.this, obj, eVar);
                    }
                });
            } else {
                aVar3.e(null);
            }
            i6.a aVar4 = new i6.a(bVar, "dev.flutter.pigeon.RecorderHostApi.prepareRecording", a());
            if (i1Var != null) {
                aVar4.e(new a.d() { // from class: m6.x3
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.i1.h(t0.i1.this, obj, eVar);
                    }
                });
            } else {
                aVar4.e(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void h(i1 i1Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            String str = (String) arrayList2.get(1);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            arrayList.add(0, i1Var.f(valueOf, str));
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void j(i1 i1Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            arrayList.add(0, i1Var.d(valueOf));
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void l(i1 i1Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            arrayList.add(0, i1Var.m(valueOf));
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void n(i1 i1Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            Number number2 = (Number) arrayList2.get(1);
            Number number3 = (Number) arrayList2.get(2);
            Number number4 = (Number) arrayList2.get(3);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            i1Var.c(valueOf, number2 == null ? null : Long.valueOf(number2.longValue()), number3 == null ? null : Long.valueOf(number3.longValue()), number4 == null ? null : Long.valueOf(number4.longValue()));
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        void c(Long l8, Long l9, Long l10, Long l11);

        Long d(Long l8);

        Long f(Long l8, String str);

        Long m(Long l8);
    }

    /* loaded from: classes.dex */
    public interface j {
        static i6.h a() {
            return new i6.n();
        }

        static void e(i6.b bVar, final j jVar) {
            i6.a aVar = new i6.a(bVar, "dev.flutter.pigeon.CameraHostApi.getCameraInfo", a());
            if (jVar != null) {
                aVar.e(new a.d() { // from class: m6.k1
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.j.f(t0.j.this, obj, eVar);
                    }
                });
            } else {
                aVar.e(null);
            }
            i6.a aVar2 = new i6.a(bVar, "dev.flutter.pigeon.CameraHostApi.getCameraControl", a());
            if (jVar != null) {
                aVar2.e(new a.d() { // from class: m6.l1
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.j.h(t0.j.this, obj, eVar);
                    }
                });
            } else {
                aVar2.e(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void f(j jVar, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            arrayList.add(0, jVar.d(valueOf));
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void h(j jVar, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            arrayList.add(0, jVar.c(valueOf));
            eVar.a(arrayList);
        }

        Long c(Long l8);

        Long d(Long l8);
    }

    /* loaded from: classes.dex */
    public static class j0 {

        /* renamed from: a  reason: collision with root package name */
        private final i6.b f9508a;

        /* loaded from: classes.dex */
        public interface a {
            void a(Object obj);
        }

        public j0(i6.b bVar) {
            this.f9508a = bVar;
        }

        static i6.h c() {
            return new i6.n();
        }

        public void b(Long l8, Long l9, Long l10, Long l11, final a aVar) {
            new i6.a(this.f9508a, "dev.flutter.pigeon.ImageProxyFlutterApi.create", c()).d(new ArrayList(Arrays.asList(l8, l9, l10, l11)), new a.e() { // from class: m6.o2
                @Override // i6.a.e
                public final void a(Object obj) {
                    t0.j0.a.this.a(null);
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public static class j1 {

        /* renamed from: a  reason: collision with root package name */
        private final i6.b f9509a;

        /* loaded from: classes.dex */
        public interface a {
            void a(Object obj);
        }

        public j1(i6.b bVar) {
            this.f9509a = bVar;
        }

        static i6.h c() {
            return new i6.n();
        }

        public void b(Long l8, final a aVar) {
            new i6.a(this.f9509a, "dev.flutter.pigeon.RecordingFlutterApi.create", c()).d(new ArrayList(Collections.singletonList(l8)), new a.e() { // from class: m6.y3
                @Override // i6.a.e
                public final void a(Object obj) {
                    t0.j1.a.this.a(null);
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public static class k {

        /* renamed from: a  reason: collision with root package name */
        private final i6.b f9510a;

        /* loaded from: classes.dex */
        public interface a {
            void a(Object obj);
        }

        public k(i6.b bVar) {
            this.f9510a = bVar;
        }

        static i6.h c() {
            return new i6.n();
        }

        public void b(Long l8, final a aVar) {
            new i6.a(this.f9510a, "dev.flutter.pigeon.CameraInfoFlutterApi.create", c()).d(new ArrayList(Collections.singletonList(l8)), new a.e() { // from class: m6.m1
                @Override // i6.a.e
                public final void a(Object obj) {
                    t0.k.a.this.a(null);
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public interface k0 {
        static i6.h a() {
            return new i6.n();
        }

        static void c(i6.b bVar, final k0 k0Var) {
            i6.a aVar = new i6.a(bVar, "dev.flutter.pigeon.ImageProxyHostApi.getPlanes", a());
            if (k0Var != null) {
                aVar.e(new a.d() { // from class: m6.p2
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.k0.d(t0.k0.this, obj, eVar);
                    }
                });
            } else {
                aVar.e(null);
            }
            i6.a aVar2 = new i6.a(bVar, "dev.flutter.pigeon.ImageProxyHostApi.close", a());
            if (k0Var != null) {
                aVar2.e(new a.d() { // from class: m6.q2
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.k0.h(t0.k0.this, obj, eVar);
                    }
                });
            } else {
                aVar2.e(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void d(k0 k0Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            arrayList.add(0, k0Var.e(valueOf));
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void h(k0 k0Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            k0Var.b(valueOf);
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        void b(Long l8);

        List e(Long l8);
    }

    /* loaded from: classes.dex */
    public interface k1 {
        static i6.h a() {
            return new i6.n();
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void f(k1 k1Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            k1Var.g(valueOf);
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void h(k1 k1Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            k1Var.j(valueOf);
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void i(k1 k1Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            k1Var.c(valueOf);
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        static void l(i6.b bVar, final k1 k1Var) {
            i6.a aVar = new i6.a(bVar, "dev.flutter.pigeon.RecordingHostApi.close", a());
            if (k1Var != null) {
                aVar.e(new a.d() { // from class: m6.z3
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.k1.n(t0.k1.this, obj, eVar);
                    }
                });
            } else {
                aVar.e(null);
            }
            i6.a aVar2 = new i6.a(bVar, "dev.flutter.pigeon.RecordingHostApi.pause", a());
            if (k1Var != null) {
                aVar2.e(new a.d() { // from class: m6.a4
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.k1.f(t0.k1.this, obj, eVar);
                    }
                });
            } else {
                aVar2.e(null);
            }
            i6.a aVar3 = new i6.a(bVar, "dev.flutter.pigeon.RecordingHostApi.resume", a());
            if (k1Var != null) {
                aVar3.e(new a.d() { // from class: m6.b4
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.k1.h(t0.k1.this, obj, eVar);
                    }
                });
            } else {
                aVar3.e(null);
            }
            i6.a aVar4 = new i6.a(bVar, "dev.flutter.pigeon.RecordingHostApi.stop", a());
            if (k1Var != null) {
                aVar4.e(new a.d() { // from class: m6.c4
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.k1.i(t0.k1.this, obj, eVar);
                    }
                });
            } else {
                aVar4.e(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void n(k1 k1Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            k1Var.b(valueOf);
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        void b(Long l8);

        void c(Long l8);

        void g(Long l8);

        void j(Long l8);
    }

    /* loaded from: classes.dex */
    public interface l {
        static i6.h a() {
            return new i6.n();
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void b(l lVar, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            arrayList.add(0, lVar.l(valueOf));
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void d(l lVar, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            arrayList.add(0, lVar.c(valueOf));
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void f(l lVar, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            arrayList.add(0, lVar.i(valueOf));
            eVar.a(arrayList);
        }

        static void h(i6.b bVar, final l lVar) {
            i6.a aVar = new i6.a(bVar, "dev.flutter.pigeon.CameraInfoHostApi.getSensorRotationDegrees", a());
            if (lVar != null) {
                aVar.e(new a.d() { // from class: m6.n1
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.l.b(t0.l.this, obj, eVar);
                    }
                });
            } else {
                aVar.e(null);
            }
            i6.a aVar2 = new i6.a(bVar, "dev.flutter.pigeon.CameraInfoHostApi.getCameraState", a());
            if (lVar != null) {
                aVar2.e(new a.d() { // from class: m6.o1
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.l.d(t0.l.this, obj, eVar);
                    }
                });
            } else {
                aVar2.e(null);
            }
            i6.a aVar3 = new i6.a(bVar, "dev.flutter.pigeon.CameraInfoHostApi.getExposureState", a());
            if (lVar != null) {
                aVar3.e(new a.d() { // from class: m6.p1
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.l.f(t0.l.this, obj, eVar);
                    }
                });
            } else {
                aVar3.e(null);
            }
            i6.a aVar4 = new i6.a(bVar, "dev.flutter.pigeon.CameraInfoHostApi.getZoomState", a());
            if (lVar != null) {
                aVar4.e(new a.d() { // from class: m6.q1
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.l.k(t0.l.this, obj, eVar);
                    }
                });
            } else {
                aVar4.e(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void k(l lVar, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            arrayList.add(0, lVar.e(valueOf));
            eVar.a(arrayList);
        }

        Long c(Long l8);

        Long e(Long l8);

        Long i(Long l8);

        Long l(Long l8);
    }

    /* loaded from: classes.dex */
    public interface l0 {
        static i6.h a() {
            return new i6.n();
        }

        static void b(i6.b bVar, final l0 l0Var) {
            new i6.a(bVar, "dev.flutter.pigeon.InstanceManagerHostApi.clear", a()).e(l0Var != null ? new a.d() { // from class: m6.r2
                @Override // i6.a.d
                public final void a(Object obj, a.e eVar) {
                    t0.l0.c(t0.l0.this, obj, eVar);
                }
            } : null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void c(l0 l0Var, Object obj, a.e eVar) {
            ArrayList arrayList = new ArrayList();
            try {
                l0Var.clear();
                arrayList.add(0, null);
            } catch (Throwable th) {
                arrayList = t0.a(th);
            }
            eVar.a(arrayList);
        }

        void clear();
    }

    /* loaded from: classes.dex */
    public interface l1 {
        static i6.h a() {
            return m1.f9516d;
        }

        static void d(i6.b bVar, final l1 l1Var) {
            new i6.a(bVar, "dev.flutter.pigeon.ResolutionFilterHostApi.createWithOnePreferredSize", a()).e(l1Var != null ? new a.d() { // from class: m6.d4
                @Override // i6.a.d
                public final void a(Object obj, a.e eVar) {
                    t0.l1.e(t0.l1.this, obj, eVar);
                }
            } : null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void e(l1 l1Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            n1 n1Var = (n1) arrayList2.get(1);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            l1Var.b(valueOf, n1Var);
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        void b(Long l8, n1 n1Var);
    }

    /* loaded from: classes.dex */
    public static final class m {

        /* renamed from: a  reason: collision with root package name */
        private String f9511a;

        /* renamed from: b  reason: collision with root package name */
        private String f9512b;

        /* loaded from: classes.dex */
        public static final class a {

            /* renamed from: a  reason: collision with root package name */
            private String f9513a;

            /* renamed from: b  reason: collision with root package name */
            private String f9514b;

            public m a() {
                m mVar = new m();
                mVar.c(this.f9513a);
                mVar.b(this.f9514b);
                return mVar;
            }

            public a b(String str) {
                this.f9514b = str;
                return this;
            }

            public a c(String str) {
                this.f9513a = str;
                return this;
            }
        }

        m() {
        }

        static m a(ArrayList arrayList) {
            m mVar = new m();
            mVar.c((String) arrayList.get(0));
            mVar.b((String) arrayList.get(1));
            return mVar;
        }

        public void b(String str) {
            if (str == null) {
                throw new IllegalStateException("Nonnull field \"description\" is null.");
            }
            this.f9512b = str;
        }

        public void c(String str) {
            if (str == null) {
                throw new IllegalStateException("Nonnull field \"errorCode\" is null.");
            }
            this.f9511a = str;
        }

        ArrayList d() {
            ArrayList arrayList = new ArrayList(2);
            arrayList.add(this.f9511a);
            arrayList.add(this.f9512b);
            return arrayList;
        }
    }

    /* loaded from: classes.dex */
    public static class m0 {

        /* renamed from: a  reason: collision with root package name */
        private final i6.b f9515a;

        /* loaded from: classes.dex */
        public interface a {
            void a(Object obj);
        }

        public m0(i6.b bVar) {
            this.f9515a = bVar;
        }

        static i6.h c() {
            return new i6.n();
        }

        public void b(Long l8, final a aVar) {
            new i6.a(this.f9515a, "dev.flutter.pigeon.JavaObjectFlutterApi.dispose", c()).d(new ArrayList(Collections.singletonList(l8)), new a.e() { // from class: m6.s2
                @Override // i6.a.e
                public final void a(Object obj) {
                    t0.m0.a.this.a(null);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class m1 extends i6.n {

        /* renamed from: d  reason: collision with root package name */
        public static final m1 f9516d = new m1();

        private m1() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // i6.n
        public Object g(byte b8, ByteBuffer byteBuffer) {
            return b8 != Byte.MIN_VALUE ? super.g(b8, byteBuffer) : n1.a((ArrayList) f(byteBuffer));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // i6.n
        public void p(ByteArrayOutputStream byteArrayOutputStream, Object obj) {
            if (!(obj instanceof n1)) {
                super.p(byteArrayOutputStream, obj);
                return;
            }
            byteArrayOutputStream.write(128);
            p(byteArrayOutputStream, ((n1) obj).f());
        }
    }

    /* loaded from: classes.dex */
    public interface n {
        static i6.h a() {
            return new i6.n();
        }

        static void d(i6.b bVar, final n nVar) {
            i6.a aVar = new i6.a(bVar, "dev.flutter.pigeon.CameraSelectorHostApi.create", a());
            if (nVar != null) {
                aVar.e(new a.d() { // from class: m6.r1
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.n.e(t0.n.this, obj, eVar);
                    }
                });
            } else {
                aVar.e(null);
            }
            i6.a aVar2 = new i6.a(bVar, "dev.flutter.pigeon.CameraSelectorHostApi.filter", a());
            if (nVar != null) {
                aVar2.e(new a.d() { // from class: m6.s1
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.n.h(t0.n.this, obj, eVar);
                    }
                });
            } else {
                aVar2.e(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void e(n nVar, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            Number number2 = (Number) arrayList2.get(1);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            nVar.b(valueOf, number2 == null ? null : Long.valueOf(number2.longValue()));
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void h(n nVar, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            List list = (List) arrayList2.get(1);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            arrayList.add(0, nVar.g(valueOf, list));
            eVar.a(arrayList);
        }

        void b(Long l8, Long l9);

        List g(Long l8, List list);
    }

    /* loaded from: classes.dex */
    public interface n0 {
        static i6.h a() {
            return new i6.n();
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void c(n0 n0Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            n0Var.b(valueOf);
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        static void d(i6.b bVar, final n0 n0Var) {
            new i6.a(bVar, "dev.flutter.pigeon.JavaObjectHostApi.dispose", a()).e(n0Var != null ? new a.d() { // from class: m6.t2
                @Override // i6.a.d
                public final void a(Object obj, a.e eVar) {
                    t0.n0.c(t0.n0.this, obj, eVar);
                }
            } : null);
        }

        void b(Long l8);
    }

    /* loaded from: classes.dex */
    public static final class n1 {

        /* renamed from: a  reason: collision with root package name */
        private Long f9517a;

        /* renamed from: b  reason: collision with root package name */
        private Long f9518b;

        /* loaded from: classes.dex */
        public static final class a {

            /* renamed from: a  reason: collision with root package name */
            private Long f9519a;

            /* renamed from: b  reason: collision with root package name */
            private Long f9520b;

            public n1 a() {
                n1 n1Var = new n1();
                n1Var.e(this.f9519a);
                n1Var.d(this.f9520b);
                return n1Var;
            }

            public a b(Long l8) {
                this.f9520b = l8;
                return this;
            }

            public a c(Long l8) {
                this.f9519a = l8;
                return this;
            }
        }

        n1() {
        }

        static n1 a(ArrayList arrayList) {
            Long valueOf;
            n1 n1Var = new n1();
            Object obj = arrayList.get(0);
            Long l8 = null;
            if (obj == null) {
                valueOf = null;
            } else {
                valueOf = Long.valueOf(obj instanceof Integer ? ((Integer) obj).intValue() : ((Long) obj).longValue());
            }
            n1Var.e(valueOf);
            Object obj2 = arrayList.get(1);
            if (obj2 != null) {
                l8 = Long.valueOf(obj2 instanceof Integer ? ((Integer) obj2).intValue() : ((Long) obj2).longValue());
            }
            n1Var.d(l8);
            return n1Var;
        }

        public Long b() {
            return this.f9518b;
        }

        public Long c() {
            return this.f9517a;
        }

        public void d(Long l8) {
            if (l8 == null) {
                throw new IllegalStateException("Nonnull field \"height\" is null.");
            }
            this.f9518b = l8;
        }

        public void e(Long l8) {
            if (l8 == null) {
                throw new IllegalStateException("Nonnull field \"width\" is null.");
            }
            this.f9517a = l8;
        }

        ArrayList f() {
            ArrayList arrayList = new ArrayList(2);
            arrayList.add(this.f9517a);
            arrayList.add(this.f9518b);
            return arrayList;
        }
    }

    /* loaded from: classes.dex */
    public static class o {

        /* renamed from: a  reason: collision with root package name */
        private final i6.b f9521a;

        /* loaded from: classes.dex */
        public interface a {
            void a(Object obj);
        }

        public o(i6.b bVar) {
            this.f9521a = bVar;
        }

        static i6.h c() {
            return new i6.n();
        }

        public void b(Long l8, Long l9, final a aVar) {
            new i6.a(this.f9521a, "dev.flutter.pigeon.CameraStateErrorFlutterApi.create", c()).d(new ArrayList(Arrays.asList(l8, l9)), new a.e() { // from class: m6.t1
                @Override // i6.a.e
                public final void a(Object obj) {
                    t0.o.a.this.a(null);
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public static class o0 {

        /* renamed from: a  reason: collision with root package name */
        private final i6.b f9522a;

        /* loaded from: classes.dex */
        public interface a {
            void a(Object obj);
        }

        public o0(i6.b bVar) {
            this.f9522a = bVar;
        }

        static i6.h c() {
            return p0.f9524d;
        }

        public void b(Long l8, C0128t0 c0128t0, final a aVar) {
            new i6.a(this.f9522a, "dev.flutter.pigeon.LiveDataFlutterApi.create", c()).d(new ArrayList(Arrays.asList(l8, c0128t0)), new a.e() { // from class: m6.u2
                @Override // i6.a.e
                public final void a(Object obj) {
                    t0.o0.a.this.a(null);
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public interface o1 {
        static i6.h a() {
            return new i6.n();
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void b(o1 o1Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            Number number2 = (Number) arrayList2.get(1);
            Number number3 = (Number) arrayList2.get(2);
            Number number4 = (Number) arrayList2.get(3);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            o1Var.c(valueOf, number2 == null ? null : Long.valueOf(number2.longValue()), number3 == null ? null : Long.valueOf(number3.longValue()), number4 == null ? null : Long.valueOf(number4.longValue()));
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        static void e(i6.b bVar, final o1 o1Var) {
            new i6.a(bVar, "dev.flutter.pigeon.ResolutionSelectorHostApi.create", a()).e(o1Var != null ? new a.d() { // from class: m6.e4
                @Override // i6.a.d
                public final void a(Object obj, a.e eVar) {
                    t0.o1.b(t0.o1.this, obj, eVar);
                }
            } : null);
        }

        void c(Long l8, Long l9, Long l10, Long l11);
    }

    /* loaded from: classes.dex */
    public static class p {

        /* renamed from: a  reason: collision with root package name */
        private final i6.b f9523a;

        /* loaded from: classes.dex */
        public interface a {
            void a(Object obj);
        }

        public p(i6.b bVar) {
            this.f9523a = bVar;
        }

        static i6.h c() {
            return q.f9525d;
        }

        public void b(Long l8, s sVar, Long l9, final a aVar) {
            new i6.a(this.f9523a, "dev.flutter.pigeon.CameraStateFlutterApi.create", c()).d(new ArrayList(Arrays.asList(l8, sVar, l9)), new a.e() { // from class: m6.u1
                @Override // i6.a.e
                public final void a(Object obj) {
                    t0.p.a.this.a(null);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class p0 extends i6.n {

        /* renamed from: d  reason: collision with root package name */
        public static final p0 f9524d = new p0();

        private p0() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // i6.n
        public Object g(byte b8, ByteBuffer byteBuffer) {
            return b8 != Byte.MIN_VALUE ? super.g(b8, byteBuffer) : C0128t0.a((ArrayList) f(byteBuffer));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // i6.n
        public void p(ByteArrayOutputStream byteArrayOutputStream, Object obj) {
            if (!(obj instanceof C0128t0)) {
                super.p(byteArrayOutputStream, obj);
                return;
            }
            byteArrayOutputStream.write(128);
            p(byteArrayOutputStream, ((C0128t0) obj).d());
        }
    }

    /* loaded from: classes.dex */
    public interface p1 {
        static i6.h a() {
            return q1.f9526d;
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void c(p1 p1Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            n1 n1Var = (n1) arrayList2.get(1);
            Number number2 = (Number) arrayList2.get(2);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            p1Var.b(valueOf, n1Var, number2 == null ? null : Long.valueOf(number2.longValue()));
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        static void d(i6.b bVar, final p1 p1Var) {
            new i6.a(bVar, "dev.flutter.pigeon.ResolutionStrategyHostApi.create", a()).e(p1Var != null ? new a.d() { // from class: m6.f4
                @Override // i6.a.d
                public final void a(Object obj, a.e eVar) {
                    t0.p1.c(t0.p1.this, obj, eVar);
                }
            } : null);
        }

        void b(Long l8, n1 n1Var, Long l9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class q extends i6.n {

        /* renamed from: d  reason: collision with root package name */
        public static final q f9525d = new q();

        private q() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // i6.n
        public Object g(byte b8, ByteBuffer byteBuffer) {
            return b8 != Byte.MIN_VALUE ? super.g(b8, byteBuffer) : s.a((ArrayList) f(byteBuffer));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // i6.n
        public void p(ByteArrayOutputStream byteArrayOutputStream, Object obj) {
            if (!(obj instanceof s)) {
                super.p(byteArrayOutputStream, obj);
                return;
            }
            byteArrayOutputStream.write(128);
            p(byteArrayOutputStream, ((s) obj).c());
        }
    }

    /* loaded from: classes.dex */
    public interface q0 {
        static i6.h a() {
            return r0.f9534d;
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void d(q0 q0Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            Number number2 = (Number) arrayList2.get(1);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            q0Var.f(valueOf, number2 == null ? null : Long.valueOf(number2.longValue()));
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void h(q0 q0Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            q0Var.g(valueOf);
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        static void i(i6.b bVar, final q0 q0Var) {
            i6.a aVar = new i6.a(bVar, "dev.flutter.pigeon.LiveDataHostApi.observe", a());
            if (q0Var != null) {
                aVar.e(new a.d() { // from class: m6.v2
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.q0.d(t0.q0.this, obj, eVar);
                    }
                });
            } else {
                aVar.e(null);
            }
            i6.a aVar2 = new i6.a(bVar, "dev.flutter.pigeon.LiveDataHostApi.removeObservers", a());
            if (q0Var != null) {
                aVar2.e(new a.d() { // from class: m6.w2
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.q0.h(t0.q0.this, obj, eVar);
                    }
                });
            } else {
                aVar2.e(null);
            }
            i6.a aVar3 = new i6.a(bVar, "dev.flutter.pigeon.LiveDataHostApi.getValue", a());
            if (q0Var != null) {
                aVar3.e(new a.d() { // from class: m6.x2
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.q0.j(t0.q0.this, obj, eVar);
                    }
                });
            } else {
                aVar3.e(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void j(q0 q0Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            C0128t0 c0128t0 = (C0128t0) arrayList2.get(1);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            arrayList.add(0, q0Var.b(valueOf, c0128t0));
            eVar.a(arrayList);
        }

        Long b(Long l8, C0128t0 c0128t0);

        void f(Long l8, Long l9);

        void g(Long l8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class q1 extends i6.n {

        /* renamed from: d  reason: collision with root package name */
        public static final q1 f9526d = new q1();

        private q1() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // i6.n
        public Object g(byte b8, ByteBuffer byteBuffer) {
            return b8 != Byte.MIN_VALUE ? super.g(b8, byteBuffer) : n1.a((ArrayList) f(byteBuffer));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // i6.n
        public void p(ByteArrayOutputStream byteArrayOutputStream, Object obj) {
            if (!(obj instanceof n1)) {
                super.p(byteArrayOutputStream, obj);
                return;
            }
            byteArrayOutputStream.write(128);
            p(byteArrayOutputStream, ((n1) obj).f());
        }
    }

    /* loaded from: classes.dex */
    public enum r {
        CLOSED(0),
        CLOSING(1),
        OPEN(2),
        OPENING(3),
        PENDING_OPEN(4);
        

        /* renamed from: e  reason: collision with root package name */
        final int f9533e;

        r(int i8) {
            this.f9533e = i8;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class r0 extends i6.n {

        /* renamed from: d  reason: collision with root package name */
        public static final r0 f9534d = new r0();

        private r0() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // i6.n
        public Object g(byte b8, ByteBuffer byteBuffer) {
            return b8 != Byte.MIN_VALUE ? super.g(b8, byteBuffer) : C0128t0.a((ArrayList) f(byteBuffer));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // i6.n
        public void p(ByteArrayOutputStream byteArrayOutputStream, Object obj) {
            if (!(obj instanceof C0128t0)) {
                super.p(byteArrayOutputStream, obj);
                return;
            }
            byteArrayOutputStream.write(128);
            p(byteArrayOutputStream, ((C0128t0) obj).d());
        }
    }

    /* loaded from: classes.dex */
    public interface r1 {
        void a(Throwable th);

        void success(Object obj);
    }

    /* loaded from: classes.dex */
    public static final class s {

        /* renamed from: a  reason: collision with root package name */
        private r f9535a;

        /* loaded from: classes.dex */
        public static final class a {

            /* renamed from: a  reason: collision with root package name */
            private r f9536a;

            public s a() {
                s sVar = new s();
                sVar.b(this.f9536a);
                return sVar;
            }

            public a b(r rVar) {
                this.f9536a = rVar;
                return this;
            }
        }

        s() {
        }

        static s a(ArrayList arrayList) {
            s sVar = new s();
            Object obj = arrayList.get(0);
            sVar.b(obj == null ? null : r.values()[((Integer) obj).intValue()]);
            return sVar;
        }

        public void b(r rVar) {
            if (rVar == null) {
                throw new IllegalStateException("Nonnull field \"value\" is null.");
            }
            this.f9535a = rVar;
        }

        ArrayList c() {
            ArrayList arrayList = new ArrayList(1);
            r rVar = this.f9535a;
            arrayList.add(rVar == null ? null : Integer.valueOf(rVar.f9533e));
            return arrayList;
        }
    }

    /* loaded from: classes.dex */
    public enum s0 {
        CAMERA_STATE(0),
        ZOOM_STATE(1);
        

        /* renamed from: e  reason: collision with root package name */
        final int f9540e;

        s0(int i8) {
            this.f9540e = i8;
        }
    }

    /* loaded from: classes.dex */
    public static class s1 {

        /* renamed from: a  reason: collision with root package name */
        private final i6.b f9541a;

        /* loaded from: classes.dex */
        public interface a {
            void a(Object obj);
        }

        public s1(i6.b bVar) {
            this.f9541a = bVar;
        }

        static i6.h b() {
            return new i6.n();
        }

        public void d(String str, final a aVar) {
            new i6.a(this.f9541a, "dev.flutter.pigeon.SystemServicesFlutterApi.onCameraError", b()).d(new ArrayList(Collections.singletonList(str)), new a.e() { // from class: m6.g4
                @Override // i6.a.e
                public final void a(Object obj) {
                    t0.s1.a.this.a(null);
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public enum t {
        CONTROL_AE_LOCK(0);
        

        /* renamed from: e  reason: collision with root package name */
        final int f9544e;

        t(int i8) {
            this.f9544e = i8;
        }
    }

    /* renamed from: m6.t0$t0  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0128t0 {

        /* renamed from: a  reason: collision with root package name */
        private s0 f9545a;

        /* renamed from: m6.t0$t0$a */
        /* loaded from: classes.dex */
        public static final class a {

            /* renamed from: a  reason: collision with root package name */
            private s0 f9546a;

            public C0128t0 a() {
                C0128t0 c0128t0 = new C0128t0();
                c0128t0.c(this.f9546a);
                return c0128t0;
            }

            public a b(s0 s0Var) {
                this.f9546a = s0Var;
                return this;
            }
        }

        C0128t0() {
        }

        static C0128t0 a(ArrayList arrayList) {
            C0128t0 c0128t0 = new C0128t0();
            Object obj = arrayList.get(0);
            c0128t0.c(obj == null ? null : s0.values()[((Integer) obj).intValue()]);
            return c0128t0;
        }

        public s0 b() {
            return this.f9545a;
        }

        public void c(s0 s0Var) {
            if (s0Var == null) {
                throw new IllegalStateException("Nonnull field \"value\" is null.");
            }
            this.f9545a = s0Var;
        }

        ArrayList d() {
            ArrayList arrayList = new ArrayList(1);
            s0 s0Var = this.f9545a;
            arrayList.add(s0Var == null ? null : Integer.valueOf(s0Var.f9540e));
            return arrayList;
        }
    }

    /* loaded from: classes.dex */
    public interface t1 {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class a implements r1 {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ ArrayList f9547a;

            /* renamed from: b  reason: collision with root package name */
            final /* synthetic */ a.e f9548b;

            a(ArrayList arrayList, a.e eVar) {
                this.f9547a = arrayList;
                this.f9548b = eVar;
            }

            @Override // m6.t0.r1
            public void a(Throwable th) {
                this.f9548b.a(t0.a(th));
            }

            @Override // m6.t0.r1
            /* renamed from: b */
            public void success(m mVar) {
                this.f9547a.add(0, mVar);
                this.f9548b.a(this.f9547a);
            }
        }

        static i6.h a() {
            return u1.f9549d;
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void e(t1 t1Var, Object obj, a.e eVar) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            try {
                arrayList.add(0, t1Var.f((String) arrayList2.get(0), (String) arrayList2.get(1)));
            } catch (Throwable th) {
                arrayList = t0.a(th);
            }
            eVar.a(arrayList);
        }

        static void g(i6.b bVar, final t1 t1Var) {
            i6.a aVar = new i6.a(bVar, "dev.flutter.pigeon.SystemServicesHostApi.requestCameraPermissions", a());
            if (t1Var != null) {
                aVar.e(new a.d() { // from class: m6.h4
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.t1.h(t0.t1.this, obj, eVar);
                    }
                });
            } else {
                aVar.e(null);
            }
            i6.a aVar2 = new i6.a(bVar, "dev.flutter.pigeon.SystemServicesHostApi.getTempFilePath", a());
            if (t1Var != null) {
                aVar2.e(new a.d() { // from class: m6.i4
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.t1.e(t0.t1.this, obj, eVar);
                    }
                });
            } else {
                aVar2.e(null);
            }
            i6.a aVar3 = new i6.a(bVar, "dev.flutter.pigeon.SystemServicesHostApi.isPreviewPreTransformed", a());
            if (t1Var != null) {
                aVar3.e(new a.d() { // from class: m6.j4
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.t1.k(t0.t1.this, obj, eVar);
                    }
                });
            } else {
                aVar3.e(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void h(t1 t1Var, Object obj, a.e eVar) {
            t1Var.c((Boolean) ((ArrayList) obj).get(0), new a(new ArrayList(), eVar));
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void k(t1 t1Var, Object obj, a.e eVar) {
            ArrayList arrayList = new ArrayList();
            try {
                arrayList.add(0, t1Var.j());
            } catch (Throwable th) {
                arrayList = t0.a(th);
            }
            eVar.a(arrayList);
        }

        void c(Boolean bool, r1 r1Var);

        String f(String str, String str2);

        Boolean j();
    }

    /* loaded from: classes.dex */
    public interface u {
        static i6.h a() {
            return v.f9550d;
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void b(u uVar, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            Map map = (Map) arrayList2.get(1);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            uVar.e(valueOf, map);
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        static void d(i6.b bVar, final u uVar) {
            new i6.a(bVar, "dev.flutter.pigeon.CaptureRequestOptionsHostApi.create", a()).e(uVar != null ? new a.d() { // from class: m6.v1
                @Override // i6.a.d
                public final void a(Object obj, a.e eVar) {
                    t0.u.b(t0.u.this, obj, eVar);
                }
            } : null);
        }

        void e(Long l8, Map map);
    }

    /* loaded from: classes.dex */
    public interface u0 {
        static i6.h a() {
            return new i6.n();
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void d(u0 u0Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            Double d8 = (Double) arrayList2.get(1);
            Double d9 = (Double) arrayList2.get(2);
            Double d10 = (Double) arrayList2.get(3);
            Number number2 = (Number) arrayList2.get(4);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            u0Var.c(valueOf, d8, d9, d10, number2 == null ? null : Long.valueOf(number2.longValue()));
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        static void f(i6.b bVar, final u0 u0Var) {
            i6.a aVar = new i6.a(bVar, "dev.flutter.pigeon.MeteringPointHostApi.create", a());
            if (u0Var != null) {
                aVar.e(new a.d() { // from class: m6.y2
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.u0.d(t0.u0.this, obj, eVar);
                    }
                });
            } else {
                aVar.e(null);
            }
            i6.a aVar2 = new i6.a(bVar, "dev.flutter.pigeon.MeteringPointHostApi.getDefaultPointSize", a());
            if (u0Var != null) {
                aVar2.e(new a.d() { // from class: m6.z2
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.u0.g(t0.u0.this, obj, eVar);
                    }
                });
            } else {
                aVar2.e(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void g(u0 u0Var, Object obj, a.e eVar) {
            ArrayList arrayList = new ArrayList();
            try {
                arrayList.add(0, u0Var.h());
            } catch (Throwable th) {
                arrayList = t0.a(th);
            }
            eVar.a(arrayList);
        }

        void c(Long l8, Double d8, Double d9, Double d10, Long l9);

        Double h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class u1 extends i6.n {

        /* renamed from: d  reason: collision with root package name */
        public static final u1 f9549d = new u1();

        private u1() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // i6.n
        public Object g(byte b8, ByteBuffer byteBuffer) {
            return b8 != Byte.MIN_VALUE ? super.g(b8, byteBuffer) : m.a((ArrayList) f(byteBuffer));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // i6.n
        public void p(ByteArrayOutputStream byteArrayOutputStream, Object obj) {
            if (!(obj instanceof m)) {
                super.p(byteArrayOutputStream, obj);
                return;
            }
            byteArrayOutputStream.write(128);
            p(byteArrayOutputStream, ((m) obj).d());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class v extends i6.n {

        /* renamed from: d  reason: collision with root package name */
        public static final v f9550d = new v();

        private v() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // i6.n
        public Object g(byte b8, ByteBuffer byteBuffer) {
            switch (b8) {
                case Byte.MIN_VALUE:
                    return m.a((ArrayList) f(byteBuffer));
                case -127:
                    return s.a((ArrayList) f(byteBuffer));
                case -126:
                    return y.a((ArrayList) f(byteBuffer));
                case -125:
                    return C0128t0.a((ArrayList) f(byteBuffer));
                case -124:
                    return v0.a((ArrayList) f(byteBuffer));
                case -123:
                    return n1.a((ArrayList) f(byteBuffer));
                case -122:
                    return y1.a((ArrayList) f(byteBuffer));
                case -121:
                    return a2.a((ArrayList) f(byteBuffer));
                default:
                    return super.g(b8, byteBuffer);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // i6.n
        public void p(ByteArrayOutputStream byteArrayOutputStream, Object obj) {
            ArrayList c8;
            if (obj instanceof m) {
                byteArrayOutputStream.write(128);
                c8 = ((m) obj).d();
            } else if (obj instanceof s) {
                byteArrayOutputStream.write(129);
                c8 = ((s) obj).c();
            } else if (obj instanceof y) {
                byteArrayOutputStream.write(130);
                c8 = ((y) obj).d();
            } else if (obj instanceof C0128t0) {
                byteArrayOutputStream.write(131);
                c8 = ((C0128t0) obj).d();
            } else if (obj instanceof v0) {
                byteArrayOutputStream.write(132);
                c8 = ((v0) obj).f();
            } else if (obj instanceof n1) {
                byteArrayOutputStream.write(133);
                c8 = ((n1) obj).f();
            } else if (obj instanceof y1) {
                byteArrayOutputStream.write(134);
                c8 = ((y1) obj).d();
            } else if (!(obj instanceof a2)) {
                super.p(byteArrayOutputStream, obj);
                return;
            } else {
                byteArrayOutputStream.write(135);
                c8 = ((a2) obj).c();
            }
            p(byteArrayOutputStream, c8);
        }
    }

    /* loaded from: classes.dex */
    public static final class v0 {

        /* renamed from: a  reason: collision with root package name */
        private Long f9551a;

        /* renamed from: b  reason: collision with root package name */
        private Long f9552b;

        v0() {
        }

        static v0 a(ArrayList arrayList) {
            Long valueOf;
            v0 v0Var = new v0();
            Object obj = arrayList.get(0);
            Long l8 = null;
            if (obj == null) {
                valueOf = null;
            } else {
                valueOf = Long.valueOf(obj instanceof Integer ? ((Integer) obj).intValue() : ((Long) obj).longValue());
            }
            v0Var.e(valueOf);
            Object obj2 = arrayList.get(1);
            if (obj2 != null) {
                l8 = Long.valueOf(obj2 instanceof Integer ? ((Integer) obj2).intValue() : ((Long) obj2).longValue());
            }
            v0Var.d(l8);
            return v0Var;
        }

        public Long b() {
            return this.f9552b;
        }

        public Long c() {
            return this.f9551a;
        }

        public void d(Long l8) {
            this.f9552b = l8;
        }

        public void e(Long l8) {
            if (l8 == null) {
                throw new IllegalStateException("Nonnull field \"meteringPointId\" is null.");
            }
            this.f9551a = l8;
        }

        ArrayList f() {
            ArrayList arrayList = new ArrayList(2);
            arrayList.add(this.f9551a);
            arrayList.add(this.f9552b);
            return arrayList;
        }
    }

    /* loaded from: classes.dex */
    public static class v1 {

        /* renamed from: a  reason: collision with root package name */
        private final i6.b f9553a;

        /* loaded from: classes.dex */
        public interface a {
            void a(Object obj);
        }

        public v1(i6.b bVar) {
            this.f9553a = bVar;
        }

        static i6.h c() {
            return new i6.n();
        }

        public void b(Long l8, final a aVar) {
            new i6.a(this.f9553a, "dev.flutter.pigeon.VideoCaptureFlutterApi.create", c()).d(new ArrayList(Collections.singletonList(l8)), new a.e() { // from class: m6.k4
                @Override // i6.a.e
                public final void a(Object obj) {
                    t0.v1.a.this.a(null);
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public static class w {

        /* renamed from: a  reason: collision with root package name */
        private final i6.b f9554a;

        /* loaded from: classes.dex */
        public interface a {
            void a(Object obj);
        }

        public w(i6.b bVar) {
            this.f9554a = bVar;
        }

        static i6.h b() {
            return new i6.n();
        }

        public void d(String str, final a aVar) {
            new i6.a(this.f9554a, "dev.flutter.pigeon.DeviceOrientationManagerFlutterApi.onDeviceOrientationChanged", b()).d(new ArrayList(Collections.singletonList(str)), new a.e() { // from class: m6.w1
                @Override // i6.a.e
                public final void a(Object obj) {
                    t0.w.a.this.a(null);
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public static class w0 {

        /* renamed from: a  reason: collision with root package name */
        private final i6.b f9555a;

        /* loaded from: classes.dex */
        public interface a {
            void a(Object obj);
        }

        public w0(i6.b bVar) {
            this.f9555a = bVar;
        }

        static i6.h b() {
            return new i6.n();
        }

        public void d(Long l8, Long l9, final a aVar) {
            new i6.a(this.f9555a, "dev.flutter.pigeon.ObserverFlutterApi.onChanged", b()).d(new ArrayList(Arrays.asList(l8, l9)), new a.e() { // from class: m6.a3
                @Override // i6.a.e
                public final void a(Object obj) {
                    t0.w0.a.this.a(null);
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public interface w1 {
        static i6.h a() {
            return new i6.n();
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void d(w1 w1Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            arrayList.add(0, w1Var.k(valueOf));
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void e(w1 w1Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            arrayList.add(0, w1Var.c(valueOf));
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void i(w1 w1Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Number number = (Number) arrayList2.get(0);
            Number number2 = (Number) arrayList2.get(1);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            w1Var.b(valueOf, number2 == null ? null : Long.valueOf(number2.longValue()));
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        static void j(i6.b bVar, final w1 w1Var) {
            i6.a aVar = new i6.a(bVar, "dev.flutter.pigeon.VideoCaptureHostApi.withOutput", a());
            if (w1Var != null) {
                aVar.e(new a.d() { // from class: m6.l4
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.w1.d(t0.w1.this, obj, eVar);
                    }
                });
            } else {
                aVar.e(null);
            }
            i6.a aVar2 = new i6.a(bVar, "dev.flutter.pigeon.VideoCaptureHostApi.getOutput", a());
            if (w1Var != null) {
                aVar2.e(new a.d() { // from class: m6.m4
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.w1.e(t0.w1.this, obj, eVar);
                    }
                });
            } else {
                aVar2.e(null);
            }
            i6.a aVar3 = new i6.a(bVar, "dev.flutter.pigeon.VideoCaptureHostApi.setTargetRotation", a());
            if (w1Var != null) {
                aVar3.e(new a.d() { // from class: m6.n4
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.w1.i(t0.w1.this, obj, eVar);
                    }
                });
            } else {
                aVar3.e(null);
            }
        }

        void b(Long l8, Long l9);

        Long c(Long l8);

        Long k(Long l8);
    }

    /* loaded from: classes.dex */
    public interface x {
        static i6.h a() {
            return new i6.n();
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void c(x xVar, Object obj, a.e eVar) {
            ArrayList arrayList = new ArrayList();
            try {
                xVar.b();
                arrayList.add(0, null);
            } catch (Throwable th) {
                arrayList = t0.a(th);
            }
            eVar.a(arrayList);
        }

        static void f(i6.b bVar, final x xVar) {
            i6.a aVar = new i6.a(bVar, "dev.flutter.pigeon.DeviceOrientationManagerHostApi.startListeningForDeviceOrientationChange", a());
            if (xVar != null) {
                aVar.e(new a.d() { // from class: m6.x1
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.x.i(t0.x.this, obj, eVar);
                    }
                });
            } else {
                aVar.e(null);
            }
            i6.a aVar2 = new i6.a(bVar, "dev.flutter.pigeon.DeviceOrientationManagerHostApi.stopListeningForDeviceOrientationChange", a());
            if (xVar != null) {
                aVar2.e(new a.d() { // from class: m6.y1
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.x.c(t0.x.this, obj, eVar);
                    }
                });
            } else {
                aVar2.e(null);
            }
            i6.a aVar3 = new i6.a(bVar, "dev.flutter.pigeon.DeviceOrientationManagerHostApi.getDefaultDisplayRotation", a());
            if (xVar != null) {
                aVar3.e(new a.d() { // from class: m6.z1
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.x.n(t0.x.this, obj, eVar);
                    }
                });
            } else {
                aVar3.e(null);
            }
            i6.a aVar4 = new i6.a(bVar, "dev.flutter.pigeon.DeviceOrientationManagerHostApi.getUiOrientation", a());
            if (xVar != null) {
                aVar4.e(new a.d() { // from class: m6.a2
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        t0.x.l(t0.x.this, obj, eVar);
                    }
                });
            } else {
                aVar4.e(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void i(x xVar, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) obj;
            Boolean bool = (Boolean) arrayList2.get(0);
            Number number = (Number) arrayList2.get(1);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            xVar.k(bool, valueOf);
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void l(x xVar, Object obj, a.e eVar) {
            ArrayList arrayList = new ArrayList();
            try {
                arrayList.add(0, xVar.g());
            } catch (Throwable th) {
                arrayList = t0.a(th);
            }
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void n(x xVar, Object obj, a.e eVar) {
            ArrayList arrayList = new ArrayList();
            try {
                arrayList.add(0, xVar.d());
            } catch (Throwable th) {
                arrayList = t0.a(th);
            }
            eVar.a(arrayList);
        }

        void b();

        Long d();

        String g();

        void k(Boolean bool, Long l8);
    }

    /* loaded from: classes.dex */
    public interface x0 {
        static i6.h a() {
            return new i6.n();
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void c(x0 x0Var, Object obj, a.e eVar) {
            Long valueOf;
            ArrayList arrayList = new ArrayList();
            Number number = (Number) ((ArrayList) obj).get(0);
            if (number == null) {
                valueOf = null;
            } else {
                try {
                    valueOf = Long.valueOf(number.longValue());
                } catch (Throwable th) {
                    arrayList = t0.a(th);
                }
            }
            x0Var.b(valueOf);
            arrayList.add(0, null);
            eVar.a(arrayList);
        }

        static void d(i6.b bVar, final x0 x0Var) {
            new i6.a(bVar, "dev.flutter.pigeon.ObserverHostApi.create", a()).e(x0Var != null ? new a.d() { // from class: m6.b3
                @Override // i6.a.d
                public final void a(Object obj, a.e eVar) {
                    t0.x0.c(t0.x0.this, obj, eVar);
                }
            } : null);
        }

        void b(Long l8);
    }

    /* loaded from: classes.dex */
    public enum x1 {
        SD(0),
        HD(1),
        FHD(2),
        UHD(3),
        LOWEST(4),
        HIGHEST(5);
        

        /* renamed from: e  reason: collision with root package name */
        final int f9563e;

        x1(int i8) {
            this.f9563e = i8;
        }
    }

    /* loaded from: classes.dex */
    public static final class y {

        /* renamed from: a  reason: collision with root package name */
        private Long f9564a;

        /* renamed from: b  reason: collision with root package name */
        private Long f9565b;

        /* loaded from: classes.dex */
        public static final class a {

            /* renamed from: a  reason: collision with root package name */
            private Long f9566a;

            /* renamed from: b  reason: collision with root package name */
            private Long f9567b;

            public y a() {
                y yVar = new y();
                yVar.c(this.f9566a);
                yVar.b(this.f9567b);
                return yVar;
            }

            public a b(Long l8) {
                this.f9567b = l8;
                return this;
            }

            public a c(Long l8) {
                this.f9566a = l8;
                return this;
            }
        }

        y() {
        }

        static y a(ArrayList arrayList) {
            Long valueOf;
            y yVar = new y();
            Object obj = arrayList.get(0);
            Long l8 = null;
            if (obj == null) {
                valueOf = null;
            } else {
                valueOf = Long.valueOf(obj instanceof Integer ? ((Integer) obj).intValue() : ((Long) obj).longValue());
            }
            yVar.c(valueOf);
            Object obj2 = arrayList.get(1);
            if (obj2 != null) {
                l8 = Long.valueOf(obj2 instanceof Integer ? ((Integer) obj2).intValue() : ((Long) obj2).longValue());
            }
            yVar.b(l8);
            return yVar;
        }

        public void b(Long l8) {
            if (l8 == null) {
                throw new IllegalStateException("Nonnull field \"maxCompensation\" is null.");
            }
            this.f9565b = l8;
        }

        public void c(Long l8) {
            if (l8 == null) {
                throw new IllegalStateException("Nonnull field \"minCompensation\" is null.");
            }
            this.f9564a = l8;
        }

        ArrayList d() {
            ArrayList arrayList = new ArrayList(2);
            arrayList.add(this.f9564a);
            arrayList.add(this.f9565b);
            return arrayList;
        }
    }

    /* loaded from: classes.dex */
    public static class y0 {

        /* renamed from: a  reason: collision with root package name */
        private final i6.b f9568a;

        /* loaded from: classes.dex */
        public interface a {
            void a(Object obj);
        }

        public y0(i6.b bVar) {
            this.f9568a = bVar;
        }

        static i6.h d() {
            return z0.f9571d;
        }

        public void c(Long l8, final a aVar) {
            new i6.a(this.f9568a, "dev.flutter.pigeon.PendingRecordingFlutterApi.create", d()).d(new ArrayList(Collections.singletonList(l8)), new a.e() { // from class: m6.c3
                @Override // i6.a.e
                public final void a(Object obj) {
                    t0.y0.a.this.a(null);
                }
            });
        }

        public void g(a2 a2Var, final a aVar) {
            new i6.a(this.f9568a, "dev.flutter.pigeon.PendingRecordingFlutterApi.onVideoRecordingEvent", d()).d(new ArrayList(Collections.singletonList(a2Var)), new a.e() { // from class: m6.d3
                @Override // i6.a.e
                public final void a(Object obj) {
                    t0.y0.a.this.a(null);
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public static final class y1 {

        /* renamed from: a  reason: collision with root package name */
        private x1 f9569a;

        y1() {
        }

        static y1 a(ArrayList arrayList) {
            y1 y1Var = new y1();
            Object obj = arrayList.get(0);
            y1Var.c(obj == null ? null : x1.values()[((Integer) obj).intValue()]);
            return y1Var;
        }

        public x1 b() {
            return this.f9569a;
        }

        public void c(x1 x1Var) {
            if (x1Var == null) {
                throw new IllegalStateException("Nonnull field \"quality\" is null.");
            }
            this.f9569a = x1Var;
        }

        ArrayList d() {
            ArrayList arrayList = new ArrayList(1);
            x1 x1Var = this.f9569a;
            arrayList.add(x1Var == null ? null : Integer.valueOf(x1Var.f9563e));
            return arrayList;
        }
    }

    /* loaded from: classes.dex */
    public static class z {

        /* renamed from: a  reason: collision with root package name */
        private final i6.b f9570a;

        /* loaded from: classes.dex */
        public interface a {
            void a(Object obj);
        }

        public z(i6.b bVar) {
            this.f9570a = bVar;
        }

        static i6.h c() {
            return a0.f9471d;
        }

        public void b(Long l8, y yVar, Double d8, final a aVar) {
            new i6.a(this.f9570a, "dev.flutter.pigeon.ExposureStateFlutterApi.create", c()).d(new ArrayList(Arrays.asList(l8, yVar, d8)), new a.e() { // from class: m6.b2
                @Override // i6.a.e
                public final void a(Object obj) {
                    t0.z.a.this.a(null);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class z0 extends i6.n {

        /* renamed from: d  reason: collision with root package name */
        public static final z0 f9571d = new z0();

        private z0() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // i6.n
        public Object g(byte b8, ByteBuffer byteBuffer) {
            return b8 != Byte.MIN_VALUE ? super.g(b8, byteBuffer) : a2.a((ArrayList) f(byteBuffer));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // i6.n
        public void p(ByteArrayOutputStream byteArrayOutputStream, Object obj) {
            if (!(obj instanceof a2)) {
                super.p(byteArrayOutputStream, obj);
                return;
            }
            byteArrayOutputStream.write(128);
            p(byteArrayOutputStream, ((a2) obj).c());
        }
    }

    /* loaded from: classes.dex */
    public enum z1 {
        START(0),
        FINALIZE(1);
        

        /* renamed from: e  reason: collision with root package name */
        final int f9575e;

        z1(int i8) {
            this.f9575e = i8;
        }
    }

    protected static ArrayList a(Throwable th) {
        Object obj;
        ArrayList arrayList = new ArrayList(3);
        if (th instanceof c0) {
            c0 c0Var = (c0) th;
            arrayList.add(c0Var.f9481e);
            arrayList.add(c0Var.getMessage());
            obj = c0Var.f9482f;
        } else {
            arrayList.add(th.toString());
            arrayList.add(th.getClass().getSimpleName());
            obj = "Cause: " + th.getCause() + ", Stacktrace: " + Log.getStackTraceString(th);
        }
        arrayList.add(obj);
        return arrayList;
    }
}
