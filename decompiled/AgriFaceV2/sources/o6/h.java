package o6;

import android.util.Log;
import i6.a;
import i6.n;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import o6.h;
/* loaded from: classes.dex */
public abstract class h {

    /* loaded from: classes.dex */
    public interface a {
        static i6.h a() {
            return b.f9865d;
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void c(a aVar, Object obj, a.e eVar) {
            ArrayList arrayList = new ArrayList();
            try {
                arrayList.add(0, aVar.i());
            } catch (Throwable th) {
                arrayList = h.a(th);
            }
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void g(a aVar, Object obj, a.e eVar) {
            ArrayList arrayList = new ArrayList();
            try {
                arrayList.add(0, aVar.u());
            } catch (Throwable th) {
                arrayList = h.a(th);
            }
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void m(a aVar, Object obj, a.e eVar) {
            ArrayList arrayList = new ArrayList();
            try {
                arrayList.add(0, aVar.d());
            } catch (Throwable th) {
                arrayList = h.a(th);
            }
            eVar.a(arrayList);
        }

        static void o(i6.b bVar, String str, final a aVar) {
            String str2;
            if (str.isEmpty()) {
                str2 = "";
            } else {
                str2 = "." + str;
            }
            i6.a aVar2 = new i6.a(bVar, "dev.flutter.pigeon.path_provider_android.PathProviderApi.getTemporaryPath" + str2, a(), bVar.c());
            if (aVar != null) {
                aVar2.e(new a.d() { // from class: o6.a
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        h.a.m(h.a.this, obj, eVar);
                    }
                });
            } else {
                aVar2.e(null);
            }
            i6.a aVar3 = new i6.a(bVar, "dev.flutter.pigeon.path_provider_android.PathProviderApi.getApplicationSupportPath" + str2, a(), bVar.c());
            if (aVar != null) {
                aVar3.e(new a.d() { // from class: o6.b
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        h.a.w(h.a.this, obj, eVar);
                    }
                });
            } else {
                aVar3.e(null);
            }
            i6.a aVar4 = new i6.a(bVar, "dev.flutter.pigeon.path_provider_android.PathProviderApi.getApplicationDocumentsPath" + str2, a(), bVar.c());
            if (aVar != null) {
                aVar4.e(new a.d() { // from class: o6.c
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        h.a.t(h.a.this, obj, eVar);
                    }
                });
            } else {
                aVar4.e(null);
            }
            i6.a aVar5 = new i6.a(bVar, "dev.flutter.pigeon.path_provider_android.PathProviderApi.getApplicationCachePath" + str2, a(), bVar.c());
            if (aVar != null) {
                aVar5.e(new a.d() { // from class: o6.d
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        h.a.s(h.a.this, obj, eVar);
                    }
                });
            } else {
                aVar5.e(null);
            }
            i6.a aVar6 = new i6.a(bVar, "dev.flutter.pigeon.path_provider_android.PathProviderApi.getExternalStoragePath" + str2, a(), bVar.c());
            if (aVar != null) {
                aVar6.e(new a.d() { // from class: o6.e
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        h.a.g(h.a.this, obj, eVar);
                    }
                });
            } else {
                aVar6.e(null);
            }
            i6.a aVar7 = new i6.a(bVar, "dev.flutter.pigeon.path_provider_android.PathProviderApi.getExternalCachePaths" + str2, a(), bVar.c());
            if (aVar != null) {
                aVar7.e(new a.d() { // from class: o6.f
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        h.a.c(h.a.this, obj, eVar);
                    }
                });
            } else {
                aVar7.e(null);
            }
            i6.a aVar8 = new i6.a(bVar, "dev.flutter.pigeon.path_provider_android.PathProviderApi.getExternalStoragePaths" + str2, a(), bVar.c());
            if (aVar != null) {
                aVar8.e(new a.d() { // from class: o6.g
                    @Override // i6.a.d
                    public final void a(Object obj, a.e eVar) {
                        h.a.v(h.a.this, obj, eVar);
                    }
                });
            } else {
                aVar8.e(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void s(a aVar, Object obj, a.e eVar) {
            ArrayList arrayList = new ArrayList();
            try {
                arrayList.add(0, aVar.e());
            } catch (Throwable th) {
                arrayList = h.a(th);
            }
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void t(a aVar, Object obj, a.e eVar) {
            ArrayList arrayList = new ArrayList();
            try {
                arrayList.add(0, aVar.f());
            } catch (Throwable th) {
                arrayList = h.a(th);
            }
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void v(a aVar, Object obj, a.e eVar) {
            ArrayList arrayList = new ArrayList();
            try {
                arrayList.add(0, aVar.n((c) ((ArrayList) obj).get(0)));
            } catch (Throwable th) {
                arrayList = h.a(th);
            }
            eVar.a(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static /* synthetic */ void w(a aVar, Object obj, a.e eVar) {
            ArrayList arrayList = new ArrayList();
            try {
                arrayList.add(0, aVar.r());
            } catch (Throwable th) {
                arrayList = h.a(th);
            }
            eVar.a(arrayList);
        }

        static void x(i6.b bVar, a aVar) {
            o(bVar, "", aVar);
        }

        String d();

        String e();

        String f();

        List i();

        List n(c cVar);

        String r();

        String u();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b extends n {

        /* renamed from: d  reason: collision with root package name */
        public static final b f9865d = new b();

        private b() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // i6.n
        public Object g(byte b8, ByteBuffer byteBuffer) {
            if (b8 != -127) {
                return super.g(b8, byteBuffer);
            }
            Object f8 = f(byteBuffer);
            if (f8 == null) {
                return null;
            }
            return c.values()[((Long) f8).intValue()];
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // i6.n
        public void p(ByteArrayOutputStream byteArrayOutputStream, Object obj) {
            if (!(obj instanceof c)) {
                super.p(byteArrayOutputStream, obj);
                return;
            }
            byteArrayOutputStream.write(129);
            p(byteArrayOutputStream, obj == null ? null : Integer.valueOf(((c) obj).f9878e));
        }
    }

    /* loaded from: classes.dex */
    public enum c {
        ROOT(0),
        MUSIC(1),
        PODCASTS(2),
        RINGTONES(3),
        ALARMS(4),
        NOTIFICATIONS(5),
        PICTURES(6),
        MOVIES(7),
        DOWNLOADS(8),
        DCIM(9),
        DOCUMENTS(10);
        

        /* renamed from: e  reason: collision with root package name */
        final int f9878e;

        c(int i8) {
            this.f9878e = i8;
        }
    }

    protected static ArrayList a(Throwable th) {
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(th.toString());
        arrayList.add(th.getClass().getSimpleName());
        arrayList.add("Cause: " + th.getCause() + ", Stacktrace: " + Log.getStackTraceString(th));
        return arrayList;
    }
}
