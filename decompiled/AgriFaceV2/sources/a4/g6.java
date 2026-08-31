package a4;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class g6 extends l3.a {
    public static final Parcelable.Creator<g6> CREATOR = new h7();

    /* renamed from: a  reason: collision with root package name */
    public int f589a;

    /* renamed from: b  reason: collision with root package name */
    public int f590b;

    /* renamed from: c  reason: collision with root package name */
    public int f591c;

    /* renamed from: d  reason: collision with root package name */
    public boolean f592d;

    /* renamed from: e  reason: collision with root package name */
    public boolean f593e;

    /* renamed from: f  reason: collision with root package name */
    public float f594f;

    public g6(int i8, int i9, int i10, boolean z7, boolean z8, float f8) {
        this.f589a = i8;
        this.f590b = i9;
        this.f591c = i10;
        this.f592d = z7;
        this.f593e = z8;
        this.f594f = f8;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 2, this.f589a);
        l3.c.g(parcel, 3, this.f590b);
        l3.c.g(parcel, 4, this.f591c);
        l3.c.c(parcel, 5, this.f592d);
        l3.c.c(parcel, 6, this.f593e);
        l3.c.e(parcel, 7, this.f594f);
        l3.c.b(parcel, a8);
    }
}
