package x3;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class k0 extends l3.a {
    public static final Parcelable.Creator<k0> CREATOR = new l0();

    /* renamed from: a  reason: collision with root package name */
    private final int f12680a;

    /* renamed from: b  reason: collision with root package name */
    private final i0 f12681b;

    /* renamed from: c  reason: collision with root package name */
    private final c4.c0 f12682c;

    /* renamed from: d  reason: collision with root package name */
    private final c4.z f12683d;

    /* renamed from: e  reason: collision with root package name */
    private final PendingIntent f12684e;

    /* renamed from: f  reason: collision with root package name */
    private final e1 f12685f;

    /* renamed from: g  reason: collision with root package name */
    private final String f12686g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public k0(int i8, i0 i0Var, IBinder iBinder, IBinder iBinder2, PendingIntent pendingIntent, IBinder iBinder3, String str) {
        this.f12680a = i8;
        this.f12681b = i0Var;
        e1 e1Var = null;
        this.f12682c = iBinder != null ? c4.b0.e(iBinder) : null;
        this.f12684e = pendingIntent;
        this.f12683d = iBinder2 != null ? c4.y.e(iBinder2) : null;
        if (iBinder3 != null) {
            IInterface queryLocalInterface = iBinder3.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
            e1Var = queryLocalInterface instanceof e1 ? (e1) queryLocalInterface : new c1(iBinder3);
        }
        this.f12685f = e1Var;
        this.f12686g = str;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int i9 = this.f12680a;
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 1, i9);
        l3.c.j(parcel, 2, this.f12681b, i8, false);
        c4.c0 c0Var = this.f12682c;
        l3.c.f(parcel, 3, c0Var == null ? null : c0Var.asBinder(), false);
        l3.c.j(parcel, 4, this.f12684e, i8, false);
        c4.z zVar = this.f12683d;
        l3.c.f(parcel, 5, zVar == null ? null : zVar.asBinder(), false);
        e1 e1Var = this.f12685f;
        l3.c.f(parcel, 6, e1Var != null ? e1Var.asBinder() : null, false);
        l3.c.k(parcel, 8, this.f12686g, false);
        l3.c.b(parcel, a8);
    }
}
