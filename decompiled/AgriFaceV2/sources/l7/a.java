package l7;

import android.os.Looper;
import java.util.List;
import k7.o1;
import p7.v;
/* loaded from: classes.dex */
public final class a implements v {
    @Override // p7.v
    public String a() {
        return "For tests Dispatchers.setMain from kotlinx-coroutines-test module can be used";
    }

    @Override // p7.v
    public o1 b(List list) {
        Looper mainLooper = Looper.getMainLooper();
        if (mainLooper != null) {
            return new c(e.a(mainLooper, true), null, 2, null);
        }
        throw new IllegalStateException("The main looper is not available");
    }

    @Override // p7.v
    public int c() {
        return 1073741823;
    }
}
