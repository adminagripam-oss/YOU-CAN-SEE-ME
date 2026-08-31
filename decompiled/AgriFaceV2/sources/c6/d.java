package c6;

import android.app.ActivityManager;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import c6.d;
import io.flutter.embedding.engine.FlutterJNI;
import io.flutter.view.h;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
/* loaded from: classes.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private boolean f4844a = false;

    /* renamed from: b  reason: collision with root package name */
    private c f4845b;

    /* renamed from: c  reason: collision with root package name */
    private long f4846c;

    /* renamed from: d  reason: collision with root package name */
    private c6.b f4847d;

    /* renamed from: e  reason: collision with root package name */
    private FlutterJNI f4848e;

    /* renamed from: f  reason: collision with root package name */
    private ExecutorService f4849f;

    /* renamed from: g  reason: collision with root package name */
    Future f4850g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements Callable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Context f4851a;

        a(Context context) {
            this.f4851a = context;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void c() {
            d.this.f4848e.prefetchDefaultFontManager();
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: b */
        public b call() {
            p6.e j8 = p6.e.j("FlutterLoader initTask");
            try {
                d.a(d.this, this.f4851a);
                try {
                    d.this.f4848e.loadLibrary();
                    d.this.f4848e.updateRefreshRate();
                    d.this.f4849f.execute(new Runnable() { // from class: c6.c
                        @Override // java.lang.Runnable
                        public final void run() {
                            d.a.this.c();
                        }
                    });
                    b bVar = new b(p6.b.d(this.f4851a), p6.b.a(this.f4851a), p6.b.c(this.f4851a), null);
                    if (j8 != null) {
                        j8.close();
                    }
                    return bVar;
                } catch (UnsatisfiedLinkError e8) {
                    if (!e8.toString().contains("couldn't find \"libflutter.so\"") && !e8.toString().contains("dlopen failed: library \"libflutter.so\" not found")) {
                        throw e8;
                    }
                    String property = System.getProperty("os.arch");
                    File file = new File(d.this.f4847d.f4841f);
                    String[] list = file.list();
                    throw new UnsupportedOperationException("Could not load libflutter.so this is possibly because the application is running on an architecture that Flutter Android does not support (e.g. x86) see https://docs.flutter.dev/deployment/android#what-are-the-supported-target-architectures for more detail.\nApp is using cpu architecture: " + property + ", and the native libraries directory (with path " + file.getAbsolutePath() + ") contains the following files: " + Arrays.toString(list), e8);
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
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        final String f4853a;

        /* renamed from: b  reason: collision with root package name */
        final String f4854b;

        /* renamed from: c  reason: collision with root package name */
        final String f4855c;

        private b(String str, String str2, String str3) {
            this.f4853a = str;
            this.f4854b = str2;
            this.f4855c = str3;
        }

        /* synthetic */ b(String str, String str2, String str3, a aVar) {
            this(str, str2, str3);
        }
    }

    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        private String f4856a;

        public String a() {
            return this.f4856a;
        }
    }

    public d(FlutterJNI flutterJNI, ExecutorService executorService) {
        this.f4848e = flutterJNI;
        this.f4849f = executorService;
    }

    static /* synthetic */ e a(d dVar, Context context) {
        dVar.j(context);
        return null;
    }

    private String h(String str) {
        return this.f4847d.f4839d + File.separator + str;
    }

    private e j(Context context) {
        return null;
    }

    private static boolean l(Bundle bundle) {
        if (bundle == null) {
            return true;
        }
        return bundle.getBoolean("io.flutter.embedding.android.LeakVM", true);
    }

    public boolean e() {
        return this.f4847d.f4842g;
    }

    public void f(Context context, String[] strArr) {
        if (this.f4844a) {
            return;
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("ensureInitializationComplete must be called on the main thread");
        }
        if (this.f4845b == null) {
            throw new IllegalStateException("ensureInitializationComplete must be called after startInitialization");
        }
        try {
            p6.e j8 = p6.e.j("FlutterLoader#ensureInitializationComplete");
            b bVar = (b) this.f4850g.get();
            ArrayList arrayList = new ArrayList();
            arrayList.add("--icu-symbol-prefix=_binary_icudtl_dat");
            StringBuilder sb = new StringBuilder();
            sb.append("--icu-native-lib-path=");
            sb.append(this.f4847d.f4841f);
            String str = File.separator;
            sb.append(str);
            sb.append("libflutter.so");
            arrayList.add(sb.toString());
            if (strArr != null) {
                Collections.addAll(arrayList, strArr);
            }
            arrayList.add("--aot-shared-library-name=" + this.f4847d.f4836a);
            arrayList.add("--aot-shared-library-name=" + this.f4847d.f4841f + str + this.f4847d.f4836a);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("--cache-dir-path=");
            sb2.append(bVar.f4854b);
            arrayList.add(sb2.toString());
            if (this.f4847d.f4840e != null) {
                arrayList.add("--domain-network-policy=" + this.f4847d.f4840e);
            }
            if (this.f4845b.a() != null) {
                arrayList.add("--log-tag=" + this.f4845b.a());
            }
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            int i8 = bundle != null ? bundle.getInt("io.flutter.embedding.android.OldGenHeapSize") : 0;
            if (i8 == 0) {
                ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
                ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
                i8 = (int) ((memoryInfo.totalMem / 1000000.0d) / 2.0d);
            }
            arrayList.add("--old-gen-heap-size=" + i8);
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            arrayList.add("--resource-cache-max-bytes-threshold=" + (displayMetrics.widthPixels * displayMetrics.heightPixels * 12 * 4));
            arrayList.add("--prefetched-default-font-manager");
            if (bundle != null) {
                if (bundle.getBoolean("io.flutter.embedding.android.EnableImpeller", false)) {
                    arrayList.add("--enable-impeller");
                }
                if (bundle.getBoolean("io.flutter.embedding.android.EnableVulkanValidation", false)) {
                    arrayList.add("--enable-vulkan-validation");
                }
                if (bundle.getBoolean("io.flutter.embedding.android.EnableOpenGLGPUTracing", false)) {
                    arrayList.add("--enable-opengl-gpu-tracing");
                }
                if (bundle.getBoolean("io.flutter.embedding.android.EnableVulkanGPUTracing", false)) {
                    arrayList.add("--enable-vulkan-gpu-tracing");
                }
                String string = bundle.getString("io.flutter.embedding.android.ImpellerBackend");
                if (string != null) {
                    arrayList.add("--impeller-backend=" + string);
                }
            }
            String str2 = l(bundle) ? "true" : "false";
            arrayList.add("--leak-vm=" + str2);
            this.f4848e.init(context, (String[]) arrayList.toArray(new String[0]), null, bVar.f4853a, bVar.f4854b, SystemClock.uptimeMillis() - this.f4846c);
            this.f4844a = true;
            if (j8 != null) {
                j8.close();
            }
        } catch (Exception e8) {
            z5.b.c("FlutterLoader", "Flutter initialization failed.", e8);
            throw new RuntimeException(e8);
        }
    }

    public String g() {
        return this.f4847d.f4839d;
    }

    public String i(String str) {
        return h(str);
    }

    public boolean k() {
        return this.f4844a;
    }

    public void m(Context context) {
        n(context, new c());
    }

    public void n(Context context, c cVar) {
        if (this.f4845b != null) {
            return;
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("startInitialization must be called on the main thread");
        }
        p6.e j8 = p6.e.j("FlutterLoader#startInitialization");
        try {
            Context applicationContext = context.getApplicationContext();
            this.f4845b = cVar;
            this.f4846c = SystemClock.uptimeMillis();
            this.f4847d = c6.a.e(applicationContext);
            h.f((DisplayManager) applicationContext.getSystemService("display"), this.f4848e).g();
            this.f4850g = this.f4849f.submit(new a(applicationContext));
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
}
