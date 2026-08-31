package k3;

import android.content.Context;
import android.util.SparseIntArray;
import i3.a;
/* loaded from: classes.dex */
public final class h0 {

    /* renamed from: a  reason: collision with root package name */
    private final SparseIntArray f8418a = new SparseIntArray();

    /* renamed from: b  reason: collision with root package name */
    private h3.e f8419b;

    public h0(h3.e eVar) {
        q.g(eVar);
        this.f8419b = eVar;
    }

    public final int a(Context context, int i8) {
        return this.f8418a.get(i8, -1);
    }

    public final int b(Context context, a.f fVar) {
        q.g(context);
        q.g(fVar);
        int i8 = 0;
        if (fVar.e()) {
            int g8 = fVar.g();
            int a8 = a(context, g8);
            if (a8 == -1) {
                int i9 = 0;
                while (true) {
                    if (i9 >= this.f8418a.size()) {
                        i8 = -1;
                        break;
                    }
                    int keyAt = this.f8418a.keyAt(i9);
                    if (keyAt > g8 && this.f8418a.get(keyAt) == 0) {
                        break;
                    }
                    i9++;
                }
                a8 = i8 == -1 ? this.f8419b.h(context, g8) : i8;
                this.f8418a.put(g8, a8);
            }
            return a8;
        }
        return 0;
    }

    public final void c() {
        this.f8418a.clear();
    }
}
