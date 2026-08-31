package x3;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.WorkSource;
import com.google.android.gms.location.LocationRequest;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public final class i0 extends l3.a {
    public static final Parcelable.Creator<i0> CREATOR = new j0();

    /* renamed from: a  reason: collision with root package name */
    LocationRequest f12676a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i0(LocationRequest locationRequest, List list, boolean z7, boolean z8, boolean z9, boolean z10, String str, long j8) {
        WorkSource workSource;
        LocationRequest.a aVar = new LocationRequest.a(locationRequest);
        if (list != null) {
            if (list.isEmpty()) {
                workSource = null;
            } else {
                workSource = new WorkSource();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    k3.d dVar = (k3.d) it.next();
                    q3.h.a(workSource, dVar.f8362a, dVar.f8363b);
                }
            }
            aVar.n(workSource);
        }
        if (z7) {
            aVar.c(1);
        }
        if (z8) {
            aVar.l(2);
        }
        if (z9) {
            aVar.m(true);
        }
        if (z10) {
            aVar.k(true);
        }
        if (j8 != Long.MAX_VALUE) {
            aVar.e(j8);
        }
        this.f12676a = aVar.a();
    }

    public static i0 a(String str, LocationRequest locationRequest) {
        return new i0(locationRequest, null, false, false, false, false, null, Long.MAX_VALUE);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof i0) {
            return k3.p.a(this.f12676a, ((i0) obj).f12676a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f12676a.hashCode();
    }

    public final String toString() {
        return this.f12676a.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.j(parcel, 1, this.f12676a, i8, false);
        l3.c.b(parcel, a8);
    }
}
