package c2;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebResourceResponse;
import androidx.webkit.internal.j0;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class r {

    /* renamed from: a  reason: collision with root package name */
    private final List f4768a;

    /* loaded from: classes.dex */
    public static final class a implements d {

        /* renamed from: a  reason: collision with root package name */
        private j0 f4769a;

        public a(Context context) {
            this.f4769a = new j0(context);
        }

        @Override // c2.r.d
        public WebResourceResponse handle(String str) {
            try {
                return new WebResourceResponse(j0.f(str), null, this.f4769a.h(str));
            } catch (IOException e8) {
                Log.e("WebViewAssetLoader", "Error opening asset path: " + str, e8);
                return new WebResourceResponse(null, null, null);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        private boolean f4770a;

        /* renamed from: b  reason: collision with root package name */
        private String f4771b = "appassets.androidplatform.net";

        /* renamed from: c  reason: collision with root package name */
        private final List f4772c = new ArrayList();

        public b a(String str, d dVar) {
            this.f4772c.add(g1.d.a(str, dVar));
            return this;
        }

        public r b() {
            ArrayList arrayList = new ArrayList();
            for (g1.d dVar : this.f4772c) {
                arrayList.add(new e(this.f4771b, (String) dVar.f7099a, this.f4770a, (d) dVar.f7100b));
            }
            return new r(arrayList);
        }

        public b c(String str) {
            this.f4771b = str;
            return this;
        }

        public b d(boolean z7) {
            this.f4770a = z7;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static final class c implements d {

        /* renamed from: b  reason: collision with root package name */
        private static final String[] f4773b = {"app_webview/", "databases/", "lib/", "shared_prefs/", "code_cache/"};

        /* renamed from: a  reason: collision with root package name */
        private final File f4774a;

        public c(Context context, File file) {
            try {
                this.f4774a = new File(j0.a(file));
                if (a(context)) {
                    return;
                }
                throw new IllegalArgumentException("The given directory \"" + file + "\" doesn't exist under an allowed app internal storage directory");
            } catch (IOException e8) {
                throw new IllegalArgumentException("Failed to resolve the canonical path for the given directory: " + file.getPath(), e8);
            }
        }

        private boolean a(Context context) {
            String a8 = j0.a(this.f4774a);
            String a9 = j0.a(context.getCacheDir());
            String a10 = j0.a(j0.c(context));
            if ((!a8.startsWith(a9) && !a8.startsWith(a10)) || a8.equals(a9) || a8.equals(a10)) {
                return false;
            }
            for (String str : f4773b) {
                if (a8.startsWith(a10 + str)) {
                    return false;
                }
            }
            return true;
        }

        @Override // c2.r.d
        public WebResourceResponse handle(String str) {
            File b8;
            try {
                b8 = j0.b(this.f4774a, str);
            } catch (IOException e8) {
                Log.e("WebViewAssetLoader", "Error opening the requested path: " + str, e8);
            }
            if (b8 != null) {
                return new WebResourceResponse(j0.f(str), null, j0.i(b8));
            }
            Log.e("WebViewAssetLoader", String.format("The requested file: %s is outside the mounted directory: %s", str, this.f4774a));
            return new WebResourceResponse(null, null, null);
        }
    }

    /* loaded from: classes.dex */
    public interface d {
        WebResourceResponse handle(String str);
    }

    /* loaded from: classes.dex */
    static class e {

        /* renamed from: a  reason: collision with root package name */
        final boolean f4775a;

        /* renamed from: b  reason: collision with root package name */
        final String f4776b;

        /* renamed from: c  reason: collision with root package name */
        final String f4777c;

        /* renamed from: d  reason: collision with root package name */
        final d f4778d;

        e(String str, String str2, boolean z7, d dVar) {
            if (str2.isEmpty() || str2.charAt(0) != '/') {
                throw new IllegalArgumentException("Path should start with a slash '/'.");
            }
            if (!str2.endsWith("/")) {
                throw new IllegalArgumentException("Path should end with a slash '/'");
            }
            this.f4776b = str;
            this.f4777c = str2;
            this.f4775a = z7;
            this.f4778d = dVar;
        }

        public String a(String str) {
            return str.replaceFirst(this.f4777c, "");
        }

        public d b(Uri uri) {
            if (!uri.getScheme().equals("http") || this.f4775a) {
                if ((uri.getScheme().equals("http") || uri.getScheme().equals("https")) && uri.getAuthority().equals(this.f4776b) && uri.getPath().startsWith(this.f4777c)) {
                    return this.f4778d;
                }
                return null;
            }
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static final class f implements d {

        /* renamed from: a  reason: collision with root package name */
        private j0 f4779a;

        public f(Context context) {
            this.f4779a = new j0(context);
        }

        @Override // c2.r.d
        public WebResourceResponse handle(String str) {
            StringBuilder sb;
            String str2;
            try {
                return new WebResourceResponse(j0.f(str), null, this.f4779a.j(str));
            } catch (Resources.NotFoundException e8) {
                e = e8;
                sb = new StringBuilder();
                str2 = "Resource not found from the path: ";
                sb.append(str2);
                sb.append(str);
                Log.e("WebViewAssetLoader", sb.toString(), e);
                return new WebResourceResponse(null, null, null);
            } catch (IOException e9) {
                e = e9;
                sb = new StringBuilder();
                str2 = "Error opening resource from the path: ";
                sb.append(str2);
                sb.append(str);
                Log.e("WebViewAssetLoader", sb.toString(), e);
                return new WebResourceResponse(null, null, null);
            }
        }
    }

    r(List list) {
        this.f4768a = list;
    }

    public WebResourceResponse a(Uri uri) {
        WebResourceResponse handle;
        for (e eVar : this.f4768a) {
            d b8 = eVar.b(uri);
            if (b8 != null && (handle = b8.handle(eVar.a(uri.getPath()))) != null) {
                return handle;
            }
        }
        return null;
    }
}
