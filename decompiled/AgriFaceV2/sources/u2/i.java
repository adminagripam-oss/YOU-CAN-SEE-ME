package u2;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
/* loaded from: classes.dex */
abstract class i {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static Executor a() {
        return new k(Executors.newSingleThreadExecutor());
    }
}
