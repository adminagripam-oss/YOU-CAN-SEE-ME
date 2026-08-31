package com.google.android.gms.vision.face.mlkit;

import android.os.RemoteException;
import q3.g;
/* loaded from: classes.dex */
abstract class c {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static RemoteException a(String str) {
        return g.a() ? new RemoteException(str) : new RemoteException();
    }
}
