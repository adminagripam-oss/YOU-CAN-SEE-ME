package k3;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public class d extends l3.a {
    public static final Parcelable.Creator<d> CREATOR = new x();

    /* renamed from: a  reason: collision with root package name */
    public final int f8362a;

    /* renamed from: b  reason: collision with root package name */
    public final String f8363b;

    public d(int i8, String str) {
        this.f8362a = i8;
        this.f8363b = str;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof d) {
            d dVar = (d) obj;
            return dVar.f8362a == this.f8362a && p.a(dVar.f8363b, this.f8363b);
        }
        return false;
    }

    public final int hashCode() {
        return this.f8362a;
    }

    public final String toString() {
        return this.f8362a + ":" + this.f8363b;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int i9 = this.f8362a;
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 1, i9);
        l3.c.k(parcel, 2, this.f8363b, false);
        l3.c.b(parcel, a8);
    }
}
