package a4;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
/* loaded from: classes.dex */
public final class je extends l3.a {
    public static final Parcelable.Creator<je> CREATOR = new ke();

    /* renamed from: a  reason: collision with root package name */
    private final int f721a;

    /* renamed from: b  reason: collision with root package name */
    private final Rect f722b;

    /* renamed from: c  reason: collision with root package name */
    private final float f723c;

    /* renamed from: d  reason: collision with root package name */
    private final float f724d;

    /* renamed from: e  reason: collision with root package name */
    private final float f725e;

    /* renamed from: f  reason: collision with root package name */
    private final float f726f;

    /* renamed from: g  reason: collision with root package name */
    private final float f727g;

    /* renamed from: h  reason: collision with root package name */
    private final float f728h;

    /* renamed from: i  reason: collision with root package name */
    private final float f729i;

    /* renamed from: j  reason: collision with root package name */
    private final List f730j;

    /* renamed from: k  reason: collision with root package name */
    private final List f731k;

    public je(int i8, Rect rect, float f8, float f9, float f10, float f11, float f12, float f13, float f14, List list, List list2) {
        this.f721a = i8;
        this.f722b = rect;
        this.f723c = f8;
        this.f724d = f9;
        this.f725e = f10;
        this.f726f = f11;
        this.f727g = f12;
        this.f728h = f13;
        this.f729i = f14;
        this.f730j = list;
        this.f731k = list2;
    }

    public final float a() {
        return this.f726f;
    }

    public final float b() {
        return this.f724d;
    }

    public final float c() {
        return this.f727g;
    }

    public final float d() {
        return this.f723c;
    }

    public final float e() {
        return this.f728h;
    }

    public final float f() {
        return this.f725e;
    }

    public final int g() {
        return this.f721a;
    }

    public final Rect h() {
        return this.f722b;
    }

    public final List i() {
        return this.f731k;
    }

    public final List j() {
        return this.f730j;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 1, this.f721a);
        l3.c.j(parcel, 2, this.f722b, i8, false);
        l3.c.e(parcel, 3, this.f723c);
        l3.c.e(parcel, 4, this.f724d);
        l3.c.e(parcel, 5, this.f725e);
        l3.c.e(parcel, 6, this.f726f);
        l3.c.e(parcel, 7, this.f727g);
        l3.c.e(parcel, 8, this.f728h);
        l3.c.e(parcel, 9, this.f729i);
        l3.c.n(parcel, 10, this.f730j, false);
        l3.c.n(parcel, 11, this.f731k, false);
        l3.c.b(parcel, a8);
    }
}
