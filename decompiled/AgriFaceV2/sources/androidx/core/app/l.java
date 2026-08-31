package androidx.core.app;

import android.os.Bundle;
import androidx.core.app.j;
import androidx.core.graphics.drawable.IconCompat;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class l {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f2893a = new Object();

    /* renamed from: b  reason: collision with root package name */
    private static final Object f2894b = new Object();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Bundle a(j.a aVar) {
        Bundle bundle = new Bundle();
        IconCompat d8 = aVar.d();
        bundle.putInt("icon", d8 != null ? d8.b() : 0);
        bundle.putCharSequence("title", aVar.h());
        bundle.putParcelable("actionIntent", aVar.a());
        Bundle bundle2 = aVar.c() != null ? new Bundle(aVar.c()) : new Bundle();
        bundle2.putBoolean("android.support.allowGeneratedReplies", aVar.b());
        bundle.putBundle("extras", bundle2);
        bundle.putParcelableArray("remoteInputs", c(aVar.e()));
        bundle.putBoolean("showsUserInterface", aVar.g());
        bundle.putInt("semanticAction", aVar.f());
        return bundle;
    }

    private static Bundle b(q qVar) {
        new Bundle();
        throw null;
    }

    private static Bundle[] c(q[] qVarArr) {
        if (qVarArr == null) {
            return null;
        }
        Bundle[] bundleArr = new Bundle[qVarArr.length];
        for (int i8 = 0; i8 < qVarArr.length; i8++) {
            q qVar = qVarArr[i8];
            bundleArr[i8] = b(null);
        }
        return bundleArr;
    }
}
