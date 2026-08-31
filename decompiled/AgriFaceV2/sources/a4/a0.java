package a4;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: classes.dex */
abstract class a0 implements Iterator {

    /* renamed from: e  reason: collision with root package name */
    int f230e;

    /* renamed from: f  reason: collision with root package name */
    int f231f;

    /* renamed from: g  reason: collision with root package name */
    int f232g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ f0 f233h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ a0(f0 f0Var, w wVar) {
        int i8;
        this.f233h = f0Var;
        i8 = f0Var.f542i;
        this.f230e = i8;
        this.f231f = f0Var.g();
        this.f232g = -1;
    }

    private final void c() {
        int i8;
        i8 = this.f233h.f542i;
        if (i8 != this.f230e) {
            throw new ConcurrentModificationException();
        }
    }

    abstract Object b(int i8);

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f231f >= 0;
    }

    @Override // java.util.Iterator
    public final Object next() {
        c();
        if (hasNext()) {
            int i8 = this.f231f;
            this.f232g = i8;
            Object b8 = b(i8);
            this.f231f = this.f233h.h(this.f231f);
            return b8;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        c();
        c.d(this.f232g >= 0, "no calls to next() since the last call to remove()");
        this.f230e += 32;
        f0 f0Var = this.f233h;
        f0Var.remove(f0.i(f0Var, this.f232g));
        this.f231f--;
        this.f232g = -1;
    }
}
