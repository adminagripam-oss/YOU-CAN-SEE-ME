package androidx.webkit;

import android.content.Context;
import androidx.webkit.internal.g1;
import androidx.webkit.internal.x;
import c2.c;
import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;
import org.chromium.support_lib_boundary.ProcessGlobalConfigConstants;
/* loaded from: classes.dex */
public class ProcessGlobalConfig {

    /* renamed from: a  reason: collision with root package name */
    String f3998a;

    /* renamed from: b  reason: collision with root package name */
    String f3999b;

    /* renamed from: c  reason: collision with root package name */
    String f4000c;
    private static final AtomicReference<HashMap<String, Object>> sProcessGlobalConfig = new AtomicReference<>();

    /* renamed from: d  reason: collision with root package name */
    private static final Object f3996d = new Object();

    /* renamed from: e  reason: collision with root package name */
    private static boolean f3997e = false;

    public static void a(ProcessGlobalConfig processGlobalConfig) {
        synchronized (f3996d) {
            if (f3997e) {
                throw new IllegalStateException("ProcessGlobalConfig#apply was called more than once, which is an illegal operation. The configuration settings provided by ProcessGlobalConfig take effect only once, when WebView is first loaded into the current process. Every process should only ever create a single instance of ProcessGlobalConfig and apply it once, before any calls to android.webkit APIs, such as during early app startup.");
            }
            f3997e = true;
        }
        HashMap hashMap = new HashMap();
        if (d()) {
            throw new IllegalStateException("WebView has already been loaded in the current process, so any attempt to apply the settings in ProcessGlobalConfig will have no effect. ProcessGlobalConfig#apply needs to be called before any calls to android.webkit APIs, such as during early app startup.");
        }
        if (processGlobalConfig.f3998a != null) {
            if (g1.M.e()) {
                x.e(processGlobalConfig.f3998a);
            } else {
                hashMap.put(ProcessGlobalConfigConstants.DATA_DIRECTORY_SUFFIX, processGlobalConfig.f3998a);
            }
        }
        String str = processGlobalConfig.f3999b;
        if (str != null) {
            hashMap.put(ProcessGlobalConfigConstants.DATA_DIRECTORY_BASE_PATH, str);
        }
        String str2 = processGlobalConfig.f4000c;
        if (str2 != null) {
            hashMap.put(ProcessGlobalConfigConstants.CACHE_DIRECTORY_BASE_PATH, str2);
        }
        if (!c.a(sProcessGlobalConfig, null, hashMap)) {
            throw new RuntimeException("Attempting to set ProcessGlobalConfig#sProcessGlobalConfig when it was already set");
        }
    }

    private static boolean d() {
        try {
            Field declaredField = Class.forName("android.webkit.WebViewFactory").getDeclaredField("sProviderInstance");
            declaredField.setAccessible(true);
            return declaredField.get(null) != null;
        } catch (Exception unused) {
            return false;
        }
    }

    public ProcessGlobalConfig b(Context context, String str) {
        if (g1.M.d(context)) {
            if (str.equals("")) {
                throw new IllegalArgumentException("Suffix cannot be an empty string");
            }
            if (str.indexOf(File.separatorChar) < 0) {
                this.f3998a = str;
                return this;
            }
            throw new IllegalArgumentException("Suffix " + str + " contains a path separator");
        }
        throw g1.a();
    }

    public ProcessGlobalConfig c(Context context, File file, File file2) {
        if (g1.N.d(context)) {
            if (file.isAbsolute()) {
                if (file2.isAbsolute()) {
                    this.f3999b = file.getAbsolutePath();
                    this.f4000c = file2.getAbsolutePath();
                    return this;
                }
                throw new IllegalArgumentException("cacheDirectoryBasePath must be a non-empty absolute path");
            }
            throw new IllegalArgumentException("dataDirectoryBasePath must be a non-empty absolute path");
        }
        throw g1.a();
    }
}
