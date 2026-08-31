package a4;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes.dex */
final class v0 extends m1 {
    /* JADX INFO: Access modifiers changed from: package-private */
    public v0(Iterator it) {
        super(it);
    }

    @Override // a4.m1
    final /* synthetic */ Object b(Object obj) {
        return ((Map.Entry) obj).getValue();
    }
}
