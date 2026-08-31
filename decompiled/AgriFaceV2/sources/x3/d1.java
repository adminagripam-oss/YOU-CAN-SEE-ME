package x3;

import android.os.Parcel;
/* loaded from: classes.dex */
public abstract class d1 extends d implements e1 {
    public d1() {
        super("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
    }

    @Override // x3.d
    protected final boolean d(int i8, Parcel parcel, Parcel parcel2, int i9) {
        if (i8 == 1) {
            n.d(parcel);
            k((a1) n.a(parcel, a1.CREATOR));
        } else if (i8 != 2) {
            return false;
        } else {
            c();
        }
        return true;
    }
}
