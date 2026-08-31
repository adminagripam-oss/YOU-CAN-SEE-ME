package x3;

import android.location.Location;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.location.LocationRequest;
/* loaded from: classes.dex */
public final class f1 extends a implements g1 {
    /* JADX INFO: Access modifiers changed from: package-private */
    public f1(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.location.internal.IGoogleLocationManagerService");
    }

    @Override // x3.g1
    public final void A(g0 g0Var, LocationRequest locationRequest, j3.d dVar) {
        Parcel d8 = d();
        n.b(d8, g0Var);
        n.b(d8, locationRequest);
        n.c(d8, dVar);
        f(88, d8);
    }

    @Override // x3.g1
    public final void D(c4.g gVar, c cVar, String str) {
        Parcel d8 = d();
        n.b(d8, gVar);
        n.c(d8, cVar);
        d8.writeString(null);
        f(63, d8);
    }

    @Override // x3.g1
    public final void F(c4.d dVar, g0 g0Var) {
        Parcel d8 = d();
        n.b(d8, dVar);
        n.b(d8, g0Var);
        f(90, d8);
    }

    @Override // x3.g1
    public final void L(k0 k0Var) {
        Parcel d8 = d();
        n.b(d8, k0Var);
        f(59, d8);
    }

    @Override // x3.g1
    public final Location Q() {
        Parcel e8 = e(7, d());
        Location location = (Location) n.a(e8, Location.CREATOR);
        e8.recycle();
        return location;
    }

    @Override // x3.g1
    public final void S(g0 g0Var, j3.d dVar) {
        Parcel d8 = d();
        n.b(d8, g0Var);
        n.c(d8, dVar);
        f(89, d8);
    }

    @Override // x3.g1
    public final void n(c4.d dVar, i1 i1Var) {
        Parcel d8 = d();
        n.b(d8, dVar);
        n.c(d8, i1Var);
        f(82, d8);
    }
}
