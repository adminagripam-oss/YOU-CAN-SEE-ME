package z3;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class sb implements pb {

    /* renamed from: a  reason: collision with root package name */
    final List f14078a;

    public sb(Context context, rb rbVar) {
        ArrayList arrayList = new ArrayList();
        this.f14078a = arrayList;
        if (rbVar.c()) {
            arrayList.add(new cc(context, rbVar));
        }
    }

    @Override // z3.pb
    public final void a(ob obVar) {
        for (pb pbVar : this.f14078a) {
            pbVar.a(obVar);
        }
    }
}
