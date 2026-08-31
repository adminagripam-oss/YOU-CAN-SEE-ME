package a4;

import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class c2 extends l3.a {
    public static final Parcelable.Creator<c2> CREATOR = new d3();

    /* renamed from: a  reason: collision with root package name */
    public final PointF[] f300a;

    /* renamed from: b  reason: collision with root package name */
    public final int f301b;

    public c2(PointF[] pointFArr, int i8) {
        this.f300a = pointFArr;
        this.f301b = i8;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.m(parcel, 2, this.f300a, i8, false);
        l3.c.g(parcel, 3, this.f301b);
        l3.c.b(parcel, a8);
    }
}
