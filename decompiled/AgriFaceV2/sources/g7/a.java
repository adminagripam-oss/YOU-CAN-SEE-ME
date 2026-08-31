package g7;

import d7.g;
import s6.a0;
/* loaded from: classes.dex */
public class a implements Iterable {

    /* renamed from: h  reason: collision with root package name */
    public static final C0089a f7126h = new C0089a(null);

    /* renamed from: e  reason: collision with root package name */
    private final int f7127e;

    /* renamed from: f  reason: collision with root package name */
    private final int f7128f;

    /* renamed from: g  reason: collision with root package name */
    private final int f7129g;

    /* renamed from: g7.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0089a {
        private C0089a() {
        }

        public /* synthetic */ C0089a(g gVar) {
            this();
        }

        public final a a(int i8, int i9, int i10) {
            return new a(i8, i9, i10);
        }
    }

    public a(int i8, int i9, int i10) {
        if (i10 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (i10 == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        }
        this.f7127e = i8;
        this.f7128f = x6.c.b(i8, i9, i10);
        this.f7129g = i10;
    }

    public final int b() {
        return this.f7127e;
    }

    public boolean equals(Object obj) {
        if (obj instanceof a) {
            if (!isEmpty() || !((a) obj).isEmpty()) {
                a aVar = (a) obj;
                if (this.f7127e != aVar.f7127e || this.f7128f != aVar.f7128f || this.f7129g != aVar.f7129g) {
                }
            }
            return true;
        }
        return false;
    }

    public final int f() {
        return this.f7128f;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (((this.f7127e * 31) + this.f7128f) * 31) + this.f7129g;
    }

    public boolean isEmpty() {
        if (this.f7129g > 0) {
            if (this.f7127e > this.f7128f) {
                return true;
            }
        } else if (this.f7127e < this.f7128f) {
            return true;
        }
        return false;
    }

    public final int j() {
        return this.f7129g;
    }

    @Override // java.lang.Iterable
    /* renamed from: k */
    public a0 iterator() {
        return new b(this.f7127e, this.f7128f, this.f7129g);
    }

    public String toString() {
        StringBuilder sb;
        int i8;
        if (this.f7129g > 0) {
            sb = new StringBuilder();
            sb.append(this.f7127e);
            sb.append("..");
            sb.append(this.f7128f);
            sb.append(" step ");
            i8 = this.f7129g;
        } else {
            sb = new StringBuilder();
            sb.append(this.f7127e);
            sb.append(" downTo ");
            sb.append(this.f7128f);
            sb.append(" step ");
            i8 = -this.f7129g;
        }
        sb.append(i8);
        return sb.toString();
    }
}
