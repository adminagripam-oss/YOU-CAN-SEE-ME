package androidx.core.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Person;
import android.app.RemoteInput;
import android.content.Context;
import android.content.LocusId;
import android.graphics.drawable.Icon;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import androidx.appcompat.app.w;
import androidx.core.app.j;
import androidx.core.graphics.drawable.IconCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
class k implements i {

    /* renamed from: a  reason: collision with root package name */
    private final Context f2884a;

    /* renamed from: b  reason: collision with root package name */
    private final Notification.Builder f2885b;

    /* renamed from: c  reason: collision with root package name */
    private final j.d f2886c;

    /* renamed from: d  reason: collision with root package name */
    private RemoteViews f2887d;

    /* renamed from: e  reason: collision with root package name */
    private RemoteViews f2888e;

    /* renamed from: f  reason: collision with root package name */
    private final List f2889f = new ArrayList();

    /* renamed from: g  reason: collision with root package name */
    private final Bundle f2890g = new Bundle();

    /* renamed from: h  reason: collision with root package name */
    private int f2891h;

    /* renamed from: i  reason: collision with root package name */
    private RemoteViews f2892i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a {
        static Notification.Builder a(Notification.Builder builder, Notification.Action action) {
            return builder.addAction(action);
        }

        static Notification.Action.Builder b(Notification.Action.Builder builder, Bundle bundle) {
            return builder.addExtras(bundle);
        }

        static Notification.Action.Builder c(Notification.Action.Builder builder, RemoteInput remoteInput) {
            return builder.addRemoteInput(remoteInput);
        }

        static Notification.Action d(Notification.Action.Builder builder) {
            return builder.build();
        }

        static Notification.Action.Builder e(int i8, CharSequence charSequence, PendingIntent pendingIntent) {
            return new Notification.Action.Builder(i8, charSequence, pendingIntent);
        }

        static String f(Notification notification) {
            return notification.getGroup();
        }

        static Notification.Builder g(Notification.Builder builder, String str) {
            return builder.setGroup(str);
        }

        static Notification.Builder h(Notification.Builder builder, boolean z7) {
            return builder.setGroupSummary(z7);
        }

        static Notification.Builder i(Notification.Builder builder, boolean z7) {
            return builder.setLocalOnly(z7);
        }

        static Notification.Builder j(Notification.Builder builder, String str) {
            return builder.setSortKey(str);
        }
    }

    /* loaded from: classes.dex */
    static class b {
        static Notification.Builder a(Notification.Builder builder, String str) {
            return builder.addPerson(str);
        }

        static Notification.Builder b(Notification.Builder builder, String str) {
            return builder.setCategory(str);
        }

        static Notification.Builder c(Notification.Builder builder, int i8) {
            return builder.setColor(i8);
        }

        static Notification.Builder d(Notification.Builder builder, Notification notification) {
            return builder.setPublicVersion(notification);
        }

        static Notification.Builder e(Notification.Builder builder, Uri uri, Object obj) {
            return builder.setSound(uri, (AudioAttributes) obj);
        }

        static Notification.Builder f(Notification.Builder builder, int i8) {
            return builder.setVisibility(i8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class c {
        static Notification.Action.Builder a(Icon icon, CharSequence charSequence, PendingIntent pendingIntent) {
            return new Notification.Action.Builder(icon, charSequence, pendingIntent);
        }

        static Notification.Builder b(Notification.Builder builder, Icon icon) {
            return builder.setLargeIcon(icon);
        }

        static Notification.Builder c(Notification.Builder builder, Object obj) {
            return builder.setSmallIcon((Icon) obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class d {
        static Notification.Action.Builder a(Notification.Action.Builder builder, boolean z7) {
            return builder.setAllowGeneratedReplies(z7);
        }

        static Notification.Builder b(Notification.Builder builder, RemoteViews remoteViews) {
            return builder.setCustomBigContentView(remoteViews);
        }

        static Notification.Builder c(Notification.Builder builder, RemoteViews remoteViews) {
            return builder.setCustomContentView(remoteViews);
        }

        static Notification.Builder d(Notification.Builder builder, RemoteViews remoteViews) {
            return builder.setCustomHeadsUpContentView(remoteViews);
        }

        static Notification.Builder e(Notification.Builder builder, CharSequence[] charSequenceArr) {
            return builder.setRemoteInputHistory(charSequenceArr);
        }
    }

    /* loaded from: classes.dex */
    static class e {
        static Notification.Builder a(Context context, String str) {
            return new Notification.Builder(context, str);
        }

        static Notification.Builder b(Notification.Builder builder, int i8) {
            return builder.setBadgeIconType(i8);
        }

        static Notification.Builder c(Notification.Builder builder, boolean z7) {
            return builder.setColorized(z7);
        }

        static Notification.Builder d(Notification.Builder builder, int i8) {
            return builder.setGroupAlertBehavior(i8);
        }

        static Notification.Builder e(Notification.Builder builder, CharSequence charSequence) {
            return builder.setSettingsText(charSequence);
        }

        static Notification.Builder f(Notification.Builder builder, String str) {
            return builder.setShortcutId(str);
        }

        static Notification.Builder g(Notification.Builder builder, long j8) {
            return builder.setTimeoutAfter(j8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class f {
        static Notification.Builder a(Notification.Builder builder, Person person) {
            return builder.addPerson(person);
        }

        static Notification.Action.Builder b(Notification.Action.Builder builder, int i8) {
            return builder.setSemanticAction(i8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class g {
        static Notification.Builder a(Notification.Builder builder, boolean z7) {
            return builder.setAllowSystemGeneratedContextualActions(z7);
        }

        static Notification.Builder b(Notification.Builder builder, Notification.BubbleMetadata bubbleMetadata) {
            return builder.setBubbleMetadata(bubbleMetadata);
        }

        static Notification.Action.Builder c(Notification.Action.Builder builder, boolean z7) {
            return builder.setContextual(z7);
        }

        static Notification.Builder d(Notification.Builder builder, Object obj) {
            return builder.setLocusId((LocusId) obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class h {
        static Notification.Action.Builder a(Notification.Action.Builder builder, boolean z7) {
            return builder.setAuthenticationRequired(z7);
        }

        static Notification.Builder b(Notification.Builder builder, int i8) {
            return builder.setForegroundServiceBehavior(i8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public k(j.d dVar) {
        int i8;
        this.f2886c = dVar;
        Context context = dVar.f2854a;
        this.f2884a = context;
        Notification.Builder a8 = e.a(context, dVar.K);
        this.f2885b = a8;
        Notification notification = dVar.R;
        a8.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, dVar.f2862i).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(dVar.f2858e).setContentText(dVar.f2859f).setContentInfo(dVar.f2864k).setContentIntent(dVar.f2860g).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(dVar.f2861h, (notification.flags & 128) != 0).setNumber(dVar.f2865l).setProgress(dVar.f2873t, dVar.f2874u, dVar.f2875v);
        IconCompat iconCompat = dVar.f2863j;
        c.b(a8, iconCompat == null ? null : iconCompat.j(context));
        a8.setSubText(dVar.f2870q).setUsesChronometer(dVar.f2868o).setPriority(dVar.f2866m);
        Iterator it = dVar.f2855b.iterator();
        while (it.hasNext()) {
            b((j.a) it.next());
        }
        Bundle bundle = dVar.D;
        if (bundle != null) {
            this.f2890g.putAll(bundle);
        }
        int i9 = Build.VERSION.SDK_INT;
        this.f2887d = dVar.H;
        this.f2888e = dVar.I;
        this.f2885b.setShowWhen(dVar.f2867n);
        a.i(this.f2885b, dVar.f2879z);
        a.g(this.f2885b, dVar.f2876w);
        a.j(this.f2885b, dVar.f2878y);
        a.h(this.f2885b, dVar.f2877x);
        this.f2891h = dVar.O;
        b.b(this.f2885b, dVar.C);
        b.c(this.f2885b, dVar.E);
        b.f(this.f2885b, dVar.F);
        b.d(this.f2885b, dVar.G);
        b.e(this.f2885b, notification.sound, notification.audioAttributes);
        List<String> e8 = i9 < 28 ? e(f(dVar.f2856c), dVar.U) : dVar.U;
        if (e8 != null && !e8.isEmpty()) {
            for (String str : e8) {
                b.a(this.f2885b, str);
            }
        }
        this.f2892i = dVar.J;
        if (dVar.f2857d.size() > 0) {
            Bundle bundle2 = dVar.c().getBundle("android.car.EXTENSIONS");
            bundle2 = bundle2 == null ? new Bundle() : bundle2;
            Bundle bundle3 = new Bundle(bundle2);
            Bundle bundle4 = new Bundle();
            for (int i10 = 0; i10 < dVar.f2857d.size(); i10++) {
                bundle4.putBundle(Integer.toString(i10), l.a((j.a) dVar.f2857d.get(i10)));
            }
            bundle2.putBundle("invisible_actions", bundle4);
            bundle3.putBundle("invisible_actions", bundle4);
            dVar.c().putBundle("android.car.EXTENSIONS", bundle2);
            this.f2890g.putBundle("android.car.EXTENSIONS", bundle3);
        }
        int i11 = Build.VERSION.SDK_INT;
        Object obj = dVar.T;
        if (obj != null) {
            c.c(this.f2885b, obj);
        }
        this.f2885b.setExtras(dVar.D);
        d.e(this.f2885b, dVar.f2872s);
        RemoteViews remoteViews = dVar.H;
        if (remoteViews != null) {
            d.c(this.f2885b, remoteViews);
        }
        RemoteViews remoteViews2 = dVar.I;
        if (remoteViews2 != null) {
            d.b(this.f2885b, remoteViews2);
        }
        RemoteViews remoteViews3 = dVar.J;
        if (remoteViews3 != null) {
            d.d(this.f2885b, remoteViews3);
        }
        e.b(this.f2885b, dVar.L);
        e.e(this.f2885b, dVar.f2871r);
        e.f(this.f2885b, dVar.M);
        e.g(this.f2885b, dVar.N);
        e.d(this.f2885b, dVar.O);
        if (dVar.B) {
            e.c(this.f2885b, dVar.A);
        }
        if (!TextUtils.isEmpty(dVar.K)) {
            this.f2885b.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
        }
        if (i11 >= 28) {
            Iterator it2 = dVar.f2856c.iterator();
            if (it2.hasNext()) {
                w.a(it2.next());
                throw null;
            }
        }
        if (i11 >= 29) {
            g.a(this.f2885b, dVar.Q);
            g.b(this.f2885b, j.c.a(null));
        }
        if (i11 >= 31 && (i8 = dVar.P) != 0) {
            h.b(this.f2885b, i8);
        }
        if (dVar.S) {
            if (this.f2886c.f2877x) {
                this.f2891h = 2;
            } else {
                this.f2891h = 1;
            }
            this.f2885b.setVibrate(null);
            this.f2885b.setSound(null);
            int i12 = notification.defaults & (-2) & (-3);
            notification.defaults = i12;
            this.f2885b.setDefaults(i12);
            if (TextUtils.isEmpty(this.f2886c.f2876w)) {
                a.g(this.f2885b, "silent");
            }
            e.d(this.f2885b, this.f2891h);
        }
    }

    private void b(j.a aVar) {
        IconCompat d8 = aVar.d();
        Notification.Action.Builder a8 = c.a(d8 != null ? d8.i() : null, aVar.h(), aVar.a());
        if (aVar.e() != null) {
            for (RemoteInput remoteInput : q.b(aVar.e())) {
                a.c(a8, remoteInput);
            }
        }
        Bundle bundle = aVar.c() != null ? new Bundle(aVar.c()) : new Bundle();
        bundle.putBoolean("android.support.allowGeneratedReplies", aVar.b());
        int i8 = Build.VERSION.SDK_INT;
        d.a(a8, aVar.b());
        bundle.putInt("android.support.action.semanticAction", aVar.f());
        if (i8 >= 28) {
            f.b(a8, aVar.f());
        }
        if (i8 >= 29) {
            g.c(a8, aVar.j());
        }
        if (i8 >= 31) {
            h.a(a8, aVar.i());
        }
        bundle.putBoolean("android.support.action.showsUserInterface", aVar.g());
        a.b(a8, bundle);
        a.a(this.f2885b, a.d(a8));
    }

    private static List e(List list, List list2) {
        if (list == null) {
            return list2;
        }
        if (list2 == null) {
            return list;
        }
        b1.b bVar = new b1.b(list.size() + list2.size());
        bVar.addAll(list);
        bVar.addAll(list2);
        return new ArrayList(bVar);
    }

    private static List f(List list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        if (it.hasNext()) {
            w.a(it.next());
            throw null;
        }
        return arrayList;
    }

    @Override // androidx.core.app.i
    public Notification.Builder a() {
        return this.f2885b;
    }

    public Notification c() {
        Bundle a8;
        RemoteViews f8;
        RemoteViews d8;
        j.e eVar = this.f2886c.f2869p;
        if (eVar != null) {
            eVar.b(this);
        }
        RemoteViews e8 = eVar != null ? eVar.e(this) : null;
        Notification d9 = d();
        if (e8 != null || (e8 = this.f2886c.H) != null) {
            d9.contentView = e8;
        }
        if (eVar != null && (d8 = eVar.d(this)) != null) {
            d9.bigContentView = d8;
        }
        if (eVar != null && (f8 = this.f2886c.f2869p.f(this)) != null) {
            d9.headsUpContentView = f8;
        }
        if (eVar != null && (a8 = j.a(d9)) != null) {
            eVar.a(a8);
        }
        return d9;
    }

    protected Notification d() {
        return this.f2885b.build();
    }
}
