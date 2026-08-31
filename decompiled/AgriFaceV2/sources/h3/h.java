package h3;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.util.Log;
/* loaded from: classes.dex */
public class h {

    /* renamed from: b  reason: collision with root package name */
    private static h f7241b;

    /* renamed from: a  reason: collision with root package name */
    private final Context f7242a;

    public h(Context context) {
        this.f7242a = context.getApplicationContext();
    }

    public static h a(Context context) {
        k3.q.g(context);
        synchronized (h.class) {
            if (f7241b == null) {
                v.a(context);
                f7241b = new h(context);
            }
        }
        return f7241b;
    }

    static final r b(PackageInfo packageInfo, r... rVarArr) {
        Signature[] signatureArr = packageInfo.signatures;
        if (signatureArr != null) {
            if (signatureArr.length != 1) {
                Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
                return null;
            }
            s sVar = new s(packageInfo.signatures[0].toByteArray());
            for (int i8 = 0; i8 < rVarArr.length; i8++) {
                if (rVarArr[i8].equals(sVar)) {
                    return rVarArr[i8];
                }
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0048 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean c(android.content.pm.PackageInfo r4, boolean r5) {
        /*
            r0 = 1
            r1 = 0
            if (r5 == 0) goto L2a
            if (r4 == 0) goto L28
            java.lang.String r2 = "com.android.vending"
            java.lang.String r3 = r4.packageName
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L1a
            java.lang.String r2 = r4.packageName
            java.lang.String r3 = "com.google.android.gms"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L2a
        L1a:
            android.content.pm.ApplicationInfo r5 = r4.applicationInfo
            if (r5 != 0) goto L20
        L1e:
            r5 = r1
            goto L2a
        L20:
            int r5 = r5.flags
            r5 = r5 & 129(0x81, float:1.81E-43)
            if (r5 == 0) goto L1e
            r5 = r0
            goto L2a
        L28:
            r2 = 0
            goto L2b
        L2a:
            r2 = r4
        L2b:
            if (r4 == 0) goto L49
            android.content.pm.Signature[] r4 = r2.signatures
            if (r4 == 0) goto L49
            if (r5 == 0) goto L3a
            h3.r[] r4 = h3.u.f7253a
            h3.r r4 = b(r2, r4)
            goto L46
        L3a:
            h3.r[] r4 = new h3.r[r0]
            h3.r[] r5 = h3.u.f7253a
            r5 = r5[r1]
            r4[r1] = r5
            h3.r r4 = b(r2, r4)
        L46:
            if (r4 == 0) goto L49
            return r0
        L49:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: h3.h.c(android.content.pm.PackageInfo, boolean):boolean");
    }
}
