package a4;

import android.os.IBinder;
import android.os.Parcel;
/* loaded from: classes.dex */
public final class i8 extends a {
    /* JADX INFO: Access modifiers changed from: package-private */
    public i8(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.face.internal.client.INativeFaceDetector");
    }

    public final void b() {
        f(3, d());
    }

    public final e4[] d0(t3.a aVar, ne neVar) {
        Parcel d8 = d();
        b1.b(d8, aVar);
        b1.a(d8, neVar);
        Parcel e8 = e(1, d8);
        e4[] e4VarArr = (e4[]) e8.createTypedArray(e4.CREATOR);
        e8.recycle();
        return e4VarArr;
    }

    public final e4[] e0(t3.a aVar, t3.a aVar2, t3.a aVar3, int i8, int i9, int i10, int i11, int i12, int i13, ne neVar) {
        Parcel d8 = d();
        b1.b(d8, aVar);
        b1.b(d8, aVar2);
        b1.b(d8, aVar3);
        d8.writeInt(i8);
        d8.writeInt(i9);
        d8.writeInt(i10);
        d8.writeInt(i11);
        d8.writeInt(i12);
        d8.writeInt(i13);
        b1.a(d8, neVar);
        Parcel e8 = e(4, d8);
        e4[] e4VarArr = (e4[]) e8.createTypedArray(e4.CREATOR);
        e8.recycle();
        return e4VarArr;
    }
}
