package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.Parcel;
import t3.a;
/* loaded from: classes.dex */
public final class n extends w3.a {
    /* JADX INFO: Access modifiers changed from: package-private */
    public n(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.dynamite.IDynamiteLoaderV2");
    }

    public final t3.a d0(t3.a aVar, String str, int i8, t3.a aVar2) {
        Parcel e8 = e();
        w3.c.c(e8, aVar);
        e8.writeString(str);
        e8.writeInt(i8);
        w3.c.c(e8, aVar2);
        Parcel d8 = d(3, e8);
        t3.a e9 = a.AbstractBinderC0152a.e(d8.readStrongBinder());
        d8.recycle();
        return e9;
    }

    public final t3.a f(t3.a aVar, String str, int i8, t3.a aVar2) {
        Parcel e8 = e();
        w3.c.c(e8, aVar);
        e8.writeString(str);
        e8.writeInt(i8);
        w3.c.c(e8, aVar2);
        Parcel d8 = d(2, e8);
        t3.a e9 = a.AbstractBinderC0152a.e(d8.readStrongBinder());
        d8.recycle();
        return e9;
    }
}
