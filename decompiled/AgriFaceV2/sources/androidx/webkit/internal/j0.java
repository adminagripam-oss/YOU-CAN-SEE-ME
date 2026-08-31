package androidx.webkit.internal;

import android.content.Context;
import android.util.TypedValue;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
/* loaded from: classes.dex */
public class j0 {

    /* renamed from: a  reason: collision with root package name */
    private Context f4053a;

    public j0(Context context) {
        this.f4053a = context;
    }

    public static String a(File file) {
        String canonicalPath = file.getCanonicalPath();
        if (canonicalPath.endsWith("/")) {
            return canonicalPath;
        }
        return canonicalPath + "/";
    }

    public static File b(File file, String str) {
        String a8 = a(file);
        String canonicalPath = new File(file, str).getCanonicalPath();
        if (canonicalPath.startsWith(a8)) {
            return new File(canonicalPath);
        }
        return null;
    }

    public static File c(Context context) {
        return c.e(context);
    }

    private int d(String str, String str2) {
        return this.f4053a.getResources().getIdentifier(str2, str, this.f4053a.getPackageName());
    }

    private int e(int i8) {
        TypedValue typedValue = new TypedValue();
        this.f4053a.getResources().getValue(i8, typedValue, true);
        return typedValue.type;
    }

    public static String f(String str) {
        String a8 = p0.a(str);
        return a8 == null ? "text/plain" : a8;
    }

    private static InputStream g(String str, InputStream inputStream) {
        return str.endsWith(".svgz") ? new GZIPInputStream(inputStream) : inputStream;
    }

    public static InputStream i(File file) {
        return g(file.getPath(), new FileInputStream(file));
    }

    private static String k(String str) {
        return (str.length() <= 1 || str.charAt(0) != '/') ? str : str.substring(1);
    }

    public InputStream h(String str) {
        String k8 = k(str);
        return g(k8, this.f4053a.getAssets().open(k8, 2));
    }

    public InputStream j(String str) {
        String k8 = k(str);
        String[] split = k8.split("/", -1);
        if (split.length != 2) {
            throw new IllegalArgumentException("Incorrect resource path: " + k8);
        }
        String str2 = split[0];
        String str3 = split[1];
        int lastIndexOf = str3.lastIndexOf(46);
        if (lastIndexOf != -1) {
            str3 = str3.substring(0, lastIndexOf);
        }
        int d8 = d(str2, str3);
        int e8 = e(d8);
        if (e8 == 3) {
            return g(k8, this.f4053a.getResources().openRawResource(d8));
        }
        throw new IOException(String.format("Expected %s resource to be of TYPE_STRING but was %d", k8, Integer.valueOf(e8)));
    }
}
