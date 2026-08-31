package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import i3.c;
import i3.j;
import k3.p;
import k3.q;
/* loaded from: classes.dex */
public final class Status extends l3.a implements j, ReflectedParcelable {

    /* renamed from: a  reason: collision with root package name */
    private final int f4967a;

    /* renamed from: b  reason: collision with root package name */
    private final String f4968b;

    /* renamed from: c  reason: collision with root package name */
    private final PendingIntent f4969c;

    /* renamed from: d  reason: collision with root package name */
    private final h3.a f4970d;

    /* renamed from: e  reason: collision with root package name */
    public static final Status f4959e = new Status(-1);

    /* renamed from: f  reason: collision with root package name */
    public static final Status f4960f = new Status(0);

    /* renamed from: g  reason: collision with root package name */
    public static final Status f4961g = new Status(14);

    /* renamed from: h  reason: collision with root package name */
    public static final Status f4962h = new Status(8);

    /* renamed from: i  reason: collision with root package name */
    public static final Status f4963i = new Status(15);

    /* renamed from: j  reason: collision with root package name */
    public static final Status f4964j = new Status(16);

    /* renamed from: l  reason: collision with root package name */
    public static final Status f4966l = new Status(17);

    /* renamed from: k  reason: collision with root package name */
    public static final Status f4965k = new Status(18);
    public static final Parcelable.Creator<Status> CREATOR = new b();

    public Status(int i8) {
        this(i8, (String) null);
    }

    public h3.a a() {
        return this.f4970d;
    }

    public int b() {
        return this.f4967a;
    }

    public String c() {
        return this.f4968b;
    }

    public boolean d() {
        return this.f4969c != null;
    }

    public boolean e() {
        return this.f4967a <= 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Status) {
            Status status = (Status) obj;
            return this.f4967a == status.f4967a && p.a(this.f4968b, status.f4968b) && p.a(this.f4969c, status.f4969c) && p.a(this.f4970d, status.f4970d);
        }
        return false;
    }

    public void f(Activity activity, int i8) {
        if (d()) {
            PendingIntent pendingIntent = this.f4969c;
            q.g(pendingIntent);
            activity.startIntentSenderForResult(pendingIntent.getIntentSender(), i8, null, 0, 0, 0);
        }
    }

    public final String g() {
        String str = this.f4968b;
        return str != null ? str : c.a(this.f4967a);
    }

    public int hashCode() {
        return p.b(Integer.valueOf(this.f4967a), this.f4968b, this.f4969c, this.f4970d);
    }

    public String toString() {
        p.a c8 = p.c(this);
        c8.a("statusCode", g());
        c8.a("resolution", this.f4969c);
        return c8.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 1, b());
        l3.c.k(parcel, 2, c(), false);
        l3.c.j(parcel, 3, this.f4969c, i8, false);
        l3.c.j(parcel, 4, a(), i8, false);
        l3.c.b(parcel, a8);
    }

    public Status(int i8, String str) {
        this(i8, str, (PendingIntent) null);
    }

    public Status(int i8, String str, PendingIntent pendingIntent) {
        this(i8, str, pendingIntent, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Status(int i8, String str, PendingIntent pendingIntent, h3.a aVar) {
        this.f4967a = i8;
        this.f4968b = str;
        this.f4969c = pendingIntent;
        this.f4970d = aVar;
    }

    public Status(h3.a aVar, String str) {
        this(aVar, str, 17);
    }

    public Status(h3.a aVar, String str, int i8) {
        this(i8, str, aVar.c(), aVar);
    }
}
