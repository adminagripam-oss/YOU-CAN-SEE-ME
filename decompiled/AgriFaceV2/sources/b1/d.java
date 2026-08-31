package b1;
/* loaded from: classes.dex */
public class d implements Cloneable {

    /* renamed from: i  reason: collision with root package name */
    private static final Object f4514i = new Object();

    /* renamed from: e  reason: collision with root package name */
    private boolean f4515e;

    /* renamed from: f  reason: collision with root package name */
    private long[] f4516f;

    /* renamed from: g  reason: collision with root package name */
    private Object[] f4517g;

    /* renamed from: h  reason: collision with root package name */
    private int f4518h;

    public d() {
        this(10);
    }

    private void d() {
        int i8 = this.f4518h;
        long[] jArr = this.f4516f;
        Object[] objArr = this.f4517g;
        int i9 = 0;
        for (int i10 = 0; i10 < i8; i10++) {
            Object obj = objArr[i10];
            if (obj != f4514i) {
                if (i10 != i9) {
                    jArr[i9] = jArr[i10];
                    objArr[i9] = obj;
                    objArr[i10] = null;
                }
                i9++;
            }
        }
        this.f4515e = false;
        this.f4518h = i9;
    }

    public void a() {
        int i8 = this.f4518h;
        Object[] objArr = this.f4517g;
        for (int i9 = 0; i9 < i8; i9++) {
            objArr[i9] = null;
        }
        this.f4518h = 0;
        this.f4515e = false;
    }

    /* renamed from: c */
    public d clone() {
        try {
            d dVar = (d) super.clone();
            dVar.f4516f = (long[]) this.f4516f.clone();
            dVar.f4517g = (Object[]) this.f4517g.clone();
            return dVar;
        } catch (CloneNotSupportedException e8) {
            throw new AssertionError(e8);
        }
    }

    public Object e(long j8) {
        return g(j8, null);
    }

    public Object g(long j8, Object obj) {
        Object obj2;
        int b8 = c.b(this.f4516f, this.f4518h, j8);
        return (b8 < 0 || (obj2 = this.f4517g[b8]) == f4514i) ? obj : obj2;
    }

    public long h(int i8) {
        if (this.f4515e) {
            d();
        }
        return this.f4516f[i8];
    }

    public void i(long j8, Object obj) {
        int b8 = c.b(this.f4516f, this.f4518h, j8);
        if (b8 >= 0) {
            this.f4517g[b8] = obj;
            return;
        }
        int i8 = ~b8;
        int i9 = this.f4518h;
        if (i8 < i9) {
            Object[] objArr = this.f4517g;
            if (objArr[i8] == f4514i) {
                this.f4516f[i8] = j8;
                objArr[i8] = obj;
                return;
            }
        }
        if (this.f4515e && i9 >= this.f4516f.length) {
            d();
            i8 = ~c.b(this.f4516f, this.f4518h, j8);
        }
        int i10 = this.f4518h;
        if (i10 >= this.f4516f.length) {
            int f8 = c.f(i10 + 1);
            long[] jArr = new long[f8];
            Object[] objArr2 = new Object[f8];
            long[] jArr2 = this.f4516f;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.f4517g;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f4516f = jArr;
            this.f4517g = objArr2;
        }
        int i11 = this.f4518h;
        if (i11 - i8 != 0) {
            long[] jArr3 = this.f4516f;
            int i12 = i8 + 1;
            System.arraycopy(jArr3, i8, jArr3, i12, i11 - i8);
            Object[] objArr4 = this.f4517g;
            System.arraycopy(objArr4, i8, objArr4, i12, this.f4518h - i8);
        }
        this.f4516f[i8] = j8;
        this.f4517g[i8] = obj;
        this.f4518h++;
    }

    public void j(long j8) {
        int b8 = c.b(this.f4516f, this.f4518h, j8);
        if (b8 >= 0) {
            Object[] objArr = this.f4517g;
            Object obj = objArr[b8];
            Object obj2 = f4514i;
            if (obj != obj2) {
                objArr[b8] = obj2;
                this.f4515e = true;
            }
        }
    }

    public int k() {
        if (this.f4515e) {
            d();
        }
        return this.f4518h;
    }

    public Object l(int i8) {
        if (this.f4515e) {
            d();
        }
        return this.f4517g[i8];
    }

    public String toString() {
        if (k() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f4518h * 28);
        sb.append('{');
        for (int i8 = 0; i8 < this.f4518h; i8++) {
            if (i8 > 0) {
                sb.append(", ");
            }
            sb.append(h(i8));
            sb.append('=');
            Object l8 = l(i8);
            if (l8 != this) {
                sb.append(l8);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public d(int i8) {
        this.f4515e = false;
        if (i8 == 0) {
            this.f4516f = c.f4512b;
            this.f4517g = c.f4513c;
            return;
        }
        int f8 = c.f(i8);
        this.f4516f = new long[f8];
        this.f4517g = new Object[f8];
    }
}
