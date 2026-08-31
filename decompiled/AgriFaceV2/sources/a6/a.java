package a6;

import android.content.res.AssetManager;
import i6.b;
import i6.p;
import io.flutter.embedding.engine.FlutterJNI;
import java.nio.ByteBuffer;
import java.util.List;
/* loaded from: classes.dex */
public class a implements i6.b {

    /* renamed from: a  reason: collision with root package name */
    private final FlutterJNI f1354a;

    /* renamed from: b  reason: collision with root package name */
    private final AssetManager f1355b;

    /* renamed from: c  reason: collision with root package name */
    private final a6.c f1356c;

    /* renamed from: d  reason: collision with root package name */
    private final i6.b f1357d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f1358e;

    /* renamed from: f  reason: collision with root package name */
    private String f1359f;

    /* renamed from: g  reason: collision with root package name */
    private final b.a f1360g;

    /* renamed from: a6.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class C0001a implements b.a {
        C0001a() {
        }

        @Override // i6.b.a
        public void a(ByteBuffer byteBuffer, b.InterfaceC0103b interfaceC0103b) {
            a.this.f1359f = p.f7553b.b(byteBuffer);
            a.g(a.this);
        }
    }

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public final String f1362a;

        /* renamed from: b  reason: collision with root package name */
        public final String f1363b;

        /* renamed from: c  reason: collision with root package name */
        public final String f1364c;

        public b(String str, String str2) {
            this.f1362a = str;
            this.f1363b = null;
            this.f1364c = str2;
        }

        public static b a() {
            c6.d c8 = z5.a.e().c();
            if (c8.k()) {
                return new b(c8.g(), "main");
            }
            throw new AssertionError("DartEntrypoints can only be created once a FlutterEngine is created.");
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            if (this.f1362a.equals(bVar.f1362a)) {
                return this.f1364c.equals(bVar.f1364c);
            }
            return false;
        }

        public int hashCode() {
            return (this.f1362a.hashCode() * 31) + this.f1364c.hashCode();
        }

        public String toString() {
            return "DartEntrypoint( bundle path: " + this.f1362a + ", function: " + this.f1364c + " )";
        }

        public b(String str, String str2, String str3) {
            this.f1362a = str;
            this.f1363b = str2;
            this.f1364c = str3;
        }
    }

    /* loaded from: classes.dex */
    private static class c implements i6.b {

        /* renamed from: a  reason: collision with root package name */
        private final a6.c f1365a;

        private c(a6.c cVar) {
            this.f1365a = cVar;
        }

        @Override // i6.b
        public b.c a(b.d dVar) {
            return this.f1365a.a(dVar);
        }

        @Override // i6.b
        public void b(String str, b.a aVar, b.c cVar) {
            this.f1365a.b(str, aVar, cVar);
        }

        @Override // i6.b
        public void d(String str, b.a aVar) {
            this.f1365a.d(str, aVar);
        }

        @Override // i6.b
        public void e(String str, ByteBuffer byteBuffer) {
            this.f1365a.h(str, byteBuffer, null);
        }

        @Override // i6.b
        public void h(String str, ByteBuffer byteBuffer, b.InterfaceC0103b interfaceC0103b) {
            this.f1365a.h(str, byteBuffer, interfaceC0103b);
        }

        /* synthetic */ c(a6.c cVar, C0001a c0001a) {
            this(cVar);
        }
    }

    /* loaded from: classes.dex */
    public interface d {
    }

    public a(FlutterJNI flutterJNI, AssetManager assetManager) {
        this.f1358e = false;
        C0001a c0001a = new C0001a();
        this.f1360g = c0001a;
        this.f1354a = flutterJNI;
        this.f1355b = assetManager;
        a6.c cVar = new a6.c(flutterJNI);
        this.f1356c = cVar;
        cVar.d("flutter/isolate", c0001a);
        this.f1357d = new c(cVar, null);
        if (flutterJNI.isAttached()) {
            this.f1358e = true;
        }
    }

    static /* synthetic */ d g(a aVar) {
        aVar.getClass();
        return null;
    }

    @Override // i6.b
    public b.c a(b.d dVar) {
        return this.f1357d.a(dVar);
    }

    @Override // i6.b
    public void b(String str, b.a aVar, b.c cVar) {
        this.f1357d.b(str, aVar, cVar);
    }

    @Override // i6.b
    public void d(String str, b.a aVar) {
        this.f1357d.d(str, aVar);
    }

    @Override // i6.b
    public void e(String str, ByteBuffer byteBuffer) {
        this.f1357d.e(str, byteBuffer);
    }

    @Override // i6.b
    public void h(String str, ByteBuffer byteBuffer, b.InterfaceC0103b interfaceC0103b) {
        this.f1357d.h(str, byteBuffer, interfaceC0103b);
    }

    public void i(b bVar, List list) {
        if (this.f1358e) {
            z5.b.g("DartExecutor", "Attempted to run a DartExecutor that is already running.");
            return;
        }
        p6.e j8 = p6.e.j("DartExecutor#executeDartEntrypoint");
        try {
            z5.b.f("DartExecutor", "Executing Dart entrypoint: " + bVar);
            this.f1354a.runBundleAndSnapshotFromLibrary(bVar.f1362a, bVar.f1364c, bVar.f1363b, this.f1355b, list);
            this.f1358e = true;
            if (j8 != null) {
                j8.close();
            }
        } catch (Throwable th) {
            if (j8 != null) {
                try {
                    j8.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public boolean j() {
        return this.f1358e;
    }

    public void k() {
        if (this.f1354a.isAttached()) {
            this.f1354a.notifyLowMemoryWarning();
        }
    }

    public void l() {
        z5.b.f("DartExecutor", "Attached to JNI. Registering the platform message handler for this Dart execution context.");
        this.f1354a.setPlatformMessageHandler(this.f1356c);
    }

    public void m() {
        z5.b.f("DartExecutor", "Detached from JNI. De-registering the platform message handler for this Dart execution context.");
        this.f1354a.setPlatformMessageHandler(null);
    }
}
