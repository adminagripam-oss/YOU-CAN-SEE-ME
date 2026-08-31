package androidx.browser.customtabs;

import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.LocaleList;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.RemoteViews;
import androidx.browser.customtabs.a;
import java.util.ArrayList;
/* loaded from: classes.dex */
public final class d {

    /* renamed from: a  reason: collision with root package name */
    public final Intent f2596a;

    /* renamed from: b  reason: collision with root package name */
    public final Bundle f2597b;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a {
        static ActivityOptions a() {
            return ActivityOptions.makeBasic();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b {
        static String a() {
            LocaleList adjustedDefault = LocaleList.getAdjustedDefault();
            if (adjustedDefault.size() > 0) {
                return adjustedDefault.get(0).toLanguageTag();
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c {
        static void a(ActivityOptions activityOptions, boolean z7) {
            activityOptions.setShareIdentityEnabled(z7);
        }
    }

    /* renamed from: androidx.browser.customtabs.d$d  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0014d {

        /* renamed from: c  reason: collision with root package name */
        private ArrayList f2600c;

        /* renamed from: d  reason: collision with root package name */
        private ActivityOptions f2601d;

        /* renamed from: e  reason: collision with root package name */
        private ArrayList f2602e;

        /* renamed from: f  reason: collision with root package name */
        private SparseArray f2603f;

        /* renamed from: g  reason: collision with root package name */
        private Bundle f2604g;

        /* renamed from: j  reason: collision with root package name */
        private boolean f2607j;

        /* renamed from: a  reason: collision with root package name */
        private final Intent f2598a = new Intent("android.intent.action.VIEW");

        /* renamed from: b  reason: collision with root package name */
        private final a.C0011a f2599b = new a.C0011a();

        /* renamed from: h  reason: collision with root package name */
        private int f2605h = 0;

        /* renamed from: i  reason: collision with root package name */
        private boolean f2606i = true;

        public C0014d() {
        }

        private void d() {
            String a8 = b.a();
            if (TextUtils.isEmpty(a8)) {
                return;
            }
            Bundle bundleExtra = this.f2598a.hasExtra("com.android.browser.headers") ? this.f2598a.getBundleExtra("com.android.browser.headers") : new Bundle();
            if (bundleExtra.containsKey("Accept-Language")) {
                return;
            }
            bundleExtra.putString("Accept-Language", a8);
            this.f2598a.putExtra("com.android.browser.headers", bundleExtra);
        }

        private void j(IBinder iBinder, PendingIntent pendingIntent) {
            Bundle bundle = new Bundle();
            bundle.putBinder("android.support.customtabs.extra.SESSION", iBinder);
            if (pendingIntent != null) {
                bundle.putParcelable("android.support.customtabs.extra.SESSION_ID", pendingIntent);
            }
            this.f2598a.putExtras(bundle);
        }

        private void k() {
            if (this.f2601d == null) {
                this.f2601d = a.a();
            }
            c.a(this.f2601d, this.f2607j);
        }

        public C0014d a(String str, PendingIntent pendingIntent) {
            if (this.f2600c == null) {
                this.f2600c = new ArrayList();
            }
            Bundle bundle = new Bundle();
            bundle.putString("android.support.customtabs.customaction.MENU_ITEM_TITLE", str);
            bundle.putParcelable("android.support.customtabs.customaction.PENDING_INTENT", pendingIntent);
            this.f2600c.add(bundle);
            return this;
        }

        public d b() {
            if (!this.f2598a.hasExtra("android.support.customtabs.extra.SESSION")) {
                j(null, null);
            }
            ArrayList<? extends Parcelable> arrayList = this.f2600c;
            if (arrayList != null) {
                this.f2598a.putParcelableArrayListExtra("android.support.customtabs.extra.MENU_ITEMS", arrayList);
            }
            ArrayList<? extends Parcelable> arrayList2 = this.f2602e;
            if (arrayList2 != null) {
                this.f2598a.putParcelableArrayListExtra("android.support.customtabs.extra.TOOLBAR_ITEMS", arrayList2);
            }
            this.f2598a.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", this.f2606i);
            this.f2598a.putExtras(this.f2599b.a().a());
            Bundle bundle = this.f2604g;
            if (bundle != null) {
                this.f2598a.putExtras(bundle);
            }
            if (this.f2603f != null) {
                Bundle bundle2 = new Bundle();
                bundle2.putSparseParcelableArray("androidx.browser.customtabs.extra.COLOR_SCHEME_PARAMS", this.f2603f);
                this.f2598a.putExtras(bundle2);
            }
            this.f2598a.putExtra("androidx.browser.customtabs.extra.SHARE_STATE", this.f2605h);
            int i8 = Build.VERSION.SDK_INT;
            d();
            if (i8 >= 34) {
                k();
            }
            ActivityOptions activityOptions = this.f2601d;
            return new d(this.f2598a, activityOptions != null ? activityOptions.toBundle() : null);
        }

        public C0014d c(Bitmap bitmap, String str, PendingIntent pendingIntent, boolean z7) {
            Bundle bundle = new Bundle();
            bundle.putInt("android.support.customtabs.customaction.ID", 0);
            bundle.putParcelable("android.support.customtabs.customaction.ICON", bitmap);
            bundle.putString("android.support.customtabs.customaction.DESCRIPTION", str);
            bundle.putParcelable("android.support.customtabs.customaction.PENDING_INTENT", pendingIntent);
            this.f2598a.putExtra("android.support.customtabs.extra.ACTION_BUTTON_BUNDLE", bundle);
            this.f2598a.putExtra("android.support.customtabs.extra.TINT_ACTION_BUTTON", z7);
            return this;
        }

        public C0014d e(androidx.browser.customtabs.a aVar) {
            this.f2604g = aVar.a();
            return this;
        }

        public C0014d f(Context context, int i8, int i9) {
            this.f2598a.putExtra("android.support.customtabs.extra.EXIT_ANIMATION_BUNDLE", androidx.core.app.c.a(context, i8, i9).b());
            return this;
        }

        public C0014d g(boolean z7) {
            this.f2606i = z7;
            return this;
        }

        public C0014d h(RemoteViews remoteViews, int[] iArr, PendingIntent pendingIntent) {
            this.f2598a.putExtra("android.support.customtabs.extra.EXTRA_REMOTEVIEWS", remoteViews);
            this.f2598a.putExtra("android.support.customtabs.extra.EXTRA_REMOTEVIEWS_VIEW_IDS", iArr);
            this.f2598a.putExtra("android.support.customtabs.extra.EXTRA_REMOTEVIEWS_PENDINGINTENT", pendingIntent);
            return this;
        }

        public C0014d i(i iVar) {
            this.f2598a.setPackage(iVar.f().getPackageName());
            j(iVar.e(), iVar.g());
            return this;
        }

        public C0014d l(int i8) {
            if (i8 < 0 || i8 > 2) {
                throw new IllegalArgumentException("Invalid value for the shareState argument");
            }
            this.f2605h = i8;
            if (i8 == 1) {
                this.f2598a.putExtra("android.support.customtabs.extra.SHARE_MENU_ITEM", true);
            } else if (i8 == 2) {
                this.f2598a.putExtra("android.support.customtabs.extra.SHARE_MENU_ITEM", false);
            } else {
                this.f2598a.removeExtra("android.support.customtabs.extra.SHARE_MENU_ITEM");
            }
            return this;
        }

        public C0014d m(boolean z7) {
            this.f2598a.putExtra("android.support.customtabs.extra.TITLE_VISIBILITY", z7 ? 1 : 0);
            return this;
        }

        public C0014d n(Context context, int i8, int i9) {
            this.f2601d = ActivityOptions.makeCustomAnimation(context, i8, i9);
            return this;
        }

        public C0014d o(boolean z7) {
            this.f2598a.putExtra("android.support.customtabs.extra.ENABLE_URLBAR_HIDING", z7);
            return this;
        }

        public C0014d(i iVar) {
            if (iVar != null) {
                i(iVar);
            }
        }
    }

    d(Intent intent, Bundle bundle) {
        this.f2596a = intent;
        this.f2597b = bundle;
    }

    public static int a() {
        return 5;
    }

    public static Intent b(Intent intent) {
        if (intent == null) {
            intent = new Intent("android.intent.action.VIEW");
        }
        intent.addFlags(268435456);
        intent.putExtra("android.support.customtabs.extra.user_opt_out", true);
        return intent;
    }
}
