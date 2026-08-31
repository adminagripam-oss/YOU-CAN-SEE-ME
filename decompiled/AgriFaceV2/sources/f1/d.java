package f1;

import android.content.ContentProviderClient;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.RemoteException;
import android.util.Log;
import f1.g;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class d {

    /* renamed from: a  reason: collision with root package name */
    private static final Comparator f6685a = new Comparator() { // from class: f1.c
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            int g8;
            g8 = d.g((byte[]) obj, (byte[]) obj2);
            return g8;
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface a {
        static a a(Context context, Uri uri) {
            return new b(context, uri);
        }

        Cursor b(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal);

        void close();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b implements a {

        /* renamed from: a  reason: collision with root package name */
        private final ContentProviderClient f6686a;

        b(Context context, Uri uri) {
            this.f6686a = context.getContentResolver().acquireUnstableContentProviderClient(uri);
        }

        @Override // f1.d.a
        public Cursor b(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
            ContentProviderClient contentProviderClient = this.f6686a;
            if (contentProviderClient == null) {
                return null;
            }
            try {
                return contentProviderClient.query(uri, strArr, str, strArr2, str2, cancellationSignal);
            } catch (RemoteException e8) {
                Log.w("FontsProvider", "Unable to query the content provider", e8);
                return null;
            }
        }

        @Override // f1.d.a
        public void close() {
            ContentProviderClient contentProviderClient = this.f6686a;
            if (contentProviderClient != null) {
                contentProviderClient.close();
            }
        }
    }

    private static List b(Signature[] signatureArr) {
        ArrayList arrayList = new ArrayList();
        for (Signature signature : signatureArr) {
            arrayList.add(signature.toByteArray());
        }
        return arrayList;
    }

    private static boolean c(List list, List list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i8 = 0; i8 < list.size(); i8++) {
            if (!Arrays.equals((byte[]) list.get(i8), (byte[]) list2.get(i8))) {
                return false;
            }
        }
        return true;
    }

    private static List d(e eVar, Resources resources) {
        return eVar.b() != null ? eVar.b() : androidx.core.content.res.d.c(resources, eVar.c());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static g.a e(Context context, e eVar, CancellationSignal cancellationSignal) {
        ProviderInfo f8 = f(context.getPackageManager(), eVar, context.getResources());
        return f8 == null ? g.a.a(1, null) : g.a.a(0, h(context, eVar, f8.authority, cancellationSignal));
    }

    static ProviderInfo f(PackageManager packageManager, e eVar, Resources resources) {
        String e8 = eVar.e();
        ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(e8, 0);
        if (resolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException("No package found for authority: " + e8);
        } else if (!resolveContentProvider.packageName.equals(eVar.f())) {
            throw new PackageManager.NameNotFoundException("Found content provider " + e8 + ", but package was not " + eVar.f());
        } else {
            List b8 = b(packageManager.getPackageInfo(resolveContentProvider.packageName, 64).signatures);
            Collections.sort(b8, f6685a);
            List d8 = d(eVar, resources);
            for (int i8 = 0; i8 < d8.size(); i8++) {
                ArrayList arrayList = new ArrayList((Collection) d8.get(i8));
                Collections.sort(arrayList, f6685a);
                if (c(b8, arrayList)) {
                    return resolveContentProvider;
                }
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int g(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            return bArr.length - bArr2.length;
        }
        for (int i8 = 0; i8 < bArr.length; i8++) {
            byte b8 = bArr[i8];
            byte b9 = bArr2[i8];
            if (b8 != b9) {
                return b8 - b9;
            }
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00fa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static f1.g.b[] h(android.content.Context r21, f1.e r22, java.lang.String r23, android.os.CancellationSignal r24) {
        /*
            Method dump skipped, instructions count: 276
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: f1.d.h(android.content.Context, f1.e, java.lang.String, android.os.CancellationSignal):f1.g$b[]");
    }
}
