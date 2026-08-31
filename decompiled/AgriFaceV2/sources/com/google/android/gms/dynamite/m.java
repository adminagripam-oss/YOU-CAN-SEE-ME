package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.Parcel;
import t3.a;
/* loaded from: classes.dex */
public final class m extends w3.a {
    /* JADX INFO: Access modifiers changed from: package-private */
    public m(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.dynamite.IDynamiteLoader");
    }

    public final int d0(t3.a aVar, String str, boolean z7) {
        Parcel e8 = e();
        w3.c.c(e8, aVar);
        e8.writeString(str);
        e8.writeInt(z7 ? 1 : 0);
        Parcel d8 = d(3, e8);
        int readInt = d8.readInt();
        d8.recycle();
        return readInt;
    }

    public final int e0(t3.a aVar, String str, boolean z7) {
        Parcel e8 = e();
        w3.c.c(e8, aVar);
        e8.writeString(str);
        e8.writeInt(z7 ? 1 : 0);
        Parcel d8 = d(5, e8);
        int readInt = d8.readInt();
        d8.recycle();
        return readInt;
    }

    public final int f() {
        Parcel d8 = d(6, e());
        int readInt = d8.readInt();
        d8.recycle();
        return readInt;
    }

    public final t3.a f0(t3.a aVar, String str, int i8) {
        Parcel e8 = e();
        w3.c.c(e8, aVar);
        e8.writeString(str);
        e8.writeInt(i8);
        Parcel d8 = d(2, e8);
        t3.a e9 = a.AbstractBinderC0152a.e(d8.readStrongBinder());
        d8.recycle();
        return e9;
    }

    public final t3.a g0(t3.a aVar, String str, int i8, t3.a aVar2) {
        Parcel e8 = e();
        w3.c.c(e8, aVar);
        e8.writeString(str);
        e8.writeInt(i8);
        w3.c.c(e8, aVar2);
        Parcel d8 = d(8, e8);
        t3.a e9 = a.AbstractBinderC0152a.e(d8.readStrongBinder());
        d8.recycle();
        return e9;
    }

    public final t3.a h0(t3.a aVar, String str, int i8) {
        Parcel e8 = e();
        w3.c.c(e8, aVar);
        e8.writeString(str);
        e8.writeInt(i8);
        Parcel d8 = d(4, e8);
        t3.a e9 = a.AbstractBinderC0152a.e(d8.readStrongBinder());
        d8.recycle();
        return e9;
    }

    public final t3.a i0(t3.a aVar, String str, boolean z7, long j8) {
        Parcel e8 = e();
        w3.c.c(e8, aVar);
        e8.writeString(str);
        e8.writeInt(z7 ? 1 : 0);
        e8.writeLong(j8);
        Parcel d8 = d(7, e8);
        t3.a e9 = a.AbstractBinderC0152a.e(d8.readStrongBinder());
        d8.recycle();
        return e9;
    }
}
