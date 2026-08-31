package a4;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes.dex */
final class z extends AbstractSet {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ f0 f1323e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public z(f0 f0Var) {
        this.f1323e = f0Var;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.f1323e.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        int x7;
        Map n8 = this.f1323e.n();
        if (n8 != null) {
            return n8.entrySet().contains(obj);
        }
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            x7 = this.f1323e.x(entry.getKey());
            if (x7 != -1 && ze.a(f0.l(this.f1323e, x7), entry.getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        f0 f0Var = this.f1323e;
        Map n8 = f0Var.n();
        return n8 != null ? n8.entrySet().iterator() : new x(f0Var);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        int w7;
        int[] B;
        Object[] a8;
        Object[] b8;
        Map n8 = this.f1323e.n();
        if (n8 != null) {
            return n8.entrySet().remove(obj);
        }
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            f0 f0Var = this.f1323e;
            if (f0Var.s()) {
                return false;
            }
            w7 = f0Var.w();
            Object key = entry.getKey();
            Object value = entry.getValue();
            Object m8 = f0.m(this.f1323e);
            B = this.f1323e.B();
            a8 = this.f1323e.a();
            b8 = this.f1323e.b();
            int b9 = g0.b(key, value, w7, m8, B, a8, b8);
            if (b9 == -1) {
                return false;
            }
            this.f1323e.r(b9, w7);
            f0.d(this.f1323e);
            this.f1323e.p();
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f1323e.size();
    }
}
