package g7;

import d7.g;
/* loaded from: classes.dex */
public final class c extends g7.a {

    /* renamed from: i  reason: collision with root package name */
    public static final a f7134i = new a(null);

    /* renamed from: j  reason: collision with root package name */
    private static final c f7135j = new c(1, 0);

    /* loaded from: classes.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(g gVar) {
            this();
        }
    }

    public c(int i8, int i9) {
        super(i8, i9, 1);
    }

    @Override // g7.a
    public boolean equals(Object obj) {
        if (obj instanceof c) {
            if (!isEmpty() || !((c) obj).isEmpty()) {
                c cVar = (c) obj;
                if (b() != cVar.b() || f() != cVar.f()) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // g7.a
    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (b() * 31) + f();
    }

    @Override // g7.a
    public boolean isEmpty() {
        return b() > f();
    }

    public boolean l(int i8) {
        return b() <= i8 && i8 <= f();
    }

    @Override // g7.a
    public String toString() {
        return b() + ".." + f();
    }
}
