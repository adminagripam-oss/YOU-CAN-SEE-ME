package androidx.core.app;

import android.app.RemoteInput;
import android.content.Intent;
import android.os.Bundle;
/* loaded from: classes.dex */
public abstract class q {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a {
        static void a(Object obj, Intent intent, Bundle bundle) {
            RemoteInput.addResultsToIntent((RemoteInput[]) obj, intent, bundle);
        }

        public static RemoteInput b(q qVar) {
            throw null;
        }

        static Bundle c(Intent intent) {
            return RemoteInput.getResultsFromIntent(intent);
        }
    }

    static RemoteInput a(q qVar) {
        return a.b(qVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RemoteInput[] b(q[] qVarArr) {
        if (qVarArr == null) {
            return null;
        }
        RemoteInput[] remoteInputArr = new RemoteInput[qVarArr.length];
        for (int i8 = 0; i8 < qVarArr.length; i8++) {
            q qVar = qVarArr[i8];
            remoteInputArr[i8] = a(null);
        }
        return remoteInputArr;
    }
}
