package k0;

import java.util.ArrayDeque;
import k0.c;
/* loaded from: classes.dex */
public class a implements c {

    /* renamed from: a  reason: collision with root package name */
    private final int f8294a;

    /* renamed from: b  reason: collision with root package name */
    private final ArrayDeque f8295b;

    /* renamed from: c  reason: collision with root package name */
    private final Object f8296c;

    /* renamed from: d  reason: collision with root package name */
    final c.a f8297d;

    public a(int i8) {
        this(i8, null);
    }

    @Override // k0.c
    public Object a() {
        Object removeLast;
        synchronized (this.f8296c) {
            removeLast = this.f8295b.removeLast();
        }
        return removeLast;
    }

    @Override // k0.c
    public void b(Object obj) {
        Object a8;
        synchronized (this.f8296c) {
            a8 = this.f8295b.size() >= this.f8294a ? a() : null;
            this.f8295b.addFirst(obj);
        }
        c.a aVar = this.f8297d;
        if (aVar == null || a8 == null) {
            return;
        }
        aVar.a(a8);
    }

    @Override // k0.c
    public boolean isEmpty() {
        boolean isEmpty;
        synchronized (this.f8296c) {
            isEmpty = this.f8295b.isEmpty();
        }
        return isEmpty;
    }

    public a(int i8, c.a aVar) {
        this.f8296c = new Object();
        this.f8294a = i8;
        this.f8295b = new ArrayDeque(i8);
        this.f8297d = aVar;
    }
}
