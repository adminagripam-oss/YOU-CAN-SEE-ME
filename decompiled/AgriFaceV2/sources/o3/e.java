package o3;

import android.os.Parcel;
import com.google.android.gms.common.api.Status;
/* loaded from: classes.dex */
public abstract class e extends v3.b implements f {
    public e() {
        super("com.google.android.gms.common.moduleinstall.internal.IModuleInstallCallbacks");
    }

    @Override // v3.b
    protected final boolean d0(int i8, Parcel parcel, Parcel parcel2, int i9) {
        if (i8 == 1) {
            v3.c.b(parcel);
            Y((Status) v3.c.a(parcel, Status.CREATOR), (n3.b) v3.c.a(parcel, n3.b.CREATOR));
        } else if (i8 == 2) {
            v3.c.b(parcel);
            u((Status) v3.c.a(parcel, Status.CREATOR), (n3.g) v3.c.a(parcel, n3.g.CREATOR));
        } else if (i8 == 3) {
            v3.c.b(parcel);
            m((Status) v3.c.a(parcel, Status.CREATOR), (n3.e) v3.c.a(parcel, n3.e.CREATOR));
        } else if (i8 != 4) {
            return false;
        } else {
            v3.c.b(parcel);
            O((Status) v3.c.a(parcel, Status.CREATOR));
        }
        return true;
    }
}
