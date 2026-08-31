package k7;
/* loaded from: classes.dex */
public enum f0 {
    DEFAULT,
    LAZY,
    ATOMIC,
    UNDISPATCHED;

    /* loaded from: classes.dex */
    public /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f8734a;

        static {
            int[] iArr = new int[f0.values().length];
            try {
                iArr[f0.DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[f0.ATOMIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[f0.UNDISPATCHED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[f0.LAZY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            f8734a = iArr;
        }
    }

    public final void e(c7.p pVar, Object obj, t6.d dVar) {
        int i8 = a.f8734a[ordinal()];
        if (i8 == 1) {
            q7.a.d(pVar, obj, dVar, null, 4, null);
        } else if (i8 == 2) {
            t6.f.a(pVar, obj, dVar);
        } else if (i8 == 3) {
            q7.b.a(pVar, obj, dVar);
        } else if (i8 != 4) {
            throw new r6.i();
        }
    }

    public final boolean f() {
        return this == LAZY;
    }
}
