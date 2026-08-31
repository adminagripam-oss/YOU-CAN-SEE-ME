package com.google.android.datatransport.cct;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import t2.j;
import t2.k;
import t2.l;
import t2.m;
import t2.n;
import t2.o;
import t2.p;
import u2.g;
import u2.h;
import v2.f;
import v2.m;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class d implements m {

    /* renamed from: a  reason: collision with root package name */
    private final d5.a f4925a;

    /* renamed from: b  reason: collision with root package name */
    private final ConnectivityManager f4926b;

    /* renamed from: c  reason: collision with root package name */
    private final Context f4927c;

    /* renamed from: d  reason: collision with root package name */
    final URL f4928d;

    /* renamed from: e  reason: collision with root package name */
    private final d3.a f4929e;

    /* renamed from: f  reason: collision with root package name */
    private final d3.a f4930f;

    /* renamed from: g  reason: collision with root package name */
    private final int f4931g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        final URL f4932a;

        /* renamed from: b  reason: collision with root package name */
        final j f4933b;

        /* renamed from: c  reason: collision with root package name */
        final String f4934c;

        a(URL url, j jVar, String str) {
            this.f4932a = url;
            this.f4933b = jVar;
            this.f4934c = str;
        }

        a a(URL url) {
            return new a(url, this.f4933b, this.f4934c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        final int f4935a;

        /* renamed from: b  reason: collision with root package name */
        final URL f4936b;

        /* renamed from: c  reason: collision with root package name */
        final long f4937c;

        b(int i8, URL url, long j8) {
            this.f4935a = i8;
            this.f4936b = url;
            this.f4937c = j8;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(Context context, d3.a aVar, d3.a aVar2) {
        this(context, aVar, aVar2, 40000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public b d(a aVar) {
        x2.a.a("CctTransportBackend", "Making request to: %s", aVar.f4932a);
        HttpURLConnection httpURLConnection = (HttpURLConnection) aVar.f4932a.openConnection();
        httpURLConnection.setConnectTimeout(30000);
        httpURLConnection.setReadTimeout(this.f4931g);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("User-Agent", String.format("datatransport/%s android/", "2.3.3"));
        httpURLConnection.setRequestProperty("Content-Encoding", "gzip");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");
        String str = aVar.f4934c;
        if (str != null) {
            httpURLConnection.setRequestProperty("X-Goog-Api-Key", str);
        }
        try {
            OutputStream outputStream = httpURLConnection.getOutputStream();
            try {
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(outputStream);
                this.f4925a.b(aVar.f4933b, new BufferedWriter(new OutputStreamWriter(gZIPOutputStream)));
                gZIPOutputStream.close();
                if (outputStream != null) {
                    outputStream.close();
                }
                int responseCode = httpURLConnection.getResponseCode();
                x2.a.e("CctTransportBackend", "Status Code: " + responseCode);
                x2.a.e("CctTransportBackend", "Content-Type: " + httpURLConnection.getHeaderField("Content-Type"));
                x2.a.e("CctTransportBackend", "Content-Encoding: " + httpURLConnection.getHeaderField("Content-Encoding"));
                if (responseCode == 302 || responseCode == 301 || responseCode == 307) {
                    return new b(responseCode, new URL(httpURLConnection.getHeaderField("Location")), 0L);
                }
                if (responseCode != 200) {
                    return new b(responseCode, null, 0L);
                }
                InputStream inputStream = httpURLConnection.getInputStream();
                try {
                    InputStream l8 = l(inputStream, httpURLConnection.getHeaderField("Content-Encoding"));
                    b bVar = new b(responseCode, null, n.b(new BufferedReader(new InputStreamReader(l8))).c());
                    if (l8 != null) {
                        l8.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    return bVar;
                } catch (Throwable th) {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable unused) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable unused2) {
                    }
                }
                throw th2;
            }
        } catch (d5.b e8) {
            e = e8;
            x2.a.c("CctTransportBackend", "Couldn't encode request, returning with 400", e);
            return new b(400, null, 0L);
        } catch (ConnectException e9) {
            e = e9;
            x2.a.c("CctTransportBackend", "Couldn't open connection, returning with 500", e);
            return new b(500, null, 0L);
        } catch (UnknownHostException e10) {
            e = e10;
            x2.a.c("CctTransportBackend", "Couldn't open connection, returning with 500", e);
            return new b(500, null, 0L);
        } catch (IOException e11) {
            e = e11;
            x2.a.c("CctTransportBackend", "Couldn't encode request, returning with 400", e);
            return new b(400, null, 0L);
        }
    }

    private static int e(NetworkInfo networkInfo) {
        o.b bVar;
        if (networkInfo == null) {
            bVar = o.b.UNKNOWN_MOBILE_SUBTYPE;
        } else {
            int subtype = networkInfo.getSubtype();
            if (subtype != -1) {
                if (o.b.d(subtype) != null) {
                    return subtype;
                }
                return 0;
            }
            bVar = o.b.COMBINED;
        }
        return bVar.e();
    }

    private static int f(NetworkInfo networkInfo) {
        return networkInfo == null ? o.c.NONE.e() : networkInfo.getType();
    }

    private static int g(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e8) {
            x2.a.c("CctTransportBackend", "Unable to find version code for package", e8);
            return -1;
        }
    }

    private j h(f fVar) {
        l.a j8;
        HashMap hashMap = new HashMap();
        for (h hVar : fVar.b()) {
            String j9 = hVar.j();
            if (hashMap.containsKey(j9)) {
                ((List) hashMap.get(j9)).add(hVar);
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(hVar);
                hashMap.put(j9, arrayList);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry entry : hashMap.entrySet()) {
            h hVar2 = (h) ((List) entry.getValue()).get(0);
            m.a b8 = t2.m.a().f(p.DEFAULT).g(this.f4930f.a()).h(this.f4929e.a()).b(k.a().c(k.b.ANDROID_FIREBASE).b(t2.a.a().m(Integer.valueOf(hVar2.g("sdk-version"))).j(hVar2.b("model")).f(hVar2.b("hardware")).d(hVar2.b("device")).l(hVar2.b("product")).k(hVar2.b("os-uild")).h(hVar2.b("manufacturer")).e(hVar2.b("fingerprint")).c(hVar2.b("country")).g(hVar2.b("locale")).i(hVar2.b("mcc_mnc")).b(hVar2.b("application_build")).a()).a());
            try {
                b8.i(Integer.parseInt((String) entry.getKey()));
            } catch (NumberFormatException unused) {
                b8.j((String) entry.getKey());
            }
            ArrayList arrayList3 = new ArrayList();
            for (h hVar3 : (List) entry.getValue()) {
                g e8 = hVar3.e();
                s2.b b9 = e8.b();
                if (b9.equals(s2.b.b("proto"))) {
                    j8 = l.j(e8.a());
                } else if (b9.equals(s2.b.b("json"))) {
                    j8 = l.i(new String(e8.a(), Charset.forName("UTF-8")));
                } else {
                    x2.a.f("CctTransportBackend", "Received event of unsupported encoding %s. Skipping...", b9);
                }
                j8.c(hVar3.f()).d(hVar3.k()).h(hVar3.h("tz-offset")).e(o.a().c(o.c.d(hVar3.g("net-type"))).b(o.b.d(hVar3.g("mobile-subtype"))).a());
                if (hVar3.d() != null) {
                    j8.b(hVar3.d());
                }
                arrayList3.add(j8.a());
            }
            b8.c(arrayList3);
            arrayList2.add(b8.a());
        }
        return j.a(arrayList2);
    }

    private static TelephonyManager i(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }

    static long j() {
        Calendar.getInstance();
        return TimeZone.getDefault().getOffset(Calendar.getInstance().getTimeInMillis()) / 1000;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ a k(a aVar, b bVar) {
        URL url = bVar.f4936b;
        if (url != null) {
            x2.a.a("CctTransportBackend", "Following redirect to: %s", url);
            return aVar.a(bVar.f4936b);
        }
        return null;
    }

    private static InputStream l(InputStream inputStream, String str) {
        return "gzip".equals(str) ? new GZIPInputStream(inputStream) : inputStream;
    }

    private static URL m(String str) {
        try {
            return new URL(str);
        } catch (MalformedURLException e8) {
            throw new IllegalArgumentException("Invalid url: " + str, e8);
        }
    }

    @Override // v2.m
    public v2.g a(f fVar) {
        j h8 = h(fVar);
        URL url = this.f4928d;
        if (fVar.c() != null) {
            try {
                com.google.android.datatransport.cct.a d8 = com.google.android.datatransport.cct.a.d(fVar.c());
                r3 = d8.e() != null ? d8.e() : null;
                if (d8.f() != null) {
                    url = m(d8.f());
                }
            } catch (IllegalArgumentException unused) {
                return v2.g.a();
            }
        }
        try {
            b bVar = (b) y2.b.a(5, new a(url, h8, r3), com.google.android.datatransport.cct.b.a(this), c.b());
            int i8 = bVar.f4935a;
            if (i8 == 200) {
                return v2.g.d(bVar.f4937c);
            }
            if (i8 < 500 && i8 != 404) {
                return v2.g.a();
            }
            return v2.g.e();
        } catch (IOException e8) {
            x2.a.c("CctTransportBackend", "Could not make request to the backend", e8);
            return v2.g.e();
        }
    }

    @Override // v2.m
    public h b(h hVar) {
        NetworkInfo activeNetworkInfo = this.f4926b.getActiveNetworkInfo();
        return hVar.l().a("sdk-version", Build.VERSION.SDK_INT).c("model", Build.MODEL).c("hardware", Build.HARDWARE).c("device", Build.DEVICE).c("product", Build.PRODUCT).c("os-uild", Build.ID).c("manufacturer", Build.MANUFACTURER).c("fingerprint", Build.FINGERPRINT).b("tz-offset", j()).a("net-type", f(activeNetworkInfo)).a("mobile-subtype", e(activeNetworkInfo)).c("country", Locale.getDefault().getCountry()).c("locale", Locale.getDefault().getLanguage()).c("mcc_mnc", i(this.f4927c).getSimOperator()).c("application_build", Integer.toString(g(this.f4927c))).d();
    }

    d(Context context, d3.a aVar, d3.a aVar2, int i8) {
        this.f4925a = j.b();
        this.f4927c = context;
        this.f4926b = (ConnectivityManager) context.getSystemService("connectivity");
        this.f4928d = m(com.google.android.datatransport.cct.a.f4915c);
        this.f4929e = aVar2;
        this.f4930f = aVar;
        this.f4931g = i8;
    }
}
