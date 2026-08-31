package a4;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class de extends l3.a {
    public static final Parcelable.Creator<de> CREATOR = new ee();

    /* renamed from: a  reason: collision with root package name */
    private final int f483a;

    /* renamed from: b  reason: collision with root package name */
    private final int f484b;

    /* renamed from: c  reason: collision with root package name */
    private final int f485c;

    /* renamed from: d  reason: collision with root package name */
    private final int f486d;

    /* renamed from: e  reason: collision with root package name */
    private final long f487e;

    public de(int i8, int i9, int i10, int i11, long j8) {
        this.f483a = i8;
        this.f484b = i9;
        this.f485c = i10;
        this.f486d = i11;
        this.f487e = j8;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 1, this.f483a);
        l3.c.g(parcel, 2, this.f484b);
        l3.c.g(parcel, 3, this.f485c);
        l3.c.g(parcel, 4, this.f486d);
        l3.c.i(parcel, 5, this.f487e);
        l3.c.b(parcel, a8);
    }
}
