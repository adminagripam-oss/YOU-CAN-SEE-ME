package androidx.emoji2.text;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.util.Log;
import androidx.emoji2.text.e;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public abstract class c {

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final b f3272a;

        public a(b bVar) {
            this.f3272a = bVar == null ? e() : bVar;
        }

        private e.c a(Context context, f1.e eVar) {
            if (eVar == null) {
                return null;
            }
            return new i(context, eVar);
        }

        private List b(Signature[] signatureArr) {
            ArrayList arrayList = new ArrayList();
            for (Signature signature : signatureArr) {
                arrayList.add(signature.toByteArray());
            }
            return Collections.singletonList(arrayList);
        }

        private f1.e d(ProviderInfo providerInfo, PackageManager packageManager) {
            String str = providerInfo.authority;
            String str2 = providerInfo.packageName;
            return new f1.e(str, str2, "emojicompat-emoji-font", b(this.f3272a.b(packageManager, str2)));
        }

        private static b e() {
            return Build.VERSION.SDK_INT >= 28 ? new d() : new C0035c();
        }

        private boolean f(ProviderInfo providerInfo) {
            ApplicationInfo applicationInfo;
            return (providerInfo == null || (applicationInfo = providerInfo.applicationInfo) == null || (applicationInfo.flags & 1) != 1) ? false : true;
        }

        private ProviderInfo g(PackageManager packageManager) {
            for (ResolveInfo resolveInfo : this.f3272a.c(packageManager, new Intent("androidx.content.action.LOAD_EMOJI_FONT"), 0)) {
                ProviderInfo a8 = this.f3272a.a(resolveInfo);
                if (f(a8)) {
                    return a8;
                }
            }
            return null;
        }

        public e.c c(Context context) {
            return a(context, h(context));
        }

        f1.e h(Context context) {
            PackageManager packageManager = context.getPackageManager();
            g1.e.k(packageManager, "Package manager required to locate emoji font provider");
            ProviderInfo g8 = g(packageManager);
            if (g8 == null) {
                return null;
            }
            try {
                return d(g8, packageManager);
            } catch (PackageManager.NameNotFoundException e8) {
                Log.wtf("emoji2.text.DefaultEmojiConfig", e8);
                return null;
            }
        }
    }

    /* loaded from: classes.dex */
    public static class b {
        public abstract ProviderInfo a(ResolveInfo resolveInfo);

        public Signature[] b(PackageManager packageManager, String str) {
            return packageManager.getPackageInfo(str, 64).signatures;
        }

        public abstract List c(PackageManager packageManager, Intent intent, int i8);
    }

    /* renamed from: androidx.emoji2.text.c$c  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0035c extends b {
        @Override // androidx.emoji2.text.c.b
        public ProviderInfo a(ResolveInfo resolveInfo) {
            return resolveInfo.providerInfo;
        }

        @Override // androidx.emoji2.text.c.b
        public List c(PackageManager packageManager, Intent intent, int i8) {
            return packageManager.queryIntentContentProviders(intent, i8);
        }
    }

    /* loaded from: classes.dex */
    public static class d extends C0035c {
        @Override // androidx.emoji2.text.c.b
        public Signature[] b(PackageManager packageManager, String str) {
            return packageManager.getPackageInfo(str, 64).signatures;
        }
    }

    public static i a(Context context) {
        return (i) new a(null).c(context);
    }
}
