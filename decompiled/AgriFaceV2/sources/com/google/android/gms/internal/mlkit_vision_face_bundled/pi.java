package com.google.android.gms.internal.mlkit_vision_face_bundled;

import android.os.Parcel;
import java.util.List;
import t3.a;
/* loaded from: classes.dex */
public abstract class pi extends b0 implements qi {
    public pi() {
        super("com.google.mlkit.vision.face.aidls.IFaceDetector");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.b0
    protected final boolean d(int i8, Parcel parcel, Parcel parcel2, int i9) {
        if (i8 == 1) {
            a();
        } else if (i8 != 2) {
            if (i8 != 3) {
                return false;
            }
            c1.b(parcel);
            List l8 = l(a.AbstractBinderC0152a.e(parcel.readStrongBinder()), (hi) c1.a(parcel, hi.CREATOR));
            parcel2.writeNoException();
            parcel2.writeTypedList(l8);
            return true;
        } else {
            b();
        }
        parcel2.writeNoException();
        return true;
    }
}
