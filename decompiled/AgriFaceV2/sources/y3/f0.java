package y3;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class f0 implements b0 {

    /* renamed from: a  reason: collision with root package name */
    final List f13223a;

    public f0(Context context, e0 e0Var) {
        ArrayList arrayList = new ArrayList();
        this.f13223a = arrayList;
        if (e0Var.c()) {
            arrayList.add(new n0(context, e0Var));
        }
    }
}
