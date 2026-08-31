package k3;

import android.os.Bundle;
import android.os.Parcel;
/* loaded from: classes.dex */
public abstract class p0 extends w3.b implements l {
    public p0() {
        super("com.google.android.gms.common.internal.IGmsCallbacks");
    }

    @Override // w3.b
    protected final boolean d(int i8, Parcel parcel, Parcel parcel2, int i9) {
        if (i8 == 1) {
            w3.c.b(parcel);
            c0(parcel.readInt(), parcel.readStrongBinder(), (Bundle) w3.c.a(parcel, Bundle.CREATOR));
        } else if (i8 == 2) {
            w3.c.b(parcel);
            I(parcel.readInt(), (Bundle) w3.c.a(parcel, Bundle.CREATOR));
        } else if (i8 != 3) {
            return false;
        } else {
            w3.c.b(parcel);
            E(parcel.readInt(), parcel.readStrongBinder(), (a1) w3.c.a(parcel, a1.CREATOR));
        }
        parcel2.writeNoException();
        return true;
    }
}
