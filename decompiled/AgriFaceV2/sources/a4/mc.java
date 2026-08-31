package a4;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class mc extends l3.a {
    public static final Parcelable.Creator<mc> CREATOR = new nd();

    /* renamed from: a  reason: collision with root package name */
    public final int f829a;

    /* renamed from: b  reason: collision with root package name */
    public final float f830b;

    /* renamed from: c  reason: collision with root package name */
    public final float f831c;

    /* renamed from: d  reason: collision with root package name */
    public final int f832d;

    public mc(int i8, float f8, float f9, int i9) {
        this.f829a = i8;
        this.f830b = f8;
        this.f831c = f9;
        this.f832d = i9;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 1, this.f829a);
        l3.c.e(parcel, 2, this.f830b);
        l3.c.e(parcel, 3, this.f831c);
        l3.c.g(parcel, 4, this.f832d);
        l3.c.b(parcel, a8);
    }
}
