package a4;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class ne extends l3.a {
    public static final Parcelable.Creator<ne> CREATOR = new se();

    /* renamed from: a  reason: collision with root package name */
    public int f872a;

    /* renamed from: b  reason: collision with root package name */
    public int f873b;

    /* renamed from: c  reason: collision with root package name */
    public int f874c;

    /* renamed from: d  reason: collision with root package name */
    public long f875d;

    /* renamed from: e  reason: collision with root package name */
    public int f876e;

    public ne(int i8, int i9, int i10, long j8, int i11) {
        this.f872a = i8;
        this.f873b = i9;
        this.f874c = i10;
        this.f875d = j8;
        this.f876e = i11;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 2, this.f872a);
        l3.c.g(parcel, 3, this.f873b);
        l3.c.g(parcel, 4, this.f874c);
        l3.c.i(parcel, 5, this.f875d);
        l3.c.g(parcel, 6, this.f876e);
        l3.c.b(parcel, a8);
    }
}
