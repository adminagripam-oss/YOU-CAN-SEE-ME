package k3;

import android.os.Parcel;
/* loaded from: classes.dex */
public abstract class n1 extends w3.b implements o0 {
    public n1() {
        super("com.google.android.gms.common.internal.ICertData");
    }

    @Override // w3.b
    protected final boolean d(int i8, Parcel parcel, Parcel parcel2, int i9) {
        if (i8 == 1) {
            t3.a b8 = b();
            parcel2.writeNoException();
            w3.c.c(parcel2, b8);
        } else if (i8 != 2) {
            return false;
        } else {
            int a8 = a();
            parcel2.writeNoException();
            parcel2.writeInt(a8);
        }
        return true;
    }
}
