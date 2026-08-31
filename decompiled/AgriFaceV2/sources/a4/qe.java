package a4;

import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class qe extends l3.a {
    public static final Parcelable.Creator<qe> CREATOR = new re();

    /* renamed from: a  reason: collision with root package name */
    private final int f998a;

    /* renamed from: b  reason: collision with root package name */
    private final PointF f999b;

    public qe(int i8, PointF pointF) {
        this.f998a = i8;
        this.f999b = pointF;
    }

    public final int a() {
        return this.f998a;
    }

    public final PointF b() {
        return this.f999b;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 1, this.f998a);
        l3.c.j(parcel, 2, this.f999b, i8, false);
        l3.c.b(parcel, a8);
    }
}
