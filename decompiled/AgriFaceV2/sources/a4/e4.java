package a4;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class e4 extends l3.a {
    public static final Parcelable.Creator<e4> CREATOR = new f5();

    /* renamed from: a  reason: collision with root package name */
    public final int f494a;

    /* renamed from: b  reason: collision with root package name */
    public final int f495b;

    /* renamed from: c  reason: collision with root package name */
    public final float f496c;

    /* renamed from: d  reason: collision with root package name */
    public final float f497d;

    /* renamed from: e  reason: collision with root package name */
    public final float f498e;

    /* renamed from: f  reason: collision with root package name */
    public final float f499f;

    /* renamed from: g  reason: collision with root package name */
    public final float f500g;

    /* renamed from: h  reason: collision with root package name */
    public final float f501h;

    /* renamed from: i  reason: collision with root package name */
    public final float f502i;

    /* renamed from: j  reason: collision with root package name */
    public final mc[] f503j;

    /* renamed from: k  reason: collision with root package name */
    public final float f504k;

    /* renamed from: l  reason: collision with root package name */
    public final float f505l;

    /* renamed from: m  reason: collision with root package name */
    public final float f506m;

    /* renamed from: n  reason: collision with root package name */
    public final c2[] f507n;

    /* renamed from: o  reason: collision with root package name */
    public final float f508o;

    public e4(int i8, int i9, float f8, float f9, float f10, float f11, float f12, float f13, float f14, mc[] mcVarArr, float f15, float f16, float f17, c2[] c2VarArr, float f18) {
        this.f494a = i8;
        this.f495b = i9;
        this.f496c = f8;
        this.f497d = f9;
        this.f498e = f10;
        this.f499f = f11;
        this.f500g = f12;
        this.f501h = f13;
        this.f502i = f14;
        this.f503j = mcVarArr;
        this.f504k = f15;
        this.f505l = f16;
        this.f506m = f17;
        this.f507n = c2VarArr;
        this.f508o = f18;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 1, this.f494a);
        l3.c.g(parcel, 2, this.f495b);
        l3.c.e(parcel, 3, this.f496c);
        l3.c.e(parcel, 4, this.f497d);
        l3.c.e(parcel, 5, this.f498e);
        l3.c.e(parcel, 6, this.f499f);
        l3.c.e(parcel, 7, this.f500g);
        l3.c.e(parcel, 8, this.f501h);
        l3.c.m(parcel, 9, this.f503j, i8, false);
        l3.c.e(parcel, 10, this.f504k);
        l3.c.e(parcel, 11, this.f505l);
        l3.c.e(parcel, 12, this.f506m);
        l3.c.m(parcel, 13, this.f507n, i8, false);
        l3.c.e(parcel, 14, this.f502i);
        l3.c.e(parcel, 15, this.f508o);
        l3.c.b(parcel, a8);
    }
}
