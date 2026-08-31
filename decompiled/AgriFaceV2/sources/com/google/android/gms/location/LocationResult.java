package com.google.android.gms.location;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import c4.q;
import com.google.android.gms.common.internal.ReflectedParcelable;
import java.util.Collections;
import java.util.List;
import k3.p;
/* loaded from: classes.dex */
public final class LocationResult extends l3.a implements ReflectedParcelable {

    /* renamed from: a  reason: collision with root package name */
    private final List f6021a;

    /* renamed from: b  reason: collision with root package name */
    static final List f6020b = Collections.emptyList();
    public static final Parcelable.Creator<LocationResult> CREATOR = new c();

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocationResult(List list) {
        this.f6021a = list;
    }

    public Location a() {
        int size = this.f6021a.size();
        if (size == 0) {
            return null;
        }
        return (Location) this.f6021a.get(size - 1);
    }

    public List b() {
        return this.f6021a;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean equals(java.lang.Object r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof com.google.android.gms.location.LocationResult
            r1 = 0
            if (r0 == 0) goto L8c
            com.google.android.gms.location.LocationResult r9 = (com.google.android.gms.location.LocationResult) r9
            int r0 = android.os.Build.VERSION.SDK_INT
            r2 = 31
            if (r0 < r2) goto L16
            java.util.List r0 = r8.f6021a
            java.util.List r9 = r9.f6021a
            boolean r9 = r0.equals(r9)
            return r9
        L16:
            java.util.List r0 = r8.f6021a
            int r0 = r0.size()
            java.util.List r2 = r9.f6021a
            int r2 = r2.size()
            if (r0 == r2) goto L25
            return r1
        L25:
            java.util.List r0 = r8.f6021a
            java.util.Iterator r0 = r0.iterator()
            java.util.List r9 = r9.f6021a
            java.util.Iterator r9 = r9.iterator()
        L31:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L8a
            java.lang.Object r2 = r0.next()
            android.location.Location r2 = (android.location.Location) r2
            java.lang.Object r3 = r9.next()
            android.location.Location r3 = (android.location.Location) r3
            double r4 = r2.getLatitude()
            double r6 = r3.getLatitude()
            int r4 = java.lang.Double.compare(r4, r6)
            if (r4 == 0) goto L52
            return r1
        L52:
            double r4 = r2.getLongitude()
            double r6 = r3.getLongitude()
            int r4 = java.lang.Double.compare(r4, r6)
            if (r4 == 0) goto L61
            return r1
        L61:
            long r4 = r2.getTime()
            long r6 = r3.getTime()
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 == 0) goto L6e
            return r1
        L6e:
            long r4 = r2.getElapsedRealtimeNanos()
            long r6 = r3.getElapsedRealtimeNanos()
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 == 0) goto L7b
            return r1
        L7b:
            java.lang.String r2 = r2.getProvider()
            java.lang.String r3 = r3.getProvider()
            boolean r2 = k3.p.a(r2, r3)
            if (r2 != 0) goto L31
            return r1
        L8a:
            r9 = 1
            return r9
        L8c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.LocationResult.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return p.b(this.f6021a);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LocationResult");
        int i8 = q.f4811d;
        List<Location> list = this.f6021a;
        sb.ensureCapacity(list.size() * 100);
        sb.append("[");
        boolean z7 = false;
        for (Location location : list) {
            q.a(location, sb);
            sb.append(", ");
            z7 = true;
        }
        if (z7) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.n(parcel, 1, b(), false);
        l3.c.b(parcel, a8);
    }
}
