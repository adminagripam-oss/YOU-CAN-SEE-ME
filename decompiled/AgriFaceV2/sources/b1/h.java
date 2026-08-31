package b1;
/* loaded from: classes.dex */
public class h implements Cloneable {

    /* renamed from: i  reason: collision with root package name */
    private static final Object f4549i = new Object();

    /* renamed from: e  reason: collision with root package name */
    private boolean f4550e;

    /* renamed from: f  reason: collision with root package name */
    private int[] f4551f;

    /* renamed from: g  reason: collision with root package name */
    private Object[] f4552g;

    /* renamed from: h  reason: collision with root package name */
    private int f4553h;

    public h() {
        this(10);
    }

    private void e() {
        int i8 = this.f4553h;
        int[] iArr = this.f4551f;
        Object[] objArr = this.f4552g;
        int i9 = 0;
        for (int i10 = 0; i10 < i8; i10++) {
            Object obj = objArr[i10];
            if (obj != f4549i) {
                if (i10 != i9) {
                    iArr[i9] = iArr[i10];
                    objArr[i9] = obj;
                    objArr[i10] = null;
                }
                i9++;
            }
        }
        this.f4550e = false;
        this.f4553h = i9;
    }

    public void a(int i8, Object obj) {
        int i9 = this.f4553h;
        if (i9 != 0 && i8 <= this.f4551f[i9 - 1]) {
            j(i8, obj);
            return;
        }
        if (this.f4550e && i9 >= this.f4551f.length) {
            e();
        }
        int i10 = this.f4553h;
        if (i10 >= this.f4551f.length) {
            int e8 = c.e(i10 + 1);
            int[] iArr = new int[e8];
            Object[] objArr = new Object[e8];
            int[] iArr2 = this.f4551f;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr2 = this.f4552g;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.f4551f = iArr;
            this.f4552g = objArr;
        }
        this.f4551f[i10] = i8;
        this.f4552g[i10] = obj;
        this.f4553h = i10 + 1;
    }

    public void c() {
        int i8 = this.f4553h;
        Object[] objArr = this.f4552g;
        for (int i9 = 0; i9 < i8; i9++) {
            objArr[i9] = null;
        }
        this.f4553h = 0;
        this.f4550e = false;
    }

    /* renamed from: d */
    public h clone() {
        try {
            h hVar = (h) super.clone();
            hVar.f4551f = (int[]) this.f4551f.clone();
            hVar.f4552g = (Object[]) this.f4552g.clone();
            return hVar;
        } catch (CloneNotSupportedException e8) {
            throw new AssertionError(e8);
        }
    }

    public Object g(int i8) {
        return h(i8, null);
    }

    public Object h(int i8, Object obj) {
        Object obj2;
        int a8 = c.a(this.f4551f, this.f4553h, i8);
        return (a8 < 0 || (obj2 = this.f4552g[a8]) == f4549i) ? obj : obj2;
    }

    public int i(int i8) {
        if (this.f4550e) {
            e();
        }
        return this.f4551f[i8];
    }

    public void j(int i8, Object obj) {
        int a8 = c.a(this.f4551f, this.f4553h, i8);
        if (a8 >= 0) {
            this.f4552g[a8] = obj;
            return;
        }
        int i9 = ~a8;
        int i10 = this.f4553h;
        if (i9 < i10) {
            Object[] objArr = this.f4552g;
            if (objArr[i9] == f4549i) {
                this.f4551f[i9] = i8;
                objArr[i9] = obj;
                return;
            }
        }
        if (this.f4550e && i10 >= this.f4551f.length) {
            e();
            i9 = ~c.a(this.f4551f, this.f4553h, i8);
        }
        int i11 = this.f4553h;
        if (i11 >= this.f4551f.length) {
            int e8 = c.e(i11 + 1);
            int[] iArr = new int[e8];
            Object[] objArr2 = new Object[e8];
            int[] iArr2 = this.f4551f;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.f4552g;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f4551f = iArr;
            this.f4552g = objArr2;
        }
        int i12 = this.f4553h;
        if (i12 - i9 != 0) {
            int[] iArr3 = this.f4551f;
            int i13 = i9 + 1;
            System.arraycopy(iArr3, i9, iArr3, i13, i12 - i9);
            Object[] objArr4 = this.f4552g;
            System.arraycopy(objArr4, i9, objArr4, i13, this.f4553h - i9);
        }
        this.f4551f[i9] = i8;
        this.f4552g[i9] = obj;
        this.f4553h++;
    }

    public int k() {
        if (this.f4550e) {
            e();
        }
        return this.f4553h;
    }

    public Object l(int i8) {
        if (this.f4550e) {
            e();
        }
        return this.f4552g[i8];
    }

    public String toString() {
        if (k() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f4553h * 28);
        sb.append('{');
        for (int i8 = 0; i8 < this.f4553h; i8++) {
            if (i8 > 0) {
                sb.append(", ");
            }
            sb.append(i(i8));
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

    public h(int i8) {
        this.f4550e = false;
        if (i8 == 0) {
            this.f4551f = c.f4511a;
            this.f4552g = c.f4513c;
            return;
        }
        int e8 = c.e(i8);
        this.f4551f = new int[e8];
        this.f4552g = new Object[e8];
    }
}
