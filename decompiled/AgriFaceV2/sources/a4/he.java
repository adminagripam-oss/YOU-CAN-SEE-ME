package a4;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class he extends l3.a {
    public static final Parcelable.Creator<he> CREATOR = new ie();

    /* renamed from: a  reason: collision with root package name */
    private final int f652a;

    /* renamed from: b  reason: collision with root package name */
    private final int f653b;

    /* renamed from: c  reason: collision with root package name */
    private final int f654c;

    /* renamed from: d  reason: collision with root package name */
    private final int f655d;

    /* renamed from: e  reason: collision with root package name */
    private final boolean f656e;

    /* renamed from: f  reason: collision with root package name */
    private final float f657f;

    public he(int i8, int i9, int i10, int i11, boolean z7, float f8) {
        this.f652a = i8;
        this.f653b = i9;
        this.f654c = i10;
        this.f655d = i11;
        this.f656e = z7;
        this.f657f = f8;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 1, this.f652a);
        l3.c.g(parcel, 2, this.f653b);
        l3.c.g(parcel, 3, this.f654c);
        l3.c.g(parcel, 4, this.f655d);
        l3.c.c(parcel, 5, this.f656e);
        l3.c.e(parcel, 6, this.f657f);
        l3.c.b(parcel, a8);
    }
}
