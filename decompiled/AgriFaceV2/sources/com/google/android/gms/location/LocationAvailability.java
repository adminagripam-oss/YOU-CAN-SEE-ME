package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import c4.r;
import com.google.android.gms.common.internal.ReflectedParcelable;
import java.util.Arrays;
import k3.p;
/* loaded from: classes.dex */
public final class LocationAvailability extends l3.a implements ReflectedParcelable {

    /* renamed from: a  reason: collision with root package name */
    private final int f5987a;

    /* renamed from: b  reason: collision with root package name */
    private final int f5988b;

    /* renamed from: c  reason: collision with root package name */
    private final long f5989c;

    /* renamed from: d  reason: collision with root package name */
    final int f5990d;

    /* renamed from: e  reason: collision with root package name */
    private final r[] f5991e;

    /* renamed from: f  reason: collision with root package name */
    public static final LocationAvailability f5985f = new LocationAvailability(0, 1, 1, 0, null, true);

    /* renamed from: g  reason: collision with root package name */
    public static final LocationAvailability f5986g = new LocationAvailability(1000, 1, 1, 0, null, false);
    public static final Parcelable.Creator<LocationAvailability> CREATOR = new a();

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocationAvailability(int i8, int i9, int i10, long j8, r[] rVarArr, boolean z7) {
        this.f5990d = i8 < 1000 ? 0 : 1000;
        this.f5987a = i9;
        this.f5988b = i10;
        this.f5989c = j8;
        this.f5991e = rVarArr;
    }

    public boolean a() {
        return this.f5990d < 1000;
    }

    public boolean equals(Object obj) {
        if (obj instanceof LocationAvailability) {
            LocationAvailability locationAvailability = (LocationAvailability) obj;
            if (this.f5987a == locationAvailability.f5987a && this.f5988b == locationAvailability.f5988b && this.f5989c == locationAvailability.f5989c && this.f5990d == locationAvailability.f5990d && Arrays.equals(this.f5991e, locationAvailability.f5991e)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return p.b(Integer.valueOf(this.f5990d));
    }

    public String toString() {
        boolean a8 = a();
        StringBuilder sb = new StringBuilder(String.valueOf(a8).length() + 22);
        sb.append("LocationAvailability[");
        sb.append(a8);
        sb.append("]");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        int i9 = this.f5987a;
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 1, i9);
        l3.c.g(parcel, 2, this.f5988b);
        l3.c.i(parcel, 3, this.f5989c);
        l3.c.g(parcel, 4, this.f5990d);
        l3.c.m(parcel, 5, this.f5991e, i8, false);
        l3.c.c(parcel, 6, a());
        l3.c.b(parcel, a8);
    }
}
