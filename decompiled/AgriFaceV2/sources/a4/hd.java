package a4;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class hd implements pd {

    /* renamed from: a  reason: collision with root package name */
    final List f651a;

    public hd(Context context, gd gdVar) {
        ArrayList arrayList = new ArrayList();
        this.f651a = arrayList;
        if (gdVar.c()) {
            arrayList.add(new yd(context, gdVar));
        }
    }

    @Override // a4.pd
    public final void a(ed edVar) {
        for (pd pdVar : this.f651a) {
            pdVar.a(edVar);
        }
    }
}
