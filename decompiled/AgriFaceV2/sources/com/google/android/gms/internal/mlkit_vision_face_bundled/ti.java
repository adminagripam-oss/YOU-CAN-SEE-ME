package com.google.android.gms.internal.mlkit_vision_face_bundled;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import t3.a;
/* loaded from: classes.dex */
public abstract class ti extends b0 implements ui {
    public ti() {
        super("com.google.mlkit.vision.face.aidls.IFaceDetectorCreator");
    }

    public static ui asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.mlkit.vision.face.aidls.IFaceDetectorCreator");
        return queryLocalInterface instanceof ui ? (ui) queryLocalInterface : new ri(iBinder);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.b0
    protected final boolean d(int i8, Parcel parcel, Parcel parcel2, int i9) {
        if (i8 == 1) {
            c1.b(parcel);
            qi newFaceDetector = newFaceDetector(a.AbstractBinderC0152a.e(parcel.readStrongBinder()), (li) c1.a(parcel, li.CREATOR));
            parcel2.writeNoException();
            parcel2.writeStrongBinder(newFaceDetector == null ? null : newFaceDetector.asBinder());
            return true;
        }
        return false;
    }
}
