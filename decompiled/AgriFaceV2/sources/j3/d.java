package j3;

import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.common.api.Status;
/* loaded from: classes.dex */
public interface d extends IInterface {

    /* loaded from: classes.dex */
    public static abstract class a extends v3.b implements d {
        public a() {
            super("com.google.android.gms.common.api.internal.IStatusCallback");
        }

        @Override // v3.b
        protected final boolean d0(int i8, Parcel parcel, Parcel parcel2, int i9) {
            if (i8 == 1) {
                v3.c.b(parcel);
                H((Status) v3.c.a(parcel, Status.CREATOR));
                return true;
            }
            return false;
        }
    }

    void H(Status status);
}
