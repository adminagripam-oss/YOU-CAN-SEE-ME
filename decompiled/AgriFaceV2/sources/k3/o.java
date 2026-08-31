package k3;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public class o extends l3.a {
    public static final Parcelable.Creator<o> CREATOR = new i0();

    /* renamed from: a  reason: collision with root package name */
    private final int f8454a;

    /* renamed from: b  reason: collision with root package name */
    private final int f8455b;

    /* renamed from: c  reason: collision with root package name */
    private final int f8456c;

    /* renamed from: d  reason: collision with root package name */
    private final long f8457d;

    /* renamed from: e  reason: collision with root package name */
    private final long f8458e;

    /* renamed from: f  reason: collision with root package name */
    private final String f8459f;

    /* renamed from: g  reason: collision with root package name */
    private final String f8460g;

    /* renamed from: h  reason: collision with root package name */
    private final int f8461h;

    /* renamed from: i  reason: collision with root package name */
    private final int f8462i;

    public o(int i8, int i9, int i10, long j8, long j9, String str, String str2, int i11) {
        this(i8, i9, i10, j8, j9, str, str2, i11, -1);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int i9 = this.f8454a;
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 1, i9);
        l3.c.g(parcel, 2, this.f8455b);
        l3.c.g(parcel, 3, this.f8456c);
        l3.c.i(parcel, 4, this.f8457d);
        l3.c.i(parcel, 5, this.f8458e);
        l3.c.k(parcel, 6, this.f8459f, false);
        l3.c.k(parcel, 7, this.f8460g, false);
        l3.c.g(parcel, 8, this.f8461h);
        l3.c.g(parcel, 9, this.f8462i);
        l3.c.b(parcel, a8);
    }

    public o(int i8, int i9, int i10, long j8, long j9, String str, String str2, int i11, int i12) {
        this.f8454a = i8;
        this.f8455b = i9;
        this.f8456c = i10;
        this.f8457d = j8;
        this.f8458e = j9;
        this.f8459f = str;
        this.f8460g = str2;
        this.f8461h = i11;
        this.f8462i = i12;
    }
}
