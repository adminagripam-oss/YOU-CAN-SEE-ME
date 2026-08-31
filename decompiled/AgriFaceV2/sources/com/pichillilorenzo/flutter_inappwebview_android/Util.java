package com.pichillilorenzo.flutter_inappwebview_android;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Insets;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.http.SslCertificate;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;
import com.pichillilorenzo.flutter_inappwebview_android.types.Size2D;
import com.pichillilorenzo.flutter_inappwebview_android.types.SyncBaseCallbackResultImpl;
import i6.j;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import javax.net.ssl.SSLHandshakeException;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class Util {
    public static final String ANDROID_ASSET_URL = "file:///android_asset/";
    static final String LOG_TAG = "Util";

    /* loaded from: classes.dex */
    public static class PrivateKeyAndCertificates {
        public X509Certificate[] certificates;
        public PrivateKey privateKey;

        public PrivateKeyAndCertificates(PrivateKey privateKey, X509Certificate[] x509CertificateArr) {
            this.privateKey = privateKey;
            this.certificates = x509CertificateArr;
        }
    }

    private Util() {
    }

    public static String JSONStringify(Object obj) {
        return obj == null ? "null" : obj instanceof Map ? new JSONObject((Map) obj).toString() : obj instanceof List ? new JSONArray((Collection) ((List) obj)).toString() : obj instanceof String ? JSONObject.quote((String) obj) : JSONObject.wrap(obj).toString();
    }

    public static Drawable drawableFromBytes(Context context, byte[] bArr) {
        return new BitmapDrawable(context.getResources(), BitmapFactory.decodeByteArray(bArr, 0, bArr.length));
    }

    public static InputStream getFileAsset(InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin, String str) {
        return inAppWebViewFlutterPlugin.applicationContext.getResources().getAssets().open(inAppWebViewFlutterPlugin.flutterAssets.a(str));
    }

    public static Size2D getFullscreenSize(Context context) {
        int i8;
        WindowMetrics currentWindowMetrics;
        WindowInsets windowInsets;
        int navigationBars;
        int displayCutout;
        Insets insetsIgnoringVisibility;
        int i9;
        int i10;
        int i11;
        int i12;
        Rect bounds;
        Size2D size2D = new Size2D(-1.0d, -1.0d);
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager != null) {
            if (Build.VERSION.SDK_INT >= 30) {
                currentWindowMetrics = windowManager.getCurrentWindowMetrics();
                windowInsets = currentWindowMetrics.getWindowInsets();
                navigationBars = WindowInsets.Type.navigationBars();
                displayCutout = WindowInsets.Type.displayCutout();
                insetsIgnoringVisibility = windowInsets.getInsetsIgnoringVisibility(navigationBars | displayCutout);
                i9 = insetsIgnoringVisibility.right;
                i10 = insetsIgnoringVisibility.left;
                int i13 = i9 + i10;
                i11 = insetsIgnoringVisibility.top;
                i12 = insetsIgnoringVisibility.bottom;
                bounds = currentWindowMetrics.getBounds();
                size2D.setWidth(bounds.width() - i13);
                i8 = bounds.height() - (i11 + i12);
            } else {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                size2D.setWidth(displayMetrics.widthPixels);
                i8 = displayMetrics.heightPixels;
            }
            size2D.setHeight(i8);
        }
        return size2D;
    }

    public static <T> T getOrDefault(Map<String, Object> map, String str, T t8) {
        return map.containsKey(str) ? (T) map.get(str) : t8;
    }

    public static float getPixelDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static String getUrlAsset(InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin, String str) {
        String a8 = inAppWebViewFlutterPlugin.flutterAssets.a(str);
        try {
            InputStream fileAsset = getFileAsset(inAppWebViewFlutterPlugin, str);
            if (fileAsset != null) {
                fileAsset.close();
            }
            e = null;
        } catch (IOException e8) {
            e = e8;
        }
        if (e == null) {
            return ANDROID_ASSET_URL + a8;
        }
        throw e;
    }

    public static X509Certificate getX509CertFromSslCertHack(SslCertificate sslCertificate) {
        byte[] byteArray = SslCertificate.saveState(sslCertificate).getByteArray("x509-certificate");
        if (byteArray != null) {
            try {
            } catch (CertificateException unused) {
                return null;
            }
        }
        return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(byteArray));
    }

    public static <T> T invokeMethodAndWaitResult(final j jVar, final String str, final Object obj, final SyncBaseCallbackResultImpl<T> syncBaseCallbackResultImpl) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.pichillilorenzo.flutter_inappwebview_android.Util.1
            @Override // java.lang.Runnable
            public void run() {
                j.this.d(str, obj, syncBaseCallbackResultImpl);
            }
        });
        syncBaseCallbackResultImpl.latch.await();
        return syncBaseCallbackResultImpl.result;
    }

    public static <O> Object invokeMethodIfExists(O o8, String str, Object... objArr) {
        Method[] methods;
        for (Method method : o8.getClass().getMethods()) {
            if (method.getName().equals(str)) {
                try {
                    return method.invoke(o8, objArr);
                } catch (IllegalAccessException | InvocationTargetException unused) {
                    return null;
                }
            }
        }
        return null;
    }

    public static boolean isClass(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static boolean isIPv6(String str) {
        try {
            InetAddress.getByName(str);
            return true;
        } catch (UnknownHostException unused) {
            return false;
        }
    }

    public static PrivateKeyAndCertificates loadPrivateKeyAndCertificate(InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin, String str, String str2, String str3) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = getFileAsset(inAppWebViewFlutterPlugin, str);
        } catch (IOException unused) {
            fileInputStream = null;
        }
        if (fileInputStream == null) {
            try {
                try {
                    try {
                        fileInputStream = new FileInputStream(str);
                    } catch (Exception e8) {
                        Log.e(LOG_TAG, "", e8);
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                    }
                } catch (IOException e9) {
                    Log.e(LOG_TAG, "", e9);
                }
            } catch (Throwable th) {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e10) {
                        Log.e(LOG_TAG, "", e10);
                    }
                }
                throw th;
            }
        }
        KeyStore keyStore = KeyStore.getInstance(str3);
        keyStore.load(fileInputStream, (str2 != null ? str2 : "").toCharArray());
        String nextElement = keyStore.aliases().nextElement();
        if (str2 == null) {
            str2 = "";
        }
        Key key = keyStore.getKey(nextElement, str2.toCharArray());
        r0 = key instanceof PrivateKey ? new PrivateKeyAndCertificates((PrivateKey) key, new X509Certificate[]{(X509Certificate) keyStore.getCertificate(nextElement)}) : null;
        fileInputStream.close();
        fileInputStream.close();
        return r0;
    }

    public static void log(String str, String str2) {
        int min;
        int length = str2.length();
        int i8 = 0;
        while (i8 < length) {
            int indexOf = str2.indexOf(10, i8);
            if (indexOf == -1) {
                indexOf = length;
            }
            while (true) {
                min = Math.min(indexOf, i8 + 4000);
                Log.d(str, str2.substring(i8, min));
                if (min >= indexOf) {
                    break;
                }
                i8 = min;
            }
            i8 = min + 1;
        }
    }

    public static HttpURLConnection makeHttpRequest(String str, String str2, Map<String, String> map) {
        HttpURLConnection httpURLConnection;
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        } catch (Exception e8) {
            e = e8;
            httpURLConnection = null;
        }
        try {
            httpURLConnection.setRequestMethod(str2);
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setInstanceFollowRedirects(true);
            if ("GET".equalsIgnoreCase(str2)) {
                httpURLConnection.setDoOutput(false);
            }
            httpURLConnection.connect();
            return httpURLConnection;
        } catch (Exception e9) {
            e = e9;
            if (!(e instanceof SSLHandshakeException)) {
                Log.e(LOG_TAG, "", e);
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return null;
        }
    }

    public static String normalizeIPv6(String str) {
        if (isIPv6(str)) {
            return InetAddress.getByName(str).getCanonicalHostName();
        }
        throw new Exception("Invalid address: " + str);
    }

    public static boolean objEquals(Object obj, Object obj2) {
        return Objects.equals(obj, obj2);
    }

    public static byte[] readAllBytes(InputStream inputStream) {
        byte[] bArr = null;
        if (inputStream == null) {
            return null;
        }
        byte[] bArr2 = new byte[4096];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            try {
                int read = inputStream.read(bArr2, 0, 4096);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr2, 0, read);
            } catch (IOException e8) {
                try {
                    inputStream.close();
                } catch (IOException e9) {
                    e8.addSuppressed(e9);
                }
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e10) {
                    e8.addSuppressed(e10);
                }
            } catch (Throwable th) {
                try {
                    inputStream.close();
                } catch (IOException unused) {
                }
                try {
                    byteArrayOutputStream.close();
                } catch (IOException unused2) {
                }
                throw th;
            }
        }
        bArr = byteArrayOutputStream.toByteArray();
        try {
            inputStream.close();
        } catch (IOException unused3) {
        }
        try {
            byteArrayOutputStream.close();
        } catch (IOException unused4) {
            return bArr;
        }
    }

    public static String replaceAll(String str, String str2, String str3) {
        return TextUtils.join(str3, str.split(Pattern.quote(str2)));
    }
}
