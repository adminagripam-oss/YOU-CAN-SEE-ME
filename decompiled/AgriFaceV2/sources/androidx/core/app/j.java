package androidx.core.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.widget.RemoteViews;
import androidx.core.graphics.drawable.IconCompat;
import java.util.ArrayList;
/* loaded from: classes.dex */
public abstract class j {

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        final Bundle f2841a;

        /* renamed from: b  reason: collision with root package name */
        private IconCompat f2842b;

        /* renamed from: c  reason: collision with root package name */
        private final q[] f2843c;

        /* renamed from: d  reason: collision with root package name */
        private final q[] f2844d;

        /* renamed from: e  reason: collision with root package name */
        private boolean f2845e;

        /* renamed from: f  reason: collision with root package name */
        boolean f2846f;

        /* renamed from: g  reason: collision with root package name */
        private final int f2847g;

        /* renamed from: h  reason: collision with root package name */
        private final boolean f2848h;

        /* renamed from: i  reason: collision with root package name */
        public int f2849i;

        /* renamed from: j  reason: collision with root package name */
        public CharSequence f2850j;

        /* renamed from: k  reason: collision with root package name */
        public PendingIntent f2851k;

        /* renamed from: l  reason: collision with root package name */
        private boolean f2852l;

        public a(int i8, CharSequence charSequence, PendingIntent pendingIntent) {
            this(i8 != 0 ? IconCompat.a(null, "", i8) : null, charSequence, pendingIntent);
        }

        public PendingIntent a() {
            return this.f2851k;
        }

        public boolean b() {
            return this.f2845e;
        }

        public Bundle c() {
            return this.f2841a;
        }

        public IconCompat d() {
            int i8;
            if (this.f2842b == null && (i8 = this.f2849i) != 0) {
                this.f2842b = IconCompat.a(null, "", i8);
            }
            return this.f2842b;
        }

        public q[] e() {
            return this.f2843c;
        }

        public int f() {
            return this.f2847g;
        }

        public boolean g() {
            return this.f2846f;
        }

        public CharSequence h() {
            return this.f2850j;
        }

        public boolean i() {
            return this.f2852l;
        }

        public boolean j() {
            return this.f2848h;
        }

        public a(IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent) {
            this(iconCompat, charSequence, pendingIntent, new Bundle(), null, null, true, 0, true, false, false);
        }

        a(IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, q[] qVarArr, q[] qVarArr2, boolean z7, int i8, boolean z8, boolean z9, boolean z10) {
            this.f2846f = true;
            this.f2842b = iconCompat;
            if (iconCompat != null && iconCompat.d() == 2) {
                this.f2849i = iconCompat.b();
            }
            this.f2850j = d.d(charSequence);
            this.f2851k = pendingIntent;
            this.f2841a = bundle == null ? new Bundle() : bundle;
            this.f2843c = qVarArr;
            this.f2844d = qVarArr2;
            this.f2845e = z7;
            this.f2847g = i8;
            this.f2846f = z8;
            this.f2848h = z9;
            this.f2852l = z10;
        }
    }

    /* loaded from: classes.dex */
    public static class b extends e {

        /* renamed from: e  reason: collision with root package name */
        private CharSequence f2853e;

        @Override // androidx.core.app.j.e
        public void a(Bundle bundle) {
            super.a(bundle);
        }

        @Override // androidx.core.app.j.e
        public void b(i iVar) {
            Notification.BigTextStyle bigText = new Notification.BigTextStyle(iVar.a()).setBigContentTitle(this.f2881b).bigText(this.f2853e);
            if (this.f2883d) {
                bigText.setSummaryText(this.f2882c);
            }
        }

        @Override // androidx.core.app.j.e
        protected String c() {
            return "androidx.core.app.NotificationCompat$BigTextStyle";
        }

        public b h(CharSequence charSequence) {
            this.f2853e = d.d(charSequence);
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static final class c {
        public static Notification.BubbleMetadata a(c cVar) {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static class d {
        boolean A;
        boolean B;
        String C;
        Bundle D;
        int E;
        int F;
        Notification G;
        RemoteViews H;
        RemoteViews I;
        RemoteViews J;
        String K;
        int L;
        String M;
        long N;
        int O;
        int P;
        boolean Q;
        Notification R;
        boolean S;
        Object T;
        public ArrayList U;

        /* renamed from: a  reason: collision with root package name */
        public Context f2854a;

        /* renamed from: b  reason: collision with root package name */
        public ArrayList f2855b;

        /* renamed from: c  reason: collision with root package name */
        public ArrayList f2856c;

        /* renamed from: d  reason: collision with root package name */
        ArrayList f2857d;

        /* renamed from: e  reason: collision with root package name */
        CharSequence f2858e;

        /* renamed from: f  reason: collision with root package name */
        CharSequence f2859f;

        /* renamed from: g  reason: collision with root package name */
        PendingIntent f2860g;

        /* renamed from: h  reason: collision with root package name */
        PendingIntent f2861h;

        /* renamed from: i  reason: collision with root package name */
        RemoteViews f2862i;

        /* renamed from: j  reason: collision with root package name */
        IconCompat f2863j;

        /* renamed from: k  reason: collision with root package name */
        CharSequence f2864k;

        /* renamed from: l  reason: collision with root package name */
        int f2865l;

        /* renamed from: m  reason: collision with root package name */
        int f2866m;

        /* renamed from: n  reason: collision with root package name */
        boolean f2867n;

        /* renamed from: o  reason: collision with root package name */
        boolean f2868o;

        /* renamed from: p  reason: collision with root package name */
        e f2869p;

        /* renamed from: q  reason: collision with root package name */
        CharSequence f2870q;

        /* renamed from: r  reason: collision with root package name */
        CharSequence f2871r;

        /* renamed from: s  reason: collision with root package name */
        CharSequence[] f2872s;

        /* renamed from: t  reason: collision with root package name */
        int f2873t;

        /* renamed from: u  reason: collision with root package name */
        int f2874u;

        /* renamed from: v  reason: collision with root package name */
        boolean f2875v;

        /* renamed from: w  reason: collision with root package name */
        String f2876w;

        /* renamed from: x  reason: collision with root package name */
        boolean f2877x;

        /* renamed from: y  reason: collision with root package name */
        String f2878y;

        /* renamed from: z  reason: collision with root package name */
        boolean f2879z;

        public d(Context context) {
            this(context, null);
        }

        protected static CharSequence d(CharSequence charSequence) {
            return (charSequence != null && charSequence.length() > 5120) ? charSequence.subSequence(0, 5120) : charSequence;
        }

        private void k(int i8, boolean z7) {
            Notification notification;
            int i9;
            if (z7) {
                notification = this.R;
                i9 = i8 | notification.flags;
            } else {
                notification = this.R;
                i9 = (~i8) & notification.flags;
            }
            notification.flags = i9;
        }

        public d a(int i8, CharSequence charSequence, PendingIntent pendingIntent) {
            this.f2855b.add(new a(i8, charSequence, pendingIntent));
            return this;
        }

        public Notification b() {
            return new k(this).c();
        }

        public Bundle c() {
            if (this.D == null) {
                this.D = new Bundle();
            }
            return this.D;
        }

        public d e(boolean z7) {
            k(16, z7);
            return this;
        }

        public d f(String str) {
            this.K = str;
            return this;
        }

        public d g(int i8) {
            this.E = i8;
            return this;
        }

        public d h(PendingIntent pendingIntent) {
            this.f2860g = pendingIntent;
            return this;
        }

        public d i(CharSequence charSequence) {
            this.f2859f = d(charSequence);
            return this;
        }

        public d j(CharSequence charSequence) {
            this.f2858e = d(charSequence);
            return this;
        }

        public d l(boolean z7) {
            this.f2879z = z7;
            return this;
        }

        public d m(boolean z7) {
            k(2, z7);
            return this;
        }

        public d n(int i8) {
            this.f2866m = i8;
            return this;
        }

        public d o(int i8) {
            this.R.icon = i8;
            return this;
        }

        public d p(e eVar) {
            if (this.f2869p != eVar) {
                this.f2869p = eVar;
                if (eVar != null) {
                    eVar.g(this);
                }
            }
            return this;
        }

        public d q(CharSequence charSequence) {
            this.R.tickerText = d(charSequence);
            return this;
        }

        public d r(long j8) {
            this.R.when = j8;
            return this;
        }

        public d(Context context, String str) {
            this.f2855b = new ArrayList();
            this.f2856c = new ArrayList();
            this.f2857d = new ArrayList();
            this.f2867n = true;
            this.f2879z = false;
            this.E = 0;
            this.F = 0;
            this.L = 0;
            this.O = 0;
            this.P = 0;
            Notification notification = new Notification();
            this.R = notification;
            this.f2854a = context;
            this.K = str;
            notification.when = System.currentTimeMillis();
            this.R.audioStreamType = -1;
            this.f2866m = 0;
            this.U = new ArrayList();
            this.Q = true;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class e {

        /* renamed from: a  reason: collision with root package name */
        protected d f2880a;

        /* renamed from: b  reason: collision with root package name */
        CharSequence f2881b;

        /* renamed from: c  reason: collision with root package name */
        CharSequence f2882c;

        /* renamed from: d  reason: collision with root package name */
        boolean f2883d = false;

        public void a(Bundle bundle) {
            if (this.f2883d) {
                bundle.putCharSequence("android.summaryText", this.f2882c);
            }
            CharSequence charSequence = this.f2881b;
            if (charSequence != null) {
                bundle.putCharSequence("android.title.big", charSequence);
            }
            String c8 = c();
            if (c8 != null) {
                bundle.putString("androidx.core.app.extra.COMPAT_TEMPLATE", c8);
            }
        }

        public abstract void b(i iVar);

        protected abstract String c();

        public RemoteViews d(i iVar) {
            return null;
        }

        public RemoteViews e(i iVar) {
            return null;
        }

        public RemoteViews f(i iVar) {
            return null;
        }

        public void g(d dVar) {
            if (this.f2880a != dVar) {
                this.f2880a = dVar;
                if (dVar != null) {
                    dVar.p(this);
                }
            }
        }
    }

    public static Bundle a(Notification notification) {
        return notification.extras;
    }
}
