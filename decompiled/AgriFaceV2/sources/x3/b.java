package x3;

import android.os.Parcel;
/* loaded from: classes.dex */
public abstract class b extends d implements c {
    public b() {
        super("com.google.android.gms.location.internal.ISettingsCallbacks");
    }

    @Override // x3.d
    protected final boolean d(int i8, Parcel parcel, Parcel parcel2, int i9) {
        if (i8 == 1) {
            n.d(parcel);
            s((c4.i) n.a(parcel, c4.i.CREATOR));
            return true;
        }
        return false;
    }
}
